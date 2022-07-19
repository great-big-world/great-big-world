package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import com.github.creoii.greatbigworld.main.util.Tags;
import com.mojang.datafixers.DataFixUtils;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class HyenaEntity extends AnimalEntity implements Angerable {
    private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(HyenaEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    public static final Predicate<LivingEntity> TARGET_PREDICATE = (entity) -> {
        EntityType<?> entityType = entity.getType();
        return entityType.isIn(Tags.Entities.HYENA_PREY);
    };
    @Nullable private UUID angryAt;
    @Nullable private HyenaEntity leader;
    private int groupSize = 1;

    public HyenaEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(ANGER_TIME, 0);
    }

    protected void initGoals() {
        super.initGoals();
        goalSelector.add(4, new FollowGroupLeaderGoal(this));
        goalSelector.add(5, new PounceAtTargetGoal(this, .4f));
        goalSelector.add(6, new MeleeAttackGoal(this, 1d, true));
        goalSelector.add(7, new AnimalMateGoal(this, 1d));
        goalSelector.add(8, new WanderAroundFarGoal(this, 1d));
        goalSelector.add(9, new LookAroundGoal(this));
        targetSelector.add(3, new RevengeGoal(this).setGroupRevenge());
        targetSelector.add(5, new ActiveTargetGoal<>(this, AnimalEntity.class, false, TARGET_PREDICATE));
        targetSelector.add(8, new UniversalAngerGoal<HyenaEntity>(this, true));
    }

    public static DefaultAttributeContainer.Builder createHyenaAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 14d).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 24d).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .4d).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6d);
    }

    @Override
    public int getAngerTime() {
        return dataTracker.get(ANGER_TIME);
    }

    @Override
    public void setAngerTime(int angerTime) {
        dataTracker.set(ANGER_TIME, angerTime);
    }

    @Nullable
    @Override
    public UUID getAngryAt() {
        return angryAt;
    }

    @Override
    public void setAngryAt(@Nullable UUID angryAt) {
        this.angryAt = angryAt;
    }

    @Override
    public void chooseRandomAngerTime() {
        setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return EntityRegistry.HYENA.create(world);
    }

    public void joinGroupOf(HyenaEntity groupLeader) {
        leader = groupLeader;
        groupLeader.increaseGroupSize();
    }

    public void leaveGroup() {
        leader.decreaseGroupSize();
        leader = null;
    }

    private void increaseGroupSize() {
        ++groupSize;
    }

    private void decreaseGroupSize() {
        --groupSize;
    }

    public boolean canHaveMoreHyenasInGroup() {
        return hasOtherHyenaInGroup() && groupSize < getMaxGroupSize();
    }

    public boolean hasOtherHyenaInGroup() {
        return groupSize > 1;
    }

    public boolean isCloseEnoughToLeader() {
        return squaredDistanceTo(leader) <= 121d;
    }

    public int getLimitPerChunk() {
        return getMaxGroupSize();
    }

    public int getMaxGroupSize() {
        return 5;
    }

    public boolean hasLeader() {
        return leader != null && leader.isAlive();
    }

    public void moveTowardLeader() {
        if (hasLeader()) {
            getNavigation().startMovingTo(leader, .65d);
        }
    }

    public void pullInOtherHyenas(Stream<? extends HyenaEntity> hyena) {
        hyena.limit(getMaxGroupSize() - groupSize).filter(hyena1 -> hyena1 != this).forEach(hyena1 -> hyena1.joinGroupOf(this));
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
        if (entityData == null) entityData = new HyenaEntity.HyenaData(this);
        else joinGroupOf(((HyenaEntity.HyenaData)entityData).leader);
        return entityData;
    }

    public record HyenaData(HyenaEntity leader) implements EntityData { }

    public static class FollowGroupLeaderGoal extends Goal {
        private final HyenaEntity hyena;
        private int moveDelay;
        private int checkSurroundingDelay;

        public FollowGroupLeaderGoal(HyenaEntity hyena) {
            this.hyena = hyena;
            checkSurroundingDelay = getSurroundingSearchDelay(hyena);
        }

        protected int getSurroundingSearchDelay(HyenaEntity hyena) {
            return toGoalTicks(200 + hyena.getRandom().nextInt(200) % 20);
        }

        public boolean canStart() {
            if (hyena.canHaveMoreHyenasInGroup()) return false;
            else if (hyena.hasLeader()) return true;
            else if (checkSurroundingDelay > 0) {
                --checkSurroundingDelay;
                return false;
            } else {
                checkSurroundingDelay = getSurroundingSearchDelay(hyena);
                List<? extends HyenaEntity> list = hyena.world.getEntitiesByClass(hyena.getClass(), hyena.getBoundingBox().expand(8.0D, 8.0D, 8.0D), hyena -> hyena.canHaveMoreHyenasInGroup() || !hyena.hasLeader());
                HyenaEntity hyenaEntity = DataFixUtils.orElse(list.stream().filter(HyenaEntity::canHaveMoreHyenasInGroup).findAny(), hyena);
                hyenaEntity.pullInOtherHyenas(list.stream().filter(hyena -> !hyena.hasLeader()));
                return hyena.hasLeader();
            }
        }

        public boolean shouldContinue() {
            return hyena.hasLeader() && hyena.isCloseEnoughToLeader();
        }

        public void start() {
            this.moveDelay = 0;
        }

        public void stop() {
            hyena.leaveGroup();
        }

        public void tick() {
            if (--this.moveDelay <= 0) {
                this.moveDelay = this.getTickCount(10);
                hyena.moveTowardLeader();
            }
        }
    }
}
