package com.github.creoii.greatbigworld.main.util;

import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class Tags {
    public static class BiomeTags {
        public static final TagKey<Biome> SPARSE_ASPEN_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "sparse_aspen_biomes"));
    }
}
