package com.github.creoii.greatbigworld.entity.brain;

import com.github.creoii.greatbigworld.entity.MooseEntity;
import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.passive.CamelEntity;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class MooseBrain {
    private static final UniformIntProvider WALKING_SPEED = UniformIntProvider.create(5, 16);

    public static Brain<?> create(Brain<MooseEntity> brain) {
        addCoreActivities(brain);
        addIdleActivities(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.resetPossibleActivities();
        return brain;
    }

    private static void addCoreActivities(Brain<MooseEntity> brain) {
        brain.setTaskList(Activity.CORE, 0, ImmutableList.of(new StayAboveWaterTask(.8f), new WalkTask(2f), new LookAroundTask(45, 90), new WanderAroundTask(), new TemptationCooldownTask(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)));
    }

    @SuppressWarnings("deprecation")
    private static void addIdleActivities(Brain<MooseEntity> brain) {
        brain.setTaskList(Activity.IDLE, ImmutableList.of(Pair.of(0, FollowMobWithIntervalTask.follow(EntityType.PLAYER, 6f, UniformIntProvider.create(30, 60))), Pair.of(0, new BreedTask(EntityRegistry.MOOSE, 1f)), Pair.of(1, new TemptTask(moose -> 1.25f)), Pair.of(2, WalkTowardClosestAdultTask.create(WALKING_SPEED, 1.25f)), Pair.of(3, new RandomTask<>(ImmutableList.of(Pair.of(StrollTask.create(1f), 2), Pair.of(GoTowardsLookTargetTask.create(1f, 3), 2), Pair.of(new WaitTask(30, 60), 1))))));
    }

    public static void updateActivities(MooseEntity moose) {
        moose.getBrain().resetPossibleActivities(ImmutableList.of(Activity.IDLE));
    }
}
