package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.entity.brain.MooseBrain;
import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import com.github.creoii.greatbigworld.main.registry.ItemRegistry;
import com.github.creoii.greatbigworld.main.registry.SensorRegistry;
import com.github.creoii.greatbigworld.main.util.Tags;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MooseEntity extends TameableEntity {
    private static final TrackedData<Boolean> RIGHT_ANTLER = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> LEFT_ANTLER = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> SHED_TIME = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> REGROW_TIME = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.INTEGER);
    protected static final ImmutableList<SensorType<? extends Sensor<? super MooseEntity>>> SENSORS = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_PLAYERS, SensorType.NEAREST_ITEMS, SensorType.NEAREST_ADULT, SensorType.HURT_BY, SensorRegistry.MOOSE_TEMPTATIONS);
    protected static final ImmutableList<MemoryModuleType<?>> MEMORY_MODULES = ImmutableList.of(MemoryModuleType.LOOK_TARGET, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH, MemoryModuleType.BREED_TARGET, MemoryModuleType.TEMPTING_PLAYER, MemoryModuleType.NEAREST_VISIBLE_ADULT, MemoryModuleType.TEMPTATION_COOLDOWN_TICKS, MemoryModuleType.IS_TEMPTED, MemoryModuleType.RAM_COOLDOWN_TICKS, MemoryModuleType.RAM_TARGET, MemoryModuleType.ANGRY_AT, MemoryModuleType.UNIVERSAL_ANGER, MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_NEMESIS, MemoryModuleType.PACIFIED, MemoryModuleType.ATTACK_TARGET);
    private static final int SHED_REGROW_TIME_BASE = 168000;
    private int tameProbability;
    private boolean preparingCharge;
    private int headPitch;

    public MooseEntity(EntityType<? extends MooseEntity> entityType, World world) {
        super(entityType, world);
        getNavigation().setCanSwim(true);
        setPathfindingPenalty(PathNodeType.POWDER_SNOW, -1f);
        setPathfindingPenalty(PathNodeType.DANGER_POWDER_SNOW, -1f);
        tameProbability = 0;
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

    protected void onGrowUp() {
        if (isBaby()) {
            getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(2d);
            removeAntlers();
        } else {
            getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(6d);
            addAntlers();
        }
    }

    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(RIGHT_ANTLER, true);
        dataTracker.startTracking(LEFT_ANTLER, true);
        dataTracker.startTracking(SHED_TIME, SHED_REGROW_TIME_BASE + random.nextInt(48000));
        dataTracker.startTracking(REGROW_TIME, SHED_REGROW_TIME_BASE + random.nextInt(48000));
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

    public void addAntlers() {
        dataTracker.set(LEFT_ANTLER, true);
        dataTracker.set(RIGHT_ANTLER, true);
    }

    public void removeAntlers() {
        dataTracker.set(LEFT_ANTLER, false);
        dataTracker.set(RIGHT_ANTLER, false);
    }

    @Nullable @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        MooseEntity mooseEntity = EntityRegistry.MOOSE.create(world);
        if (mooseEntity != null) {
            MooseBrain.rememberRamCooldown(mooseEntity, world.getRandom());
        }

        return mooseEntity;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(Tags.ItemTags.MOOSE_BREEDING_ITEMS);
    }

    public boolean isFoodItem(ItemStack stack) {
        return stack.isIn(Tags.ItemTags.MOOSE_FOOD);
    }

    public boolean tryAttack(Entity target) {
        if (target.isLiving()) {
            return Hoglin.tryAttack(this, (LivingEntity)target);
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
            if (bl && source.getAttacker() instanceof LivingEntity) {
                MooseBrain.onAttacked(this, (LivingEntity)source.getAttacker());
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
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        setLeftAntler(nbt.getBoolean("HasLeftAntler"));
        setRightAntler(nbt.getBoolean("HasRightAntler"));
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
        super.tick();
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

    public float getHeadPitch() {
        return (float)headPitch / 20f * 30f * .017453292f;
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

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (world.isClient) {
            boolean bl = isOwner(player) || isBaby() || isTamed() || itemStack.isOf(Items.BONE) && !isTamed();
            return bl ? ActionResult.CONSUME : ActionResult.PASS;
        } else {
            if (isFoodItem(itemStack)) {
                if (!player.getAbilities().creativeMode) itemStack.decrement(1);
                if (isTamed() && getHealth() < getMaxHealth()) {
                    heal(item.getFoodComponent().getHunger());
                } else {
                    if (tameProbability <= 100) showFeedParticle();
                    tameProbability = MathHelper.clamp(tameProbability += random.nextBetween(2, 5), 0, 100);
                }
                return ActionResult.SUCCESS;
            } else if (isBreedingItem(itemStack)) {
                if (!player.getAbilities().creativeMode) itemStack.decrement(1);

                if (random.nextInt((16 / tameProbability) * 100) == 0) {
                    setOwner(player);
                    navigation.stop();
                    setTarget(null);
                    world.sendEntityStatus(this, (byte)7);
                } else {
                    if (random.nextInt((8 / tameProbability) * 100) == 0)
                        MooseBrain.onAttacked(this, player);
                    world.sendEntityStatus(this, (byte)6);
                }
                return ActionResult.SUCCESS;
            }
            return super.interactMob(player, hand);
        }
    }

    public void showFeedParticle() {
        for (int i = 0; i < 5; ++i) {
            double d = random.nextGaussian() * .02d;
            double e = random.nextGaussian() * .02d;
            double f = random.nextGaussian() * .02d;
            world.addParticle(ParticleTypes.HAPPY_VILLAGER, getParticleX(1d), getRandomBodyY() + .5d, getParticleZ(1d), d, e, f);
        }
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return isBaby() ? dimensions.height * .65f : dimensions.height;
    }

    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        return isBaby() ? super.getDimensions(pose).scaled(.5f) : super.getDimensions(pose);
    }
}
