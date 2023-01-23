package com.github.creoii.greatbigworld.item;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.ItemRegistry;
import com.github.creoii.greatbigworld.main.util.Tags;
import com.github.creoii.greatbigworld.main.util.material.WoodenMaskArmorMaterial;
import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

import java.util.ArrayList;
import java.util.List;

public class WoodenMaskItem extends GBWArmorItem {
    public static final List<Item> WOODEN_MASKS = new ArrayList<>();

    public WoodenMaskItem(WoodenMaskArmorMaterial material) {
        super(material, EquipmentSlot.HEAD, new FabricItemSettings().maxCount(1), enchantment -> {
            return Registries.ENCHANTMENT.getEntry(enchantment).isIn(Tags.EnchantmentTags.MASK_ALLOWED);
        });
        WOODEN_MASKS.add(this);
    }

    public static Item getRandomMask() {
        return WOODEN_MASKS.get(GreatBigWorld.RANDOM.nextInt(WOODEN_MASKS.size()));
    }
}
