package com.github.creoii.greatbigworld.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

import java.util.function.Predicate;

public class GBWArmorItem extends ArmorItem {
    private final Predicate<Enchantment> allowedEnchantments;

    public GBWArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings, Predicate<Enchantment> allowedEnchantments) {
        super(material, slot, settings);
        this.allowedEnchantments = allowedEnchantments;
    }

    public Predicate<Enchantment> getAllowedEnchantments() {
        return allowedEnchantments;
    }
}
