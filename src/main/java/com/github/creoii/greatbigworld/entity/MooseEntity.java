package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.entity.brain.MooseBrain;
import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import com.github.creoii.greatbigworld.main.registry.SensorRegistry;
import com.github.creoii.greatbigworld.main.util.Tags;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.Hoglin;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MooseEntity extends TameableEntity {
    protected static final ImmutableList<SensorType<? extends Sensor<? super MooseEntity>>> SENSORS = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_PLAYERS, SensorType.NEAREST_ITEMS, SensorType.NEAREST_ADULT, SensorType.HURT_BY, SensorRegistry.MOOSE_TEMPTATIONS);
    protected static final ImmutableList<MemoryModuleType<?>> MEMORY_MODULES = ImmutableList.of(MemoryModuleType.LOOK_TARGET, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH, MemoryModuleType.BREED_TARGET, MemoryModuleType.TEMPTING_PLAYER, MemoryModuleType.NEAREST_VISIBLE_ADULT, MemoryModuleType.TEMPTATION_COOLDOWN_TICKS, MemoryModuleType.IS_TEMPTED, MemoryModuleType.RAM_COOLDOWN_TICKS, MemoryModuleType.RAM_TARGET, MemoryModuleType.ANGRY_AT, MemoryModuleType.UNIVERSAL_ANGER, MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_NEMESIS, MemoryModuleType.PACIFIED, MemoryModuleType.ATTACK_TARGET);
    private int tameProbability;
    private boolean preparingCharge;
    private int headPitch;

    public MooseEntity(EntityType<? extends MooseEntity> entityType, World world) {
        super(entityType, world);
        getNavigation().setCanSwim(true);
        setPathfindingPenalty(PathNodeType.POWDER_SNOW, -1f);
        setPathfindingPenalty(PathNodeType.DANGER_POWDER_SNOW, -1f);
        tameProbability = 0;
    }

    public Brain.Profile<MooseEntity> createBrainProfile() {
        return Brain.createProfile(MEMORY_MODULES, SENSORS);
    }

    public Brain<?> deserializeBrain(Dynamic<?> dynamic) {
        return MooseBrain.create(createBrainProfile().deserialize(dynamic));
    }

    public static DefaultAttributeContainer.Builder createMooseAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 40d).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .30000001192092896d).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, .6000000238418579d).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1d).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6d);
    }

    @Nullable @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        MooseEntity mooseEntity = EntityRegistry.MOOSE.create(world);
        if (mooseEntity != null) {
            MooseBrain.rememberRamCooldown(mooseEntity, world.getRandom());
        }

        return mooseEntity;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(Tags.ItemTags.MOOSE_BREEDING_ITEMS);
    }

    public boolean isFoodItem(ItemStack stack) {
        return stack.isIn(Tags.ItemTags.MOOSE_FOOD);
    }

    public boolean tryAttack(Entity target) {
        if (target.isLiving()) {
            return Hoglin.tryAttack(this, (LivingEntity)target);
        }
        return false;
    }

    protected void knockback(LivingEntity target) {
        if (isAdult()) {
            Hoglin.knockback(this, target);
        }
    }

    public boolean damage(DamageSource source, float amount) {
        boolean bl = super.damage(source, amount);
        if (world.isClient) return false;
        else {
            if (bl && source.getAttacker() instanceof LivingEntity) {
                MooseBrain.onAttacked(this, (LivingEntity)source.getAttacker());
            }
            return bl;
        }
    }

    public int getMaxHeadRotation() {
        return 15;
    }

    public void setHeadYaw(float headYaw) {
        int i = getMaxHeadRotation();
        float f = MathHelper.subtractAngles(bodyYaw, headYaw);
        float g = MathHelper.clamp(f, (float)(-i), (float)i);
        super.setHeadYaw(bodyYaw + g);
    }

    public void handleStatus(byte status) {
        if (status == 58) preparingCharge = true;
        else if (status == 59) preparingCharge = false;
        else super.handleStatus(status);
    }

    public void tickMovement() {
        if (preparingCharge) {
            ++headPitch;
        } else {
            headPitch -= 2;
        }

        headPitch = MathHelper.clamp(headPitch, 0, 20);
        super.tickMovement();
    }

    public float getHeadPitch() {
        return (float)headPitch / 20f * 30f * .017453292f;
    }

    protected void mobTick() {
        world.getProfiler().push("mooseBrain");
        getBrain().tick((ServerWorld)world, this);
        world.getProfiler().pop();
        world.getProfiler().push("mooseActivityUpdate");
        MooseBrain.updateActivities(this);
        world.getProfiler().pop();
        super.mobTick();
    }

    @SuppressWarnings("unchecked")
    public Brain<MooseEntity> getBrain() {
        return (Brain<MooseEntity>) brain;
    }

    public boolean isAdult() {
        return !isBaby();
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (world.isClient) {
            boolean bl = isOwner(player) || isBaby() || isTamed() || itemStack.isOf(Items.BONE) && !isTamed();
            return bl ? ActionResult.CONSUME : ActionResult.PASS;
        } else {
            if (isFoodItem(itemStack)) {
                if (!player.getAbilities().creativeMode) itemStack.decrement(1);
                if (isTamed() && getHealth() < getMaxHealth()) {
                    heal(item.getFoodComponent().getHunger());
                } else {
                    if (tameProbability <= 100) showFeedParticle();
                    tameProbability = MathHelper.clamp(tameProbability += random.nextBetween(2, 5), 0, 100);
                }
                return ActionResult.SUCCESS;
            } else if (isBreedingItem(itemStack)) {
                if (!player.getAbilities().creativeMode) itemStack.decrement(1);

                if (random.nextInt((16 / tameProbability) * 100) == 0) {
                    setOwner(player);
                    navigation.stop();
                    setTarget(null);
                    world.sendEntityStatus(this, (byte)7);
                } else {
                    if (random.nextInt((8 / tameProbability) * 100) == 0)
                        MooseBrain.onAttacked(this, player);
                    world.sendEntityStatus(this, (byte)6);
                }
                return ActionResult.SUCCESS;
            }
            return super.interactMob(player, hand);
        }
    }

    public void showFeedParticle() {
        for (int i = 0; i < 5; ++i) {
            double d = random.nextGaussian() * .02d;
            double e = random.nextGaussian() * .02d;
            double f = random.nextGaussian() * .02d;
            world.addParticle(ParticleTypes.HAPPY_VILLAGER, getParticleX(1d), getRandomBodyY() + .5d, getParticleZ(1d), d, e, f);
        }
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return isBaby() ? dimensions.height * .65f : dimensions.height;
    }

    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        return isBaby() ? super.getDimensions(pose).scaled(.5f) : super.getDimensions(pose);
    }
}
