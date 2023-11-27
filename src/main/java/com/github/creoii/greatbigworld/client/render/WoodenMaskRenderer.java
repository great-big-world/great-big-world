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
            .put(GBWItems.OAK_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask/oak.png"))
            .put(GBWItems.SPRUCE_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask/spruce.png"))
            .put(GBWItems.PINE_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask/pine.png"))
            .put(GBWItems.BIRCH_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask/birch.png"))
            .put(GBWItems.WISTERIA_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask/wisteria.png"))
            .put(GBWItems.ASPEN_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask/aspen.png"))
            .put(GBWItems.JUNGLE_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask/jungle.png"))
            .put(GBWItems.MAHOGANY_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask/mahogany.png"))
            .put(GBWItems.ACAI_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask/acai.png"))
            .put(GBWItems.ACACIA_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask/acacia.png"))
            .put(GBWItems.PALO_VERDE_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask/palo_verde.png"))
            .put(GBWItems.DARK_OAK_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask/dark_oak.png"))
            .put(GBWItems.MANGROVE_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask/mangrove.png"))
            .put(GBWItems.BAMBOO_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask/bamboo.png"))
            .put(GBWItems.CHERRY_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask/cherry.png"))
            .put(GBWItems.CRIMSON_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask/crimson.png"))
            .put(GBWItems.WARPED_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask/warped.png"))
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
