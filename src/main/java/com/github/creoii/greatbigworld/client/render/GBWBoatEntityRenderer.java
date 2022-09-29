package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.client.GBWModelLayers;
import com.github.creoii.greatbigworld.entity.GBWBoatEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;

import java.util.Map;
import java.util.stream.Stream;

@Environment(EnvType.CLIENT)
public class GBWBoatEntityRenderer extends EntityRenderer<GBWBoatEntity> {
    private final Map<GBWBoatEntity.GBWType, Pair<Identifier, BoatEntityModel>> texturesAndModels;

    public GBWBoatEntityRenderer(EntityRendererFactory.Context ctx, boolean chest) {
        super(ctx);
        shadowRadius = .8f;
        texturesAndModels = Stream.of(GBWBoatEntity.GBWType.values()).collect(ImmutableMap.toImmutableMap((type) -> type, (type) -> {
            return Pair.of(new Identifier(GreatBigWorld.NAMESPACE, getTexture(type, chest)), createModel(ctx, type, chest));
        }));
    }

    private BoatEntityModel createModel(EntityRendererFactory.Context ctx, GBWBoatEntity.GBWType type, boolean chest) {
        EntityModelLayer entityModelLayer = GBWModelLayers.createBoat(type, chest);
        return new BoatEntityModel(ctx.getPart(entityModelLayer), chest);
    }

    private static String getTexture(GBWBoatEntity.GBWType type, boolean chest) {
        return chest ? "textures/entity/chest_boat/" + type.getName() + ".png" : "textures/entity/boat/" + type.getName() + ".png";
    }

    public void render(GBWBoatEntity boatEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0d, .375d, .0d);
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180f - f));
        float h = (float)boatEntity.getDamageWobbleTicks() - g;
        float j = boatEntity.getDamageWobbleStrength() - g;
        if (j < 0f) {
            j = 0f;
        }

        if (h > 0f) {
            matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(MathHelper.sin(h) * h * j / 10f * (float)boatEntity.getDamageWobbleSide()));
        }

        float k = boatEntity.interpolateBubbleWobble(g);
        if (!MathHelper.approximatelyEquals(k, 0f)) {
            matrixStack.multiply(new Quaternion(new Vec3f(1f, 1f, 1f), boatEntity.interpolateBubbleWobble(g), true));
        }

        Pair<Identifier, BoatEntityModel> pair = texturesAndModels.get(boatEntity.getGBWBoatType());
        Identifier identifier = pair.getFirst();
        BoatEntityModel boatEntityModel = pair.getSecond();
        matrixStack.scale(-1f, -1f, 1f);
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90f));
        boatEntityModel.setAngles(boatEntity, g, 0f, -.1f, 0f, 0f);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(boatEntityModel.getLayer(identifier));
        boatEntityModel.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, 1f);
        if (!boatEntity.isSubmergedInWater()) {
            VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(RenderLayer.getWaterMask());
            boatEntityModel.getWaterPatch().render(matrixStack, vertexConsumer2, i, OverlayTexture.DEFAULT_UV);
        }

        matrixStack.pop();
        super.render(boatEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(GBWBoatEntity boatEntity) {
        return texturesAndModels.get(boatEntity.getGBWBoatType()).getFirst();
    }
}
