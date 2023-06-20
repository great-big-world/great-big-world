package com.github.creoii.greatbigworld.main.util.material;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.integration.ModMenuIntegration;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public record WoodenMaskArmorMaterial(ItemConvertible repairItem) implements ArmorMaterial {
    @Override
    public int getDurability(ArmorItem.Type type) {
        int value = GreatBigWorld.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.woodenMaskDurability.intValue() : 140;
        return value;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        int value = GreatBigWorld.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.woodenMaskProtection.intValue() : 2;
        return value;
    }

    @Override
    public int getEnchantability() {
        int value = GreatBigWorld.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.woodenMaskEnchantability.intValue() : 11;
        return value;
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
        float value = GreatBigWorld.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.woodenMaskToughness.floatValue() : 0f;
        return value;
    }

    @Override
    public float getKnockbackResistance() {
        return 0f;
    }
}