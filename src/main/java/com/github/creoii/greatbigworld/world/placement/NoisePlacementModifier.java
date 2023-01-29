package com.github.creoii.greatbigworld.world.placement;

import com.github.creoii.greatbigworld.main.registry.PlacementRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.List;
import java.util.Optional;

public class NoisePlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<NoisePlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(Identifier.CODEC.fieldOf("noise_parameters").forGetter(predicate -> {
            return predicate.noise;
        }), Range.CODEC.listOf().optionalFieldOf("ranges", List.of(new Range(-1d, 1d))).forGetter(predicate -> {
            return predicate.ranges;
        }), Codec.BOOL.fieldOf("legacy").orElse(false).forGetter(predicate -> {
            return predicate.legacy;
        })).apply(instance, NoisePlacementModifier::new);
    });

    private static final Optional<RegistryEntryLookup<DoublePerlinNoiseSampler.NoiseParameters>> LOOKUP = BuiltinRegistries.createWrapperLookup().createRegistryLookup().getOptional(RegistryKeys.NOISE_PARAMETERS);
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
    protected boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        if (LOOKUP.isPresent()) {
            RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> parametersKey = RegistryKey.of(RegistryKeys.NOISE_PARAMETERS, noise);
            Optional<RegistryEntry.Reference<DoublePerlinNoiseSampler.NoiseParameters>> optionalNoise = LOOKUP.get().getOptional(parametersKey);
            if (optionalNoise.isPresent()) {
                DoublePerlinNoiseSampler.NoiseParameters parameters = optionalNoise.get().value();
                DoublePerlinNoiseSampler sampler = legacy ? DoublePerlinNoiseSampler.createLegacy(random, parameters) : DoublePerlinNoiseSampler.create(random, parameters);
                double noiseValue = sampler.sample(pos.getX(), pos.getY(), pos.getZ());
                for (Range range : ranges) {
                    if (noiseValue >= range.min() && noiseValue < range.max()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return PlacementRegistry.NOISE;
    }

    public record Range(double min, double max) {
        public static final Codec<Range> CODEC = RecordCodecBuilder.create(instance -> {
            return instance.group(Codec.DOUBLE.fieldOf("min_inclusive").forGetter(range -> {
                return range.min;
            }), Codec.DOUBLE.fieldOf("max_exclusive").forGetter(range -> {
                return range.max;
            })).apply(instance, Range::new);
        });
    }
}
