package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.decorator.BranchTreeDecorator;
import com.github.creoii.greatbigworld.world.placer.AspenFoliagePlacer;
import com.github.creoii.greatbigworld.world.placer.TwistingTrunkPlacer;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class GBWConfiguredFeatures implements Register {
    public static RegistryEntry<ConfiguredFeature<?, ?>> MAHOGANY;
    public static RegistryEntry<ConfiguredFeature<?, ?>> GREEN_ASPEN;
    public static RegistryEntry<ConfiguredFeature<?, ?>> YELLOW_ASPEN;
    public static RegistryEntry<ConfiguredFeature<?, ?>> ACAI;
    public static RegistryKey<ConfiguredFeature<?, ?>> GREEN_ASPEN_KEY;
    public static RegistryKey<ConfiguredFeature<?, ?>> YELLOW_ASPEN_KEY;
    public static RegistryKey<ConfiguredFeature<?, ?>> ACAI_KEY;

    @Override
    public void register() {
        MAHOGANY = BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(NAMESPACE, "mahogany"),
                new ConfiguredFeature<>(Feature.TREE, new TreeFeatureConfig.Builder(SimpleBlockStateProvider.of(GBWBlocks.MAHOGANY.log().getDefaultState()), new TwistingTrunkPlacer(3, 2, 0, UniformIntProvider.create(4, 6), UniformIntProvider.create(1, 2), UniformIntProvider.create(2, 3), UniformIntProvider.create(1, 2), false), SimpleBlockStateProvider.of(GBWBlocks.MAHOGANY_LEAVES), new AcaciaFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(1)), new TwoLayersFeatureSize(9, 2, 6)).dirtProvider(SimpleBlockStateProvider.of(Blocks.DIRT)).build())
        );
        GREEN_ASPEN = BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(NAMESPACE, "green_aspen"),
                new ConfiguredFeature<>(Feature.TREE, new TreeFeatureConfig.Builder(SimpleBlockStateProvider.of(GBWBlocks.ASPEN.log().getDefaultState()), new StraightTrunkPlacer(6, 3, 1), SimpleBlockStateProvider.of(GBWBlocks.GREEN_ASPEN_LEAVES), new AspenFoliagePlacer(UniformIntProvider.create(2, 3), ConstantIntProvider.create(2), UniformIntProvider.create(2, 3)), new TwoLayersFeatureSize(2, 0, 1)).decorators(List.of(new BranchTreeDecorator(GBWBlocks.ASPEN.log().getDefaultState(), .2f, false, "minecraft:leaves", UniformIntProvider.create(2, 6), true))).build())
        );
        YELLOW_ASPEN = BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(NAMESPACE, "yellow_aspen"),
                new ConfiguredFeature<>(Feature.TREE, new TreeFeatureConfig.Builder(SimpleBlockStateProvider.of(GBWBlocks.ASPEN.log().getDefaultState()), new StraightTrunkPlacer(6, 3, 1), SimpleBlockStateProvider.of(GBWBlocks.YELLOW_ASPEN_LEAVES), new AspenFoliagePlacer(UniformIntProvider.create(2, 3), ConstantIntProvider.create(2), UniformIntProvider.create(2, 3)), new TwoLayersFeatureSize(2, 0, 1)).decorators(List.of(new BranchTreeDecorator(GBWBlocks.ASPEN.log().getDefaultState(), .2f, false, "minecraft:leaves", UniformIntProvider.create(2, 6), true))).build())
        );
        // todo
        ACAI = BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(NAMESPACE, "acai"),
                new ConfiguredFeature<>(Feature.TREE, new TreeFeatureConfig.Builder(SimpleBlockStateProvider.of(GBWBlocks.ACAI.log().getDefaultState()), new StraightTrunkPlacer(6, 3, 1), SimpleBlockStateProvider.of(GBWBlocks.ACAI_LEAVES), new AspenFoliagePlacer(UniformIntProvider.create(2, 3), ConstantIntProvider.create(2), UniformIntProvider.create(2, 3)), new TwoLayersFeatureSize(2, 0, 1)).decorators(List.of(new BranchTreeDecorator(GBWBlocks.ASPEN.log().getDefaultState(), .2f, false, "minecraft:leaves", UniformIntProvider.create(2, 6), true))).build())
        );
        GREEN_ASPEN_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(NAMESPACE, "green_aspen"));
        YELLOW_ASPEN_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(NAMESPACE, "yellow_aspen"));
        ACAI_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(NAMESPACE, "acai"));
    }
}
