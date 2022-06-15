package com.github.creoii.greatbigworld.client;

import com.github.creoii.greatbigworld.client.model.BearEntityModel;
import com.github.creoii.greatbigworld.client.model.ButterflyEntityModel;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ModelLayers {
    public static final EntityModelLayer BEAR_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "bear"), "main");
    public static final EntityModelLayer BUTTERFLY_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "butterfly"), "main");
    public static final EntityModelLayer BUTTERFLY_PATTERN_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "butterfly"), "pattern");

    @Environment(EnvType.CLIENT)
    public static void registerClient() {
        EntityModelLayerRegistry.registerModelLayer(BEAR_LAYER, BearEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BUTTERFLY_LAYER, ButterflyEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BUTTERFLY_PATTERN_LAYER, ButterflyEntityModel::getTexturedModelData);
    }
}
