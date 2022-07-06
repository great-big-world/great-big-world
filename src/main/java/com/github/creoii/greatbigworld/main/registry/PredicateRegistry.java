package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.world.predicate.SkyVisiblePredicate;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockpredicate.BlockPredicateType;

public class PredicateRegistry {
    public static final BlockPredicateType<SkyVisiblePredicate> SKY_VISIBLE = () -> SkyVisiblePredicate.CODEC;

    public static void register() {
        Registry.register(Registry.BLOCK_PREDICATE_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "sky_visible"), SKY_VISIBLE);
    }
}
