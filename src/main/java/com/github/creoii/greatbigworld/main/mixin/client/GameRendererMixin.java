package com.github.creoii.greatbigworld.main.mixin.client;

import com.github.creoii.greatbigworld.main.registry.GBWEnchantments;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
@Environment(EnvType.CLIENT)
public class GameRendererMixin {
    @Inject(method = "getNightVisionStrength", at = @At("HEAD"), cancellable = true)
    private static void gbw_applyIlluminating(LivingEntity entity, float tickDelta, CallbackInfoReturnable<Float> cir) {
        int i = EnchantmentHelper.getEquipmentLevel(GBWEnchantments.ILLUMINATING, entity);
        if (i > 0) {
            cir.setReturnValue(.2f * i);
        }
    }
}