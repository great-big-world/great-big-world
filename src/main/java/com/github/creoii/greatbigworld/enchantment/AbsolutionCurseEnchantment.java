package com.github.creoii.greatbigworld.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class AbsolutionCurseEnchantment extends Enchantment {
    public AbsolutionCurseEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.BREAKABLE, EquipmentSlot.values());
    }

    @Override
    public boolean isCursed() {
        return true;
    }
}
