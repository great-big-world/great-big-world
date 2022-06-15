package com.github.creoii.greatbigworld.item;

import com.github.creoii.greatbigworld.main.util.ItemUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class SpawnEggItem extends net.minecraft.item.SpawnEggItem {
    public SpawnEggItem(EntityType<? extends MobEntity> type, int primaryColor, int secondaryColor, Settings settings) {
        super(type, primaryColor, secondaryColor, settings);
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        ItemUtil.appendStackInGroup(stacks, getDefaultStack(), Items.ZOMBIFIED_PIGLIN_SPAWN_EGG);
    }
}
