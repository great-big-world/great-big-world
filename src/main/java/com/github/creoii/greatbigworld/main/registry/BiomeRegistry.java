package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.GBWBiomeCreator;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class BiomeRegistry implements Register {
    public static final Biome ASPEN_FOREST = GBWBiomeCreator.createAspenForest(false);
    public static final Biome SNOWY_ASPEN_FOREST = GBWBiomeCreator.createAspenForest(true);

    public static final RegistryKey<Biome> ASPEN_FOREST_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "aspen_forest"));
    public static final RegistryKey<Biome> SNOWY_ASPEN_FOREST_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "snowy_aspen_forest"));

    @Override
    public void register() {
        BuiltinRegistries.add(BuiltinRegistries.BIOME, ASPEN_FOREST_KEY, ASPEN_FOREST);
        BuiltinRegistries.add(BuiltinRegistries.BIOME, SNOWY_ASPEN_FOREST_KEY, SNOWY_ASPEN_FOREST);
    }
}
