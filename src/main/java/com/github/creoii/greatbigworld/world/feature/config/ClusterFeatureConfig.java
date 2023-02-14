package com.github.creoii.greatbigworld.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.List;

public record ClusterFeatureConfig(RegistryEntry<PlacedFeature> feature, IntProvider count, IntProvider range) implements FeatureConfig {
    public static final Codec<ClusterFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(PlacedFeature.REGISTRY_CODEC.fieldOf("feature").forGetter(config -> {
            return config.feature;
        }), IntProvider.POSITIVE_CODEC.fieldOf("count").forGetter(config -> {
            return config.count;
        }), IntProvider.createValidatingCodec(0, 16).fieldOf("range").forGetter(config -> {
            return config.range;
        })).apply(instance, ClusterFeatureConfig::new);
    });
}
