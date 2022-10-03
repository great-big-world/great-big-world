package com.github.creoii.greatbigworld.main.util;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class Tags {
    public static class ItemTags {
        public static final TagKey<Item> MOOSE_FOOD = TagKey.of(Registry.ITEM_KEY, new Identifier(NAMESPACE, "moose_food"));
        public static final TagKey<Item> MOOSE_BREED_ITEMS = TagKey.of(Registry.ITEM_KEY, new Identifier(NAMESPACE, "moose_breed_items"));
    }

    public static class BiomeTags {
        public static final TagKey<Biome> MAHOGANY_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "mahogany_biomes"));
        public static final TagKey<Biome> YELLOW_ASPEN_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "yellow_aspen_biomes"));
        public static final TagKey<Biome> GREEN_ASPEN_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "green_aspen_biomes"));
        public static final TagKey<Biome> MOOSE_SPAWNABLE = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "moose_spawnable"));
    }
}
