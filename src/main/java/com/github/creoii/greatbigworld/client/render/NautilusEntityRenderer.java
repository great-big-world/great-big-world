package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.client.model.NautilusEntityModel;
import com.github.creoii.greatbigworld.entity.NautilusEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.RenderRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class NautilusEntityRenderer<T extends NautilusEntity> extends MobEntityRenderer<T, NautilusEntityModel<T>> {
    private static final Identifier TEXTURE = new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/nautilus/nautilus.png");

    public NautilusEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new NautilusEntityModel<>(context.getPart(RenderRegistry.NAUTILUS_MODEL_LAYER)), .3f);
    }

    @Override
    protected void scale(T entity, MatrixStack matrices, float amount) {
        matrices.scale(.95f, .95f, .95f);
    }

    public Identifier getTexture(NautilusEntity nautilusEntity) {
        return TEXTURE;
    }
}
