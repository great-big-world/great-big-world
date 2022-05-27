package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.block.CakelikeBlock;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {
    //region World Of Plenty
    public static final Block PACKED_DIRT = new Block(FabricBlockSettings.copy(Blocks.DIRT));
    public static final Block DIRT_BRICKS = new Block(FabricBlockSettings.copy(Blocks.DIRT));
    public static final Block DIRT_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(DIRT_BRICKS));
    public static final Block DIRT_BRICK_STAIRS = new StairsBlock(DIRT_BRICKS.getDefaultState(), FabricBlockSettings.copy(DIRT_BRICKS));
    public static final Block DIRT_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(DIRT_BRICKS));
    public static final Block GOLD_DEPOSIT = new Block(FabricBlockSettings.copy(Blocks.DIRT));
    public static final Block IRON_DEPOSIT = new Block(FabricBlockSettings.copy(Blocks.DIRT));
    public static final Block COPPER_DEPOSIT = new Block(FabricBlockSettings.copy(Blocks.DIRT));
    public static final Block CLAY_DEPOSIT = new Block(FabricBlockSettings.copy(Blocks.DIRT));
    public static final Block PEAT_DEPOSIT = new Block(FabricBlockSettings.copy(Blocks.DIRT));
    public static final Block APPLE_PIE = new CakelikeBlock(new FoodComponent.Builder().hunger(3).saturationModifier(.25f).build(), FabricBlockSettings.copy(Blocks.CAKE));
    public static final Block MARIGOLD = new FlowerBlock(StatusEffects.SATURATION, 9, FabricBlockSettings.copy(Blocks.DANDELION));
    public static final Block POTTED_MARIGOLD = new FlowerPotBlock(MARIGOLD, FabricBlockSettings.copy(Blocks.POTTED_DANDELION));
    //endregion

    //region Change The World
    public static Block PERMAFROST = new Block(FabricBlockSettings.of(Material.SOIL).strength(.65f).sounds(BlockSoundGroup.GRASS));
    public static Block PALO_VERDE_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.PALE_GREEN));
    public static Block STRIPPED_PALO_VERDE_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.PALE_GREEN));
    public static Block PALO_VERDE_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.PALE_GREEN));
    public static Block PALO_VERDE_LEAVES = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
    public static Block REDWOOD_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.SPRUCE_BROWN));
    public static Block STRIPPED_REDWOOD_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.SPRUCE_BROWN));
    public static Block REDWOOD_PLANKS = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.TERRACOTTA_RED));
    public static Block REDWOOD_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
    //endregion

    public static void register() {
        //if (GreatBigWorld.isLoaded("world_of_plenty"))
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "packed_dirt"), PACKED_DIRT, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "dirt_bricks"), DIRT_BRICKS, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "dirt_brick_slab"), DIRT_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "dirt_brick_stairs"), DIRT_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "dirt_brick_wall"), DIRT_BRICK_WALL, ItemGroup.DECORATIONS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "gold_deposit"), GOLD_DEPOSIT, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "iron_deposit"), IRON_DEPOSIT, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "copper_deposit"), COPPER_DEPOSIT, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "clay_deposit"), CLAY_DEPOSIT, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "peat_deposit"), PEAT_DEPOSIT, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "apple_pie"), APPLE_PIE, ItemGroup.FOOD);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "marigold"), MARIGOLD, ItemGroup.DECORATIONS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "potted_marigold"), POTTED_MARIGOLD, null);

        //if (GreatBigWorld.isLoaded("change_the_world"))
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "permafrost"), PERMAFROST, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "palo_verde_log"), PALO_VERDE_LOG, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "stripped_palo_verde_log"), STRIPPED_PALO_VERDE_LOG, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "palo_verde_planks"), PALO_VERDE_PLANKS, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "palo_verde_leaves"), PALO_VERDE_LEAVES, ItemGroup.DECORATIONS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "redwood_log"), REDWOOD_LOG, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "stripped_redwood_log"), STRIPPED_REDWOOD_LOG, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "redwood_planks"), REDWOOD_PLANKS, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(GreatBigWorld.MOD_ID, "redwood_leaves"), REDWOOD_LEAVES, ItemGroup.DECORATIONS);

        flammables();
        strippables();
        compostables();
    }

    private static void flammables() {
        FireBlock fire = (FireBlock) Blocks.FIRE;
        fire.registerFlammableBlock(PALO_VERDE_LOG, 5, 5);
        fire.registerFlammableBlock(STRIPPED_PALO_VERDE_LOG, 5, 5);
        fire.registerFlammableBlock(PALO_VERDE_PLANKS, 5, 20);
        fire.registerFlammableBlock(PALO_VERDE_LEAVES, 30, 60);
        fire.registerFlammableBlock(REDWOOD_LOG, 5, 5);
        fire.registerFlammableBlock(STRIPPED_REDWOOD_LOG, 5, 5);
        fire.registerFlammableBlock(REDWOOD_PLANKS, 5, 20);
        fire.registerFlammableBlock(REDWOOD_LEAVES, 30, 60);
        fire.registerFlammableBlock(MARIGOLD, 60, 100);
    }

    private static void strippables() {
        AxeItem.STRIPPED_BLOCKS = new ImmutableMap.Builder<Block, Block>()
                .putAll(AxeItem.STRIPPED_BLOCKS)
                .put(PALO_VERDE_LOG, STRIPPED_PALO_VERDE_LOG)
                .put(REDWOOD_LOG, STRIPPED_REDWOOD_LOG)
                .build();
    }

    private static void compostables() {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(MARIGOLD, .65f);
    }

    @Environment(EnvType.CLIENT)
    public static void renderLayers() {
        RenderLayers.BLOCKS.put(MARIGOLD, RenderLayer.getCutout());
        RenderLayers.BLOCKS.put(POTTED_MARIGOLD, RenderLayer.getCutout());
    }

    public static <B extends Block> Block registerBlock(Identifier id, B block, ItemGroup group) {
        Registry.register(Registry.BLOCK, id, block);
        if (group != null) Registry.register(Registry.ITEM, id, new BlockItem(block, new Item.Settings().group(group)));
        return block;
    }
}
