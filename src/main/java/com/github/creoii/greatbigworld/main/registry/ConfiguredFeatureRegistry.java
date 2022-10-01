package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.block.GlimmeringMushroomBlock;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.decorator.BranchTreeDecorator;
import com.github.creoii.greatbigworld.world.placer.AspenFoliagePlacer;
import com.github.creoii.greatbigworld.world.placer.TwistingTrunkPlacer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class ConfiguredFeatureRegistry implements Register {
    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MAHOGANY;
    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> YELLOW_ASPEN;
    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> GREEN_ASPEN;
    public static RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_DAYLIGHT_MUSHROOMS;
    public static RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_DARKBLIGHT_MUSHROOMS;

    @Override
    public void register() { }

    static {
        MAHOGANY = ConfiguredFeatures.register("mahogany", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(BlockRegistry.MAHOGANY.log()), new TwistingTrunkPlacer(3, 2, 0, UniformIntProvider.create(4, 6), UniformIntProvider.create(1, 2), UniformIntProvider.create(2, 3), UniformIntProvider.create(1, 2), false), BlockStateProvider.of(BlockRegistry.MAHOGANY_LEAVES), new AcaciaFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(1)), new TwoLayersFeatureSize(9, 2, 6)).decorators(List.of(new LeavesVineTreeDecorator(.1f))).ignoreVines().build());
        YELLOW_ASPEN = ConfiguredFeatures.register("yellow_aspen", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(BlockRegistry.ASPEN.log()), new StraightTrunkPlacer(6, 3, 1), BlockStateProvider.of(BlockRegistry.YELLOW_ASPEN_LEAVES), new AspenFoliagePlacer(ConstantIntProvider.create(2), UniformIntProvider.create(2, 3), UniformIntProvider.create(2, 3)), new TwoLayersFeatureSize(2, 0, 1)).decorators(List.of(new BranchTreeDecorator(BlockRegistry.ASPEN.log().getDefaultState(), .1f, false, UniformIntProvider.create(3, 6)))).build());
        GREEN_ASPEN = ConfiguredFeatures.register("green_aspen", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(BlockRegistry.ASPEN.log()), new StraightTrunkPlacer(6, 3, 1), BlockStateProvider.of(BlockRegistry.GREEN_ASPEN_LEAVES), new AspenFoliagePlacer(ConstantIntProvider.create(2), UniformIntProvider.create(2, 3), UniformIntProvider.create(2, 3)), new TwoLayersFeatureSize(2, 0, 1)).decorators(List.of(new BranchTreeDecorator(BlockRegistry.ASPEN.log().getDefaultState(), .1f, false, UniformIntProvider.create(3, 6)))).build());
        PATCH_DAYLIGHT_MUSHROOMS = ConfiguredFeatures.register("patch_daylight_mushrooms", Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder()
                .add(BlockRegistry.DAYLIGHT_MUSHROOM.getDefaultState().with(GlimmeringMushroomBlock.MUSHROOMS, 1).with(GlimmeringMushroomBlock.LIGHT, 4), 6)
                .add(BlockRegistry.DAYLIGHT_MUSHROOM.getDefaultState().with(GlimmeringMushroomBlock.MUSHROOMS, 2).with(GlimmeringMushroomBlock.LIGHT, 4), 4)
                .add(BlockRegistry.DAYLIGHT_MUSHROOM.getDefaultState().with(GlimmeringMushroomBlock.MUSHROOMS, 3).with(GlimmeringMushroomBlock.LIGHT, 4), 2)
                .add(BlockRegistry.DAYLIGHT_MUSHROOM.getDefaultState().with(GlimmeringMushroomBlock.MUSHROOMS, 1).with(GlimmeringMushroomBlock.LIGHT, 8), 6)
                .add(BlockRegistry.DAYLIGHT_MUSHROOM.getDefaultState().with(GlimmeringMushroomBlock.MUSHROOMS, 2).with(GlimmeringMushroomBlock.LIGHT, 8), 4)
                .add(BlockRegistry.DAYLIGHT_MUSHROOM.getDefaultState().with(GlimmeringMushroomBlock.MUSHROOMS, 3).with(GlimmeringMushroomBlock.LIGHT, 8), 2)
                .add(BlockRegistry.DAYLIGHT_MUSHROOM.getDefaultState().with(GlimmeringMushroomBlock.MUSHROOMS, 1).with(GlimmeringMushroomBlock.LIGHT, 12), 6)
                .add(BlockRegistry.DAYLIGHT_MUSHROOM.getDefaultState().with(GlimmeringMushroomBlock.MUSHROOMS, 2).with(GlimmeringMushroomBlock.LIGHT, 12), 4)
                .add(BlockRegistry.DAYLIGHT_MUSHROOM.getDefaultState().with(GlimmeringMushroomBlock.MUSHROOMS, 3).with(GlimmeringMushroomBlock.LIGHT, 12), 2)
                .build())), List.of(Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL)));
        PATCH_DARKBLIGHT_MUSHROOMS = ConfiguredFeatures.register("patch_darkblight_mushrooms", Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder()
                .add(BlockRegistry.DARKBLIGHT_MUSHROOM.getDefaultState().with(GlimmeringMushroomBlock.MUSHROOMS, 1).with(GlimmeringMushroomBlock.LIGHT, 4), 6)
                .add(BlockRegistry.DARKBLIGHT_MUSHROOM.getDefaultState().with(GlimmeringMushroomBlock.MUSHROOMS, 2).with(GlimmeringMushroomBlock.LIGHT, 4), 4)
                .add(BlockRegistry.DARKBLIGHT_MUSHROOM.getDefaultState().with(GlimmeringMushroomBlock.MUSHROOMS, 3).with(GlimmeringMushroomBlock.LIGHT, 4), 2)
                .add(BlockRegistry.DARKBLIGHT_MUSHROOM.getDefaultState().with(GlimmeringMushroomBlock.MUSHROOMS, 1).with(GlimmeringMushroomBlock.LIGHT, 8), 6)
                .add(BlockRegistry.DARKBLIGHT_MUSHROOM.getDefaultState().with(GlimmeringMushroomBlock.MUSHROOMS, 2).with(GlimmeringMushroomBlock.LIGHT, 8), 4)
                .add(BlockRegistry.DARKBLIGHT_MUSHROOM.getDefaultState().with(GlimmeringMushroomBlock.MUSHROOMS, 3).with(GlimmeringMushroomBlock.LIGHT, 8), 2)
                .add(BlockRegistry.DARKBLIGHT_MUSHROOM.getDefaultState().with(GlimmeringMushroomBlock.MUSHROOMS, 1).with(GlimmeringMushroomBlock.LIGHT, 12), 6)
                .add(BlockRegistry.DARKBLIGHT_MUSHROOM.getDefaultState().with(GlimmeringMushroomBlock.MUSHROOMS, 2).with(GlimmeringMushroomBlock.LIGHT, 12), 4)
                .add(BlockRegistry.DARKBLIGHT_MUSHROOM.getDefaultState().with(GlimmeringMushroomBlock.MUSHROOMS, 3).with(GlimmeringMushroomBlock.LIGHT, 12), 2)
                .build())), List.of(Blocks.STONE, Blocks.DEEPSLATE, Blocks.GRANITE, Blocks.ANDESITE, Blocks.DIORITE, Blocks.OAK_PLANKS, Blocks.DARK_OAK_PLANKS, Blocks.DIRT, Blocks.GRAVEL, Blocks.TUFF, Blocks.CALCITE, Blocks.DEEPSLATE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS, Blocks.COBBLED_DEEPSLATE, Blocks.DEEPSLATE_TILES, Blocks.CHISELED_DEEPSLATE, Blocks.SCULK)));
    }
}
