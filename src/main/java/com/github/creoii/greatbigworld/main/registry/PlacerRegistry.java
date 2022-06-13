package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.world.placer.RectangledTrunkPlacer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class PlacerRegistry {
    public static TrunkPlacerType<RectangledTrunkPlacer> RECTANGLED_TRUNK_PLACER;

    public static void register() {
        RECTANGLED_TRUNK_PLACER = Registry.register(Registry.TRUNK_PLACER_TYPE, new Identifier(GreatBigWorld.MOD_ID, "rectangled_trunk_placer"), new TrunkPlacerType<RectangledTrunkPlacer>(RectangledTrunkPlacer.CODEC));
    }
}
