package com.github.creoii.greatbigworld.main.mixin.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.tag.BlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {
    @Inject(method = "supports", at = @At("RETURN"), cancellable = true)
    private void gbw_supportAllSigns(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this == BlockEntityType.SIGN) {
            cir.setReturnValue(state.isIn(BlockTags.SIGNS));
        } else if ((Object) this == BlockEntityType.HANGING_SIGN) {
            cir.setReturnValue(state.isIn(BlockTags.ALL_HANGING_SIGNS));
        }
    }
}
