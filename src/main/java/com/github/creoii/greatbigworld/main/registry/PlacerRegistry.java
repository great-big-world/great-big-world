package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.world.placer.TwistingTrunkPlacer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class PlacerRegistry {
    public static TrunkPlacerType<TwistingTrunkPlacer> RECTANGLED_TRUNK_PLACER;

    public static void register() {
        RECTANGLED_TRUNK_PLACER = Registry.register(Registry.TRUNK_PLACER_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "rectangled_trunk_placer"), new TrunkPlacerType<TwistingTrunkPlacer>(TwistingTrunkPlacer.CODEC));
    }
}
