package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.entity.brain.MooseBrain;
import com.github.creoii.greatbigworld.entity.navigation.SwimNavigation;
import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import com.github.creoii.greatbigworld.main.registry.SensorRegistry;
import com.github.creoii.greatbigworld.main.util.Tags;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.LivingTargetCache;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Hoglin;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class MooseEntity extends TameableEntity implements Saddleable {
    private static final TrackedData<Boolean> RIGHT_ANTLER = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> LEFT_ANTLER = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> SHED_TIME = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> REGROW_TIME = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> TAME_CHANCE = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> SADDLED = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    protected static final ImmutableList<SensorType<? extends Sensor<? super MooseEntity>>> SENSORS = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_PLAYERS, SensorType.NEAREST_ITEMS, SensorType.NEAREST_ADULT, SensorType.HURT_BY, SensorRegistry.MOOSE_TEMPTATIONS);
    protected static final ImmutableList<MemoryModuleType<?>> MEMORY_MODULES = ImmutableList.of(MemoryModuleType.LOOK_TARGET, MemoryModuleType.VISIBLE_MOBS, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH, MemoryModuleType.ATE_RECENTLY, MemoryModuleType.BREED_TARGET, MemoryModuleType.TEMPTING_PLAYER, MemoryModuleType.NEAREST_VISIBLE_ADULT, MemoryModuleType.TEMPTATION_COOLDOWN_TICKS, MemoryModuleType.IS_TEMPTED, MemoryModuleType.RAM_COOLDOWN_TICKS, MemoryModuleType.RAM_TARGET, MemoryModuleType.IS_PANICKING, MemoryModuleType.IS_IN_WATER, MemoryModuleType.IS_PANICKING, MemoryModuleType.AVOID_TARGET, MemoryModuleType.ATTACK_TARGET, MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER, MemoryModuleType.ATTACK_COOLING_DOWN);
    private static final int SHED_REGROW_TIME_BASE = 168000;
    private boolean preparingCharge;
    private int headPitch;
    public final AnimationState walkingAnimationState = new AnimationState();
    public final AnimationState swimmingAnimationState = new AnimationState();
    public final AnimationState idlingInWaterAnimationState = new AnimationState();

    public MooseEntity(EntityType<? extends MooseEntity> entityType, World world) {
        super(entityType, world);
        getNavigation().setCanSwim(true);
        setPathfindingPenalty(PathNodeType.POWDER_SNOW, -1f);
        setPathfindingPenalty(PathNodeType.DANGER_POWDER_SNOW, -1f);
        setPathfindingPenalty(PathNodeType.WATER, 4f);
        lookControl = new MooseLookControl(this);
        moveControl = new AquaticMoveControl(this, 85, 10, .05f, .3f, true);
        stepHeight = 1;
    }

    public Brain.Profile<MooseEntity> createBrainProfile() {
        return Brain.createProfile(MEMORY_MODULES, SENSORS);
    }

    public Brain<?> deserializeBrain(Dynamic<?> dynamic) {
        return MooseBrain.create(createBrainProfile().deserialize(dynamic));
    }

    public static DefaultAttributeContainer.Builder createMooseAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 45d).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .30000001192092896d).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, .6000000238418579d).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1d).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6d);
    }

    public boolean isPushedByFluids() {
        return false;
    }

    public boolean canJumpToNextPathNode(PathNodeType type) {
        return super.canJumpToNextPathNode(type) && type != PathNodeType.WATER_BORDER;
    }

    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(RIGHT_ANTLER, true);
        dataTracker.startTracking(LEFT_ANTLER, true);
        dataTracker.startTracking(SHED_TIME, SHED_REGROW_TIME_BASE + random.nextInt(48000));
        dataTracker.startTracking(REGROW_TIME, SHED_REGROW_TIME_BASE + random.nextInt(48000));
        dataTracker.startTracking(TAME_CHANCE, 0);
        dataTracker.startTracking(SADDLED, false);
    }

    public boolean hasLeftAntler() {
        return dataTracker.get(LEFT_ANTLER);
    }

    public boolean hasRightAntler() {
        return dataTracker.get(RIGHT_ANTLER);
    }

    public void setLeftAntler(boolean has) {
        dataTracker.set(LEFT_ANTLER, has);
    }

    public void setRightAntler(boolean has) {
        dataTracker.set(RIGHT_ANTLER, has);
    }

    public int getShedTime() {
        return dataTracker.get(SHED_TIME);
    }

    public int getRegrowTime() {
        return dataTracker.get(REGROW_TIME);
    }

    public void decrementShedTime() {
        dataTracker.set(SHED_TIME, getShedTime() - 1);
    }

    public void decrementRegrowTime() {
        dataTracker.set(REGROW_TIME, getShedTime() - 1);
    }

    public void setShedTime(int time) {
        dataTracker.set(SHED_TIME, time);
    }

    public void setRegrowTime(int time) {
        dataTracker.set(REGROW_TIME, time);
    }

    public int getTameChance() {
        return dataTracker.get(TAME_CHANCE);
    }

    public void incrementTameChance(int amt) {
        dataTracker.set(TAME_CHANCE, Math.min(getTameChance() + amt, 100));
    }

    public void setSaddled(boolean saddled) {
        dataTracker.set(SADDLED, saddled);
    }

    public void dropAntler() {
        TrackedData<Boolean> trackedData;
        if (!hasLeftAntler()) trackedData = RIGHT_ANTLER;
        else if (!hasRightAntler()) trackedData = LEFT_ANTLER;
        else trackedData = random.nextBoolean() ? LEFT_ANTLER : RIGHT_ANTLER;

        dataTracker.set(trackedData, false);
        Vec3d vec3d = getPos();
        ItemStack itemStack = BlockRegistry.ANTLER.asItem().getDefaultStack();
        double d = MathHelper.nextBetween(random, -.2f, .2f);
        double e = MathHelper.nextBetween(random, .3f, .7f);
        double f = MathHelper.nextBetween(random, -.2f, .2f);
        ItemEntity itemEntity = new ItemEntity(world, vec3d.getX(), vec3d.getY(), vec3d.getZ(), itemStack, d, e, f);
        world.spawnEntity(itemEntity);
    }

    @Nullable @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        MooseEntity mooseEntity = EntityRegistry.MOOSE.create(world);
        if (mooseEntity != null) {
            mooseEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(25f);
            MooseBrain.rememberRamCooldown(mooseEntity, world.getRandom());
        }

        return mooseEntity;
    }

    @Override
    protected void onGrowUp() {
        getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(45f);
        super.onGrowUp();
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(Tags.ItemTags.MOOSE_BREEDING_ITEMS);
    }

    public boolean isFoodItem(ItemStack stack) {
        return stack.isIn(Tags.ItemTags.MOOSE_FOOD);
    }

    public boolean tryAttack(Entity target) {
        if (target instanceof LivingEntity living) {
            return Hoglin.tryAttack(this, living);
        }
        return false;
    }

    protected void knockback(LivingEntity target) {
        if (isAdult()) {
            Hoglin.knockback(this, target);
        }
    }

    public boolean damage(DamageSource source, float amount) {
        boolean bl = super.damage(source, amount);
        if (world.isClient) return false;
        else {
            if (bl && source.getAttacker() instanceof LivingEntity living) {
                MooseBrain.onAttacked(this, living);
            }
            return bl;
        }
    }

    public int getMaxHeadRotation() {
        return 15;
    }

    public void setHeadYaw(float headYaw) {
        int i = getMaxHeadRotation();
        float f = MathHelper.subtractAngles(bodyYaw, headYaw);
        float g = MathHelper.clamp(f, (float)(-i), (float)i);
        super.setHeadYaw(bodyYaw + g);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("HasLeftAntler", hasLeftAntler());
        nbt.putBoolean("HasRightAntler", hasRightAntler());
        if (isSaddled()) {
            nbt.put("ArmorItem", Items.SADDLE.getDefaultStack().writeNbt(new NbtCompound()));
            nbt.putBoolean("Saddled", true);
        }
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        setLeftAntler(nbt.getBoolean("HasLeftAntler"));
        setRightAntler(nbt.getBoolean("HasRightAntler"));
        if (nbt.contains("ArmorItem", 10)) {
            ItemStack itemStack = ItemStack.fromNbt(nbt.getCompound("ArmorItem"));
            if (itemStack.isOf(Items.SADDLE)) {
                setSaddled(nbt.getBoolean("Saddled"));
            }
        }
    }

    public void handleStatus(byte status) {
        if (status == 58) preparingCharge = true;
        else if (status == 59) preparingCharge = false;
        else super.handleStatus(status);
    }

    public void tickMovement() {
        if (preparingCharge) ++headPitch;
        else headPitch -= 2;

        headPitch = MathHelper.clamp(headPitch, 0, 20);
        super.tickMovement();
    }

    @Override
    public void tick() {
        // Handle Animations
        if (world.isClient()) {
            if (shouldWalk()) walkingAnimationState.startIfNotRunning(age);
            else walkingAnimationState.stop();

            if (shouldSwim()) {
                idlingInWaterAnimationState.stop();
                swimmingAnimationState.startIfNotRunning(age);
            } else if (isInsideWaterOrBubbleColumn()) {
                swimmingAnimationState.stop();
                idlingInWaterAnimationState.startIfNotRunning(age);
            } else {
                swimmingAnimationState.stop();
                idlingInWaterAnimationState.stop();
            }
        }

        super.tick();

        // Handle Antler Shedding & Regrowth
        if (isAlive() && isAdult()) {
            if (!hasRightAntler() || !hasLeftAntler()) {
                if (getRegrowTime() > 0) decrementRegrowTime();
                else {
                    if (!hasRightAntler() && !hasLeftAntler()) {
                        if (random.nextBoolean()) setRightAntler(true);
                        else setLeftAntler(true);
                    } else if (!hasRightAntler()) setRightAntler(true);
                    else if (!hasLeftAntler()) setLeftAntler(true);

                    setRegrowTime(SHED_REGROW_TIME_BASE + random.nextInt(48000));
                }
            }

            if (getShedTime() > 0) decrementShedTime();
            else {
                dropAntler();
                setShedTime(SHED_REGROW_TIME_BASE + random.nextInt(48000));
            }
        }
    }

    protected void putPlayerOnBack(PlayerEntity player) {
        if (!world.isClient) {
            player.setYaw(getYaw());
            player.setPitch(getPitch());
            player.startRiding(this);
        }
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack held = player.getStackInHand(hand);
        if (held.isEmpty()) {
            if (isBaby()) {
                return super.interactMob(player, hand);
            } else {
                putPlayerOnBack(player);
                return ActionResult.success(world.isClient);
            }
        } else {
            if (world.isClient) {
                boolean bl = isOwner(player) || isTamed() || isFoodItem(held) && !isTamed();
                return bl ? ActionResult.CONSUME : ActionResult.PASS;
            } else {
                if (!player.getAbilities().creativeMode)
                    held.decrement(1);

                if (isFoodItem(held)) {
                    incrementTameChance(random.nextInt(2) + 1);
                } else if (isBreedingItem(held)) {
                    if (random.nextInt(100) < getTameChance()) {
                        Criteria.TAME_ANIMAL.trigger((ServerPlayerEntity) player, this);
                        setOwner(player);
                        navigation.stop();
                        setTarget(null);
                        setSitting(true);
                        world.sendEntityStatus(this, (byte) 7);
                    } else {
                        world.sendEntityStatus(this, (byte) 6);
                    }
                }
                return ActionResult.SUCCESS;
            }
        }
    }

    public void travel(Vec3d movementInput) {
        if (isAlive()) {
            if (!isTamed() && canMoveVoluntarily() && isTouchingWater()) {
                updateVelocity(getMovementSpeed(), movementInput);
                move(MovementType.SELF, getVelocity());
                setVelocity(getVelocity().multiply(1.1d));
            } else {
                Entity entity = getPrimaryPassenger();
                if (entity instanceof LivingEntity livingEntity) {
                    if (hasPassengers()) {
                        setYaw(livingEntity.getYaw());
                        prevYaw = getYaw();
                        setPitch(livingEntity.getPitch() * .5f);
                        setRotation(getYaw(), getPitch());
                        bodyYaw = getYaw();
                        headYaw = bodyYaw;
                        float f = livingEntity.sidewaysSpeed * .5f;
                        float g = livingEntity.forwardSpeed;
                        if (g <= 0f) {
                            g *= .25f;
                        }

                        if (onGround) {
                            f = 0f;
                            g = 0f;
                        }

                        airStrafingSpeed = getMovementSpeed() * .1f;
                        if (isLogicalSideForUpdatingMovement()) {
                            setMovementSpeed((float) getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
                            super.travel(new Vec3d(f, movementInput.y, g));
                        } else if (livingEntity instanceof PlayerEntity) {
                            setVelocity(Vec3d.ZERO);
                        }

                        updateLimbs(this, false);
                        tryCheckBlockCollision();
                    } else {
                        airStrafingSpeed = .02f;
                        super.travel(movementInput);
                    }
                }
            }
        }
    }

    public float getHeadPitch() {
        return (float)headPitch / 20f * 30f * .017453292f;
    }

    public boolean canBreatheInWater() {
        return true;
    }

    private boolean shouldWalk() {
        return onGround && getVelocity().horizontalLengthSquared() > 1.0E-6d && !isInsideWaterOrBubbleColumn();
    }

    private boolean shouldSwim() {
        return getVelocity().horizontalLengthSquared() > 1.0E-6d && isInsideWaterOrBubbleColumn();
    }

    protected void mobTick() {
        world.getProfiler().push("mooseBrain");
        getBrain().tick((ServerWorld)world, this);
        world.getProfiler().pop();
        world.getProfiler().push("mooseActivityUpdate");
        MooseBrain.updateActivities(this);
        world.getProfiler().pop();
        super.mobTick();
    }

    @SuppressWarnings("unchecked")
    public Brain<MooseEntity> getBrain() {
        return (Brain<MooseEntity>) brain;
    }

    public boolean isAdult() {
        return !isBaby();
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return isBaby() ? dimensions.height * .65f : dimensions.height;
    }

    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        return isBaby() ? super.getDimensions(pose).scaled(.5f) : super.getDimensions(pose);
    }

    public Optional<? extends LivingEntity> getMooseTarget(MooseEntity moose) {
        return moose.getBrain().getOptionalMemory(MemoryModuleType.VISIBLE_MOBS).orElse(LivingTargetCache.empty()).findFirst(this::shouldAttack);
    }

    public boolean shouldAttack(LivingEntity entity) {
        EntityType<?> entityType = entity.getType();
        return entityType != EntityRegistry.MOOSE && entity.getPos().squaredDistanceTo(getPos()) < 64f;
    }

    protected EntityNavigation createNavigation(World world) {
        return new SwimNavigation<>(this, world, false);
    }

    @Override
    public boolean canBeSaddled() {
        return isAlive() && !isBaby() && isTamed();
    }

    @Override
    public void saddle(@Nullable SoundCategory sound) {
        if (sound != null) {
            world.playSoundFromEntity(null, this, SoundEvents.ENTITY_HORSE_SADDLE, sound, .5f, 1f);
        }
    }

    @Override
    public boolean isSaddled() {
        return dataTracker.get(SADDLED);
    }

    public class MooseLookControl extends LookControl {
        MooseLookControl(MobEntity entity) {
            super(entity);
        }

        protected boolean shouldStayHorizontal() {
            return MooseEntity.this.getMooseTarget(MooseEntity.this).isEmpty();
        }
    }
}
