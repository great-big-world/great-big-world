package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tag.BiomeTags;
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

    @Override
    public void register() {
        MAHOGANY = PlacedFeatures.register("mahogany", ConfiguredFeatureRegistry.MAHOGANY, PlacedFeatures.createCountExtraModifier(4, .05f, 1), SquarePlacementModifier.of(), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG), SurfaceWaterDepthFilterPlacementModifier.of(4), BiomePlacementModifier.of(), PlacedFeatures.wouldSurvive(BlockRegistry.MAHOGANY_SAPLING));
        generation();
    }

    public void generation() {
        if (MAHOGANY.getKey().isPresent()) BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE).and(BiomeSelectors.excludeByKey(BiomeKeys.SPARSE_JUNGLE)), GenerationStep.Feature.VEGETAL_DECORATION, MAHOGANY.getKey().get());
    }
}
