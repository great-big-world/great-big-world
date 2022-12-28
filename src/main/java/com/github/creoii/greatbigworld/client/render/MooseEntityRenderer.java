package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.client.model.MooseEntityModel;
import com.github.creoii.greatbigworld.entity.MooseEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.SaddleFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class MooseEntityRenderer<T extends MooseEntity> extends MobEntityRenderer<T, MooseEntityModel<T>> {
    private static final Identifier TEXTURE = new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/moose/moose.png");

    public MooseEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new MooseEntityModel<>(context.getPart(EntityRegistry.MOOSE_MODEL_LAYER)), .7f);
        addFeature(new SaddleFeatureRenderer<>(this, new MooseEntityModel<>(context.getPart(EntityRegistry.MOOSE_SADDLE_MODEL_LAYER)), new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/moose/moose_saddle.png")));
    }

    public Identifier getTexture(MooseEntity mooseEntity) {
        return TEXTURE;
    }
}
