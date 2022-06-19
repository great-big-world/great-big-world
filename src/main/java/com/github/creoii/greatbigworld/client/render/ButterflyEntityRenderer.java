package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.client.ModelLayers;
import com.github.creoii.greatbigworld.client.model.ButterflyEntityModel;
import com.github.creoii.greatbigworld.entity.ButterflyEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.TintableCompositeModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class ButterflyEntityRenderer extends MobEntityRenderer<ButterflyEntity, TintableCompositeModel<ButterflyEntity>> {
    private final TintableCompositeModel<ButterflyEntity> butterflyModel = getModel();

    public ButterflyEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new ButterflyEntityModel(context.getPart(ModelLayers.BUTTERFLY_LAYER)), .3f);
        addFeature(new ButterflyPatternInnerFeatureRenderer(this, context.getModelLoader()));
        addFeature(new ButterflyPatternOutlineFeatureRenderer(this, context.getModelLoader()));
    }

    public Identifier getTexture(ButterflyEntity butterflyEntity) {
        return new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/butterfly/body.png");
    }

    public void render(ButterflyEntity butterflyEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(butterflyEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    protected void scale(ButterflyEntity butterflyEntity, MatrixStack matrixStack, float f) {
        matrixStack.scale(.8f, .8f, .8f);
    }

    protected void setupTransforms(ButterflyEntity butterflyEntity, MatrixStack matrixStack, float f, float g, float h) {
        if (butterflyEntity.isSitting()) {
            matrixStack.translate(0d, .10000000149011612d, 0d);
        } else {
            matrixStack.translate(0d, MathHelper.cos(f * .3f) * .1f, 0d);
        }

        super.setupTransforms(butterflyEntity, matrixStack, f, g, h);
    }
}