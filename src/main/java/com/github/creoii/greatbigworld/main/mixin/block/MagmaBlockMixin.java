package com.github.creoii.greatbigworld.main.mixin.block;

import com.github.creoii.greatbigworld.main.registry.GBWBlocks;
import com.github.creoii.greatbigworld.main.util.Tags;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MagmaBlock.class)
public class MagmaBlockMixin extends Block {
    public MagmaBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(4) == 0) return;

        BlockState upState = world.getBlockState(pos.up());
        if (world.getBlockState(pos).isOf(Blocks.MAGMA_BLOCK) && upState.isOf(Blocks.BUBBLE_COLUMN) && !upState.get(BubbleColumnBlock.DRAG)) return;

        BlockPos checkPos = pos.add(random.nextInt(6) - 3, random.nextInt(4) - 1, random.nextInt(6) - 3);
        if (!world.getBlockState(checkPos.up()).isOf(Blocks.WATER)) return;

        BlockState checkState = world.getBlockState(checkPos);
        if (!checkState.isOf(GBWBlocks.LAVAROCK) && checkState.isIn(Tags.BlockTags.ERODES_INTO_LAVAROCK)) {
            world.setBlockState(checkPos, GBWBlocks.LAVAROCK.getDefaultState(), Block.NOTIFY_ALL);
        } else if (!checkState.isOf(GBWBlocks.VOLCANIC_SAND) && checkState.isIn(Tags.BlockTags.ERODES_INTO_VOLCANIC_SAND)) {
            world.setBlockState(checkPos, GBWBlocks.VOLCANIC_SAND.getDefaultState(), Block.NOTIFY_ALL);
        }
    }
}
