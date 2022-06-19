package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.world.placer.FungusHatFoliagePlacer;
import com.github.creoii.greatbigworld.world.placer.InvertedFungusHatFoliagePlacer;
import com.github.creoii.greatbigworld.world.placer.InvertedTwistingTrunkPlacer;
import com.github.creoii.greatbigworld.world.placer.TwistingTrunkPlacer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class PlacerRegistry {
    public static TrunkPlacerType<TwistingTrunkPlacer> TWISTING_TRUNK_PLACER;
    public static TrunkPlacerType<InvertedTwistingTrunkPlacer> INVERTED_TWISTING_TRUNK_PLACER;

    public static FoliagePlacerType<FungusHatFoliagePlacer> FUNGUS_HAT_FOLIAGE_PLACER;
    public static FoliagePlacerType<InvertedFungusHatFoliagePlacer> INVERTED_FUNGUS_HAT_FOLIAGE_PLACER;

    public static void register() {
        TWISTING_TRUNK_PLACER = Registry.register(Registry.TRUNK_PLACER_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "twisting_trunk_placer"), new TrunkPlacerType<TwistingTrunkPlacer>(TwistingTrunkPlacer.CODEC));
        INVERTED_TWISTING_TRUNK_PLACER = Registry.register(Registry.TRUNK_PLACER_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "inverted_twisting_trunk_placer"), new TrunkPlacerType<InvertedTwistingTrunkPlacer>(InvertedTwistingTrunkPlacer.CODEC));

        FUNGUS_HAT_FOLIAGE_PLACER = Registry.register(Registry.FOLIAGE_PLACER_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "fungus_hat_foliage_placer"), new FoliagePlacerType<FungusHatFoliagePlacer>(FungusHatFoliagePlacer.CODEC));
        INVERTED_FUNGUS_HAT_FOLIAGE_PLACER = Registry.register(Registry.FOLIAGE_PLACER_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "inverted_fungus_hat_foliage_placer"), new FoliagePlacerType<InvertedFungusHatFoliagePlacer>(InvertedFungusHatFoliagePlacer.CODEC));
    }
}
