package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.creolib.api.util.block.BlockRegistryHelper;
import com.github.creoii.creolib.api.util.block.CBlockSettings;
import com.github.creoii.creolib.api.util.block.Spreadable;
import com.github.creoii.creolib.api.util.item.CItemSettings;
import com.github.creoii.creolib.api.util.item.ItemRegistryHelper;
import com.github.creoii.creolib.api.util.registry.RegistrySets;
import com.github.creoii.greatbigworld.block.*;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.sapling.AcaiSaplingGenerator;
import com.github.creoii.greatbigworld.world.sapling.GreenAspenSaplingGenerator;
import com.github.creoii.greatbigworld.world.sapling.MahoganySaplingGenerator;
import com.github.creoii.greatbigworld.world.sapling.YellowAspenSaplingGenerator;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.List;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class GBWBlocks implements Register {
    //region Mahogany Wood
    public static RegistrySets.WoodSet MAHOGANY = RegistrySets.createWoodSet(NAMESPACE, "mahogany", MapColor.TERRACOTTA_BROWN, MapColor.TERRACOTTA_ORANGE, Items.JUNGLE_BUTTON, Items.JUNGLE_LOG, Items.JUNGLE_SIGN);
    public static final Block MAHOGANY_LEAVES = new LeavesBlock(CBlockSettings.copy(Blocks.OAK_LEAVES).fireSettings(30, 60));
    public static final Block MAHOGANY_SAPLING = new SaplingBlock(new MahoganySaplingGenerator(), CBlockSettings.copy(Blocks.JUNGLE_SAPLING));
    public static final Block POTTED_MAHOGANY_SAPLING = new FlowerPotBlock(MAHOGANY_SAPLING, CBlockSettings.copy(Blocks.FLOWER_POT));
    //endregion
    //region Aspen Wood
    public static RegistrySets.WoodSet ASPEN = RegistrySets.createWoodSet(NAMESPACE, "aspen", MapColor.TERRACOTTA_BROWN, MapColor.TERRACOTTA_ORANGE, Items.BIRCH_BUTTON, Items.BIRCH_LOG, Items.BIRCH_SIGN);
    public static final Block YELLOW_ASPEN_LEAVES = new LeavesBlock(CBlockSettings.copy(Blocks.OAK_LEAVES).mapColor(MapColor.YELLOW).fireSettings(30, 60));
    public static final Block YELLOW_ASPEN_LEAF_PILE = new LeafPileBlock(CBlockSettings.of(Material.DECORATION, MapColor.YELLOW).strength(.1f).sounds(BlockSoundGroup.GRASS).nonOpaque().noCollision().fireSettings(30, 60));
    public static final Block GREEN_ASPEN_LEAVES = new LeavesBlock(CBlockSettings.copy(Blocks.OAK_LEAVES).fireSettings(30, 60));
    public static final Block GREEN_ASPEN_LEAF_PILE = new LeafPileBlock(CBlockSettings.copy(YELLOW_ASPEN_LEAF_PILE).mapColor(MapColor.DARK_GREEN));
    public static final Block YELLOW_ASPEN_SAPLING = new SaplingBlock(new YellowAspenSaplingGenerator(), CBlockSettings.copy(Blocks.BIRCH_SAPLING));
    public static final Block POTTED_YELLOW_ASPEN_SAPLING = new FlowerPotBlock(YELLOW_ASPEN_SAPLING, CBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block GREEN_ASPEN_SAPLING = new SaplingBlock(new GreenAspenSaplingGenerator(), CBlockSettings.copy(Blocks.BIRCH_SAPLING));
    public static final Block POTTED_GREEN_ASPEN_SAPLING = new FlowerPotBlock(GREEN_ASPEN_SAPLING, CBlockSettings.copy(Blocks.FLOWER_POT));
    //endregion
    //region Bamboo Torches
    public static final Block BAMBOO_TORCH = new BambooTorchBlock(ParticleTypes.FLAME);
    public static final Block BAMBOO_WALL_TORCH = new BambooWallTorchBlock(ParticleTypes.FLAME);
    public static final Block SOUL_BAMBOO_TORCH = new BambooTorchBlock(ParticleTypes.SOUL_FIRE_FLAME);
    public static final Block SOUL_BAMBOO_WALL_TORCH = new BambooWallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME);
    public static final Block POTTED_BAMBOO_TORCH = new FlowerPotBlock(BAMBOO_TORCH, CBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block POTTED_SOUL_BAMBOO_TORCH = new FlowerPotBlock(SOUL_BAMBOO_TORCH, CBlockSettings.copy(Blocks.FLOWER_POT));
    //endregion
    //region Glimmering Mushrooms
    public static final Block DAYLIGHT_MUSHROOM = new GlimmeringMushroomBlock(GBWParticles.DAY_GLIMMER, new StatusEffectInstance(StatusEffects.GLOWING, 100), 4, 16765440);
    public static final Block MIDNIGHT_MUSHROOM = new GlimmeringMushroomBlock(GBWParticles.NIGHT_GLIMMER, new StatusEffectInstance(StatusEffects.BLINDNESS, 100), 8, 9558015);
    public static final Block DARKBLIGHT_MUSHROOM = new GlimmeringMushroomBlock(GBWParticles.DARK_GLIMMER, new StatusEffectInstance(StatusEffects.DARKNESS, 150), 12, 0);
    //endregion
    //region Cobblestone Bricks
    public static final Block COBBLESTONE_BRICKS = new Block(CBlockSettings.copy(Blocks.COBBLESTONE));
    public static final Block COBBLESTONE_BRICK_STAIRS = new StairsBlock(COBBLESTONE_BRICKS.getDefaultState(), CBlockSettings.copy(Blocks.COBBLESTONE_STAIRS));
    public static final Block COBBLESTONE_BRICK_SLAB = new SlabBlock(CBlockSettings.copy(Blocks.COBBLESTONE_SLAB));
    public static final Block COBBLESTONE_BRICK_WALL = new WallBlock(CBlockSettings.copy(Blocks.COBBLESTONE_WALL));
    public static final Block CHISELED_COBBLESTONE_BRICKS = new Block(CBlockSettings.copy(COBBLESTONE_BRICKS));
    public static final Block MOSSY_COBBLESTONE_BRICKS = new Block(CBlockSettings.copy(Blocks.MOSSY_COBBLESTONE));
    public static final Block MOSSY_COBBLESTONE_BRICK_STAIRS = new StairsBlock(MOSSY_COBBLESTONE_BRICKS.getDefaultState(), CBlockSettings.copy(Blocks.MOSSY_COBBLESTONE_STAIRS));
    public static final Block MOSSY_COBBLESTONE_BRICK_SLAB = new SlabBlock(CBlockSettings.copy(Blocks.MOSSY_COBBLESTONE_SLAB));
    public static final Block MOSSY_COBBLESTONE_BRICK_WALL = new WallBlock(CBlockSettings.copy(Blocks.MOSSY_COBBLESTONE_WALL));
    //endregion
    //region Stained Calcite
    public static final Block BROWN_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_BROWN));
    public static final Block RED_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_RED));
    public static final Block ORANGE_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Block YELLOW_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_YELLOW));
    public static final Block LIME_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_LIME));
    public static final Block GREEN_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_GREEN));
    public static final Block CYAN_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_CYAN));
    public static final Block BLUE_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_BLACK));
    public static final Block LIGHT_BLUE_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE));
    public static final Block PINK_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_PINK));
    public static final Block MAGENTA_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_MAGENTA));
    public static final Block PURPLE_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_PURPLE));
    public static final Block BLACK_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_BLACK));
    public static final Block GRAY_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_GRAY));
    public static final Block LIGHT_GRAY_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY));
    public static final Block WHITE_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_RED));
    //endregion
    //region Thatch
    public static final Block TRIMMED_BEACHGRASS_THATCH = new PillarBlock(CBlockSettings.of(Material.SOLID_ORGANIC, MapColor.PALE_YELLOW).strength(.5f).sounds(BlockSoundGroup.GRASS).nonOpaque().fireSettings(70, 140));
    public static final Block BEACHGRASS_THATCH = new ThatchBlock(CBlockSettings.copy(TRIMMED_BEACHGRASS_THATCH).fireSettings(80, 160), TRIMMED_BEACHGRASS_THATCH);
    public static final Block TRIMMED_BEACHGRASS_THATCH_STAIRS = new StairsBlock(TRIMMED_BEACHGRASS_THATCH.getDefaultState(), CBlockSettings.copy(TRIMMED_BEACHGRASS_THATCH).fireSettings(70, 140));
    public static final Block BEACHGRASS_THATCH_STAIRS = new ThatchStairsBlock(CBlockSettings.copy(BEACHGRASS_THATCH).fireSettings(80, 160), BEACHGRASS_THATCH.getDefaultState(), TRIMMED_BEACHGRASS_THATCH_STAIRS);
    public static final Block TRIMMED_BEACHGRASS_THATCH_SLAB = new SlabBlock(CBlockSettings.copy(TRIMMED_BEACHGRASS_THATCH).fireSettings(70, 140));
    public static final Block BEACHGRASS_THATCH_SLAB = new ThatchSlabBlock(CBlockSettings.copy(BEACHGRASS_THATCH).fireSettings(80, 160), TRIMMED_BEACHGRASS_THATCH_SLAB);
    public static final Block TRIMMED_BAMBOO_THATCH = new PillarBlock(CBlockSettings.of(Material.SOLID_ORGANIC, MapColor.GREEN).strength(.5f).sounds(BlockSoundGroup.GRASS).nonOpaque().fireSettings(70, 140));
    public static final Block BAMBOO_THATCH = new ThatchBlock(CBlockSettings.copy(TRIMMED_BAMBOO_THATCH).fireSettings(80, 160), TRIMMED_BAMBOO_THATCH);
    public static final Block TRIMMED_BAMBOO_THATCH_STAIRS = new StairsBlock(TRIMMED_BAMBOO_THATCH.getDefaultState(), CBlockSettings.copy(TRIMMED_BAMBOO_THATCH).fireSettings(70, 140));
    public static final Block BAMBOO_THATCH_STAIRS = new ThatchStairsBlock(CBlockSettings.copy(BAMBOO_THATCH).fireSettings(80, 160), BAMBOO_THATCH.getDefaultState(), TRIMMED_BAMBOO_THATCH_STAIRS);
    public static final Block TRIMMED_BAMBOO_THATCH_SLAB = new SlabBlock(CBlockSettings.copy(TRIMMED_BAMBOO_THATCH).fireSettings(70, 140));
    public static final Block BAMBOO_THATCH_SLAB = new ThatchSlabBlock(CBlockSettings.copy(BAMBOO_THATCH).fireSettings(80, 160), TRIMMED_BAMBOO_THATCH_SLAB);
    public static final Block TRIMMED_GRASS_THATCH = new PillarBlock(CBlockSettings.of(Material.SOLID_ORGANIC, MapColor.PALE_GREEN).strength(.5f).sounds(BlockSoundGroup.GRASS).nonOpaque().fireSettings(70, 140));
    public static final Block GRASS_THATCH = new ThatchBlock(CBlockSettings.copy(TRIMMED_GRASS_THATCH).fireSettings(80, 160), TRIMMED_GRASS_THATCH);
    public static final Block TRIMMED_GRASS_THATCH_STAIRS = new StairsBlock(TRIMMED_GRASS_THATCH.getDefaultState(), CBlockSettings.copy(TRIMMED_GRASS_THATCH).fireSettings(70, 140));
    public static final Block GRASS_THATCH_STAIRS = new ThatchStairsBlock(CBlockSettings.copy(GRASS_THATCH).fireSettings(80, 160), GRASS_THATCH.getDefaultState(), TRIMMED_GRASS_THATCH_STAIRS);
    public static final Block TRIMMED_GRASS_THATCH_SLAB = new SlabBlock(CBlockSettings.copy(TRIMMED_GRASS_THATCH).fireSettings(70, 140));
    public static final Block GRASS_THATCH_SLAB = new ThatchSlabBlock(CBlockSettings.copy(GRASS_THATCH).fireSettings(80, 160), TRIMMED_GRASS_THATCH_SLAB);
    //endregion
    //region Lavarock
    public static final Block VOLCANIC_SAND = new VolcanicSandBlock();
    public static final Block LAVAROCK = new Block(CBlockSettings.copy(Blocks.STONE).mapColor(MapColor.BLACK));
    public static final Block GRASSY_LAVAROCK = new GrassyLavarockBlock();
    public static final Block LAVAROCK_STAIRS = new StairsBlock(LAVAROCK.getDefaultState(), CBlockSettings.copy(LAVAROCK));
    public static final Block LAVAROCK_SLAB = new SlabBlock(CBlockSettings.copy(LAVAROCK));
    public static final Block LAVAROCK_WALL = new WallBlock(CBlockSettings.copy(LAVAROCK));
    public static final Block LAVAROCK_BRICKS = new Block(CBlockSettings.copy(Blocks.STONE_BRICKS).mapColor(MapColor.BLACK));
    public static final Block LAVAROCK_BRICK_STAIRS = new StairsBlock(LAVAROCK_BRICKS.getDefaultState(), CBlockSettings.copy(LAVAROCK_BRICKS));
    public static final Block LAVAROCK_BRICK_SLAB = new SlabBlock(CBlockSettings.copy(LAVAROCK_BRICKS));
    public static final Block LAVAROCK_BRICK_WALL = new WallBlock(CBlockSettings.copy(LAVAROCK_BRICKS));
    //endregion
    //region Acai
    public static RegistrySets.WoodSet ACAI = RegistrySets.createWoodSet(NAMESPACE, "acai", MapColor.TERRACOTTA_BROWN, MapColor.TERRACOTTA_YELLOW, MAHOGANY.button(), MAHOGANY.log(), MAHOGANY.sign());
    public static final Block ACAI_LEAVES = new LeavesBlock(CBlockSettings.copy(Blocks.JUNGLE_LEAVES).fireSettings(30, 60));
    public static final Block HANGING_ACAI_LEAVES = new HangingLeavesBlock(CBlockSettings.copy(ACAI_LEAVES).fireSettings(30, 60));
    public static final Block ACAI_SAPLING = new SaplingBlock(new AcaiSaplingGenerator(), CBlockSettings.copy(MAHOGANY_SAPLING));
    public static final Block POTTED_ACAI_SAPLING = new FlowerPotBlock(ACAI_SAPLING, CBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block ACAI_BERRY_CLUMP = new AcaiBerryClumpBlock(CBlockSettings.copy(Blocks.MUSHROOM_STEM).mapColor(MapColor.TERRACOTTA_PURPLE).ticksRandomly());
    //endregion
    //region Elder Prismarine
    public static final Block ELDER_PRISMARINE = new Block(CBlockSettings.copy(Blocks.PRISMARINE).mapColor(MapColor.PALE_YELLOW));
    public static final Block ELDER_PRISMARINE_SLAB = new SlabBlock(CBlockSettings.copy(ELDER_PRISMARINE));
    public static final Block ELDER_PRISMARINE_STAIRS = new StairsBlock(ELDER_PRISMARINE.getDefaultState(), CBlockSettings.copy(ELDER_PRISMARINE));
    public static final Block ELDER_PRISMARINE_WALL = new WallBlock(CBlockSettings.copy(ELDER_PRISMARINE));
    public static final Block ELDER_PRISMARINE_BRICKS = new Block(CBlockSettings.copy(Blocks.PRISMARINE_BRICKS).mapColor(MapColor.PALE_YELLOW));
    public static final Block ELDER_PRISMARINE_BRICK_SLAB = new SlabBlock(CBlockSettings.copy(ELDER_PRISMARINE_BRICKS));
    public static final Block ELDER_PRISMARINE_BRICK_STAIRS = new StairsBlock(ELDER_PRISMARINE_BRICKS.getDefaultState(), CBlockSettings.copy(ELDER_PRISMARINE_BRICKS));
    public static final Block ELDER_PRISMARINE_BRICK_WALL = new WallBlock(CBlockSettings.copy(ELDER_PRISMARINE_BRICKS));
    public static final Block ELDER_SEA_LANTERN = new Block(CBlockSettings.copy(Blocks.SEA_LANTERN).mapColor(MapColor.PALE_YELLOW).luminance(state -> 14));
    //endregion
    //region Miscellaneous
    public static final Block ANTLER = new AntlerBlock();
    public static final Block TALL_HEATHER = new TallFlowerBlock(CBlockSettings.copy(Blocks.ROSE_BUSH).fireSettings(60, 100));
    public static final Block HEATHER = new FernBlock(CBlockSettings.copy(Blocks.POPPY).fireSettings(60, 100));
    public static final Block POTTED_HEATHER = new FlowerPotBlock(HEATHER, CBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block BEACHGRASS = new BeachgrassBlock();
    public static final Block TALL_BEACHGRASS = new TallBeachgrassBlock();
    public static final Block POTTED_BEACHGRASS = new FlowerPotBlock(BEACHGRASS, CBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block TROPICAL_FERN = new FernBlock(CBlockSettings.copy(Blocks.FERN).fireSettings(60, 100));
    public static final Block LARGE_TROPICAL_FERN = new TallPlantBlock(CBlockSettings.copy(Blocks.LARGE_FERN).fireSettings(60, 100));
    public static final Block POTTED_TROPICAL_FERN = new FlowerPotBlock(TROPICAL_FERN, CBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block NAUTILUS_FOSSIL = new Block(CBlockSettings.copy(Blocks.STONE));
    //endregion

    @Override
    public void register() {
        MAHOGANY.register();
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_leaves"), MAHOGANY_LEAVES);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_sapling"), MAHOGANY_SAPLING, new CItemSettings().compostingChance(.1f), Items.JUNGLE_SAPLING, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_mahogany_sapling"), POTTED_MAHOGANY_SAPLING);

        ASPEN.register();
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "yellow_aspen_leaves"), YELLOW_ASPEN_LEAVES, new CItemSettings().compostingChance(.3f), Items.BIRCH_LEAVES, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "yellow_aspen_leaf_pile"), YELLOW_ASPEN_LEAF_PILE, new CItemSettings().compostingChance(.1f), Items.BIRCH_LEAVES, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "yellow_aspen_sapling"), YELLOW_ASPEN_SAPLING, new CItemSettings().compostingChance(.3f), Items.BIRCH_SAPLING, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_yellow_aspen_sapling"), POTTED_YELLOW_ASPEN_SAPLING);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "green_aspen_leaves"), GREEN_ASPEN_LEAVES);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "green_aspen_leaf_pile"), GREEN_ASPEN_LEAF_PILE);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "green_aspen_sapling"), GREEN_ASPEN_SAPLING, new CItemSettings().compostingChance(.3f), Items.BIRCH_SAPLING, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_green_aspen_sapling"), POTTED_GREEN_ASPEN_SAPLING);

        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "soul_bamboo_torch"), SOUL_BAMBOO_TORCH);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "soul_bamboo_wall_torch"), SOUL_BAMBOO_WALL_TORCH);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "bamboo_torch"), BAMBOO_TORCH);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "bamboo_wall_torch"), BAMBOO_WALL_TORCH);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_bamboo_torch"), POTTED_BAMBOO_TORCH);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_soul_bamboo_torch"), POTTED_SOUL_BAMBOO_TORCH);

        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "daylight_mushroom"), DAYLIGHT_MUSHROOM, new CItemSettings().compostingChance(.15f), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, Items.RED_MUSHROOM), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.INGREDIENTS, Items.PHANTOM_MEMBRANE));
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "midnight_mushroom"), MIDNIGHT_MUSHROOM, new CItemSettings().compostingChance(.15f), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, Items.RED_MUSHROOM), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.INGREDIENTS, Items.PHANTOM_MEMBRANE));
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "darkblight_mushroom"), DARKBLIGHT_MUSHROOM, new CItemSettings().compostingChance(.15f), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, Items.RED_MUSHROOM), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.INGREDIENTS, Items.PHANTOM_MEMBRANE));

        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "cobblestone_bricks"), COBBLESTONE_BRICKS, Items.COBBLESTONE_WALL, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "cobblestone_brick_stairs"), COBBLESTONE_BRICK_STAIRS, COBBLESTONE_BRICKS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "cobblestone_brick_slab"), COBBLESTONE_BRICK_SLAB, COBBLESTONE_BRICK_STAIRS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "cobblestone_brick_wall"), COBBLESTONE_BRICK_WALL, COBBLESTONE_BRICK_SLAB, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "chiseled_cobblestone_bricks`"), CHISELED_COBBLESTONE_BRICKS, COBBLESTONE_BRICK_WALL, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mossy_cobblestone_bricks"), MOSSY_COBBLESTONE_BRICKS, Items.MOSSY_COBBLESTONE_WALL, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mossy_cobblestone_brick_stairs"), MOSSY_COBBLESTONE_BRICK_STAIRS, MOSSY_COBBLESTONE_BRICKS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mossy_cobblestone_brick_slab"), MOSSY_COBBLESTONE_BRICK_SLAB, MOSSY_COBBLESTONE_BRICK_STAIRS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mossy_cobblestone_brick_wall"), MOSSY_COBBLESTONE_BRICK_WALL, MOSSY_COBBLESTONE_BRICK_SLAB, ItemGroups.BUILDING_BLOCKS);

        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "white_stained_calcite"), WHITE_STAINED_CALCITE, null, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "light_gray_stained_calcite"), LIGHT_GRAY_STAINED_CALCITE, null, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "gray_stained_calcite"), GRAY_STAINED_CALCITE, null, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "black_stained_calcite"), BLACK_STAINED_CALCITE, null, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "brown_stained_calcite"), BROWN_STAINED_CALCITE, null, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "red_stained_calcite"), RED_STAINED_CALCITE, null, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "orange_stained_calcite"), ORANGE_STAINED_CALCITE, null, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "yellow_stained_calcite"), YELLOW_STAINED_CALCITE, null, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "lime_stained_calcite"), LIME_STAINED_CALCITE, null, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "green_stained_calcite"), GREEN_STAINED_CALCITE, null, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "cyan_stained_calcite"), CYAN_STAINED_CALCITE, null, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "light_blue_stained_calcite"), LIGHT_BLUE_STAINED_CALCITE, null, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "blue_stained_calcite"), BLUE_STAINED_CALCITE, null, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "purple_stained_calcite"), PURPLE_STAINED_CALCITE, null, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "magenta_stained_calcite"), MAGENTA_STAINED_CALCITE, null, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pink_stained_calcite"), PINK_STAINED_CALCITE, null, ItemGroups.COLORED_BLOCKS);

        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "beachgrass_thatch"), BEACHGRASS_THATCH, new CItemSettings().compostingChance(.35f), null, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "trimmed_beachgrass_thatch"), TRIMMED_BEACHGRASS_THATCH, new CItemSettings().compostingChance(.3f), null, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "beachgrass_thatch_stairs"), BEACHGRASS_THATCH_STAIRS, new CItemSettings().compostingChance(.35f), null, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "trimmed_beachgrass_thatch_stairs"), TRIMMED_BEACHGRASS_THATCH_STAIRS, new CItemSettings().compostingChance(.3f), null, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "beachgrass_thatch_slab"), BEACHGRASS_THATCH_SLAB, new CItemSettings().compostingChance(.35f), null, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "trimmed_beachgrass_thatch_slab"), TRIMMED_BEACHGRASS_THATCH_SLAB, new CItemSettings().compostingChance(.3f), null, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "bamboo_thatch"), BAMBOO_THATCH, new CItemSettings(), null, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "trimmed_bamboo_thatch"), TRIMMED_BAMBOO_THATCH, new CItemSettings(), null, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "bamboo_thatch_stairs"), BAMBOO_THATCH_STAIRS, new CItemSettings(), null, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "trimmed_bamboo_thatch_stairs"), TRIMMED_BAMBOO_THATCH_STAIRS, new CItemSettings(), null, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "bamboo_thatch_slab"), BAMBOO_THATCH_SLAB, new CItemSettings(), null, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "trimmed_bamboo_thatch_slab"), TRIMMED_BAMBOO_THATCH_SLAB, new CItemSettings(), null, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "grass_thatch"), GRASS_THATCH);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "trimmed_grass_thatch"), TRIMMED_GRASS_THATCH);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "grass_thatch_stairs"), GRASS_THATCH_STAIRS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "trimmed_grass_thatch_stairs"), TRIMMED_GRASS_THATCH_STAIRS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "grass_thatch_slab"), GRASS_THATCH_SLAB);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "trimmed_grass_thatch_slab"), TRIMMED_GRASS_THATCH_SLAB);

        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "volcanic_sand"), VOLCANIC_SAND, new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, Items.RED_SANDSTONE));
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "grassy_lavarock"), GRASSY_LAVAROCK);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "lavarock"), LAVAROCK, new ItemRegistryHelper.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, null), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, VOLCANIC_SAND));
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "lavarock_stairs"), LAVAROCK_STAIRS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "lavarock_slab"), LAVAROCK_SLAB, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "lavarock_wall"), LAVAROCK_WALL, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "lavarock_bricks"), LAVAROCK_BRICKS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "lavarock_brick_stairs"), LAVAROCK_BRICK_STAIRS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "lavarock_brick_slab"), LAVAROCK_BRICK_SLAB, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "lavarock_brick_wall"), LAVAROCK_BRICK_WALL, ItemGroups.BUILDING_BLOCKS);

        ACAI.register();
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "acai_leaves"), ACAI_LEAVES);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "hanging_acai_leaves"), HANGING_ACAI_LEAVES);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "acai_sapling"), ACAI_SAPLING, new CItemSettings().compostingChance(.3f), MAHOGANY_SAPLING, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_acai_sapling"), POTTED_ACAI_SAPLING);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "acai_berry_clump"), ACAI_BERRY_CLUMP);

        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "elder_prismarine"), ELDER_PRISMARINE, Items.DARK_PRISMARINE_SLAB, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "elder_prismarine_stairs"), ELDER_PRISMARINE_STAIRS, ELDER_PRISMARINE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "elder_prismarine_slab"), ELDER_PRISMARINE_SLAB, ELDER_PRISMARINE_STAIRS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "elder_prismarine_wall"), ELDER_PRISMARINE_WALL, ELDER_PRISMARINE_SLAB, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "elder_prismarine_bricks"), ELDER_PRISMARINE_BRICKS, ELDER_PRISMARINE_WALL, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "elder_prismarine_brick_stairs"), ELDER_PRISMARINE_BRICK_STAIRS, ELDER_PRISMARINE_BRICKS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "elder_prismarine_brick_slab"), ELDER_PRISMARINE_BRICK_SLAB, ELDER_PRISMARINE_BRICK_STAIRS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "elder_prismarine_brick_wall"), ELDER_PRISMARINE_BRICK_WALL, ELDER_PRISMARINE_BRICK_SLAB, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "elder_sea_lantern"), ELDER_SEA_LANTERN, new ItemRegistryHelper.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, Items.SEA_LANTERN), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.FUNCTIONAL, Items.SEA_LANTERN));

        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "antler"), ANTLER, new CItemSettings().compostingChance(.7f), Items.TURTLE_EGG, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "heather"), HEATHER, new CItemSettings().compostingChance(.65f), Items.LILY_OF_THE_VALLEY, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "tall_heather"), TALL_HEATHER, new CItemSettings().compostingChance(.65f), Items.PEONY, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_heather"), POTTED_HEATHER);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "beachgrass"), BEACHGRASS, new CItemSettings().compostingChance(.15f), Items.FERN, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "tall_beachgrass"), TALL_BEACHGRASS, new CItemSettings().compostingChance(.2f), Items.LARGE_FERN, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_beachgrass"), POTTED_BEACHGRASS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "tropical_fern"), TROPICAL_FERN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "large_tropical_fern"), LARGE_TROPICAL_FERN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_tropical_fern"), POTTED_TROPICAL_FERN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "nautilus_fossil"), NAUTILUS_FOSSIL, Items.END_STONE, ItemGroups.NATURAL);

        Spreadable.register(GRASSY_LAVAROCK, LAVAROCK, List.of(
                Spreadable.Spread.of(Blocks.DIRT, Blocks.GRASS_BLOCK),
                Spreadable.Spread.of(LAVAROCK)
        ), (state, serverWorld, pos, random) -> {
            if (serverWorld.getBlockState(pos.up()).isOf(Blocks.SNOW)) {
                serverWorld.setBlockState(pos, state.with(SnowyBlock.SNOWY, true));
            }
        });
    }

    @Override
    public void registerClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                MAHOGANY.door(),
                MAHOGANY.trapdoor(),
                ASPEN.door(),
                ASPEN.trapdoor(),
                ACAI.door(),
                ACAI.trapdoor(),
                MAHOGANY_SAPLING,
                POTTED_MAHOGANY_SAPLING,
                YELLOW_ASPEN_SAPLING,
                POTTED_YELLOW_ASPEN_SAPLING,
                GREEN_ASPEN_SAPLING,
                POTTED_GREEN_ASPEN_SAPLING,
                ACAI_SAPLING,
                POTTED_ACAI_SAPLING,
                BAMBOO_TORCH,
                BAMBOO_WALL_TORCH,
                POTTED_BAMBOO_TORCH,
                SOUL_BAMBOO_TORCH,
                SOUL_BAMBOO_WALL_TORCH,
                POTTED_SOUL_BAMBOO_TORCH,
                BEACHGRASS_THATCH,
                BEACHGRASS_THATCH_SLAB,
                BEACHGRASS_THATCH_STAIRS,
                BAMBOO_THATCH,
                BAMBOO_THATCH_SLAB,
                BAMBOO_THATCH_STAIRS,
                GRASS_THATCH,
                GRASS_THATCH_SLAB,
                GRASS_THATCH_STAIRS,
                ANTLER,
                HEATHER,
                TALL_HEATHER,
                POTTED_HEATHER,
                BEACHGRASS,
                TALL_BEACHGRASS,
                POTTED_BEACHGRASS,
                TROPICAL_FERN,
                LARGE_TROPICAL_FERN,
                POTTED_TROPICAL_FERN
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
                MAHOGANY_LEAVES,
                YELLOW_ASPEN_LEAVES,
                YELLOW_ASPEN_LEAF_PILE,
                GREEN_ASPEN_LEAVES,
                GREEN_ASPEN_LEAF_PILE,
                GRASSY_LAVAROCK,
                ACAI_LEAVES,
                HANGING_ACAI_LEAVES
        );
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(), MAHOGANY_LEAVES, GREEN_ASPEN_LEAVES, GREEN_ASPEN_LEAF_PILE, ACAI_LEAVES, HANGING_ACAI_LEAVES);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, state.get(TallPlantBlock.HALF) == DoubleBlockHalf.UPPER ? pos.down() : pos) : -1, LARGE_TROPICAL_FERN);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5, 1.0), TROPICAL_FERN, POTTED_TROPICAL_FERN);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(.5d, 1d), GRASSY_LAVAROCK, GRASS_THATCH, GRASS_THATCH_SLAB, GRASS_THATCH_STAIRS, TRIMMED_GRASS_THATCH, TRIMMED_GRASS_THATCH_SLAB, TRIMMED_GRASS_THATCH_STAIRS);
    }
}
