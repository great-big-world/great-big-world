package com.github.creoii.greatbigworld.main.util;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;

import java.util.Optional;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class Tags {
    public static final String COMMON_NAMESPACE = "c";

    public static boolean isEnchantmentIn(Enchantment enchantment, TagKey<Enchantment> tag) {
        Optional<RegistryEntry<Enchantment>> entry = Registry.ENCHANTMENT.getEntry(Registry.ENCHANTMENT.getKey(enchantment).get());
        if (entry.isPresent() && entry.get().hasKeyAndValue()) {
            return entry.get().isIn(tag);
        } return false;
    }

    public static class BlockTags {
        public static final TagKey<Block> MOOSE_EDIBLES = TagKey.of(Registry.BLOCK_KEY, new Identifier(NAMESPACE, "moose_edibles"));
        public static final TagKey<Block> ACAI_BERRY_PLACEABLE = TagKey.of(Registry.BLOCK_KEY, new Identifier(NAMESPACE, "acai_berry_placeable"));
    }

    public static class ItemTags {
        public static final TagKey<Item> MOOSE_FOOD = TagKey.of(Registry.ITEM_KEY, new Identifier(NAMESPACE, "moose_food"));
        public static final TagKey<Item> MOOSE_FOOD_LIKES = TagKey.of(Registry.ITEM_KEY, new Identifier(NAMESPACE, "moose_food_likes"));
        public static final TagKey<Item> MOOSE_FOOD_LOVES = TagKey.of(Registry.ITEM_KEY, new Identifier(NAMESPACE, "moose_food_loves"));
        public static final TagKey<Item> WOODEN_MASKS = TagKey.of(Registry.ITEM_KEY, new Identifier(NAMESPACE, "wooden_masks"));
        public static final TagKey<Item> GLIMMERING_MUSHROOMS = TagKey.of(Registry.ITEM_KEY, new Identifier(NAMESPACE, "glimmering_mushrooms"));
        public static final TagKey<Item> COMMON_BOWS = TagKey.of(Registry.ITEM_KEY, new Identifier(COMMON_NAMESPACE, "bows"));
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
        public static final TagKey<Biome> SPARSE_HEATHER_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "sparse_heather_biomes"));
        public static final TagKey<Biome> IS_TROPICAL_ISLAND = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "is_tropical_island"));
        public static final TagKey<Biome> TROPICAL_FERN_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "tropical_fern_biomes"));
        public static final TagKey<Biome> ACAI_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "acai_biomes"));
        public static final TagKey<Biome> SPARSE_ACAI_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "sparse_acai_biomes"));
        public static final TagKey<Biome> NAUTILUS_SPAWNABLE = TagKey.of(Registry.BIOME_KEY, new Identifier(NAMESPACE, "nautilus_spawnable"));
    }

    public static final class EntityTypeTags {
        public static final TagKey<EntityType<?>> THICKET_IGNORES = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier(NAMESPACE, "thicket_ignores"));
    }

    public static class EnchantmentTags {
        public static final TagKey<Enchantment> MASK_ALLOWED = TagKey.of(Registry.ENCHANTMENT_KEY, new Identifier(NAMESPACE, "allowed/mask"));
    }
}
