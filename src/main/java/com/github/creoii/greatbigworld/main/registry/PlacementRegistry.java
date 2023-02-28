package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.placement.NoisePlacementModifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class PlacementRegistry implements Register {
    public static final PlacementModifierType<NoisePlacementModifier> NOISE = () -> NoisePlacementModifier.CODEC;

    @Override
    public void register() {
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "noise"), NOISE);
    }
}

