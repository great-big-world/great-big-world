package com.github.creoii.greatbigworld.world.feature;

import com.github.creoii.greatbigworld.world.feature.config.ClusterFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class ClusterFeature extends Feature<ClusterFeatureConfig> {
    public ClusterFeature(Codec<ClusterFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<ClusterFeatureConfig> context) {
        ClusterFeatureConfig config = context.getConfig();
        RegistryEntry<PlacedFeature> entry = config.feature();
        if (entry.hasKeyAndValue()) {
            BlockPos origin = context.getOrigin();
            Random random = context.getRandom();
            int range = config.range().get(random);
            for (int i = 0; i < config.count().get(random); ++i) {
                BlockPos pos = origin.add(random.nextBetween(-range, range), 0, random.nextBetween(-range, range));
                if (!entry.value().generateUnregistered(context.getWorld(), context.getGenerator(), random, pos)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
