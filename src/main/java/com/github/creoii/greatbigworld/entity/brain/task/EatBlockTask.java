package com.github.creoii.greatbigworld.entity.brain.task;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.brain.BlockPosLookTarget;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.WalkTarget;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;

import java.util.Optional;

public class EatBlockTask<E extends MobEntity> extends Task<E> {
    private final BlockPredicate blockPredicate;
    private int timer;
    private Optional<BlockPos> pos = Optional.empty();

    public EatBlockTask(BlockPredicate blockPredicate) {
        super(ImmutableMap.of(MemoryModuleType.LOOK_TARGET, MemoryModuleState.VALUE_ABSENT, MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT), 40);
        this.blockPredicate = blockPredicate;
        timer = 40;
    }

    @Override
    protected boolean shouldRun(ServerWorld world, E entity) {
        this.pos = findValidPos(world, entity);
        return pos.isPresent();
    }

    @Override
    protected boolean shouldKeepRunning(ServerWorld world, E entity, long time) {
        return timer > 0 && pos.isPresent();
    }

    @Override
    protected void run(ServerWorld world, E entity, long time) {
        addLookWalkTargets(entity);
        timer = 40;
    }

    private void addLookWalkTargets(E entity) {
        pos.ifPresent((pos) -> {
            BlockPosLookTarget blockPosLookTarget = new BlockPosLookTarget(pos);
            entity.getBrain().remember(MemoryModuleType.LOOK_TARGET, blockPosLookTarget);
            entity.getBrain().remember(MemoryModuleType.WALK_TARGET, new WalkTarget(blockPosLookTarget, .5f, 1));
        });
    }

    protected void keepRunning(ServerWorld world, E entity, long l) {
        BlockPos blockPos = pos.get();
        if (blockPos.isWithinDistance(entity.getPos(), 1d)) {
            if (blockPredicate.test(world, blockPos)) {
                if (world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                    world.breakBlock(blockPos, false);
                }
            } else {
                if (world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                    world.syncWorldEvent(2001, blockPos, Block.getRawIdFromState(world.getBlockState(blockPos)));
                }
            }
            entity.onEatingGrass();
            pos = findValidPos(world, entity);
            addLookWalkTargets(entity);

            --timer;
        }
    }

    private Optional<BlockPos> findValidPos(ServerWorld world, E entity) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        Optional<BlockPos> optional = Optional.empty();
        int i = 0;

        for (int j = -1; j <= 1; ++j) {
            for (int k = -1; k <= 1; ++k) {
                for (int l = -1; l <= 1; ++l) {
                    mutable.set(entity.getBlockPos(), j, k, l);
                    ++i;
                    if (world.random.nextInt(i) == 0) {
                        optional = Optional.of(mutable.toImmutable());
                    }
                }
            }
        }

        return optional;
    }
}
