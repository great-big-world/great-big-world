package com.github.creoii.greatbigworld.main;

import com.github.creoii.greatbigworld.main.registry.*;
import com.github.creoii.greatbigworld.main.util.Events;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.math.random.Random;

public class GreatBigWorld implements ModInitializer {
    public static final String NAMESPACE = "great_big_world";
    public static final Random RANDOM = Random.create();
    private static final boolean DEV_ENV = true;

    @Override
    public void onInitialize() {
        BlockRegistry.register();
        BlockEntityRegistry.register();
        ItemRegistry.register();
        EntityRegistry.register();
        PredicateRegistry.register();
        FeatureRegistry.register();
        PlacerRegistry.register();
        DecoratorRegistry.register();
        ConfiguredFeatureRegistry.register();
        PlacedFeatureRegistry.register();
        StructurePieceRegistry.register();
        StructureRegistry.register();
        BiomeRegistry.register();

        Events.loadEvents();
    }

    public static boolean isLoaded(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }

    public static boolean inDev() {
        return DEV_ENV;
    }
}
