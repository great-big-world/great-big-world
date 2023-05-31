package com.github.creoii.greatbigworld.main.mixin.block;

import com.github.creoii.greatbigworld.main.registry.GBWBlocks;
import com.github.creoii.greatbigworld.main.util.Tags;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(MagmaBlock.class)
public class MagmaBlockMixin {
    @Inject(method = "randomTick", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void great_big_world_magmaGeneratesLavarock(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci, BlockPos blockPos) {
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
