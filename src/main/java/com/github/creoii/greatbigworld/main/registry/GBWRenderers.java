package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.client.model.MooseEntityModel;
import com.github.creoii.greatbigworld.client.model.NautilusEntityModel;
import com.github.creoii.greatbigworld.client.model.WoodenMaskModel;
import com.github.creoii.greatbigworld.client.render.MooseEntityRenderer;
import com.github.creoii.greatbigworld.client.render.NautilusEntityRenderer;
import com.github.creoii.greatbigworld.client.render.ThicketEntityRenderer;
import com.github.creoii.greatbigworld.client.render.WoodenMaskRenderer;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.util.Identifier;

public class GBWRenderers implements Register {
    public static final EntityModelLayer MOOSE_MODEL_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "moose"), "main");
    public static final EntityModelLayer MOOSE_SADDLE_MODEL_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "moose"), "saddle");
    public static final EntityModelLayer THICKET_MODEL_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "thicket"), "main");
    public static final EntityModelLayer THICKET_OUTER_MODEL_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "thicket"), "outer");
    public static final EntityModelLayer NAUTILUS_MODEL_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "nautilus"), "main");
    public static final EntityModelLayer WOODEN_MASK_MODEL_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "player"), "mask");

    @Override
    public void registerClient() {
        EntityRendererRegistry.register(GBWEntityTypes.MOOSE, MooseEntityRenderer::new);
        EntityRendererRegistry.register(GBWEntityTypes.THICKET, ThicketEntityRenderer::new);
        EntityRendererRegistry.register(GBWEntityTypes.NAUTILUS, NautilusEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MOOSE_MODEL_LAYER, MooseEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(MOOSE_SADDLE_MODEL_LAYER, MooseEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(THICKET_MODEL_LAYER, SkeletonEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(THICKET_OUTER_MODEL_LAYER, SkeletonEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(NAUTILUS_MODEL_LAYER, NautilusEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(WOODEN_MASK_MODEL_LAYER, WoodenMaskModel::getTexturedModelData);
        ArmorRenderer.register(new WoodenMaskRenderer(), GBWItems.OAK_MASK, GBWItems.SPRUCE_MASK, GBWItems.BIRCH_MASK, GBWItems.ASPEN_MASK, GBWItems.JUNGLE_MASK, GBWItems.MAHOGANY_MASK, GBWItems.ACAI_MASK, GBWItems.ACACIA_MASK, GBWItems.DARK_OAK_MASK, GBWItems.MANGROVE_MASK, GBWItems.CHERRY_MASK, GBWItems.BAMBOO_MASK, GBWItems.CRIMSON_MASK, GBWItems.WARPED_MASK);
    }
}
