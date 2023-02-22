package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.client.model.WoodenMaskModel;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.ItemRegistry;
import com.github.creoii.greatbigworld.main.registry.RenderRegistry;
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
            .put(ItemRegistry.OAK_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_oak.png"))
            .put(ItemRegistry.SPRUCE_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_spruce.png"))
            .put(ItemRegistry.BIRCH_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_birch.png"))
            .put(ItemRegistry.ASPEN_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_aspen.png"))
            .put(ItemRegistry.JUNGLE_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_jungle.png"))
            .put(ItemRegistry.MAHOGANY_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_mahogany.png"))
            .put(ItemRegistry.ACAI_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_acai.png"))
            .put(ItemRegistry.ACACIA_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_acacia.png"))
            .put(ItemRegistry.DARK_OAK_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_dark_oak.png"))
            .put(ItemRegistry.MANGROVE_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_mangrove.png"))
            .put(ItemRegistry.CRIMSON_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_crimson.png"))
            .put(ItemRegistry.WARPED_MASK, new Identifier(GreatBigWorld.NAMESPACE, "textures/models/armor/wooden_mask_warped.png"))
            .build();
    private WoodenMaskModel maskModel;

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, BipedEntityModel<LivingEntity> model) {
        if (maskModel == null) {
            maskModel = new WoodenMaskModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(RenderRegistry.WOODEN_MASK_MODEL_LAYER));
        }
        model.copyStateTo(maskModel);
        maskModel.mask.setTransform(model.head.getTransform());
        maskModel.mask.visible = slot == EquipmentSlot.HEAD;
        ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, maskModel, TEXTURES.get(stack.getItem()));
    }
}
