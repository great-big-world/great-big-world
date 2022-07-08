package com.github.creoii.greatbigworld.main.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class ItemUtil {
    public static void appendStackInGroup(DefaultedList<ItemStack> stacks, ItemStack add, Item after) {
        for (int i = 0; i < stacks.size(); ++i) {
            if (stacks.get(i).isOf(after) && !stacks.contains(add)) {
                if (i < stacks.size() - 1) stacks.add(i + 1, add);
                else stacks.add(add);
            }
        }
    }
}
