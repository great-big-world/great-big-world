package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.entity.AncientEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class AncientEntityRenderer<T extends AncientEntity> extends MobEntityRenderer<T, SkeletonEntityModel<T>> {
    private static final Identifier TEXTURE = new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/ancient/ancient.png");

    public AncientEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SkeletonEntityModel<>(context.getPart(EntityRegistry.ANCIENT_MODEL_LAYER)), .7f);
        addFeature(new AncientOverlayFeatureRenderer<>(this, context.getModelLoader()));
    }

    public Identifier getTexture(AncientEntity mooseEntity) {
        return TEXTURE;
    }
}
