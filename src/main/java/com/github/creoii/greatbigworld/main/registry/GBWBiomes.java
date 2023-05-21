package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
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

    @Override
    public void register() {
        ASPEN_FOREST = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "aspen_forest"));
        SNOWY_ASPEN_FOREST = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "snowy_aspen_forest"));
        ISLAND_JUNGLE = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "island_jungle"));
        ISLAND_SPARSE_JUNGLE = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "island_sparse_jungle"));
        ISLAND_BEACH = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "island_beach"));
        VOLCANIC_BEACH = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "volcanic_beach"));
        VOLCANIC_SLOPES = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "volcanic_slopes"));
        VOLCANIC_CRATER = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "volcanic_crater"));
    }
}
