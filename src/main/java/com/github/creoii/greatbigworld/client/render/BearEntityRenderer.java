package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.client.model.BearEntityModel;
import com.github.creoii.greatbigworld.entity.BearEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.client.ModelLayers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BearEntityRenderer extends MobEntityRenderer<BearEntity, BearEntityModel<BearEntity>> {
    private static final Identifier TEXTURE = new Identifier(GreatBigWorld.MOD_ID, "textures/entity/bear.png");

    public BearEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new BearEntityModel<BearEntity>(context.getPart(ModelLayers.BEAR_LAYER)), .9f);
    }

    public Identifier getTexture(BearEntity bearEntity) {
        return TEXTURE;
    }

    protected void scale(BearEntity bearEntity, MatrixStack matrixStack, float f) {
        matrixStack.scale(1.2f, 1.2f, 1.2f);
        super.scale(bearEntity, matrixStack, f);
    }
}
