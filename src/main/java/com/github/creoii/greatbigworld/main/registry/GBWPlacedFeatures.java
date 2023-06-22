package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.main.util.Tags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class GBWPlacedFeatures implements Register {
    public static RegistryKey<PlacedFeature> MAHOGANY;
    public static RegistryKey<PlacedFeature> SPARSE_MAHOGANY;
    public static RegistryKey<PlacedFeature> TREES_ASPEN_GREEN;
    public static RegistryKey<PlacedFeature> TREES_ASPEN_YELLOW;
    public static RegistryKey<PlacedFeature> TREES_ASPEN_GREEN_SNOWY;
    public static RegistryKey<PlacedFeature> TREES_ASPEN_YELLOW_SNOWY;
    public static RegistryKey<PlacedFeature> GREEN_ASPEN_ON_SNOW;
    public static RegistryKey<PlacedFeature> PATCH_DAYLIGHT_MUSHROOMS;
    public static RegistryKey<PlacedFeature> PATCH_DARKBLIGHT_MUSHROOMS;
    public static RegistryKey<PlacedFeature> PATCH_HEATHER;
    public static RegistryKey<PlacedFeature> PATCH_SPARSE_HEATHER;
    public static RegistryKey<PlacedFeature> PATCH_BEACHGRASS;
    public static RegistryKey<PlacedFeature> PATCH_TROPICAL_FERN;
    public static RegistryKey<PlacedFeature> ACAI;
    public static RegistryKey<PlacedFeature> SPARSE_ACAI;
    public static RegistryKey<PlacedFeature> ORE_NAUTILUS_FOSSIL;

    @Override
    public void register() {
        MAHOGANY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "mahogany"));
        SPARSE_MAHOGANY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "sparse_mahogany"));
        TREES_ASPEN_GREEN = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "trees_aspen_green"));
        TREES_ASPEN_YELLOW = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "trees_aspen_yellow"));
        TREES_ASPEN_GREEN_SNOWY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "trees_aspen_green_snowy"));
        TREES_ASPEN_YELLOW_SNOWY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "trees_aspen_yellow_snowy"));
        GREEN_ASPEN_ON_SNOW = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "green_aspen_on_snow"));
        PATCH_DAYLIGHT_MUSHROOMS = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "patch_daylight_mushrooms"));
        PATCH_DARKBLIGHT_MUSHROOMS = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "patch_darkblight_mushrooms"));
        PATCH_HEATHER = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "patch_heather"));
        PATCH_SPARSE_HEATHER = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "patch_sparse_heather"));
        PATCH_BEACHGRASS = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "patch_beachgrass"));
        PATCH_TROPICAL_FERN = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "patch_tropical_fern"));
        ACAI = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "acai"));
        SPARSE_ACAI = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "sparse_acai"));
        ORE_NAUTILUS_FOSSIL = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "ore_nautilus_fossil"));
        modifyGeneration();
    }

    private void modifyGeneration() {
        BiomeModifications.addFeature(BiomeSelectors.tag(Tags.BiomeTags.MAHOGANY_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, MAHOGANY);
        BiomeModifications.addFeature(BiomeSelectors.tag(Tags.BiomeTags.SPARSE_MAHOGANY_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, SPARSE_MAHOGANY);
        BiomeModifications.addFeature(BiomeSelectors.tag(Tags.BiomeTags.GREEN_ASPEN_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, TREES_ASPEN_GREEN);
        BiomeModifications.addFeature(BiomeSelectors.tag(Tags.BiomeTags.YELLOW_ASPEN_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, TREES_ASPEN_YELLOW);
        BiomeModifications.addFeature(BiomeSelectors.tag(Tags.BiomeTags.SNOWY_GREEN_ASPEN_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, TREES_ASPEN_GREEN_SNOWY);
        BiomeModifications.addFeature(BiomeSelectors.tag(Tags.BiomeTags.SNOWY_YELLOW_ASPEN_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, TREES_ASPEN_YELLOW_SNOWY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.GROVE), GenerationStep.Feature.VEGETAL_DECORATION, GREEN_ASPEN_ON_SNOW);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, BiomeKeys.OLD_GROWTH_PINE_TAIGA), GenerationStep.Feature.VEGETAL_DECORATION, PATCH_DAYLIGHT_MUSHROOMS);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.VEGETAL_DECORATION, PATCH_DARKBLIGHT_MUSHROOMS);
        BiomeModifications.addFeature(BiomeSelectors.tag(Tags.BiomeTags.HEATHER_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, PATCH_HEATHER);
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_BEACH), GenerationStep.Feature.VEGETAL_DECORATION, PATCH_BEACHGRASS);
        BiomeModifications.addFeature(BiomeSelectors.tag(Tags.BiomeTags.TROPICAL_FERN_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, PATCH_TROPICAL_FERN);
        BiomeModifications.addFeature(BiomeSelectors.tag(Tags.BiomeTags.ACAI_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, ACAI);
        BiomeModifications.addFeature(BiomeSelectors.tag(Tags.BiomeTags.SPARSE_ACAI_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, SPARSE_ACAI);
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_OCEAN), GenerationStep.Feature.UNDERGROUND_ORES, ORE_NAUTILUS_FOSSIL);
        BiomeModifications.addFeature(BiomeSelectors.tag(Tags.BiomeTags.SPARSE_HEATHER_BIOMES), GenerationStep.Feature.UNDERGROUND_ORES, PATCH_SPARSE_HEATHER);
    }
}
