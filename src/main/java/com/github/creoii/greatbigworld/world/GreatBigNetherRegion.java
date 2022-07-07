package com.github.creoii.greatbigworld.world;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.BiomeRegistry;
import com.mojang.datafixers.util.Pair;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class GreatBigNetherRegion extends Region {
    public GreatBigNetherRegion(int weight) {
        super(new Identifier(GreatBigWorld.NAMESPACE, "nether"), RegionType.NETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        addBiome(mapper, BiomeRegistry.TWISTED_FOREST_POINT_0, BiomeRegistry.TWISTED_FOREST);
    }
}
