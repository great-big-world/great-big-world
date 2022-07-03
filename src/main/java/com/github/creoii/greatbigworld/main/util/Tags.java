package com.github.creoii.greatbigworld.main.util;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Tags {
    public static final String COMMON_MOD_ID = "c";

    public static class Blocks {
        public static final TagKey<Block> BUTTERFLY_SITTABLE = TagKey.of(Registry.BLOCK_KEY, new Identifier(COMMON_MOD_ID, "butterfly_sittable"));
    }

    public static class Items {
        public static final TagKey<Item> DAGGERS = TagKey.of(Registry.ITEM_KEY, new Identifier(COMMON_MOD_ID, "daggers"));
    }

    public static class Entities {
        public static final TagKey<EntityType<?>> MOLTEN_MAGMA_WALKABLE = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier(COMMON_MOD_ID, "molten_magma_walkable"));
        public static final TagKey<EntityType<?>> QUICKSAND_WALKABLE = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier(COMMON_MOD_ID, "quicksand_walkable"));
    }
}
