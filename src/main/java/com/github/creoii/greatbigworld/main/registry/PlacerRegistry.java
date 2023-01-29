package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.placer.AspenFoliagePlacer;
import com.github.creoii.greatbigworld.world.placer.TwistingTrunkPlacer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class PlacerRegistry implements Register {
    public static TrunkPlacerType<TwistingTrunkPlacer> TWISTING_TRUNK_PLACER;
    public static FoliagePlacerType<AspenFoliagePlacer> ASPEN_FOLIAGE_PLACER;

    public void register() {
        TWISTING_TRUNK_PLACER = Registry.register(Registry.TRUNK_PLACER_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "twisting_trunk_placer"), new TrunkPlacerType<>(TwistingTrunkPlacer.CODEC));
        ASPEN_FOLIAGE_PLACER = Registry.register(Registry.FOLIAGE_PLACER_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "aspen_foliage_placer"), new FoliagePlacerType<>(AspenFoliagePlacer.CODEC));
    }
}
