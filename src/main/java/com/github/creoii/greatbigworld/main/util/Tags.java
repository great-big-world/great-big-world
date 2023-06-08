package com.github.creoii.greatbigworld.main.util;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class Tags {
    public static final String COMMON_NAMESPACE = "c";

    public static class BlockTags {
        public static final TagKey<Block> MAHOGANY_LOGS = TagKey.of(RegistryKeys.BLOCK, new Identifier(NAMESPACE, "mahogany_logs"));
        public static final TagKey<Block> ASPEN_LOGS = TagKey.of(RegistryKeys.BLOCK, new Identifier(NAMESPACE, "aspen_logs"));
        public static final TagKey<Block> ACAI_LOGS = TagKey.of(RegistryKeys.BLOCK, new Identifier(NAMESPACE, "acai_logs"));
        public static final TagKey<Block> MOOSE_EDIBLES = TagKey.of(RegistryKeys.BLOCK, new Identifier(NAMESPACE, "moose_edibles"));
        public static final TagKey<Block> MUD_REPLACEABLE = TagKey.of(RegistryKeys.BLOCK, new Identifier(NAMESPACE, "mud_replaceable"));
        public static final TagKey<Block> ACAI_BERRY_PLACEABLE = TagKey.of(RegistryKeys.BLOCK, new Identifier(NAMESPACE, "acai_berry_placeable"));
        public static final TagKey<Block> THATCH = TagKey.of(RegistryKeys.BLOCK, new Identifier(NAMESPACE, "thatch"));
        public static final TagKey<Block> ERODES_INTO_LAVAROCK = TagKey.of(RegistryKeys.BLOCK, new Identifier(NAMESPACE, "erodes_into_lavarock"));
        public static final TagKey<Block> ERODES_INTO_VOLCANIC_SAND = TagKey.of(RegistryKeys.BLOCK, new Identifier(NAMESPACE, "erodes_into_volcanic_sand"));
        public static final TagKey<Block> LEAF_PILES = TagKey.of(RegistryKeys.BLOCK, new Identifier(NAMESPACE, "leaf_piles"));
    }

    public static class ItemTags {
        public static final TagKey<Item> MOOSE_FOOD = TagKey.of(RegistryKeys.ITEM, new Identifier(NAMESPACE, "moose_food"));
        public static final TagKey<Item> MOOSE_FOOD_LIKES = TagKey.of(RegistryKeys.ITEM, new Identifier(NAMESPACE, "moose_food_likes"));
        public static final TagKey<Item> MOOSE_FOOD_LOVES = TagKey.of(RegistryKeys.ITEM, new Identifier(NAMESPACE, "moose_food_loves"));
        public static final TagKey<Item> WOODEN_MASKS = TagKey.of(RegistryKeys.ITEM, new Identifier(NAMESPACE, "wooden_masks"));
        public static final TagKey<Item> GLIMMERING_MUSHROOMS = TagKey.of(RegistryKeys.ITEM, new Identifier(NAMESPACE, "glimmering_mushrooms"));
    }

    public static class BiomeTags {
        public static final TagKey<Biome> MAHOGANY_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier(NAMESPACE, "mahogany_biomes"));
        public static final TagKey<Biome> SPARSE_MAHOGANY_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier(NAMESPACE, "sparse_mahogany_biomes"));
        public static final TagKey<Biome> GREEN_ASPEN_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier(NAMESPACE, "green_aspen_biomes"));
        public static final TagKey<Biome> YELLOW_ASPEN_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier(NAMESPACE, "yellow_aspen_biomes"));
        public static final TagKey<Biome> SNOWY_GREEN_ASPEN_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier(NAMESPACE, "snowy_green_aspen_biomes"));
        public static final TagKey<Biome> SNOWY_YELLOW_ASPEN_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier(NAMESPACE, "snowy_yellow_aspen_biomes"));
        public static final TagKey<Biome> MOOSE_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, new Identifier(NAMESPACE, "moose_spawnable"));
        public static final TagKey<Biome> HEATHER_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier(NAMESPACE, "heather_biomes"));
        public static final TagKey<Biome> SPARSE_HEATHER_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier(NAMESPACE, "sparse_heather_biomes"));
        public static final TagKey<Biome> IS_TROPICAL_ISLAND = TagKey.of(RegistryKeys.BIOME, new Identifier(NAMESPACE, "is_tropical_island"));
        public static final TagKey<Biome> IS_VOLCANIC = TagKey.of(RegistryKeys.BIOME, new Identifier(NAMESPACE, "is_volcanic"));
        public static final TagKey<Biome> TROPICAL_FERN_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier(NAMESPACE, "tropical_fern_biomes"));
        public static final TagKey<Biome> ACAI_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier(NAMESPACE, "acai_biomes"));
        public static final TagKey<Biome> SPARSE_ACAI_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier(NAMESPACE, "sparse_acai_biomes"));
        public static final TagKey<Biome> NAUTILUS_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, new Identifier(NAMESPACE, "nautilus_spawnable"));
    }

    public static final class EntityTypeTags {
        public static final TagKey<EntityType<?>> THICKET_IGNORES = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(NAMESPACE, "thicket_ignores"));
    }

    public static class EnchantmentTags {
        public static final TagKey<Enchantment> MASK_ALLOWED = TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier(NAMESPACE, "allowed/mask"));
    }
}
