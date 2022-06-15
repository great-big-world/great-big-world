package com.github.creoii.greatbigworld.block;

import com.github.creoii.greatbigworld.main.util.ItemUtil;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class OxidizableBlock extends net.minecraft.block.OxidizableBlock {
    public OxidizableBlock(OxidationLevel oxidationLevel, Settings settings) {
        super(oxidationLevel, settings);
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.OXIDIZED_CUT_COPPER_SLAB);
    }
}
