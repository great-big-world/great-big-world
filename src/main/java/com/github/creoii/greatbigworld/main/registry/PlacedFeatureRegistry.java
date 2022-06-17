package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.predicate.SkyVisiblePredicate;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
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

        TWISTED_FUNGI = PlacedFeatures.register("twisted_fungi", ConfiguredFeatureRegistry.TWISTED_FUNGUS, CountMultilayerPlacementModifier.of(8), BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiers(int count, PlacementModifier heightModifier, int chance) {
        return List.of(CountPlacementModifier.of(count), SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of(), RarityFilterPlacementModifier.of(chance));
    }

    private static List<PlacementModifier> countExtraModifiers(int count, float extraChance, int extraCount, PlacementModifier heightModifier, int chance) {
        return List.of(PlacedFeatures.createCountExtraModifier(count, extraChance, extraCount), SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of(), RarityFilterPlacementModifier.of(chance));
    }

    private static List<PlacementModifier> heightmapModifiers(int count, HeightmapPlacementModifier heightmapPlacement, @Nullable PlacementModifier heightModifier, int chance) {
        if (heightModifier == null) return List.of(CountPlacementModifier.of(count), SquarePlacementModifier.of(), heightmapPlacement, BiomePlacementModifier.of(), RarityFilterPlacementModifier.of(chance));
        else return List.of(CountPlacementModifier.of(count), SquarePlacementModifier.of(), heightModifier, heightmapPlacement, BiomePlacementModifier.of(), RarityFilterPlacementModifier.of(chance));
    }
}
