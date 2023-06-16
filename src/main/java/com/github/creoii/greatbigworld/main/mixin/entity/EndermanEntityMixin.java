package com.github.creoii.greatbigworld.main.mixin.entity;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.integration.ModMenuIntegration;
import com.github.creoii.greatbigworld.main.util.Tags;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndermanEntity.class)
public class EndermanEntityMixin {
    @Inject(method = "isPlayerStaring", at = @At("HEAD"), cancellable = true)
    private void gbw_masksBlockStaring(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        boolean value = GreatBigWorld.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.masksAngerEndermen.booleanValue() : false;
        if (player.getInventory().armor.get(3).isIn(Tags.ItemTags.WOODEN_MASKS) || value) cir.setReturnValue(false);
    }
}
