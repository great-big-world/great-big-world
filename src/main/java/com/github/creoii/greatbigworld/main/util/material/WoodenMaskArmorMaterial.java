package com.github.creoii.greatbigworld.main.util.material;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class WoodenMaskArmorMaterial implements ArmorMaterial {
    private final Item repairItem;

    public WoodenMaskArmorMaterial(Item repairItem) {
        this.repairItem = repairItem;
    }

    @Override
    public int getDurability(EquipmentSlot slot) {
        return 0;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 12;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.BLOCK_WOOD_BREAK;
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
