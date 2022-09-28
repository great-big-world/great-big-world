package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.ItemUtil;
import com.github.creoii.greatbigworld.main.util.Register;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class BlockRegistry implements Register {
    private static final Map<Block, Block> STRIPPED_BLOCKS = new HashMap<>();
    private static final FireBlock FIRE = (FireBlock) Blocks.FIRE;

    public static final Block MAHOGANY_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD, state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? MapColor.TERRACOTTA_ORANGE : MapColor.TERRACOTTA_BROWN).strength(2f).sounds(BlockSoundGroup.WOOD)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MUDDY_MANGROVE_ROOTS); }
    };
    public static final Block STRIPPED_MAHOGANY_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).mapColor(MapColor.TERRACOTTA_ORANGE)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.STRIPPED_MANGROVE_LOG); }
    };
    public static final Block MAHOGANY_WOOD = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD).mapColor(MapColor.TERRACOTTA_BROWN)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_WOOD); }
    };
    public static final Block STRIPPED_MAHOGANY_WOOD = new PillarBlock(FabricBlockSettings.copy(STRIPPED_MAHOGANY_LOG)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.STRIPPED_MANGROVE_WOOD); }
    };
    public static final Block MAHOGANY_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.TERRACOTTA_ORANGE)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_PLANKS); }
    };
    public static final Block MAHOGANY_SLAB = new SlabBlock(FabricBlockSettings.copy(MAHOGANY_PLANKS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_SLAB); }
    };
    public static final Block MAHOGANY_STAIRS = new StairsBlock(MAHOGANY_PLANKS.getDefaultState(), FabricBlockSettings.copy(MAHOGANY_PLANKS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_STAIRS); }
    };
    public static final Block MAHOGANY_FENCE = new FenceBlock(FabricBlockSettings.copy(MAHOGANY_PLANKS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_FENCE); }
    };
    public static final Block MAHOGANY_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(MAHOGANY_PLANKS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_FENCE_GATE); }
    };
    public static final Block MAHOGANY_BUTTON = new WoodenButtonBlock(FabricBlockSettings.copy(MAHOGANY_PLANKS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_BUTTON); }
    };
    public static final Block MAHOGANY_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(MAHOGANY_PLANKS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_PRESSURE_PLATE); }
    };
    public static final Block MAHOGANY_DOOR = new DoorBlock(FabricBlockSettings.copy(MAHOGANY_PLANKS).strength(3f).sounds(BlockSoundGroup.WOOD).nonOpaque()) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_DOOR); }
    };
    public static final Block MAHOGANY_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings.copy(MAHOGANY_PLANKS).strength(3f).nonOpaque().allowsSpawning((state, world, pos, type) -> false)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_TRAPDOOR); }
    };
    public static final Block MAHOGANY_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
    public static final Block MAHOGANY_SAPLING = new SaplingBlock(new SaplingGenerator() {
        @Nullable @Override
        protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
            return ConfiguredFeatureRegistry.MAHOGANY;
        }
    }, FabricBlockSettings.copy(Blocks.JUNGLE_SAPLING)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_PROPAGULE); }
    };
    public static final Block POTTED_MAHOGANY_SAPLING = new FlowerPotBlock(MAHOGANY_SAPLING, FabricBlockSettings.copy(Blocks.FLOWER_POT));
    public static final SignType MAHOGANY_TYPE = SignType.register(new SignType("mahogany"));
    public static final Block MAHOGANY_SIGN = new SignBlock(AbstractBlock.Settings.of(Material.WOOD).noCollision().strength(1f).sounds(BlockSoundGroup.WOOD), MAHOGANY_TYPE) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_SIGN);
        }
    };
    public static final Block MAHOGANY_WALL_SIGN = new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD).noCollision().strength(1f).sounds(BlockSoundGroup.WOOD).dropsLike(MAHOGANY_SIGN), MAHOGANY_TYPE);

    public void register() {
        registerBlock(new Identifier(NAMESPACE, "mahogany_log"), MAHOGANY_LOG, ItemGroup.BUILDING_BLOCKS, new ExtendedBlockSettings(0f, 5, 5, STRIPPED_MAHOGANY_LOG));
        registerBlock(new Identifier(NAMESPACE, "stripped_mahogany_log"), STRIPPED_MAHOGANY_LOG, ItemGroup.BUILDING_BLOCKS, new ExtendedBlockSettings(.3f, 5, 5, null));
        registerBlock(new Identifier(NAMESPACE, "mahogany_wood"), MAHOGANY_WOOD, ItemGroup.BUILDING_BLOCKS, new ExtendedBlockSettings(0f, 5, 5, STRIPPED_MAHOGANY_WOOD));
        registerBlock(new Identifier(NAMESPACE, "stripped_mahogany_wood"), STRIPPED_MAHOGANY_WOOD, ItemGroup.BUILDING_BLOCKS, new ExtendedBlockSettings(0f, 5, 5, null));
        registerBlock(new Identifier(NAMESPACE, "mahogany_planks"), MAHOGANY_PLANKS, ItemGroup.BUILDING_BLOCKS, new ExtendedBlockSettings(0f, 5, 20, null));
        registerBlock(new Identifier(NAMESPACE, "mahogany_slab"), MAHOGANY_SLAB, ItemGroup.BUILDING_BLOCKS, new ExtendedBlockSettings(0f, 0, 0, null));
        registerBlock(new Identifier(NAMESPACE, "mahogany_stairs"), MAHOGANY_STAIRS, ItemGroup.BUILDING_BLOCKS, new ExtendedBlockSettings(0f, 0, 0, null));
        registerBlock(new Identifier(NAMESPACE, "mahogany_fence"), MAHOGANY_FENCE, ItemGroup.DECORATIONS, new ExtendedBlockSettings(0f, 0, 0, null));
        registerBlock(new Identifier(NAMESPACE, "mahogany_fence_gate"), MAHOGANY_FENCE_GATE, ItemGroup.REDSTONE, new ExtendedBlockSettings(0f, 0, 0, null));
        registerBlock(new Identifier(NAMESPACE, "mahogany_button"), MAHOGANY_BUTTON, ItemGroup.REDSTONE, new ExtendedBlockSettings(0f, 0, 0, null));
        registerBlock(new Identifier(NAMESPACE, "mahogany_pressure_plate"), MAHOGANY_PRESSURE_PLATE, ItemGroup.REDSTONE, new ExtendedBlockSettings(0f, 0, 0, null));
        registerBlock(new Identifier(NAMESPACE, "mahogany_door"), MAHOGANY_DOOR, ItemGroup.REDSTONE, new ExtendedBlockSettings(0f, 0, 0, null));
        registerBlock(new Identifier(NAMESPACE, "mahogany_trapdoor"), MAHOGANY_TRAPDOOR, ItemGroup.REDSTONE, new ExtendedBlockSettings(0f, 0, 0, null));
        registerBlock(new Identifier(NAMESPACE, "mahogany_leaves"), MAHOGANY_LEAVES, null, new ExtendedBlockSettings(.3f, 30, 60, null));
        registerBlock(new Identifier(NAMESPACE, "mahogany_sapling"), MAHOGANY_SAPLING, ItemGroup.DECORATIONS, new ExtendedBlockSettings(.3f, 0, 0, null));
        registerBlock(new Identifier(NAMESPACE, "potted_mahogany_sapling"), POTTED_MAHOGANY_SAPLING, null, null);
        registerBlock(new Identifier(NAMESPACE, "mahogany_sign"), MAHOGANY_SIGN, null, null);
        registerBlock(new Identifier(NAMESPACE, "mahogany_wall_sign"), MAHOGANY_WALL_SIGN, null, null);

        AxeItem.STRIPPED_BLOCKS = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPED_BLOCKS).putAll(STRIPPED_BLOCKS).build();
        BlockEntityType.SIGN.blocks = new ImmutableSet.Builder<Block>().addAll(BlockEntityType.SIGN.blocks).add(MAHOGANY_SIGN).add(MAHOGANY_WALL_SIGN).build();
    }

    @Override
    public void registerClient() {
        RenderLayers.BLOCKS.put(MAHOGANY_DOOR, RenderLayer.getCutout());
        RenderLayers.BLOCKS.put(MAHOGANY_TRAPDOOR, RenderLayer.getCutout());
        RenderLayers.BLOCKS.put(MAHOGANY_SAPLING, RenderLayer.getCutout());
        RenderLayers.BLOCKS.put(POTTED_MAHOGANY_SAPLING, RenderLayer.getCutout());
        RenderLayers.BLOCKS.put(MAHOGANY_LEAVES, RenderLayer.getCutoutMipped());
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(), MAHOGANY_LEAVES);
    }

    public static void registerBlock(Identifier id, Block block, @Nullable ItemGroup group, @Nullable ExtendedBlockSettings extension) {
        Registry.register(Registry.BLOCK, id, block);
        if (group != null) Registry.register(Registry.ITEM, id, new BlockItem(block, new Item.Settings().group(group)));
        if (extension != null) {
            if (extension.compostChance() > 0f)
                ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(block, extension.compostChance());
            if (extension.burnChance() > 0 && extension.spreadChance() > 0)
                FIRE.registerFlammableBlock(block, extension.burnChance(), extension.spreadChance());
            if (extension.strippedBlock() != null)
                BlockRegistry.STRIPPED_BLOCKS.put(block, extension.strippedBlock());
        }
    }

    public static record ExtendedBlockSettings(float compostChance, int burnChance, int spreadChance, @Nullable Block strippedBlock) { }
}
