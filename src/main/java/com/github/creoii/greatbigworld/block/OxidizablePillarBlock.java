package com.github.creoii.greatbigworld.block;

import com.github.creoii.greatbigworld.main.util.ItemUtil;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class OxidizablePillarBlock extends PillarBlock implements Oxidizable {
    private final OxidationLevel oxidationLevel;

    public OxidizablePillarBlock(OxidationLevel oxidationLevel, Settings settings) {
        super(settings);
        this.oxidationLevel = oxidationLevel;
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.WAXED_OXIDIZED_CUT_COPPER_SLAB);
    }

    @Override
    public OxidationLevel getDegradationLevel() {
        return oxidationLevel;
    }
}
