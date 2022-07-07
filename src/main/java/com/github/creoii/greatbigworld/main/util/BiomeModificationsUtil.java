package com.github.creoii.greatbigworld.main.util;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.function.Predicate;

public class BiomeModificationsUtil {
    public static void removeFeature(Predicate<BiomeSelectionContext> biomeSelector, RegistryEntry<PlacedFeature> placedFeature) {
        if (placedFeature.getKey().isPresent()) {
            BiomeModifications.create(placedFeature.getKey().get().getValue()).add(ModificationPhase.REMOVALS, biomeSelector, context -> {
                context.getGenerationSettings().removeBuiltInFeature(placedFeature.value());
            });
        }
    }
}
