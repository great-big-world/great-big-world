package com.github.creoii.greatbigworld.main.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class ItemUtil {
    /**
     * Inserts an ItemStack into an ItemGroup's list of ItemStacks after a specified item
     * in order to integrate modded items with the vanilla ItemGroups.
     *
     * eg. Inserting a modded log after vanilla logs so it doesn't get put at the bottom of
     *     the ItemGroup.
     * @param stacks - The list of ItemStacks to insert into
     * @param add - The ItemStack to insert
     * @param after - The item to insert after
     */
    public static void appendStackInGroup(DefaultedList<ItemStack> stacks, ItemStack add, Item after) {
        for (int i = 0; i < stacks.size(); ++i) {
            if (stacks.get(i).isOf(after) && !stacks.contains(add)) {
                if (i < stacks.size() - 1) stacks.add(i + 1, add);
                else stacks.add(add);
            }
        }
    }
}
