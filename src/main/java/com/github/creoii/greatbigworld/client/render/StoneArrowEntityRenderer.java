package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.entity.StoneArrowEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class StoneArrowEntityRenderer extends ProjectileEntityRenderer<StoneArrowEntity> {
    public static final Identifier TEXTURE = new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/projectiles/stone_arrow.png");

    public StoneArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public Identifier getTexture(StoneArrowEntity arrowEntity) {
        return TEXTURE;
    }
}
