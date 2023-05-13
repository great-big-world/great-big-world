package com.github.creoii.greatbigworld.main.mixin.item;

import com.github.creoii.greatbigworld.main.registry.GBWEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SwordItem.class)
public class SwordItemMixin {
    @Inject(method = "postHit", at = @At("RETURN"))
    private void great_big_world_applyPoisonGlaze(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        int level = EnchantmentHelper.getEquipmentLevel(GBWEnchantments.POISON_GAZE, attacker);
        if (level > 0) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, level * 50, level));
        }
    }
}
