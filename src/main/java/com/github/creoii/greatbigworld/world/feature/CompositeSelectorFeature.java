package com.github.creoii.greatbigworld.world.feature;

import com.github.creoii.greatbigworld.world.feature.config.CompositeSelectorFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.ArrayList;
import java.util.List;

public class CompositeSelectorFeature extends Feature<CompositeSelectorFeatureConfig> {
    public CompositeSelectorFeature(Codec<CompositeSelectorFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext context) {
        boolean generated = false;
        CompositeSelectorFeatureConfig config = (CompositeSelectorFeatureConfig) context.getConfig();
        List<RegistryEntry<PlacedFeature>> toPlace = new ArrayList<>();
        for (List<RegistryEntry<PlacedFeature>> entry : config.features()) {
            toPlace.add(entry.get(context.getRandom().nextInt(entry.size())));
        }
        for (RegistryEntry<PlacedFeature> entry : toPlace) {
            if (entry.value().generateUnregistered(context.getWorld(), context.getGenerator(), context.getRandom(), context.getOrigin())) {
                generated = true;
            } else if (config.failIfFirstFails()) break;
        }
        return generated;
    }
}
