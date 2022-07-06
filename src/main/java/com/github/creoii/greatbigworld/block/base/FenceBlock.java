package com.github.creoii.greatbigworld.block.base;

import com.github.creoii.greatbigworld.main.util.ItemUtil;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class FenceBlock extends net.minecraft.block.FenceBlock {
    private final boolean isNetherBrick;

    public FenceBlock(Settings settings, boolean isNetherBrick) {
        super(settings);
        this.isNetherBrick = isNetherBrick;
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        ItemUtil.appendStackInGroup(stacks, new ItemStack(this), isNetherBrick ? Items.NETHER_BRICK_FENCE : Items.WARPED_FENCE);
    }
}
