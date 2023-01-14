package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.entity.ThicketEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.RenderRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ThicketEntityRenderer<T extends ThicketEntity> extends MobEntityRenderer<T, SkeletonEntityModel<T>> {
    private static final Identifier TEXTURE = new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/thicket/thicket.png");

    public ThicketEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SkeletonEntityModel<>(context.getPart(RenderRegistry.THICKET_MODEL_LAYER)), .7f);
        addFeature(new ThicketOverlayFeatureRenderer<>(this, context.getModelLoader()));
    }

    public Identifier getTexture(ThicketEntity thicketEntity) {
        return TEXTURE;
    }
}
