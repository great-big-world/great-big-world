package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.BiomeCreator;
import com.github.creoii.greatbigworld.main.util.BiomeKeys;
import net.minecraft.util.registry.BuiltinRegistries;

public class BiomeRegistry {
    public static void register() {
        BuiltinRegistries.add(BuiltinRegistries.BIOME, BiomeKeys.DIRT_CAVES, BiomeCreator.createDirtCaves());
    }
}
