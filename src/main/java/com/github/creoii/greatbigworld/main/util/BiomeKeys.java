package com.github.creoii.greatbigworld.main.util;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class BiomeKeys {
    public static final RegistryKey<Biome> DIRT_CAVES = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.MOD_ID, "dirt_caves"));
}
