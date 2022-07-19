package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.client.ModelLayers;
import com.github.creoii.greatbigworld.client.model.HyenaEntityModel;
import com.github.creoii.greatbigworld.entity.HyenaEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class HyenaEntityRenderer extends MobEntityRenderer<HyenaEntity, HyenaEntityModel<HyenaEntity>> {
    private static final Identifier TEXTURE = new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/hyena.png");

    public HyenaEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new HyenaEntityModel<HyenaEntity>(context.getPart(ModelLayers.HYENA_LAYER)), .5f);
    }

    public Identifier getTexture(HyenaEntity hyenaEntity) {
        return TEXTURE;
    }
}
