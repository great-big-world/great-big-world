package com.github.creoii.greatbigworld.world.feature.config;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class TwoSimpleBlocksFeatureConfig implements FeatureConfig {
    public static final Codec<TwoSimpleBlocksFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("to_place").forGetter((simpleBlockFeatureConfig) -> {
            return simpleBlockFeatureConfig.toPlace;
        }), BlockStateProvider.TYPE_CODEC.fieldOf("base").forGetter((simpleBlockFeatureConfig) -> {
            return simpleBlockFeatureConfig.base;
        }), BlockState.CODEC.listOf().fieldOf("place_on").orElse(ImmutableList.of()).forGetter((simpleBlockFeatureConfig) -> {
            return simpleBlockFeatureConfig.placeOn;
        }), BlockState.CODEC.listOf().fieldOf("place_in").orElse(ImmutableList.of()).forGetter((simpleBlockFeatureConfig) -> {
            return simpleBlockFeatureConfig.placeIn;
        }), BlockState.CODEC.listOf().fieldOf("place_under").orElse(ImmutableList.of()).forGetter((simpleBlockFeatureConfig) -> {
            return simpleBlockFeatureConfig.placeUnder;
        })).apply(instance, TwoSimpleBlocksFeatureConfig::new);
    });
    public final BlockStateProvider toPlace;
    public final BlockStateProvider base;
    public final List<BlockState> placeOn;
    public final List<BlockState> placeIn;
    public final List<BlockState> placeUnder;

    public TwoSimpleBlocksFeatureConfig(BlockStateProvider blockStateProvider, BlockStateProvider base, List<BlockState> placeOn, List<BlockState> placeIn, List<BlockState> placeUnder) {
        this.toPlace = blockStateProvider;
        this.base = base;
        this.placeOn = placeOn;
        this.placeIn = placeIn;
        this.placeUnder = placeUnder;
    }

    public TwoSimpleBlocksFeatureConfig(BlockStateProvider blockStateProvider, BlockStateProvider base) {
        this(blockStateProvider, base, ImmutableList.of(), ImmutableList.of(), ImmutableList.of());
    }
}