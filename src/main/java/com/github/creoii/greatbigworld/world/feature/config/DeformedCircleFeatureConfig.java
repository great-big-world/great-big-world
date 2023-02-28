package com.github.creoii.greatbigworld.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record DeformedCircleFeatureConfig(int yOffset, IntProvider rimSize, BlockStateProvider rimState, BlockStateProvider innerState, IntProvider height, IntProvider startRadius, IntProvider innerDepth, TagKey<Block> replaceable, boolean solidRim) implements FeatureConfig {
    public static final Codec<DeformedCircleFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(Codec.INT.fieldOf("y_offset").orElse(0).forGetter(config -> {
            return config.yOffset;
        }), IntProvider.createValidatingCodec(0, 16).fieldOf("rim_size").forGetter(config -> {
            return config.rimSize;
        }), BlockStateProvider.TYPE_CODEC.fieldOf("rim_state").forGetter(config -> {
            return config.rimState;
        }), BlockStateProvider.TYPE_CODEC.fieldOf("inner_state").forGetter(config -> {
            return config.innerState;
        }), IntProvider.POSITIVE_CODEC.fieldOf("height").forGetter(config -> {
            return config.height;
        }), IntProvider.POSITIVE_CODEC.fieldOf("start_radius").forGetter(config -> {
            return config.startRadius;
        }), IntProvider.POSITIVE_CODEC.fieldOf("inner_depth").forGetter(config -> {
            return config.innerDepth;
        }), TagKey.codec(RegistryKeys.BLOCK).fieldOf("replaceable").forGetter(config -> {
            return config.replaceable;
        }), Codec.BOOL.fieldOf("solid_rim").orElse(false).forGetter(config -> {
            return config.solidRim;
        })).apply(instance, DeformedCircleFeatureConfig::new);
    });
}
