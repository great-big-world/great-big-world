package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.feature.ClusterFeature;
import com.github.creoii.greatbigworld.world.feature.config.ClusterFeatureConfig;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;

public class FeatureRegistry implements Register {
    public static final Feature<ClusterFeatureConfig> CLUSTER = new ClusterFeature(ClusterFeatureConfig.CODEC);

    @Override
    public void register() {
        Registry.register(Registries.FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "cluster"), CLUSTER);
    }
}
