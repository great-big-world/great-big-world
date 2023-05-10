package com.github.creoii.greatbigworld.block;

import com.github.creoii.creolib.api.util.block.CBlockSettings;
import com.github.creoii.greatbigworld.main.mixin.block.FarmlandBlockInvoker;
import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class VolcanicSandBlock extends SandBlock {
    public VolcanicSandBlock() {
        super(2827557, CBlockSettings.copy(Blocks.SAND).mapColor(MapColor.BLACK).ticksRandomly());
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextFloat() > .8f) return;
        BlockState upState = world.getBlockState(pos.up());
        if (upState.isOf(Blocks.FARMLAND) && FarmlandBlockInvoker.hasCrop(world, pos.up()) && FarmlandBlockInvoker.isWaterNearby(world, pos.up())) {
            BlockPos.Mutable mutable = pos.mutableCopy();
            for (int i = 1; i < 4; ++i) {
                mutable.setY(pos.getY() - i);
                BlockState atState = world.getBlockState(mutable);
                boolean passUp = world.getBlockState(mutable.up()).isOf(Blocks.ROOTED_DIRT) || world.getBlockState(mutable.up()).isOf(BlockRegistry.VOLCANIC_SAND);
                if (atState.isOf(Blocks.DIRT) && passUp) {
                    world.setBlockState(mutable, Blocks.ROOTED_DIRT.getDefaultState(), 3);
                } else if (atState.isOf(Blocks.ROOTED_DIRT) && passUp && world.getBlockState(mutable.down()).isAir()) {
                    world.setBlockState(mutable.down(), Blocks.HANGING_ROOTS.getDefaultState(), 3);
                }
            }
        }
        super.randomTick(state, world, pos, random);
    }
}
