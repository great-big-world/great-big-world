package com.github.creoii.greatbigworld.main.mixin;

import com.github.creoii.greatbigworld.main.util.AllowEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    private void great_big_world_acceptableEnchantments(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof AllowEnchantments allowEnchantments) {
            cir.setReturnValue(allowEnchantments.getAllowedEnchantments().test((Enchantment) (Object) this));
        }
    }
}
