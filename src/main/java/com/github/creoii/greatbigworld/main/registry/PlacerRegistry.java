package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.placer.AspenFoliagePlacer;
import com.github.creoii.greatbigworld.world.placer.PalmFoliagePlacer;
import com.github.creoii.greatbigworld.world.placer.TwistingTrunkPlacer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class PlacerRegistry implements Register {
    public static final TrunkPlacerType<TwistingTrunkPlacer> TWISTING_TRUNK_PLACER = new TrunkPlacerType<>(TwistingTrunkPlacer.CODEC);
    public static final FoliagePlacerType<AspenFoliagePlacer> ASPEN_FOLIAGE_PLACER = new FoliagePlacerType<>(AspenFoliagePlacer.CODEC);
    public static final FoliagePlacerType<PalmFoliagePlacer> PALM_FOLIAGE_PLACER = new FoliagePlacerType<>(PalmFoliagePlacer.CODEC);

    public void register() {
        Registry.register(Registries.TRUNK_PLACER_TYPE, new Identifier(NAMESPACE, "twisting_trunk_placer"), TWISTING_TRUNK_PLACER);
        Registry.register(Registries.FOLIAGE_PLACER_TYPE, new Identifier(NAMESPACE, "aspen_foliage_placer"), ASPEN_FOLIAGE_PLACER);
        Registry.register(Registries.FOLIAGE_PLACER_TYPE, new Identifier(NAMESPACE, "palm_foliage_placer"), PALM_FOLIAGE_PLACER);
    }
}
