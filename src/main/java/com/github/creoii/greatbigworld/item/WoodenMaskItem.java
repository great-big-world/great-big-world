package com.github.creoii.greatbigworld.item;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.ItemRegistry;
import com.github.creoii.greatbigworld.main.util.AllowEnchantments;
import com.github.creoii.greatbigworld.main.util.Tags;
import com.github.creoii.greatbigworld.main.util.material.WoodenMaskArmorMaterial;
import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class WoodenMaskItem extends ArmorItem implements AllowEnchantments {
    public static final List<Item> WOODEN_MASKS = new ArrayList<>();

    public WoodenMaskItem(WoodenMaskArmorMaterial material) {
        super(material, EquipmentSlot.HEAD, new FabricItemSettings().maxCount(1));
        WOODEN_MASKS.add(this);
    }

    @Override
    public Predicate<Enchantment> getAllowedEnchantments() {
        return enchantment -> {
            return Registries.ENCHANTMENT.getEntry(enchantment).isIn(Tags.EnchantmentTags.MASK_ALLOWED);
        };
    }

    public static Item getRandomMask() {
        return WOODEN_MASKS.get(GreatBigWorld.RANDOM.nextInt(WOODEN_MASKS.size()));
    }
}
