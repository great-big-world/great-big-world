package com.github.creoii.greatbigworld.block;

import com.github.creoii.greatbigworld.main.util.ItemUtil;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class StairsBlock extends net.minecraft.block.StairsBlock {
    public StairsBlock(BlockState baseBlockState, Settings settings) {
        super(baseBlockState, settings);
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.DEEPSLATE_TILE_STAIRS);
    }
}
