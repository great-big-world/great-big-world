package com.github.creoii.greatbigworld.world.placement;

import com.github.creoii.greatbigworld.main.registry.PlacementModifierRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.List;

public class NoisePlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<NoisePlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(Identifier.CODEC.fieldOf("noise").forGetter(predicate -> {
            return predicate.noise;
        }), Range.CODEC.listOf().optionalFieldOf("ranges", List.of(new Range(-1d, 1d))).forGetter(predicate -> {
            return predicate.ranges;
        }), Codec.BOOL.fieldOf("legacy").orElse(false).forGetter(predicate -> {
            return predicate.legacy;
        })).apply(instance, NoisePlacementModifier::new);
    });

    private final Identifier noise;
    private final List<Range> ranges;
    private final boolean legacy;

    private NoisePlacementModifier(Identifier noise, List<Range> ranges, boolean legacy) {
        this.noise = noise;
        this.ranges = ranges;
        this.legacy = legacy;
    }

    public static NoisePlacementModifier of(Identifier noise, List<Range> ranges, boolean legacy) {
        return new NoisePlacementModifier(noise, ranges, legacy);
    }

    @Override
    public PlacementModifierType<?> getType() {
        return PlacementModifierRegistry.NOISE;
    }

    @Override
    @SuppressWarnings("deprecation")
    protected boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        DoublePerlinNoiseSampler.NoiseParameters parameters = BuiltinRegistries.NOISE_PARAMETERS.get(noise);
        if (parameters != null) {
            DoublePerlinNoiseSampler sampler = legacy ? DoublePerlinNoiseSampler.createLegacy(random, parameters) : DoublePerlinNoiseSampler.create(random, parameters);
            double noiseValue = sampler.sample(pos.getX(), pos.getY(), pos.getZ());
            for (Range range : ranges) {
                if (noiseValue >= range.min() && noiseValue < range.max()) return true;
            }
        }

        return false;
    }

    public static record Range(double min, double max) {
        public static final Codec<Range> CODEC = RecordCodecBuilder.create(instance -> {
            return instance.group(Codec.DOUBLE.fieldOf("min").forGetter(range -> {
                return range.min;
            }), Codec.DOUBLE.fieldOf("max").forGetter(range -> {
                return range.max;
            })).apply(instance, Range::new);
        });
    }
}
