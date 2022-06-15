package com.github.creoii.greatbigworld.block;

import com.github.creoii.greatbigworld.main.util.ItemUtil;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class PressurePlateBlock extends net.minecraft.block.PressurePlateBlock {
    public PressurePlateBlock(net.minecraft.block.PressurePlateBlock.ActivationRule type, Settings settings) {
        super(type, settings);
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.WARPED_PRESSURE_PLATE);
    }
}
