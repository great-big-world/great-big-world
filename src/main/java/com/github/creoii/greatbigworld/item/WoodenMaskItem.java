package com.github.creoii.greatbigworld.item;

import com.github.creoii.creolib.api.item.AllowEnchantments;
import com.github.creoii.creolib.api.util.item.CItemSettings;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Tags;
import com.github.creoii.greatbigworld.main.util.material.WoodenMaskArmorMaterial;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class WoodenMaskItem extends ArmorItem implements AllowEnchantments {
    public static final List<Item> WOODEN_MASKS = new ArrayList<>();

    public WoodenMaskItem(WoodenMaskArmorMaterial material) {
        super(material, Type.HELMET, new CItemSettings().maxCount(1));
        WOODEN_MASKS.add(this);
    }

    @Override
    public Predicate<Enchantment> getAllowedEnchantments() {
        return enchantment -> Registries.ENCHANTMENT.getEntry(enchantment).isIn(Tags.EnchantmentTags.MASK_ALLOWED);
    }

    public static Item getRandomMask() {
        return WOODEN_MASKS.get(GreatBigWorld.RANDOM.nextInt(WOODEN_MASKS.size()));
    }
}
