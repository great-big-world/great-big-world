package com.github.creoii.greatbigworld.entity.brain.task;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.WalkTarget;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

public class RamImpactTask<E extends MobEntity> extends Task<E> {
    private final Function<E, UniformIntProvider> cooldownRangeFactory;
    private final TargetPredicate targetPredicate;
    private final float speed;
    private final ToDoubleFunction<E> strengthMultiplierFactory;
    private Vec3d direction;
    private final Function<E, SoundEvent> impactSoundFactory;

    public RamImpactTask(Function<E, UniformIntProvider> cooldownRangeFactory, TargetPredicate targetPredicate, float speed, ToDoubleFunction<E> strengthMultiplierFactory, Function<E, SoundEvent> impactSoundFactory) {
        super(ImmutableMap.of(MemoryModuleType.RAM_COOLDOWN_TICKS, MemoryModuleState.VALUE_ABSENT, MemoryModuleType.RAM_TARGET, MemoryModuleState.VALUE_PRESENT), 200);
        this.cooldownRangeFactory = cooldownRangeFactory;
        this.targetPredicate = targetPredicate;
        this.speed = speed;
        this.strengthMultiplierFactory = strengthMultiplierFactory;
        this.impactSoundFactory = impactSoundFactory;
        this.direction = Vec3d.ZERO;
    }

    protected boolean shouldRun(ServerWorld serverWorld, E mobEntity) {
        return mobEntity.getBrain().hasMemoryModule(MemoryModuleType.RAM_TARGET);
    }

    protected boolean shouldKeepRunning(ServerWorld serverWorld, E mobEntity, long l) {
        return mobEntity.getBrain().hasMemoryModule(MemoryModuleType.RAM_TARGET);
    }

    protected void run(ServerWorld serverWorld, E mobEntity, long l) {
        BlockPos blockPos = mobEntity.getBlockPos();
        Brain<?> brain = mobEntity.getBrain();
        Vec3d vec3d = brain.getOptionalMemory(MemoryModuleType.RAM_TARGET).get();
        direction = (new Vec3d((double)blockPos.getX() - vec3d.getX(), 0d, (double)blockPos.getZ() - vec3d.getZ())).normalize();
        brain.remember(MemoryModuleType.WALK_TARGET, new WalkTarget(vec3d, speed, 0));
    }

    protected void keepRunning(ServerWorld serverWorld, E mobEntity, long l) {
        List<LivingEntity> list = serverWorld.getTargets(LivingEntity.class, targetPredicate, mobEntity, mobEntity.getBoundingBox());
        Brain<?> brain = mobEntity.getBrain();
        if (!list.isEmpty()) {
            LivingEntity livingEntity = list.get(0);
            livingEntity.damage(DamageSource.mob(mobEntity).setNeutral(), (float)mobEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE));
            int i = mobEntity.hasStatusEffect(StatusEffects.SPEED) ? mobEntity.getStatusEffect(StatusEffects.SPEED).getAmplifier() + 1 : 0;
            int j = mobEntity.hasStatusEffect(StatusEffects.SLOWNESS) ? mobEntity.getStatusEffect(StatusEffects.SLOWNESS).getAmplifier() + 1 : 0;
            float f = .25f * (float)(i - j);
            float g = MathHelper.clamp(mobEntity.getMovementSpeed() * 1.65f, .2f, 3f) + f;
            float h = livingEntity.blockedByShield(DamageSource.mob(mobEntity)) ? .5F : 1f;
            livingEntity.takeKnockback((double)(h * g) * strengthMultiplierFactory.applyAsDouble(mobEntity), direction.getX(), direction.getZ());
            finishRam(serverWorld, mobEntity);
            serverWorld.playSoundFromEntity(null, mobEntity, impactSoundFactory.apply(mobEntity), SoundCategory.HOSTILE, 1f, 1f);
        } else {
            Optional<WalkTarget> walkTarget = brain.getOptionalMemory(MemoryModuleType.WALK_TARGET);
            Optional<Vec3d> ramTarget = brain.getOptionalMemory(MemoryModuleType.RAM_TARGET);
            boolean bl2 = walkTarget.isEmpty() || ramTarget.isEmpty() || (walkTarget.get()).getLookTarget().getPos().isInRange(ramTarget.get(), .25d);
            if (bl2) {
                finishRam(serverWorld, mobEntity);
            }
        }
    }

    protected void finishRam(ServerWorld world, E mobEntity) {
        world.sendEntityStatus(mobEntity, (byte)59);
        mobEntity.getBrain().remember(MemoryModuleType.RAM_COOLDOWN_TICKS, cooldownRangeFactory.apply(mobEntity).get(world.random));
        mobEntity.getBrain().forget(MemoryModuleType.RAM_TARGET);
    }
}