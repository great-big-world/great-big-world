package com.github.creoii.greatbigworld.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public record RandomSpreadFeatureConfig(BlockStateProvider state, IntProvider iterations, IntProvider walkLength, boolean randomStart, List<Integer> randomStartRadius) implements FeatureConfig {
    public static final Codec<RandomSpreadFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("state").forGetter(config -> {
            return config.state;
        }), IntProvider.POSITIVE_CODEC.fieldOf("iterations").orElse(ConstantIntProvider.create(0)).forGetter(config -> {
            return config.iterations;
        }), IntProvider.createValidatingCodec(1, 20).fieldOf("walkLength").orElse(ConstantIntProvider.create(0)).forGetter(config -> {
            return config.walkLength;
        }), Codec.BOOL.fieldOf("random_start").orElse(true).forGetter(config -> {
            return config.randomStart;
        }), Codec.list(Codec.INT).fieldOf("random_start_radius").forGetter(config -> {
            return config.randomStartRadius;
        })).apply(instance, RandomSpreadFeatureConfig::new);
    });
}
