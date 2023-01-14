package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.client.model.AncientEntityModel;
import com.github.creoii.greatbigworld.entity.AncientEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.RenderRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class AncientEntityRenderer<T extends AncientEntity> extends BipedEntityRenderer<T, AncientEntityModel<T>> {
    private static final Identifier TEXTURE = new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/ancient/ancient.png");

    public AncientEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new AncientEntityModel<>(context.getPart(RenderRegistry.ANCIENT_MODEL_LAYER)), .75f);
        addFeature(new ArmorFeatureRenderer<>(this, new BipedEntityModel<>(context.getPart(EntityModelLayers.PLAYER_INNER_ARMOR)), new BipedEntityModel<>(context.getPart(EntityModelLayers.PLAYER_OUTER_ARMOR))));
    }

    @Override
    protected void scale(T entity, MatrixStack matrices, float amount) {
        matrices.scale(1.1f, 1.1f, 1.1f);
    }

    public Identifier getTexture(AncientEntity ancientEntity) {
        return TEXTURE;
    }
}
