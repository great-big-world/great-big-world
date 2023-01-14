package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.client.model.WoodenMaskModel;
import com.github.creoii.greatbigworld.item.WoodenMaskItem;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.RenderRegistry;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Map;

public class WoodenMaskRenderer implements ArmorRenderer {
    private static final Map<WoodenMaskItem.WoodType, Identifier> TEXTURES = ImmutableMap.<WoodenMaskItem.WoodType, Identifier>builder()
            .put(WoodenMaskItem.WoodType.OAK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_oak.png"))
            .put(WoodenMaskItem.WoodType.SPRUCE, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_spruce.png"))
            .put(WoodenMaskItem.WoodType.BIRCH, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_birch.png"))
            .put(WoodenMaskItem.WoodType.JUNGLE, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_jungle.png"))
            .put(WoodenMaskItem.WoodType.ACACIA, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_acacia.png"))
            .put(WoodenMaskItem.WoodType.DARK_OAK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_dark_oak.png"))
            .put(WoodenMaskItem.WoodType.MANGROVE, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_mangrove.png"))
            .put(WoodenMaskItem.WoodType.MAHOGANY, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_mahogany.png"))
            .put(WoodenMaskItem.WoodType.ASPEN, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_aspen.png"))
            .put(WoodenMaskItem.WoodType.CRIMSON, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_crimson.png"))
            .put(WoodenMaskItem.WoodType.WARPED, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_warped.png"))
            .build();
    private WoodenMaskModel maskModel;

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, BipedEntityModel<LivingEntity> model) {
        if (stack.getItem() instanceof WoodenMaskItem maskItem) {
            if (maskModel == null) {
                maskModel = new WoodenMaskModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(RenderRegistry.WOODEN_MASK_MODEL_LAYER));
            }
            model.copyStateTo(maskModel);
            maskModel.mask.setTransform(model.head.getTransform());
            maskModel.mask.visible = slot == EquipmentSlot.HEAD;
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, maskModel, getTexture(maskItem));
        }
    }

    public static Identifier getTexture(WoodenMaskItem maskItem) {
        return TEXTURES.get(maskItem.getWoodType());
    }
}
