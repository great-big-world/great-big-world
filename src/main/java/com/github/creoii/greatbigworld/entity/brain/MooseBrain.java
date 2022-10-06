package com.github.creoii.greatbigworld.entity.brain;

import com.github.creoii.greatbigworld.entity.MooseEntity;
import com.github.creoii.greatbigworld.entity.brain.task.PrepareRamTask;
import com.github.creoii.greatbigworld.entity.brain.task.RamImpactTask;
import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import com.github.creoii.greatbigworld.main.util.Tags;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class MooseBrain {
    private static final UniformIntProvider WALKING_SPEED = UniformIntProvider.create(10, 24);
    private static final UniformIntProvider RAM_COOLDOWN_RANGE = UniformIntProvider.create(0, 40);
    private static final UniformIntProvider AVOID_MEMORY_DURATION = TimeHelper.betweenSeconds(5, 20);
    private static final TargetPredicate RAM_TARGET_PREDICATE = TargetPredicate.createAttackable().setPredicate(entity -> {
        return !entity.getType().equals(EntityRegistry.MOOSE) && entity.world.getWorldBorder().contains(entity.getBoundingBox());
    });

    public static void rememberRamCooldown(MooseEntity moose, Random random) {
        moose.getBrain().remember(MemoryModuleType.RAM_COOLDOWN_TICKS, RAM_COOLDOWN_RANGE.get(random));
    }

    public static Brain<?> create(Brain<MooseEntity> brain) {
        addCoreTasks(brain);
        addIdleTasks(brain);
        addSwimTasks(brain);
        //addRamTasks(brain);
        //addFightTasks(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.resetPossibleActivities();
        return brain;
    }

    private static void addCoreTasks(Brain<MooseEntity> brain) {
        brain.setTaskList(Activity.CORE, 0, ImmutableList.of(
                new WanderAroundTask(),
                new WalkTask(2f),
                new LookAroundTask(45, 90),
                new TemptationCooldownTask(MemoryModuleType.RAM_COOLDOWN_TICKS),
                new TemptationCooldownTask(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)
        ));
    }

    private static void addIdleTasks(Brain<MooseEntity> brain) {
        brain.setTaskList(Activity.IDLE, ImmutableList.of(
                Pair.of(0, new TimeLimitedTask<>(new FollowMobTask(EntityType.PLAYER, 6f), UniformIntProvider.create(30, 60))),
                Pair.of(0, new BreedTask(EntityRegistry.MOOSE, 2f)),
                Pair.of(1, new TemptTask(moose -> 2f)),
                Pair.of(2, new WalkTowardClosestAdultTask<>(WALKING_SPEED, 2f)),
                Pair.of(3, new RandomTask<>(ImmutableList.of(
                        Pair.of(new WalkTowardsLandTask(6, 2f), 3),
                        Pair.of(new StrollTask(1f), 2),
                        Pair.of(new GoTowardsLookTarget(2f, 3), 2),
                        Pair.of(new WaitTask(30, 60), 1)
                ))),
                Pair.of(5, new UpdateAttackTargetTask<>(MooseBrain::getNearestVisibleTargetablePlayer))
        ), Set.of(Pair.of(MemoryModuleType.RAM_TARGET, MemoryModuleState.VALUE_ABSENT), Pair.of(MemoryModuleType.IS_IN_WATER, MemoryModuleState.VALUE_ABSENT)));
    }

    private static void addSwimTasks(Brain<MooseEntity> brain) {
        brain.setTaskList(Activity.SWIM, ImmutableList.of(
                Pair.of(0, new TimeLimitedTask<>(new FollowMobTask(EntityType.PLAYER, 6f), UniformIntProvider.create(30, 60))),
                Pair.of(1, new TemptTask(moose -> 2f)),
                Pair.of(3, new WalkTowardsLandTask(8, 2f)),
                Pair.of(3, new CompositeTask<>(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT), ImmutableSet.of(), CompositeTask.Order.ORDERED, CompositeTask.RunMode.TRY_ALL, ImmutableList.of(
                        Pair.of(new AquaticStrollTask(1.5f), 1),
                        Pair.of(new StrollTask(2f, true), 1),
                        Pair.of(new GoTowardsLookTarget(2f, 3), 1),
                        Pair.of(new ConditionalTask<>(Entity::isInsideWaterOrBubbleColumn, new WaitTask(30, 60)), 5)
                ))),
                Pair.of(5, new UpdateAttackTargetTask<>(MooseBrain::getNearestVisibleTargetablePlayer))
        ), Set.of(Pair.of(MemoryModuleType.RAM_TARGET, MemoryModuleState.VALUE_ABSENT), Pair.of(MemoryModuleType.IS_IN_WATER, MemoryModuleState.VALUE_PRESENT)));
    }

    private static void addFightTasks(Brain<MooseEntity> brain) {
        brain.setTaskList(Activity.FIGHT, 5, ImmutableList.of(
                new BreedTask(EntityRegistry.MOOSE, 2f),
                new RangedApproachTask(2f),
                new ConditionalTask<>(MooseEntity::isAdult, new MeleeAttackTask(40)),
                new ConditionalTask<>(PassiveEntity::isBaby, new MeleeAttackTask(15)),
                new ForgetAttackTargetTask<>(),
                new ForgetTask<>(MooseBrain::hasBreedTarget, MemoryModuleType.ATTACK_TARGET)
        ), MemoryModuleType.ATTACK_TARGET);
    }

    /**
     * This will make the moose ram entities when attacked
     */
    private static void addRamTasks(Brain<MooseEntity> brain) {
        brain.setTaskList(Activity.RAM, ImmutableList.of(
                Pair.of(0, new RamImpactTask<>(moose -> RAM_COOLDOWN_RANGE, RAM_TARGET_PREDICATE, 3f, moose -> moose.isBaby() ? 1d : 2.5d, moose -> SoundEvents.ENTITY_GOAT_RAM_IMPACT)),
                Pair.of(1, new PrepareRamTask<>(moose -> RAM_COOLDOWN_RANGE.getMin(), 2, 12, 3f, moose -> moose.hasLeftAntler() || moose.hasRightAntler(), RAM_TARGET_PREDICATE, 4, moose -> SoundEvents.ENTITY_GOAT_PREPARE_RAM, false))
        ), Set.of(Pair.of(MemoryModuleType.TEMPTING_PLAYER, MemoryModuleState.VALUE_ABSENT), Pair.of(MemoryModuleType.BREED_TARGET, MemoryModuleState.VALUE_ABSENT), Pair.of(MemoryModuleType.RAM_COOLDOWN_TICKS, MemoryModuleState.VALUE_ABSENT)));
    }

    public static void updateActivities(MooseEntity moose) {
        Brain<MooseEntity> brain = moose.getBrain();
        Activity activity = brain.getFirstPossibleNonCoreActivity().orElse(null);
        brain.resetPossibleActivities(ImmutableList.of(Activity.FIGHT, Activity.RAM, Activity.SWIM, Activity.IDLE));
        Activity activity2 = brain.getFirstPossibleNonCoreActivity().orElse(null);
        if (activity != activity2)
            Objects.requireNonNull(moose);

        moose.setAttacking(brain.hasMemoryModule(MemoryModuleType.ATTACK_TARGET));
    }

    public static Ingredient getTemptItems() {
        return Ingredient.fromTag(Tags.ItemTags.MOOSE_BREEDING_ITEMS);
    }

    public static void onAttacked(MooseEntity moose, LivingEntity attacker) {
        moose.getBrain().forget(MemoryModuleType.BREED_TARGET);

        if (moose.isBaby()) avoidEnemy(moose, attacker);
        else targetEnemy(moose, attacker);
    }

    private static void avoidEnemy(MooseEntity moose, LivingEntity target) {
        moose.getBrain().forget(MemoryModuleType.ATTACK_TARGET);
        moose.getBrain().forget(MemoryModuleType.WALK_TARGET);
        moose.getBrain().remember(MemoryModuleType.AVOID_TARGET, target, AVOID_MEMORY_DURATION.get(moose.world.random));
    }

    private static void targetEnemy(MooseEntity moose, LivingEntity target) {
        if (target.getType() != EntityRegistry.MOOSE) {
            if (!LookTargetUtil.isNewTargetTooFar(moose, target, 4d)) {
                if (Sensor.testAttackableTargetPredicate(moose, target)) {
                    setAttackTarget(moose, target);
                }
            }
        }
    }

    private static void setAttackTarget(MooseEntity moose, LivingEntity target) {
        Brain<MooseEntity> brain = moose.getBrain();
        brain.forget(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
        brain.forget(MemoryModuleType.BREED_TARGET);
        brain.remember(MemoryModuleType.RAM_TARGET, target.getPos());
        brain.remember(MemoryModuleType.ATTACK_TARGET, target, 200L);
    }

    private static Optional<? extends LivingEntity> getNearestVisibleTargetablePlayer(MooseEntity moose) {
        return !hasBreedTarget(moose) ? moose.getBrain().getOptionalMemory(MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER) : Optional.empty();
    }

    private static boolean hasBreedTarget(MooseEntity moose) {
        return moose.getBrain().hasMemoryModule(MemoryModuleType.BREED_TARGET);
    }
}
