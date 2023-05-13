package com.github.creoii.greatbigworld.main.mixin.block;

import com.github.creoii.greatbigworld.main.registry.GBWBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowyBlock;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SpreadableBlock.class)
public abstract class SpreadableBlockMixin {
    @Shadow
    private static boolean canSpread(BlockState state, WorldView world, BlockPos pos) {
        return false;
    }

    @Inject(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;", ordinal = 0), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_lib_injectGrassSpreadables(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci, BlockState blockState, int i, BlockPos blockPos) {
        if (world.getBlockState(blockPos).isOf(GBWBlocks.LAVAROCK) && canSpread(blockState, world, blockPos)) {
            world.setBlockState(blockPos, GBWBlocks.GRASSY_LAVAROCK.getDefaultState().with(SnowyBlock.SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)));
        }
    }
}
