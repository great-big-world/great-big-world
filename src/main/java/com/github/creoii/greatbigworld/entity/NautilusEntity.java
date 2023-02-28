package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.main.registry.ItemRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class NautilusEntity extends FishEntity {
    public NautilusEntity(EntityType<? extends NautilusEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createNautilusAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 12d).add(EntityAttributes.GENERIC_ARMOR, 4d);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_PUFFER_FISH_FLOP;
    }

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(ItemRegistry.NAUTILUS_BUCKET);
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height * .35f;
    }

    @SuppressWarnings("deprecation")
    public static boolean canSpawn(EntityType<? extends WaterCreatureEntity> type, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        return pos.getY() <= world.getSeaLevel() - 17 && world.getBlockState(pos).isOf(Blocks.WATER);
    }
}
