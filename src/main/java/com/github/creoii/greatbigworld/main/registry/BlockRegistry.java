package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.block.*;
import com.github.creoii.greatbigworld.block.base.FenceBlock;
import com.github.creoii.greatbigworld.block.base.FenceGateBlock;
import com.github.creoii.greatbigworld.block.base.FlowerBlock;
import com.github.creoii.greatbigworld.block.base.LeavesBlock;
import com.github.creoii.greatbigworld.block.base.OxidizableBlock;
import com.github.creoii.greatbigworld.block.base.PressurePlateBlock;
import com.github.creoii.greatbigworld.block.base.SaplingBlock;
import com.github.creoii.greatbigworld.block.base.SlabBlock;
import com.github.creoii.greatbigworld.block.base.StairsBlock;
import com.github.creoii.greatbigworld.block.base.StoneButtonBlock;
import com.github.creoii.greatbigworld.block.base.WallBlock;
import com.github.creoii.greatbigworld.block.base.WoodenButtonBlock;
import com.github.creoii.greatbigworld.block.base.*;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Foods;
import com.github.creoii.greatbigworld.main.util.ItemUtil;
import com.github.creoii.greatbigworld.world.sapling.MahoganySaplingGenerator;
import com.github.creoii.greatbigworld.world.sapling.PaloVerdeSaplingGenerator;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class BlockRegistry {
    //region Cavier Caves
    public static final Block MALACHITE_BLOCK = new Block(FabricBlockSettings.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.TEAL));
    public static final Block BUDDING_MALACHITE_BLOCK = null;
    public static final Block MALACHITE_CLUSTER = null;
    public static final Block LARGE_MALACHITE_BUD = null;
    public static final Block MEDIUM_MALACHITE_BUD = null;
    public static final Block SMALL_MALACHITE_BUD = null;
    public static final Block TOPAZ_BLOCK = new Block(FabricBlockSettings.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.YELLOW));
    public static final Block BUDDING_TOPAZ_BLOCK = null;
    public static final Block TOPAZ_CLUSTER = null;
    public static final Block LARGE_TOPAZ_BUD = null;
    public static final Block MEDIUM_TOPAZ_BUD = null;
    public static final Block SMALL_TOPAZ_BUD = null;

    public static final Block QUICKSAND = new QuicksandBlock();
    public static final Block ARIDSTONE = new Block(FabricBlockSettings.copy(Blocks.STONE).mapColor(MapColor.OAK_TAN)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.DRIPSTONE_BLOCK);
        }
    };
    public static final Block ARIDSTONE_SLAB = new SlabBlock(FabricBlockSettings.copy(ARIDSTONE));
    public static final Block ARIDSTONE_STAIRS = new StairsBlock(ARIDSTONE.getDefaultState(), FabricBlockSettings.copy(ARIDSTONE));
    public static final Block ARIDSTONE_WALL = new WallBlock(FabricBlockSettings.copy(ARIDSTONE));
    public static final Block ARIDSTONE_BRICKS = new Block(FabricBlockSettings.copy(ARIDSTONE));
    public static final Block ARIDSTONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(ARIDSTONE_BRICKS));
    public static final Block ARIDSTONE_BRICK_STAIRS = new StairsBlock(ARIDSTONE_BRICKS.getDefaultState(), FabricBlockSettings.copy(ARIDSTONE_BRICKS));
    public static final Block ARIDSTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(ARIDSTONE_BRICKS));
    //public static final Block CHISELED_ARIDSTONE_BRICKS = new Block(FabricBlockSettings.copy(ARIDSTONE_BRICKS));
    //public static final Block ARIDSTONE_BRICK_PILLAR = new PillarBlock(FabricBlockSettings.copy(ARIDSTONE_BRICKS));

    public static final Block MOLTEN_MAGMA = new MoltenMagmaBlock();
    public static final Block COOLED_MAGMA = new Block(FabricBlockSettings.copy(Blocks.STONE));
    public static final Block LAVAROCK = new Block(FabricBlockSettings.copy(Blocks.STONE).mapColor(MapColor.TERRACOTTA_BLACK)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.DRIPSTONE_BLOCK);
        }
    };
    public static final Block LAVAROCK_SLAB = new SlabBlock(FabricBlockSettings.copy(LAVAROCK));
    public static final Block LAVAROCK_STAIRS = new StairsBlock(LAVAROCK.getDefaultState(), FabricBlockSettings.copy(LAVAROCK));
    public static final Block LAVAROCK_WALL = new WallBlock(FabricBlockSettings.copy(LAVAROCK));
    public static final Block COBBLED_LAVAROCK = new Block(FabricBlockSettings.copy(LAVAROCK));
    public static final Block COBBLED_LAVAROCK_SLAB = new SlabBlock(FabricBlockSettings.copy(COBBLED_LAVAROCK));
    public static final Block COBBLED_LAVAROCK_STAIRS = new StairsBlock(COBBLED_LAVAROCK.getDefaultState(), FabricBlockSettings.copy(COBBLED_LAVAROCK));
    public static final Block COBBLED_LAVAROCK_WALL = new WallBlock(FabricBlockSettings.copy(COBBLED_LAVAROCK));
    public static final Block LAVAROCK_BRICKS = new Block(FabricBlockSettings.copy(LAVAROCK));
    public static final Block LAVAROCK_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(LAVAROCK_BRICKS));
    public static final Block LAVAROCK_BRICK_STAIRS = new StairsBlock(LAVAROCK_BRICKS.getDefaultState(), FabricBlockSettings.copy(LAVAROCK_BRICKS));
    public static final Block LAVAROCK_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(LAVAROCK_BRICKS));
    //public static final Block CHISELED_LAVAROCK_BRICKS = new Block(FabricBlockSettings.copy(LAVAROCK_BRICKS));
    //public static final Block LAVAROCK_BRICK_PILLAR = new PillarBlock(FabricBlockSettings.copy(LAVAROCK_BRICKS));

    public static final Block ICICLE = null;
    public static final Block GLACITE = new Block(FabricBlockSettings.copy(Blocks.STONE).mapColor(MapColor.TERRACOTTA_BLUE));
    public static final Block GLACITE_SLAB = new SlabBlock(FabricBlockSettings.copy(GLACITE));
    public static final Block GLACITE_STAIRS = new StairsBlock(GLACITE.getDefaultState(), FabricBlockSettings.copy(GLACITE));
    public static final Block GLACITE_WALL = new WallBlock(FabricBlockSettings.copy(GLACITE));
    //public static final Block POLISHED_GLACITE = new Block(FabricBlockSettings.copy(GLACITE));
    //public static final Block POLISHED_GLACITE_SLAB = new SlabBlock(FabricBlockSettings.copy(POLISHED_GLACITE));
    //public static final Block POLISHED_GLACITE_STAIRS = new StairsBlock(POLISHED_GLACITE.getDefaultState(), FabricBlockSettings.copy(POLISHED_GLACITE));
    //public static final Block POLISHED_GLACITE_WALL = new WallBlock(FabricBlockSettings.copy(POLISHED_GLACITE));
    //public static final Block CHISELED_GLACITE_BRICKS = new Block(FabricBlockSettings.copy(GLACITE_BRICKS));
    //public static final Block GLACITE_BRICK_PILLAR = new PillarBlock(FabricBlockSettings.copy(GLACITE_BRICKS));

    public static final Block COW_SKULL = null;
    public static final Block SHEEP_SKULL = null;
    public static final Block CHICKEN_SKULL = null;
    public static final Block PIG_SKULL = null;
    public static final Block HORSE_SKULL = null;
    public static final Block ZOMBIE_SKULL = null;
    public static final Block SPIDER_SKULL = null;
    public static final Block VILLAGER_SKULL = null;
    public static final Block BEAR_SKULL = null;
    public static final Block FOX_SKULL = null;
    public static final Block WOLF_SKULL = null;
    public static final Block CAT_SKULL = null;
    public static final Block HOGLIN_SKULL = null;
    public static final Block PIGLIN_SKULL = null;
    public static final Block GUARDIAN_SKULL = null;
    public static final Block LLAMA_SKULL = null;
    public static final Block PARROT_SKULL = null;
    public static final Block FISH_SKULL = null;
    public static final Block TURTLE_SKULL = null;
    public static final Block STRIDER_SKULL = null;
    public static final Block SILVERFISH_SKULL = null;
    public static final Block SHULKER_SKULL = null;
    public static final Block SQUID_SKULL = null;

    public static final Block GLIMMERING_MUSHROOM = null;
    public static final Block GLIMMERING_MUSHROOM_BLOCK = null;
    public static final Block GLOW_TORCH = null;
    public static final Block WALL_GLOW_TORCH = null;

    public static final Block CHISELED_CUT_COPPER_BLOCK = new OxidizableBlock(Oxidizable.OxidationLevel.UNAFFECTED, FabricBlockSettings.copy(Blocks.CUT_COPPER));
    public static final Block EXPOSED_CHISELED_CUT_COPPER_BLOCK = new OxidizableBlock(Oxidizable.OxidationLevel.EXPOSED, FabricBlockSettings.copy(Blocks.EXPOSED_CUT_COPPER));
    public static final Block WEATHERED_CHISELED_CUT_COPPER_BLOCK = new OxidizableBlock(Oxidizable.OxidationLevel.WEATHERED, FabricBlockSettings.copy(Blocks.WEATHERED_CUT_COPPER));
    public static final Block OXIDIZED_CHISELED_CUT_COPPER_BLOCK = new OxidizableBlock(Oxidizable.OxidationLevel.OXIDIZED, FabricBlockSettings.copy(Blocks.OXIDIZED_CUT_COPPER));
    public static final Block WAXED_CHISELED_CUT_COPPER_BLOCK = new Block(FabricBlockSettings.copy(Blocks.CUT_COPPER)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.WAXED_OXIDIZED_CUT_COPPER_SLAB);
        }
    };
    public static final Block WAXED_EXPOSED_CHISELED_CUT_COPPER_BLOCK = new Block(FabricBlockSettings.copy(Blocks.EXPOSED_CUT_COPPER)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.WAXED_OXIDIZED_CUT_COPPER_SLAB);
        }
    };
    public static final Block WAXED_WEATHERED_CHISELED_CUT_COPPER_BLOCK = new Block(FabricBlockSettings.copy(Blocks.WEATHERED_CUT_COPPER)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.WAXED_OXIDIZED_CUT_COPPER_SLAB);
        }
    };
    public static final Block WAXED_OXIDIZED_CHISELED_CUT_COPPER_BLOCK = new Block(FabricBlockSettings.copy(Blocks.OXIDIZED_CUT_COPPER)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.WAXED_OXIDIZED_CUT_COPPER_SLAB);
        }
    };
    //public static final Block CUT_COPPER_PILLAR = new OxidizablePillarBlock(Oxidizable.OxidationLevel.UNAFFECTED, FabricBlockSettings.copy(Blocks.CUT_COPPER));
    //public static final Block EXPOSED_CUT_COPPER_PILLAR = new OxidizablePillarBlock(Oxidizable.OxidationLevel.EXPOSED, FabricBlockSettings.copy(Blocks.EXPOSED_CUT_COPPER));
    //public static final Block WEATHERED_CUT_COPPER_PILLAR = new OxidizablePillarBlock(Oxidizable.OxidationLevel.WEATHERED, FabricBlockSettings.copy(Blocks.WEATHERED_CUT_COPPER));
    //public static final Block OXIDIZED_CUT_COPPER_PILLAR = new OxidizablePillarBlock(Oxidizable.OxidationLevel.OXIDIZED, FabricBlockSettings.copy(Blocks.OXIDIZED_CUT_COPPER));
    //public static final Block WAXED_CUT_COPPER_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.CUT_COPPER));
    //public static final Block WAXED_EXPOSED_CUT_COPPER_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.EXPOSED_CUT_COPPER));
    //public static final Block WAXED_WEATHERED_CUT_COPPER_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.WEATHERED_CUT_COPPER));
    //public static final Block WAXED_OXIDIZED_CUT_COPPER_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.OXIDIZED_CUT_COPPER));

    public static final Block TUFF_BRICKS = new Block(FabricBlockSettings.copy(Blocks.TUFF));
    public static final Block TUFF_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(TUFF_BRICKS));
    public static final Block TUFF_BRICK_STAIRS = new StairsBlock(TUFF_BRICKS.getDefaultState(), FabricBlockSettings.copy(TUFF_BRICKS));
    public static final Block TUFF_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(TUFF_BRICKS));
    //public static final Block CHISELED_TUFF_BRICKS = new Block(FabricBlockSettings.copy(TUFF_BRICKS));
    //public static final Block TUFF_BRICK_PILLAR = new PillarBlock(FabricBlockSettings.copy(TUFF_BRICKS));

    public static final Block CALCITE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.CALCITE));
    public static final Block CALCITE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(CALCITE_BRICKS));
    public static final Block CALCITE_BRICK_STAIRS = new StairsBlock(CALCITE_BRICKS.getDefaultState(), FabricBlockSettings.copy(CALCITE_BRICKS));
    public static final Block CALCITE_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(CALCITE_BRICKS));
    //public static final Block CHISELED_CALCITE_BRICKS = new Block(FabricBlockSettings.copy(CALCITE_BRICKS));
    //public static final Block CALCITE_BRICK_PILLAR = new PillarBlock(FabricBlockSettings.copy(CALCITE_BRICKS));

    public static final Block DRIPSTONE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.DRIPSTONE_BLOCK));
    public static final Block DRIPSTONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(DRIPSTONE_BRICKS));
    public static final Block DRIPSTONE_BRICK_STAIRS = new StairsBlock(DRIPSTONE_BRICKS.getDefaultState(), FabricBlockSettings.copy(DRIPSTONE_BRICKS));
    public static final Block DRIPSTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(DRIPSTONE_BRICKS));
    //public static final Block CHISELED_DRIPSTONE_BRICKS = new Block(FabricBlockSettings.copy(DRIPSTONE_BRICKS));
    //public static final Block DRIPSTONE_BRICK_PILLAR = new PillarBlock(FabricBlockSettings.copy(DRIPSTONE_BRICKS));

    public static final Block POLISHED_DEEPSLATE_BUTTON = new StoneButtonBlock(FabricBlockSettings.copy(Blocks.POLISHED_DEEPSLATE));
    public static final Block POLISHED_DEEPSLATE_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, FabricBlockSettings.copy(Blocks.POLISHED_DEEPSLATE));
    //endregion

    //region Colormatic
    public static final Block BROWN_CONCRETE = new LayerConcreteBlock(DyeColor.BROWN);
    public static final Block RED_CONCRETE = new LayerConcreteBlock(DyeColor.RED);
    public static final Block ORANGE_CONCRETE = new LayerConcreteBlock(DyeColor.ORANGE);
    public static final Block YELLOW_CONCRETE = new LayerConcreteBlock(DyeColor.YELLOW);
    public static final Block LIME_CONCRETE = new LayerConcreteBlock(DyeColor.LIME);
    public static final Block GREEN_CONCRETE = new LayerConcreteBlock(DyeColor.GREEN);
    public static final Block CYAN_CONCRETE = new LayerConcreteBlock(DyeColor.CYAN);
    public static final Block BLUE_CONCRETE = new LayerConcreteBlock(DyeColor.BLUE);
    public static final Block LIGHT_BLUE_CONCRETE = new LayerConcreteBlock(DyeColor.LIGHT_BLUE);
    public static final Block PINK_CONCRETE = new LayerConcreteBlock(DyeColor.PINK);
    public static final Block MAGENTA_CONCRETE = new LayerConcreteBlock(DyeColor.MAGENTA);
    public static final Block PURPLE_CONCRETE = new LayerConcreteBlock(DyeColor.PURPLE);
    public static final Block BLACK_CONCRETE = new LayerConcreteBlock(DyeColor.BLACK);
    public static final Block GRAY_CONCRETE = new LayerConcreteBlock(DyeColor.GRAY);
    public static final Block LIGHT_GRAY_CONCRETE = new LayerConcreteBlock(DyeColor.LIGHT_GRAY);
    public static final Block WHITE_CONCRETE = new LayerConcreteBlock(DyeColor.WHITE);

    public static final Block BROWN_CONCRETE_POWDER = new LayerConcretePowderBlock(BROWN_CONCRETE, DyeColor.BROWN);
    public static final Block RED_CONCRETE_POWDER = new LayerConcretePowderBlock(RED_CONCRETE, DyeColor.RED);
    public static final Block ORANGE_CONCRETE_POWDER = new LayerConcretePowderBlock(ORANGE_CONCRETE, DyeColor.ORANGE);
    public static final Block YELLOW_CONCRETE_POWDER = new LayerConcretePowderBlock(YELLOW_CONCRETE, DyeColor.YELLOW);
    public static final Block LIME_CONCRETE_POWDER = new LayerConcretePowderBlock(LIME_CONCRETE, DyeColor.LIME);
    public static final Block GREEN_CONCRETE_POWDER = new LayerConcretePowderBlock(GREEN_CONCRETE, DyeColor.GREEN);
    public static final Block CYAN_CONCRETE_POWDER = new LayerConcretePowderBlock(CYAN_CONCRETE, DyeColor.CYAN);
    public static final Block BLUE_CONCRETE_POWDER = new LayerConcretePowderBlock(BLUE_CONCRETE, DyeColor.BLUE);
    public static final Block LIGHT_BLUE_CONCRETE_POWDER = new LayerConcretePowderBlock(LIGHT_BLUE_CONCRETE, DyeColor.LIGHT_BLUE);
    public static final Block PINK_CONCRETE_POWDER = new LayerConcretePowderBlock(PINK_CONCRETE, DyeColor.PINK);
    public static final Block MAGENTA_CONCRETE_POWDER = new LayerConcretePowderBlock(MAGENTA_CONCRETE, DyeColor.MAGENTA);
    public static final Block PURPLE_CONCRETE_POWDER = new LayerConcretePowderBlock(PURPLE_CONCRETE, DyeColor.PURPLE);
    public static final Block BLACK_CONCRETE_POWDER = new LayerConcretePowderBlock(BLACK_CONCRETE, DyeColor.BLACK);
    public static final Block GRAY_CONCRETE_POWDER = new LayerConcretePowderBlock(GRAY_CONCRETE, DyeColor.GRAY);
    public static final Block LIGHT_GRAY_CONCRETE_POWDER = new LayerConcretePowderBlock(LIGHT_GRAY_CONCRETE, DyeColor.LIGHT_GRAY);
    public static final Block WHITE_CONCRETE_POWDER = new LayerConcretePowderBlock(WHITE_CONCRETE, DyeColor.WHITE);

    public static final Block BROWN_QUILTED_WOOL = null;
    public static final Block RED_QUILTED_WOOL = null;
    public static final Block ORANGE_QUILTED_WOOL = null;
    public static final Block YELLOW_QUILTED_WOOL = null;
    public static final Block LIME_QUILTED_WOOL = null;
    public static final Block GREEN_QUILTED_WOOL = null;
    public static final Block CYAN_QUILTED_WOOL = null;
    public static final Block BLUE_QUILTED_WOOL = null;
    public static final Block LIGHT_BLUE_QUILTED_WOOL = null;
    public static final Block PINK_QUILTED_WOOL = null;
    public static final Block MAGENTA_QUILTED_WOOL = null;
    public static final Block PURPLE_QUILTED_WOOL = null;
    public static final Block BLACK_QUILTED_WOOL = null;
    public static final Block GRAY_QUILTED_WOOL = null;
    public static final Block LIGHT_GRAY_QUILTED_WOOL = null;
    public static final Block WHITE_QUILTED_WOOL = null;

    public static final Block BROWN_QUILTED_CARPET = new QuiltedCarpetBlock(DyeColor.BROWN);
    public static final Block RED_QUILTED_CARPET = new QuiltedCarpetBlock(DyeColor.RED);
    public static final Block ORANGE_QUILTED_CARPET = new QuiltedCarpetBlock(DyeColor.ORANGE);
    public static final Block YELLOW_QUILTED_CARPET = new QuiltedCarpetBlock(DyeColor.YELLOW);
    public static final Block LIME_QUILTED_CARPET = new QuiltedCarpetBlock(DyeColor.LIME);
    public static final Block GREEN_QUILTED_CARPET = new QuiltedCarpetBlock(DyeColor.GREEN);
    public static final Block CYAN_QUILTED_CARPET = new QuiltedCarpetBlock(DyeColor.CYAN);
    public static final Block BLUE_QUILTED_CARPET = new QuiltedCarpetBlock(DyeColor.BLUE);
    public static final Block LIGHT_BLUE_QUILTED_CARPET = new QuiltedCarpetBlock(DyeColor.LIGHT_BLUE);
    public static final Block PINK_QUILTED_CARPET = new QuiltedCarpetBlock(DyeColor.PINK);
    public static final Block MAGENTA_QUILTED_CARPET = new QuiltedCarpetBlock(DyeColor.MAGENTA);
    public static final Block PURPLE_QUILTED_CARPET = new QuiltedCarpetBlock(DyeColor.PURPLE);
    public static final Block BLACK_QUILTED_CARPET = new QuiltedCarpetBlock(DyeColor.BLACK);
    public static final Block GRAY_QUILTED_CARPET = new QuiltedCarpetBlock(DyeColor.GRAY);
    public static final Block LIGHT_GRAY_QUILTED_CARPET = new QuiltedCarpetBlock(DyeColor.LIGHT_GRAY);
    public static final Block WHITE_QUILTED_CARPET = new QuiltedCarpetBlock(DyeColor.WHITE);

    //public static final Block BROWN_WALLPAPER = new WallpaperBlock(DyeColor.BROWN);
    //public static final Block RED_WALLPAPER = new WallpaperBlock(DyeColor.RED);
    //public static final Block ORANGE_WALLPAPER = new WallpaperBlock(DyeColor.ORANGE);
    //public static final Block YELLOW_WALLPAPER = new WallpaperBlock(DyeColor.YELLOW);
    //public static final Block LIME_WALLPAPER = new WallpaperBlock(DyeColor.LIME);
    //public static final Block GREEN_WALLPAPER = new WallpaperBlock(DyeColor.GREEN);
    //public static final Block CYAN_WALLPAPER = new WallpaperBlock(DyeColor.CYAN);
    //public static final Block BLUE_WALLPAPER = new WallpaperBlock(DyeColor.BLUE);
    //public static final Block LIGHT_BLUE_WALLPAPER = new WallpaperBlock(DyeColor.LIGHT_BLUE);
    //public static final Block PINK_WALLPAPER = new WallpaperBlock(DyeColor.PINK);
    //public static final Block MAGENTA_WALLPAPER = new WallpaperBlock(DyeColor.MAGENTA);
    //public static final Block PURPLE_WALLPAPER = new WallpaperBlock(DyeColor.PURPLE);
    //public static final Block BLACK_WALLPAPER = new WallpaperBlock(DyeColor.BLACK);
    //public static final Block GRAY_WALLPAPER = new WallpaperBlock(DyeColor.GRAY);
    //public static final Block LIGHT_GRAY_WALLPAPER = new WallpaperBlock(DyeColor.LIGHT_GRAY);
    //public static final Block WHITE_WALLPAPER = new WallpaperBlock(DyeColor.WHITE);

    //public static final Block WISTERIA_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.OFF_WHITE));
    //public static final Block STRIPPED_WISTERIA_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.OFF_WHITE));
    //public static final Block WISTERIA_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.OFF_WHITE));
    //public static final Block WISTERIA_SLAB = new SlabBlock(FabricBlockSettings.copy(WISTERIA_PLANKS));
    //public static final Block WISTERIA_STAIRS = new StairsBlock(WISTERIA_PLANKS.getDefaultState(), FabricBlockSettings.copy(WISTERIA_PLANKS));
    //public static final Block WISTERIA_FENCE = new FenceBlock(FabricBlockSettings.copy(WISTERIA_PLANKS));
    //public static final Block WISTERIA_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(WISTERIA_PLANKS));
    //public static final Block WISTERIA_BUTTON = new WoodenButtonBlock(FabricBlockSettings.copy(WISTERIA_PLANKS));
    //public static final Block WISTERIA_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(WISTERIA_PLANKS));
    //public static final Block WISTERIA_DOOR = new DoorBlock(FabricBlockSettings.copy(WISTERIA_PLANKS));
    //public static final Block WISTERIA_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings.copy(WISTERIA_PLANKS));
    //public static final Block WISTERIA_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));

    //public static final Block REDBUD_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.DULL_RED));
    //public static final Block STRIPPED_REDBUD_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.DULL_RED));
    //public static final Block REDBUD_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.BRIGHT_RED));
    //public static final Block REDBUD_SLAB = new SlabBlock(FabricBlockSettings.copy(REDBUD_PLANKS));
    //public static final Block REDBUD_STAIRS = new StairsBlock(REDBUD_PLANKS.getDefaultState(), FabricBlockSettings.copy(REDBUD_PLANKS));
    //public static final Block REDBUD_FENCE = new FenceBlock(FabricBlockSettings.copy(REDBUD_PLANKS));
    //public static final Block REDBUD_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(REDBUD_PLANKS));
    //public static final Block REDBUD_BUTTON = new WoodenButtonBlock(FabricBlockSettings.copy(REDBUD_PLANKS));
    //public static final Block REDBUD_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(REDBUD_PLANKS));
    //public static final Block REDBUD_DOOR = new DoorBlock(FabricBlockSettings.copy(REDBUD_PLANKS));
    //public static final Block REDBUD_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings.copy(REDBUD_PLANKS));
    //public static final Block REDBUD_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));

    public static final Block KILN = null;
    public static final Block LEATHER_BLOCK = null;

    public static final Block HANGING_OAK_LEAVES = new HangingLeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
    public static final Block HANGING_SPRUCE_LEAVES = new HangingLeavesBlock(FabricBlockSettings.copy(Blocks.SPRUCE_LEAVES));
    public static final Block HANGING_BIRCH_LEAVES = new HangingLeavesBlock(FabricBlockSettings.copy(Blocks.BIRCH_LEAVES));
    public static final Block HANGING_JUNGLE_LEAVES = new HangingLeavesBlock(FabricBlockSettings.copy(Blocks.JUNGLE_LEAVES));
    public static final Block HANGING_DARK_OAK_LEAVES = new HangingLeavesBlock(FabricBlockSettings.copy(Blocks.DARK_OAK_LEAVES));
    public static final Block HANGING_ACACIA_LEAVES = new HangingLeavesBlock(FabricBlockSettings.copy(Blocks.ACACIA_LEAVES));
    public static final Block HANGING_MANGROVE_LEAVES = new HangingLeavesBlock(FabricBlockSettings.copy(Blocks.MANGROVE_LEAVES));
    //public static final Block HANGING_SAKURA_LEAVES = new HangingLeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
    public static final Block HANGING_PALO_VERDE_LEAVES = new HangingLeavesBlock(FabricBlockSettings.copy(Blocks.ACACIA_LEAVES));
    //public static final Block HANGING_SEQUOIA_LEAVES = new HangingLeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
    public static final Block HANGING_MAHOGANY_LEAVES = new HangingLeavesBlock(FabricBlockSettings.copy(Blocks.JUNGLE_LEAVES));
    //public static final Block HANGING_ASPEN_LEAVES = new HangingLeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
    //endregion

    //region Cornucopia
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
    //public static final Block WORM_BLOCK = new PillarBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.DULL_PINK).strength(.4f).sounds(BlockSoundGroup.FUNGUS));

    public static final Block MARIGOLD = new FlowerBlock(StatusEffects.SATURATION, 9, FabricBlockSettings.copy(Blocks.DANDELION));
    public static final Block POTTED_MARIGOLD = new FlowerPotBlock(MARIGOLD, FabricBlockSettings.copy(Blocks.POTTED_DANDELION));

    public static final Block CHARRED_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).strength(.15f));
    public static final Block CHARRED_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).breakInstantly());

    //public static final Block SAKURA_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.DULL_RED));
    //public static final Block STRIPPED_SAKURA_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.DULL_RED));
    //public static final Block SAKURA_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.DULL_RED));
    //public static final Block SAKURA_SLAB = new SlabBlock(FabricBlockSettings.copy(SAKURA_PLANKS));
    //public static final Block SAKURA_STAIRS = new StairsBlock(SAKURA_PLANKS.getDefaultState(), FabricBlockSettings.copy(SAKURA_PLANKS));
    //public static final Block SAKURA_FENCE = new FenceBlock(FabricBlockSettings.copy(SAKURA_PLANKS));
    //public static final Block SAKURA_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(SAKURA_PLANKS));
    //public static final Block SAKURA_BUTTON = new WoodenButtonBlock(FabricBlockSettings.copy(SAKURA_PLANKS));
    //public static final Block SAKURA_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(SAKURA_PLANKS));
    //public static final Block SAKURA_DOOR = new DoorBlock(FabricBlockSettings.copy(SAKURA_PLANKS));
    //public static final Block SAKURA_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings.copy(SAKURA_PLANKS));
    //public static final Block WHITE_SAKURA_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
    //public static final Block PINK_SAKURA_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));

    //public static final Block CHISELED_PRISMARINE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.PRISMARINE_BRICKS));
    //public static final Block PRISMARINE_BRICK_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.PRISMARINE_BRICKS));
    //public static final Block CHISELED_DARK_PRISMARINE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.DARK_PRISMARINE));
    //public static final Block DARK_PRISMARINE_BRICK_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.DARK_PRISMARINE));
    //endregion

    //region Change The World
    public static final Block PALO_VERDE_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.TERRACOTTA_GREEN)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MUDDY_MANGROVE_ROOTS);
        }
    };
    public static final Block STRIPPED_PALO_VERDE_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.TERRACOTTA_GREEN)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.STRIPPED_MANGROVE_LOG);
        }
    };
    public static final Block PALO_VERDE_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.PALE_GREEN)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.WARPED_PLANKS);
        }
    };
    public static final Block PALO_VERDE_SLAB = new SlabBlock(FabricBlockSettings.copy(PALO_VERDE_PLANKS));
    public static final Block PALO_VERDE_STAIRS = new StairsBlock(PALO_VERDE_PLANKS.getDefaultState(), FabricBlockSettings.copy(PALO_VERDE_PLANKS));
    public static final Block PALO_VERDE_FENCE = new FenceBlock(FabricBlockSettings.copy(PALO_VERDE_PLANKS), false);
    public static final Block PALO_VERDE_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(PALO_VERDE_PLANKS));
    //public static final Block PALO_VERDE_BUTTON = new WoodenButtonBlock(FabricBlockSettings.copy(PALO_VERDE_PLANKS));
    //public static final Block PALO_VERDE_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(PALO_VERDE_PLANKS));
    //public static final Block PALO_VERDE_DOOR = new DoorBlock(FabricBlockSettings.copy(PALO_VERDE_PLANKS));
    //public static final Block PALO_VERDE_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings.copy(PALO_VERDE_PLANKS));
    public static final Block PALO_VERDE_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
    public static final Block PALO_VERDE_SAPLING = new SaplingBlock(new PaloVerdeSaplingGenerator(), FabricBlockSettings.copy(Blocks.ACACIA_SAPLING));

    public static final Block SEQUOIA_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.SPRUCE_BROWN)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MUDDY_MANGROVE_ROOTS);
        }
    };
    public static final Block STRIPPED_SEQUOIA_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.SPRUCE_BROWN)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.STRIPPED_MANGROVE_LOG);
        }
    };
    public static final Block SEQUOIA_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.TERRACOTTA_RED)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.WARPED_PLANKS);
        }
    };
    public static final Block SEQUOIA_SLAB = new SlabBlock(FabricBlockSettings.copy(SEQUOIA_PLANKS));
    public static final Block SEQUOIA_STAIRS = new StairsBlock(SEQUOIA_PLANKS.getDefaultState(), FabricBlockSettings.copy(SEQUOIA_PLANKS));
    public static final Block SEQUOIA_FENCE = new FenceBlock(FabricBlockSettings.copy(SEQUOIA_PLANKS), false);
    public static final Block SEQUOIA_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(SEQUOIA_PLANKS));
    //public static final Block SEQUOIA_BUTTON = new WoodenButtonBlock(FabricBlockSettings.copy(SEQUOIA_PLANKS));
    //public static final Block SEQUOIA_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(SEQUOIA_PLANKS));
    //public static final Block SEQUOIA_DOOR = new DoorBlock(FabricBlockSettings.copy(SEQUOIA_PLANKS));
    //public static final Block SEQUOIA_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings.copy(SEQUOIA_PLANKS));
    public static final Block SEQUOIA_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));

    public static final Block MAHOGANY_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.TERRACOTTA_BROWN)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MUDDY_MANGROVE_ROOTS);
        }
    };
    public static final Block STRIPPED_MAHOGANY_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.TERRACOTTA_BROWN)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.STRIPPED_MANGROVE_LOG);
        }
    };
    public static final Block MAHOGANY_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.TERRACOTTA_BROWN)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.WARPED_PLANKS);
        }
    };
    public static final Block MAHOGANY_SLAB = new SlabBlock(FabricBlockSettings.copy(MAHOGANY_PLANKS));
    public static final Block MAHOGANY_STAIRS = new StairsBlock(MAHOGANY_PLANKS.getDefaultState(), FabricBlockSettings.copy(MAHOGANY_PLANKS));
    public static final Block MAHOGANY_FENCE = new FenceBlock(FabricBlockSettings.copy(MAHOGANY_PLANKS), false);
    public static final Block MAHOGANY_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(MAHOGANY_PLANKS));
    //public static final Block MAHOGANY_BUTTON = new WoodenButtonBlock(FabricBlockSettings.copy(MAHOGANY_PLANKS));
    //public static final Block MAHOGANY_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(MAHOGANY_PLANKS));
    //public static final Block MAHOGANY_DOOR = new DoorBlock(FabricBlockSettings.copy(MAHOGANY_PLANKS));
    //public static final Block MAHOGANY_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings.copy(MAHOGANY_PLANKS));
    public static final Block MAHOGANY_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
    public static final Block MAHOGANY_SAPLING = new SaplingBlock(new MahoganySaplingGenerator(), FabricBlockSettings.copy(Blocks.JUNGLE_SAPLING));

    public static final Block PERMAFROST = new Block(FabricBlockSettings.of(Material.SOIL).strength(.65f).sounds(BlockSoundGroup.GRASS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.PODZOL);
        }
    };
    public static final Block GRASSY_STONE = new Block(FabricBlockSettings.copy(Blocks.STONE).mapColor(MapColor.GREEN)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.GRASS_BLOCK);
        }
    };
    public static final Block GRASSY_DEEPSLATE = new Block(FabricBlockSettings.copy(Blocks.DEEPSLATE).mapColor(MapColor.GREEN)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.GRASS_BLOCK);
        }
    };

    public static final Block GRASS_THATCH = new PillarBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.DARK_GREEN).strength(1.5F, 2.0F).sounds(BlockSoundGroup.GRASS));
    public static final Block GRASS_THATCH_SLAB = new DirectionalSlabBlock(FabricBlockSettings.copy(GRASS_THATCH));
    public static final Block GRASS_THATCH_STAIRS = new StairsBlock(GRASS_THATCH.getDefaultState(), FabricBlockSettings.copy(GRASS_THATCH));
    public static final Block BAMBOO_THATCH = new PillarBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.DIRT_BROWN).strength(1.5F, 2.0F).sounds(BlockSoundGroup.GRASS));
    public static final Block BAMBOO_THATCH_SLAB = new DirectionalSlabBlock(FabricBlockSettings.copy(BAMBOO_THATCH));
    public static final Block BAMBOO_THATCH_STAIRS = new StairsBlock(BAMBOO_THATCH.getDefaultState(), FabricBlockSettings.copy(BAMBOO_THATCH));

    public static final Block TERRACOTTA_BRICKS = new Block(FabricBlockSettings.copy(Blocks.TERRACOTTA)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.TERRACOTTA);
        }
    };
    public static final Block BROWN_TERRACOTTA_BRICKS = new TerracottaBricksBlock(MapColor.TERRACOTTA_BROWN);
    public static final Block RED_TERRACOTTA_BRICKS = new TerracottaBricksBlock(MapColor.TERRACOTTA_RED);
    public static final Block ORANGE_TERRACOTTA_BRICKS = new TerracottaBricksBlock(MapColor.TERRACOTTA_ORANGE);
    public static final Block YELLOW_TERRACOTTA_BRICKS = new TerracottaBricksBlock(MapColor.TERRACOTTA_YELLOW);
    public static final Block LIME_TERRACOTTA_BRICKS = new TerracottaBricksBlock(MapColor.TERRACOTTA_LIME);
    public static final Block GREEN_TERRACOTTA_BRICKS = new TerracottaBricksBlock(MapColor.TERRACOTTA_GREEN);
    public static final Block CYAN_TERRACOTTA_BRICKS = new TerracottaBricksBlock(MapColor.TERRACOTTA_CYAN);
    public static final Block BLUE_TERRACOTTA_BRICKS = new TerracottaBricksBlock(MapColor.TERRACOTTA_BLUE);
    public static final Block LIGHT_BLUE_TERRACOTTA_BRICKS = new TerracottaBricksBlock(MapColor.TERRACOTTA_LIGHT_BLUE);
    public static final Block PINK_TERRACOTTA_BRICKS = new TerracottaBricksBlock(MapColor.TERRACOTTA_PINK);
    public static final Block MAGENTA_TERRACOTTA_BRICKS = new TerracottaBricksBlock(MapColor.TERRACOTTA_MAGENTA);
    public static final Block PURPLE_TERRACOTTA_BRICKS = new TerracottaBricksBlock(MapColor.TERRACOTTA_PURPLE);
    public static final Block BLACK_TERRACOTTA_BRICKS = new TerracottaBricksBlock(MapColor.TERRACOTTA_BLACK);
    public static final Block GRAY_TERRACOTTA_BRICKS = new TerracottaBricksBlock(MapColor.TERRACOTTA_GRAY);
    public static final Block LIGHT_GRAY_TERRACOTTA_BRICKS = new TerracottaBricksBlock(MapColor.TERRACOTTA_LIGHT_GRAY);
    public static final Block WHITE_TERRACOTTA_BRICKS = new TerracottaBricksBlock(MapColor.TERRACOTTA_WHITE);
    //endregion

    //region Fire And Ice
    public static final Block HEARTH = null;
    public static final Block TANNING_RACK = null;

    public static final Block ICE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.PACKED_ICE));
    public static final Block ICE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(ICE_BRICKS));
    public static final Block ICE_BRICK_STAIRS = new StairsBlock(ICE_BRICKS.getDefaultState(), FabricBlockSettings.copy(ICE_BRICKS));
    public static final Block ICE_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(ICE_BRICKS));
    //public static final Block CHISELED_ICE_BRICKS = new Block(FabricBlockSettings.copy(ICE_BRICKS));
    //public static final Block ICE_BRICK_PILLAR = new PillarBlock(FabricBlockSettings.copy(ICE_BRICKS));

    public static final Block MAGMA_BRICKS = new Block(FabricBlockSettings.copy(Blocks.MAGMA_BLOCK));
    public static final Block MAGMA_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(MAGMA_BRICKS));
    public static final Block MAGMA_BRICK_STAIRS = new StairsBlock(MAGMA_BRICKS.getDefaultState(), FabricBlockSettings.copy(MAGMA_BRICKS));
    public static final Block MAGMA_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(MAGMA_BRICKS));
    //public static final Block CHISELED_MAGMA_BRICKS = new Block(FabricBlockSettings.copy(MAGMA_BRICKS));
    //public static final Block MAGMA_BRICK_PILLAR = new PillarBlock(FabricBlockSettings.copy(MAGMA_BRICKS));

    public static final Block NETHER_BRICK_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(Blocks.NETHER_BRICKS));
    public static final Block RED_NETHER_BRICK_FENCE = new FenceBlock(FabricBlockSettings.copy(Blocks.RED_NETHER_BRICKS), true);
    public static final Block RED_NETHER_BRICK_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(Blocks.RED_NETHER_BRICKS));
    //endregion

    //region Venture N' Voyage
    //public static final Block CARVED_OAK_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS));
    //public static final Block CARVED_SPRUCE_PLANKS = new Block(FabricBlockSettings.copy(Blocks.SPRUCE_PLANKS));
    //public static final Block CARVED_BIRCH_PLANKS = new Block(FabricBlockSettings.copy(Blocks.BIRCH_PLANKS));
    //public static final Block CARVED_JUNGLE_PLANKS = new Block(FabricBlockSettings.copy(Blocks.JUNGLE_PLANKS));
    //public static final Block CARVED_DARK_OAK_PLANKS = new Block(FabricBlockSettings.copy(Blocks.DARK_OAK_PLANKS));
    //public static final Block CARVED_ACACIA_PLANKS = new Block(FabricBlockSettings.copy(Blocks.ACACIA_PLANKS));
    //public static final Block CARVED_MANGROVE_PLANKS = new Block(FabricBlockSettings.copy(Blocks.ACACIA_PLANKS));
    //public static final Block CARVED_CRIMSON_PLANKS = new Block(FabricBlockSettings.copy(Blocks.CRIMSON_PLANKS));
    //public static final Block CARVED_WARPED_PLANKS = new Block(FabricBlockSettings.copy(Blocks.WARPED_PLANKS));
    //public static final Block CARVED_SAKURA_PLANKS = new Block(FabricBlockSettings.copy(SAKURA_PLANKS));
    //public static final Block CARVED_PALO_VERDE_PLANKS = new Block(FabricBlockSettings.copy(PALO_VERDE_PLANKS));
    //public static final Block CARVED_SEQUOIA_PLANKS = new Block(FabricBlockSettings.copy(SEQUOIA_PLANKS));
    //public static final Block CARVED_MAHOGANY_PLANKS = new Block(FabricBlockSettings.copy(MAHOGANY_PLANKS));

    //public static final Block STRIPPED_MUSHROOM_STEM = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.TERRACOTTA_WHITE));
    //public static final Block MUSHROOM_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.TERRACOTTA_WHITE));
    //public static final Block MUSHROOM_SLAB = new SlabBlock(FabricBlockSettings.copy(MUSHROOM_PLANKS).mapColor(MapColor.TERRACOTTA_WHITE));
    //public static final Block MUSHROOM_STAIRS = new StairsBlock(MUSHROOM_PLANKS.getDefaultState(), FabricBlockSettings.copy(MUSHROOM_PLANKS));
    //public static final Block MUSHROOM_FENCE = new FenceBlock(FabricBlockSettings.copy(MUSHROOM_PLANKS).mapColor(MapColor.TERRACOTTA_WHITE));
    //public static final Block MUSHROOM_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(MUSHROOM_PLANKS));
    //public static final Block MUSHROOM_BUTTON = new WoodenButtonBlock(FabricBlockSettings.copy(MUSHROOM_PLANKS));
    //public static final Block MUSHROOM_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(MUSHROOM_PLANKS));
    //public static final Block MUSHROOM_DOOR = new DoorBlock(FabricBlockSettings.copy(MUSHROOM_PLANKS));
    //public static final Block MUSHROOM_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings.copy(MUSHROOM_PLANKS));
    //public static final Block CARVED_MUSHROOM_PLANKS = new Block(FabricBlockSettings.copy(BlockRegistry.MUSHROOM_PLANKS));

    //public static final Block PUMPKIN_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.ORANGE));
    //public static final Block PUMPKIN_SLAB = new SlabBlock(FabricBlockSettings.copy(PUMPKIN_PLANKS).mapColor(MapColor.ORANGE));
    //public static final Block PUMPKIN_STAIRS = new StairsBlock(PUMPKIN_PLANKS.getDefaultState(), FabricBlockSettings.copy(PUMPKIN_PLANKS).mapColor(MapColor.ORANGE));
    //public static final Block PUMPKIN_FENCE = new FenceBlock(FabricBlockSettings.copy(PUMPKIN_PLANKS));
    //public static final Block PUMPKIN_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(PUMPKIN_PLANKS));
    //public static final Block PUMPKIN_BUTTON = new WoodenButtonBlock(FabricBlockSettings.copy(PUMPKIN_PLANKS));
    //public static final Block PUMPKIN_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(PUMPKIN_PLANKS));
    //public static final Block PUMPKIN_DOOR = new DoorBlock(FabricBlockSettings.copy(PUMPKIN_PLANKS));
    //public static final Block PUMPKIN_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings.copy(PUMPKIN_PLANKS));
    //public static final Block CARVED_PUMPKIN_PLANKS = new Block(FabricBlockSettings.copy(BlockRegistry.PUMPKIN_PLANKS));

    //public static final Block FLOWER_STEM = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.GREEN));
    //public static final Block STRIPPED_FLOWER_STEM = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.GREEN));
    //public static final Block FLOWER_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.GREEN));
    //public static final Block FLOWER_SLAB = new SlabBlock(FabricBlockSettings.copy(FLOWER_PLANKS));
    //public static final Block FLOWER_STAIRS = new StairsBlock(FLOWER_PLANKS.getDefaultState(), FabricBlockSettings.copy(FLOWER_PLANKS));
    //public static final Block FLOWER_FENCE = new FenceBlock(FabricBlockSettings.copy(FLOWER_PLANKS));
    //public static final Block FLOWER_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(FLOWER_PLANKS));
    //public static final Block FLOWER_BUTTON = new WoodenButtonBlock(FabricBlockSettings.copy(FLOWER_PLANKS));
    //public static final Block FLOWER_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(FLOWER_PLANKS));
    //public static final Block FLOWER_DOOR = new DoorBlock(FabricBlockSettings.copy(FLOWER_PLANKS));
    //public static final Block FLOWER_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings.copy(FLOWER_PLANKS));
    //public static final Block CARVED_FLOWER_PLANKS = new Block(FabricBlockSettings.copy(BlockRegistry.FLOWER_PLANKS));
    //public static final Block DANDELION_FLUFF = new CrushableBlock(-1, FabricBlockSettings.of(Material.AGGREGATE).mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.SAND).strength(.3f).suffocates((state, world, pos) -> false));
    public static final Block ROSE_THORN = null;
    //endregion

    //region High Seas
    public static final Block ELDER_PRISMARINE = new Block(FabricBlockSettings.copy(Blocks.PRISMARINE).mapColor(MapColor.PALE_YELLOW)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.PRISMARINE);
        }
    };
    public static final Block ELDER_PRISMARINE_SLAB = new SlabBlock(FabricBlockSettings.copy(ELDER_PRISMARINE));
    public static final Block ELDER_PRISMARINE_STAIRS = new StairsBlock(ELDER_PRISMARINE.getDefaultState(), FabricBlockSettings.copy(ELDER_PRISMARINE));
    public static final Block ELDER_PRISMARINE_WALL = new WallBlock(FabricBlockSettings.copy(ELDER_PRISMARINE));
    public static final Block ELDER_PRISMARINE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.PRISMARINE_BRICKS).mapColor(MapColor.PALE_YELLOW)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.PRISMARINE_BRICKS);
        }
    };
    public static final Block ELDER_PRISMARINE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(ELDER_PRISMARINE_BRICKS));
    public static final Block ELDER_PRISMARINE_BRICK_STAIRS = new StairsBlock(ELDER_PRISMARINE_BRICKS.getDefaultState(), FabricBlockSettings.copy(ELDER_PRISMARINE_BRICKS));
    public static final Block ELDER_PRISMARINE_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(ELDER_PRISMARINE_BRICKS));
    //public static final Block CHISELED_ELDER_PRISMARINE_BRICKS = new Block(FabricBlockSettings.copy(ELDER_PRISMARINE_BRICKS));
    //public static final Block ELDER_PRISMARINE_BRICK_PILLAR = new PillarBlock(FabricBlockSettings.copy(ELDER_PRISMARINE_BRICKS));
    public static final Block ELDER_SEA_LANTERN = new Block(FabricBlockSettings.copy(Blocks.SEA_LANTERN).mapColor(MapColor.PALE_YELLOW).luminance(state -> 14)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.SEA_LANTERN);
        }
    };

    public static final Block MILK_CAULDRON = new MilkCauldronBlock();
    public static final Block CHEESE_CAULDRON = new CheeseCauldronBlock(false);
    public static final Block MOLDY_CHEESE_CAULDRON = new CheeseCauldronBlock(true);
    public static final Block CHEESE_WHEEL = new CakelikeBlock(Foods.CHEESE, FabricBlockSettings.copy(Blocks.CAKE));
    public static final Block MOLDY_CHEESE_WHEEL = new CakelikeBlock(Foods.MOLDY_CHEESE, FabricBlockSettings.copy(Blocks.CAKE));
    public static final Block RED_KELP = null;
    public static final Block JELLYFISH_BLOCK = null;
    public static final Block PHANTOM_GLASS = new PhantomGlassBlock();
    public static final Block NAUTILUS_FOSSIL = new Block(FabricBlockSettings.copy(Blocks.STONE));
    public static final Block ALGAE = new GlowLichenBlock(FabricBlockSettings.copy(Blocks.GLOW_LICHEN).luminance(state -> 0));
    //endregion

    //region Honeycomb
    public static final Block HEATHER = new FlowerBlock(StatusEffects.INSTANT_HEALTH, 1, FabricBlockSettings.copy(Blocks.DANDELION));
    public static final Block ROSE = new FlowerBlock(StatusEffects.INSTANT_DAMAGE, 1, FabricBlockSettings.copy(Blocks.DANDELION));

    public static final Block POLLEN_BOCK = new CrushableBlock(-1, FabricBlockSettings.copy(Blocks.GRASS_BLOCK).sounds(BlockSoundGroup.SAND));
    //endregion

    //region Magic V Melee
    public static final Block SAWMILL = new SawmillBlock();

    public static final Block SOUR_BERRY_BUSH = new BerryBushBlock(Items.SWEET_BERRIES);
    public static final Block BITTER_BERRY_BUSH = new BerryBushBlock(Items.SWEET_BERRIES);
    public static final Block PUNGENT_BERRY_BUSH = new BerryBushBlock(Items.SWEET_BERRIES);

    public static final Block END_STONE_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.END_STONE));
    public static final Block DEEPSLATE_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.DEEPSLATE));
    public static final Block SMOOTH_BASALT_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.SMOOTH_BASALT));
    public static final Block TUFF_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.TUFF));
    public static final Block CALCITE_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.CALCITE));
    public static final Block DRIPSTONE_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.DRIPSTONE_BLOCK));

    public static final Block END_STONE_STAIRS = new StairsBlock(Blocks.END_STONE.getDefaultState(), FabricBlockSettings.copy(Blocks.END_STONE));
    public static final Block DEEPSLATE_STAIRS = new StairsBlock(Blocks.DEEPSLATE.getDefaultState(), FabricBlockSettings.copy(Blocks.DEEPSLATE));
    public static final Block SMOOTH_BASALT_STAIRS = new StairsBlock(Blocks.SMOOTH_BASALT.getDefaultState(), FabricBlockSettings.copy(Blocks.SMOOTH_BASALT));
    public static final Block TUFF_STAIRS = new StairsBlock(Blocks.TUFF.getDefaultState(), FabricBlockSettings.copy(Blocks.TUFF));
    public static final Block CALCITE_STAIRS = new StairsBlock(Blocks.CALCITE.getDefaultState(), FabricBlockSettings.copy(Blocks.CALCITE));
    public static final Block DRIPSTONE_STAIRS = new StairsBlock(Blocks.DRIPSTONE_BLOCK.getDefaultState(), FabricBlockSettings.copy(Blocks.DRIPSTONE_BLOCK));

    public static final Block END_STONE_WALL = new WallBlock(FabricBlockSettings.copy(Blocks.END_STONE));
    public static final Block SMOOTH_BASALT_WALL = new WallBlock(FabricBlockSettings.copy(Blocks.SMOOTH_BASALT));
    public static final Block POLISHED_GRANITE_WALL = new WallBlock(FabricBlockSettings.copy(Blocks.POLISHED_GRANITE));
    public static final Block POLISHED_ANDESITE_WALL = new WallBlock(FabricBlockSettings.copy(Blocks.POLISHED_ANDESITE));
    public static final Block POLISHED_DIORITE_WALL = new WallBlock(FabricBlockSettings.copy(Blocks.POLISHED_DIORITE));
    public static final Block SMOOTH_SANDSTONE_WALL = new WallBlock(FabricBlockSettings.copy(Blocks.SMOOTH_SANDSTONE));
    public static final Block SMOOTH_RED_SANDSTONE_WALL = new WallBlock(FabricBlockSettings.copy(Blocks.SMOOTH_RED_SANDSTONE));
    public static final Block PRISMARINE_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(Blocks.PRISMARINE_BRICKS));
    public static final Block DARK_PRISMARINE_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(Blocks.DARK_PRISMARINE));
    public static final Block TUFF_WALL = new WallBlock(FabricBlockSettings.copy(Blocks.TUFF));
    public static final Block CALCITE_WALL = new WallBlock(FabricBlockSettings.copy(Blocks.CALCITE));
    public static final Block DRIPSTONE_WALL = new WallBlock(FabricBlockSettings.copy(Blocks.DRIPSTONE_BLOCK));

    public static final Block BAMBOO_TORCH = new BambooTorchBlock(ParticleTypes.FLAME);
    public static final Block BAMBOO_WALL_TORCH = new BambooWallTorchBlock(ParticleTypes.FLAME);
    public static final Block SOUL_BAMBOO_TORCH = new BambooTorchBlock(ParticleTypes.SOUL_FIRE_FLAME);
    public static final Block SOUL_BAMBOO_WALL_TORCH = new BambooWallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME);
    public static final Block DRIED_BAMBOO_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.PALE_YELLOW));
    public static final Block DRIED_BAMBOO_SLAB = new SlabBlock(FabricBlockSettings.copy(DRIED_BAMBOO_PLANKS));
    public static final Block DRIED_BAMBOO_STAIRS = new StairsBlock(DRIED_BAMBOO_PLANKS.getDefaultState(), FabricBlockSettings.copy(DRIED_BAMBOO_PLANKS));
    public static final Block DRIED_BAMBOO_FENCE = new FenceBlock(FabricBlockSettings.copy(DRIED_BAMBOO_PLANKS), false);
    public static final Block DRIED_BAMBOO_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(DRIED_BAMBOO_PLANKS));
    public static final Block DRIED_BAMBOO_BUTTON = new WoodenButtonBlock(FabricBlockSettings.copy(DRIED_BAMBOO_PLANKS));
    public static final Block DRIED_BAMBOO_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(DRIED_BAMBOO_PLANKS));
    public static final Block DRIED_BAMBOO_DOOR = new TrapdoorBlock(FabricBlockSettings.copy(DRIED_BAMBOO_PLANKS));
    public static final Block DRIED_BAMBOO_TRAPDOOR = new DoorBlock(FabricBlockSettings.copy(DRIED_BAMBOO_PLANKS));

    public static final Block CORVUS = new Block(FabricBlockSettings.copy(Blocks.STONE).mapColor(MapColor.DEEPSLATE_GRAY));
    public static final Block CORVUS_SLAB = new SlabBlock(FabricBlockSettings.copy(CORVUS));
    public static final Block CORVUS_STAIRS = new StairsBlock(CORVUS.getDefaultState(), FabricBlockSettings.copy(CORVUS));
    public static final Block CORVUS_WALL = new WallBlock(FabricBlockSettings.copy(CORVUS));
    public static final Block CORVUS_BRICKS = new Block(FabricBlockSettings.copy(CORVUS));
    public static final Block CORVUS_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(CORVUS_BRICKS));
    public static final Block CORVUS_BRICK_STAIRS = new StairsBlock(CORVUS_BRICKS.getDefaultState(), FabricBlockSettings.copy(CORVUS_BRICKS));
    public static final Block CORVUS_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(CORVUS_BRICKS));
    //public static final Block CHISELED_CORVUS_BRICKS = new Block(FabricBlockSettings.copy(CORVUS_BRICKS));
    //public static final Block CORVUS_BRICK_PILLAR = new PillarBlock(FabricBlockSettings.copy(CORVUS_BRICKS));
    //endregion

    //region Redstone Refurbished
    public static final Block RECEPTOR = new ReceptorBlock();
    public static final Block SLIME = new LayerSlimeBlock();
    //endregion

    //region Twisted Nether
    public static final Block TWISTED_WART_BLOCK = new Block(FabricBlockSettings.copy(Blocks.NETHER_WART_BLOCK).mapColor(MapColor.PURPLE)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.WARPED_WART_BLOCK);
        }
    };
    public static final Block TWISTED_NYLIUM = new DirectionalNyliumBlock(FabricBlockSettings.copy(Blocks.WARPED_NYLIUM).mapColor(MapColor.PALE_PURPLE));

    //public static final Block TWISTED_ROOTS = new RootsBlock(FabricBlockSettings.copy(Blocks.WARPED_ROOTS));
    public static final Block TWISTED_FUNGUS = new DirectionalFungusBlock(FabricBlockSettings.copy(Blocks.WARPED_FUNGUS), () -> ConfiguredFeatureRegistry.TWISTED_FUNGUS);
    public static final Block TWISTED_STEM = new PillarBlock(FabricBlockSettings.copy(Blocks.CRIMSON_STEM).mapColor(MapColor.DARK_DULL_PINK)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.WARPED_STEM);
        }
    };
    public static final Block STRIPPED_TWISTED_STEM = new PillarBlock(FabricBlockSettings.copy(TWISTED_STEM).mapColor(MapColor.PURPLE)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.STRIPPED_WARPED_STEM);
        }
    };
    public static final Block TWISTED_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.TERRACOTTA_PURPLE)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.WARPED_PLANKS);
        }
    };
    public static final Block TWISTED_SLAB = new SlabBlock(FabricBlockSettings.copy(TWISTED_PLANKS));
    public static final Block TWISTED_STAIRS = new StairsBlock(TWISTED_PLANKS.getDefaultState(), FabricBlockSettings.copy(TWISTED_PLANKS));
    public static final Block TWISTED_FENCE = new FenceBlock(FabricBlockSettings.copy(TWISTED_PLANKS), false);
    public static final Block TWISTED_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(TWISTED_PLANKS));
    //public static final Block TWISTED_BUTTON = new WoodenButtonBlock(FabricBlockSettings.copy(TWISTED_PLANKS));
    //public static final Block TWISTED_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(TWISTED_PLANKS));
    //public static final Block TWISTED_DOOR = new DoorBlock(FabricBlockSettings.copy(TWISTED_PLANKS));
    //public static final Block TWISTED_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings.copy(TWISTED_PLANKS));
    //public static final Block CARVED_TWISTED_PLANKS = new Block(FabricBlockSettings.copy(BlockRegistry.TWISTED_PLANKS));

    public static final Block WARPED_SHROOMLIGHT = new Block(FabricBlockSettings.copy(Blocks.SHROOMLIGHT).mapColor(MapColor.BRIGHT_TEAL).luminance(state -> 14)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.SHROOMLIGHT);
        }
    };
    public static final Block TWISTED_SHROOMLIGHT = new Block(FabricBlockSettings.copy(Blocks.SHROOMLIGHT).mapColor(MapColor.BRIGHT_TEAL).luminance(state -> 14)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.SHROOMLIGHT);
        }
    };

    public static final Block BLUE_NETHER_BRICKS = new Block(FabricBlockSettings.copy(Blocks.NETHER_BRICKS).mapColor(MapColor.TEAL)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.RED_NETHER_BRICKS);
        }
    };
    public static final Block BLUE_NETHER_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(BLUE_NETHER_BRICKS));
    public static final Block BLUE_NETHER_BRICK_STAIRS = new StairsBlock(BLUE_NETHER_BRICKS.getDefaultState(), FabricBlockSettings.copy(BLUE_NETHER_BRICKS));
    public static final Block BLUE_NETHER_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(BLUE_NETHER_BRICKS));
    public static final Block BLUE_NETHER_BRICK_FENCE = new FenceBlock(FabricBlockSettings.copy(BLUE_NETHER_BRICKS), true);
    public static final Block BLUE_NETHER_BRICK_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(BLUE_NETHER_BRICKS));
    //public static final Block CHISELED_BLUE_NETHER_BRICK = new Block(FabricBlockSettings.copy(BLUE_NETHER_BRICKS));
    public static final Block PURPLE_NETHER_BRICKS = new Block(FabricBlockSettings.copy(Blocks.NETHER_BRICKS).mapColor(MapColor.TERRACOTTA_PURPLE)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.RED_NETHER_BRICKS);
        }
    };
    public static final Block PURPLE_NETHER_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(PURPLE_NETHER_BRICKS));
    public static final Block PURPLE_NETHER_BRICK_STAIRS = new StairsBlock(PURPLE_NETHER_BRICKS.getDefaultState(), FabricBlockSettings.copy(PURPLE_NETHER_BRICKS));
    public static final Block PURPLE_NETHER_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(PURPLE_NETHER_BRICKS));
    public static final Block PURPLE_NETHER_BRICK_FENCE = new FenceBlock(FabricBlockSettings.copy(PURPLE_NETHER_BRICKS), true);
    public static final Block PURPLE_NETHER_BRICK_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(PURPLE_NETHER_BRICKS));
    //public static final Block CHISELED_PURPLE_NETHER_BRICK = new Block(FabricBlockSettings.copy(PURPLE_NETHER_BRICKS));

    public static final Block QUARTZ_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.QUARTZ_BRICKS));
    public static final Block QUARTZ_BRICK_STAIRS = new StairsBlock(Blocks.QUARTZ_BRICKS.getDefaultState(), FabricBlockSettings.copy(Blocks.QUARTZ_BRICKS));
    public static final Block QUARTZ_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(Blocks.QUARTZ_BRICKS));

    public static final Block COBBLED_BASALT = new Block(FabricBlockSettings.copy(Blocks.BASALT)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.SMOOTH_BASALT);
        }
    };
    public static final Block COBBLED_BASALT_SLAB = new SlabBlock(FabricBlockSettings.copy(COBBLED_BASALT));
    public static final Block COBBLED_BASALT_STAIRS = new StairsBlock(COBBLED_BASALT.getDefaultState(), FabricBlockSettings.copy(COBBLED_BASALT));
    public static final Block COBBLED_BASALT_WALL = new WallBlock(FabricBlockSettings.copy(COBBLED_BASALT));
    public static final Block BASALT_BRICKS = new Block(FabricBlockSettings.copy(COBBLED_BASALT));
    public static final Block BASALT_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(BASALT_BRICKS));
    public static final Block BASALT_BRICK_STAIRS = new StairsBlock(BASALT_BRICKS.getDefaultState(), FabricBlockSettings.copy(BASALT_BRICKS));
    public static final Block BASALT_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(BASALT_BRICKS));
    //public static final Block CHISELED_BASALT_BRICKS = new Block(FabricBlockSettings.copy(BASALT_BRICKS));
    //public static final Block BASALT_BRICK_PILLAR = new PillarBlock(FabricBlockSettings.copy(BASALT_BRICKS));

    public static final Block SOULSHALE = new Block(FabricBlockSettings.copy(Blocks.SANDSTONE).mapColor(MapColor.TERRACOTTA_BROWN));
    public static final Block SOULSHALE_SLAB = new SlabBlock(FabricBlockSettings.copy(SOULSHALE));
    public static final Block SOULSHALE_STAIRS = new StairsBlock(SOULSHALE.getDefaultState(), FabricBlockSettings.copy(SOULSHALE));
    public static final Block SOULSHALE_WALL = new WallBlock(FabricBlockSettings.copy(SOULSHALE));
    //public static final Block CHISELED_SOULSHALE = new Block(FabricBlockSettings.copy(SOULSHALE));
    public static final Block WHISPERING_SOULSHALE = new Block(FabricBlockSettings.copy(SOULSHALE).luminance(state -> 3));

    public static final Block SULFUR_BLOCK = null;
    public static final Block GHAST_SCREAMER = null;
    public static final Block SOUL_URN = null;
    //endregion

    //region Wonders Of The Wild
    //public static final Block RED_LOTUS = new LotusBlock(StatusEffects.ABSORPTION, 8);
    //public static final Block BLUE_LOTUS = new LotusBlock(StatusEffects.ABSORPTION, 8);
    //public static final Block YELLOW_LOTUS = new LotusBlock(StatusEffects.ABSORPTION, 8);
    //public static final Block ORANGE_LOTUS = new LotusBlock(StatusEffects.ABSORPTION, 8);
    //public static final Block PURPLE_LOTUS = new LotusBlock(StatusEffects.ABSORPTION, 8);
    //public static final Block PINK_LOTUS = new LotusBlock(StatusEffects.ABSORPTION, 8);
    //public static final Block BLACK_LOTUS = new LotusBlock(StatusEffects.INSTANT_DAMAGE, 1);
    //public static final Block WHITE_LOTUS = new LotusBlock(StatusEffects.HEALTH_BOOST, 1);

    public static final Block HOLLOW_OAK_LOG = new HollowLogBlock(FabricBlockSettings.copy(Blocks.OAK_LOG));
    public static final Block HOLLOW_SPRUCE_LOG = new HollowLogBlock(FabricBlockSettings.copy(Blocks.SPRUCE_LOG));
    public static final Block HOLLOW_BIRCH_LOG = new HollowLogBlock(FabricBlockSettings.copy(Blocks.BIRCH_LOG));
    public static final Block HOLLOW_JUNGLE_LOG = new HollowLogBlock(FabricBlockSettings.copy(Blocks.JUNGLE_LOG));
    public static final Block HOLLOW_DARK_OAK_LOG = new HollowLogBlock(FabricBlockSettings.copy(Blocks.DARK_OAK_LOG));
    public static final Block HOLLOW_ACACIA_LOG = new HollowLogBlock(FabricBlockSettings.copy(Blocks.ACACIA_LOG));
    public static final Block HOLLOW_MANGROVE_LOG = new HollowLogBlock(FabricBlockSettings.copy(Blocks.MANGROVE_LOG));
    //public static final Block HOLLOW_SAKURA_LOG = new HollowLogBlock(FabricBlockSettings.copy(SAKURA_LOG));
    //public static final Block HOLLOW_SEQUOIA_LOG = new HollowLogBlock(FabricBlockSettings.copy(SEQUOIA_LOG));
    public static final Block HOLLOW_MAHOGANY_LOG = new HollowLogBlock(FabricBlockSettings.copy(MAHOGANY_LOG));
    public static final Block HOLLOW_PALO_VERDE_LOG = new HollowLogBlock(FabricBlockSettings.copy(PALO_VERDE_LOG));
    public static final Block STRIPPED_HOLLOW_OAK_LOG = new HollowLogBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG));
    public static final Block STRIPPED_HOLLOW_SPRUCE_LOG = new HollowLogBlock(FabricBlockSettings.copy(Blocks.STRIPPED_SPRUCE_LOG));
    public static final Block STRIPPED_HOLLOW_BIRCH_LOG = new HollowLogBlock(FabricBlockSettings.copy(Blocks.STRIPPED_BIRCH_LOG));
    public static final Block STRIPPED_HOLLOW_JUNGLE_LOG = new HollowLogBlock(FabricBlockSettings.copy(Blocks.STRIPPED_JUNGLE_LOG));
    public static final Block STRIPPED_HOLLOW_DARK_OAK_LOG = new HollowLogBlock(FabricBlockSettings.copy(Blocks.STRIPPED_DARK_OAK_LOG));
    public static final Block STRIPPED_HOLLOW_ACACIA_LOG = new HollowLogBlock(FabricBlockSettings.copy(Blocks.STRIPPED_ACACIA_LOG));
    public static final Block STRIPPED_HOLLOW_MANGROVE_LOG = new HollowLogBlock(FabricBlockSettings.copy(Blocks.STRIPPED_MANGROVE_LOG));
    public static final Block STRIPPED_HOLLOW_MAHOGANY_LOG = new HollowLogBlock(FabricBlockSettings.copy(STRIPPED_MAHOGANY_LOG));
    public static final Block STRIPPED_HOLLOW_PALO_VERDE_LOG = new HollowLogBlock(FabricBlockSettings.copy(STRIPPED_PALO_VERDE_LOG));

    public static final Block COLD_WILDFLOWER = null;
    public static final Block TEMPERATE_WILDFLOWER = null;
    public static final Block WARM_WILDFLOWER = null;

    public static final Block RED_SHELF_MUSHROOM = null;
    public static final Block BROWN_SHELF_MUSHROOM = null;

    public static final Block BEACHGRASS = null;
    public static final Block CATTAIL = null;

    //public static final Block ASPEN_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.PALE_YELLOW));
    //public static final Block STRIPPED_ASPEN_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.PALE_YELLOW));
    //public static final Block ASPEN_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.PALE_YELLOW));
    //public static final Block ASPEN_SLAB = new SlabBlock(FabricBlockSettings.copy(ASPEN_PLANKS));
    //public static final Block ASPEN_STAIRS = new StairsBlock(ASPEN_PLANKS.getDefaultState(), FabricBlockSettings.copy(ASPEN_PLANKS));
    //public static final Block ASPEN_FENCE = new FenceBlock(FabricBlockSettings.copy(ASPEN_PLANKS));
    //public static final Block ASPEN_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(ASPEN_PLANKS));
    //public static final Block ASPEN_BUTTON = new WoodenButtonBlock(FabricBlockSettings.copy(ASPEN_PLANKS));
    //public static final Block ASPEN_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(ASPEN_PLANKS));
    //public static final Block ASPEN_DOOR = new DoorBlock(FabricBlockSettings.copy(ASPEN_PLANKS));
    //public static final Block ASPEN_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings.copy(ASPEN_PLANKS));
    //public static final Block ASPEN_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
    //public static final Block CARVED_ASPEN_PLANKS = new Block(FabricBlockSettings.copy(BlockRegistry.ASPEN_PLANKS));
    //public static final Block HOLLOW_ASPEN_LOG = new HollowLogBlock(FabricBlockSettings.copy(BlockRegistry.ASPEN_LOG));

    //public static final Block STRIPPED_OAK_BRANCH = new PostBlock(FabricBlockSettings.copy(Blocks.OAK_LOG));
    //public static final Block OAK_BRANCH = new BranchBlock(STRIPPED_OAK_BRANCH, FabricBlockSettings.copy(Blocks.OAK_LOG));
    //public static final Block STRIPPED_SPRUCE_BRANCH = new PostBlock(FabricBlockSettings.copy(Blocks.SPRUCE_LOG));
    //public static final Block SPRUCE_BRANCH = new BranchBlock(STRIPPED_SPRUCE_BRANCH, FabricBlockSettings.copy(ASPEN_LOG));
    //public static final Block STRIPPED_BIRCH_BRANCH = new PostBlock(FabricBlockSettings.copy(Blocks.BIRCH_LOG));
    //public static final Block BIRCH_BRANCH = new BranchBlock(STRIPPED_BIRCH_BRANCH, FabricBlockSettings.copy(ASPEN_LOG));
    //public static final Block STRIPPED_JUNGLE_BRANCH = new PostBlock(FabricBlockSettings.copy(Blocks.JUNGLE_LOG));
    //public static final Block JUNGLE_BRANCH = new BranchBlock(STRIPPED_JUNGLE_BRANCH, FabricBlockSettings.copy(ASPEN_LOG));
    //public static final Block STRIPPED_DARK_OAK_BRANCH = new PostBlock(FabricBlockSettings.copy(Blocks.DARK_OAK_LOG));
    //public static final Block DARK_OAK_BRANCH = new BranchBlock(STRIPPED_DARK_OAK_BRANCH, FabricBlockSettings.copy(ASPEN_LOG));
    //public static final Block STRIPPED_ACACIA_BRANCH = new PostBlock(FabricBlockSettings.copy(Blocks.ACACIA_LOG));
    //public static final Block ACACIA_BRANCH = new BranchBlock(STRIPPED_ACACIA_BRANCH, FabricBlockSettings.copy(ASPEN_LOG));
    //public static final Block STRIPPED_SAKURA_BRANCH = new PostBlock(FabricBlockSettings.copy(SAKURA_LOG));
    //public static final Block SAKURA_BRANCH = new BranchBlock(STRIPPED_SAKURA_BRANCH, FabricBlockSettings.copy(ASPEN_LOG));
    //public static final Block STRIPPED_PALO_VERDE_BRANCH = new PostBlock(FabricBlockSettings.copy(PALO_VERDE_LOG));
    //public static final Block PALO_VERDE_BRANCH = new BranchBlock(STRIPPED_PALO_VERDE_BRANCH, FabricBlockSettings.copy(ASPEN_LOG));
    //public static final Block STRIPPED_SEQUOIA_BRANCH = new PostBlock(FabricBlockSettings.copy(SEQUOIA_LOG));
    //public static final Block SEQUOIA_BRANCH = new BranchBlock(STRIPPED_SEQUOIA_BRANCH, FabricBlockSettings.copy(ASPEN_LOG));
    //public static final Block STRIPPED_MAHOGANY_BRANCH = new PostBlock(FabricBlockSettings.copy(MAHOGANY_LOG));
    //public static final Block MAHOGANY_BRANCH = new BranchBlock(STRIPPED_MAHOGANY_BRANCH, FabricBlockSettings.copy(ASPEN_LOG));
    //public static final Block STRIPPED_ASPEN_BRANCH = new PostBlock(FabricBlockSettings.copy(ASPEN_LOG));
    //public static final Block ASPEN_BRANCH = new BranchBlock(STRIPPED_ASPEN_BRANCH, FabricBlockSettings.copy(ASPEN_LOG));
    //endregion

    //region Seecret
    public static final Block GIANT_POTATO = new Block(FabricBlockSettings.copy(Blocks.MELON));
    public static final Block GIANT_POISONOUS_POTATO = new Block(FabricBlockSettings.copy(GIANT_POTATO));
    public static final Block GIANT_CARROT = new PillarBlock(FabricBlockSettings.copy(Blocks.MELON));
    public static final Block GIANT_BEETROOT = new Block(FabricBlockSettings.copy(Blocks.MELON));
    public static final Block GIANT_HEARTBEET = new Block(FabricBlockSettings.copy(GIANT_BEETROOT));

    //public static final Block WILD_WHEAT = new CropBlock(FabricBlockSettings.copy(Blocks.WHEAT));
    //public static final Block WILD_POTATOES = new CropBlock(FabricBlockSettings.copy(Blocks.POTATOES));
    //public static final Block WILD_CARROTS = new CropBlock(FabricBlockSettings.copy(Blocks.CARROTS));
    //public static final Block WILD_BEETROOTS = new CropBlock(FabricBlockSettings.copy(Blocks.BEETROOTS));

    public static final Block RED_ROCK = new Block(FabricBlockSettings.copy(Blocks.STONE).mapColor(MapColor.TERRACOTTA_RED));
    //endregion

    public static void register() {
        if (GreatBigWorld.isLoaded("cavier_caves") || GreatBigWorld.inDev()) {
            registerBlock(new Identifier(NAMESPACE, "malachite_block"), MALACHITE_BLOCK, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "topaz_block"), TOPAZ_BLOCK, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "quicksand"), QUICKSAND, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "aridstone"), ARIDSTONE, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "aridstone_slab"), ARIDSTONE_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "aridstone_stairs"), ARIDSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "aridstone_wall"), ARIDSTONE_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "aridstone_bricks"), ARIDSTONE_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "aridstone_brick_slab"), ARIDSTONE_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "aridstone_brick_stairs"), ARIDSTONE_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "aridstone_brick_wall"), ARIDSTONE_BRICK_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "molten_magma"), MOLTEN_MAGMA, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "cooled_magma"), COOLED_MAGMA, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "lavarock"), LAVAROCK, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "lavarock_slab"), LAVAROCK_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "lavarock_stairs"), LAVAROCK_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "lavarock_wall"), LAVAROCK_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "cobbled_lavarock"), COBBLED_LAVAROCK, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "cobbled_lavarock_slab"), COBBLED_LAVAROCK_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "cobbled_lavarock_stairs"), COBBLED_LAVAROCK_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "cobbled_lavarock_wall"), COBBLED_LAVAROCK_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "lavarock_bricks"), LAVAROCK_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "lavarock_brick_slab"), LAVAROCK_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "lavarock_brick_stairs"), LAVAROCK_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "lavarock_brick_wall"), LAVAROCK_BRICK_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "glacite"), GLACITE, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "glacite_slab"), GLACITE_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "glacite_stairs"), GLACITE_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "glacite_wall"), GLACITE_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "chiseled_cut_copper_block"), CHISELED_CUT_COPPER_BLOCK, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "exposed_chiseled_cut_copper_block"), EXPOSED_CHISELED_CUT_COPPER_BLOCK, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "weathered_chiseled_cut_copper_block"), WEATHERED_CHISELED_CUT_COPPER_BLOCK, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "oxidized_chiseled_cut_copper_block"), OXIDIZED_CHISELED_CUT_COPPER_BLOCK, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "waxed_chiseled_cut_copper_block"), WAXED_CHISELED_CUT_COPPER_BLOCK, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "waxed_exposed_chiseled_cut_copper_block"), WAXED_EXPOSED_CHISELED_CUT_COPPER_BLOCK, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "waxed_weathered_chiseled_cut_copper_block"), WAXED_WEATHERED_CHISELED_CUT_COPPER_BLOCK, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "waxed_oxidized_chiseled_cut_copper_block"), WAXED_OXIDIZED_CHISELED_CUT_COPPER_BLOCK, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "tuff_bricks"), TUFF_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "tuff_brick_slab"), TUFF_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "tuff_brick_stairs"), TUFF_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "tuff_brick_wall"), TUFF_BRICK_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "calcite_bricks"), CALCITE_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "calcite_brick_slab"), CALCITE_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "calcite_brick_stairs"), CALCITE_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "calcite_brick_wall"), CALCITE_BRICK_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "dripstone_bricks"), DRIPSTONE_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "dripstone_brick_slab"), DRIPSTONE_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "dripstone_brick_stairs"), DRIPSTONE_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "dripstone_brick_wall"), DRIPSTONE_BRICK_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "polished_deepslate_button"), POLISHED_DEEPSLATE_BUTTON, ItemGroup.REDSTONE);
            registerBlock(new Identifier(NAMESPACE, "polished_deepslate_pressure_plate"), POLISHED_DEEPSLATE_PRESSURE_PLATE, ItemGroup.REDSTONE);
        }

        if (GreatBigWorld.isLoaded("colormatic") || GreatBigWorld.inDev()) {
            registerBlock(new Identifier(NAMESPACE, "brown_concrete"), BROWN_CONCRETE, null);
            registerBlock(new Identifier(NAMESPACE, "red_concrete"), RED_CONCRETE, null);
            registerBlock(new Identifier(NAMESPACE, "orange_concrete"), ORANGE_CONCRETE, null);
            registerBlock(new Identifier(NAMESPACE, "yellow_concrete"), YELLOW_CONCRETE, null);
            registerBlock(new Identifier(NAMESPACE, "lime_concrete"), LIME_CONCRETE, null);
            registerBlock(new Identifier(NAMESPACE, "green_concrete"), GREEN_CONCRETE, null);
            registerBlock(new Identifier(NAMESPACE, "cyan_concrete"), CYAN_CONCRETE, null);
            registerBlock(new Identifier(NAMESPACE, "blue_concrete"), BLUE_CONCRETE, null);
            registerBlock(new Identifier(NAMESPACE, "light_blue_concrete"), LIGHT_BLUE_CONCRETE, null);
            registerBlock(new Identifier(NAMESPACE, "pink_concrete"), PINK_CONCRETE, null);
            registerBlock(new Identifier(NAMESPACE, "magenta_concrete"), MAGENTA_CONCRETE, null);
            registerBlock(new Identifier(NAMESPACE, "purple_concrete"), PURPLE_CONCRETE, null);
            registerBlock(new Identifier(NAMESPACE, "black_concrete"), BLACK_CONCRETE, null);
            registerBlock(new Identifier(NAMESPACE, "gray_concrete"), GRAY_CONCRETE, null);
            registerBlock(new Identifier(NAMESPACE, "light_gray_concrete"), LIGHT_GRAY_CONCRETE, null);
            registerBlock(new Identifier(NAMESPACE, "white_concrete"), WHITE_CONCRETE, null);
            registerBlock(new Identifier(NAMESPACE, "brown_concrete_powder"), BROWN_CONCRETE_POWDER, null);
            registerBlock(new Identifier(NAMESPACE, "red_concrete_powder"), RED_CONCRETE_POWDER, null);
            registerBlock(new Identifier(NAMESPACE, "orange_concrete_powder"), ORANGE_CONCRETE_POWDER, null);
            registerBlock(new Identifier(NAMESPACE, "yellow_concrete_powder"), YELLOW_CONCRETE_POWDER, null);
            registerBlock(new Identifier(NAMESPACE, "lime_concrete_powder"), LIME_CONCRETE_POWDER, null);
            registerBlock(new Identifier(NAMESPACE, "green_concrete_powder"), GREEN_CONCRETE_POWDER, null);
            registerBlock(new Identifier(NAMESPACE, "cyan_concrete_powder"), CYAN_CONCRETE_POWDER, null);
            registerBlock(new Identifier(NAMESPACE, "blue_concrete_powder"), BLUE_CONCRETE_POWDER, null);
            registerBlock(new Identifier(NAMESPACE, "light_blue_concrete_powder"), LIGHT_BLUE_CONCRETE_POWDER, null);
            registerBlock(new Identifier(NAMESPACE, "pink_concrete_powder"), PINK_CONCRETE_POWDER, null);
            registerBlock(new Identifier(NAMESPACE, "magenta_concrete_powder"), MAGENTA_CONCRETE_POWDER, null);
            registerBlock(new Identifier(NAMESPACE, "purple_concrete_powder"), PURPLE_CONCRETE_POWDER, null);
            registerBlock(new Identifier(NAMESPACE, "black_concrete_powder"), BLACK_CONCRETE_POWDER, null);
            registerBlock(new Identifier(NAMESPACE, "gray_concrete_powder"), GRAY_CONCRETE_POWDER, null);
            registerBlock(new Identifier(NAMESPACE, "light_gray_concrete_powder"), LIGHT_GRAY_CONCRETE_POWDER, null);
            registerBlock(new Identifier(NAMESPACE, "white_concrete_powder"), WHITE_CONCRETE_POWDER, null);
            registerBlock(new Identifier(NAMESPACE, "brown_quilted_carpet"), BROWN_QUILTED_CARPET, null);
            registerBlock(new Identifier(NAMESPACE, "red_quilted_carpet"), RED_QUILTED_CARPET, null);
            registerBlock(new Identifier(NAMESPACE, "orange_quilted_carpet"), ORANGE_QUILTED_CARPET, null);
            registerBlock(new Identifier(NAMESPACE, "yellow_quilted_carpet"), YELLOW_QUILTED_CARPET, null);
            registerBlock(new Identifier(NAMESPACE, "lime_quilted_carpet"), LIME_QUILTED_CARPET, null);
            registerBlock(new Identifier(NAMESPACE, "green_quilted_carpet"), GREEN_QUILTED_CARPET, null);
            registerBlock(new Identifier(NAMESPACE, "cyan_quilted_carpet"), CYAN_QUILTED_CARPET, null);
            registerBlock(new Identifier(NAMESPACE, "blue_quilted_carpet"), BLUE_QUILTED_CARPET, null);
            registerBlock(new Identifier(NAMESPACE, "light_blue_quilted_carpet"), LIGHT_BLUE_QUILTED_CARPET, null);
            registerBlock(new Identifier(NAMESPACE, "pink_quilted_carpet"), PINK_QUILTED_CARPET, null);
            registerBlock(new Identifier(NAMESPACE, "magenta_quilted_carpet"), MAGENTA_QUILTED_CARPET, null);
            registerBlock(new Identifier(NAMESPACE, "purple_quilted_carpet"), PURPLE_QUILTED_CARPET, null);
            registerBlock(new Identifier(NAMESPACE, "black_quilted_carpet"), BLACK_QUILTED_CARPET, null);
            registerBlock(new Identifier(NAMESPACE, "gray_quilted_carpet"), GRAY_QUILTED_CARPET, null);
            registerBlock(new Identifier(NAMESPACE, "light_gray_quilted_carpet"), LIGHT_GRAY_QUILTED_CARPET, null);
            registerBlock(new Identifier(NAMESPACE, "white_quilted_carpet"), WHITE_QUILTED_CARPET, null);
            registerBlock(new Identifier(NAMESPACE, "hanging_oak_leaves"), HANGING_OAK_LEAVES, null);
            registerBlock(new Identifier(NAMESPACE, "hanging_spruce_leaves"), HANGING_SPRUCE_LEAVES, null);
            registerBlock(new Identifier(NAMESPACE, "hanging_birch_leaves"), HANGING_BIRCH_LEAVES, null);
            registerBlock(new Identifier(NAMESPACE, "hanging_jungle_leaves"), HANGING_JUNGLE_LEAVES, null);
            registerBlock(new Identifier(NAMESPACE, "hanging_dark_oak_leaves"), HANGING_DARK_OAK_LEAVES, null);
            registerBlock(new Identifier(NAMESPACE, "hanging_acacia_leaves"), HANGING_ACACIA_LEAVES, null);
            registerBlock(new Identifier(NAMESPACE, "hanging_mangrove_leaves"), HANGING_MANGROVE_LEAVES, null);
            registerBlock(new Identifier(NAMESPACE, "hanging_palo_verde_leaves"), HANGING_PALO_VERDE_LEAVES, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "hanging_mahogany_leaves"), HANGING_MAHOGANY_LEAVES, null);
            LayerConcretePowderBlock.POWDER_TO_LAYERED.put(Blocks.BROWN_CONCRETE_POWDER, BlockRegistry.BROWN_CONCRETE_POWDER);
            LayerConcretePowderBlock.POWDER_TO_LAYERED.put(Blocks.RED_CONCRETE_POWDER, BlockRegistry.RED_CONCRETE_POWDER);
            LayerConcretePowderBlock.POWDER_TO_LAYERED.put(Blocks.ORANGE_CONCRETE_POWDER, BlockRegistry.ORANGE_CONCRETE_POWDER);
            LayerConcretePowderBlock.POWDER_TO_LAYERED.put(Blocks.YELLOW_CONCRETE_POWDER, BlockRegistry.YELLOW_CONCRETE_POWDER);
            LayerConcretePowderBlock.POWDER_TO_LAYERED.put(Blocks.LIME_CONCRETE_POWDER, BlockRegistry.LIME_CONCRETE_POWDER);
            LayerConcretePowderBlock.POWDER_TO_LAYERED.put(Blocks.GREEN_CONCRETE_POWDER, BlockRegistry.GREEN_CONCRETE_POWDER);
            LayerConcretePowderBlock.POWDER_TO_LAYERED.put(Blocks.CYAN_CONCRETE_POWDER, BlockRegistry.CYAN_CONCRETE_POWDER);
            LayerConcretePowderBlock.POWDER_TO_LAYERED.put(Blocks.BLUE_CONCRETE_POWDER, BlockRegistry.BLUE_CONCRETE_POWDER);
            LayerConcretePowderBlock.POWDER_TO_LAYERED.put(Blocks.LIGHT_BLUE_CONCRETE_POWDER, BlockRegistry.LIGHT_BLUE_CONCRETE_POWDER);
            LayerConcretePowderBlock.POWDER_TO_LAYERED.put(Blocks.PINK_CONCRETE_POWDER, BlockRegistry.PINK_CONCRETE_POWDER);
            LayerConcretePowderBlock.POWDER_TO_LAYERED.put(Blocks.MAGENTA_CONCRETE_POWDER, BlockRegistry.MAGENTA_CONCRETE_POWDER);
            LayerConcretePowderBlock.POWDER_TO_LAYERED.put(Blocks.PURPLE_CONCRETE_POWDER, BlockRegistry.PURPLE_CONCRETE_POWDER);
            LayerConcretePowderBlock.POWDER_TO_LAYERED.put(Blocks.BLACK_CONCRETE_POWDER, BlockRegistry.BLACK_CONCRETE_POWDER);
            LayerConcretePowderBlock.POWDER_TO_LAYERED.put(Blocks.GRAY_CONCRETE_POWDER, BlockRegistry.GRAY_CONCRETE_POWDER);
            LayerConcretePowderBlock.POWDER_TO_LAYERED.put(Blocks.LIGHT_GRAY_CONCRETE_POWDER, BlockRegistry.LIGHT_GRAY_CONCRETE_POWDER);
            LayerConcretePowderBlock.POWDER_TO_LAYERED.put(Blocks.WHITE_CONCRETE_POWDER, BlockRegistry.WHITE_CONCRETE_POWDER);
        }

        if (GreatBigWorld.isLoaded("cornucopia") || GreatBigWorld.inDev()) {
            registerBlock(new Identifier(NAMESPACE, "packed_dirt"), PACKED_DIRT, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "dirt_bricks"), DIRT_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "dirt_brick_slab"), DIRT_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "dirt_brick_stairs"), DIRT_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "dirt_brick_wall"), DIRT_BRICK_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "gold_deposit"), GOLD_DEPOSIT, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "iron_deposit"), IRON_DEPOSIT, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "copper_deposit"), COPPER_DEPOSIT, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "clay_deposit"), CLAY_DEPOSIT, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "peat_deposit"), PEAT_DEPOSIT, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "apple_pie"), APPLE_PIE, ItemGroup.FOOD);
            registerBlock(new Identifier(NAMESPACE, "marigold"), MARIGOLD, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "potted_marigold"), POTTED_MARIGOLD, null);
            registerBlock(new Identifier(NAMESPACE, "charred_log"), CHARRED_LOG, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "charred_leaves"), CHARRED_LEAVES, ItemGroup.DECORATIONS);
        }

        if (GreatBigWorld.isLoaded("change_the_world") || GreatBigWorld.inDev()) {
            registerBlock(new Identifier(NAMESPACE, "permafrost"), PERMAFROST, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "palo_verde_log"), PALO_VERDE_LOG, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "stripped_palo_verde_log"), STRIPPED_PALO_VERDE_LOG, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "palo_verde_planks"), PALO_VERDE_PLANKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "palo_verde_slab"), PALO_VERDE_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "palo_verde_stairs"), PALO_VERDE_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "palo_verde_fence"), PALO_VERDE_FENCE, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "palo_verde_fence_gate"), PALO_VERDE_FENCE_GATE, ItemGroup.REDSTONE);
            registerBlock(new Identifier(NAMESPACE, "palo_verde_leaves"), PALO_VERDE_LEAVES, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "palo_verde_sapling"), PALO_VERDE_SAPLING, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "sequoia_log"), SEQUOIA_LOG, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "stripped_sequoia_log"), STRIPPED_SEQUOIA_LOG, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "sequoia_planks"), SEQUOIA_PLANKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "sequoia_slab"), SEQUOIA_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "sequoia_stairs"), SEQUOIA_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "sequoia_fence"), SEQUOIA_FENCE, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "sequoia_fence_gate"), SEQUOIA_FENCE_GATE, ItemGroup.REDSTONE);
            registerBlock(new Identifier(NAMESPACE, "sequoia_leaves"), SEQUOIA_LEAVES, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "mahogany_log"), MAHOGANY_LOG, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "stripped_mahogany_log"), STRIPPED_MAHOGANY_LOG, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "mahogany_planks"), MAHOGANY_PLANKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "mahogany_slab"), MAHOGANY_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "mahogany_stairs"), MAHOGANY_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "mahogany_fence"), MAHOGANY_FENCE, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "mahogany_fence_gate"), MAHOGANY_FENCE_GATE, ItemGroup.REDSTONE);
            registerBlock(new Identifier(NAMESPACE, "mahogany_leaves"), MAHOGANY_LEAVES, null);
            registerBlock(new Identifier(NAMESPACE, "mahogany_sapling"), MAHOGANY_SAPLING, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "grassy_stone"), GRASSY_STONE, null);
            registerBlock(new Identifier(NAMESPACE, "grassy_deepslate"), GRASSY_DEEPSLATE, null);
            registerBlock(new Identifier(NAMESPACE, "grass_thatch"), GRASS_THATCH, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "grass_thatch_slab"), GRASS_THATCH_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "grass_thatch_stairs"), GRASS_THATCH_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "bamboo_thatch"), BAMBOO_THATCH, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "bamboo_thatch_slab"), BAMBOO_THATCH_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "bamboo_thatch_stairs"), BAMBOO_THATCH_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "terracotta_bricks"), TERRACOTTA_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "brown_terracotta_bricks"), BROWN_TERRACOTTA_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "red_terracotta_bricks"), RED_TERRACOTTA_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "orange_terracotta_bricks"), ORANGE_TERRACOTTA_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "yellow_terracotta_bricks"), YELLOW_TERRACOTTA_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "lime_terracotta_bricks"), LIME_TERRACOTTA_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "green_terracotta_bricks"), GREEN_TERRACOTTA_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "cyan_terracotta_bricks"), CYAN_TERRACOTTA_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "blue_terracotta_bricks"), BLUE_TERRACOTTA_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "light_blue_terracotta_bricks"), LIGHT_BLUE_TERRACOTTA_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "pink_terracotta_bricks"), PINK_TERRACOTTA_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "magenta_terracotta_bricks"), MAGENTA_TERRACOTTA_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "purple_terracotta_bricks"), PURPLE_TERRACOTTA_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "black_terracotta_bricks"), BLACK_TERRACOTTA_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "gray_terracotta_bricks"), GRAY_TERRACOTTA_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "light_gray_terracotta_bricks"), LIGHT_GRAY_TERRACOTTA_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "white_terracotta_bricks"), WHITE_TERRACOTTA_BRICKS, ItemGroup.BUILDING_BLOCKS);
        }

        if (GreatBigWorld.isLoaded("fire_and_ice") || GreatBigWorld.inDev()) {
            registerBlock(new Identifier(NAMESPACE, "ice_bricks"), ICE_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "ice_brick_slab"), ICE_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "ice_brick_stairs"), ICE_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "ice_brick_wall"), ICE_BRICK_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "magma_bricks"), MAGMA_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "magma_brick_slab"), MAGMA_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "magma_brick_stairs"), MAGMA_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "magma_brick_wall"), MAGMA_BRICK_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "nether_brick_fence_gate"), NETHER_BRICK_FENCE_GATE, ItemGroup.REDSTONE);
            registerBlock(new Identifier(NAMESPACE, "red_nether_brick_fence"), RED_NETHER_BRICK_FENCE, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "red_nether_brick_fence_gate"), RED_NETHER_BRICK_FENCE_GATE, ItemGroup.REDSTONE);
        }

        if (GreatBigWorld.isLoaded("high_seas") || GreatBigWorld.inDev()) {
            registerBlock(new Identifier(NAMESPACE, "elder_prismarine"), ELDER_PRISMARINE, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "elder_prismarine_slab"), ELDER_PRISMARINE_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "elder_prismarine_stairs"), ELDER_PRISMARINE_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "elder_prismarine_wall"), ELDER_PRISMARINE_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "elder_prismarine_bricks"), ELDER_PRISMARINE_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "elder_prismarine_brick_slab"), ELDER_PRISMARINE_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "elder_prismarine_brick_stairs"), ELDER_PRISMARINE_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "elder_prismarine_brick_wall"), ELDER_PRISMARINE_BRICK_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "elder_sea_lantern"), ELDER_SEA_LANTERN, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "milk_cauldron"), MILK_CAULDRON, null);
            registerBlock(new Identifier(NAMESPACE, "cheese_cauldron"), CHEESE_CAULDRON, null);
            registerBlock(new Identifier(NAMESPACE, "moldy_cheese_cauldron"), MOLDY_CHEESE_CAULDRON, null);
            registerBlock(new Identifier(NAMESPACE, "cheese_wheel"), CHEESE_WHEEL, ItemGroup.FOOD);
            registerBlock(new Identifier(NAMESPACE, "moldy_cheese_wheel"), MOLDY_CHEESE_WHEEL, ItemGroup.FOOD);
            registerBlock(new Identifier(NAMESPACE, "phantom_glass"), PHANTOM_GLASS, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "nautilus_fossil"), NAUTILUS_FOSSIL, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "algae"), ALGAE, ItemGroup.DECORATIONS);
        }

        if (GreatBigWorld.isLoaded("honeycomb") || GreatBigWorld.inDev()) {
            registerBlock(new Identifier(NAMESPACE, "heather"), HEATHER, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "rose"), ROSE, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "pollen_block"), POLLEN_BOCK, ItemGroup.DECORATIONS);
        }

        if (GreatBigWorld.isLoaded("magic_v_melee") || GreatBigWorld.inDev()) {
            registerBlock(new Identifier(NAMESPACE, "sawmill"), SAWMILL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "sour_berry_bush"), SOUR_BERRY_BUSH, null);
            registerBlock(new Identifier(NAMESPACE, "bitter_berry_bush"), BITTER_BERRY_BUSH, null);
            registerBlock(new Identifier(NAMESPACE, "pungent_berry_bush"), PUNGENT_BERRY_BUSH, null);
            registerBlock(new Identifier(NAMESPACE, "end_stone_slab"), END_STONE_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "deepslate_slab"), DEEPSLATE_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "smooth_basalt_slab"), SMOOTH_BASALT_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "tuff_slab"), TUFF_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "calcite_slab"), CALCITE_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "dripstone_slab"), DRIPSTONE_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "end_stone_stairs"), END_STONE_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "deepslate_stairs"), DEEPSLATE_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "smooth_basalt_stairs"), SMOOTH_BASALT_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "tuff_stairs"), TUFF_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "calcite_stairs"), CALCITE_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "dripstone_stairs"), DRIPSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "end_stone_wall"), END_STONE_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "smooth_basalt_wall"), SMOOTH_BASALT_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "polished_granite_wall"), POLISHED_GRANITE_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "polished_andesite_wall"), POLISHED_ANDESITE_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "polished_diorite_wall"), POLISHED_DIORITE_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "smooth_sandstone_wall"), SMOOTH_SANDSTONE_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "smooth_red_sandstone_wall"), SMOOTH_RED_SANDSTONE_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "prismarine_brick_wall"), PRISMARINE_BRICK_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "dark_prismarine_wall"), DARK_PRISMARINE_BRICK_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "tuff_wall"), TUFF_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "calcite_wall"), CALCITE_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "dripstone_wall"), DRIPSTONE_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "bamboo_torch"), BAMBOO_TORCH, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "bamboo_wall_torch"), BAMBOO_WALL_TORCH, null);
            registerBlock(new Identifier(NAMESPACE, "soul_bamboo_torch"), SOUL_BAMBOO_TORCH, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "soul_bamboo_wall_torch"), SOUL_BAMBOO_WALL_TORCH, null);
            registerBlock(new Identifier(NAMESPACE, "dried_bamboo_planks"), DRIED_BAMBOO_PLANKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "dried_bamboo_slab"), DRIED_BAMBOO_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "dried_bamboo_stairs"), DRIED_BAMBOO_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "dried_bamboo_fence"), DRIED_BAMBOO_FENCE, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "dried_bamboo_fence_gate"), DRIED_BAMBOO_FENCE_GATE, ItemGroup.REDSTONE);
            registerBlock(new Identifier(NAMESPACE, "dried_bamboo_button"), DRIED_BAMBOO_BUTTON, ItemGroup.REDSTONE);
            registerBlock(new Identifier(NAMESPACE, "dried_bamboo_pressure_plate"), DRIED_BAMBOO_PRESSURE_PLATE, ItemGroup.REDSTONE);
            registerBlock(new Identifier(NAMESPACE, "dried_bamboo_door"), DRIED_BAMBOO_DOOR, ItemGroup.REDSTONE);
            registerBlock(new Identifier(NAMESPACE, "dried_bamboo_trapdoor"), DRIED_BAMBOO_TRAPDOOR, ItemGroup.REDSTONE);
            registerBlock(new Identifier(NAMESPACE, "corvus"), CORVUS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "corvus_slab"), CORVUS_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "corvus_stairs"), CORVUS_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "corvus_wall"), CORVUS_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "corvus_bricks"), CORVUS_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "corvus_brick_slab"), CORVUS_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "corvus_brick_stairs"), CORVUS_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "corvus_brick_wall"), CORVUS_BRICK_WALL, ItemGroup.DECORATIONS);
        }

        if (GreatBigWorld.isLoaded("redstone_refurbished") || GreatBigWorld.inDev()) {
            registerBlock(new Identifier(NAMESPACE, "receptor"), RECEPTOR, ItemGroup.REDSTONE);
            registerBlock(new Identifier(NAMESPACE, "slime"), SLIME, ItemGroup.REDSTONE);
        }

        if (GreatBigWorld.isLoaded("twisted_nether") || GreatBigWorld.inDev()) {
            registerBlock(new Identifier(NAMESPACE, "twisted_wart_block"), TWISTED_WART_BLOCK, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "twisted_nylium"), TWISTED_NYLIUM, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "twisted_fungus"), TWISTED_FUNGUS, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "twisted_stem"), TWISTED_STEM, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "stripped_twisted_stem"), STRIPPED_TWISTED_STEM, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "twisted_planks"), TWISTED_PLANKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "twisted_slab"), TWISTED_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "twisted_stairs"), TWISTED_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "twisted_fence"), TWISTED_FENCE, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "twisted_fence_gate"), TWISTED_FENCE_GATE, ItemGroup.REDSTONE);
            registerBlock(new Identifier(NAMESPACE, "warped_shroomlight"), WARPED_SHROOMLIGHT, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "twisted_shroomlight"), TWISTED_SHROOMLIGHT, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "blue_nether_bricks"), BLUE_NETHER_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "blue_nether_brick_slab"), BLUE_NETHER_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "blue_nether_brick_stairs"), BLUE_NETHER_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "blue_nether_brick_wall"), BLUE_NETHER_BRICK_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "blue_nether_brick_fence"), BLUE_NETHER_BRICK_FENCE, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "blue_nether_brick_fence_gate"), BLUE_NETHER_BRICK_FENCE_GATE, ItemGroup.REDSTONE);
            registerBlock(new Identifier(NAMESPACE, "purple_nether_bricks"), PURPLE_NETHER_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "purple_nether_brick_slab"), PURPLE_NETHER_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "purple_nether_brick_stairs"), PURPLE_NETHER_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "purple_nether_brick_wall"), PURPLE_NETHER_BRICK_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "purple_nether_brick_fence"), PURPLE_NETHER_BRICK_FENCE, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "purple_nether_brick_fence_gate"), PURPLE_NETHER_BRICK_FENCE_GATE, ItemGroup.REDSTONE);
            registerBlock(new Identifier(NAMESPACE, "quartz_brick_slab"), QUARTZ_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "quartz_brick_stairs"), QUARTZ_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "quartz_brick_wall"), QUARTZ_BRICK_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "cobbled_basalt"), COBBLED_BASALT, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "cobbled_basalt_slab"), COBBLED_BASALT_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "cobbled_basalt_stairs"), COBBLED_BASALT_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "cobbled_basalt_wall"), COBBLED_BASALT_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "basalt_bricks"), BASALT_BRICKS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "basalt_brick_slab"), BASALT_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "basalt_brick_stairs"), BASALT_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "basalt_brick_wall"), BASALT_BRICK_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "soulshale"), SOULSHALE, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "soulshale_slab"), SOULSHALE_SLAB, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "soulshale_stairs"), SOULSHALE_STAIRS, ItemGroup.BUILDING_BLOCKS);
            registerBlock(new Identifier(NAMESPACE, "soulshale_wall"), SOULSHALE_WALL, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "whispering_soulshale"), WHISPERING_SOULSHALE, ItemGroup.BUILDING_BLOCKS);
        }

        if (GreatBigWorld.isLoaded("wonders_of_the_wild") || GreatBigWorld.inDev()) {
            registerBlock(new Identifier(NAMESPACE, "hollow_oak_log"), HOLLOW_OAK_LOG, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "hollow_spruce_log"), HOLLOW_SPRUCE_LOG, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "hollow_birch_log"), HOLLOW_BIRCH_LOG, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "hollow_jungle_log"), HOLLOW_JUNGLE_LOG, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "hollow_dark_oak_log"), HOLLOW_DARK_OAK_LOG, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "hollow_acacia_log"), HOLLOW_ACACIA_LOG, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "hollow_mangrove_log"), HOLLOW_MANGROVE_LOG, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "hollow_mahogany_log"), HOLLOW_MAHOGANY_LOG, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "hollow_palo_verde_log"), HOLLOW_PALO_VERDE_LOG, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "stripped_hollow_oak_log"), STRIPPED_HOLLOW_OAK_LOG, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "stripped_hollow_spruce_log"), STRIPPED_HOLLOW_SPRUCE_LOG, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "stripped_hollow_birch_log"), STRIPPED_HOLLOW_BIRCH_LOG, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "stripped_hollow_jungle_log"), STRIPPED_HOLLOW_JUNGLE_LOG, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "stripped_hollow_dark_oak_log"), STRIPPED_HOLLOW_DARK_OAK_LOG, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "stripped_hollow_acacia_log"), STRIPPED_HOLLOW_ACACIA_LOG, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "stripped_hollow_mangrove_log"), STRIPPED_HOLLOW_MANGROVE_LOG, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "stripped_hollow_mahogany_log"), STRIPPED_HOLLOW_MAHOGANY_LOG, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "stripped_hollow_palo_verde_log"), STRIPPED_HOLLOW_PALO_VERDE_LOG, ItemGroup.DECORATIONS);
            registerBlock(new Identifier(NAMESPACE, "giant_potato"), GIANT_POTATO, ItemGroup.FOOD);
            registerBlock(new Identifier(NAMESPACE, "giant_poisonous_potato"), GIANT_POISONOUS_POTATO, ItemGroup.FOOD);
            registerBlock(new Identifier(NAMESPACE, "giant_carrot"), GIANT_CARROT, ItemGroup.FOOD);
            registerBlock(new Identifier(NAMESPACE, "giant_beetroot"), GIANT_BEETROOT, ItemGroup.FOOD);
            registerBlock(new Identifier(NAMESPACE, "giant_heartbeet"), GIANT_HEARTBEET, ItemGroup.FOOD);
        }

        registerBlock(new Identifier(NAMESPACE, "red_rock"), RED_ROCK, ItemGroup.BUILDING_BLOCKS);

        flammables();
        strippables();
        compostables();
        oxidizables();
    }

    private static void flammables() {
        FireBlock fire = (FireBlock) Blocks.FIRE;
        fire.registerFlammableBlock(PALO_VERDE_LOG, 5, 5);
        fire.registerFlammableBlock(STRIPPED_PALO_VERDE_LOG, 5, 5);
        fire.registerFlammableBlock(PALO_VERDE_PLANKS, 5, 20);
        fire.registerFlammableBlock(PALO_VERDE_LEAVES, 30, 60);
        fire.registerFlammableBlock(SEQUOIA_LOG, 5, 5);
        fire.registerFlammableBlock(STRIPPED_SEQUOIA_LOG, 5, 5);
        fire.registerFlammableBlock(SEQUOIA_PLANKS, 5, 20);
        fire.registerFlammableBlock(SEQUOIA_LEAVES, 30, 60);
        fire.registerFlammableBlock(MAHOGANY_LOG, 5, 5);
        fire.registerFlammableBlock(STRIPPED_MAHOGANY_LOG, 5, 5);
        fire.registerFlammableBlock(MAHOGANY_PLANKS, 5, 20);
        fire.registerFlammableBlock(HOLLOW_OAK_LOG, 30, 60);
        fire.registerFlammableBlock(HOLLOW_SPRUCE_LOG, 30, 60);
        fire.registerFlammableBlock(HOLLOW_BIRCH_LOG, 30, 60);
        fire.registerFlammableBlock(HOLLOW_JUNGLE_LOG, 30, 60);
        fire.registerFlammableBlock(HOLLOW_DARK_OAK_LOG, 30, 60);
        fire.registerFlammableBlock(HOLLOW_ACACIA_LOG, 30, 60);
        fire.registerFlammableBlock(HOLLOW_MANGROVE_LOG, 30, 60);
        fire.registerFlammableBlock(HOLLOW_PALO_VERDE_LOG, 30, 60);
        fire.registerFlammableBlock(HOLLOW_MAHOGANY_LOG, 30, 60);
        fire.registerFlammableBlock(STRIPPED_HOLLOW_OAK_LOG, 30, 60);
        fire.registerFlammableBlock(STRIPPED_HOLLOW_SPRUCE_LOG, 30, 60);
        fire.registerFlammableBlock(STRIPPED_HOLLOW_BIRCH_LOG, 30, 60);
        fire.registerFlammableBlock(STRIPPED_HOLLOW_JUNGLE_LOG, 30, 60);
        fire.registerFlammableBlock(STRIPPED_HOLLOW_DARK_OAK_LOG, 30, 60);
        fire.registerFlammableBlock(STRIPPED_HOLLOW_ACACIA_LOG, 30, 60);
        fire.registerFlammableBlock(STRIPPED_HOLLOW_MANGROVE_LOG, 30, 60);
        fire.registerFlammableBlock(STRIPPED_HOLLOW_PALO_VERDE_LOG, 30, 60);
        fire.registerFlammableBlock(STRIPPED_HOLLOW_MAHOGANY_LOG, 30, 60);
        fire.registerFlammableBlock(PALO_VERDE_SLAB, 5, 20);
        fire.registerFlammableBlock(MAHOGANY_SLAB, 5, 20);
        fire.registerFlammableBlock(SEQUOIA_SLAB, 5, 20);
        fire.registerFlammableBlock(PALO_VERDE_FENCE_GATE, 5, 20);
        fire.registerFlammableBlock(MAHOGANY_FENCE_GATE, 5, 20);
        fire.registerFlammableBlock(SEQUOIA_FENCE_GATE, 5, 20);
        fire.registerFlammableBlock(PALO_VERDE_FENCE, 5, 20);
        fire.registerFlammableBlock(MAHOGANY_FENCE, 5, 20);
        fire.registerFlammableBlock(SEQUOIA_FENCE, 5, 20);
        fire.registerFlammableBlock(PALO_VERDE_STAIRS, 5, 20);
        fire.registerFlammableBlock(MAHOGANY_STAIRS, 5, 20);
        fire.registerFlammableBlock(SEQUOIA_STAIRS, 5, 20);
        fire.registerFlammableBlock(DRIED_BAMBOO_PLANKS, 5, 20);
        fire.registerFlammableBlock(MARIGOLD, 60, 100);
        fire.registerFlammableBlock(HEATHER, 60, 100);
        fire.registerFlammableBlock(ROSE, 60, 100);
        fire.registerFlammableBlock(SOUR_BERRY_BUSH, 60, 100);
        fire.registerFlammableBlock(BITTER_BERRY_BUSH, 60, 100);
        fire.registerFlammableBlock(PUNGENT_BERRY_BUSH, 60, 100);
        fire.registerFlammableBlock(WHITE_QUILTED_CARPET, 60, 20);
        fire.registerFlammableBlock(ORANGE_QUILTED_CARPET, 60, 20);
        fire.registerFlammableBlock(MAGENTA_QUILTED_CARPET, 60, 20);
        fire.registerFlammableBlock(LIGHT_BLUE_QUILTED_CARPET, 60, 20);
        fire.registerFlammableBlock(YELLOW_QUILTED_CARPET, 60, 20);
        fire.registerFlammableBlock(LIME_QUILTED_CARPET, 60, 20);
        fire.registerFlammableBlock(PINK_QUILTED_CARPET, 60, 20);
        fire.registerFlammableBlock(GRAY_QUILTED_CARPET, 60, 20);
        fire.registerFlammableBlock(LIGHT_GRAY_QUILTED_CARPET, 60, 20);
        fire.registerFlammableBlock(CYAN_QUILTED_CARPET, 60, 20);
        fire.registerFlammableBlock(PURPLE_QUILTED_CARPET, 60, 20);
        fire.registerFlammableBlock(BLUE_QUILTED_CARPET, 60, 20);
        fire.registerFlammableBlock(BROWN_QUILTED_CARPET, 60, 20);
        fire.registerFlammableBlock(GREEN_QUILTED_CARPET, 60, 20);
        fire.registerFlammableBlock(RED_QUILTED_CARPET, 60, 20);
        fire.registerFlammableBlock(BLACK_QUILTED_CARPET, 60, 20);
        fire.registerFlammableBlock(BAMBOO_TORCH, 60, 60);
        fire.registerFlammableBlock(SOUL_BAMBOO_TORCH, 60, 60);
        fire.registerFlammableBlock(BAMBOO_WALL_TORCH, 60, 60);
        fire.registerFlammableBlock(SOUL_BAMBOO_WALL_TORCH, 60, 60);
        fire.registerFlammableBlock(ALGAE, 10, 80);
    }

    private static void strippables() {
        AxeItem.STRIPPED_BLOCKS = new ImmutableMap.Builder<Block, Block>()
                .putAll(AxeItem.STRIPPED_BLOCKS)
                .put(PALO_VERDE_LOG, STRIPPED_PALO_VERDE_LOG)
                .put(SEQUOIA_LOG, STRIPPED_SEQUOIA_LOG)
                .put(MAHOGANY_LOG, STRIPPED_MAHOGANY_LOG)
                .put(TWISTED_STEM, STRIPPED_TWISTED_STEM)
                .put(HOLLOW_OAK_LOG, STRIPPED_HOLLOW_OAK_LOG)
                .put(HOLLOW_SPRUCE_LOG, STRIPPED_HOLLOW_SPRUCE_LOG)
                .put(HOLLOW_BIRCH_LOG, STRIPPED_HOLLOW_BIRCH_LOG)
                .put(HOLLOW_JUNGLE_LOG, STRIPPED_HOLLOW_JUNGLE_LOG)
                .put(HOLLOW_DARK_OAK_LOG, STRIPPED_HOLLOW_DARK_OAK_LOG)
                .put(HOLLOW_ACACIA_LOG, STRIPPED_HOLLOW_ACACIA_LOG)
                .put(HOLLOW_MANGROVE_LOG, STRIPPED_HOLLOW_MANGROVE_LOG)
                .put(HOLLOW_MAHOGANY_LOG, STRIPPED_HOLLOW_MAHOGANY_LOG)
                .put(HOLLOW_PALO_VERDE_LOG, STRIPPED_HOLLOW_PALO_VERDE_LOG)
                .build();
    }

    private static void compostables() {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(MARIGOLD, .65f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(HEATHER, .65f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ROSE, .65f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(APPLE_PIE, 1f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(PALO_VERDE_LEAVES, .3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(MAHOGANY_LEAVES, .3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(PALO_VERDE_SAPLING, .3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(MAHOGANY_SAPLING, .3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ItemRegistry.PUNGENT_BERRIES, .3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ItemRegistry.SOUR_BERRIES, .3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ItemRegistry.BITTER_BERRIES, .3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(HANGING_OAK_LEAVES, .3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(HANGING_SPRUCE_LEAVES, .3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(HANGING_BIRCH_LEAVES, .3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(HANGING_JUNGLE_LEAVES, .3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(HANGING_DARK_OAK_LEAVES, .3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(HANGING_ACACIA_LEAVES, .3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(HANGING_MANGROVE_LEAVES, .3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(HANGING_PALO_VERDE_LEAVES, .3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(HANGING_MAHOGANY_LEAVES, .3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(TWISTED_FUNGUS, .65f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(TWISTED_WART_BLOCK, .85f);
    }

    private static void oxidizables() {
        //Oxidizable.OXIDATION_LEVEL_INCREASES.get().put(CUT_COPPER_PILLAR, EXPOSED_CUT_COPPER_PILLAR);
        //Oxidizable.OXIDATION_LEVEL_INCREASES.get().put(EXPOSED_CUT_COPPER_PILLAR, WEATHERED_CUT_COPPER_PILLAR);
        //Oxidizable.OXIDATION_LEVEL_INCREASES.get().put(WEATHERED_CUT_COPPER_PILLAR, OXIDIZED_CUT_COPPER_PILLAR);
        //Oxidizable.OXIDATION_LEVEL_DECREASES.get().put(OXIDIZED_CUT_COPPER_PILLAR, WEATHERED_CUT_COPPER_PILLAR);
        //Oxidizable.OXIDATION_LEVEL_DECREASES.get().put(WEATHERED_CUT_COPPER_PILLAR, EXPOSED_CUT_COPPER_PILLAR);
        //Oxidizable.OXIDATION_LEVEL_DECREASES.get().put(EXPOSED_CUT_COPPER_PILLAR, CUT_COPPER_PILLAR);
        Oxidizable.OXIDATION_LEVEL_INCREASES.get().put(CHISELED_CUT_COPPER_BLOCK, EXPOSED_CHISELED_CUT_COPPER_BLOCK);
        Oxidizable.OXIDATION_LEVEL_INCREASES.get().put(EXPOSED_CHISELED_CUT_COPPER_BLOCK, WEATHERED_CHISELED_CUT_COPPER_BLOCK);
        Oxidizable.OXIDATION_LEVEL_INCREASES.get().put(WEATHERED_CHISELED_CUT_COPPER_BLOCK, OXIDIZED_CHISELED_CUT_COPPER_BLOCK);
        Oxidizable.OXIDATION_LEVEL_DECREASES.get().put(OXIDIZED_CHISELED_CUT_COPPER_BLOCK, WEATHERED_CHISELED_CUT_COPPER_BLOCK);
        Oxidizable.OXIDATION_LEVEL_DECREASES.get().put(WEATHERED_CHISELED_CUT_COPPER_BLOCK, EXPOSED_CHISELED_CUT_COPPER_BLOCK);
        Oxidizable.OXIDATION_LEVEL_DECREASES.get().put(EXPOSED_CHISELED_CUT_COPPER_BLOCK, CHISELED_CUT_COPPER_BLOCK);
    }

    @Environment(EnvType.CLIENT)
    public static void renderLayers() {
        RenderLayers.BLOCKS.put(MARIGOLD, RenderLayer.getCutout());
        RenderLayers.BLOCKS.put(POTTED_MARIGOLD, RenderLayer.getCutout());
        RenderLayers.BLOCKS.put(BAMBOO_TORCH, RenderLayer.getCutout());
        RenderLayers.BLOCKS.put(SOUL_BAMBOO_TORCH, RenderLayer.getCutout());
        RenderLayers.BLOCKS.put(ALGAE, RenderLayer.getCutout());
        RenderLayers.BLOCKS.put(PALO_VERDE_SAPLING, RenderLayer.getCutout());
        RenderLayers.BLOCKS.put(MAHOGANY_SAPLING, RenderLayer.getCutout());

        RenderLayers.BLOCKS.put(GRASSY_STONE, RenderLayer.getCutoutMipped());
        RenderLayers.BLOCKS.put(GRASSY_DEEPSLATE, RenderLayer.getCutoutMipped());
        RenderLayers.BLOCKS.put(MAHOGANY_LEAVES, RenderLayer.getCutoutMipped());
        RenderLayers.BLOCKS.put(HANGING_OAK_LEAVES, RenderLayer.getCutoutMipped());
        RenderLayers.BLOCKS.put(HANGING_SPRUCE_LEAVES, RenderLayer.getCutoutMipped());
        RenderLayers.BLOCKS.put(HANGING_BIRCH_LEAVES, RenderLayer.getCutoutMipped());
        RenderLayers.BLOCKS.put(HANGING_JUNGLE_LEAVES, RenderLayer.getCutoutMipped());
        RenderLayers.BLOCKS.put(HANGING_DARK_OAK_LEAVES, RenderLayer.getCutoutMipped());
        RenderLayers.BLOCKS.put(HANGING_ACACIA_LEAVES, RenderLayer.getCutoutMipped());
        RenderLayers.BLOCKS.put(HANGING_MANGROVE_LEAVES, RenderLayer.getCutoutMipped());
        RenderLayers.BLOCKS.put(HANGING_PALO_VERDE_LEAVES, RenderLayer.getCutoutMipped());
        RenderLayers.BLOCKS.put(HANGING_MAHOGANY_LEAVES, RenderLayer.getCutoutMipped());

        RenderLayers.BLOCKS.put(SLIME, RenderLayer.getTranslucent());
    }

    @Environment(EnvType.CLIENT)
    public static void tintBlocks() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(.5d, 1d), GRASSY_STONE, GRASSY_DEEPSLATE);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(), MAHOGANY_LEAVES, HANGING_OAK_LEAVES, HANGING_JUNGLE_LEAVES, HANGING_ACACIA_LEAVES, HANGING_DARK_OAK_LEAVES, HANGING_MANGROVE_LEAVES, HANGING_MAHOGANY_LEAVES);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FoliageColors.getSpruceColor(), HANGING_SPRUCE_LEAVES);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FoliageColors.getBirchColor(), HANGING_BIRCH_LEAVES);
    }

    public static void registerBlock(Identifier id, Block block, ItemGroup group) {
        Registry.register(Registry.BLOCK, id, block);
        if (group != null) Registry.register(Registry.ITEM, id, new BlockItem(block, new Item.Settings().group(group)));
    }
}
