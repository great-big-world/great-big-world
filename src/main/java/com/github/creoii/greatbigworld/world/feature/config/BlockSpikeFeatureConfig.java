package com.github.creoii.greatbigworld.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record BlockSpikeFeatureConfig(BlockStateProvider state, int floorToCeilingSearchRange, IntProvider columnRadius, FloatProvider heightScale, float maxColumnRadiusToCaveHeightRatio, FloatProvider stalactiteBluntness, FloatProvider stalagmiteBluntness, FloatProvider windSpeed, int minRadiusForWind, float minBluntnessForWind) implements FeatureConfig {
    public static final Codec<BlockSpikeFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("state").forGetter((blockSpikeFeatureConfig) -> {
            return blockSpikeFeatureConfig.state;
        }), Codec.intRange(1, 512).fieldOf("floor_to_ceiling_search_range").orElse(30).forGetter((blockSpikeFeatureConfig) -> {
            return blockSpikeFeatureConfig.floorToCeilingSearchRange;
        }), IntProvider.createValidatingCodec(1, 60).fieldOf("column_radius").forGetter((blockSpikeFeatureConfig) -> {
            return blockSpikeFeatureConfig.columnRadius;
        }), FloatProvider.createValidatedCodec(0.0F, 20.0F).fieldOf("height_scale").forGetter((blockSpikeFeatureConfig) -> {
            return blockSpikeFeatureConfig.heightScale;
        }), Codec.floatRange(0.1F, 1.0F).fieldOf("max_column_radius_to_cave_height_ratio").forGetter((blockSpikeFeatureConfig) -> {
            return blockSpikeFeatureConfig.maxColumnRadiusToCaveHeightRatio;
        }), FloatProvider.createValidatedCodec(0.1F, 10.0F).fieldOf("stalactite_bluntness").forGetter((blockSpikeFeatureConfig) -> {
            return blockSpikeFeatureConfig.stalactiteBluntness;
        }), FloatProvider.createValidatedCodec(0.1F, 10.0F).fieldOf("stalagmite_bluntness").forGetter((blockSpikeFeatureConfig) -> {
            return blockSpikeFeatureConfig.stalagmiteBluntness;
        }), FloatProvider.createValidatedCodec(0.0F, 2.0F).fieldOf("wind_speed").forGetter((blockSpikeFeatureConfig) -> {
            return blockSpikeFeatureConfig.windSpeed;
        }), Codec.intRange(0, 100).fieldOf("min_radius_for_wind").forGetter((blockSpikeFeatureConfig) -> {
            return blockSpikeFeatureConfig.minRadiusForWind;
        }), Codec.floatRange(0.0F, 5.0F).fieldOf("min_bluntness_for_wind").forGetter((blockSpikeFeatureConfig) -> {
            return blockSpikeFeatureConfig.minBluntnessForWind;
        })).apply(instance, BlockSpikeFeatureConfig::new);
    });
}