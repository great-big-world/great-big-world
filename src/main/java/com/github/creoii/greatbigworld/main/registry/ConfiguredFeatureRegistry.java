package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class ConfiguredFeatureRegistry implements Register {
    public static RegistryKey<ConfiguredFeature<?, ?>> MAHOGANY;
    public static RegistryKey<ConfiguredFeature<?, ?>> GREEN_ASPEN;
    public static RegistryKey<ConfiguredFeature<?, ?>> YELLOW_ASPEN;
    public static RegistryKey<ConfiguredFeature<?, ?>> ACAI;
    public static RegistryKey<ConfiguredFeature<?, ?>> WHITE_WISTERIA;
    public static RegistryKey<ConfiguredFeature<?, ?>> YELLOW_WISTERIA;
    public static RegistryKey<ConfiguredFeature<?, ?>> BLUE_WISTERIA;
    public static RegistryKey<ConfiguredFeature<?, ?>> PINK_WISTERIA;
    public static RegistryKey<ConfiguredFeature<?, ?>> PURPLE_WISTERIA;

    @Override
    public void register() {
        MAHOGANY = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(NAMESPACE, "mahogany"));
        GREEN_ASPEN = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(NAMESPACE, "green_aspen"));
        YELLOW_ASPEN = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(NAMESPACE, "yellow_aspen"));
        ACAI = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(NAMESPACE, "acai"));
        WHITE_WISTERIA = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(NAMESPACE, "white_wisteria"));
        YELLOW_WISTERIA = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(NAMESPACE, "yellow_wisteria"));
        BLUE_WISTERIA = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(NAMESPACE, "blue_wisteria"));
        PINK_WISTERIA = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(NAMESPACE, "pink_wisteria"));
        PURPLE_WISTERIA = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(NAMESPACE, "purple_wisteria"));
    }
}
