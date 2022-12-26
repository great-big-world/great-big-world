package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.main.util.Tags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class PlacedFeatureRegistry implements Register {
    public static RegistryKey<PlacedFeature> MAHOGANY;
    public static RegistryKey<PlacedFeature> TREES_ASPEN_GREEN;
    public static RegistryKey<PlacedFeature> GREEN_ASPEN_ON_SNOW;
    public static RegistryKey<PlacedFeature> PATCH_DAYLIGHT_MUSHROOMS;
    public static RegistryKey<PlacedFeature> PATCH_DARKBLIGHT_MUSHROOMS;

    @Override
    public void register() {
        MAHOGANY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "mahogany"));
        TREES_ASPEN_GREEN = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "trees_aspen_green"));
        GREEN_ASPEN_ON_SNOW = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "green_aspen_on_snow"));
        PATCH_DAYLIGHT_MUSHROOMS = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "patch_daylight_mushrooms"));
        PATCH_DARKBLIGHT_MUSHROOMS = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "patch_darkblight_mushrooms"));
        modifyGeneration();
    }

    private void modifyGeneration() {
        BiomeModifications.addFeature(BiomeSelectors.tag(Tags.BiomeTags.MAHOGANY_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, MAHOGANY);
        BiomeModifications.addFeature(BiomeSelectors.tag(Tags.BiomeTags.GREEN_ASPEN_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, TREES_ASPEN_GREEN);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.GROVE), GenerationStep.Feature.VEGETAL_DECORATION, GREEN_ASPEN_ON_SNOW);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, BiomeKeys.OLD_GROWTH_PINE_TAIGA), GenerationStep.Feature.VEGETAL_DECORATION, PATCH_DAYLIGHT_MUSHROOMS);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.VEGETAL_DECORATION, PATCH_DARKBLIGHT_MUSHROOMS);
    }
}
