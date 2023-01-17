package com.github.creoii.greatbigworld.main.util;

import com.github.creoii.greatbigworld.item.WoodenMaskItem;
import com.github.creoii.greatbigworld.main.registry.ItemRegistry;
import com.google.common.collect.ImmutableMap;
import net.minecraft.item.Item;

import java.util.function.Predicate;

public class EnchantmentTarget {
    public static final EnchantmentTarget MASK = new EnchantmentTarget(item -> item instanceof WoodenMaskItem);
    public static final ImmutableMap<Item, EnchantmentTarget> TARGETS = ImmutableMap.<Item, EnchantmentTarget>builder()
            .put(ItemRegistry.OAK_MASK, MASK)
            .put(ItemRegistry.SPRUCE_MASK, MASK)
            .put(ItemRegistry.BIRCH_MASK, MASK)
            .put(ItemRegistry.ASPEN_MASK, MASK)
            .put(ItemRegistry.JUNGLE_MASK, MASK)
            .put(ItemRegistry.MAHOGANY_MASK, MASK)
            .put(ItemRegistry.ACACIA_MASK, MASK)
            .put(ItemRegistry.DARK_OAK_MASK, MASK)
            .put(ItemRegistry.MANGROVE_MASK, MASK)
            .put(ItemRegistry.CRIMSON_MASK, MASK)
            .put(ItemRegistry.WARPED_MASK, MASK)
            .build();
    private final Predicate<Item> acceptableItem;

    public EnchantmentTarget(Predicate<Item> acceptableItem) {
        this.acceptableItem = acceptableItem;
    }

    public EnchantmentTarget(net.minecraft.enchantment.EnchantmentTarget target) {
        acceptableItem = target::isAcceptableItem;
    }

    public boolean isAcceptableItem(Item item) {
        return acceptableItem.test(item);
    }
}
