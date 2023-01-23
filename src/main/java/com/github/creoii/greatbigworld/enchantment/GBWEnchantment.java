package com.github.creoii.greatbigworld.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

import java.util.function.Predicate;

public class GBWEnchantment extends Enchantment {
    private final Predicate<ItemStack> acceptableItem;

    protected GBWEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes, Predicate<ItemStack> acceptableItem) {
        super(weight, type, slotTypes);
        this.acceptableItem = acceptableItem;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return acceptableItem.test(stack);
    }
}
