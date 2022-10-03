package com.github.creoii.greatbigworld.entity.brain;

import com.github.creoii.greatbigworld.entity.MooseEntity;
import com.github.creoii.greatbigworld.entity.brain.task.RamImpactTask;
import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;

import java.util.Optional;
import java.util.Set;

public class MooseBrain {
    private static final UniformIntProvider WALKING_SPEED = UniformIntProvider.create(5, 16);
    private static final UniformIntProvider RAM_COOLDOWN_RANGE = UniformIntProvider.create(150, 300);
    private static final UniformIntProvider AVOID_MEMORY_DURATION = TimeHelper.betweenSeconds(5, 20);
    private static final TargetPredicate RAM_TARGET_PREDICATE = TargetPredicate.createAttackable().setPredicate(entity -> {
        return !entity.getType().equals(EntityRegistry.MOOSE) && entity.world.getWorldBorder().contains(entity.getBoundingBox());
    });

    public static void rememberRamCooldown(MooseEntity moose, Random random) {
        moose.getBrain().remember(MemoryModuleType.RAM_COOLDOWN_TICKS, RAM_COOLDOWN_RANGE.get(random));
    }

    public static Brain<?> create(Brain<MooseEntity> brain) {
        addCoreActivities(brain);
        addIdleActivities(brain);
        addSwimActivities(brain);
        addRamActivities(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.resetPossibleActivities();
        return brain;
    }

    private static void addCoreActivities(Brain<MooseEntity> brain) {
        brain.setTaskList(Activity.CORE, 0, ImmutableList.of(
                new WalkTask(1f),
                new LookAroundTask(45, 90),
                new WanderAroundTask(),
                new TemptationCooldownTask(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS),
                new TemptationCooldownTask(MemoryModuleType.RAM_COOLDOWN_TICKS)
        ));
    }

    private static void addIdleActivities(Brain<MooseEntity> brain) {
        brain.setTaskList(Activity.IDLE, ImmutableList.of(
                Pair.of(1, new UpdateAttackTargetTask<>(MooseEntity::isAdult, MooseBrain::getPreferredTarget)),
                Pair.of(2, new WalkTowardClosestAdultTask<>(WALKING_SPEED, 1.25f)),
                Pair.of(3, new FollowMobTask(MooseBrain::isThreateningEntity, 14f)),
                Pair.of(4, new FindInteractionTargetTask(EntityType.PLAYER, 5))
        ), Set.of(Pair.of(MemoryModuleType.ANGRY_AT, MemoryModuleState.VALUE_ABSENT), Pair.of(MemoryModuleType.UNIVERSAL_ANGER, MemoryModuleState.REGISTERED), Pair.of(MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER, MemoryModuleState.VALUE_ABSENT), Pair.of(MemoryModuleType.NEAREST_VISIBLE_NEMESIS, MemoryModuleState.VALUE_ABSENT)));
    }

    private static void addSwimActivities(Brain<MooseEntity> brain) {
        brain.setTaskList(Activity.SWIM, ImmutableList.of(
                Pair.of(3, new WalkTowardsLandTask(8, 1.5f)),
                Pair.of(4, new AquaticStrollTask(1f))
        ), Set.of(Pair.of(MemoryModuleType.IS_IN_WATER, MemoryModuleState.VALUE_PRESENT)));
    }

    private static void addRamActivities(Brain<MooseEntity> brain) {
        brain.setTaskList(Activity.RAM, ImmutableList.of(
                Pair.of(0, new RamImpactTask<>(moose -> RAM_COOLDOWN_RANGE, RAM_TARGET_PREDICATE, 1.25f, moose -> moose.isBaby() ? 1d : 2.5d, moose -> SoundEvents.ENTITY_GOAT_RAM_IMPACT)),
                Pair.of(1, new PrepareRamTask<>(moose -> RAM_COOLDOWN_RANGE.getMin(), 2, 7, 1f, RAM_TARGET_PREDICATE, 16, moose -> SoundEvents.ENTITY_GOAT_PREPARE_RAM))
        ), Set.of(Pair.of(MemoryModuleType.TEMPTING_PLAYER, MemoryModuleState.VALUE_ABSENT), Pair.of(MemoryModuleType.BREED_TARGET, MemoryModuleState.VALUE_ABSENT), Pair.of(MemoryModuleType.RAM_COOLDOWN_TICKS, MemoryModuleState.VALUE_ABSENT)));
    }

    public static void updateActivities(MooseEntity moose) {
        moose.getBrain().resetPossibleActivities(ImmutableList.of(Activity.IDLE, Activity.SWIM, Activity.RAM));
    }

    public static Ingredient getTemptItems() {
        return Ingredient.ofItems(BlockRegistry.YELLOW_ASPEN_SAPLING, BlockRegistry.GREEN_ASPEN_SAPLING, Items.SPRUCE_SAPLING);
    }

    private static Optional<? extends LivingEntity> getPreferredTarget(MooseEntity moose) {
        Brain<MooseEntity> brain = moose.getBrain();
        Optional<LivingEntity> livingTarget = LookTargetUtil.getEntity(moose, MemoryModuleType.ANGRY_AT);
        if (livingTarget.isPresent() && Sensor.testAttackableTargetPredicate(moose, livingTarget.get())) {
            return livingTarget;
        } else {
            Optional<PlayerEntity> playerTarget;
            if (brain.hasMemoryModule(MemoryModuleType.UNIVERSAL_ANGER)) {
                playerTarget = brain.getOptionalMemory(MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER);
                if (playerTarget.isPresent()) {
                    return playerTarget;
                }
            }

            return brain.getOptionalMemory(MemoryModuleType.NEAREST_VISIBLE_NEMESIS);
        }
    }

    public static void onAttacked(MooseEntity moose, LivingEntity attacker) {
        Brain<MooseEntity> brain = moose.getBrain();
        brain.forget(MemoryModuleType.PACIFIED);
        brain.forget(MemoryModuleType.BREED_TARGET);
        if (moose.isBaby()) {
            avoidEnemy(moose, attacker);
        } else {
            targetEnemy(moose, attacker);
        }
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
        brain.remember(MemoryModuleType.ATTACK_TARGET, target, 200L);
    }

    public static boolean isThreateningEntity(LivingEntity target) {
        return target.getType() == EntityType.PLAYER || target.getType() == EntityType.WOLF;
    }
}
