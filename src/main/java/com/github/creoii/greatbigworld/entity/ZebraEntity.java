package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ZebraEntity extends AnimalEntity {
    public ZebraEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createZebraAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 24d).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 24d).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .35d);
    }

    @Override
    protected void initGoals() {
        goalSelector.add(1, new EscapeDangerGoal(this, .65f));
        goalSelector.add(3, new FleeEntityGoal<WolfEntity>(this, WolfEntity.class, 12f, .45, .55f));
        goalSelector.add(4, new FollowParentGoal(this, .75d));
        goalSelector.add(6, new WanderAroundFarGoal(this, .7d));
        goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6f));
        goalSelector.add(8, new LookAroundGoal(this));
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return EntityRegistry.ZEBRA.create(world);
    }
}
