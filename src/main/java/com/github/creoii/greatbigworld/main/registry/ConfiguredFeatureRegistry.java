package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class ConfiguredFeatureRegistry implements Register {
    public static RegistryKey<ConfiguredFeature<?, ?>> MAHOGANY;
    public static RegistryKey<ConfiguredFeature<?, ?>> GREEN_ASPEN;
    public static RegistryKey<ConfiguredFeature<?, ?>> YELLOW_ASPEN;

    @Override
    public void register() {
        MAHOGANY = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(NAMESPACE, "mahogany"));
        GREEN_ASPEN = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(NAMESPACE, "green_aspen"));
        YELLOW_ASPEN = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(NAMESPACE, "yellow_aspen"));
    }
}
