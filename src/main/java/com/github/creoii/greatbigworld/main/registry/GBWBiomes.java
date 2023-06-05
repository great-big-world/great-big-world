package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class GBWBiomes implements Register {
    public static final RegistryKey<Biome> ASPEN_FOREST = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "aspen_forest"));
    public static final RegistryKey<Biome> SNOWY_ASPEN_FOREST = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "snowy_aspen_forest"));
    public static final RegistryKey<Biome> ISLAND_JUNGLE = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "island_jungle"));
    public static final RegistryKey<Biome> ISLAND_SPARSE_JUNGLE = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "island_sparse_jungle"));
    public static final RegistryKey<Biome> ISLAND_BEACH = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "island_beach"));
    // island stony shores
    public static final RegistryKey<Biome> VOLCANIC_BEACH = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "volcanic_beach"));
    public static final RegistryKey<Biome> VOLCANIC_SLOPES = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "volcanic_slopes"));
    public static final RegistryKey<Biome> VOLCANIC_CRATER = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "volcanic_crater"));

    @Override
    public void register() {
    }
}
