package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.GBWBoatTypes;
import com.github.creoii.greatbigworld.main.util.ItemUtil;
import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.*;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class ItemRegistry implements Register {
    //region Mahogany Wood
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
    public static final Item MAHOGANY_BOAT = new BoatItem(false, GBWBoatTypes.MAHOGANY, new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1));
    public static final Item MAHOGANY_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.MAHOGANY, new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1));
    //endregion
    //region Dried Bamboo
    public static final Item DRIED_BAMBOO_SIGN = new SignItem(new Item.Settings().maxCount(16).group(ItemGroup.DECORATIONS), BlockRegistry.DRIED_BAMBOO_SIGN, BlockRegistry.DRIED_BAMBOO_WALL_SIGN) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.WARPED_SIGN);
        }
    };
    public static final Item DRIED_BAMBOO_BOAT = new BoatItem(false, GBWBoatTypes.DRIED_BAMBOO, new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1));
    public static final Item DRIED_BAMBOO_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.DRIED_BAMBOO, new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1));
    //endregion
    //region Aspen Wood
    public static final Item GREEN_ASPEN_LEAVES = new BlockItem(BlockRegistry.GREEN_ASPEN_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.FLOWERING_AZALEA_LEAVES); }
    };
    public static final Item ASPEN_SIGN = new SignItem(new Item.Settings().maxCount(16).group(ItemGroup.DECORATIONS), BlockRegistry.ASPEN_SIGN, BlockRegistry.ASPEN_WALL_SIGN) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.WARPED_SIGN);
        }
    };
    public static final Item ASPEN_BOAT = new BoatItem(false, GBWBoatTypes.ASPEN, new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1));
    public static final Item ASPEN_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.ASPEN, new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1));
    //endregion
    //region Bamboo Torches
    public static final Item BAMBOO_TORCH_ITEM = new WallStandingBlockItem(BlockRegistry.BAMBOO_TORCH, BlockRegistry.BAMBOO_WALL_TORCH, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item SOUL_BAMBOO_TORCH_ITEM = new WallStandingBlockItem(BlockRegistry.SOUL_BAMBOO_TORCH, BlockRegistry.SOUL_BAMBOO_WALL_TORCH, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    //endregion

    @Override
    public void register() {
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "mahogany_leaves"), MAHOGANY_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "mahogany_sign"), MAHOGANY_SIGN);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "mahogany_boat"), MAHOGANY_BOAT);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "mahogany_chest_boat"), MAHOGANY_CHEST_BOAT);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "dried_bamboo_sign"), DRIED_BAMBOO_SIGN);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "dried_bamboo_boat"), DRIED_BAMBOO_BOAT);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "dried_bamboo_chest_boat"), DRIED_BAMBOO_CHEST_BOAT);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "green_aspen_leaves"), GREEN_ASPEN_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "aspen_sign"), ASPEN_SIGN);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "aspen_boat"), ASPEN_BOAT);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "aspen_chest_boat"), ASPEN_CHEST_BOAT);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "bamboo_torch"), BAMBOO_TORCH_ITEM);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "soul_bamboo_torch"), SOUL_BAMBOO_TORCH_ITEM);
    }

    @Override
    public void registerClient() {
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> FoliageColors.getDefaultColor(), MAHOGANY_LEAVES, GREEN_ASPEN_LEAVES);
    }
}
