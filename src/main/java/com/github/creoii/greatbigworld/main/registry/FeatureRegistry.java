package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.feature.CompositeFeature;
import com.github.creoii.greatbigworld.world.feature.config.CompositeFeatureConfig;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;

public class FeatureRegistry implements Register {
    public static final Feature<CompositeFeatureConfig> COMPOSITE = new CompositeFeature(CompositeFeatureConfig.CODEC);

    @Override
    public void register() {
        Registry.register(Registries.FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "composite"), COMPOSITE);
    }
}
