package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.world.placement.NearBlockPlacementModifier;
import com.github.creoii.greatbigworld.world.placement.NoisePlacementModifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class PlacementModifierRegistry {
    public static final PlacementModifierType<NoisePlacementModifier> NOISE = () -> NoisePlacementModifier.CODEC;
    public static final PlacementModifierType<NearBlockPlacementModifier> NEAR_BLOCK = () -> NearBlockPlacementModifier.CODEC;

    public static void register() {
        Registry.register(Registry.PLACEMENT_MODIFIER_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "noise"), NOISE);
        Registry.register(Registry.PLACEMENT_MODIFIER_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "near_block"), NEAR_BLOCK);
    }
}
