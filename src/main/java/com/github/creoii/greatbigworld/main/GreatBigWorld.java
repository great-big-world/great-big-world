package com.github.creoii.greatbigworld.main;

import com.github.creoii.greatbigworld.main.registry.*;
import com.github.creoii.greatbigworld.main.util.Events;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class GreatBigWorld implements ModInitializer {
    public static final String NAMESPACE = "great_big_world";
    public static final Random RANDOM = Random.create();
    private static final boolean DEV_ENV = true;

    @Override
    public void onInitialize() {
        BlockRegistry.register();
        BlockEntityRegistry.register();
        ItemRegistry.register();
        EntityRegistry.register();
        PredicateRegistry.register();
        FeatureRegistry.register();
        PlacerRegistry.register();
        DecoratorRegistry.register();
        ConfiguredFeatureRegistry.register();
        PlacedFeatureRegistry.register();
        BiomeRegistry.register();

        modifyBiomes();
        Events.loadEvents();

        Registry.BLOCK.forEach(block -> {
            if (Registry.BLOCK.getId(block).getNamespace().equals(NAMESPACE)) {
                System.out.println(block.getTranslationKey());
            }
        });
    }

    public static boolean isLoaded(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }

    public static boolean inDev() {
        return DEV_ENV;
    }

    private static void modifyBiomes() {
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_RIVER), GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.ALGAE_PATCH.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE).and(BiomeSelectors.excludeByKey(BiomeKeys.SPARSE_JUNGLE)), GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.MAHOGANY.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.WOODED_BADLANDS), GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.PALO_VERDE.getKey().get());
    }
}
