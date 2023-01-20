package com.github.creoii.greatbigworld.main.util;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class Tags {
    public static class BlockTags {
        public static final TagKey<Block> MOOSE_EDIBLES = TagKey.of(Registry.BLOCK_KEY, new Identifier(NAMESPACE, "moose_edibles"));
    }

    public static class ItemTags {
        public static final TagKey<Item> MOOSE_FOOD = TagKey.of(Registry.ITEM_KEY, new Identifier(NAMESPACE, "moose_food"));
        public static final TagKey<Item> MOOSE_FOOD_LIKES = TagKey.of(Registry.ITEM_KEY, new Identifier(NAMESPACE, "moose_food_likes"));
        public static final TagKey<Item> MOOSE_FOOD_LOVES = TagKey.of(Registry.ITEM_KEY, new Identifier(NAMESPACE, "moose_food_loves"));
    }

    public static class EntityTypeTags {
        public static final TagKey<EntityType<?>> MOOSE_FLEE_FROM = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier(NAMESPACE, "moose_flee_from"));
    }

    public static class BiomeTags {
        public static final TagKey<Biome> MAHOGANY_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "mahogany_biomes"));
        public static final TagKey<Biome> SPARSE_MAHOGANY_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "sparse_mahogany_biomes"));
        public static final TagKey<Biome> GREEN_ASPEN_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "green_aspen_biomes"));
        public static final TagKey<Biome> YELLOW_ASPEN_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "yellow_aspen_biomes"));
        public static final TagKey<Biome> SNOWY_GREEN_ASPEN_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "snowy_green_aspen_biomes"));
        public static final TagKey<Biome> SNOWY_YELLOW_ASPEN_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "snowy_yellow_aspen_biomes"));
        public static final TagKey<Biome> MOOSE_SPAWNABLE = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "moose_spawnable"));
        public static final TagKey<Biome> HEATHER_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "heather_biomes"));
    }
}
