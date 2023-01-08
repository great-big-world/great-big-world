package com.github.creoii.greatbigworld.block.base;

import net.minecraft.block.LeavesBlock;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class GBWLeavesBlock extends LeavesBlock {
    public GBWLeavesBlock(Settings settings) {
        super(settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public Item asItem() {
        if (cachedItem == null || cachedItem == Items.AIR) {
            cachedItem = Item.fromBlock(this);
        }

        return cachedItem;
    }
}
