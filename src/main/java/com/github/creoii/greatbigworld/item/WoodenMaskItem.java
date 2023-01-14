package com.github.creoii.greatbigworld.item;

import com.github.creoii.greatbigworld.main.util.material.WoodenMaskArmorMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;

public class WoodenMaskItem extends ArmorItem {
    public WoodenMaskItem(WoodenMaskArmorMaterial material) {
        super(material, EquipmentSlot.HEAD, new FabricItemSettings().maxCount(1));
    }
}
