package com.github.creoii.greatbigworld.item;

import com.github.creoii.greatbigworld.main.util.material.WoodenMaskArmorMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;

public class WoodenMaskItem extends ArmorItem {
    private final WoodType woodType;

    public WoodenMaskItem(WoodType woodType) {
        super(WoodenMaskArmorMaterial.INSTANCE, EquipmentSlot.HEAD, new FabricItemSettings().maxCount(1));
        this.woodType = woodType;
    }

    public WoodType getWoodType() {
        return woodType;
    }

    public enum WoodType {
        OAK,
        SPRUCE,
        BIRCH,
        JUNGLE,
        DARK_OAK,
        ACACIA,
        MANGROVE,
        MAHOGANY,
        ASPEN,
        CRIMSON,
        WARPED
    }
}
