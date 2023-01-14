package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.entity.ThicketEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.RenderRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ThicketEntityRenderer<T extends ThicketEntity> extends MobEntityRenderer<T, SkeletonEntityModel<T>> {
    private static final Identifier TEXTURE = new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/thicket/thicket.png");

    public ThicketEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SkeletonEntityModel<>(context.getPart(RenderRegistry.THICKET_MODEL_LAYER)), .75f);
        addFeature(new ThicketOverlayFeatureRenderer<>(this, context.getModelLoader()));
    }

    @Override
    protected void scale(T entity, MatrixStack matrices, float amount) {
        matrices.scale(1.1f, 1.1f, 1.1f);
    }

    public Identifier getTexture(ThicketEntity thicketEntity) {
        return TEXTURE;
    }
}
