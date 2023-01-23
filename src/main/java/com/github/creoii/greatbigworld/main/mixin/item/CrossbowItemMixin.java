package com.github.creoii.greatbigworld.main.mixin.item;

import com.github.creoii.greatbigworld.main.registry.EnchantmentRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {
    @Inject(method = "createArrow", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private static void great_big_world_applyPoisonGlaze(World world, LivingEntity entity, ItemStack crossbow, ItemStack arrow, CallbackInfoReturnable<PersistentProjectileEntity> cir, ArrowItem arrowItem, PersistentProjectileEntity persistentProjectileEntity) {
        int level = EnchantmentHelper.getEquipmentLevel(EnchantmentRegistry.POISON_GLAZE, entity);
        if (level > 0 && persistentProjectileEntity instanceof ArrowEntity arrowEntity) {
            arrowEntity.addEffect(new StatusEffectInstance(StatusEffects.POISON, level * 250, level));
            cir.setReturnValue(arrowEntity);
        }
    }
}
