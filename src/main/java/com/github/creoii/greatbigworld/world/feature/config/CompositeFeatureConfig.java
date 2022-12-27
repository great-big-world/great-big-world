package com.github.creoii.greatbigworld.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.List;

public record CompositeFeatureConfig(List<RegistryEntry<PlacedFeature>> features) implements FeatureConfig {
    public static final Codec<CompositeFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(PlacedFeature.REGISTRY_CODEC.listOf().fieldOf("features").forGetter(config -> {
            return config.features;
        })).apply(instance, CompositeFeatureConfig::new);
    });
}
