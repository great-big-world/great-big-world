package com.github.creoii.greatbigworld.main;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class GreatBigWorld implements ModInitializer {
    public static final String MOD_ID = "great_big_world";

    @Override
    public void onInitialize() {
        BlockRegistry.register();
    }

    public static boolean isLoaded(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }
}
