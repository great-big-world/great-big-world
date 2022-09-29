package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.entity.GBWBoatEntity;
import com.github.creoii.greatbigworld.item.GBWBoatItem;
import com.github.creoii.greatbigworld.main.util.ItemUtil;
import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class ItemRegistry implements Register {
    public static final Item MAHOGANY_LEAVES = new BlockItem(BlockRegistry.MAHOGANY_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.FLOWERING_AZALEA_LEAVES); }
    };
    public static final Item MAHOGANY_SIGN = new SignItem(new Item.Settings().maxCount(16).group(ItemGroup.DECORATIONS), BlockRegistry.MAHOGANY_SIGN, BlockRegistry.MAHOGANY_WALL_SIGN) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.WARPED_SIGN);
        }
    };
    public static final Item MAHOGANY_BOAT = new GBWBoatItem(false, GBWBoatEntity.GBWType.MAHOGANY, new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1));
    public static final Item MAHOGANY_CHEST_BOAT = new GBWBoatItem(true, GBWBoatEntity.GBWType.MAHOGANY, new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1));

    @Override
    public void register() {
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "mahogany_leaves"), MAHOGANY_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "mahogany_sign"), MAHOGANY_SIGN);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "mahogany_boat"), MAHOGANY_BOAT);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "mahogany_chest_boat"), MAHOGANY_CHEST_BOAT);
    }

    @Override
    public void registerClient() {
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> FoliageColors.getDefaultColor(), MAHOGANY_LEAVES);
    }
}
