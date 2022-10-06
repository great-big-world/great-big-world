package com.github.creoii.greatbigworld.entity.brain.task;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.brain.*;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public class PrepareRamTask<E extends PathAwareEntity> extends Task<E> {
    private final ToIntFunction<E> cooldownFactory;
    private final int minRamDistance;
    private final int maxRamDistance;
    private final float speed;
    private final Predicate<E> shouldRunPredicate;
    private final TargetPredicate targetPredicate;
    private final int prepareTime;
    private final Function<E, SoundEvent> soundFactory;
    private Optional<Long> prepareStartTime = Optional.empty();
    private Optional<Ram> ram = Optional.empty();
    private final boolean useAttackTarget;

    public PrepareRamTask(ToIntFunction<E> cooldownFactory, int minDistance, int maxDistance, float speed, Predicate<E> shouldRunPredicate, TargetPredicate targetPredicate, int prepareTime, Function<E, SoundEvent> soundFactory, boolean useAttackTarget) {
        super(ImmutableMap.of(MemoryModuleType.LOOK_TARGET, MemoryModuleState.REGISTERED, MemoryModuleType.RAM_COOLDOWN_TICKS, MemoryModuleState.VALUE_ABSENT, MemoryModuleType.VISIBLE_MOBS, MemoryModuleState.VALUE_PRESENT, (useAttackTarget ? MemoryModuleType.ATTACK_TARGET : MemoryModuleType.RAM_TARGET), MemoryModuleState.VALUE_ABSENT), 160);
        this.cooldownFactory = cooldownFactory;
        this.minRamDistance = minDistance;
        this.maxRamDistance = maxDistance;
        this.speed = speed;
        this.shouldRunPredicate = shouldRunPredicate;
        this.targetPredicate = targetPredicate;
        this.prepareTime = prepareTime;
        this.soundFactory = soundFactory;
        this.useAttackTarget = useAttackTarget;
    }

    @Override
    protected boolean shouldRun(ServerWorld world, E entity) {
        return shouldRunPredicate.test(entity);
    }

    protected void run(ServerWorld serverWorld, PathAwareEntity pathAwareEntity, long l) {
        Brain<?> brain = pathAwareEntity.getBrain();
        brain.getOptionalMemory(MemoryModuleType.VISIBLE_MOBS).flatMap((livingTargetCache) -> {
            return livingTargetCache.findFirst(mob -> targetPredicate.test(pathAwareEntity, mob));
        }).ifPresent(mob -> findRam(pathAwareEntity, mob));
    }

    protected void finishRunning(ServerWorld serverWorld, E pathAwareEntity, long l) {
        Brain<?> brain = pathAwareEntity.getBrain();
        if (!brain.hasMemoryModule(useAttackTarget ? MemoryModuleType.ATTACK_TARGET : MemoryModuleType.RAM_TARGET)) {
            serverWorld.sendEntityStatus(pathAwareEntity, (byte)59);
            brain.remember(MemoryModuleType.RAM_COOLDOWN_TICKS, cooldownFactory.applyAsInt(pathAwareEntity));
        }
    }

    protected boolean shouldKeepRunning(ServerWorld serverWorld, PathAwareEntity pathAwareEntity, long l) {
        return ram.isPresent() && ram.get().entity().isAlive();
    }

    protected void keepRunning(ServerWorld serverWorld, E pathAwareEntity, long l) {
        if (ram.isPresent()) {
            pathAwareEntity.getBrain().remember(MemoryModuleType.WALK_TARGET, new WalkTarget(ram.get().start(), speed, 0));
            pathAwareEntity.getBrain().remember(MemoryModuleType.LOOK_TARGET, new EntityLookTarget(ram.get().entity(), true));
            boolean bl = !ram.get().entity().getBlockPos().equals(ram.get().end());
            if (bl) {
                serverWorld.sendEntityStatus(pathAwareEntity, (byte)59);
                pathAwareEntity.getNavigation().stop();
                findRam(pathAwareEntity, ram.get().entity);
            } else {
                BlockPos blockPos = pathAwareEntity.getBlockPos();
                if (blockPos.equals(ram.get().start())) {
                    serverWorld.sendEntityStatus(pathAwareEntity, (byte)58);
                    if (prepareStartTime.isEmpty()) {
                        prepareStartTime = Optional.of(l);
                    }

                    if (l - prepareStartTime.get() >= (long)prepareTime) {
                        if (useAttackTarget)
                            pathAwareEntity.getBrain().remember(MemoryModuleType.ATTACK_TARGET, ram.get().entity());
                        else
                            pathAwareEntity.getBrain().remember(MemoryModuleType.RAM_TARGET, calculateRamTarget(blockPos, ram.get().end()));
                        serverWorld.playSoundFromEntity(null, pathAwareEntity, soundFactory.apply(pathAwareEntity), SoundCategory.HOSTILE, 1f, pathAwareEntity.getSoundPitch());
                        ram = Optional.empty();
                    }
                }
            }
        }
    }

    private Vec3d calculateRamTarget(BlockPos start, BlockPos end) {
        double e = .5d * MathHelper.sign(end.getX() - start.getX());
        double f = .5d * MathHelper.sign(end.getZ() - start.getZ());
        return Vec3d.ofBottomCenter(end).add(e, 0d, f);
    }

    private Optional<BlockPos> findRamStart(PathAwareEntity entity, LivingEntity target) {
        BlockPos blockPos = target.getBlockPos();
        if (canReach(entity, blockPos)) {
            return Optional.empty();
        } else {
            List<BlockPos> list = Lists.newArrayList();
            BlockPos.Mutable mutable = blockPos.mutableCopy();

            for (Direction direction : Direction.Type.HORIZONTAL) {
                mutable.set(blockPos);

                for (int i = 0; i < maxRamDistance; ++i) {
                    if (canReach(entity, mutable.move(direction))) {
                        mutable.move(direction.getOpposite());
                        break;
                    }
                }

                if (mutable.getManhattanDistance(blockPos) >= minRamDistance) {
                    list.add(mutable.toImmutable());
                }
            }

            EntityNavigation entityNavigation = entity.getNavigation();
            Stream<BlockPos> var10000 = list.stream();
            BlockPos entityPos = entity.getBlockPos();
            Objects.requireNonNull(entityPos);
            return var10000.sorted(Comparator.comparingDouble(entityPos::getSquaredDistance)).filter(start -> {
                Path path = entityNavigation.findPathTo(start, 0);
                return path != null && path.reachesTarget();
            }).findFirst();
        }
    }

    private boolean canReach(PathAwareEntity entity, BlockPos target) {
        return !entity.getNavigation().isValidPosition(target) || entity.getPathfindingPenalty(LandPathNodeMaker.getLandNodeType(entity.world, target.mutableCopy())) != 0f;
    }

    private void findRam(PathAwareEntity entity, LivingEntity target) {
        prepareStartTime = Optional.empty();
        ram = findRamStart(entity, target).map(start -> {
            return new Ram(start, target.getBlockPos(), target);
        });
    }

    public record Ram(BlockPos start, BlockPos end, LivingEntity entity) { }
}
