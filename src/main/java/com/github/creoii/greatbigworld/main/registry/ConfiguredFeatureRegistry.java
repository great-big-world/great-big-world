package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.world.decorator.HangingLeavesTreeDecorator;
import com.github.creoii.greatbigworld.world.feature.config.BlockSpikeFeatureConfig;
import com.github.creoii.greatbigworld.world.feature.config.MultiRandomSpreadFeatureConfig;
import com.github.creoii.greatbigworld.world.feature.config.RandomSpreadFeatureConfig;
import com.github.creoii.greatbigworld.world.placer.TwistingTrunkPlacer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.floatprovider.UniformFloatProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
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

    public static RegistryEntry<ConfiguredFeature<MultiRandomSpreadFeatureConfig, ?>> ALGAE_PATCH;

    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MAHOGANY;
    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PALO_VERDE;
    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PALO_VERDE_BIG;
    public static RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> TREES_PALO_VERDE;

    public static RegistryEntry<ConfiguredFeature<HugeFungusFeatureConfig, ?>> TWISTED_FUNGUS;
    public static RegistryEntry<ConfiguredFeature<HugeFungusFeatureConfig, ?>> INVERTED_TWISTED_FUNGUS;

    public static RegistryEntry<ConfiguredFeature<VegetationPatchFeatureConfig, ?>> GRASSY_STONE_PATCH;
    public static RegistryEntry<ConfiguredFeature<VegetationPatchFeatureConfig, ?>> GRASSY_DEEPSLATE_PATCH;

    //public static RegistryEntry<ConfiguredFeature<BasaltColumnsFeatureConfig, ?>> SPARSE_SMALL_BASALT_COLUMNS;
    //public static RegistryEntry<ConfiguredFeature<BasaltColumnsFeatureConfig, ?>> SPARSE_LARGE_BASALT_COLUMNS;
    //public static RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> MOLTEN_CAVES_BASALT_COLUMNS;
    public static RegistryEntry<ConfiguredFeature<VegetationPatchFeatureConfig, ?>> MOLTEN_CAVES_VEGETATION;
    public static RegistryEntry<ConfiguredFeature<VegetationPatchFeatureConfig, ?>> MOLTEN_CAVES_CEILING_VEGETATION;
    public static RegistryEntry<ConfiguredFeature<BlockSpikeFeatureConfig, ?>> LAVAROCK_SPIKE;
    public static RegistryEntry<ConfiguredFeature<DeltaFeatureConfig, ?>> SPARSE_MAGMA_DELTA;
    public static RegistryEntry<ConfiguredFeature<DeltaFeatureConfig, ?>> SPARSE_LAVA_DELTA;
    public static RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_DIAMOND_EXTRA;
    public static RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_DIAMOND_BURIED_EXTRA;
    public static RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_DIAMOND_LARGE_EXTRA;

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

        ALGAE_PATCH = ConfiguredFeatures.register("algae_patch", FeatureRegistry.MULTI_RANDOM_SPREAD, new MultiRandomSpreadFeatureConfig(UniformIntProvider.create(1, 4), List.of(new RandomSpreadFeatureConfig(BlockStateProvider.of(BlockRegistry.ALGAE.getDefaultState().with(Properties.DOWN, true)), ConstantIntProvider.create(3), ConstantIntProvider.create(4), false, List.of()), new RandomSpreadFeatureConfig(BlockStateProvider.of(BlockRegistry.ALGAE.getDefaultState().with(Properties.DOWN, true)), ConstantIntProvider.create(3), ConstantIntProvider.create(6), false, List.of())), List.of(4, 8)));

        MAHOGANY = ConfiguredFeatures.register("mahogany", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(BlockRegistry.MAHOGANY_LOG), new TwistingTrunkPlacer(3, 2, 0, UniformIntProvider.create(4, 6), UniformIntProvider.create(1, 2), UniformIntProvider.create(2, 3), UniformIntProvider.create(1, 2), true), BlockStateProvider.of(BlockRegistry.MAHOGANY_LEAVES), new AcaciaFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(1)), new TwoLayersFeatureSize(4, 2, 3)).decorators(List.of(new HangingLeavesTreeDecorator(BlockRegistry.HANGING_MAHOGANY_LEAVES.getDefaultState(), 1, 4, .25f, true))).ignoreVines().build());
        PALO_VERDE = ConfiguredFeatures.register("palo_verde", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(BlockRegistry.PALO_VERDE_LOG), new TwistingTrunkPlacer(1, 1, 0, UniformIntProvider.create(1, 2), UniformIntProvider.create(1, 2), UniformIntProvider.create(1, 2), UniformIntProvider.create(1, 2), true), BlockStateProvider.of(BlockRegistry.PALO_VERDE_LEAVES), new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1)), new TwoLayersFeatureSize(2, 1, 1)).decorators(List.of(new HangingLeavesTreeDecorator(BlockRegistry.HANGING_PALO_VERDE_LEAVES.getDefaultState(), 1, 2, .15f, false))).ignoreVines().build());
        PALO_VERDE_BIG = ConfiguredFeatures.register("palo_verde_big", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(BlockRegistry.PALO_VERDE_LOG), new TwistingTrunkPlacer(1, 1, 0, UniformIntProvider.create(2, 3), UniformIntProvider.create(1, 2), UniformIntProvider.create(1, 2), UniformIntProvider.create(1, 3), true), BlockStateProvider.of(BlockRegistry.PALO_VERDE_LEAVES), new AcaciaFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(1)), new TwoLayersFeatureSize(3, 1, 2)).decorators(List.of(new HangingLeavesTreeDecorator(BlockRegistry.HANGING_PALO_VERDE_LEAVES.getDefaultState(), 1, 2, .15f, false))).ignoreVines().build());
        TREES_PALO_VERDE = ConfiguredFeatures.register("trees_palo_verde", Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(new RandomFeatureEntry(PlacedFeatures.createEntry(PALO_VERDE_BIG, PlacedFeatures.wouldSurvive(BlockRegistry.PALO_VERDE_SAPLING)), .1f)), PlacedFeatures.createEntry(PALO_VERDE, PlacedFeatures.wouldSurvive(BlockRegistry.PALO_VERDE_SAPLING))));

        TWISTED_FUNGUS = ConfiguredFeatures.register("twisted_fungus", FeatureRegistry.HUGE_TWISTED_FUNGUS, new HugeFungusFeatureConfig(BlockRegistry.TWISTED_NYLIUM.getDefaultState(), BlockRegistry.TWISTED_STEM.getDefaultState(), BlockRegistry.TWISTED_WART_BLOCK.getDefaultState(), BlockRegistry.TWISTED_SHROOMLIGHT.getDefaultState(), false));
        INVERTED_TWISTED_FUNGUS = ConfiguredFeatures.register("inverted_twisted_fungus", FeatureRegistry.HUGE_TWISTED_FUNGUS, new HugeFungusFeatureConfig(BlockRegistry.TWISTED_NYLIUM.getDefaultState(), BlockRegistry.TWISTED_STEM.getDefaultState(), BlockRegistry.TWISTED_WART_BLOCK.getDefaultState(), BlockRegistry.TWISTED_SHROOMLIGHT.getDefaultState(), false));

        GRASSY_STONE_PATCH = ConfiguredFeatures.register("grassy_stone_patch", Feature.VEGETATION_PATCH, new VegetationPatchFeatureConfig(BlockTags.BASE_STONE_OVERWORLD, SimpleBlockStateProvider.of(BlockRegistry.GRASSY_STONE), PlacedFeatures.createEntry(DIRT_ROOTS), VerticalSurfaceType.FLOOR, ConstantIntProvider.create(1), 0f, 1, 0f, UniformIntProvider.create(4, 7), 0.75F));
        GRASSY_DEEPSLATE_PATCH = ConfiguredFeatures.register("grassy_deepslate_patch", Feature.VEGETATION_PATCH, new VegetationPatchFeatureConfig(BlockTags.BASE_STONE_OVERWORLD, SimpleBlockStateProvider.of(BlockRegistry.GRASSY_DEEPSLATE), PlacedFeatures.createEntry(DIRT_ROOTS), VerticalSurfaceType.FLOOR, ConstantIntProvider.create(1), 0f, 1, 0f, UniformIntProvider.create(4, 7), 0.75F));

        //SPARSE_SMALL_BASALT_COLUMNS = ConfiguredFeatures.register("sparse_small_basalt_columns", Feature.BASALT_COLUMNS, new BasaltColumnsFeatureConfig(ConstantIntProvider.create(1), UniformIntProvider.create(2, 3)));
        //SPARSE_LARGE_BASALT_COLUMNS = ConfiguredFeatures.register("sparse_large_basalt_columns", Feature.BASALT_COLUMNS, new BasaltColumnsFeatureConfig(UniformIntProvider.create(1, 2), UniformIntProvider.create(3, 5)));
        //MOLTEN_CAVES_BASALT_COLUMNS = ConfiguredFeatures.register("molten_caves_basalt_columns", Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(new RandomFeatureEntry(PlacedFeatures.createEntry(ConfiguredFeatureRegistry.SPARSE_LARGE_BASALT_COLUMNS), .2f)), PlacedFeatures.createEntry(ConfiguredFeatureRegistry.SPARSE_SMALL_BASALT_COLUMNS)));
        MOLTEN_CAVES_VEGETATION = ConfiguredFeatures.register("molten_caves_vegetation", Feature.VEGETATION_PATCH, new VegetationPatchFeatureConfig(BlockTags.MOSS_REPLACEABLE, SimpleBlockStateProvider.of(BlockRegistry.LAVAROCK), UndergroundPlacedFeatures.LUSH_CAVES_VEGETATION, VerticalSurfaceType.FLOOR, UniformIntProvider.create(2, 3), 0.0F, 5, 0.0F, UniformIntProvider.create(2, 5), 0.3F));
        MOLTEN_CAVES_CEILING_VEGETATION = ConfiguredFeatures.register("molten_caves_ceiling_vegetation", Feature.VEGETATION_PATCH, new VegetationPatchFeatureConfig(BlockTags.MOSS_REPLACEABLE, SimpleBlockStateProvider.of(BlockRegistry.LAVAROCK), UndergroundPlacedFeatures.LUSH_CAVES_VEGETATION, VerticalSurfaceType.FLOOR, UniformIntProvider.create(2, 3), 0.0F, 5, 0.0F, UniformIntProvider.create(2, 5), 0.3F));
        LAVAROCK_SPIKE = ConfiguredFeatures.register("lavarock_spike", FeatureRegistry.BLOCK_SPIKE, new BlockSpikeFeatureConfig(SimpleBlockStateProvider.of(Blocks.SMOOTH_BASALT), 30, UniformIntProvider.create(5, 9), UniformFloatProvider.create(0.4F, 1.2F), 0.25F, UniformFloatProvider.create(0.33F, 1.0F), UniformFloatProvider.create(0.33F, 1.0F), UniformFloatProvider.create(0.0F, 0.25F), 7, 0.5F));
        SPARSE_MAGMA_DELTA = ConfiguredFeatures.register("sparse_magma_delta", Feature.DELTA_FEATURE, new DeltaFeatureConfig(BlockRegistry.MOLTEN_MAGMA.getDefaultState(), Blocks.MAGMA_BLOCK.getDefaultState(), UniformIntProvider.create(3, 6), UniformIntProvider.create(0, 2)));
        SPARSE_LAVA_DELTA = ConfiguredFeatures.register("sparse_lava_delta", Feature.DELTA_FEATURE, new DeltaFeatureConfig(Blocks.LAVA.getDefaultState(), Blocks.MAGMA_BLOCK.getDefaultState(), UniformIntProvider.create(4, 8), UniformIntProvider.create(1, 2)));
        ORE_DIAMOND_EXTRA = ConfiguredFeatures.register("ore_diamond_extra", Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.DIAMOND_ORES, 6, 0.5F));
        ORE_DIAMOND_BURIED_EXTRA = ConfiguredFeatures.register("ore_diamond_buried_extra", Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.DIAMOND_ORES, 10, 1.0F));
        ORE_DIAMOND_LARGE_EXTRA = ConfiguredFeatures.register("ore_diamond_large_extra", Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.DIAMOND_ORES, 16, 0.7F));
    }
}
