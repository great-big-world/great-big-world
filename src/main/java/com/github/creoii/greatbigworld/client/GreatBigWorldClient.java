package com.github.creoii.greatbigworld.client;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class GreatBigWorldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRegistry.renderLayers();
        EntityRegistry.registerClient();
    }
}
