package com.github.creoii.greatbigworld.block.base;

import com.github.creoii.greatbigworld.main.util.ItemUtil;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class WallBlock extends net.minecraft.block.WallBlock {
    public WallBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.DEEPSLATE_TILE_WALL);
    }
}
