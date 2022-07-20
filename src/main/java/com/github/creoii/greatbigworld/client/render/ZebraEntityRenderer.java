package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.client.ModelLayers;
import com.github.creoii.greatbigworld.client.model.ZebraEntityModel;
import com.github.creoii.greatbigworld.entity.ZebraEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ZebraEntityRenderer extends MobEntityRenderer<ZebraEntity, ZebraEntityModel<ZebraEntity>> {
    private static final Identifier TEXTURE = new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/zebra.png");

    public ZebraEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new ZebraEntityModel<ZebraEntity>(context.getPart(ModelLayers.ZEBRA_LAYER)), 1.1f);
    }

    public Identifier getTexture(ZebraEntity zebraEntity) {
        return TEXTURE;
    }
}
