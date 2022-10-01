package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.main.util.Tags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

public class PlacedFeatureRegistry implements Register {
    public static RegistryEntry<PlacedFeature> MAHOGANY;
    public static RegistryEntry<PlacedFeature> TREES_MAHOGANY;
    public static RegistryEntry<PlacedFeature> YELLOW_ASPEN;
    public static RegistryEntry<PlacedFeature> GREEN_ASPEN;
    public static RegistryEntry<PlacedFeature> TREES_ASPEN_YELLOW_DENSE;
    public static RegistryEntry<PlacedFeature> TREES_ASPEN_YELLOW_SPARSE;
    public static RegistryEntry<PlacedFeature> TREES_ASPEN_GREEN_DENSE;
    public static RegistryEntry<PlacedFeature> TREES_ASPEN_GREEN_SPARSE;
    public static RegistryEntry<PlacedFeature> PATCH_DAYLIGHT_MUSHROOMS;
    public static RegistryEntry<PlacedFeature> PATCH_DARKBLIGHT_MUSHROOMS;

    @Override
    public void register() {
        if (TREES_MAHOGANY.getKey().isPresent()) BiomeModifications.addFeature(BiomeSelectors.tag(Tags.BiomeTags.MAHOGANY_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, TREES_MAHOGANY.getKey().get());
        if (TREES_ASPEN_YELLOW_SPARSE.getKey().isPresent()) BiomeModifications.addFeature(BiomeSelectors.tag(Tags.BiomeTags.YELLOW_ASPEN_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, TREES_ASPEN_YELLOW_SPARSE.getKey().get());
        if (TREES_ASPEN_GREEN_SPARSE.getKey().isPresent()) BiomeModifications.addFeature(BiomeSelectors.tag(Tags.BiomeTags.GREEN_ASPEN_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, TREES_ASPEN_GREEN_SPARSE.getKey().get());
        if (PATCH_DAYLIGHT_MUSHROOMS.getKey().isPresent()) BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, BiomeKeys.OLD_GROWTH_PINE_TAIGA), GenerationStep.Feature.VEGETAL_DECORATION, PATCH_DAYLIGHT_MUSHROOMS.getKey().get());
        if (PATCH_DARKBLIGHT_MUSHROOMS.getKey().isPresent()) BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.VEGETAL_DECORATION, PATCH_DARKBLIGHT_MUSHROOMS.getKey().get());
    }

    static {
        MAHOGANY = PlacedFeatures.register("mahogany", ConfiguredFeatureRegistry.MAHOGANY, PlacedFeatures.wouldSurvive(BlockRegistry.MAHOGANY_SAPLING));
        TREES_MAHOGANY = PlacedFeatures.register("trees_mahogany", ConfiguredFeatureRegistry.MAHOGANY, PlacedFeatures.createCountExtraModifier(4, .05f, 1), SquarePlacementModifier.of(), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG), SurfaceWaterDepthFilterPlacementModifier.of(4), BiomePlacementModifier.of());
        YELLOW_ASPEN = PlacedFeatures.register("yellow_aspen", ConfiguredFeatureRegistry.YELLOW_ASPEN, PlacedFeatures.wouldSurvive(BlockRegistry.YELLOW_ASPEN_SAPLING));
        GREEN_ASPEN = PlacedFeatures.register("green_aspen", ConfiguredFeatureRegistry.GREEN_ASPEN, PlacedFeatures.wouldSurvive(BlockRegistry.GREEN_ASPEN_SAPLING));
        TREES_ASPEN_YELLOW_DENSE = PlacedFeatures.register("trees_aspen_yellow_dense", ConfiguredFeatureRegistry.YELLOW_ASPEN, PlacedFeatures.createCountExtraModifier(10, .1f, 1), SquarePlacementModifier.of(), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG), SurfaceWaterDepthFilterPlacementModifier.of(0), BiomePlacementModifier.of());
        TREES_ASPEN_YELLOW_SPARSE = PlacedFeatures.register("trees_aspen_yellow_sparse", ConfiguredFeatureRegistry.YELLOW_ASPEN, PlacedFeatures.createCountExtraModifier(0, .25f, 1), SquarePlacementModifier.of(), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG), SurfaceWaterDepthFilterPlacementModifier.of(0), BiomePlacementModifier.of());
        TREES_ASPEN_GREEN_DENSE = PlacedFeatures.register("trees_aspen_green_dense", ConfiguredFeatureRegistry.GREEN_ASPEN, PlacedFeatures.createCountExtraModifier(1, .1f, 1), SquarePlacementModifier.of(), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG), SurfaceWaterDepthFilterPlacementModifier.of(0), BiomePlacementModifier.of());
        TREES_ASPEN_GREEN_SPARSE = PlacedFeatures.register("trees_aspen_green_sparse", ConfiguredFeatureRegistry.GREEN_ASPEN, PlacedFeatures.createCountExtraModifier(0, .25f, 1), SquarePlacementModifier.of(), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG), SurfaceWaterDepthFilterPlacementModifier.of(0), BiomePlacementModifier.of());
        PATCH_DAYLIGHT_MUSHROOMS = PlacedFeatures.register("patch_daylight_mushrooms", ConfiguredFeatureRegistry.PATCH_DAYLIGHT_MUSHROOMS, RarityFilterPlacementModifier.of(10), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
        PATCH_DARKBLIGHT_MUSHROOMS = PlacedFeatures.register("patch_darkblight_mushrooms", ConfiguredFeatureRegistry.PATCH_DARKBLIGHT_MUSHROOMS, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, BiomePlacementModifier.of());
    }
}
