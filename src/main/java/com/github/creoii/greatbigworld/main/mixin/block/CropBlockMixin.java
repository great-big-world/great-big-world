package com.github.creoii.greatbigworld.main.mixin.block;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CropBlock.class)
public class CropBlockMixin {
    @Shadow public int getMaxAge() { return 0; };

    @Inject(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void great_big_world_growGiantCrops(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci, int i, float f) {
        if (i == getMaxAge() - 1 && world.getRandom().nextInt((int) (20 / f)) == 0) {
            if (state.isOf(Blocks.POTATOES)) {
                world.setBlockState(pos, world.getRandom().nextFloat() < .02f ? BlockRegistry.GIANT_POISONOUS_POTATO.getDefaultState() : BlockRegistry.GIANT_POTATO.getDefaultState(), 2);
            } else if (state.isOf(Blocks.CARROTS)) {
                world.setBlockState(pos, BlockRegistry.GIANT_CARROT.getDefaultState(), 2);
            } else if (state.isOf(Blocks.BEETROOTS)) {
                world.setBlockState(pos, world.getRandom().nextFloat() < .05f ? BlockRegistry.GIANT_HEARTBEET.getDefaultState() : BlockRegistry.GIANT_BEETROOT.getDefaultState(), 2);
            }
            ci.cancel();
        }
    }
}
