package com.github.creoii.greatbigworld.entity.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.function.Predicate;

public class FleeOrAngerAtEntityGoal<T extends LivingEntity> extends Goal {
    protected final PathAwareEntity mob;
    private final double slowSpeed;
    private final double fastSpeed;
    @Nullable protected T targetEntity;
    protected final float angerDistance;
    protected final float fleeDistance;
    @Nullable protected Path fleePath;
    protected final EntityNavigation fleeingEntityNavigation;
    protected final Class<T> classToFleeFrom;
    protected final Predicate<LivingEntity> angerPredicate;
    protected final Predicate<LivingEntity> extraInclusionSelector;
    protected final Predicate<LivingEntity> inclusionSelector;
    private final TargetPredicate withinRangePredicate;

    public FleeOrAngerAtEntityGoal(PathAwareEntity mob, Class<T> fleeFromType, float angerDistance, float distance, double slowSpeed, double fastSpeed) {
        this(mob, fleeFromType, livingEntity -> true, livingEntity -> true, angerDistance, distance, slowSpeed, fastSpeed, EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR::test);
    }

    public FleeOrAngerAtEntityGoal(PathAwareEntity mob, Class<T> fleeFromType, Predicate<LivingEntity> extraInclusionSelector, Predicate<LivingEntity> angerPredicate, float angerDistance, float distance, double slowSpeed, double fastSpeed, Predicate<LivingEntity> inclusionSelector) {
        this.mob = mob;
        this.classToFleeFrom = fleeFromType;
        this.extraInclusionSelector = extraInclusionSelector;
        this.angerPredicate = angerPredicate;
        this.angerDistance = angerDistance;
        this.fleeDistance = distance;
        this.slowSpeed = slowSpeed;
        this.fastSpeed = fastSpeed;
        this.inclusionSelector = inclusionSelector;
        this.fleeingEntityNavigation = mob.getNavigation();
        this.setControls(EnumSet.of(Control.MOVE));
        this.withinRangePredicate = TargetPredicate.createAttackable().setBaseMaxDistance(distance).setPredicate(inclusionSelector.and(extraInclusionSelector));
    }

    public FleeOrAngerAtEntityGoal(PathAwareEntity fleeingEntity, Class<T> classToFleeFrom, Predicate<LivingEntity> angerPredicate, float angerDistance, float fleeDistance, double fleeSlowSpeed, double fleeFastSpeed, Predicate<LivingEntity> inclusionSelector) {
        this(fleeingEntity, classToFleeFrom, livingEntity -> true, angerPredicate, angerDistance, fleeDistance, fleeSlowSpeed, fleeFastSpeed, inclusionSelector);
    }

    public boolean canStart() {
        targetEntity = mob.world.getClosestEntity(mob.world.getEntitiesByClass(classToFleeFrom, mob.getBoundingBox().expand(angerDistance, 3d, angerDistance), livingEntity -> true), withinRangePredicate, mob, mob.getX(), mob.getY(), mob.getZ());
        if (targetEntity == null) {
            targetEntity = mob.world.getClosestEntity(mob.world.getEntitiesByClass(classToFleeFrom, mob.getBoundingBox().expand(fleeDistance, 3d, fleeDistance), livingEntity -> true), withinRangePredicate, mob, mob.getX(), mob.getY(), mob.getZ());
        } else if (angerPredicate.test(targetEntity)) {
            mob.setTarget(targetEntity);
            return false;
        }

        if (targetEntity == null) {
            return false;
        } else {
            Vec3d vec3d = NoPenaltyTargeting.findFrom(mob, 16, 7, targetEntity.getPos());
            if (vec3d == null) {
                return false;
            } else if (targetEntity.squaredDistanceTo(vec3d.x, vec3d.y, vec3d.z) < targetEntity.squaredDistanceTo(mob)) {
                return false;
            } else {
                fleePath = fleeingEntityNavigation.findPathTo(vec3d.x, vec3d.y, vec3d.z, 0);
                return fleePath != null;
            }
        }
    }

    public boolean shouldContinue() {
        return !fleeingEntityNavigation.isIdle();
    }

    public void start() {
        fleeingEntityNavigation.startMovingAlong(fleePath, slowSpeed);
    }

    public void stop() {
        targetEntity = null;
    }

    public void tick() {
        if (mob.squaredDistanceTo(targetEntity) < 49d) {
            mob.getNavigation().setSpeed(fastSpeed);
        } else {
            mob.getNavigation().setSpeed(slowSpeed);
        }
    }
}
