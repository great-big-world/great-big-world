package com.github.creoii.greatbigworld.main.util.material;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public record WoodenMaskArmorMaterial(ItemConvertible repairItem) implements ArmorMaterial {
    @Override
    public int getDurability(ArmorItem.Type type) {
        return 140;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return 2;
    }

    @Override
    public int getEnchantability() {
        return 11;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.BLOCK_WOOD_PLACE;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(repairItem);
    }

    @Override
    public String getName() {
        return "wooden_mask";
    }

    @Override
    public float getToughness() {
        return 0f;
    }

    @Override
    public float getKnockbackResistance() {
        return 0f;
    }
}