package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.client.model.WoodenMaskModel;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.GBWItems;
import com.github.creoii.greatbigworld.main.registry.GBWRenderers;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class WoodenMaskRenderer implements ArmorRenderer {
    public static final Map<Item, Identifier> TEXTURES = ImmutableMap.<Item, Identifier>builder()
            .put(GBWItems.OAK_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_oak.png"))
            .put(GBWItems.SPRUCE_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_spruce.png"))
            .put(GBWItems.BIRCH_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_birch.png"))
            .put(GBWItems.ASPEN_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_aspen.png"))
            .put(GBWItems.JUNGLE_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_jungle.png"))
            .put(GBWItems.MAHOGANY_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_mahogany.png"))
            .put(GBWItems.ACAI_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_acai.png"))
            .put(GBWItems.ACACIA_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_acacia.png"))
            .put(GBWItems.DARK_OAK_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_dark_oak.png"))
            .put(GBWItems.MANGROVE_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_mangrove.png"))
            .put(GBWItems.BAMBOO_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_bamboo.png"))
            .put(GBWItems.CHERRY_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_cherry.png"))
            .put(GBWItems.CRIMSON_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_crimson.png"))
            .put(GBWItems.WARPED_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_warped.png"))
            .build();
    private WoodenMaskModel maskModel;

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, BipedEntityModel<LivingEntity> model) {
        if (maskModel == null) {
            maskModel = new WoodenMaskModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(GBWRenderers.WOODEN_MASK_MODEL_LAYER));
        }
        model.copyStateTo(maskModel);
        maskModel.mask.setTransform(model.head.getTransform());
        maskModel.mask.visible = slot == EquipmentSlot.HEAD;
        ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, maskModel, TEXTURES.get(stack.getItem()));
    }
}
