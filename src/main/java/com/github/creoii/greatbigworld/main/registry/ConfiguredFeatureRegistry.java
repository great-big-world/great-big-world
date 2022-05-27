package com.github.creoii.greatbigworld.main.registry;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import java.util.List;

public class ConfiguredFeatureRegistry {
    public static RegistryEntry<ConfiguredFeature<SimpleBlockFeatureConfig, ?>> DIRT_VEGETATION;
    public static RegistryEntry<ConfiguredFeature<SimpleBlockFeatureConfig, ?>> DIRT_ROOTS;
    public static RegistryEntry<ConfiguredFeature<VegetationPatchFeatureConfig, ?>> DIRT_PATCH;
    public static RegistryEntry<ConfiguredFeature<VegetationPatchFeatureConfig, ?>> DIRT_PATCH_CEILING;

    public static RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> GOLD_DEPOSIT;
    public static RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> IRON_DEPOSIT;
    public static RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> COPPER_DEPOSIT;
    public static RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> CLAY_DEPOSIT;
    public static RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> PEAT_DEPOSIT;
    public static RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_PACKED_DIRT;

    public static void register() {
        DIRT_VEGETATION = ConfiguredFeatures.register("dirt_vegetation", Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DANDELION.getDefaultState(), 4).add(BlockRegistry.MARIGOLD.getDefaultState(), 4).add(Blocks.SUNFLOWER.getDefaultState(), 7).add(Blocks.MOSS_CARPET.getDefaultState(), 25).add(Blocks.GRASS.getDefaultState(), 50).add(Blocks.TALL_GRASS.getDefaultState(), 10))));
        DIRT_ROOTS = ConfiguredFeatures.register("dirt_roots", Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(SimpleBlockStateProvider.of(Blocks.HANGING_ROOTS)));
        DIRT_PATCH = ConfiguredFeatures.register("dirt_patch", Feature.VEGETATION_PATCH, new VegetationPatchFeatureConfig(BlockTags.MOSS_REPLACEABLE, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(BlockRegistry.PACKED_DIRT.getDefaultState(), 3).add(Blocks.ROOTED_DIRT.getDefaultState(), 2).build()), PlacedFeatures.createEntry(DIRT_VEGETATION), VerticalSurfaceType.FLOOR, ConstantIntProvider.create(1), 1.0F, 7, 0.2F, UniformIntProvider.create(4, 7), 0.3F));
        DIRT_PATCH_CEILING = ConfiguredFeatures.register("dirt_patch_ceiling", Feature.VEGETATION_PATCH, new VegetationPatchFeatureConfig(BlockTags.MOSS_REPLACEABLE, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(BlockRegistry.PACKED_DIRT.getDefaultState(), 1).add(Blocks.ROOTED_DIRT.getDefaultState(), 3).build()), PlacedFeatures.createEntry(DIRT_ROOTS), VerticalSurfaceType.CEILING, UniformIntProvider.create(1, 2), 1.0F, 5, 0.1F, UniformIntProvider.create(4, 7), 0.3F));

        GOLD_DEPOSIT = ConfiguredFeatures.register("gold_deposit", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(new TagMatchRuleTest(BlockTags.DIRT), BlockRegistry.GOLD_DEPOSIT.getDefaultState())), 9));
        IRON_DEPOSIT = ConfiguredFeatures.register("iron_deposit", Feature.SCATTERED_ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(new TagMatchRuleTest(BlockTags.DIRT), BlockRegistry.IRON_DEPOSIT.getDefaultState())), 6));
        COPPER_DEPOSIT = ConfiguredFeatures.register("copper_deposit", Feature.SCATTERED_ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(new TagMatchRuleTest(BlockTags.DIRT), BlockRegistry.COPPER_DEPOSIT.getDefaultState())), 9));
        CLAY_DEPOSIT = ConfiguredFeatures.register("clay_deposit", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(new TagMatchRuleTest(BlockTags.DIRT), BlockRegistry.CLAY_DEPOSIT.getDefaultState())), 12));
        PEAT_DEPOSIT = ConfiguredFeatures.register("peat_deposit", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(new TagMatchRuleTest(BlockTags.DIRT), BlockRegistry.PEAT_DEPOSIT.getDefaultState())), 14));
        ORE_PACKED_DIRT = ConfiguredFeatures.register("ore_packed_dirt", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), BlockRegistry.PACKED_DIRT.getDefaultState())), 64));
    }
}
