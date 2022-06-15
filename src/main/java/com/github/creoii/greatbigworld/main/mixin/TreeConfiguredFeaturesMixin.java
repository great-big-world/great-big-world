package com.github.creoii.greatbigworld.main.mixin;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.world.decorator.HangingLeavesTreeDecorator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PropaguleBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryEntryList;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.root.AboveRootPlacement;
import net.minecraft.world.gen.root.MangroveRootPlacement;
import net.minecraft.world.gen.root.MangroveRootPlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.RandomizedIntBlockStateProvider;
import net.minecraft.world.gen.treedecorator.*;
import net.minecraft.world.gen.trunk.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Mixin(TreeConfiguredFeatures.class)
public abstract class TreeConfiguredFeaturesMixin {
    @Mutable @Shadow @Final public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MANGROVE;
    @Shadow @Final private static BeehiveTreeDecorator BEES_001;
    @Mutable @Shadow @Final public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> TALL_MANGROVE;
    @Mutable @Shadow @Final public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MEGA_JUNGLE_TREE;
    @Mutable @Shadow @Final public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> SPRUCE;
    @Mutable @Shadow @Final public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MEGA_SPRUCE;
    @Mutable @Shadow @Final public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MEGA_PINE;
    @Mutable @Shadow @Final public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> DARK_OAK;
    @Mutable @Shadow @Final public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> SWAMP_OAK;
    @Mutable @Shadow @Final public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> OAK;
    @Mutable @Shadow @Final public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> BIRCH;
    @Mutable @Shadow @Final public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> JUNGLE_TREE;
    @Mutable @Shadow @Final public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FANCY_OAK;
    @Mutable @Shadow @Final public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> JUNGLE_TREE_NO_VINE;

    @Shadow private static TreeFeatureConfig.Builder builder(Block log, Block leaves, int baseHeight, int firstRandomHeight, int secondRandomHeight, int radius) {
        return null;
    }
    @Shadow private static TreeFeatureConfig.Builder oak() {
        return null;
    }
    @Shadow private static TreeFeatureConfig.Builder birch() {
        return null;
    }
    @Shadow private static TreeFeatureConfig.Builder jungle() {
        return null;
    }
    @Shadow private static TreeFeatureConfig.Builder fancyOak() {
        return null;
    }

