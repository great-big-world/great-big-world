package com.github.creoii.greatbigworld.main.util;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Tags {
    public static class Entities {
        public static final TagKey<EntityType<?>> MOLTEN_MAGMA_WALKABLE = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier(GreatBigWorld.MOD_ID, "molten_magma_walkable"));
        public static final TagKey<EntityType<?>> QUICKSAND_WALKABLE = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier(GreatBigWorld.MOD_ID, "quicksand_walkable"));
    }
}
