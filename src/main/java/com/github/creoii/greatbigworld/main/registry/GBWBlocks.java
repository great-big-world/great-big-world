package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.block.*;
import com.github.creoii.greatbigworld.main.util.DefaultBlockSets;
import com.github.creoii.greatbigworld.main.util.GBWSignTypes;
import com.github.creoii.greatbigworld.main.util.ItemUtil;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.sapling.AcaiSaplingGenerator;
import com.github.creoii.greatbigworld.world.sapling.GreenAspenSaplingGenerator;
import com.github.creoii.greatbigworld.world.sapling.MahoganySaplingGenerator;
import com.github.creoii.greatbigworld.world.sapling.YellowAspenSaplingGenerator;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class GBWBlocks implements Register {
    //region Mahogany Wood
    public static DefaultBlockSets.WoodSet MAHOGANY = DefaultBlockSets.createWoodSet("mahogany", MapColor.TERRACOTTA_BROWN, MapColor.TERRACOTTA_ORANGE);
    public static final Block MAHOGANY_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
    public static final Block MAHOGANY_SAPLING = new SaplingBlock(new MahoganySaplingGenerator(), FabricBlockSettings.copy(Blocks.JUNGLE_SAPLING)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.JUNGLE_SAPLING);
        }
    };
    public static final Block POTTED_MAHOGANY_SAPLING = new FlowerPotBlock(MAHOGANY_SAPLING, FabricBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block MAHOGANY_SIGN = new SignBlock(AbstractBlock.Settings.of(Material.WOOD).noCollision().strength(1f).sounds(BlockSoundGroup.WOOD), GBWSignTypes.MAHOGANY);
    public static final Block MAHOGANY_WALL_SIGN = new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD).noCollision().strength(1f).sounds(BlockSoundGroup.WOOD).dropsLike(MAHOGANY_SIGN), GBWSignTypes.MAHOGANY);
    //endregion
    //region Aspen Wood
    public static DefaultBlockSets.WoodSet ASPEN = DefaultBlockSets.createWoodSet("aspen", MapColor.TERRACOTTA_BROWN, MapColor.TERRACOTTA_ORANGE);
    public static final Block YELLOW_ASPEN_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.PLANT, MapColor.YELLOW).strength(.15f).sounds(BlockSoundGroup.GRASS).nonOpaque()) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.BIRCH_LEAVES);
        }
    };
    public static final Block YELLOW_ASPEN_LEAF_PILE = new LeafPileBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.YELLOW).strength(.1f).sounds(BlockSoundGroup.GRASS).nonOpaque().noCollision()) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.BIRCH_LEAVES);
        }
    };
    public static final Block GREEN_ASPEN_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.PLANT).strength(.15f).sounds(BlockSoundGroup.GRASS).nonOpaque());
    public static final Block GREEN_ASPEN_LEAF_PILE = new LeafPileBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.DARK_GREEN).strength(.1f).sounds(BlockSoundGroup.GRASS).nonOpaque().noCollision());
    public static final Block YELLOW_ASPEN_SAPLING = new SaplingBlock(new YellowAspenSaplingGenerator(), FabricBlockSettings.copy(Blocks.BIRCH_SAPLING)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.BIRCH_SAPLING);
        }
    };
    public static final Block POTTED_YELLOW_ASPEN_SAPLING = new FlowerPotBlock(YELLOW_ASPEN_SAPLING, FabricBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block GREEN_ASPEN_SAPLING = new SaplingBlock(new GreenAspenSaplingGenerator(), FabricBlockSettings.copy(Blocks.BIRCH_SAPLING)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.BIRCH_SAPLING);
        }
    };
    public static final Block POTTED_GREEN_ASPEN_SAPLING = new FlowerPotBlock(GREEN_ASPEN_SAPLING, FabricBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block ASPEN_SIGN = new SignBlock(AbstractBlock.Settings.of(Material.WOOD).noCollision().strength(1f).sounds(BlockSoundGroup.WOOD), GBWSignTypes.ASPEN);
    public static final Block ASPEN_WALL_SIGN = new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD).noCollision().strength(1f).sounds(BlockSoundGroup.WOOD).dropsLike(ASPEN_SIGN), GBWSignTypes.ASPEN);
    //endregion
    //region Bamboo Torches
    public static final Block BAMBOO_TORCH = new BambooTorchBlock(ParticleTypes.FLAME);
    public static final Block BAMBOO_WALL_TORCH = new BambooWallTorchBlock(ParticleTypes.FLAME);
    public static final Block SOUL_BAMBOO_TORCH = new BambooTorchBlock(ParticleTypes.SOUL_FIRE_FLAME);
    public static final Block SOUL_BAMBOO_WALL_TORCH = new BambooWallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME);
    public static final Block POTTED_BAMBOO_TORCH = new FlowerPotBlock(BAMBOO_TORCH, FabricBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block POTTED_SOUL_BAMBOO_TORCH = new FlowerPotBlock(SOUL_BAMBOO_TORCH, FabricBlockSettings.copy(Blocks.FLOWER_POT));
    //endregion
    //region Glimmering Mushrooms
    public static final Block DAYLIGHT_MUSHROOM = new GlimmeringMushroomBlock(GBWParticles.DAY_GLIMMER, new StatusEffectInstance(StatusEffects.GLOWING, 100), 4, 16765440) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.RED_MUSHROOM);
        }
    };
    public static final Block MIDNIGHT_MUSHROOM = new GlimmeringMushroomBlock(GBWParticles.NIGHT_GLIMMER, new StatusEffectInstance(StatusEffects.BLINDNESS, 100), 8, 9558015) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.RED_MUSHROOM);
        }
    };
    public static final Block DARKBLIGHT_MUSHROOM = new GlimmeringMushroomBlock(GBWParticles.DARK_GLIMMER, new StatusEffectInstance(StatusEffects.DARKNESS, 150), 12, 0) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.RED_MUSHROOM);
        }
    };
    //endregion
    //region Cobblestone Bricks
    public static final Block COBBLESTONE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.COBBLESTONE));
    public static final Block COBBLESTONE_BRICK_STAIRS = new StairsBlock(COBBLESTONE_BRICKS.getDefaultState(), FabricBlockSettings.copy(Blocks.COBBLESTONE_STAIRS));
    public static final Block COBBLESTONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.COBBLESTONE_SLAB));
    public static final Block COBBLESTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(Blocks.COBBLESTONE_WALL)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.DEEPSLATE_TILE_WALL);
        }
    };
    public static final Block CHISELED_COBBLESTONE_BRICKS = new Block(FabricBlockSettings.copy(COBBLESTONE_BRICKS));
    public static final Block MOSSY_COBBLESTONE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.MOSSY_COBBLESTONE));
    public static final Block MOSSY_COBBLESTONE_BRICK_STAIRS = new StairsBlock(MOSSY_COBBLESTONE_BRICKS.getDefaultState(), FabricBlockSettings.copy(Blocks.MOSSY_COBBLESTONE_STAIRS));
    public static final Block MOSSY_COBBLESTONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.MOSSY_COBBLESTONE_SLAB));
    public static final Block MOSSY_COBBLESTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(Blocks.MOSSY_COBBLESTONE_WALL)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.DEEPSLATE_TILE_WALL);
        }
    };
    //endregion
    //region Stained Calcite
    public static final Block BROWN_STAINED_CALCITE = new Block(FabricBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_BROWN));
    public static final Block RED_STAINED_CALCITE = new Block(FabricBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_RED));
    public static final Block ORANGE_STAINED_CALCITE = new Block(FabricBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Block YELLOW_STAINED_CALCITE = new Block(FabricBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_YELLOW));
    public static final Block LIME_STAINED_CALCITE = new Block(FabricBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_LIME));
    public static final Block GREEN_STAINED_CALCITE = new Block(FabricBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_GREEN));
    public static final Block CYAN_STAINED_CALCITE = new Block(FabricBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_CYAN));
    public static final Block BLUE_STAINED_CALCITE = new Block(FabricBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_BLACK));
    public static final Block LIGHT_BLUE_STAINED_CALCITE = new Block(FabricBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE));
    public static final Block PINK_STAINED_CALCITE = new Block(FabricBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_PINK));
    public static final Block MAGENTA_STAINED_CALCITE = new Block(FabricBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_MAGENTA));
    public static final Block PURPLE_STAINED_CALCITE = new Block(FabricBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_PURPLE));
    public static final Block BLACK_STAINED_CALCITE = new Block(FabricBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_BLACK));
    public static final Block GRAY_STAINED_CALCITE = new Block(FabricBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_GRAY));
    public static final Block LIGHT_GRAY_STAINED_CALCITE = new Block(FabricBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY));
    public static final Block WHITE_STAINED_CALCITE = new Block(FabricBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_RED));
    //endregion
    //region Thatch
    public static final Block TRIMMED_BEACHGRASS_THATCH = new PillarBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.PALE_YELLOW).strength(.5f).sounds(BlockSoundGroup.GRASS).nonOpaque());
    public static final Block BEACHGRASS_THATCH = new ThatchBlock(FabricBlockSettings.copy(TRIMMED_BEACHGRASS_THATCH), TRIMMED_BEACHGRASS_THATCH);
    public static final Block TRIMMED_BEACHGRASS_THATCH_STAIRS = new StairsBlock(TRIMMED_BEACHGRASS_THATCH.getDefaultState(), FabricBlockSettings.copy(TRIMMED_BEACHGRASS_THATCH));
    public static final Block BEACHGRASS_THATCH_STAIRS = new ThatchStairsBlock(FabricBlockSettings.copy(BEACHGRASS_THATCH), BEACHGRASS_THATCH.getDefaultState(), TRIMMED_BEACHGRASS_THATCH_STAIRS);
    public static final Block TRIMMED_BEACHGRASS_THATCH_SLAB = new SlabBlock(FabricBlockSettings.copy(TRIMMED_BEACHGRASS_THATCH));
    public static final Block BEACHGRASS_THATCH_SLAB = new ThatchSlabBlock(FabricBlockSettings.copy(BEACHGRASS_THATCH), TRIMMED_BEACHGRASS_THATCH_SLAB);
    public static final Block TRIMMED_BAMBOO_THATCH = new PillarBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.GREEN).strength(.5f).sounds(BlockSoundGroup.GRASS).nonOpaque());
    public static final Block BAMBOO_THATCH = new ThatchBlock(FabricBlockSettings.copy(TRIMMED_BAMBOO_THATCH), TRIMMED_BAMBOO_THATCH);
    public static final Block TRIMMED_BAMBOO_THATCH_STAIRS = new StairsBlock(TRIMMED_BAMBOO_THATCH.getDefaultState(), FabricBlockSettings.copy(TRIMMED_BAMBOO_THATCH));
    public static final Block BAMBOO_THATCH_STAIRS = new ThatchStairsBlock(FabricBlockSettings.copy(BAMBOO_THATCH), BAMBOO_THATCH.getDefaultState(), TRIMMED_BAMBOO_THATCH_STAIRS);
    public static final Block TRIMMED_BAMBOO_THATCH_SLAB = new SlabBlock(FabricBlockSettings.copy(TRIMMED_BAMBOO_THATCH));
    public static final Block BAMBOO_THATCH_SLAB = new ThatchSlabBlock(FabricBlockSettings.copy(BAMBOO_THATCH), TRIMMED_BAMBOO_THATCH_SLAB);
    public static final Block TRIMMED_GRASS_THATCH = new PillarBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.PALE_GREEN).strength(.5f).sounds(BlockSoundGroup.GRASS).nonOpaque());
    public static final Block GRASS_THATCH = new ThatchBlock(FabricBlockSettings.copy(TRIMMED_GRASS_THATCH), TRIMMED_GRASS_THATCH);
    public static final Block TRIMMED_GRASS_THATCH_STAIRS = new StairsBlock(TRIMMED_GRASS_THATCH.getDefaultState(), FabricBlockSettings.copy(TRIMMED_GRASS_THATCH));
    public static final Block GRASS_THATCH_STAIRS = new ThatchStairsBlock(FabricBlockSettings.copy(GRASS_THATCH), GRASS_THATCH.getDefaultState(), TRIMMED_GRASS_THATCH_STAIRS);
    public static final Block TRIMMED_GRASS_THATCH_SLAB = new SlabBlock(FabricBlockSettings.copy(TRIMMED_GRASS_THATCH));
    public static final Block GRASS_THATCH_SLAB = new ThatchSlabBlock(FabricBlockSettings.copy(GRASS_THATCH), TRIMMED_GRASS_THATCH_SLAB);
    //endregion
    //region Lavarock
    public static final Block VOLCANIC_SAND = new VolcanicSandBlock();
    public static final Block LAVAROCK = new Block(FabricBlockSettings.copy(Blocks.STONE).mapColor(MapColor.BLACK));
    public static final Block GRASSY_LAVAROCK = new GrassyLavarockBlock();
    public static final Block LAVAROCK_STAIRS = new StairsBlock(LAVAROCK.getDefaultState(), FabricBlockSettings.copy(LAVAROCK));
    public static final Block LAVAROCK_SLAB = new SlabBlock(FabricBlockSettings.copy(LAVAROCK));
    public static final Block LAVAROCK_WALL = new WallBlock(FabricBlockSettings.copy(LAVAROCK)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.DEEPSLATE_TILE_WALL);
        }
    };
    public static final Block LAVAROCK_BRICKS = new Block(FabricBlockSettings.copy(Blocks.STONE_BRICKS).mapColor(MapColor.BLACK));
    public static final Block LAVAROCK_BRICK_STAIRS = new StairsBlock(LAVAROCK_BRICKS.getDefaultState(), FabricBlockSettings.copy(LAVAROCK_BRICKS));
    public static final Block LAVAROCK_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(LAVAROCK_BRICKS));
    public static final Block LAVAROCK_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(LAVAROCK_BRICKS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.DEEPSLATE_TILE_WALL);
        }
    };
    public static final Block CHISELED_LAVAROCK_BRICKS = new Block(FabricBlockSettings.copy(LAVAROCK_BRICKS));
    //endregion
    //region Acai
    public static DefaultBlockSets.WoodSet ACAI = DefaultBlockSets.createWoodSet("acai", MapColor.TERRACOTTA_BROWN, MapColor.TERRACOTTA_YELLOW);
    public static final Block ACAI_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.JUNGLE_LEAVES)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), GBWItems.MAHOGANY_LEAVES);
        }
    };
    public static final Block HANGING_ACAI_LEAVES = new HangingLeavesBlock(FabricBlockSettings.copy(ACAI_LEAVES));
    public static final Block ACAI_SAPLING = new SaplingBlock(new AcaiSaplingGenerator(), FabricBlockSettings.copy(MAHOGANY_SAPLING)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.JUNGLE_SAPLING);
        }
    };
    public static final Block POTTED_ACAI_SAPLING = new FlowerPotBlock(ACAI_SAPLING, FabricBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block ACAI_BERRY_CLUMP = new AcaiBerryClumpBlock(FabricBlockSettings.copy(Blocks.MUSHROOM_STEM).mapColor(MapColor.TERRACOTTA_PURPLE).ticksRandomly());
    //endregion
    //region Elder Prismarine
    public static final Block ELDER_PRISMARINE = new Block(FabricBlockSettings.copy(Blocks.PRISMARINE).mapColor(MapColor.PALE_YELLOW));
    public static final Block ELDER_PRISMARINE_SLAB = new SlabBlock(FabricBlockSettings.copy(ELDER_PRISMARINE));
    public static final Block ELDER_PRISMARINE_STAIRS = new StairsBlock(ELDER_PRISMARINE.getDefaultState(), FabricBlockSettings.copy(ELDER_PRISMARINE));
    public static final Block ELDER_PRISMARINE_WALL = new WallBlock(FabricBlockSettings.copy(ELDER_PRISMARINE)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.DEEPSLATE_TILE_WALL);
        }
    };
    public static final Block ELDER_PRISMARINE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.PRISMARINE_BRICKS).mapColor(MapColor.PALE_YELLOW));
    public static final Block ELDER_PRISMARINE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(ELDER_PRISMARINE_BRICKS));
    public static final Block ELDER_PRISMARINE_BRICK_STAIRS = new StairsBlock(ELDER_PRISMARINE_BRICKS.getDefaultState(), FabricBlockSettings.copy(ELDER_PRISMARINE_BRICKS));
    public static final Block ELDER_PRISMARINE_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(ELDER_PRISMARINE_BRICKS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.DEEPSLATE_TILE_WALL);
        }
    };
    public static final Block ELDER_SEA_LANTERN = new Block(FabricBlockSettings.copy(Blocks.SEA_LANTERN).mapColor(MapColor.PALE_YELLOW).luminance(state -> 14));
    //endregion
    //region Miscellaneous
    public static final Block ANTLER = new AntlerBlock();
    public static final Block TALL_HEATHER = new TallFlowerBlock(FabricBlockSettings.copy(Blocks.ROSE_BUSH)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.PEONY);
        }
    };
    public static final Block HEATHER = new FernBlock(FabricBlockSettings.copy(Blocks.POPPY)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.LILY_OF_THE_VALLEY);
        }
    };
    public static final Block POTTED_HEATHER = new FlowerPotBlock(HEATHER, FabricBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block BEACHGRASS = new BeachgrassBlock();
    public static final Block TALL_BEACHGRASS = new TallBeachgrassBlock();
    public static final Block POTTED_BEACHGRASS = new FlowerPotBlock(BEACHGRASS, FabricBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block TROPICAL_FERN = new FernBlock(FabricBlockSettings.copy(Blocks.FERN));
    public static final Block LARGE_TROPICAL_FERN = new TallPlantBlock(FabricBlockSettings.copy(Blocks.LARGE_FERN));
    public static final Block POTTED_TROPICAL_FERN = new FlowerPotBlock(TROPICAL_FERN, FabricBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block NAUTILUS_FOSSIL = new Block(FabricBlockSettings.copy(Blocks.STONE));
    //endregion

    public void register() {
        MAHOGANY.register();
        registerBlock(new Identifier(NAMESPACE, "mahogany_leaves"), MAHOGANY_LEAVES, new ExtendedBlockSettings(0f, 30, 60, null));
        registerBlock(new Identifier(NAMESPACE, "mahogany_sapling"), MAHOGANY_SAPLING, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroup.DECORATIONS);
        registerBlock(new Identifier(NAMESPACE, "potted_mahogany_sapling"), POTTED_MAHOGANY_SAPLING, null);
        registerBlock(new Identifier(NAMESPACE, "mahogany_sign"), MAHOGANY_SIGN, null);
        registerBlock(new Identifier(NAMESPACE, "mahogany_wall_sign"), MAHOGANY_WALL_SIGN, null);

        ASPEN.register();
        registerBlock(new Identifier(NAMESPACE, "yellow_aspen_leaves"), YELLOW_ASPEN_LEAVES, new ExtendedBlockSettings(0f, 30, 60, null), ItemGroup.DECORATIONS);
        registerBlock(new Identifier(NAMESPACE, "yellow_aspen_leaf_pile"), YELLOW_ASPEN_LEAF_PILE, new ExtendedBlockSettings(0f, 30, 60, null), ItemGroup.DECORATIONS);
        registerBlock(new Identifier(NAMESPACE, "yellow_aspen_sapling"), YELLOW_ASPEN_SAPLING, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroup.DECORATIONS);
        registerBlock(new Identifier(NAMESPACE, "potted_yellow_aspen_sapling"), POTTED_YELLOW_ASPEN_SAPLING, null);
        registerBlock(new Identifier(NAMESPACE, "green_aspen_leaves"), GREEN_ASPEN_LEAVES, new ExtendedBlockSettings(0f, 30, 60, null));
        registerBlock(new Identifier(NAMESPACE, "green_aspen_leaf_pile"), GREEN_ASPEN_LEAF_PILE, new ExtendedBlockSettings(0f, 30, 60, null));
        registerBlock(new Identifier(NAMESPACE, "green_aspen_sapling"), GREEN_ASPEN_SAPLING, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroup.DECORATIONS);
        registerBlock(new Identifier(NAMESPACE, "potted_green_aspen_sapling"), POTTED_GREEN_ASPEN_SAPLING, null);
        registerBlock(new Identifier(NAMESPACE, "aspen_sign"), ASPEN_SIGN, null);
        registerBlock(new Identifier(NAMESPACE, "aspen_wall_sign"), ASPEN_WALL_SIGN, null);

        registerBlock(new Identifier(NAMESPACE, "bamboo_torch"), BAMBOO_TORCH, null);
        registerBlock(new Identifier(NAMESPACE, "bamboo_wall_torch"), BAMBOO_WALL_TORCH, null);
        registerBlock(new Identifier(NAMESPACE, "soul_bamboo_torch"), SOUL_BAMBOO_TORCH, null);
        registerBlock(new Identifier(NAMESPACE, "soul_bamboo_wall_torch"), SOUL_BAMBOO_WALL_TORCH, null);
        registerBlock(new Identifier(NAMESPACE, "potted_bamboo_torch"), POTTED_BAMBOO_TORCH, null);
        registerBlock(new Identifier(NAMESPACE, "potted_soul_bamboo_torch"), POTTED_SOUL_BAMBOO_TORCH, null);

        registerBlock(new Identifier(NAMESPACE, "daylight_mushroom"), DAYLIGHT_MUSHROOM, null, ItemGroup.DECORATIONS);
        registerBlock(new Identifier(NAMESPACE, "midnight_mushroom"), MIDNIGHT_MUSHROOM, null, ItemGroup.DECORATIONS);
        registerBlock(new Identifier(NAMESPACE, "darkblight_mushroom"), DARKBLIGHT_MUSHROOM, null, ItemGroup.DECORATIONS);

        registerBlock(new Identifier(NAMESPACE, "cobblestone_bricks"), COBBLESTONE_BRICKS, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "cobblestone_brick_stairs"), COBBLESTONE_BRICK_STAIRS, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "cobblestone_brick_slab"), COBBLESTONE_BRICK_SLAB, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "cobblestone_brick_wall"), COBBLESTONE_BRICK_WALL, null, ItemGroup.DECORATIONS);
        registerBlock(new Identifier(NAMESPACE, "chiseled_cobblestone_bricks"), CHISELED_COBBLESTONE_BRICKS, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "mossy_cobblestone_bricks"), MOSSY_COBBLESTONE_BRICKS, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "mossy_cobblestone_brick_stairs"), MOSSY_COBBLESTONE_BRICK_STAIRS, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "mossy_cobblestone_brick_slab"), MOSSY_COBBLESTONE_BRICK_SLAB, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "mossy_cobblestone_brick_wall"), MOSSY_COBBLESTONE_BRICK_WALL, null, ItemGroup.DECORATIONS);

        registerBlock(new Identifier(NAMESPACE, "white_stained_calcite"), WHITE_STAINED_CALCITE, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "light_gray_stained_calcite"), LIGHT_GRAY_STAINED_CALCITE, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "gray_stained_calcite"), GRAY_STAINED_CALCITE, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "black_stained_calcite"), BLACK_STAINED_CALCITE, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "brown_stained_calcite"), BROWN_STAINED_CALCITE, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "red_stained_calcite"), RED_STAINED_CALCITE, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "orange_stained_calcite"), ORANGE_STAINED_CALCITE, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "yellow_stained_calcite"), YELLOW_STAINED_CALCITE, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "lime_stained_calcite"), LIME_STAINED_CALCITE, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "green_stained_calcite"), GREEN_STAINED_CALCITE, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "cyan_stained_calcite"), CYAN_STAINED_CALCITE, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "light_blue_stained_calcite"), LIGHT_BLUE_STAINED_CALCITE, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "blue_stained_calcite"), BLUE_STAINED_CALCITE, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "purple_stained_calcite"), PURPLE_STAINED_CALCITE, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "magenta_stained_calcite"), MAGENTA_STAINED_CALCITE, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "pink_stained_calcite"), PINK_STAINED_CALCITE, null, ItemGroup.BUILDING_BLOCKS);

        registerBlock(new Identifier(NAMESPACE, "beachgrass_thatch"), BEACHGRASS_THATCH, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "trimmed_beachgrass_thatch"), TRIMMED_BEACHGRASS_THATCH, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "beachgrass_thatch_stairs"), BEACHGRASS_THATCH_STAIRS, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "trimmed_beachgrass_thatch_stairs"), TRIMMED_BEACHGRASS_THATCH_STAIRS, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "beachgrass_thatch_slab"), BEACHGRASS_THATCH_SLAB, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "trimmed_beachgrass_thatch_slab"), TRIMMED_BEACHGRASS_THATCH_SLAB, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "bamboo_thatch"), BAMBOO_THATCH, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "trimmed_bamboo_thatch"), TRIMMED_BAMBOO_THATCH, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "bamboo_thatch_stairs"), BAMBOO_THATCH_STAIRS, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "trimmed_bamboo_thatch_stairs"), TRIMMED_BAMBOO_THATCH_STAIRS, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "bamboo_thatch_slab"), BAMBOO_THATCH_SLAB, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "trimmed_bamboo_thatch_slab"), TRIMMED_BAMBOO_THATCH_SLAB, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "grass_thatch"), GRASS_THATCH, null);
        registerBlock(new Identifier(NAMESPACE, "trimmed_grass_thatch"), TRIMMED_GRASS_THATCH, null);
        registerBlock(new Identifier(NAMESPACE, "grass_thatch_stairs"), GRASS_THATCH_STAIRS, null);
        registerBlock(new Identifier(NAMESPACE, "trimmed_grass_thatch_stairs"), TRIMMED_GRASS_THATCH_STAIRS, null);
        registerBlock(new Identifier(NAMESPACE, "grass_thatch_slab"), GRASS_THATCH_SLAB, null);
        registerBlock(new Identifier(NAMESPACE, "trimmed_grass_thatch_slab"), TRIMMED_GRASS_THATCH_SLAB, null);

        registerBlock(new Identifier(NAMESPACE, "volcanic_sand"), VOLCANIC_SAND, null, ItemGroup.DECORATIONS);
        registerBlock(new Identifier(NAMESPACE, "grassy_lavarock"), GRASSY_LAVAROCK, null);
        registerBlock(new Identifier(NAMESPACE, "lavarock"), LAVAROCK, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "lavarock_stairs"), LAVAROCK_STAIRS, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "lavarock_slab"), LAVAROCK_SLAB, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "lavarock_wall"), LAVAROCK_WALL, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "lavarock_bricks"), LAVAROCK_BRICKS, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "lavarock_brick_stairs"), LAVAROCK_BRICK_STAIRS, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "lavarock_brick_slab"), LAVAROCK_BRICK_SLAB, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "lavarock_brick_wall"), LAVAROCK_BRICK_WALL, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "chiseled_lavarock_bricks"), CHISELED_LAVAROCK_BRICKS, null, ItemGroup.BUILDING_BLOCKS);

        ACAI.register();
        registerBlock(new Identifier(NAMESPACE, "acai_leaves"), ACAI_LEAVES, new ExtendedBlockSettings(0f, 30, 60, null));
        registerBlock(new Identifier(NAMESPACE, "hanging_acai_leaves"), HANGING_ACAI_LEAVES, new ExtendedBlockSettings(0f, 30, 60, null));
        registerBlock(new Identifier(NAMESPACE, "acai_sapling"), ACAI_SAPLING, null, ItemGroup.DECORATIONS);
        registerBlock(new Identifier(NAMESPACE, "potted_acai_sapling"), POTTED_ACAI_SAPLING, null);
        registerBlock(new Identifier(NAMESPACE, "acai_berry_clump"), ACAI_BERRY_CLUMP, null);

        registerBlock(new Identifier(NAMESPACE, "elder_prismarine"), ELDER_PRISMARINE, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "elder_prismarine_stairs"), ELDER_PRISMARINE_STAIRS, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "elder_prismarine_slab"), ELDER_PRISMARINE_SLAB, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "elder_prismarine_wall"), ELDER_PRISMARINE_WALL, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "elder_prismarine_bricks"), ELDER_PRISMARINE_BRICKS, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "elder_prismarine_brick_stairs"), ELDER_PRISMARINE_BRICK_STAIRS, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "elder_prismarine_brick_slab"), ELDER_PRISMARINE_BRICK_SLAB, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "elder_prismarine_brick_wall"), ELDER_PRISMARINE_BRICK_WALL, null, ItemGroup.BUILDING_BLOCKS);
        registerBlock(new Identifier(NAMESPACE, "elder_sea_lantern"), ELDER_SEA_LANTERN, null, ItemGroup.BUILDING_BLOCKS);

        registerBlock(new Identifier(NAMESPACE, "antler"), ANTLER, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroup.MATERIALS);
        registerBlock(new Identifier(NAMESPACE, "tall_heather"), TALL_HEATHER, new ExtendedBlockSettings(0f, 60, 100, null), ItemGroup.DECORATIONS);
        registerBlock(new Identifier(NAMESPACE, "heather"), HEATHER, new ExtendedBlockSettings(0f, 60, 100, null), ItemGroup.DECORATIONS);
        registerBlock(new Identifier(NAMESPACE, "potted_heather"), POTTED_HEATHER, null);
        registerBlock(new Identifier(NAMESPACE, "beachgrass"), BEACHGRASS, new ExtendedBlockSettings(0f, 75, 120, null), ItemGroup.DECORATIONS);
        registerBlock(new Identifier(NAMESPACE, "tall_beachgrass"), TALL_BEACHGRASS, new ExtendedBlockSettings(0f, 75, 120, null), ItemGroup.DECORATIONS);
        registerBlock(new Identifier(NAMESPACE, "potted_beachgrass"), POTTED_BEACHGRASS, null);
        registerBlock(new Identifier(NAMESPACE, "tropical_fern"), TROPICAL_FERN, new ExtendedBlockSettings(0f, 60, 100, null));
        registerBlock(new Identifier(NAMESPACE, "large_tropical_fern"), LARGE_TROPICAL_FERN, new ExtendedBlockSettings(0f, 60, 100, null));
        registerBlock(new Identifier(NAMESPACE, "potted_tropical_fern"), POTTED_TROPICAL_FERN, null);
        registerBlock(new Identifier(NAMESPACE, "nautilus_fossil"), NAUTILUS_FOSSIL, null, ItemGroup.BUILDING_BLOCKS);
    }

    @Override
    public void registerClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                MAHOGANY.door(),
                MAHOGANY.trapdoor(),
                ASPEN.door(),
                ASPEN.trapdoor(),
                MAHOGANY_SAPLING,
                POTTED_MAHOGANY_SAPLING,
                YELLOW_ASPEN_SAPLING,
                POTTED_YELLOW_ASPEN_SAPLING,
                GREEN_ASPEN_SAPLING,
                POTTED_GREEN_ASPEN_SAPLING,
                BAMBOO_TORCH,
                BAMBOO_WALL_TORCH,
                POTTED_BAMBOO_TORCH,
                SOUL_BAMBOO_TORCH,
                SOUL_BAMBOO_WALL_TORCH,
                POTTED_SOUL_BAMBOO_TORCH,
                HEATHER,
                TALL_HEATHER,
                ANTLER
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
                MAHOGANY_LEAVES,
                YELLOW_ASPEN_LEAVES,
                YELLOW_ASPEN_LEAF_PILE,
                GREEN_ASPEN_LEAVES,
                GREEN_ASPEN_LEAF_PILE
        );
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(), MAHOGANY_LEAVES, GREEN_ASPEN_LEAVES, GREEN_ASPEN_LEAF_PILE);
    }

    public static void registerBlock(Identifier id, Block block, @Nullable ExtendedBlockSettings extension) {
        Registry.register(Registry.BLOCK, id, block);
        if (extension != null) {
            if (extension.compostChance() > 0f)
                CompostingChanceRegistry.INSTANCE.add(block, extension.compostChance());
            if (extension.burnChance() > 0 && extension.spreadChance() > 0)
                FlammableBlockRegistry.getDefaultInstance().add(block, extension.burnChance(), extension.spreadChance());
            if (extension.strippedBlock() != null)
                StrippableBlockRegistry.register(block, extension.strippedBlock());
        }
    }

    public static void registerBlock(Identifier id, Block block, @Nullable ExtendedBlockSettings extension, @Nullable ItemGroup group) {
        registerBlock(id, block, extension);
        if (group != null) {
            GBWItems.registerItem(id, new BlockItem(block, new Item.Settings().group(group)));
        }
    }

    public record ExtendedBlockSettings(float compostChance, int burnChance, int spreadChance, @Nullable Block strippedBlock) { }
}
