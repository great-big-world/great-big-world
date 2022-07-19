package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DeerEntity extends AnimalEntity {
    public DeerEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createDeerAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 16d).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 24d).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .5d);
    }

    @Override
    protected void initGoals() {
        goalSelector.add(1, new EscapeDangerGoal(this, .75f));
        goalSelector.add(3, new FleeEntityGoal<PlayerEntity>(this, PlayerEntity.class, 12f, .65, 1.05f));
        goalSelector.add(4, new FollowParentGoal(this, .65d));
        goalSelector.add(6, new WanderAroundFarGoal(this, .7d));
        goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 10f));
        goalSelector.add(8, new LookAroundGoal(this));
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return EntityRegistry.DEER.create(world);
    }
}
