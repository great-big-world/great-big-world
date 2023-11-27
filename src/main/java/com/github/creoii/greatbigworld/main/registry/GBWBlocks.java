package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.creolib.api.util.block.BlockRegistryHelper;
import com.github.creoii.creolib.api.util.block.CBlockSettings;
import com.github.creoii.creolib.api.util.block.Spreadable;
import com.github.creoii.creolib.api.util.item.CItemSettings;
import com.github.creoii.creolib.api.util.item.ItemRegistryHelper;
import com.github.creoii.greatbigworld.block.*;
import com.github.creoii.greatbigworld.block.thatch.ThatchBlock;
import com.github.creoii.greatbigworld.block.thatch.ThatchSlabBlock;
import com.github.creoii.greatbigworld.block.thatch.ThatchStairsBlock;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.sapling.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.List;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class GBWBlocks implements Register {
    //region Mahogany Wood
    public static final Block STRIPPED_MAHOGANY_LOG = new PillarBlock(CBlockSettings.create().strength(2f).instrument(Instrument.BASS).sounds(BlockSoundGroup.WOOD).mapColor(MapColor.TERRACOTTA_ORANGE).burnable());
    public static final Block MAHOGANY_LOG = new PillarBlock(CBlockSettings.create().mapColor((state) -> {
        return state.get(PillarBlock.AXIS) == Direction.Axis.Y ? MapColor.TERRACOTTA_ORANGE : MapColor.TERRACOTTA_BROWN;
    }).strength(2f).instrument(Instrument.BASS).sounds(BlockSoundGroup.WOOD).burnable());
    public static final Block STRIPPED_MAHOGANY_WOOD = new PillarBlock(CBlockSettings.copy(STRIPPED_MAHOGANY_LOG));
    public static final Block MAHOGANY_WOOD = new PillarBlock(CBlockSettings.copy(STRIPPED_MAHOGANY_LOG).mapColor(MapColor.TERRACOTTA_BROWN));
    public static final Block MAHOGANY_PLANKS = new Block(CBlockSettings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2f, 3f).sounds(BlockSoundGroup.WOOD).burnable());
    public static final Block MAHOGANY_SLAB = new SlabBlock(CBlockSettings.create().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(Instrument.BASS).strength(2f, 3f).sounds(BlockSoundGroup.WOOD).burnable());
    public static final Block MAHOGANY_STAIRS = new StairsBlock(MAHOGANY_PLANKS.getDefaultState(), CBlockSettings.copy(MAHOGANY_PLANKS));
    public static final Block MAHOGANY_FENCE = new FenceBlock(CBlockSettings.create().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(Instrument.BASS).strength(2f, 3f).burnable().sounds(BlockSoundGroup.WOOD));
    public static final Block MAHOGANY_FENCE_GATE = new FenceGateBlock(CBlockSettings.create().mapColor(MapColor.TERRACOTTA_ORANGE).solid().instrument(Instrument.BASS).strength(2f, 3f).burnable(), GBWWoodTypes.MAHOGANY);
    public static final Block MAHOGANY_BUTTON = new ButtonBlock(CBlockSettings.create().strength(.5f).noCollision().pistonBehavior(PistonBehavior.DESTROY), GBWBlockSetTypes.MAHOGANY, 30, true);
    public static final Block MAHOGANY_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, CBlockSettings.create().mapColor(MapColor.TERRACOTTA_ORANGE).solid().instrument(Instrument.BASS).burnable().noCollision().strength(.5f).pistonBehavior(PistonBehavior.DESTROY), GBWBlockSetTypes.MAHOGANY);
    public static final Block MAHOGANY_DOOR = new DoorBlock(CBlockSettings.create().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(Instrument.BASS).strength(3f).nonOpaque().burnable().pistonBehavior(PistonBehavior.DESTROY), GBWBlockSetTypes.MAHOGANY);
    public static final Block MAHOGANY_TRAPDOOR = new TrapdoorBlock(CBlockSettings.create().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(Instrument.BASS).strength(3f).nonOpaque().allowsSpawning((state, world, pos, type) -> {
        return false;
    }).burnable(), GBWBlockSetTypes.MAHOGANY);
    public static final Block MAHOGANY_SIGN = new SignBlock(CBlockSettings.copy(Blocks.OAK_SIGN).mapColor(MapColor.TERRACOTTA_ORANGE), GBWWoodTypes.MAHOGANY);
    public static final Block MAHOGANY_WALL_SIGN = new WallSignBlock(CBlockSettings.copy(Blocks.OAK_WALL_SIGN).mapColor(MapColor.TERRACOTTA_ORANGE).dropsLike(MAHOGANY_SIGN), GBWWoodTypes.MAHOGANY);
    public static final Block MAHOGANY_HANGING_SIGN = new HangingSignBlock(CBlockSettings.copy(Blocks.OAK_HANGING_SIGN).mapColor(MapColor.TERRACOTTA_ORANGE), GBWWoodTypes.MAHOGANY);
    public static final Block MAHOGANY_WALL_HANGING_SIGN = new WallHangingSignBlock(CBlockSettings.copy(MAHOGANY_HANGING_SIGN).dropsLike(MAHOGANY_HANGING_SIGN), GBWWoodTypes.MAHOGANY);
    public static final Block MAHOGANY_LEAVES = new LeavesBlock(CBlockSettings.copy(Blocks.OAK_LEAVES).fireSettings(30, 60));
    public static final Block MAHOGANY_SAPLING = new SaplingBlock(new MahoganySaplingGenerator(), CBlockSettings.copy(Blocks.JUNGLE_SAPLING));
    public static final Block POTTED_MAHOGANY_SAPLING = new FlowerPotBlock(MAHOGANY_SAPLING, CBlockSettings.copy(Blocks.FLOWER_POT));
    //endregion
    //region Aspen Wood
    public static final Block STRIPPED_ASPEN_LOG = new PillarBlock(CBlockSettings.create().strength(2f).instrument(Instrument.BASS).sounds(BlockSoundGroup.WOOD).mapColor(MapColor.PALE_YELLOW).burnable());
    public static final Block ASPEN_LOG = new PillarBlock(CBlockSettings.create().mapColor((state) -> {
        return state.get(PillarBlock.AXIS) == Direction.Axis.Y ? MapColor.PALE_YELLOW : MapColor.OFF_WHITE;
    }).strength(2f).instrument(Instrument.BASS).sounds(BlockSoundGroup.WOOD).burnable());
    public static final Block STRIPPED_ASPEN_WOOD = new PillarBlock(CBlockSettings.copy(STRIPPED_ASPEN_LOG));
    public static final Block ASPEN_WOOD = new PillarBlock(CBlockSettings.copy(STRIPPED_ASPEN_LOG).mapColor(MapColor.OFF_WHITE));
    public static final Block ASPEN_PLANKS = new Block(CBlockSettings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2f, 3f).sounds(BlockSoundGroup.WOOD).burnable());
    public static final Block ASPEN_SLAB = new SlabBlock(CBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.BASS).strength(2f, 3f).sounds(BlockSoundGroup.WOOD).burnable());
    public static final Block ASPEN_STAIRS = new StairsBlock(ASPEN_PLANKS.getDefaultState(), CBlockSettings.copy(ASPEN_PLANKS));
    public static final Block ASPEN_FENCE = new FenceBlock(CBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.BASS).strength(2f, 3f).burnable().sounds(BlockSoundGroup.WOOD));
    public static final Block ASPEN_FENCE_GATE = new FenceGateBlock(CBlockSettings.create().mapColor(MapColor.PALE_YELLOW).solid().instrument(Instrument.BASS).strength(2f, 3f).burnable(), GBWWoodTypes.ASPEN);
    public static final Block ASPEN_BUTTON = new ButtonBlock(CBlockSettings.create().strength(.5f).noCollision().pistonBehavior(PistonBehavior.DESTROY), GBWBlockSetTypes.ASPEN, 30, true);
    public static final Block ASPEN_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, CBlockSettings.create().mapColor(MapColor.PALE_YELLOW).solid().instrument(Instrument.BASS).burnable().noCollision().strength(.5f).pistonBehavior(PistonBehavior.DESTROY), GBWBlockSetTypes.ASPEN);
    public static final Block ASPEN_DOOR = new DoorBlock(CBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.BASS).strength(3f).nonOpaque().burnable().pistonBehavior(PistonBehavior.DESTROY), GBWBlockSetTypes.ASPEN);
    public static final Block ASPEN_TRAPDOOR = new TrapdoorBlock(CBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.BASS).strength(3f).nonOpaque().allowsSpawning((state, world, pos, type) -> {
        return false;
    }).burnable(), GBWBlockSetTypes.ASPEN);
    public static final Block ASPEN_SIGN = new SignBlock(CBlockSettings.copy(Blocks.OAK_SIGN).mapColor(MapColor.PALE_YELLOW), GBWWoodTypes.ASPEN);
    public static final Block ASPEN_WALL_SIGN = new WallSignBlock(CBlockSettings.copy(Blocks.OAK_WALL_SIGN).mapColor(MapColor.PALE_YELLOW).dropsLike(ASPEN_SIGN), GBWWoodTypes.ASPEN);
    public static final Block ASPEN_HANGING_SIGN = new HangingSignBlock(CBlockSettings.copy(Blocks.OAK_HANGING_SIGN).mapColor(MapColor.PALE_YELLOW), GBWWoodTypes.ASPEN);
    public static final Block ASPEN_WALL_HANGING_SIGN = new WallHangingSignBlock(CBlockSettings.copy(ASPEN_HANGING_SIGN).dropsLike(ASPEN_HANGING_SIGN), GBWWoodTypes.ASPEN);
    public static final Block YELLOW_ASPEN_LEAVES = new LeavesBlock(CBlockSettings.copy(Blocks.OAK_LEAVES).mapColor(MapColor.YELLOW).fireSettings(30, 60));
    public static final Block YELLOW_ASPEN_LEAF_PILE = new LeafPileBlock(CBlockSettings.create().mapColor(MapColor.YELLOW).strength(.1f).sounds(BlockSoundGroup.GRASS).nonOpaque().noCollision().fireSettings(30, 60));
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
    public static final Block COBBLESTONE_BRICK_WALL = new WallBlock(CBlockSettings.copy(Blocks.COBBLESTONE_WALL).solid());
    public static final Block CHISELED_COBBLESTONE_BRICKS = new Block(CBlockSettings.copy(COBBLESTONE_BRICKS));
    public static final Block COBBLESTONE_BRICK_PILLAR = new PillarBlock(CBlockSettings.copy(COBBLESTONE_BRICKS));
    public static final Block COBBLESTONE_TILES = new Block(CBlockSettings.copy(COBBLESTONE_BRICKS));
    public static final Block MOSSY_COBBLESTONE_BRICKS = new Block(CBlockSettings.copy(Blocks.MOSSY_COBBLESTONE));
    public static final Block MOSSY_COBBLESTONE_BRICK_STAIRS = new StairsBlock(MOSSY_COBBLESTONE_BRICKS.getDefaultState(), CBlockSettings.copy(Blocks.MOSSY_COBBLESTONE_STAIRS));
    public static final Block MOSSY_COBBLESTONE_BRICK_SLAB = new SlabBlock(CBlockSettings.copy(Blocks.MOSSY_COBBLESTONE_SLAB));
    public static final Block MOSSY_COBBLESTONE_BRICK_WALL = new WallBlock(CBlockSettings.copy(Blocks.MOSSY_COBBLESTONE_WALL).solid());
    public static final Block MOSSY_COBBLESTONE_TILES = new Block(CBlockSettings.copy(MOSSY_COBBLESTONE_BRICKS));
    //endregion
    //region Stained Calcite
    public static final Block WHITE_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_RED));
    public static final Block LIGHT_GRAY_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY));
    public static final Block GRAY_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_GRAY));
    public static final Block BLACK_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_BLACK));
    public static final Block BROWN_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_BROWN));
    public static final Block RED_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_RED));
    public static final Block ORANGE_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final Block YELLOW_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_YELLOW));
    public static final Block LIME_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_LIME));
    public static final Block GREEN_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_GREEN));
    public static final Block CYAN_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_CYAN));
    public static final Block LIGHT_BLUE_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE));
    public static final Block BLUE_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_BLACK));
    public static final Block PURPLE_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_PURPLE));
    public static final Block MAGENTA_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_MAGENTA));
    public static final Block PINK_STAINED_CALCITE = new Block(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.TERRACOTTA_PINK));
    //endregion
    //region Thatch
    public static final Block TRIMMED_BEACHGRASS_THATCH = new PillarBlock(CBlockSettings.create().mapColor(MapColor.PALE_YELLOW).strength(.5f).sounds(BlockSoundGroup.GRASS).nonOpaque().fireSettings(70, 140).instrument(Instrument.BANJO));
    public static final Block BEACHGRASS_THATCH = new ThatchBlock(CBlockSettings.copy(TRIMMED_BEACHGRASS_THATCH).fireSettings(80, 160), TRIMMED_BEACHGRASS_THATCH);
    public static final Block TRIMMED_BEACHGRASS_THATCH_STAIRS = new StairsBlock(TRIMMED_BEACHGRASS_THATCH.getDefaultState(), CBlockSettings.copy(TRIMMED_BEACHGRASS_THATCH).fireSettings(70, 140));
    public static final Block BEACHGRASS_THATCH_STAIRS = new ThatchStairsBlock(CBlockSettings.copy(BEACHGRASS_THATCH).fireSettings(80, 160), BEACHGRASS_THATCH.getDefaultState(), TRIMMED_BEACHGRASS_THATCH_STAIRS);
    public static final Block TRIMMED_BEACHGRASS_THATCH_SLAB = new SlabBlock(CBlockSettings.copy(TRIMMED_BEACHGRASS_THATCH).fireSettings(70, 140));
    public static final Block BEACHGRASS_THATCH_SLAB = new ThatchSlabBlock(CBlockSettings.copy(BEACHGRASS_THATCH).fireSettings(80, 160), TRIMMED_BEACHGRASS_THATCH_SLAB);
    public static final Block TRIMMED_BAMBOO_THATCH = new PillarBlock(CBlockSettings.create().mapColor(MapColor.GREEN).strength(.5f).sounds(BlockSoundGroup.GRASS).nonOpaque().fireSettings(70, 140).instrument(Instrument.BANJO));
    public static final Block BAMBOO_THATCH = new ThatchBlock(CBlockSettings.copy(TRIMMED_BAMBOO_THATCH).fireSettings(80, 160), TRIMMED_BAMBOO_THATCH);
    public static final Block TRIMMED_BAMBOO_THATCH_STAIRS = new StairsBlock(TRIMMED_BAMBOO_THATCH.getDefaultState(), CBlockSettings.copy(TRIMMED_BAMBOO_THATCH).fireSettings(70, 140));
    public static final Block BAMBOO_THATCH_STAIRS = new ThatchStairsBlock(CBlockSettings.copy(BAMBOO_THATCH).fireSettings(80, 160), BAMBOO_THATCH.getDefaultState(), TRIMMED_BAMBOO_THATCH_STAIRS);
    public static final Block TRIMMED_BAMBOO_THATCH_SLAB = new SlabBlock(CBlockSettings.copy(TRIMMED_BAMBOO_THATCH).fireSettings(70, 140));
    public static final Block BAMBOO_THATCH_SLAB = new ThatchSlabBlock(CBlockSettings.copy(BAMBOO_THATCH).fireSettings(80, 160), TRIMMED_BAMBOO_THATCH_SLAB);
    public static final Block TRIMMED_GRASS_THATCH = new PillarBlock(CBlockSettings.create().mapColor(MapColor.PALE_GREEN).strength(.5f).sounds(BlockSoundGroup.GRASS).nonOpaque().fireSettings(70, 140).instrument(Instrument.BANJO));
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
    public static final Block LAVAROCK_WALL = new WallBlock(CBlockSettings.copy(LAVAROCK).solid());
    public static final Block LAVAROCK_BRICKS = new Block(CBlockSettings.copy(Blocks.STONE_BRICKS).mapColor(MapColor.BLACK));
    public static final Block LAVAROCK_BRICK_STAIRS = new StairsBlock(LAVAROCK_BRICKS.getDefaultState(), CBlockSettings.copy(LAVAROCK_BRICKS));
    public static final Block LAVAROCK_BRICK_SLAB = new SlabBlock(CBlockSettings.copy(LAVAROCK_BRICKS));
    public static final Block LAVAROCK_BRICK_WALL = new WallBlock(CBlockSettings.copy(LAVAROCK_BRICKS).solid());
    public static final Block CHISELED_LAVAROCK_BRICKS = new Block(CBlockSettings.copy(LAVAROCK_BRICKS));
    //endregion
    //region Acai
    public static final Block STRIPPED_ACAI_LOG = new PillarBlock(CBlockSettings.create().strength(2f).instrument(Instrument.BASS).sounds(BlockSoundGroup.WOOD).mapColor(MapColor.TERRACOTTA_YELLOW).burnable());
    public static final Block ACAI_LOG = new PillarBlock(CBlockSettings.create().mapColor((state) -> {
        return state.get(PillarBlock.AXIS) == Direction.Axis.Y ? MapColor.TERRACOTTA_YELLOW : MapColor.TERRACOTTA_BROWN;
    }).strength(2f).instrument(Instrument.BASS).sounds(BlockSoundGroup.WOOD).burnable());
    public static final Block STRIPPED_ACAI_WOOD = new PillarBlock(CBlockSettings.copy(STRIPPED_ACAI_LOG));
    public static final Block ACAI_WOOD = new PillarBlock(CBlockSettings.copy(STRIPPED_ACAI_LOG).mapColor(MapColor.TERRACOTTA_BROWN));
    public static final Block ACAI_PLANKS = new Block(CBlockSettings.create().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(Instrument.BASS).strength(2f, 3f).sounds(BlockSoundGroup.WOOD).burnable());
    public static final Block ACAI_SLAB = new SlabBlock(CBlockSettings.create().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(Instrument.BASS).strength(2f, 3f).sounds(BlockSoundGroup.WOOD).burnable());
    public static final Block ACAI_STAIRS = new StairsBlock(ACAI_PLANKS.getDefaultState(), CBlockSettings.copy(ACAI_PLANKS));
    public static final Block ACAI_FENCE = new FenceBlock(CBlockSettings.create().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(Instrument.BASS).strength(2f, 3f).burnable().sounds(BlockSoundGroup.WOOD));
    public static final Block ACAI_FENCE_GATE = new FenceGateBlock(CBlockSettings.create().mapColor(MapColor.TERRACOTTA_YELLOW).solid().instrument(Instrument.BASS).strength(2f, 3f).burnable(), GBWWoodTypes.ACAI);
    public static final Block ACAI_BUTTON = new ButtonBlock(CBlockSettings.create().strength(.5f).noCollision().pistonBehavior(PistonBehavior.DESTROY), GBWBlockSetTypes.ACAI, 30, true);
    public static final Block ACAI_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, CBlockSettings.create().mapColor(MapColor.TERRACOTTA_YELLOW).solid().instrument(Instrument.BASS).burnable().noCollision().strength(.5f).pistonBehavior(PistonBehavior.DESTROY), GBWBlockSetTypes.ACAI);
    public static final Block ACAI_DOOR = new DoorBlock(CBlockSettings.create().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(Instrument.BASS).strength(3f).nonOpaque().burnable().pistonBehavior(PistonBehavior.DESTROY), GBWBlockSetTypes.ACAI);
    public static final Block ACAI_TRAPDOOR = new TrapdoorBlock(CBlockSettings.create().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(Instrument.BASS).strength(3f).nonOpaque().allowsSpawning((state, world, pos, type) -> {
        return false;
    }).burnable(), GBWBlockSetTypes.ACAI);
    public static final Block ACAI_SIGN = new SignBlock(CBlockSettings.copy(Blocks.OAK_SIGN).mapColor(MapColor.TERRACOTTA_YELLOW), GBWWoodTypes.ACAI);
    public static final Block ACAI_WALL_SIGN = new WallSignBlock(CBlockSettings.copy(Blocks.OAK_WALL_SIGN).mapColor(MapColor.TERRACOTTA_YELLOW).dropsLike(ACAI_SIGN), GBWWoodTypes.ACAI);
    public static final Block ACAI_HANGING_SIGN = new HangingSignBlock(CBlockSettings.copy(Blocks.OAK_HANGING_SIGN).mapColor(MapColor.TERRACOTTA_YELLOW), GBWWoodTypes.ACAI);
    public static final Block ACAI_WALL_HANGING_SIGN = new WallHangingSignBlock(CBlockSettings.copy(ACAI_HANGING_SIGN).dropsLike(ACAI_HANGING_SIGN), GBWWoodTypes.ACAI);
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
    public static final Block ELDER_PRISMARINE_WALL = new WallBlock(CBlockSettings.copy(ELDER_PRISMARINE).solid());
    public static final Block ELDER_PRISMARINE_BRICKS = new Block(CBlockSettings.copy(Blocks.PRISMARINE_BRICKS).mapColor(MapColor.PALE_YELLOW));
    public static final Block ELDER_PRISMARINE_BRICK_SLAB = new SlabBlock(CBlockSettings.copy(ELDER_PRISMARINE_BRICKS));
    public static final Block ELDER_PRISMARINE_BRICK_STAIRS = new StairsBlock(ELDER_PRISMARINE_BRICKS.getDefaultState(), CBlockSettings.copy(ELDER_PRISMARINE_BRICKS));
    public static final Block ELDER_PRISMARINE_BRICK_WALL = new WallBlock(CBlockSettings.copy(ELDER_PRISMARINE_BRICKS).solid());
    public static final Block ELDER_SEA_LANTERN = new Block(CBlockSettings.copy(Blocks.SEA_LANTERN).mapColor(MapColor.PALE_YELLOW).luminance(state -> 14));
    //endregion
    //region Wisteria Wood
    public static final Block STRIPPED_WISTERIA_LOG = new PillarBlock(CBlockSettings.create().strength(2f).instrument(Instrument.BASS).sounds(BlockSoundGroup.WOOD).mapColor(MapColor.OFF_WHITE).burnable());
    public static final Block WISTERIA_LOG = new PillarBlock(CBlockSettings.create().mapColor((state) -> {
        return state.get(PillarBlock.AXIS) == Direction.Axis.Y ? MapColor.OFF_WHITE : MapColor.TERRACOTTA_GRAY;
    }).strength(2f).instrument(Instrument.BASS).sounds(BlockSoundGroup.WOOD).burnable());
    public static final Block STRIPPED_WISTERIA_WOOD = new PillarBlock(CBlockSettings.copy(STRIPPED_WISTERIA_LOG));
    public static final Block WISTERIA_WOOD = new PillarBlock(CBlockSettings.copy(STRIPPED_WISTERIA_LOG).mapColor(MapColor.TERRACOTTA_GRAY));
    public static final Block WISTERIA_PLANKS = new Block(CBlockSettings.create().mapColor(MapColor.OFF_WHITE).instrument(Instrument.BASS).strength(2f, 3f).sounds(BlockSoundGroup.WOOD).burnable());
    public static final Block WISTERIA_SLAB = new SlabBlock(CBlockSettings.create().mapColor(MapColor.OFF_WHITE).instrument(Instrument.BASS).strength(2f, 3f).sounds(BlockSoundGroup.WOOD).burnable());
    public static final Block WISTERIA_STAIRS = new StairsBlock(WISTERIA_PLANKS.getDefaultState(), CBlockSettings.copy(WISTERIA_PLANKS));
    public static final Block WISTERIA_FENCE = new FenceBlock(CBlockSettings.create().mapColor(MapColor.OFF_WHITE).instrument(Instrument.BASS).strength(2f, 3f).burnable().sounds(BlockSoundGroup.WOOD));
    public static final Block WISTERIA_FENCE_GATE = new FenceGateBlock(CBlockSettings.create().mapColor(MapColor.OFF_WHITE).solid().instrument(Instrument.BASS).strength(2f, 3f).burnable(), GBWWoodTypes.WISTERIA);
    public static final Block WISTERIA_BUTTON = new ButtonBlock(CBlockSettings.create().strength(.5f).noCollision().pistonBehavior(PistonBehavior.DESTROY), GBWBlockSetTypes.WISTERIA, 30, true);
    public static final Block WISTERIA_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, CBlockSettings.create().mapColor(MapColor.OFF_WHITE).solid().instrument(Instrument.BASS).burnable().noCollision().strength(.5f).pistonBehavior(PistonBehavior.DESTROY), GBWBlockSetTypes.WISTERIA);
    public static final Block WISTERIA_DOOR = new DoorBlock(CBlockSettings.create().mapColor(MapColor.OFF_WHITE).instrument(Instrument.BASS).strength(3f).nonOpaque().burnable().pistonBehavior(PistonBehavior.DESTROY), GBWBlockSetTypes.WISTERIA);
    public static final Block WISTERIA_TRAPDOOR = new TrapdoorBlock(CBlockSettings.create().mapColor(MapColor.OFF_WHITE).instrument(Instrument.BASS).strength(3f).nonOpaque().allowsSpawning((state, world, pos, type) -> {
        return false;
    }).burnable(), GBWBlockSetTypes.WISTERIA);
    public static final Block WISTERIA_SIGN = new SignBlock(CBlockSettings.copy(Blocks.OAK_SIGN).mapColor(MapColor.OFF_WHITE), GBWWoodTypes.WISTERIA);
    public static final Block WISTERIA_WALL_SIGN = new WallSignBlock(CBlockSettings.copy(Blocks.OAK_WALL_SIGN).mapColor(MapColor.OFF_WHITE).dropsLike(WISTERIA_SIGN), GBWWoodTypes.WISTERIA);
    public static final Block WISTERIA_HANGING_SIGN = new HangingSignBlock(CBlockSettings.copy(Blocks.OAK_HANGING_SIGN).mapColor(MapColor.OFF_WHITE), GBWWoodTypes.WISTERIA);
    public static final Block WISTERIA_WALL_HANGING_SIGN = new WallHangingSignBlock(CBlockSettings.copy(WISTERIA_HANGING_SIGN).dropsLike(WISTERIA_HANGING_SIGN), GBWWoodTypes.WISTERIA);
    public static final Block WHITE_WISTERIA_LEAVES = new LeavesBlock(CBlockSettings.copy(GREEN_ASPEN_LEAVES).mapColor(MapColor.OFF_WHITE).sounds(BlockSoundGroup.CHERRY_LEAVES));
    public static final Block YELLOW_WISTERIA_LEAVES = new LeavesBlock(CBlockSettings.copy(GREEN_ASPEN_LEAVES).mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.CHERRY_LEAVES));
    public static final Block BLUE_WISTERIA_LEAVES = new LeavesBlock(CBlockSettings.copy(GREEN_ASPEN_LEAVES).mapColor(MapColor.LIGHT_BLUE).sounds(BlockSoundGroup.CHERRY_LEAVES));
    public static final Block PINK_WISTERIA_LEAVES = new LeavesBlock(CBlockSettings.copy(GREEN_ASPEN_LEAVES).mapColor(MapColor.PINK).sounds(BlockSoundGroup.CHERRY_LEAVES));
    public static final Block PURPLE_WISTERIA_LEAVES = new LeavesBlock(CBlockSettings.copy(GREEN_ASPEN_LEAVES).mapColor(MapColor.PURPLE).sounds(BlockSoundGroup.CHERRY_LEAVES));
    public static final Block HANGING_WHITE_WISTERIA_LEAVES = new HangingLeavesBlock(CBlockSettings.copy(WHITE_WISTERIA_LEAVES));
    public static final Block HANGING_YELLOW_WISTERIA_LEAVES = new HangingLeavesBlock(CBlockSettings.copy(YELLOW_WISTERIA_LEAVES));
    public static final Block HANGING_BLUE_WISTERIA_LEAVES = new HangingLeavesBlock(CBlockSettings.copy(BLUE_WISTERIA_LEAVES));
    public static final Block HANGING_PINK_WISTERIA_LEAVES = new HangingLeavesBlock(CBlockSettings.copy(PINK_WISTERIA_LEAVES));
    public static final Block HANGING_PURPLE_WISTERIA_LEAVES = new HangingLeavesBlock(CBlockSettings.copy(PURPLE_WISTERIA_LEAVES));
    public static final Block WHITE_WISTERIA_SAPLING = new SaplingBlock(new WisteriaSaplingGenerator(0), CBlockSettings.copy(GREEN_ASPEN_SAPLING).sounds(BlockSoundGroup.CHERRY_SAPLING));
    public static final Block YELLOW_WISTERIA_SAPLING = new SaplingBlock(new WisteriaSaplingGenerator(1), CBlockSettings.copy(GREEN_ASPEN_SAPLING).sounds(BlockSoundGroup.CHERRY_SAPLING));
    public static final Block BLUE_WISTERIA_SAPLING = new SaplingBlock(new WisteriaSaplingGenerator(2), CBlockSettings.copy(GREEN_ASPEN_SAPLING).sounds(BlockSoundGroup.CHERRY_SAPLING));
    public static final Block PINK_WISTERIA_SAPLING = new SaplingBlock(new WisteriaSaplingGenerator(3), CBlockSettings.copy(GREEN_ASPEN_SAPLING).sounds(BlockSoundGroup.CHERRY_SAPLING));
    public static final Block PURPLE_WISTERIA_SAPLING = new SaplingBlock(new WisteriaSaplingGenerator(4), CBlockSettings.copy(GREEN_ASPEN_SAPLING).sounds(BlockSoundGroup.CHERRY_SAPLING));
    public static final Block POTTED_WHITE_WISTERIA_SAPLING = new FlowerPotBlock(WHITE_WISTERIA_SAPLING, CBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block POTTED_YELLOW_WISTERIA_SAPLING = new FlowerPotBlock(YELLOW_WISTERIA_SAPLING, CBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block POTTED_BLUE_WISTERIA_SAPLING = new FlowerPotBlock(BLUE_WISTERIA_SAPLING, CBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block POTTED_PINK_WISTERIA_SAPLING = new FlowerPotBlock(PINK_WISTERIA_SAPLING, CBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block POTTED_PURPLE_WISTERIA_SAPLING = new FlowerPotBlock(PURPLE_WISTERIA_SAPLING, CBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block WHITE_PETALS = new FlowerbedBlock(CBlockSettings.copy(Blocks.PINK_PETALS).fireSettings(60, 100));
    public static final Block YELLOW_PETALS = new FlowerbedBlock(CBlockSettings.copy(Blocks.PINK_PETALS).fireSettings(60, 100));
    public static final Block BLUE_PETALS = new FlowerbedBlock(CBlockSettings.copy(Blocks.PINK_PETALS).fireSettings(60, 100));
    public static final Block PURPLE_PETALS = new FlowerbedBlock(CBlockSettings.copy(Blocks.PINK_PETALS).fireSettings(60, 100));
    //endregion
    //region Travertine
    public static final Block PEACH_TRAVERTINE = new PillarBlock(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.ORANGE));
    public static final Block GOLDEN_TRAVERTINE = new PillarBlock(CBlockSettings.copy(Blocks.CALCITE).mapColor(MapColor.PALE_YELLOW));
    public static final Block IVORY_TRAVERTINE = new PillarBlock(CBlockSettings.copy(Blocks.CALCITE));
    public static final Block POLISHED_PEACH_TRAVERTINE = new Block(CBlockSettings.copy(PEACH_TRAVERTINE).strength(1f));
    public static final Block POLISHED_GOLDEN_TRAVERTINE = new Block(CBlockSettings.copy(GOLDEN_TRAVERTINE).strength(1f));
    public static final Block POLISHED_IVORY_TRAVERTINE = new Block(CBlockSettings.copy(IVORY_TRAVERTINE).strength(1f));
    public static final Block POLISHED_PEACH_TRAVERTINE_TILES = new Block(CBlockSettings.copy(POLISHED_PEACH_TRAVERTINE));
    public static final Block POLISHED_GOLDEN_TRAVERTINE_TILES = new Block(CBlockSettings.copy(POLISHED_GOLDEN_TRAVERTINE));
    public static final Block POLISHED_IVORY_TRAVERTINE_TILES = new Block(CBlockSettings.copy(POLISHED_IVORY_TRAVERTINE));
    public static final Block STEAMING_IVORY_TRAVERTINE = new SteamingTravertineBlock(CBlockSettings.copy(IVORY_TRAVERTINE));
    //endregion
    //region Red Rock
    public static final Block RED_ROCK = new Block(CBlockSettings.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_RED));
    public static final Block RED_ROCK_SLAB = new SlabBlock(CBlockSettings.copy(RED_ROCK));
    public static final Block RED_ROCK_STAIRS = new StairsBlock(RED_ROCK.getDefaultState(), CBlockSettings.copy(RED_ROCK));
    public static final Block RED_ROCK_WALL = new WallBlock(CBlockSettings.copy(RED_ROCK).solid());
    public static final Block POLISHED_RED_ROCK = new Block(CBlockSettings.copy(RED_ROCK));
    public static final Block POLISHED_RED_ROCK_SLAB = new SlabBlock(CBlockSettings.copy(POLISHED_RED_ROCK));
    public static final Block POLISHED_RED_ROCK_STAIRS = new StairsBlock(POLISHED_RED_ROCK.getDefaultState(), CBlockSettings.copy(POLISHED_RED_ROCK));
    public static final Block POLISHED_RED_ROCK_WALL = new WallBlock(CBlockSettings.copy(POLISHED_RED_ROCK).solid());
    public static final Block RED_ROCK_BRICKS = new Block(CBlockSettings.copy(RED_ROCK));
    public static final Block RED_ROCK_BRICK_SLAB = new SlabBlock(CBlockSettings.copy(RED_ROCK_BRICKS));
    public static final Block RED_ROCK_BRICK_STAIRS = new StairsBlock(RED_ROCK_BRICKS.getDefaultState(), CBlockSettings.copy(RED_ROCK_BRICKS));
    public static final Block RED_ROCK_BRICK_WALL = new WallBlock(CBlockSettings.copy(RED_ROCK_BRICKS).solid());
    public static final Block CRACKED_RED_ROCK_BRICKS = new Block(CBlockSettings.copy(RED_ROCK_BRICKS));
    public static final Block CHISELED_RED_ROCK_BRICKS = new Block(CBlockSettings.copy(RED_ROCK_BRICKS));
    public static final Block RED_ROCK_PILLAR = new PillarBlock(CBlockSettings.copy(RED_ROCK));
    public static final Block RED_ROCK_DIRT = new Block(CBlockSettings.copy(RED_ROCK));
    public static final Block DRY_DIRT = new Block(CBlockSettings.copy(Blocks.PACKED_MUD));
    //endregion
    //region Pine Wood
    public static final Block STRIPPED_PINE_LOG = new PillarBlock(CBlockSettings.create().strength(2f).instrument(Instrument.BASS).sounds(BlockSoundGroup.WOOD).mapColor(MapColor.TERRACOTTA_BROWN).burnable());
    public static final Block PINE_LOG = new PillarBlock(CBlockSettings.create().mapColor((state) -> {
        return state.get(PillarBlock.AXIS) == Direction.Axis.Y ? MapColor.TERRACOTTA_BROWN : MapColor.BROWN;
    }).strength(2f).instrument(Instrument.BASS).sounds(BlockSoundGroup.WOOD).burnable());
    public static final Block STRIPPED_PINE_WOOD = new PillarBlock(CBlockSettings.copy(STRIPPED_PINE_LOG));
    public static final Block PINE_WOOD = new PillarBlock(CBlockSettings.copy(STRIPPED_PINE_LOG).mapColor(MapColor.BROWN));
    public static final Block PINE_PLANKS = new Block(CBlockSettings.create().mapColor(MapColor.TERRACOTTA_BROWN).instrument(Instrument.BASS).strength(2f, 3f).sounds(BlockSoundGroup.WOOD).burnable());
    public static final Block PINE_SLAB = new SlabBlock(CBlockSettings.create().mapColor(MapColor.TERRACOTTA_BROWN).instrument(Instrument.BASS).strength(2f, 3f).sounds(BlockSoundGroup.WOOD).burnable());
    public static final Block PINE_STAIRS = new StairsBlock(PINE_PLANKS.getDefaultState(), CBlockSettings.copy(PINE_PLANKS));
    public static final Block PINE_FENCE = new FenceBlock(CBlockSettings.create().mapColor(MapColor.TERRACOTTA_BROWN).instrument(Instrument.BASS).strength(2f, 3f).burnable().sounds(BlockSoundGroup.WOOD));
    public static final Block PINE_FENCE_GATE = new FenceGateBlock(CBlockSettings.create().mapColor(MapColor.TERRACOTTA_BROWN).solid().instrument(Instrument.BASS).strength(2f, 3f).burnable(), GBWWoodTypes.PINE);
    public static final Block PINE_BUTTON = new ButtonBlock(CBlockSettings.create().strength(.5f).noCollision().pistonBehavior(PistonBehavior.DESTROY), GBWBlockSetTypes.PINE, 30, true);
    public static final Block PINE_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, CBlockSettings.create().mapColor(MapColor.TERRACOTTA_BROWN).solid().instrument(Instrument.BASS).burnable().noCollision().strength(.5f).pistonBehavior(PistonBehavior.DESTROY), GBWBlockSetTypes.PINE);
    public static final Block PINE_DOOR = new DoorBlock(CBlockSettings.create().mapColor(MapColor.TERRACOTTA_BROWN).instrument(Instrument.BASS).strength(3f).nonOpaque().burnable().pistonBehavior(PistonBehavior.DESTROY), GBWBlockSetTypes.PINE);
    public static final Block PINE_TRAPDOOR = new TrapdoorBlock(CBlockSettings.create().mapColor(MapColor.TERRACOTTA_BROWN).instrument(Instrument.BASS).strength(3f).nonOpaque().allowsSpawning((state, world, pos, type) -> {
        return false;
    }).burnable(), GBWBlockSetTypes.PINE);
    public static final Block PINE_SIGN = new SignBlock(CBlockSettings.copy(Blocks.OAK_SIGN).mapColor(MapColor.TERRACOTTA_BROWN), GBWWoodTypes.PINE);
    public static final Block PINE_WALL_SIGN = new WallSignBlock(CBlockSettings.copy(Blocks.OAK_WALL_SIGN).mapColor(MapColor.TERRACOTTA_BROWN).dropsLike(PINE_SIGN), GBWWoodTypes.PINE);
    public static final Block PINE_HANGING_SIGN = new HangingSignBlock(CBlockSettings.copy(Blocks.OAK_HANGING_SIGN).mapColor(MapColor.TERRACOTTA_BROWN), GBWWoodTypes.PINE);
    public static final Block PINE_WALL_HANGING_SIGN = new WallHangingSignBlock(CBlockSettings.copy(PINE_HANGING_SIGN).dropsLike(PINE_HANGING_SIGN), GBWWoodTypes.PINE);
    public static final Block PINE_LEAVES = new LeavesBlock(CBlockSettings.copy(Blocks.OAK_LEAVES).mapColor(MapColor.TERRACOTTA_BROWN));
    public static final Block PINE_SAPLING = new SaplingBlock(new WisteriaSaplingGenerator(4), CBlockSettings.copy(Blocks.OAK_SAPLING));
    public static final Block POTTED_PINE_SAPLING = new FlowerPotBlock(PINE_SAPLING, CBlockSettings.copy(Blocks.FLOWER_POT));
    //endregion
    //region Palo Verde Wood
    public static final Block STRIPPED_PALO_VERDE_LOG = new PillarBlock(CBlockSettings.create().strength(2f).instrument(Instrument.BASS).sounds(BlockSoundGroup.WOOD).mapColor(MapColor.TERRACOTTA_BROWN).burnable());
    public static final Block PALO_VERDE_LOG = new PillarBlock(CBlockSettings.create().mapColor((state) -> {
        return state.get(PillarBlock.AXIS) == Direction.Axis.Y ? MapColor.PALE_GREEN : MapColor.EMERALD_GREEN;
    }).strength(2f).instrument(Instrument.BASS).sounds(BlockSoundGroup.WOOD).burnable());
    public static final Block STRIPPED_PALO_VERDE_WOOD = new PillarBlock(CBlockSettings.copy(STRIPPED_PALO_VERDE_LOG));
    public static final Block PALO_VERDE_WOOD = new PillarBlock(CBlockSettings.copy(STRIPPED_PALO_VERDE_LOG).mapColor(MapColor.EMERALD_GREEN));
    public static final Block PALO_VERDE_PLANKS = new Block(CBlockSettings.create().mapColor(MapColor.PALE_GREEN).instrument(Instrument.BASS).strength(2f, 3f).sounds(BlockSoundGroup.WOOD).burnable());
    public static final Block PALO_VERDE_SLAB = new SlabBlock(CBlockSettings.create().mapColor(MapColor.PALE_GREEN).instrument(Instrument.BASS).strength(2f, 3f).sounds(BlockSoundGroup.WOOD).burnable());
    public static final Block PALO_VERDE_STAIRS = new StairsBlock(PALO_VERDE_PLANKS.getDefaultState(), CBlockSettings.copy(PALO_VERDE_PLANKS));
    public static final Block PALO_VERDE_FENCE = new FenceBlock(CBlockSettings.create().mapColor(MapColor.PALE_GREEN).instrument(Instrument.BASS).strength(2f, 3f).burnable().sounds(BlockSoundGroup.WOOD));
    public static final Block PALO_VERDE_FENCE_GATE = new FenceGateBlock(CBlockSettings.create().mapColor(MapColor.PALE_GREEN).solid().instrument(Instrument.BASS).strength(2f, 3f).burnable(), GBWWoodTypes.PALO_VERDE);
    public static final Block PALO_VERDE_BUTTON = new ButtonBlock(CBlockSettings.create().strength(.5f).noCollision().pistonBehavior(PistonBehavior.DESTROY), GBWBlockSetTypes.PALO_VERDE, 30, true);
    public static final Block PALO_VERDE_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, CBlockSettings.create().mapColor(MapColor.PALE_GREEN).solid().instrument(Instrument.BASS).burnable().noCollision().strength(.5f).pistonBehavior(PistonBehavior.DESTROY), GBWBlockSetTypes.PALO_VERDE);
    public static final Block PALO_VERDE_DOOR = new DoorBlock(CBlockSettings.create().mapColor(MapColor.PALE_GREEN).instrument(Instrument.BASS).strength(3f).nonOpaque().burnable().pistonBehavior(PistonBehavior.DESTROY), GBWBlockSetTypes.PALO_VERDE);
    public static final Block PALO_VERDE_TRAPDOOR = new TrapdoorBlock(CBlockSettings.create().mapColor(MapColor.PALE_GREEN).instrument(Instrument.BASS).strength(3f).nonOpaque().allowsSpawning((state, world, pos, type) -> {
        return false;
    }).burnable(), GBWBlockSetTypes.PALO_VERDE);
    public static final Block PALO_VERDE_SIGN = new SignBlock(CBlockSettings.copy(Blocks.OAK_SIGN).mapColor(MapColor.PALE_GREEN), GBWWoodTypes.PALO_VERDE);
    public static final Block PALO_VERDE_WALL_SIGN = new WallSignBlock(CBlockSettings.copy(Blocks.OAK_WALL_SIGN).mapColor(MapColor.PALE_GREEN).dropsLike(PALO_VERDE_SIGN), GBWWoodTypes.PALO_VERDE);
    public static final Block PALO_VERDE_HANGING_SIGN = new HangingSignBlock(CBlockSettings.copy(Blocks.OAK_HANGING_SIGN).mapColor(MapColor.PALE_GREEN), GBWWoodTypes.PALO_VERDE);
    public static final Block PALO_VERDE_WALL_HANGING_SIGN = new WallHangingSignBlock(CBlockSettings.copy(PALO_VERDE_HANGING_SIGN).dropsLike(PALO_VERDE_HANGING_SIGN), GBWWoodTypes.PALO_VERDE);
    public static final Block PALO_VERDE_LEAVES = new LeavesBlock(CBlockSettings.copy(Blocks.OAK_LEAVES).mapColor(MapColor.PALE_GREEN));
    public static final Block PALO_VERDE_SAPLING = new SaplingBlock(new WisteriaSaplingGenerator(4), CBlockSettings.copy(Blocks.OAK_SAPLING));
    public static final Block POTTED_PALO_VERDE_SAPLING = new FlowerPotBlock(PALO_VERDE_SAPLING, CBlockSettings.copy(Blocks.FLOWER_POT));
    //endregion
    //region Miscellaneous
    public static final Block ANTLER = new AntlerBlock();
    public static final Block TALL_HEATHER = new TallFlowerBlock(CBlockSettings.copy(Blocks.ROSE_BUSH).fireSettings(60, 100));
    public static final Block HEATHER = new FlowerBlock(StatusEffects.STRENGTH, 4, CBlockSettings.copy(Blocks.POPPY).fireSettings(60, 100));
    public static final Block POTTED_HEATHER = new FlowerPotBlock(HEATHER, CBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block ASTER = new FlowerBlock(StatusEffects.SATURATION, 7, CBlockSettings.copy(Blocks.POPPY).fireSettings(60, 100));
    public static final Block POTTED_ASTER = new FlowerPotBlock(ASTER, CBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block DRY_GRASS = new FernBlock(CBlockSettings.copy(Blocks.GRASS));
    public static final Block BEACHGRASS = new BeachgrassBlock();
    public static final Block TALL_BEACHGRASS = new TallBeachgrassBlock();
    public static final Block POTTED_BEACHGRASS = new FlowerPotBlock(BEACHGRASS, CBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block TROPICAL_FERN = new FernBlock(CBlockSettings.copy(Blocks.FERN).fireSettings(60, 100));
    public static final Block LARGE_TROPICAL_FERN = new TallPlantBlock(CBlockSettings.copy(Blocks.LARGE_FERN).fireSettings(60, 100));
    public static final Block POTTED_TROPICAL_FERN = new FlowerPotBlock(TROPICAL_FERN, CBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block NAUTILUS_FOSSIL = new Block(CBlockSettings.copy(Blocks.STONE));
    public static final Block WHITE_DELPHINIUM = new TallFlowerBlock(CBlockSettings.copy(Blocks.ROSE_BUSH).fireSettings(60, 100));
    public static final Block YELLOW_DELPHINIUM = new TallFlowerBlock(CBlockSettings.copy(Blocks.ROSE_BUSH).fireSettings(60, 100));
    public static final Block BLUE_DELPHINIUM = new TallFlowerBlock(CBlockSettings.copy(Blocks.ROSE_BUSH).fireSettings(60, 100));
    public static final Block PINK_DELPHINIUM = new TallFlowerBlock(CBlockSettings.copy(Blocks.ROSE_BUSH).fireSettings(60, 100));
    public static final Block PURPLE_DELPHINIUM = new TallFlowerBlock(CBlockSettings.copy(Blocks.ROSE_BUSH).fireSettings(60, 100));
    public static final Block GOLDEN_APPLE_LEAVES = new LeavesBlock(CBlockSettings.copy(Blocks.OAK_LEAVES).mapColor(MapColor.YELLOW).fireSettings(20, 25));
    public static final Block SULFUR_BLOCK = new SulfurBlock();
    public static final Block SAWMILL = new SawmillBlock();
    public static final Block KILN = new KilnBlock();
    //endregion

    @Override
    public void register() {
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_log"), MAHOGANY_LOG, new ItemRegistryHelper.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, Items.JUNGLE_BUTTON), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, Items.JUNGLE_LOG));
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "stripped_mahogany_log"), STRIPPED_MAHOGANY_LOG, MAHOGANY_LOG, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_wood"), MAHOGANY_WOOD, STRIPPED_MAHOGANY_LOG, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "stripped_mahogany_wood"), STRIPPED_MAHOGANY_WOOD, MAHOGANY_WOOD, ItemGroups.BUILDING_BLOCKS);
        StrippableBlockRegistry.register(MAHOGANY_LOG, STRIPPED_MAHOGANY_LOG);
        StrippableBlockRegistry.register(MAHOGANY_WOOD, STRIPPED_MAHOGANY_WOOD);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_planks"), MAHOGANY_PLANKS, STRIPPED_MAHOGANY_WOOD, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_stairs"), MAHOGANY_STAIRS, MAHOGANY_PLANKS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_slab"), MAHOGANY_SLAB, MAHOGANY_STAIRS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_fence"), MAHOGANY_FENCE, MAHOGANY_SLAB, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_fence_gate"), MAHOGANY_FENCE_GATE, MAHOGANY_FENCE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_door"), MAHOGANY_DOOR, MAHOGANY_FENCE_GATE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_trapdoor"), MAHOGANY_TRAPDOOR, MAHOGANY_DOOR, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_pressure_plate"), MAHOGANY_PRESSURE_PLATE, MAHOGANY_TRAPDOOR, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_button"), MAHOGANY_BUTTON, MAHOGANY_PRESSURE_PLATE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_sign"), MAHOGANY_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_wall_sign"), MAHOGANY_WALL_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_hanging_sign"), MAHOGANY_HANGING_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_wall_hanging_sign"), MAHOGANY_WALL_HANGING_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_leaves"), MAHOGANY_LEAVES);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mahogany_sapling"), MAHOGANY_SAPLING, new CItemSettings().compostingChance(.1f), Items.JUNGLE_SAPLING, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_mahogany_sapling"), POTTED_MAHOGANY_SAPLING);

        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "aspen_log"), ASPEN_LOG, new ItemRegistryHelper.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, Items.BIRCH_BUTTON), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, Items.BIRCH_LOG));
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "stripped_aspen_log"), STRIPPED_ASPEN_LOG, ASPEN_LOG, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "aspen_wood"), ASPEN_WOOD, STRIPPED_ASPEN_LOG, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "stripped_aspen_wood"), STRIPPED_ASPEN_WOOD, ASPEN_WOOD, ItemGroups.BUILDING_BLOCKS);
        StrippableBlockRegistry.register(ASPEN_LOG, STRIPPED_ASPEN_LOG);
        StrippableBlockRegistry.register(ASPEN_WOOD, STRIPPED_ASPEN_WOOD);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "aspen_planks"), ASPEN_PLANKS, STRIPPED_ASPEN_WOOD, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "aspen_stairs"), ASPEN_STAIRS, ASPEN_PLANKS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "aspen_slab"), ASPEN_SLAB, ASPEN_STAIRS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "aspen_fence"), ASPEN_FENCE, ASPEN_SLAB, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "aspen_fence_gate"), ASPEN_FENCE_GATE, ASPEN_FENCE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "aspen_door"), ASPEN_DOOR, ASPEN_FENCE_GATE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "aspen_trapdoor"), ASPEN_TRAPDOOR, ASPEN_DOOR, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "aspen_pressure_plate"), ASPEN_PRESSURE_PLATE, ASPEN_TRAPDOOR, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "aspen_button"), ASPEN_BUTTON, ASPEN_PRESSURE_PLATE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "aspen_sign"), ASPEN_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "aspen_wall_sign"), ASPEN_WALL_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "aspen_hanging_sign"), ASPEN_HANGING_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "aspen_wall_hanging_sign"), ASPEN_WALL_HANGING_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "yellow_aspen_leaves"), YELLOW_ASPEN_LEAVES, new CItemSettings().compostingChance(.3f), Items.BIRCH_LEAVES, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "yellow_aspen_leaf_pile"), YELLOW_ASPEN_LEAF_PILE, new CItemSettings().compostingChance(.1f), YELLOW_ASPEN_LEAVES, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "yellow_aspen_sapling"), YELLOW_ASPEN_SAPLING, new CItemSettings().compostingChance(.3f), Items.BIRCH_SAPLING, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_yellow_aspen_sapling"), POTTED_YELLOW_ASPEN_SAPLING);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "green_aspen_leaves"), GREEN_ASPEN_LEAVES);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "green_aspen_leaf_pile"), GREEN_ASPEN_LEAF_PILE);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "green_aspen_sapling"), GREEN_ASPEN_SAPLING, new CItemSettings().compostingChance(.3f), YELLOW_ASPEN_SAPLING, ItemGroups.NATURAL);
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
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "chiseled_cobblestone_bricks"), CHISELED_COBBLESTONE_BRICKS, COBBLESTONE_BRICK_WALL, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "cobblestone_brick_pillar"), COBBLESTONE_BRICK_PILLAR, CHISELED_COBBLESTONE_BRICKS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "cobblestone_tiles"), COBBLESTONE_TILES, COBBLESTONE_BRICK_PILLAR, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mossy_cobblestone_bricks"), MOSSY_COBBLESTONE_BRICKS, Items.MOSSY_COBBLESTONE_WALL, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mossy_cobblestone_brick_stairs"), MOSSY_COBBLESTONE_BRICK_STAIRS, MOSSY_COBBLESTONE_BRICKS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mossy_cobblestone_brick_slab"), MOSSY_COBBLESTONE_BRICK_SLAB, MOSSY_COBBLESTONE_BRICK_STAIRS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mossy_cobblestone_brick_wall"), MOSSY_COBBLESTONE_BRICK_WALL, MOSSY_COBBLESTONE_BRICK_SLAB, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "mossy_cobblestone_tiles"), MOSSY_COBBLESTONE_TILES, MOSSY_COBBLESTONE_BRICK_WALL, ItemGroups.BUILDING_BLOCKS);

        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "white_stained_calcite"), WHITE_STAINED_CALCITE, null, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "light_gray_stained_calcite"), LIGHT_GRAY_STAINED_CALCITE, WHITE_STAINED_CALCITE, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "gray_stained_calcite"), GRAY_STAINED_CALCITE, LIGHT_GRAY_STAINED_CALCITE, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "black_stained_calcite"), BLACK_STAINED_CALCITE, GRAY_STAINED_CALCITE, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "brown_stained_calcite"), BROWN_STAINED_CALCITE, BLACK_STAINED_CALCITE, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "red_stained_calcite"), RED_STAINED_CALCITE, BROWN_STAINED_CALCITE, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "orange_stained_calcite"), ORANGE_STAINED_CALCITE, RED_STAINED_CALCITE, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "yellow_stained_calcite"), YELLOW_STAINED_CALCITE, ORANGE_STAINED_CALCITE, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "lime_stained_calcite"), LIME_STAINED_CALCITE, YELLOW_STAINED_CALCITE, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "green_stained_calcite"), GREEN_STAINED_CALCITE, LIME_STAINED_CALCITE, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "cyan_stained_calcite"), CYAN_STAINED_CALCITE, GREEN_STAINED_CALCITE, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "light_blue_stained_calcite"), LIGHT_BLUE_STAINED_CALCITE, CYAN_STAINED_CALCITE, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "blue_stained_calcite"), BLUE_STAINED_CALCITE, LIGHT_BLUE_STAINED_CALCITE, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "purple_stained_calcite"), PURPLE_STAINED_CALCITE, BLUE_STAINED_CALCITE, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "magenta_stained_calcite"), MAGENTA_STAINED_CALCITE, PURPLE_STAINED_CALCITE, ItemGroups.COLORED_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pink_stained_calcite"), PINK_STAINED_CALCITE, MAGENTA_STAINED_CALCITE, ItemGroups.COLORED_BLOCKS);

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
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "chiseled_lavarock_bricks"), CHISELED_LAVAROCK_BRICKS, ItemGroups.BUILDING_BLOCKS);

        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "acai_log"), ACAI_LOG, new ItemRegistryHelper.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, MAHOGANY_BUTTON), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, MAHOGANY_LOG));
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "stripped_acai_log"), STRIPPED_ACAI_LOG, ACAI_LOG, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "acai_wood"), ACAI_WOOD, STRIPPED_ACAI_LOG, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "stripped_acai_wood"), STRIPPED_ACAI_WOOD, ACAI_WOOD, ItemGroups.BUILDING_BLOCKS);
        StrippableBlockRegistry.register(ACAI_LOG, STRIPPED_ACAI_LOG);
        StrippableBlockRegistry.register(ACAI_WOOD, STRIPPED_ACAI_WOOD);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "acai_planks"), ACAI_PLANKS, STRIPPED_ACAI_WOOD, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "acai_stairs"), ACAI_STAIRS, ACAI_PLANKS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "acai_slab"), ACAI_SLAB, ACAI_STAIRS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "acai_fence"), ACAI_FENCE, ACAI_SLAB, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "acai_fence_gate"), ACAI_FENCE_GATE, ACAI_FENCE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "acai_door"), ACAI_DOOR, ACAI_FENCE_GATE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "acai_trapdoor"), ACAI_TRAPDOOR, ACAI_DOOR, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "acai_pressure_plate"), ACAI_PRESSURE_PLATE, ACAI_TRAPDOOR, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "acai_button"), ACAI_BUTTON, ACAI_PRESSURE_PLATE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "acai_sign"), ACAI_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "acai_wall_sign"), ACAI_WALL_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "acai_hanging_sign"), ACAI_HANGING_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "acai_wall_hanging_sign"), ACAI_WALL_HANGING_SIGN);
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

        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "wisteria_log"), WISTERIA_LOG, new ItemRegistryHelper.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, Items.BIRCH_BUTTON), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, Items.BIRCH_LOG));
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "stripped_wisteria_log"), STRIPPED_WISTERIA_LOG, ACAI_LOG, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "wisteria_wood"), WISTERIA_WOOD, STRIPPED_WISTERIA_LOG, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "stripped_wisteria_wood"), STRIPPED_WISTERIA_WOOD, WISTERIA_WOOD, ItemGroups.BUILDING_BLOCKS);
        StrippableBlockRegistry.register(WISTERIA_LOG, STRIPPED_WISTERIA_LOG);
        StrippableBlockRegistry.register(WISTERIA_WOOD, STRIPPED_WISTERIA_WOOD);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "wisteria_planks"), WISTERIA_PLANKS, STRIPPED_WISTERIA_WOOD, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "wisteria_stairs"), WISTERIA_STAIRS, WISTERIA_PLANKS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "wisteria_slab"), WISTERIA_SLAB, WISTERIA_STAIRS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "wisteria_fence"), WISTERIA_FENCE, WISTERIA_SLAB, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "wisteria_fence_gate"), WISTERIA_FENCE_GATE, WISTERIA_FENCE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "wisteria_door"), WISTERIA_DOOR, WISTERIA_FENCE_GATE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "wisteria_trapdoor"), WISTERIA_TRAPDOOR, WISTERIA_DOOR, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "wisteria_pressure_plate"), WISTERIA_PRESSURE_PLATE, WISTERIA_TRAPDOOR, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "wisteria_button"), WISTERIA_BUTTON, WISTERIA_PRESSURE_PLATE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "wisteria_sign"), WISTERIA_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "wisteria_wall_sign"), WISTERIA_WALL_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "wisteria_hanging_sign"), WISTERIA_HANGING_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "wisteria_wall_hanging_sign"), WISTERIA_WALL_HANGING_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "white_wisteria_leaves"), WHITE_WISTERIA_LEAVES, Blocks.BIRCH_LEAVES, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "yellow_wisteria_leaves"), YELLOW_WISTERIA_LEAVES, Blocks.BIRCH_LEAVES, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "blue_wisteria_leaves"), BLUE_WISTERIA_LEAVES, Blocks.BIRCH_LEAVES, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pink_wisteria_leaves"), PINK_WISTERIA_LEAVES, Blocks.BIRCH_LEAVES, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "purple_wisteria_leaves"), PURPLE_WISTERIA_LEAVES, Blocks.BIRCH_LEAVES, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "hanging_white_wisteria_leaves"), HANGING_WHITE_WISTERIA_LEAVES, WHITE_WISTERIA_LEAVES, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "hanging_yellow_wisteria_leaves"), HANGING_YELLOW_WISTERIA_LEAVES, YELLOW_WISTERIA_LEAVES, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "hanging_blue_wisteria_leaves"), HANGING_BLUE_WISTERIA_LEAVES, BLUE_WISTERIA_LEAVES, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "hanging_pink_wisteria_leaves"), HANGING_PINK_WISTERIA_LEAVES, PINK_WISTERIA_LEAVES, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "hanging_purple_wisteria_leaves"), HANGING_PURPLE_WISTERIA_LEAVES, PURPLE_WISTERIA_LEAVES, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "white_wisteria_sapling"), WHITE_WISTERIA_SAPLING, Blocks.BIRCH_SAPLING, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "yellow_wisteria_sapling"), YELLOW_WISTERIA_SAPLING, Blocks.BIRCH_SAPLING, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "blue_wisteria_sapling"), BLUE_WISTERIA_SAPLING, Blocks.BIRCH_SAPLING, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pink_wisteria_sapling"), PINK_WISTERIA_SAPLING, Blocks.BIRCH_SAPLING, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "purple_wisteria_sapling"), PURPLE_WISTERIA_SAPLING, Blocks.BIRCH_SAPLING, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_white_wisteria_sapling"), POTTED_WHITE_WISTERIA_SAPLING);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_yellow_wisteria_sapling"), POTTED_YELLOW_WISTERIA_SAPLING);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_blue_wisteria_sapling"), POTTED_BLUE_WISTERIA_SAPLING);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_pink_wisteria_sapling"), POTTED_PINK_WISTERIA_SAPLING);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_purple_wisteria_sapling"), POTTED_PURPLE_WISTERIA_SAPLING);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "white_petals"), WHITE_PETALS, new CItemSettings().compostingChance(.3f), Items.PINK_PETALS, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "yellow_petals"), YELLOW_PETALS, new CItemSettings().compostingChance(.3f), Items.PINK_PETALS, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "blue_petals"), BLUE_PETALS, new CItemSettings().compostingChance(.3f), Items.PINK_PETALS, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "purple_petals"), PURPLE_PETALS, new CItemSettings().compostingChance(.3f), Items.PINK_PETALS, ItemGroups.NATURAL);

        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "peach_travertine"), PEACH_TRAVERTINE, new ItemRegistryHelper.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, null), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, Items.POINTED_DRIPSTONE));
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "golden_travertine"), GOLDEN_TRAVERTINE, new ItemRegistryHelper.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, null), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, Items.POINTED_DRIPSTONE));
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "ivory_travertine"), IVORY_TRAVERTINE, new ItemRegistryHelper.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, null), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, Items.POINTED_DRIPSTONE));
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "polished_peach_travertine"), POLISHED_PEACH_TRAVERTINE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "polished_golden_travertine"), POLISHED_GOLDEN_TRAVERTINE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "polished_ivory_travertine"), POLISHED_IVORY_TRAVERTINE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "polished_peach_travertine_tiles"), POLISHED_PEACH_TRAVERTINE_TILES, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "polished_golden_travertine_tiles"), POLISHED_GOLDEN_TRAVERTINE_TILES, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "polished_ivory_travertine_tiles"), POLISHED_IVORY_TRAVERTINE_TILES, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "steaming_ivory_travertine"), STEAMING_IVORY_TRAVERTINE, new ItemRegistryHelper.ItemGroupSettings(ItemGroups.FUNCTIONAL, null), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, null));

        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "red_rock"), RED_ROCK, new ItemRegistryHelper.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, Items.CUT_RED_SANDSTONE_SLAB), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, Items.RED_SANDSTONE));
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "red_rock_stairs"), RED_ROCK_STAIRS, RED_ROCK, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "red_rock_slab"), RED_ROCK_SLAB, RED_ROCK_STAIRS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "red_rock_wall"), RED_ROCK_WALL, RED_ROCK_SLAB, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "polished_red_rock"), POLISHED_RED_ROCK, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "polished_red_rock_stairs"), POLISHED_RED_ROCK_STAIRS, POLISHED_RED_ROCK, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "polished_red_rock_slab"), POLISHED_RED_ROCK_SLAB, POLISHED_RED_ROCK_STAIRS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "polished_red_rock_wall"), POLISHED_RED_ROCK_WALL, POLISHED_RED_ROCK_SLAB, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "red_rock_bricks"), RED_ROCK_BRICKS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "red_rock_brick_slab"), RED_ROCK_BRICK_SLAB, RED_ROCK_BRICKS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "red_rock_brick_stairs"), RED_ROCK_BRICK_STAIRS, RED_ROCK_BRICK_SLAB, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "red_rock_brick_wall"), RED_ROCK_BRICK_WALL, RED_ROCK_BRICK_STAIRS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "cracked_red_rock_bricks"), CRACKED_RED_ROCK_BRICKS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "chiseled_red_rock_bricks"), CHISELED_RED_ROCK_BRICKS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "red_rock_pillar"), RED_ROCK_PILLAR, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "red_rock_dirt"), RED_ROCK_DIRT, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "dry_dirt"), DRY_DIRT, ItemGroups.BUILDING_BLOCKS);

        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pine_log"), PINE_LOG, new ItemRegistryHelper.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, Items.SPRUCE_BUTTON), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, Items.SPRUCE_LOG));
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "stripped_pine_log"), STRIPPED_PINE_LOG, PINE_LOG, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pine_wood"), PINE_WOOD, STRIPPED_PINE_LOG, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "stripped_pine_wood"), STRIPPED_PINE_WOOD, PINE_WOOD, ItemGroups.BUILDING_BLOCKS);
        StrippableBlockRegistry.register(PINE_LOG, STRIPPED_PINE_LOG);
        StrippableBlockRegistry.register(PINE_WOOD, STRIPPED_PINE_WOOD);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pine_planks"), PINE_PLANKS, STRIPPED_PINE_WOOD, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pine_stairs"), PINE_STAIRS, PINE_PLANKS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pine_slab"), PINE_SLAB, PINE_STAIRS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pine_fence"), PINE_FENCE, PINE_SLAB, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pine_fence_gate"), PINE_FENCE_GATE, PINE_FENCE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pine_door"), PINE_DOOR, PINE_FENCE_GATE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pine_trapdoor"), PINE_TRAPDOOR, PINE_DOOR, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pine_pressure_plate"), PINE_PRESSURE_PLATE, PINE_TRAPDOOR, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pine_button"), PINE_BUTTON, PINE_PRESSURE_PLATE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pine_sign"), PINE_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pine_wall_sign"), PINE_WALL_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pine_hanging_sign"), PINE_HANGING_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pine_wall_hanging_sign"), PINE_WALL_HANGING_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pine_leaves"), PINE_LEAVES);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pine_sapling"), PINE_SAPLING, new CItemSettings().compostingChance(.3f), MAHOGANY_SAPLING, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_pine_sapling"), POTTED_PINE_SAPLING);

        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "palo_verde_log"), PALO_VERDE_LOG, new ItemRegistryHelper.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, Items.ACACIA_BUTTON), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, Items.ACACIA_LOG));
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "stripped_palo_verde_log"), STRIPPED_PALO_VERDE_LOG, PALO_VERDE_LOG, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "palo_verde_wood"), PALO_VERDE_WOOD, STRIPPED_PALO_VERDE_LOG, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "stripped_palo_verde_wood"), STRIPPED_PALO_VERDE_WOOD, PALO_VERDE_WOOD, ItemGroups.BUILDING_BLOCKS);
        StrippableBlockRegistry.register(PALO_VERDE_LOG, STRIPPED_PALO_VERDE_LOG);
        StrippableBlockRegistry.register(PALO_VERDE_WOOD, STRIPPED_PALO_VERDE_WOOD);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "palo_verde_planks"), PALO_VERDE_PLANKS, STRIPPED_PALO_VERDE_WOOD, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "palo_verde_stairs"), PALO_VERDE_STAIRS, PALO_VERDE_PLANKS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "palo_verde_slab"), PALO_VERDE_SLAB, PALO_VERDE_STAIRS, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "palo_verde_fence"), PALO_VERDE_FENCE, PALO_VERDE_SLAB, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "palo_verde_fence_gate"), PALO_VERDE_FENCE_GATE, PALO_VERDE_FENCE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "palo_verde_door"), PALO_VERDE_DOOR, PALO_VERDE_FENCE_GATE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "palo_verde_trapdoor"), PALO_VERDE_TRAPDOOR, PALO_VERDE_DOOR, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "palo_verde_pressure_plate"), PALO_VERDE_PRESSURE_PLATE, PALO_VERDE_TRAPDOOR, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "palo_verde_button"), PALO_VERDE_BUTTON, PALO_VERDE_PRESSURE_PLATE, ItemGroups.BUILDING_BLOCKS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "palo_verde_sign"), PALO_VERDE_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "palo_verde_wall_sign"), PALO_VERDE_WALL_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "palo_verde_hanging_sign"), PALO_VERDE_HANGING_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "palo_verde_wall_hanging_sign"), PALO_VERDE_WALL_HANGING_SIGN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "palo_verde_leaves"), PALO_VERDE_LEAVES);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "palo_verde_sapling"), PALO_VERDE_SAPLING, new CItemSettings().compostingChance(.1f), Items.JUNGLE_SAPLING, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_palo_verde_sapling"), POTTED_PALO_VERDE_SAPLING);

        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "antler"), ANTLER, new CItemSettings().compostingChance(.7f), Items.TURTLE_EGG, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "heather"), HEATHER, new CItemSettings().compostingChance(.65f), Items.LILY_OF_THE_VALLEY, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "tall_heather"), TALL_HEATHER, new CItemSettings().compostingChance(.65f), Items.PEONY, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_heather"), POTTED_HEATHER);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "aster"), ASTER, new CItemSettings().compostingChance(.65f), HEATHER, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_aster"), POTTED_ASTER);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "dry_grass"), DRY_GRASS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "beachgrass"), BEACHGRASS, new CItemSettings().compostingChance(.15f), Items.FERN, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "tall_beachgrass"), TALL_BEACHGRASS, new CItemSettings().compostingChance(.2f), Items.LARGE_FERN, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_beachgrass"), POTTED_BEACHGRASS);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "tropical_fern"), TROPICAL_FERN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "large_tropical_fern"), LARGE_TROPICAL_FERN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "potted_tropical_fern"), POTTED_TROPICAL_FERN);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "nautilus_fossil"), NAUTILUS_FOSSIL, Items.END_STONE, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "white_delphinium"), WHITE_DELPHINIUM, new CItemSettings().compostingChance(.65f), Items.LILY_OF_THE_VALLEY, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "yellow_delphinium"), YELLOW_DELPHINIUM, new CItemSettings().compostingChance(.65f), Items.LILY_OF_THE_VALLEY, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "blue_delphinium"), BLUE_DELPHINIUM, new CItemSettings().compostingChance(.65f), Items.LILY_OF_THE_VALLEY, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "pink_delphinium"), PINK_DELPHINIUM, new CItemSettings().compostingChance(.65f), Items.LILY_OF_THE_VALLEY, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "purple_delphinium"), PURPLE_DELPHINIUM, new CItemSettings().compostingChance(.65f), Items.LILY_OF_THE_VALLEY, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "golden_apple_leaves"), GOLDEN_APPLE_LEAVES, Items.CHERRY_LEAVES, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "sulfur_block"), SULFUR_BLOCK, ItemGroups.NATURAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "sawmill"), SAWMILL, Items.STONECUTTER, ItemGroups.FUNCTIONAL);
        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "kiln"), KILN, Items.BLAST_FURNACE, ItemGroups.FUNCTIONAL);

        Spreadable.register(GRASSY_LAVAROCK, LAVAROCK, GrassyLavarockBlock::canSurvive, GrassyLavarockBlock::canSpread, List.of(
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
                MAHOGANY_DOOR, MAHOGANY_TRAPDOOR,
                ASPEN_DOOR, ASPEN_TRAPDOOR,
                ACAI_DOOR, ACAI_TRAPDOOR,
                MAHOGANY_SAPLING, POTTED_MAHOGANY_SAPLING,
                YELLOW_ASPEN_SAPLING, POTTED_YELLOW_ASPEN_SAPLING,
                GREEN_ASPEN_SAPLING, POTTED_GREEN_ASPEN_SAPLING,
                ACAI_SAPLING, POTTED_ACAI_SAPLING,
                WHITE_WISTERIA_SAPLING, POTTED_WHITE_WISTERIA_SAPLING,
                YELLOW_WISTERIA_SAPLING, POTTED_YELLOW_WISTERIA_SAPLING,
                BLUE_WISTERIA_SAPLING, POTTED_BLUE_WISTERIA_SAPLING,
                PINK_WISTERIA_SAPLING, POTTED_PINK_WISTERIA_SAPLING,
                PURPLE_WISTERIA_SAPLING, POTTED_PURPLE_WISTERIA_SAPLING,
                WISTERIA_DOOR, WISTERIA_TRAPDOOR,
                BAMBOO_TORCH, BAMBOO_WALL_TORCH, POTTED_BAMBOO_TORCH,
                SOUL_BAMBOO_TORCH, SOUL_BAMBOO_WALL_TORCH, POTTED_SOUL_BAMBOO_TORCH,
                BEACHGRASS_THATCH, BEACHGRASS_THATCH_SLAB, BEACHGRASS_THATCH_STAIRS,
                BAMBOO_THATCH, BAMBOO_THATCH_SLAB, BAMBOO_THATCH_STAIRS,
                GRASS_THATCH, GRASS_THATCH_SLAB, GRASS_THATCH_STAIRS,
                PINE_SAPLING, POTTED_PINE_SAPLING,
                PINE_DOOR, PINE_TRAPDOOR,
                PALO_VERDE_SAPLING, POTTED_PALO_VERDE_SAPLING,
                PALO_VERDE_DOOR, PALO_VERDE_TRAPDOOR,
                ANTLER,
                HEATHER, TALL_HEATHER, POTTED_HEATHER,
                ASTER, POTTED_ASTER,
                DRY_GRASS,
                BEACHGRASS, TALL_BEACHGRASS, POTTED_BEACHGRASS,
                TROPICAL_FERN, LARGE_TROPICAL_FERN, POTTED_TROPICAL_FERN,
                WHITE_DELPHINIUM, YELLOW_DELPHINIUM, BLUE_DELPHINIUM, PINK_DELPHINIUM, PURPLE_DELPHINIUM,
                WHITE_PETALS, YELLOW_PETALS, BLUE_PETALS, PURPLE_PETALS
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
                MAHOGANY_LEAVES,
                YELLOW_ASPEN_LEAVES, YELLOW_ASPEN_LEAF_PILE,
                GREEN_ASPEN_LEAVES, GREEN_ASPEN_LEAF_PILE,
                GRASSY_LAVAROCK,
                ACAI_LEAVES, HANGING_ACAI_LEAVES,
                WHITE_WISTERIA_LEAVES, YELLOW_WISTERIA_LEAVES, BLUE_WISTERIA_LEAVES, PINK_WISTERIA_LEAVES, PURPLE_WISTERIA_LEAVES,
                HANGING_WHITE_WISTERIA_LEAVES, HANGING_YELLOW_WISTERIA_LEAVES, HANGING_BLUE_WISTERIA_LEAVES, HANGING_PINK_WISTERIA_LEAVES, HANGING_PURPLE_WISTERIA_LEAVES,
                PINE_LEAVES,
                PALO_VERDE_LEAVES,
                GOLDEN_APPLE_LEAVES
        );
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(), MAHOGANY_LEAVES, GREEN_ASPEN_LEAVES, GREEN_ASPEN_LEAF_PILE, ACAI_LEAVES, HANGING_ACAI_LEAVES, PINE_LEAVES);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, state.get(TallPlantBlock.HALF) == DoubleBlockHalf.UPPER ? pos.down() : pos) : -1, LARGE_TROPICAL_FERN);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(.5d, 1d), TROPICAL_FERN, POTTED_TROPICAL_FERN);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(.5d, 1d), GRASSY_LAVAROCK, GRASS_THATCH, GRASS_THATCH_SLAB, GRASS_THATCH_STAIRS, TRIMMED_GRASS_THATCH, TRIMMED_GRASS_THATCH_SLAB, TRIMMED_GRASS_THATCH_STAIRS);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (tintIndex != 0) {
                return (world == null || pos == null) ? GrassColors.getDefaultColor() : BiomeColors.getGrassColor(world, pos);
            } return -1;
        }, WHITE_PETALS, YELLOW_PETALS, BLUE_PETALS, PURPLE_PETALS);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> state != null ? SulfurBlock.getEffectFromProperty(state.get(SulfurBlock.EFFECT)).getColor() : 1, SULFUR_BLOCK);
    }
}
