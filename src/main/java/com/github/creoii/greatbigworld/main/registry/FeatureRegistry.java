package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.feature.ClusterFeature;
import com.github.creoii.greatbigworld.world.feature.CompositeFeature;
import com.github.creoii.greatbigworld.world.feature.DeformedCircleFeature;
import com.github.creoii.greatbigworld.world.feature.CompositeSelectorFeature;
import com.github.creoii.greatbigworld.world.feature.config.ClusterFeatureConfig;
import com.github.creoii.greatbigworld.world.feature.config.CompositeFeatureConfig;
import com.github.creoii.greatbigworld.world.feature.config.DeformedCircleFeatureConfig;
import com.github.creoii.greatbigworld.world.feature.config.CompositeSelectorFeatureConfig;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;

public class FeatureRegistry implements Register {
    public static final Feature<CompositeFeatureConfig> COMPOSITE = new CompositeFeature(CompositeFeatureConfig.CODEC);
    public static final Feature<ClusterFeatureConfig> CLUSTER = new ClusterFeature(ClusterFeatureConfig.CODEC);
    public static final Feature<DeformedCircleFeatureConfig> DEFORMED_CIRCLE = new DeformedCircleFeature(DeformedCircleFeatureConfig.CODEC);
    public static final Feature<CompositeSelectorFeatureConfig> COMPOSITE_SELECTOR = new CompositeSelectorFeature(CompositeSelectorFeatureConfig.CODEC);

    @Override
    public void register() {
        Registry.register(Registries.FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "composite"), COMPOSITE);
        Registry.register(Registries.FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "cluster"), CLUSTER);
        Registry.register(Registries.FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "deformed_circle"), DEFORMED_CIRCLE);
        Registry.register(Registries.FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "composite_selector"), COMPOSITE_SELECTOR);
    }
}
