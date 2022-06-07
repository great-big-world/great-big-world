package com.github.creoii.greatbigworld.client;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import com.github.creoii.greatbigworld.main.registry.ItemRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class GreatBigWorldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRegistry.renderLayers();
        BlockRegistry.tintBlocks();
        ItemRegistry.tintItems();
        EntityRegistry.registerClient();
        ModelLayers.registerClient();
    }
}
