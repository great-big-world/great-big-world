package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.client.ModelLayers;
import com.github.creoii.greatbigworld.client.model.ButterflyEntityModel;
import com.github.creoii.greatbigworld.entity.ButterflyEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ButterflyColorFeatureRenderer extends FeatureRenderer<ButterflyEntity, TintableCompositeModel<ButterflyEntity>> {
    private final ButterflyEntityModel model;

    public ButterflyColorFeatureRenderer(FeatureRendererContext<ButterflyEntity, TintableCompositeModel<ButterflyEntity>> context, EntityModelLoader loader) {
        super(context);
        model = new ButterflyEntityModel(loader.getModelPart(ModelLayers.BUTTERFLY_PATTERN_LAYER));
    }

    public Identifier getTexture(ButterflyEntity butterflyEntity) {
        return butterflyEntity.getShapeId();
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, ButterflyEntity butterflyEntity, float f, float g, float h, float j, float k, float l) {
        float[] fs = butterflyEntity.getPatternColorComponents();
        render(model, model, butterflyEntity.getPatternId(), matrixStack, vertexConsumerProvider, i, butterflyEntity, f, g, j, k, l, h, fs[0], fs[1], fs[2]);
    }
}
