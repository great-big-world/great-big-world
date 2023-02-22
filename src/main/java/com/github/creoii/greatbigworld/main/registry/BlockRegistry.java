package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.block.*;
import com.github.creoii.greatbigworld.main.util.DefaultBlockSets;
import com.github.creoii.greatbigworld.main.util.GBWSignTypes;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.sapling.GreenAspenSaplingGenerator;
import com.github.creoii.greatbigworld.world.sapling.MahoganySaplingGenerator;
import com.github.creoii.greatbigworld.world.sapling.YellowAspenSaplingGenerator;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
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
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class BlockRegistry implements Register {
    //region Mahogany Wood
    public static DefaultBlockSets.WoodSet MAHOGANY = DefaultBlockSets.createWoodSet("mahogany", MapColor.TERRACOTTA_BROWN, MapColor.TERRACOTTA_ORANGE, Items.JUNGLE_BUTTON, Items.JUNGLE_LOG);
    public static final Block MAHOGANY_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
    public static final Block MAHOGANY_SAPLING = new SaplingBlock(new MahoganySaplingGenerator(), FabricBlockSettings.copy(Blocks.JUNGLE_SAPLING));
    public static final Block POTTED_MAHOGANY_SAPLING = new FlowerPotBlock(MAHOGANY_SAPLING, FabricBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block MAHOGANY_SIGN = new SignBlock(AbstractBlock.Settings.of(Material.WOOD).noCollision().strength(1f).sounds(BlockSoundGroup.WOOD), GBWSignTypes.MAHOGANY);
    public static final Block MAHOGANY_WALL_SIGN = new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD).noCollision().strength(1f).sounds(BlockSoundGroup.WOOD).dropsLike(MAHOGANY_SIGN), GBWSignTypes.MAHOGANY);
    //endregion
    //region Aspen Wood
    public static DefaultBlockSets.WoodSet ASPEN = DefaultBlockSets.createWoodSet("aspen", MapColor.TERRACOTTA_BROWN, MapColor.TERRACOTTA_ORANGE, Items.BIRCH_BUTTON, Items.BIRCH_LOG);
    public static final Block YELLOW_ASPEN_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.PLANT, MapColor.YELLOW).strength(.15f).sounds(BlockSoundGroup.GRASS).nonOpaque());
    public static final Block YELLOW_ASPEN_LEAF_PILE = new LeafPileBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.YELLOW).strength(.1f).sounds(BlockSoundGroup.GRASS).nonOpaque().noCollision());
    public static final Block GREEN_ASPEN_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.PLANT).strength(.15f).sounds(BlockSoundGroup.GRASS).nonOpaque());
    public static final Block GREEN_ASPEN_LEAF_PILE = new LeafPileBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.DARK_GREEN).strength(.1f).sounds(BlockSoundGroup.GRASS).nonOpaque().noCollision());
    public static final Block YELLOW_ASPEN_SAPLING = new SaplingBlock(new YellowAspenSaplingGenerator(), FabricBlockSettings.copy(Blocks.BIRCH_SAPLING));
    public static final Block POTTED_YELLOW_ASPEN_SAPLING = new FlowerPotBlock(YELLOW_ASPEN_SAPLING, FabricBlockSettings.copy(Blocks.FLOWER_POT));
    public static final Block GREEN_ASPEN_SAPLING = new SaplingBlock(new GreenAspenSaplingGenerator(), FabricBlockSettings.copy(Blocks.BIRCH_SAPLING));
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
    public static final Block DAYLIGHT_MUSHROOM = new GlimmeringMushroomBlock(ParticleRegistry.DAY_GLIMMER, new StatusEffectInstance(StatusEffects.GLOWING, 100), 4, 16765440);
    public static final Block MIDNIGHT_MUSHROOM = new GlimmeringMushroomBlock(ParticleRegistry.NIGHT_GLIMMER, new StatusEffectInstance(StatusEffects.BLINDNESS, 100), 8, 9558015);
    public static final Block DARKBLIGHT_MUSHROOM = new GlimmeringMushroomBlock(ParticleRegistry.DARK_GLIMMER, new StatusEffectInstance(StatusEffects.DARKNESS, 150), 12, 0);
    //endregion
    //region Cobblestone Bricks
    public static final Block COBBLESTONE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.COBBLESTONE));
    public static final Block COBBLESTONE_BRICK_STAIRS = new StairsBlock(COBBLESTONE_BRICKS.getDefaultState(), FabricBlockSettings.copy(Blocks.COBBLESTONE_STAIRS));
    public static final Block COBBLESTONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.COBBLESTONE_SLAB));
    public static final Block COBBLESTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(Blocks.COBBLESTONE_WALL));
    public static final Block MOSSY_COBBLESTONE_BRICKS = new Block(FabricBlockSettings.copy(Blocks.MOSSY_COBBLESTONE));
    public static final Block MOSSY_COBBLESTONE_BRICK_STAIRS = new StairsBlock(MOSSY_COBBLESTONE_BRICKS.getDefaultState(), FabricBlockSettings.copy(Blocks.MOSSY_COBBLESTONE_STAIRS));
    public static final Block MOSSY_COBBLESTONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.MOSSY_COBBLESTONE_SLAB));
    public static final Block MOSSY_COBBLESTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(Blocks.MOSSY_COBBLESTONE_WALL));
    //endregion
    //region Miscellaneous
    public static final Block ANTLER = new AntlerBlock();
    public static final Block TALL_HEATHER = new TallFlowerBlock(FabricBlockSettings.copy(Blocks.ROSE_BUSH));
    public static final Block HEATHER = new FernBlock(FabricBlockSettings.copy(Blocks.POPPY));
    public static final Block POTTED_HEATHER = new FlowerPotBlock(HEATHER, FabricBlockSettings.copy(Blocks.FLOWER_POT));
    //endregion

    public void register() {
        MAHOGANY.register();
        registerBlock(new Identifier(NAMESPACE, "mahogany_leaves"), MAHOGANY_LEAVES, new ExtendedBlockSettings(0f, 30, 60, null));
        registerBlock(new Identifier(NAMESPACE, "mahogany_sapling"), MAHOGANY_SAPLING, new ExtendedBlockSettings(0f, 0, 0, null), Items.JUNGLE_SAPLING, ItemGroups.NATURAL);
        registerBlock(new Identifier(NAMESPACE, "potted_mahogany_sapling"), POTTED_MAHOGANY_SAPLING, null);
        registerBlock(new Identifier(NAMESPACE, "mahogany_sign"), MAHOGANY_SIGN, null);
        registerBlock(new Identifier(NAMESPACE, "mahogany_wall_sign"), MAHOGANY_WALL_SIGN, null);

        ASPEN.register();
        registerBlock(new Identifier(NAMESPACE, "yellow_aspen_leaves"), YELLOW_ASPEN_LEAVES, new ExtendedBlockSettings(0f, 30, 60, null), Items.BIRCH_LEAVES, ItemGroups.NATURAL);
        registerBlock(new Identifier(NAMESPACE, "yellow_aspen_leaf_pile"), YELLOW_ASPEN_LEAF_PILE, new ExtendedBlockSettings(0f, 30, 60, null), Items.BIRCH_LEAVES, ItemGroups.NATURAL);
        registerBlock(new Identifier(NAMESPACE, "yellow_aspen_sapling"), YELLOW_ASPEN_SAPLING, new ExtendedBlockSettings(0f, 0, 0, null), Items.BIRCH_SAPLING, ItemGroups.NATURAL);
        registerBlock(new Identifier(NAMESPACE, "potted_yellow_aspen_sapling"), POTTED_YELLOW_ASPEN_SAPLING, null);
        registerBlock(new Identifier(NAMESPACE, "green_aspen_leaves"), GREEN_ASPEN_LEAVES, new ExtendedBlockSettings(0f, 30, 60, null));
        registerBlock(new Identifier(NAMESPACE, "green_aspen_leaf_pile"), GREEN_ASPEN_LEAF_PILE, new ExtendedBlockSettings(0f, 30, 60, null));
        registerBlock(new Identifier(NAMESPACE, "green_aspen_sapling"), GREEN_ASPEN_SAPLING, new ExtendedBlockSettings(0f, 0, 0, null), Items.BIRCH_SAPLING, ItemGroups.NATURAL);
        registerBlock(new Identifier(NAMESPACE, "potted_green_aspen_sapling"), POTTED_GREEN_ASPEN_SAPLING, null);
        registerBlock(new Identifier(NAMESPACE, "aspen_sign"), ASPEN_SIGN, null);
        registerBlock(new Identifier(NAMESPACE, "aspen_wall_sign"), ASPEN_WALL_SIGN, null);

        registerBlock(new Identifier(NAMESPACE, "bamboo_torch"), BAMBOO_TORCH, new ExtendedBlockSettings(0f, 0, 0, null));
        registerBlock(new Identifier(NAMESPACE, "bamboo_wall_torch"), BAMBOO_WALL_TORCH, new ExtendedBlockSettings(0f, 0, 0, null));
        registerBlock(new Identifier(NAMESPACE, "soul_bamboo_torch"), SOUL_BAMBOO_TORCH, new ExtendedBlockSettings(0f, 0, 0, null));
        registerBlock(new Identifier(NAMESPACE, "soul_bamboo_wall_torch"), SOUL_BAMBOO_WALL_TORCH, new ExtendedBlockSettings(0f, 0, 0, null));
        registerBlock(new Identifier(NAMESPACE, "potted_bamboo_torch"), POTTED_BAMBOO_TORCH, new ExtendedBlockSettings(0f, 0, 0, null));
        registerBlock(new Identifier(NAMESPACE, "potted_soul_bamboo_torch"), POTTED_SOUL_BAMBOO_TORCH, new ExtendedBlockSettings(0f, 0, 0, null));

        registerBlock(new Identifier(NAMESPACE, "daylight_mushroom"), DAYLIGHT_MUSHROOM, new ExtendedBlockSettings(0f, 0, 0, null), new ItemRegistry.ItemGroupSettings(ItemGroups.NATURAL, Items.RED_MUSHROOM), new ItemRegistry.ItemGroupSettings(ItemGroups.INGREDIENTS, Items.PHANTOM_MEMBRANE));
        registerBlock(new Identifier(NAMESPACE, "midnight_mushroom"), MIDNIGHT_MUSHROOM, new ExtendedBlockSettings(0f, 0, 0, null), new ItemRegistry.ItemGroupSettings(ItemGroups.NATURAL, Items.RED_MUSHROOM), new ItemRegistry.ItemGroupSettings(ItemGroups.INGREDIENTS, Items.PHANTOM_MEMBRANE));
        registerBlock(new Identifier(NAMESPACE, "darkblight_mushroom"), DARKBLIGHT_MUSHROOM, new ExtendedBlockSettings(0f, 0, 0, null), new ItemRegistry.ItemGroupSettings(ItemGroups.NATURAL, Items.RED_MUSHROOM), new ItemRegistry.ItemGroupSettings(ItemGroups.INGREDIENTS, Items.PHANTOM_MEMBRANE));

        registerBlock(new Identifier(NAMESPACE, "antler"), ANTLER, new ExtendedBlockSettings(0f, 0, 0, null), Items.TURTLE_EGG, ItemGroups.NATURAL);
        registerBlock(new Identifier(NAMESPACE, "tall_heather"), TALL_HEATHER, new ExtendedBlockSettings(0f, 60, 100, null), Items.PEONY, ItemGroups.NATURAL);
        registerBlock(new Identifier(NAMESPACE, "heather"), HEATHER, new ExtendedBlockSettings(0f, 60, 100, null), Items.LILY_OF_THE_VALLEY, ItemGroups.NATURAL);
        registerBlock(new Identifier(NAMESPACE, "potted_heather"), POTTED_HEATHER, new ExtendedBlockSettings(0f, 60, 100, null));

        registerBlock(new Identifier(NAMESPACE, "cobblestone_bricks"), COBBLESTONE_BRICKS, null, new ItemRegistry.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, Items.COBBLESTONE_WALL));
        registerBlock(new Identifier(NAMESPACE, "cobblestone_brick_stairs"), COBBLESTONE_BRICK_STAIRS, null, new ItemRegistry.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, COBBLESTONE_BRICKS));
        registerBlock(new Identifier(NAMESPACE, "cobblestone_brick_slab"), COBBLESTONE_BRICK_SLAB, null, new ItemRegistry.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, COBBLESTONE_BRICK_STAIRS));
        registerBlock(new Identifier(NAMESPACE, "cobblestone_brick_wall"), COBBLESTONE_BRICK_WALL, null, new ItemRegistry.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, COBBLESTONE_BRICK_SLAB));
        registerBlock(new Identifier(NAMESPACE, "mossy_cobblestone_bricks"), MOSSY_COBBLESTONE_BRICKS, null, new ItemRegistry.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, Items.MOSSY_COBBLESTONE_WALL));
        registerBlock(new Identifier(NAMESPACE, "mossy_cobblestone_brick_stairs"), MOSSY_COBBLESTONE_BRICK_STAIRS, null, new ItemRegistry.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, MOSSY_COBBLESTONE_BRICKS));
        registerBlock(new Identifier(NAMESPACE, "mossy_cobblestone_brick_slab"), MOSSY_COBBLESTONE_BRICK_SLAB, null, new ItemRegistry.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, MOSSY_COBBLESTONE_BRICK_STAIRS));
        registerBlock(new Identifier(NAMESPACE, "mossy_cobblestone_brick_wall"), MOSSY_COBBLESTONE_BRICK_WALL, null, new ItemRegistry.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, MOSSY_COBBLESTONE_BRICK_SLAB));
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
                ANTLER,
                HEATHER,
                TALL_HEATHER,
                POTTED_HEATHER
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
        Registry.register(Registries.BLOCK, id, block);
        if (extension != null) {
            if (extension.compostChance() > 0f)
                CompostingChanceRegistry.INSTANCE.add(block, extension.compostChance());
            if (extension.burnChance() > 0 && extension.spreadChance() > 0)
                FlammableBlockRegistry.getDefaultInstance().add(block, extension.burnChance(), extension.spreadChance());
            if (extension.strippedBlock() != null)
                StrippableBlockRegistry.register(block, extension.strippedBlock());
        }
    }

    public static void registerBlock(Identifier id, Block block, @Nullable ExtendedBlockSettings extension, ItemGroup group) {
        registerBlock(id, block, extension, new ItemRegistry.ItemGroupSettings(group, null));
    }

    public static void registerBlock(Identifier id, Block block, @Nullable ExtendedBlockSettings extension, @Nullable ItemConvertible after, ItemGroup group) {
        registerBlock(id, block, extension, new ItemRegistry.ItemGroupSettings(group, after));
    }

    public static void registerBlock(Identifier id, Block block, @Nullable ExtendedBlockSettings extension, @Nullable ItemRegistry.ItemGroupSettings... groups) {
        registerBlock(id, block, extension);
        if (groups != null) {
            ItemRegistry.registerItem(id, new BlockItem(block, new FabricItemSettings()), groups);
        }
    }

    public record ExtendedBlockSettings(float compostChance, int burnChance, int spreadChance, @Nullable Block strippedBlock) { }
}
