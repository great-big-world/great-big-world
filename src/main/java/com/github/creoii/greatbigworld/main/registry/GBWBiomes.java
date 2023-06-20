package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class GBWBiomes implements Register {
    public static RegistryKey<Biome> ASPEN_FOREST;
    public static RegistryKey<Biome> SNOWY_ASPEN_FOREST;
    public static RegistryKey<Biome> ISLAND_JUNGLE;
    public static RegistryKey<Biome> ISLAND_SPARSE_JUNGLE;
    public static RegistryKey<Biome> ISLAND_BEACH;
    // island stony shores
    public static RegistryKey<Biome> VOLCANIC_BEACH;
    public static RegistryKey<Biome> VOLCANIC_SLOPES;
    public static RegistryKey<Biome> VOLCANIC_CRATER;
    public static RegistryKey<Biome> BLOOMING_FLOWER_FOREST;
    public static RegistryKey<Biome> HOT_SPRINGS;

    @Override
    public void register() {
        ASPEN_FOREST = RegistryKey.of(RegistryKeys.BIOME, new Identifier(GreatBigWorld.NAMESPACE, "aspen_forest"));
        SNOWY_ASPEN_FOREST = RegistryKey.of(RegistryKeys.BIOME, new Identifier(GreatBigWorld.NAMESPACE, "snowy_aspen_forest"));
        ISLAND_JUNGLE = RegistryKey.of(RegistryKeys.BIOME, new Identifier(GreatBigWorld.NAMESPACE, "island_jungle"));
        ISLAND_SPARSE_JUNGLE = RegistryKey.of(RegistryKeys.BIOME, new Identifier(GreatBigWorld.NAMESPACE, "island_sparse_jungle"));
        ISLAND_BEACH = RegistryKey.of(RegistryKeys.BIOME, new Identifier(GreatBigWorld.NAMESPACE, "island_beach"));
        VOLCANIC_BEACH = RegistryKey.of(RegistryKeys.BIOME, new Identifier(GreatBigWorld.NAMESPACE, "volcanic_beach"));
        VOLCANIC_SLOPES = RegistryKey.of(RegistryKeys.BIOME, new Identifier(GreatBigWorld.NAMESPACE, "volcanic_slopes"));
        VOLCANIC_CRATER = RegistryKey.of(RegistryKeys.BIOME, new Identifier(GreatBigWorld.NAMESPACE, "volcanic_crater"));
        BLOOMING_FLOWER_FOREST = RegistryKey.of(RegistryKeys.BIOME, new Identifier(GreatBigWorld.NAMESPACE, "blooming_flower_forest"));
        HOT_SPRINGS = RegistryKey.of(RegistryKeys.BIOME, new Identifier(GreatBigWorld.NAMESPACE, "hot_springs"));
    }
}
