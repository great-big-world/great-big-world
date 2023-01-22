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
        public static final TagKey<Block> MOOSE_EDIBLES = TagKey.of(RegistryKeys.BLOCK, new Identifier(NAMESPACE, "moose_edibles"));
    }

    public static class ItemTags {
        public static final TagKey<Item> MOOSE_FOOD = TagKey.of(RegistryKeys.ITEM, new Identifier(NAMESPACE, "moose_food"));
        public static final TagKey<Item> MOOSE_FOOD_LIKES = TagKey.of(RegistryKeys.ITEM, new Identifier(NAMESPACE, "moose_food_likes"));
        public static final TagKey<Item> MOOSE_FOOD_LOVES = TagKey.of(RegistryKeys.ITEM, new Identifier(NAMESPACE, "moose_food_loves"));
        public static final TagKey<Item> WOODEN_MASKS = TagKey.of(RegistryKeys.ITEM, new Identifier(NAMESPACE, "wooden_masks"));
        public static final TagKey<Item> COMMON_BOWS = TagKey.of(RegistryKeys.ITEM, new Identifier(COMMON_NAMESPACE, "bows"));
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
    }

    public static final class EntityTypeTags {
        public static final TagKey<EntityType<?>> THICKET_IGNORES = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(NAMESPACE, "thicket_ignores"));
    }

    public static class EnchantmentTags {
        public static final TagKey<Enchantment> MASK_ALLOWED = TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier(NAMESPACE, "allowed/mask"));
    }
}
