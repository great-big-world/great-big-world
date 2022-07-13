package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.world.placement.NoisePlacementModifier;
import com.github.creoii.greatbigworld.world.predicate.SkyVisiblePredicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.placementmodifier.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PlacedFeatureRegistry {
    public static RegistryEntry<PlacedFeature> DIRT_CAVES_VEGETATION;
    public static RegistryEntry<PlacedFeature> DIRT_CAVES_CEILING_VEGETATION;

    public static RegistryEntry<PlacedFeature> DEPOSIT_GOLD;
    public static RegistryEntry<PlacedFeature> DEPOSIT_IRON;
    public static RegistryEntry<PlacedFeature> DEPOSIT_COPPER;
    public static RegistryEntry<PlacedFeature> DEPOSIT_CLAY;
    public static RegistryEntry<PlacedFeature> DEPOSIT_PEAT;
    public static RegistryEntry<PlacedFeature> ORE_PACKED_DIRT;

    public static RegistryEntry<PlacedFeature> ALGAE_PATCH;

    public static RegistryEntry<PlacedFeature> MAHOGANY;
    public static RegistryEntry<PlacedFeature> TREES_PALO_VERDE;

    public static RegistryEntry<PlacedFeature> TWISTED_FUNGI;
    public static RegistryEntry<PlacedFeature> INVERTED_TWISTED_FUNGI;

    public static RegistryEntry<PlacedFeature> GRASSY_STONE_PATCH;
    public static RegistryEntry<PlacedFeature> GRASSY_DEEPSLATE_PATCH;

    //public static RegistryEntry<PlacedFeature> MOLTEN_CAVES_BASALT_COLUMNS;
    public static RegistryEntry<PlacedFeature> MOLTEN_CAVES_VEGETATION;
    public static RegistryEntry<PlacedFeature> MOLTEN_CAVES_CEILING_VEGETATION;
    public static RegistryEntry<PlacedFeature> LAVAROCK_SPIKE;
    public static RegistryEntry<PlacedFeature> SPARSE_MAGMA_DELTA;
    public static RegistryEntry<PlacedFeature> SPARSE_LAVA_DELTA;
    public static RegistryEntry<PlacedFeature> ORE_DIAMOND_EXTRA;
    public static RegistryEntry<PlacedFeature> ORE_DIAMOND_BURIED_EXTRA;
    public static RegistryEntry<PlacedFeature> ORE_DIAMOND_LARGE_EXTRA;

    @SuppressWarnings("deprecation")
    public static void register() {
        DIRT_CAVES_VEGETATION = PlacedFeatures.register("dirt_caves_vegetation", ConfiguredFeatureRegistry.DIRT_PATCH, CountPlacementModifier.of(125), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12), RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)), BiomePlacementModifier.of(), BlockFilterPlacementModifier.of(new SkyVisiblePredicate(BlockPos.ORIGIN, .4f)));
        DIRT_CAVES_CEILING_VEGETATION = PlacedFeatures.register("dirt_caves_ceiling_vegetation", ConfiguredFeatureRegistry.DIRT_PATCH_CEILING, CountPlacementModifier.of(125), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, EnvironmentScanPlacementModifier.of(Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12), RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-2)), BiomePlacementModifier.of());

        DEPOSIT_GOLD = PlacedFeatures.register("deposit_gold", ConfiguredFeatureRegistry.GOLD_DEPOSIT, modifiers(56, HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.getTop()), 1));
        DEPOSIT_IRON = PlacedFeatures.register("deposit_iron", ConfiguredFeatureRegistry.IRON_DEPOSIT, modifiers(42, HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.getTop()), 1));
        DEPOSIT_COPPER = PlacedFeatures.register("deposit_copper", ConfiguredFeatureRegistry.COPPER_DEPOSIT, modifiers(42, HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.getTop()), 1));
        DEPOSIT_CLAY = PlacedFeatures.register("deposit_clay", ConfiguredFeatureRegistry.CLAY_DEPOSIT, modifiers(42, HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.getTop()), 1));
        DEPOSIT_PEAT = PlacedFeatures.register("deposit_peat", ConfiguredFeatureRegistry.PEAT_DEPOSIT, modifiers(64, HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.getTop()), 1));
        ORE_PACKED_DIRT = PlacedFeatures.register("ore_packed_dirt", ConfiguredFeatureRegistry.ORE_PACKED_DIRT, modifiers(192, HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.getTop()), 1));

        ALGAE_PATCH = PlacedFeatures.register("algae_patch", ConfiguredFeatureRegistry.ALGAE_PATCH, heightmapModifiers(2, HeightmapPlacementModifier.of(Heightmap.Type.WORLD_SURFACE_WG), null, 2));

        MAHOGANY = PlacedFeatures.register("mahogany", ConfiguredFeatureRegistry.MAHOGANY, PlacedFeatures.createCountExtraModifier(6, .1f, 1), SquarePlacementModifier.of(), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG), BiomePlacementModifier.of(), PlacedFeatures.wouldSurvive(BlockRegistry.MAHOGANY_SAPLING));
        TREES_PALO_VERDE = PlacedFeatures.register("trees_palo_verde", ConfiguredFeatureRegistry.TREES_PALO_VERDE, PlacedFeatures.createCountExtraModifier(3, .1f, 2), SquarePlacementModifier.of(), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG), BiomePlacementModifier.of(), PlacedFeatures.wouldSurvive(BlockRegistry.PALO_VERDE_SAPLING));

        TWISTED_FUNGI = PlacedFeatures.register("twisted_fungi", ConfiguredFeatureRegistry.TWISTED_FUNGUS, CountMultilayerPlacementModifier.of(8), BiomePlacementModifier.of(), NoisePlacementModifier.of(NoiseParametersKeys.VEGETATION.getValue(), List.of(new NoisePlacementModifier.Range(-1d, 0d)), false));
        INVERTED_TWISTED_FUNGI = PlacedFeatures.register("inverted_twisted_fungi", ConfiguredFeatureRegistry.INVERTED_TWISTED_FUNGUS, CountMultilayerPlacementModifier.of(6), BiomePlacementModifier.of());

        GRASSY_STONE_PATCH = PlacedFeatures.register("grassy_stone_patch", ConfiguredFeatureRegistry.GRASSY_STONE_PATCH, CountPlacementModifier.of(100), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, EnvironmentScanPlacementModifier.of(Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12), RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)), HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.getTop()), BiomePlacementModifier.of());
        GRASSY_DEEPSLATE_PATCH = PlacedFeatures.register("grassy_deepslate_patch", ConfiguredFeatureRegistry.GRASSY_DEEPSLATE_PATCH, CountPlacementModifier.of(100), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, EnvironmentScanPlacementModifier.of(Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12), RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(5)), BiomePlacementModifier.of());

        //MOLTEN_CAVES_BASALT_COLUMNS = PlacedFeatures.register("molten_caves_basalt_columns", ConfiguredFeatureRegistry.MOLTEN_CAVES_BASALT_COLUMNS, RarityFilterPlacementModifier.of(50));
        MOLTEN_CAVES_VEGETATION = PlacedFeatures.register("molten_caves_vegetation", ConfiguredFeatureRegistry.MOLTEN_CAVES_VEGETATION, CountPlacementModifier.of(60), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12), BiomePlacementModifier.of());
        MOLTEN_CAVES_CEILING_VEGETATION = PlacedFeatures.register("molten_caves_ceiling_vegetation", ConfiguredFeatureRegistry.MOLTEN_CAVES_CEILING_VEGETATION, CountPlacementModifier.of(60), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, EnvironmentScanPlacementModifier.of(Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12), BiomePlacementModifier.of());
        LAVAROCK_SPIKE = PlacedFeatures.register("lavarock_spike", ConfiguredFeatureRegistry.LAVAROCK_SPIKE, CountPlacementModifier.of(UniformIntProvider.create(16, 32)), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE);
        SPARSE_MAGMA_DELTA = PlacedFeatures.register("sparse_magma_delta", ConfiguredFeatureRegistry.SPARSE_MAGMA_DELTA, CountMultilayerPlacementModifier.of(24), BiomePlacementModifier.of(), RarityFilterPlacementModifier.of(8));
        SPARSE_LAVA_DELTA = PlacedFeatures.register("sparse_lava_delta", ConfiguredFeatureRegistry.SPARSE_LAVA_DELTA, CountMultilayerPlacementModifier.of(20), BiomePlacementModifier.of(), RarityFilterPlacementModifier.of(5));
        ORE_DIAMOND_EXTRA = PlacedFeatures.register("ore_diamond_extra", ConfiguredFeatureRegistry.ORE_DIAMOND_EXTRA, modifiers(7, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)), 1));
        ORE_DIAMOND_BURIED_EXTRA = PlacedFeatures.register("ore_diamond_buried_extra", ConfiguredFeatureRegistry.ORE_DIAMOND_BURIED_EXTRA, modifiers(4, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)), 8));
        ORE_DIAMOND_LARGE_EXTRA = PlacedFeatures.register("ore_diamond_large_extra", ConfiguredFeatureRegistry.ORE_DIAMOND_LARGE_EXTRA, modifiers(9, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)), 1));
    }

    private static List<PlacementModifier> modifiers(int count, PlacementModifier heightModifier, int chance) {
        return List.of(CountPlacementModifier.of(count), SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of(), RarityFilterPlacementModifier.of(chance));
    }

    private static List<PlacementModifier> heightmapModifiers(int count, HeightmapPlacementModifier heightmapPlacement, @Nullable PlacementModifier heightModifier, int chance) {
        if (heightModifier == null) return List.of(CountPlacementModifier.of(count), SquarePlacementModifier.of(), heightmapPlacement, BiomePlacementModifier.of(), RarityFilterPlacementModifier.of(chance));
        else return List.of(CountPlacementModifier.of(count), SquarePlacementModifier.of(), heightModifier, heightmapPlacement, BiomePlacementModifier.of(), RarityFilterPlacementModifier.of(chance));
    }
}