    static {
        MANGROVE = ConfiguredFeatures.register("mangrove_hanging_leaves", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.MANGROVE_LOG), new UpwardsBranchingTrunkPlacer(2, 1, 4, UniformIntProvider.create(1, 4), 0.5F, UniformIntProvider.create(0, 1), Registry.BLOCK.getOrCreateEntryList(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)), BlockStateProvider.of(Blocks.MANGROVE_LEAVES), new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(2), 70), Optional.of(new MangroveRootPlacer(UniformIntProvider.create(1, 3), BlockStateProvider.of(Blocks.MANGROVE_ROOTS), Optional.of(new AboveRootPlacement(BlockStateProvider.of(Blocks.MOSS_CARPET), 0.5F)), new MangroveRootPlacement(Registry.BLOCK.getOrCreateEntryList(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH), RegistryEntryList.of(Block::getRegistryEntry, Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS), BlockStateProvider.of(Blocks.MUDDY_MANGROVE_ROOTS), 8, 15, 0.2F))), new TwoLayersFeatureSize(2, 0, 2)).decorators(List.of(new HangingLeavesTreeDecorator(BlockRegistry.HANGING_MANGROVE_LEAVES.getDefaultState(), 1, 3, .25f, true), new LeavesVineTreeDecorator(0.125F), new AttachedToLeavesTreeDecorator(0.14F, 1, 0, new RandomizedIntBlockStateProvider(BlockStateProvider.of(Blocks.MANGROVE_PROPAGULE.getDefaultState().with(PropaguleBlock.HANGING, true)), PropaguleBlock.AGE, UniformIntProvider.create(0, 4)), 2, List.of(Direction.DOWN)), BEES_001)).ignoreVines().build());
        TALL_MANGROVE = ConfiguredFeatures.register("tall_mangrove_hanging_leaves", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.MANGROVE_LOG), new UpwardsBranchingTrunkPlacer(4, 1, 9, UniformIntProvider.create(1, 6), 0.5F, UniformIntProvider.create(0, 1), Registry.BLOCK.getOrCreateEntryList(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)), BlockStateProvider.of(Blocks.MANGROVE_LEAVES), new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(2), 70), Optional.of(new MangroveRootPlacer(UniformIntProvider.create(3, 7), BlockStateProvider.of(Blocks.MANGROVE_ROOTS), Optional.of(new AboveRootPlacement(BlockStateProvider.of(Blocks.MOSS_CARPET), 0.5F)), new MangroveRootPlacement(Registry.BLOCK.getOrCreateEntryList(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH), RegistryEntryList.of(Block::getRegistryEntry, Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS), BlockStateProvider.of(Blocks.MUDDY_MANGROVE_ROOTS), 8, 15, 0.2F))), new TwoLayersFeatureSize(3, 0, 2)).decorators(List.of(new HangingLeavesTreeDecorator(BlockRegistry.HANGING_MANGROVE_LEAVES.getDefaultState(), 1, 3, .25f, true), new LeavesVineTreeDecorator(0.125F), new AttachedToLeavesTreeDecorator(0.14F, 1, 0, new RandomizedIntBlockStateProvider(BlockStateProvider.of((BlockState)Blocks.MANGROVE_PROPAGULE.getDefaultState().with(PropaguleBlock.HANGING, true)), PropaguleBlock.AGE, UniformIntProvider.create(0, 4)), 2, List.of(Direction.DOWN)), BEES_001)).ignoreVines().build());
        MEGA_JUNGLE_TREE = ConfiguredFeatures.register("mega_jungle_tree_hanging_leaves", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.JUNGLE_LOG), new MegaJungleTrunkPlacer(10, 2, 19), BlockStateProvider.of(Blocks.JUNGLE_LEAVES), new JungleFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 2), new TwoLayersFeatureSize(1, 1, 2)).decorators(List.of(new HangingLeavesTreeDecorator(BlockRegistry.HANGING_JUNGLE_LEAVES.getDefaultState(), 2, 5, .25f, true), TrunkVineTreeDecorator.INSTANCE, new LeavesVineTreeDecorator(0.25F))).build());
        SPRUCE = ConfiguredFeatures.register("spruce_hanging_leaves", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.SPRUCE_LOG), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.of(Blocks.SPRUCE_LEAVES), new SpruceFoliagePlacer(UniformIntProvider.create(2, 3), UniformIntProvider.create(0, 2), UniformIntProvider.create(1, 2)), new TwoLayersFeatureSize(2, 0, 2)).decorators(List.of(new HangingLeavesTreeDecorator(BlockRegistry.HANGING_SPRUCE_LEAVES.getDefaultState(), 1, 2, .1f, false))).ignoreVines().build());
        MEGA_SPRUCE = ConfiguredFeatures.register("mega_spruce_sequoia_hanging_leaves", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(BlockRegistry.SEQUOIA_LOG), new GiantTrunkPlacer(13, 2, 14), BlockStateProvider.of(Blocks.SPRUCE_LEAVES), new MegaPineFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), UniformIntProvider.create(13, 17)), new TwoLayersFeatureSize(1, 1, 2)).decorators(List.of(new HangingLeavesTreeDecorator(BlockRegistry.HANGING_SPRUCE_LEAVES.getDefaultState(), 1, 2, .1f, false), new AlterGroundTreeDecorator(BlockStateProvider.of(Blocks.PODZOL)))).build());
        MEGA_PINE = ConfiguredFeatures.register("mega_pine_sequoia", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(BlockRegistry.SEQUOIA_LOG), new GiantTrunkPlacer(13, 2, 14), BlockStateProvider.of(Blocks.SPRUCE_LEAVES), new MegaPineFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), UniformIntProvider.create(13, 17)), new TwoLayersFeatureSize(1, 1, 2)).decorators(List.of(new HangingLeavesTreeDecorator(BlockRegistry.HANGING_SPRUCE_LEAVES.getDefaultState(), 1, 2, .1f, false), new AlterGroundTreeDecorator(BlockStateProvider.of(Blocks.PODZOL)))).build());
        DARK_OAK = ConfiguredFeatures.register("dark_oak_hanging_leaves", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.DARK_OAK_LOG), new DarkOakTrunkPlacer(6, 2, 1), BlockStateProvider.of(Blocks.DARK_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())).decorators(List.of(new HangingLeavesTreeDecorator(BlockRegistry.HANGING_DARK_OAK_LEAVES.getDefaultState(), 1, 2, .2f, false))).ignoreVines().build());
        SWAMP_OAK = ConfiguredFeatures.register("swamp_oak_hanging_leaves", Feature.TREE, builder(Blocks.OAK_LOG, Blocks.OAK_LEAVES, 5, 3, 0, 3).decorators(List.of(new HangingLeavesTreeDecorator(BlockRegistry.HANGING_OAK_LEAVES.getDefaultState(), 1, 3, .2f, false), new LeavesVineTreeDecorator(0.25F))).build());
        OAK = ConfiguredFeatures.register("oak_hanging_leaves", Feature.TREE, oak().decorators(List.of(new HangingLeavesTreeDecorator(BlockRegistry.HANGING_OAK_LEAVES.getDefaultState(), 1, 2, .2f, false))).build());
        BIRCH = ConfiguredFeatures.register("birch_hanging_leaves", Feature.TREE, birch().decorators(List.of(new HangingLeavesTreeDecorator(BlockRegistry.HANGING_BIRCH_LEAVES.getDefaultState(), 1, 2, .15f, false))).build());
        JUNGLE_TREE = ConfiguredFeatures.register("jungle_tree_hanging_leaves", Feature.TREE, jungle().decorators(List.of(new HangingLeavesTreeDecorator(BlockRegistry.HANGING_JUNGLE_LEAVES.getDefaultState(), 1, 3, .2f, false), new CocoaBeansTreeDecorator(0.2F), TrunkVineTreeDecorator.INSTANCE, new LeavesVineTreeDecorator(0.25F))).ignoreVines().build());
        JUNGLE_TREE_NO_VINE = ConfiguredFeatures.register("jungle_tree_no_vine_hanging_leaves", Feature.TREE, jungle().decorators(List.of(new HangingLeavesTreeDecorator(BlockRegistry.HANGING_JUNGLE_LEAVES.getDefaultState(), 1, 3, .2f, false))).ignoreVines().build());
        FANCY_OAK = ConfiguredFeatures.register("fancy_oak_hanging_leaves", Feature.TREE, fancyOak().decorators(List.of(new HangingLeavesTreeDecorator(BlockRegistry.HANGING_OAK_LEAVES.getDefaultState(), 1, 3, .2f, false))).build());
    }
}
