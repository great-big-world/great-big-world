package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.BiomeCreator;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class BiomeRegistry {
    public static final Biome DIRT_CAVES = BiomeCreator.createDirtCaves();

    public static void register() {
        Registry.register(BuiltinRegistries.BIOME, new Identifier(GreatBigWorld.MOD_ID, "dirt_caves"), DIRT_CAVES);
    }
}
