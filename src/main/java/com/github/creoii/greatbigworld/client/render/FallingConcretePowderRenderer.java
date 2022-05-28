package com.github.creoii.greatbigworld.client.render;

import com.github.creoii.greatbigworld.entity.FallingConcretePowderEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

@Environment(EnvType.CLIENT)
public class FallingConcretePowderRenderer extends EntityRenderer<FallingConcretePowderEntity> {
    public FallingConcretePowderRenderer(EntityRendererFactory.Context context) {
        super(context);
        shadowRadius = .5f;
    }

    public void render(FallingConcretePowderEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        BlockState blockState = entity.getBlockState();
        if (blockState.getRenderType() == BlockRenderType.MODEL) {
            World world = entity.getWorld();
            if (blockState != world.getBlockState(entity.getBlockPos()) && blockState.getRenderType() != BlockRenderType.INVISIBLE) {
                matrixStack.push();
                BlockPos blockPos = new BlockPos(entity.getX(), entity.getBoundingBox().maxY, entity.getZ());
                matrixStack.translate(-.5d, 0d, -.5d);
                BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
                blockRenderManager.getModelRenderer().render(world, blockRenderManager.getModel(blockState), blockState, blockPos, matrixStack, vertexConsumerProvider.getBuffer(RenderLayers.getMovingBlockLayer(blockState)), false, new Random(), blockState.getRenderingSeed(entity.getFallingBlockPos()), OverlayTexture.DEFAULT_UV);
                matrixStack.pop();
                super.render(entity, f, g, matrixStack, vertexConsumerProvider, i);
            }
        }
    }

    @SuppressWarnings("deprecation")
    public Identifier getTexture(FallingConcretePowderEntity fallingBlockEntity) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
    }
}