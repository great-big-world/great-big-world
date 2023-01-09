package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.Foods;
import com.github.creoii.greatbigworld.main.util.GBWBoatTypes;
import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class ItemRegistry implements Register {
    //region Mahogany Wood
    public static final Item MAHOGANY_LEAVES = new BlockItem(BlockRegistry.MAHOGANY_LEAVES, new Item.Settings());
    public static final Item MAHOGANY_SIGN = new SignItem(new Item.Settings().maxCount(16), BlockRegistry.MAHOGANY_SIGN, BlockRegistry.MAHOGANY_WALL_SIGN);
    public static final Item MAHOGANY_BOAT = new BoatItem(false, GBWBoatTypes.MAHOGANY, new FabricItemSettings().maxCount(1));
    public static final Item MAHOGANY_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.MAHOGANY, new FabricItemSettings().maxCount(1));
    //endregion
    //region Aspen Wood
    public static final Item GREEN_ASPEN_LEAVES = new BlockItem(BlockRegistry.GREEN_ASPEN_LEAVES, new Item.Settings());
    public static final Item GREEN_ASPEN_LEAF_PILE = new BlockItem(BlockRegistry.GREEN_ASPEN_LEAF_PILE, new Item.Settings());
    public static final Item ASPEN_SIGN = new SignItem(new Item.Settings().maxCount(16), BlockRegistry.ASPEN_SIGN, BlockRegistry.ASPEN_WALL_SIGN);
    public static final Item ASPEN_BOAT = new BoatItem(false, GBWBoatTypes.ASPEN, new FabricItemSettings().maxCount(1));
    public static final Item ASPEN_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.ASPEN, new FabricItemSettings().maxCount(1));
    //endregion
    //region Bamboo Torches
    public static final Item BAMBOO_TORCH = new VerticallyAttachableBlockItem(BlockRegistry.BAMBOO_TORCH, BlockRegistry.BAMBOO_WALL_TORCH, new FabricItemSettings(), Direction.DOWN);
    public static final Item SOUL_BAMBOO_TORCH = new VerticallyAttachableBlockItem(BlockRegistry.SOUL_BAMBOO_TORCH, BlockRegistry.SOUL_BAMBOO_WALL_TORCH, new FabricItemSettings(), Direction.DOWN);
    //endregion
    //region Foods
    public static final Item VENISON = new Item(new FabricItemSettings().food(Foods.VENISON));
    public static final Item COOKED_VENISON = new Item(new FabricItemSettings().food(Foods.COOKED_VENISON));
    //endregion
    //region Miscellaneous
    public static final Item ANTLER = new Item(new FabricItemSettings());
    //endregion

    @Override
    public void register() {
        registerItem(new Identifier(NAMESPACE, "mahogany_leaves"), MAHOGANY_LEAVES, Items.JUNGLE_LEAVES, ItemGroups.NATURAL);
        registerItem(new Identifier(NAMESPACE, "mahogany_sign"), MAHOGANY_SIGN, Items.JUNGLE_SIGN, ItemGroups.FUNCTIONAL);
        registerItem(new Identifier(NAMESPACE, "mahogany_chest_boat"), MAHOGANY_CHEST_BOAT, Items.JUNGLE_CHEST_BOAT, ItemGroups.TOOLS);
        registerItem(new Identifier(NAMESPACE, "mahogany_boat"), MAHOGANY_BOAT, Items.JUNGLE_CHEST_BOAT, ItemGroups.TOOLS);
        registerItem(new Identifier(NAMESPACE, "green_aspen_leaves"), GREEN_ASPEN_LEAVES, Items.BIRCH_LEAVES, ItemGroups.NATURAL);
        registerItem(new Identifier(NAMESPACE, "green_aspen_leaf_pile"), GREEN_ASPEN_LEAF_PILE, Items.BIRCH_LEAVES, ItemGroups.NATURAL);
        registerItem(new Identifier(NAMESPACE, "aspen_sign"), ASPEN_SIGN, Items.BIRCH_SIGN, ItemGroups.FUNCTIONAL);
        registerItem(new Identifier(NAMESPACE, "aspen_chest_boat"), ASPEN_CHEST_BOAT, Items.BIRCH_CHEST_BOAT, ItemGroups.TOOLS);
        registerItem(new Identifier(NAMESPACE, "aspen_boat"), ASPEN_BOAT, Items.BIRCH_CHEST_BOAT, ItemGroups.TOOLS);
        registerItem(new Identifier(NAMESPACE, "bamboo_torch"), BAMBOO_TORCH, Items.REDSTONE_TORCH, ItemGroups.FUNCTIONAL);
        registerItem(new Identifier(NAMESPACE, "soul_bamboo_torch"), SOUL_BAMBOO_TORCH, Items.REDSTONE_TORCH, ItemGroups.FUNCTIONAL);
        registerItem(new Identifier(NAMESPACE, "venison"), VENISON, Items.COOKED_RABBIT, ItemGroups.FOOD_AND_DRINK);
        registerItem(new Identifier(NAMESPACE, "cooked_venison"), COOKED_VENISON, Items.COOKED_RABBIT, ItemGroups.FOOD_AND_DRINK);
        registerItem(new Identifier(NAMESPACE, "antler"), ANTLER, Items.TURTLE_EGG, ItemGroups.NATURAL);
    }

    @Override
    public void registerClient() {
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> FoliageColors.getDefaultColor(), MAHOGANY_LEAVES, GREEN_ASPEN_LEAVES, GREEN_ASPEN_LEAF_PILE);
    }

    public static void registerItem(Identifier id, Item item, @Nullable ItemGroup group) {
        registerItem(id, item, null, group);
    }

    public static void registerItem(Identifier id, Item item, @Nullable ItemConvertible after, @Nullable ItemGroup group) {
        Registry.register(Registries.ITEM, id, item);
        if (group != null) {
            if (after != null) {
                ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.addAfter(after, item));
            } else {
                ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
            }
        }
    }

    public static void registerItem(Identifier id, Item item, @Nullable ItemGroupSettings... groups) {
        Registry.register(Registries.ITEM, id, item);
        if (groups != null) {
            for (ItemGroupSettings settings : groups) {
                if (settings.after() != null) {
                    ItemGroupEvents.modifyEntriesEvent(settings.group()).register(entries -> entries.addAfter(settings.after(), item));
                } else {
                    ItemGroupEvents.modifyEntriesEvent(settings.group()).register(entries -> entries.add(item));
                }
            }
        }
    }

    public static record ItemGroupSettings(ItemGroup group, @Nullable ItemConvertible after) { }
}
