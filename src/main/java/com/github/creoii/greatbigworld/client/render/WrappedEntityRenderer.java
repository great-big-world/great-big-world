package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.client.ModelLayers;
import com.github.creoii.greatbigworld.client.model.BearEntityModel;
import com.github.creoii.greatbigworld.entity.BearEntity;
import com.github.creoii.greatbigworld.entity.WrappedEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class WrappedEntityRenderer extends BipedEntityRenderer<WrappedEntity, BipedEntityModel<WrappedEntity>> {
    private static final Identifier TEXTURE = new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/wrapped.png");

    public WrappedEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new BipedEntityModel<WrappedEntity>(context.getPart(ModelLayers.WRAPPED_LAYER)), .5f);
    }

    public static TexturedModelData getTexturedModelData() {
        return TexturedModelData.of(BipedEntityModel.getModelData(Dilation.NONE, 0f), 64, 64);
    }

    public Identifier getTexture(WrappedEntity wrappedEntity) {
        return TEXTURE;
    }
}
