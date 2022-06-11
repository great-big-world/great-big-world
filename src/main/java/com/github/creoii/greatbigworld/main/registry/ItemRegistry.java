package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Foods;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
    //region Cavier Caves
    public static final Item QUICKSAND_BUCKET = new BucketItem(Fluids.EMPTY, new FabricItemSettings().group(ItemGroup.MISC));
    //endregion

    //region Change The World
    public static final Item GRASSY_STONE = new BlockItem(BlockRegistry.GRASSY_STONE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item GRASSY_DEEPSLATE = new BlockItem(BlockRegistry.GRASSY_DEEPSLATE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    //endregion

    //region Colormatic
    public static final Item HANGING_OAK_LEAVES = new BlockItem(BlockRegistry.HANGING_OAK_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item HANGING_SPRUCE_LEAVES = new BlockItem(BlockRegistry.HANGING_SPRUCE_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item HANGING_BIRCH_LEAVES = new BlockItem(BlockRegistry.HANGING_BIRCH_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item HANGING_JUNGLE_LEAVES = new BlockItem(BlockRegistry.HANGING_JUNGLE_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item HANGING_DARK_OAK_LEAVES = new BlockItem(BlockRegistry.HANGING_DARK_OAK_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item HANGING_ACACIA_LEAVES = new BlockItem(BlockRegistry.HANGING_ACACIA_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item HANGING_MANGROVE_LEAVES = new BlockItem(BlockRegistry.HANGING_MANGROVE_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    //endregion

    //region Honeycomb
    public static final Item BEAR_SPAWN_EGG = new SpawnEggItem(EntityRegistry.BEAR, 0, 0, new FabricItemSettings().group(ItemGroup.MISC));
    //endregion

    //region Magic V Melee
    public static final Item SOUR_BERRIES = new AliasedBlockItem(BlockRegistry.SOUR_BERRY_BUSH, new FabricItemSettings().food(Foods.SOUR_BERRIES).group(ItemGroup.FOOD));
    public static final Item BITTER_BERRIES = new AliasedBlockItem(BlockRegistry.BITTER_BERRY_BUSH, new FabricItemSettings().food(Foods.SOUR_BERRIES).group(ItemGroup.FOOD));
    public static final Item PUNGENT_BERRIES = new AliasedBlockItem(BlockRegistry.PUNGENT_BERRY_BUSH, new FabricItemSettings().food(Foods.SOUR_BERRIES).group(ItemGroup.FOOD));
    //endregion

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.MOD_ID, "quicksand_bucket"), QUICKSAND_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.MOD_ID, "grassy_stone"), GRASSY_STONE);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.MOD_ID, "grassy_deepslate"), GRASSY_DEEPSLATE);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.MOD_ID, "hanging_oak_leaves"), HANGING_OAK_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.MOD_ID, "hanging_spruce_leaves"), HANGING_SPRUCE_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.MOD_ID, "hanging_birch_leaves"), HANGING_BIRCH_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.MOD_ID, "hanging_jungle_leaves"), HANGING_JUNGLE_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.MOD_ID, "hanging_dark_oak_leaves"), HANGING_DARK_OAK_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.MOD_ID, "hanging_acacia_leaves"), HANGING_ACACIA_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.MOD_ID, "hanging_mangrove_leaves"), HANGING_MANGROVE_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.MOD_ID, "bear_spawn_egg"), BEAR_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.MOD_ID, "sour_berries"), SOUR_BERRIES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.MOD_ID, "bitter_berries"), BITTER_BERRIES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.MOD_ID, "pungent_berries"), PUNGENT_BERRIES);
    }

    @Environment(EnvType.CLIENT)
    public static void tintItems() {
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> GrassColors.getColor(.5d, 1d), GRASSY_STONE, GRASSY_DEEPSLATE);
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> FoliageColors.getDefaultColor(), HANGING_OAK_LEAVES, HANGING_JUNGLE_LEAVES, HANGING_ACACIA_LEAVES, HANGING_DARK_OAK_LEAVES, HANGING_MANGROVE_LEAVES);
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> FoliageColors.getSpruceColor(), HANGING_SPRUCE_LEAVES);
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> FoliageColors.getBirchColor(), HANGING_BIRCH_LEAVES);
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> FoliageColors.method_43717(), HANGING_MANGROVE_LEAVES);
    }
}
