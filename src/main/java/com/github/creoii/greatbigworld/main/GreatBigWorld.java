package com.github.creoii.greatbigworld.main;

import com.github.creoii.greatbigworld.main.registry.*;
import com.github.creoii.greatbigworld.main.util.Events;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class GreatBigWorld implements ModInitializer {
    public static final String MOD_ID = "great_big_world";
    private static final boolean DEV_ENV = true;

    @Override
    public void onInitialize() {
        BlockRegistry.register();
        BlockEntityRegistry.register();
        ItemRegistry.register();
        EntityRegistry.register();
        PredicateRegistry.register();
        ConfiguredFeatureRegistry.register();
        PlacedFeatureRegistry.register();
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
