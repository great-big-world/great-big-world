package com.github.creoii.greatbigworld.main.mixin.client;

import com.github.creoii.greatbigworld.main.registry.GBWEnchantments;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(LightmapTextureManager.class)
public class LightmapTextureManagerMixin {
    @Redirect(method = "update",  at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;hasStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Z", ordinal = 0))
    private boolean great_big_world_applyIlluminating(ClientPlayerEntity instance, StatusEffect effect) {
        return instance.hasStatusEffect(StatusEffects.NIGHT_VISION) || EnchantmentHelper.getEquipmentLevel(GBWEnchantments.ILLUMINATING, instance) > 0;
    }
}
