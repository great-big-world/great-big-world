package com.github.creoii.greatbigworld.item;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.AllowEnchantments;
import com.github.creoii.greatbigworld.main.util.Tags;
import com.github.creoii.greatbigworld.main.util.material.WoodenMaskArmorMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class WoodenMaskItem extends ArmorItem implements AllowEnchantments {
    public static final List<Item> WOODEN_MASKS = new ArrayList<>();

    public WoodenMaskItem(WoodenMaskArmorMaterial material) {
        super(material, EquipmentSlot.HEAD, new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1));
        WOODEN_MASKS.add(this);
    }

    @Override
    public Predicate<Enchantment> getAllowedEnchantments() {
        return enchantment -> {
            return Tags.isEnchantmentIn(enchantment, Tags.EnchantmentTags.MASK_ALLOWED);
        };
    }

    public static Item getRandomMask() {
        return WOODEN_MASKS.get(GreatBigWorld.RANDOM.nextInt(WOODEN_MASKS.size()));
    }
}
