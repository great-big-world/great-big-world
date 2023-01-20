package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.Foods;
import com.github.creoii.greatbigworld.main.util.GBWBoatTypes;
import com.github.creoii.greatbigworld.main.util.ItemUtil;
import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class ItemRegistry implements Register {
    //region Mahogany Wood
    public static final Item MAHOGANY_LEAVES = new BlockItem(BlockRegistry.MAHOGANY_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.JUNGLE_LEAVES);
        }
    };
    public static final Item MAHOGANY_SIGN = new SignItem(new FabricItemSettings().group(ItemGroup.DECORATIONS).maxCount(16), BlockRegistry.MAHOGANY_SIGN, BlockRegistry.MAHOGANY_WALL_SIGN) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.JUNGLE_SIGN);
        }
    };
    public static final Item MAHOGANY_BOAT = new BoatItem(false, GBWBoatTypes.MAHOGANY, new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_CHEST_BOAT);
        }
    };
    public static final Item MAHOGANY_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.MAHOGANY, new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_CHEST_BOAT);
        }
    };
    //endregion
    //region Aspen Wood
    public static final Item GREEN_ASPEN_LEAVES = new BlockItem(BlockRegistry.GREEN_ASPEN_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.BIRCH_LEAVES);
        }
    };
    public static final Item GREEN_ASPEN_LEAF_PILE = new BlockItem(BlockRegistry.GREEN_ASPEN_LEAF_PILE, new FabricItemSettings().group(ItemGroup.DECORATIONS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.BIRCH_LEAVES);
        }
    };
    public static final Item ASPEN_SIGN = new SignItem(new FabricItemSettings().group(ItemGroup.DECORATIONS).maxCount(16), BlockRegistry.ASPEN_SIGN, BlockRegistry.ASPEN_WALL_SIGN) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.BIRCH_SIGN);
        }
    };
    public static final Item ASPEN_BOAT = new BoatItem(false, GBWBoatTypes.ASPEN, new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_CHEST_BOAT);
        }
    };
    public static final Item ASPEN_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.ASPEN, new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_CHEST_BOAT);
        }
    };
    //endregion
    //region Bamboo Torches
    public static final Item BAMBOO_TORCH = new WallStandingBlockItem(BlockRegistry.BAMBOO_TORCH, BlockRegistry.BAMBOO_WALL_TORCH, new FabricItemSettings().group(ItemGroup.DECORATIONS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.TORCH);
        }
    };
    public static final Item SOUL_BAMBOO_TORCH = new WallStandingBlockItem(BlockRegistry.SOUL_BAMBOO_TORCH, BlockRegistry.SOUL_BAMBOO_WALL_TORCH, new FabricItemSettings().group(ItemGroup.DECORATIONS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.TORCH);
        }
    };
    //endregion
    //region Foods
    public static final Item VENISON = new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(Foods.VENISON)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.COOKED_RABBIT);
        }
    };
    public static final Item COOKED_VENISON = new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(Foods.COOKED_VENISON)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.COOKED_RABBIT);
        }
    };
    //endregion
    //region Miscellaneous
    public static final Item ANTLER = new Item(new FabricItemSettings().group(ItemGroup.DECORATIONS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.TURTLE_EGG);
        }
    };
    //endregion

    @Override
    public void register() {
        registerItem(new Identifier(NAMESPACE, "mahogany_leaves"), MAHOGANY_LEAVES);
        registerItem(new Identifier(NAMESPACE, "mahogany_sign"), MAHOGANY_SIGN);
        registerItem(new Identifier(NAMESPACE, "mahogany_chest_boat"), MAHOGANY_CHEST_BOAT);
        registerItem(new Identifier(NAMESPACE, "mahogany_boat"), MAHOGANY_BOAT);
        registerItem(new Identifier(NAMESPACE, "green_aspen_leaves"), GREEN_ASPEN_LEAVES);
        registerItem(new Identifier(NAMESPACE, "green_aspen_leaf_pile"), GREEN_ASPEN_LEAF_PILE);
        registerItem(new Identifier(NAMESPACE, "aspen_sign"), ASPEN_SIGN);
        registerItem(new Identifier(NAMESPACE, "aspen_chest_boat"), ASPEN_CHEST_BOAT);
        registerItem(new Identifier(NAMESPACE, "aspen_boat"), ASPEN_BOAT);
        registerItem(new Identifier(NAMESPACE, "bamboo_torch"), BAMBOO_TORCH);
        registerItem(new Identifier(NAMESPACE, "soul_bamboo_torch"), SOUL_BAMBOO_TORCH);
        registerItem(new Identifier(NAMESPACE, "venison"), VENISON);
        registerItem(new Identifier(NAMESPACE, "cooked_venison"), COOKED_VENISON);
        registerItem(new Identifier(NAMESPACE, "antler"), ANTLER);
        compostables();
    }

    private void compostables() {
        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.HEATHER, .65f);
        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.TALL_HEATHER, .65f);
        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.DAYLIGHT_MUSHROOM, .15f);
        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.MIDNIGHT_MUSHROOM, .15f);
        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.DARKBLIGHT_MUSHROOM, .15f);
        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.YELLOW_ASPEN_LEAVES, .3f);
        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.YELLOW_ASPEN_LEAF_PILE, .1f);
        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.YELLOW_ASPEN_SAPLING, .3f);
        CompostingChanceRegistry.INSTANCE.add(GREEN_ASPEN_LEAVES, .3f);
        CompostingChanceRegistry.INSTANCE.add(GREEN_ASPEN_LEAF_PILE, .1f);
        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.GREEN_ASPEN_SAPLING, .3f);
        CompostingChanceRegistry.INSTANCE.add(MAHOGANY_LEAVES, .3f);
        CompostingChanceRegistry.INSTANCE.add(BlockRegistry.MAHOGANY_SAPLING, .3f);
    }

    @Override
    public void registerClient() {
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> FoliageColors.getDefaultColor(), MAHOGANY_LEAVES, GREEN_ASPEN_LEAVES, GREEN_ASPEN_LEAF_PILE);
    }

    public static void registerItem(Identifier id, Item item) {
        Registry.register(Registry.ITEM, id, item);
    }

    public static record ItemGroupSettings(ItemGroup group, @Nullable ItemConvertible after) { }
}
