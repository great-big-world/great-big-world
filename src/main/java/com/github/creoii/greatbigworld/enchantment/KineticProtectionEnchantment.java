package com.github.creoii.greatbigworld.enchantment;

import net.minecraft.enchantment.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;

public class KineticProtectionEnchantment extends Enchantment {
    public KineticProtectionEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.ARMOR, new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
    }

    public int getMinPower(int level) {
        return 5 + (level - 1) * 8;
    }

    public int getMaxPower(int level) {
        return getMinPower(level) + 8;
    }

    public int getMaxLevel() {
        return 4;
    }

    public int getProtectionAmount(int level, DamageSource source) {
        return source == DamageSource.FLY_INTO_WALL ? level : 0;
    }

    public boolean canAccept(Enchantment other) {
        return !(other instanceof ProtectionEnchantment);
    }
}
