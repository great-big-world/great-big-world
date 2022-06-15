package com.github.creoii.greatbigworld.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.List;

public record MultiRandomSpreadFeatureConfig(IntProvider tries, List<RandomSpreadFeatureConfig> randomSpreads, List<Integer> randomStartRadius) implements FeatureConfig {
    public static final Codec<MultiRandomSpreadFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(IntProvider.createValidatingCodec(0, 64).fieldOf("tries").forGetter(config -> {
            return config.tries;
        }), Codec.list(RandomSpreadFeatureConfig.CODEC).fieldOf("random_spreads").forGetter(config -> {
            return config.randomSpreads;
        }), Codec.list(Codec.INT).fieldOf("random_start_radius").forGetter(config -> {
            return config.randomStartRadius;
        })).apply(instance, MultiRandomSpreadFeatureConfig::new);
    });
}
