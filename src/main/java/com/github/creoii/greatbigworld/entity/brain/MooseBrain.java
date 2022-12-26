package com.github.creoii.greatbigworld.entity.brain;

import com.github.creoii.greatbigworld.entity.MooseEntity;
import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class MooseBrain {
    protected static final ImmutableList<SensorType<? extends Sensor<? super MooseEntity>>> SENSORS = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.HURT_BY, SensorType.CAMEL_TEMPTATIONS, SensorType.NEAREST_ADULT);
    protected static final ImmutableList<MemoryModuleType<?>> MEMORY_MODULES = ImmutableList.of(MemoryModuleType.IS_PANICKING, MemoryModuleType.HURT_BY, MemoryModuleType.HURT_BY_ENTITY, MemoryModuleType.WALK_TARGET, MemoryModuleType.LOOK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH, MemoryModuleType.VISIBLE_MOBS, MemoryModuleType.TEMPTING_PLAYER, MemoryModuleType.TEMPTATION_COOLDOWN_TICKS, MemoryModuleType.IS_TEMPTED, MemoryModuleType.BREED_TARGET, MemoryModuleType.NEAREST_VISIBLE_ADULT);

    public static Brain.Profile<MooseEntity> createProfile() {
        return Brain.createProfile(MEMORY_MODULES, SENSORS);
    }

    public static Brain<?> create(Brain<MooseEntity> brain) {
        addCoreActivities(brain);
        addIdleActivities(brain);
        addSwimActivities(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.resetPossibleActivities();
        return brain;
    }

    private static void addCoreActivities(Brain<MooseEntity> brain) {
        brain.setTaskList(Activity.CORE, 0, ImmutableList.of(new StayAboveWaterTask(.025f), new WalkTask(1f), new LookAroundTask(45, 90), new WanderAroundTask(), new TemptationCooldownTask(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)));
    }

    @SuppressWarnings("deprecation")
    private static void addIdleActivities(Brain<MooseEntity> brain) {
        brain.setTaskList(Activity.IDLE, ImmutableList.of(Pair.of(0, FollowMobWithIntervalTask.follow(EntityType.PLAYER, 6f, UniformIntProvider.create(30, 60))), Pair.of(1, new BreedTask(EntityRegistry.MOOSE, 2f)), Pair.of(2, new TemptTask(entity -> 1.25f)), Pair.of(3, WalkTowardClosestAdultTask.create(UniformIntProvider.create(5, 16), 1f)), Pair.of(4, new RandomLookAroundTask(UniformIntProvider.create(150, 250), 30f, 0f, 0f)), Pair.of(5, new RandomTask<>(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT), ImmutableList.of(Pair.of(StrollTask.create(1f), 1), Pair.of(GoTowardsLookTargetTask.create(1f, 3), 1), Pair.of(new WaitTask(30, 60), 1))))));
    }

    @SuppressWarnings("deprecation")
    private static void addSwimActivities(Brain<MooseEntity> brain) {
        brain.setTaskList(Activity.SWIM, ImmutableList.of(Pair.of(0, FollowMobWithIntervalTask.follow(EntityType.PLAYER, 6f, UniformIntProvider.create(30, 60))), Pair.of(1, new TemptTask(moose -> 1.25f)), Pair.of(3, WalkTowardsLandTask.create(8, 1.25f)), Pair.of(5, new CompositeTask<>(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT), ImmutableSet.of(), CompositeTask.Order.ORDERED, CompositeTask.RunMode.TRY_ALL, ImmutableList.of(Pair.of(StrollTask.createDynamicRadius(1f), 1), Pair.of(StrollTask.create(1f, true), 1), Pair.of(GoTowardsLookTargetTask.create(1f, 3), 1), Pair.of(TaskTriggerer.predicate(Entity::isInsideWaterOrBubbleColumn), 5))))), ImmutableSet.of(Pair.of(MemoryModuleType.IS_IN_WATER, MemoryModuleState.VALUE_PRESENT)));
    }

    public static void updateActivities(MooseEntity moose) {
        moose.getBrain().resetPossibleActivities(ImmutableList.of(Activity.SWIM, Activity.IDLE));
    }
}
