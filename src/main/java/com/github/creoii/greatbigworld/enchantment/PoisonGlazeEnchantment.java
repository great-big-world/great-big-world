package com.github.creoii.greatbigworld.enchantment;

import com.github.creoii.greatbigworld.main.util.Tags;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class PoisonGlazeEnchantment extends GBWEnchantment {
    public PoisonGlazeEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEARABLE, new EquipmentSlot[]{EquipmentSlot.HEAD}, stack -> stack.isIn(Tags.ItemTags.WOODEN_MASKS));
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getMinPower(int level) {
        return super.getMinPower(level) + 4;
    }
}
