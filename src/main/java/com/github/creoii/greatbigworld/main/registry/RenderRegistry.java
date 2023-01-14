package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.client.model.AncientEntityModel;
import com.github.creoii.greatbigworld.client.model.MooseEntityModel;
import com.github.creoii.greatbigworld.client.model.WoodenMaskModel;
import com.github.creoii.greatbigworld.client.render.*;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class RenderRegistry implements Register {
    public static final EntityModelLayer MOOSE_MODEL_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "moose"), "main");
    public static final EntityModelLayer MOOSE_SADDLE_MODEL_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "moose"), "saddle");
    public static final EntityModelLayer THICKET_MODEL_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "thicket"), "main");
    public static final EntityModelLayer THICKET_OUTER_MODEL_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "thicket"), "outer");
    public static final EntityModelLayer ANCIENT_MODEL_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "ancient"), "main");
    public static final EntityModelLayer WOODEN_MASK_MODEL_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "player"), "mask");

    @Override
    public void registerClient() {
        EntityRendererRegistry.register(EntityRegistry.MOOSE, MooseEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.STONE_ARROW, StoneArrowEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.THICKET, ThicketEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.ANCIENT, AncientEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MOOSE_MODEL_LAYER, MooseEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(MOOSE_SADDLE_MODEL_LAYER, MooseEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(THICKET_MODEL_LAYER, SkeletonEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(THICKET_OUTER_MODEL_LAYER, SkeletonEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ANCIENT_MODEL_LAYER, () -> {
            return TexturedModelData.of(AncientEntityModel.getModelData(Dilation.NONE, 0f), 64, 64);
        });
        EntityModelLayerRegistry.registerModelLayer(WOODEN_MASK_MODEL_LAYER, WoodenMaskModel::getTexturedModelData);
        ArmorRenderer.register(new WoodenMaskRenderer(), ItemRegistry.OAK_MASK);
    }
}
