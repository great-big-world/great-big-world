package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.entity.brain.MooseBrain;
import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import com.github.creoii.greatbigworld.main.util.Tags;
import com.mojang.serialization.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Hoglin;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SaddleItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class MooseEntity extends AbstractHorseEntity implements Saddleable {
    private static final TrackedData<Boolean> RIGHT_ANTLER = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> LEFT_ANTLER = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> SHED_TIME = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> REGROW_TIME = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final int SHED_REGROW_TIME_BASE = 168000;
    public final AnimationState walkingAnimationState = new AnimationState();
    public final AnimationState swimmingAnimationState = new AnimationState();
    public final AnimationState idlingInWaterAnimationState = new AnimationState();

    public MooseEntity(EntityType<? extends MooseEntity> entityType, World world) {
        super(entityType, world);
        getNavigation().setCanSwim(true);
        setPathfindingPenalty(PathNodeType.POWDER_SNOW, -1f);
        setPathfindingPenalty(PathNodeType.DANGER_POWDER_SNOW, -1f);
        setPathfindingPenalty(PathNodeType.WATER, 2f);
        lookControl = new LookControl(this);
        moveControl = new AquaticMoveControl(this, 85, 10, .06f, .15f, true);
        stepHeight = 1.5f;
    }

    @Override
    protected Brain.Profile<MooseEntity> createBrainProfile() {
        return MooseBrain.createProfile();
    }

    public Brain<?> deserializeBrain(Dynamic<?> dynamic) {
        return MooseBrain.create(createBrainProfile().deserialize(dynamic));
    }

    public static DefaultAttributeContainer.Builder createMooseAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 60d).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .22499999403953552d).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, .6000000238418579d).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1d).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6d).add(EntityAttributes.HORSE_JUMP_STRENGTH, 0d);
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
    }

    public boolean hasLeftAntler() {
        return dataTracker.get(LEFT_ANTLER);
    }

    public boolean hasRightAntler() {
        return dataTracker.get(RIGHT_ANTLER);
    }

    public void setLeftAntler(boolean hasLeftAntler) {
        dataTracker.set(LEFT_ANTLER, hasLeftAntler);
    }

    public void setRightAntler(boolean hasRightAntler) {
        dataTracker.set(RIGHT_ANTLER, hasRightAntler);
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

    @Nullable @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        MooseEntity mooseEntity = EntityRegistry.MOOSE.create(world);
        if (mooseEntity != null) {
            mooseEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(25f);
            mooseEntity.setLeftAntler(false);
            mooseEntity.setRightAntler(false);
        }
        return mooseEntity;
    }

    @Override
    protected void onGrowUp() {
        getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(45f);
        setLeftAntler(true);
        setRightAntler(true);
        super.onGrowUp();
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(Tags.ItemTags.MOOSE_BREEDING_ITEMS);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (!hasPassengers() && !isBaby()) {
            if (!isTame() || !player.shouldCancelInteraction()) {
                ItemStack itemStack = player.getStackInHand(hand);
                if (!itemStack.isEmpty()) {
                    if (isBreedingItem(itemStack)) {
                        eat(player, hand, itemStack);
                        if (!world.isClient && getBreedingAge() == 0 && !isInLove()) {
                            lovePlayer(player);
                        }
                        if (getHealth() < getMaxHealth()) {
                            heal(5f);
                        }
                        if (isBaby()) {
                            world.addParticle(ParticleTypes.HAPPY_VILLAGER, getParticleX(1d), getRandomBodyY() + .5d, getParticleZ(1d), 0d, 0d, 0d);
                            if (!world.isClient) {
                                growUp(100);
                            }
                        }
                        emitGameEvent(GameEvent.EAT);
                        return ActionResult.success(world.isClient);
                    }
                    ActionResult actionResult = itemStack.useOnEntity(player, this, hand);
                    if (actionResult.isAccepted()) {
                        if (itemStack.getItem() instanceof SaddleItem) {
                            updateSaddle();
                        }
                        return actionResult;
                    }
                }
                putPlayerOnBack(player);
            }
            return ActionResult.success(this.world.isClient);
        } else {
            return super.interactMob(player, hand);
        }
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

    @Override
    public double getMountedHeightOffset() {
        return 1.9d;
    }

    @Override
    public void tick() {
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

    public boolean canBreatheInWater() {
        return true;
    }

    private boolean shouldWalk() {
        return onGround && getVelocity().horizontalLengthSquared() > 1e-6d && !isInsideWaterOrBubbleColumn();
    }

    private boolean shouldSwim() {
        return getVelocity().horizontalLengthSquared() > 1e-6d && isInsideWaterOrBubbleColumn();
    }

    protected void mobTick() {
        world.getProfiler().push("mooseBrain");
        getBrain().tick((ServerWorld) world, this);
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

    protected EntityNavigation createNavigation(World world) {
        return new MooseSwimNavigation(this, world);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        if (entityData == null) {
            entityData = new PassiveEntity.PassiveData(true);
        }

        PassiveEntity.PassiveData passiveData = (PassiveEntity.PassiveData)entityData;
        if (passiveData.canSpawnBaby() && passiveData.getSpawnedCount() > 0 && world.getRandom().nextFloat() <= passiveData.getBabyChance()) {
            setBreedingAge(-24000);
        }

        if (isBaby()) {
            setRightAntler(false);
            setLeftAntler(false);
        }

        passiveData.countSpawned();
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    @Override
    public void saddle(@Nullable SoundCategory sound) {
        if (sound != null) {
            world.playSoundFromEntity(null, this, SoundEvents.ENTITY_HORSE_SADDLE, sound, .5f, 1f);
        }
    }

    static class MooseSwimNavigation extends AmphibiousSwimNavigation {
        MooseSwimNavigation(MooseEntity moose, World world) {
            super(moose, world);
        }

        protected PathNodeNavigator createPathNodeNavigator(int range) {
            nodeMaker = new MooseSwimPathNodeMaker(true);
            return new PathNodeNavigator(nodeMaker, range);
        }
    }

    static class MooseSwimPathNodeMaker extends AmphibiousPathNodeMaker {
        private final BlockPos.Mutable pos = new BlockPos.Mutable();

        public MooseSwimPathNodeMaker(boolean bl) {
            super(bl);
        }

        public PathNode getStart() {
            return !entity.isTouchingWater() ? super.getStart() : getStart(new BlockPos(MathHelper.floor(entity.getBoundingBox().minX), MathHelper.floor(entity.getBoundingBox().minY), MathHelper.floor(entity.getBoundingBox().minZ)));
        }

        public PathNodeType getDefaultNodeType(BlockView world, int x, int y, int z) {
            pos.set(x, y - 1, z);
            BlockState blockState = world.getBlockState(pos);
            return blockState.isIn(BlockTags.FROG_PREFER_JUMP_TO) ? PathNodeType.OPEN : super.getDefaultNodeType(world, x, y, z);
        }
    }
}
