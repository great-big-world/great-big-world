package com.github.creoii.greatbigworld.main.mixin.block;

import com.github.creoii.greatbigworld.block.Crushable;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilBlock.class)
public class AnvilBlockMixin {
    @Inject(method = "onLanding", at = @At("TAIL"))
    private void great_big_world_destroyCrushables(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity, CallbackInfo ci) {
        if (world.getBlockState(pos.down()).getBlock() instanceof Crushable crushable) {
            if (fallingBlockEntity.fallDistance > crushable.getMinimumFallDistance() || crushable.getMinimumFallDistance() == -1) {
                world.breakBlock(pos.down(), crushable.shouldDropOnBreak(), fallingBlockEntity);
            }
        }
    }
}
