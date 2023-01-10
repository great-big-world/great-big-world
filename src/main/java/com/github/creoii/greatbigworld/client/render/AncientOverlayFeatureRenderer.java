package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class AncientOverlayFeatureRenderer<T extends MobEntity & RangedAttackMob, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    private static final Identifier SKIN = new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/ancient/ancient_overlay.png");
    private final SkeletonEntityModel<T> model;

    public AncientOverlayFeatureRenderer(FeatureRendererContext<T, M> context, EntityModelLoader loader) {
        super(context);
        model = new SkeletonEntityModel<>(loader.getModelPart(EntityRegistry.ANCIENT_OUTER_MODEL_LAYER));
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T mobEntity, float f, float g, float h, float j, float k, float l) {
        render(getContextModel(), model, SKIN, matrixStack, vertexConsumerProvider, i, mobEntity, f, g, j, k, l, h, 1f, 1f, 1f);
    }
}