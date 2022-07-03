package com.github.creoii.greatbigworld.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class PoweredEnchantment extends Enchantment {
    public PoweredEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    }

    public int getMinPower(int level) {
        return level * 10;
    }

    public int getMaxPower(int level) {
        return getMinPower(level) + 15;
    }

    public boolean isTreasure() {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
