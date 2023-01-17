package com.github.creoii.greatbigworld.item;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.ItemRegistry;
import com.github.creoii.greatbigworld.main.util.Tags;
import com.github.creoii.greatbigworld.main.util.material.WoodenMaskArmorMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

public class WoodenMaskItem extends GBWArmorItem {
    public WoodenMaskItem(WoodenMaskArmorMaterial material) {
        super(material, EquipmentSlot.HEAD, new FabricItemSettings().maxCount(1), enchantment -> {
            return Registries.ENCHANTMENT.getEntry(enchantment).isIn(Tags.EnchantmentTags.MASK_ALLOWED);
        });
    }

    public static Item getRandomMask() {
        return switch (GreatBigWorld.RANDOM.nextInt(11)) {
            case 0 -> ItemRegistry.SPRUCE_MASK;
            case 1 -> ItemRegistry.BIRCH_MASK;
            case 2 -> ItemRegistry.ASPEN_MASK;
            case 3 -> ItemRegistry.JUNGLE_MASK;
            case 4 -> ItemRegistry.MAHOGANY_MASK;
            case 5 -> ItemRegistry.ACACIA_MASK;
            case 6 -> ItemRegistry.DARK_OAK_MASK;
            case 7 -> ItemRegistry.MANGROVE_MASK;
            case 8 -> ItemRegistry.CRIMSON_MASK;
            case 9 -> ItemRegistry.WARPED_MASK;
            default -> ItemRegistry.OAK_MASK;
        };
    }
}
