package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.placer.TwistingTrunkPlacer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class PlacerRegistry implements Register {
    public static final TrunkPlacerType<TwistingTrunkPlacer> TWISTING_TRUNK_PLACER = new TrunkPlacerType<>(TwistingTrunkPlacer.CODEC);

    public void register() {
        Registry.register(Registry.TRUNK_PLACER_TYPE, new Identifier(NAMESPACE, "twisting_trunk_placer"), TWISTING_TRUNK_PLACER);
    }
}
