package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.GBWBiomeCreator;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class BiomeRegistry implements Register {
    public static final Biome ASPEN_FOREST = GBWBiomeCreator.createAspenForest();
    public static final RegistryKey<Biome> ASPEN_FOREST_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "aspen_forest"));

    @Override
    public void register() {
        Registry.register(BuiltinRegistries.BIOME, new Identifier(GreatBigWorld.NAMESPACE, "aspen_forest"), ASPEN_FOREST);
    }
}
