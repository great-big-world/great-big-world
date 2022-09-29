package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.decorator.BranchTreeDecorator;
import com.github.creoii.greatbigworld.world.placer.AspenFoliagePlacer;
import com.github.creoii.greatbigworld.world.placer.TwistingTrunkPlacer;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class ConfiguredFeatureRegistry implements Register {
    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MAHOGANY;
    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> YELLOW_ASPEN;
    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> YELLOW_ASPEN_2;
    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> GREEN_ASPEN;
    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> GREEN_ASPEN_2;
    public static RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> TREES_ASPEN_MIXED;
    public static RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> TREES_ASPEN_GREEN;

    @Override
    public void register() {
        MAHOGANY = ConfiguredFeatures.register("mahogany", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(BlockRegistry.MAHOGANY.log()), new TwistingTrunkPlacer(3, 2, 0, UniformIntProvider.create(4, 6), UniformIntProvider.create(1, 2), UniformIntProvider.create(2, 3), UniformIntProvider.create(1, 2), false), BlockStateProvider.of(BlockRegistry.MAHOGANY_LEAVES), new AcaciaFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(1)), new TwoLayersFeatureSize(9, 2, 6)).ignoreVines().build());
        YELLOW_ASPEN = ConfiguredFeatures.register("yellow_aspen", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(BlockRegistry.ASPEN.log()), new StraightTrunkPlacer(6, 4, 1), BlockStateProvider.of(BlockRegistry.YELLOW_ASPEN_LEAVES), new AspenFoliagePlacer(ConstantIntProvider.create(2), UniformIntProvider.create(2, 3), UniformIntProvider.create(2, 3)), new TwoLayersFeatureSize(2, 0, 1)).decorators(List.of(new BranchTreeDecorator(BlockRegistry.ASPEN.log().getDefaultState(), .1f, false, UniformIntProvider.create(3, 6)))).build());
        YELLOW_ASPEN_2 = ConfiguredFeatures.register("yellow_aspen_2", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(BlockRegistry.ASPEN.log()), new StraightTrunkPlacer(7, 4, 2), BlockStateProvider.of(BlockRegistry.YELLOW_ASPEN_LEAVES), new AspenFoliagePlacer(ConstantIntProvider.create(2), UniformIntProvider.create(3, 4), UniformIntProvider.create(4, 5)), new TwoLayersFeatureSize(2, 0, 1)).decorators(List.of(new BranchTreeDecorator(BlockRegistry.ASPEN.log().getDefaultState(), .1f, false, UniformIntProvider.create(3, 6)))).build());
        GREEN_ASPEN = ConfiguredFeatures.register("green_aspen", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(BlockRegistry.ASPEN.log()), new StraightTrunkPlacer(6, 4, 2), BlockStateProvider.of(BlockRegistry.GREEN_ASPEN_LEAVES), new AspenFoliagePlacer(ConstantIntProvider.create(2), UniformIntProvider.create(2, 3), UniformIntProvider.create(2, 3)), new TwoLayersFeatureSize(2, 0, 1)).decorators(List.of(new BranchTreeDecorator(BlockRegistry.ASPEN.log().getDefaultState(), .1f, false, UniformIntProvider.create(3, 6)))).build());
        GREEN_ASPEN_2 = ConfiguredFeatures.register("green_aspen_2", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(BlockRegistry.ASPEN.log()), new StraightTrunkPlacer(7, 4, 2), BlockStateProvider.of(BlockRegistry.GREEN_ASPEN_LEAVES), new AspenFoliagePlacer(ConstantIntProvider.create(2), UniformIntProvider.create(3, 4), UniformIntProvider.create(4, 5)), new TwoLayersFeatureSize(2, 0, 1)).decorators(List.of(new BranchTreeDecorator(BlockRegistry.ASPEN.log().getDefaultState(), .1f, false, UniformIntProvider.create(3, 6)))).build());
        TREES_ASPEN_MIXED = ConfiguredFeatures.register("trees_aspen_mixed", Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(new RandomFeatureEntry(PlacedFeatureRegistry.YELLOW_ASPEN_2, .25f), new RandomFeatureEntry(PlacedFeatureRegistry.GREEN_ASPEN, .2f), new RandomFeatureEntry(PlacedFeatureRegistry.GREEN_ASPEN_2, .1f)), PlacedFeatureRegistry.YELLOW_ASPEN));
        TREES_ASPEN_GREEN = ConfiguredFeatures.register("trees_aspen_green", Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(new RandomFeatureEntry(PlacedFeatureRegistry.GREEN_ASPEN_2, .25f)), PlacedFeatureRegistry.GREEN_ASPEN));
    }
}
