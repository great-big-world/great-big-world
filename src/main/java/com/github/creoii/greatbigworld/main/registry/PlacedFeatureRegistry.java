package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.main.util.Tags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tag.BiomeTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightmapPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SurfaceWaterDepthFilterPlacementModifier;

public class PlacedFeatureRegistry implements Register {
    public static RegistryEntry<PlacedFeature> MAHOGANY;
    public static RegistryEntry<PlacedFeature> YELLOW_ASPEN;
    public static RegistryEntry<PlacedFeature> YELLOW_ASPEN_2;
    public static RegistryEntry<PlacedFeature> GREEN_ASPEN;
    public static RegistryEntry<PlacedFeature> GREEN_ASPEN_2;
    public static RegistryEntry<PlacedFeature> TREES_ASPEN_MIXED;
    public static RegistryEntry<PlacedFeature> TREES_ASPEN_GREEN_SPARSE;

    @Override
    public void register() {
        MAHOGANY = PlacedFeatures.register("mahogany", ConfiguredFeatureRegistry.MAHOGANY, PlacedFeatures.createCountExtraModifier(4, .05f, 1), SquarePlacementModifier.of(), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG), SurfaceWaterDepthFilterPlacementModifier.of(4), BiomePlacementModifier.of(), PlacedFeatures.wouldSurvive(BlockRegistry.MAHOGANY_SAPLING));
        YELLOW_ASPEN = PlacedFeatures.register("yellow_aspen", ConfiguredFeatureRegistry.YELLOW_ASPEN, PlacedFeatures.wouldSurvive(BlockRegistry.YELLOW_ASPEN_SAPLING));
        YELLOW_ASPEN_2 = PlacedFeatures.register("yellow_aspen_2", ConfiguredFeatureRegistry.YELLOW_ASPEN_2, PlacedFeatures.wouldSurvive(BlockRegistry.YELLOW_ASPEN_SAPLING));
        GREEN_ASPEN = PlacedFeatures.register("green_aspen", ConfiguredFeatureRegistry.GREEN_ASPEN, PlacedFeatures.wouldSurvive(BlockRegistry.GREEN_ASPEN_SAPLING));
        GREEN_ASPEN_2 = PlacedFeatures.register("green_aspen_2", ConfiguredFeatureRegistry.GREEN_ASPEN_2, PlacedFeatures.wouldSurvive(BlockRegistry.GREEN_ASPEN_SAPLING));
        TREES_ASPEN_MIXED = PlacedFeatures.register("trees_aspen_mixed", ConfiguredFeatureRegistry.TREES_ASPEN_MIXED, PlacedFeatures.createCountExtraModifier(10, .1f, 1), SquarePlacementModifier.of(), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG), BiomePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0));
        TREES_ASPEN_GREEN_SPARSE = PlacedFeatures.register("trees_aspen_green_sparse", ConfiguredFeatureRegistry.TREES_ASPEN_GREEN, PlacedFeatures.createCountExtraModifier(0, .2f, 1), SquarePlacementModifier.of(), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG), BiomePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0));
        generation();
    }

    public void generation() {
        if (MAHOGANY.getKey().isPresent())
            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE).and(BiomeSelectors.excludeByKey(BiomeKeys.SPARSE_JUNGLE)), GenerationStep.Feature.VEGETAL_DECORATION, MAHOGANY.getKey().get());
        if (TREES_ASPEN_GREEN_SPARSE.getKey().isPresent())
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST).or(BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST)).or(BiomeSelectors.tag(Tags.BiomeTags.SPARSE_ASPEN_BIOMES)), GenerationStep.Feature.VEGETAL_DECORATION, TREES_ASPEN_GREEN_SPARSE.getKey().get());
    }
}
