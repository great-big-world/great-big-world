package com.github.creoii.greatbigworld.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

import java.util.function.Predicate;

public class GBWEnchantment extends Enchantment {
    private final Predicate<ItemStack> acceptableItemPredicate;

    protected GBWEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes, Predicate<ItemStack> acceptableItemPredicate) {
        super(weight, type, slotTypes);
        this.acceptableItemPredicate = acceptableItemPredicate;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return acceptableItemPredicate.test(stack);
    }
}
