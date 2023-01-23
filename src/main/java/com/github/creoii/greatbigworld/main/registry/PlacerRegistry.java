package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.placer.AspenFoliagePlacer;
import com.github.creoii.greatbigworld.world.placer.TwistingTrunkPlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class PlacerRegistry implements Register {
    public static TrunkPlacerType<TwistingTrunkPlacer> TWISTING_TRUNK_PLACER;
    public static FoliagePlacerType<AspenFoliagePlacer> ASPEN_FOLIAGE_PLACER;

    public void register() {
        TWISTING_TRUNK_PLACER = TrunkPlacerType.register("twisting_trunk_placer", TwistingTrunkPlacer.CODEC);
        ASPEN_FOLIAGE_PLACER = FoliagePlacerType.register("aspen_foliage_placer", AspenFoliagePlacer.CODEC);
    }
}
