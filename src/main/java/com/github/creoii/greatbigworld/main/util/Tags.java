package com.github.creoii.greatbigworld.main.util;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
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
        public static final TagKey<Block> INFINIBURN_HALLOW = TagKey.of(Registry.BLOCK_KEY, new Identifier(GreatBigWorld.NAMESPACE, "infiniburn_hallow"));
    }

    public static class Items {
        public static final TagKey<Item> DAGGERS = TagKey.of(Registry.ITEM_KEY, new Identifier(COMMON_MOD_ID, "daggers"));
        public static final TagKey<Item> BOOTS = TagKey.of(Registry.ITEM_KEY, new Identifier(COMMON_MOD_ID, "boots"));
    }

    public static class Entities {
        public static final TagKey<EntityType<?>> MOLTEN_MAGMA_WALKABLES = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier(COMMON_MOD_ID, "molten_magma_walkables"));
        public static final TagKey<EntityType<?>> QUICKSAND_WALKABLES = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier(COMMON_MOD_ID, "quicksand_walkables"));
        public static final TagKey<EntityType<?>> HYENA_PREY = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier(COMMON_MOD_ID, "hyena_prey"));
    }
}
