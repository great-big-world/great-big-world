package com.github.creoii.greatbigworld.main.util.material;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public record WoodenMaskArmorMaterial(ItemConvertible repairItem) implements ArmorMaterial {
    @Override
    public int getDurability(ArmorItem.Type type) {
        return GreatBigWorld.CONFIG.woodenMaskDurability.intValue();
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return GreatBigWorld.CONFIG.woodenMaskProtection.intValue();
    }

    @Override
    public int getEnchantability() {
        return GreatBigWorld.CONFIG.woodenMaskEnchantability.intValue();
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
        return GreatBigWorld.CONFIG.woodenMaskToughness.floatValue();
    }

    @Override
    public float getKnockbackResistance() {
        return 0f;
    }
}
