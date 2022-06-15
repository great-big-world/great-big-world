package com.github.creoii.greatbigworld.block;

import com.github.creoii.greatbigworld.main.util.ItemUtil;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class LeavesBlock extends net.minecraft.block.LeavesBlock {
    public LeavesBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.FLOWERING_AZALEA_LEAVES);
    }
}
