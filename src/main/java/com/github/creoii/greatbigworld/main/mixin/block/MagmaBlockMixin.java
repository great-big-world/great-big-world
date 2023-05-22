package com.github.creoii.greatbigworld.main.mixin.block;

import com.github.creoii.greatbigworld.main.registry.GBWBlocks;
import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
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
        BlockState upState = world.getBlockState(pos.up());
        if (world.getBlockState(pos).isOf(Blocks.MAGMA_BLOCK) && upState.isOf(Blocks.BUBBLE_COLUMN) && !upState.get(BubbleColumnBlock.DRAG)) return;

        BlockPos checkPos = pos.add(random.nextInt(7) - 3, random.nextInt(4) - 1, random.nextInt(7) - 3);
        if (!world.getBlockState(checkPos.up()).isOf(Blocks.WATER)) return;

        BlockState checkState = world.getBlockState(checkPos);
        if (checkState.isIn(BlockTags.BASE_STONE_OVERWORLD)) {
            world.setBlockState(checkPos, GBWBlocks.LAVAROCK.getDefaultState(), Block.NOTIFY_ALL);
        } else if (checkState.isIn(BlockTags.SAND)) {
            world.setBlockState(checkPos, GBWBlocks.VOLCANIC_SAND.getDefaultState(), Block.NOTIFY_ALL);
        }
    }
}
