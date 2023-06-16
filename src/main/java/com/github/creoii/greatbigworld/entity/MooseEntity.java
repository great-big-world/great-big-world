package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.integration.ModMenuIntegration;
import com.github.creoii.greatbigworld.main.registry.GBWBlocks;
import com.github.creoii.greatbigworld.main.registry.GBWEntityTypes;
import com.github.creoii.greatbigworld.main.registry.GBWGameEvents;
import com.github.creoii.greatbigworld.main.registry.GBWSoundEvents;
import com.github.creoii.greatbigworld.main.util.Tags;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.AmphibiousSwimNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.EntityView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

public class MooseEntity extends AbstractHorseEntity implements Angerable, JumpingMount, Saddleable {
    private static final TrackedData<Boolean> RIGHT_ANTLER = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> LEFT_ANTLER = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> RAMMING = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> SHED_TIME = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> REGROW_TIME = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final int SHED_REGROW_TIME_MOD = 9600;
    public final AnimationState walkingAnimationState = new AnimationState();
    public final AnimationState swimmingAnimationState = new AnimationState();
    public final AnimationState idlingInWaterAnimationState = new AnimationState();
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    private int ramCooldown = 0;
    private int headPitch;
    @Nullable private UUID angryAt;

    public MooseEntity(EntityType<? extends MooseEntity> entityType, World world) {
        super(entityType, world);
        getNavigation().setCanSwim(true);
        setPathfindingPenalty(PathNodeType.POWDER_SNOW, -1f);
        setPathfindingPenalty(PathNodeType.DANGER_POWDER_SNOW, -1f);
        setPathfindingPenalty(PathNodeType.WATER, 2f);
        lookControl = new LookControl(this);
        moveControl = new AquaticMoveControl(this, 85, 10, 2f, 1f, true);
    }

    public static DefaultAttributeContainer.Builder createMooseAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 50d)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .22499999403953552d)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, .6000000238418579d)
                .add(EntityAttributes.GENERIC_ARMOR, 5d)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1d)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8d)
                .add(EntityAttributes.HORSE_JUMP_STRENGTH)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 25d);
    }

    @Override
    protected void initAttributes(Random random) {
        getAttributeInstance(EntityAttributes.HORSE_JUMP_STRENGTH).setBaseValue(getChildRamStrengthBonus(random));
    }

    protected float getChildRamStrengthBonus(Random random) {
        return 10f + (float)random.nextInt(3) + (float)random.nextInt(3);
    }

    public boolean isPushedByFluids() {
        return false;
    }

    @Override
    public float getStepHeight() {
        return 1.5f;
    }

    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(RIGHT_ANTLER, true);
        dataTracker.startTracking(LEFT_ANTLER, true);
        dataTracker.startTracking(RAMMING, false);
        int value = GreatBigWorld.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.shedAntlerBaseRegrowTime.intValue() : 4800;
        dataTracker.startTracking(SHED_TIME, value + random.nextInt(SHED_REGROW_TIME_MOD));
        dataTracker.startTracking(REGROW_TIME, 0);
        dataTracker.startTracking(ANGER_TIME, 0);
    }

    protected void initGoals() {
        goalSelector.add(0, new MooseEscapeDangerGoal(this));
        goalSelector.add(1, new MeleeAttackGoal(this, 1.4d, false));
        goalSelector.add(2, new AnimalMateGoal(this, 1d));
        goalSelector.add(3, new TemptGoal(this, 1.25d, Ingredient.fromTag(Tags.ItemTags.MOOSE_FOOD), true));
        goalSelector.add(3, new EatGrassGoal(this));
        goalSelector.add(4, new FollowParentGoal(this, 1d));
        goalSelector.add(5, new WanderAroundFarGoal(this, 1d));
        goalSelector.add(5, new SwimAroundGoal(this, 1.25d, 120));
        goalSelector.add(6, new LookAtEntityGoal(this, LivingEntity.class, 6f));
        goalSelector.add(7, new LookAroundGoal(this));
        targetSelector.add(1, new ProtectBabiesGoal());
        targetSelector.add(2, new MooseBondOrAngerAtPlayerGoal(this));
        targetSelector.add(3, new ActiveTargetGoal<>(this, LivingEntity.class, 10, true, false, this::shouldAngerAt));
        targetSelector.add(4, new UniversalAngerGoal<>(this, true));
    }

    public void tickMovement() {
        if (isAlive() && isRamming()) {
            ram();
        }
        if (ramCooldown > 0 || isInLove()) {
            headPitch += random.nextInt(5) + 4;
        } else {
            headPitch -= 3;
        }
        headPitch = MathHelper.clamp(headPitch, 0, 40);
        super.tickMovement();
        if (!getWorld().isClient) {
            tickAngerLogic((ServerWorld) getWorld(), true);
        }
        if (hasPlayerRider() && isTouchingWater()) {
            if (getWorld().isWater(getBlockPos().down())) {
                move(MovementType.PLAYER, new Vec3d(0d, .1d, 0d));
            }
        }
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return GBWSoundEvents.ENTITY_MOOSE_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return GBWSoundEvents.ENTITY_MOOSE_DEATH;
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
        dataTracker.set(REGROW_TIME, getRegrowTime() - 1);
    }

    public void setShedTime(int time) {
        dataTracker.set(SHED_TIME, time);
    }

    public void setRegrowTime(int time) {
        dataTracker.set(REGROW_TIME, time);
    }

    public float getHeadPitch() {
        return (float) headPitch / 20f * 30f * .017453292f;
    }

    public boolean isOwner(LivingEntity livingEntity) {
        return livingEntity.getUuid().equals(getOwnerUuid());
    }

    public void shedAntlers() {
        int value = GreatBigWorld.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.shedAntlerBaseRegrowTime.intValue() : 4800;
        setRegrowTime(value + random.nextInt(SHED_REGROW_TIME_MOD));
        setLeftAntler(false);
        setRightAntler(false);
        if (!getWorld().isClient) {
            dropItem(GBWBlocks.ANTLER);
            dropItem(GBWBlocks.ANTLER);
            playSound(SoundEvents.ENTITY_GOAT_HORN_BREAK, getSoundVolume(), getSoundPitch());
            emitGameEvent(GBWGameEvents.SHED_ANTLERS);
        }
    }

    public void regrowAntlers() {
        int value = GreatBigWorld.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.shedAntlerBaseRegrowTime.intValue() : 4800;
        setShedTime(value + random.nextInt(SHED_REGROW_TIME_MOD));
        setLeftAntler(true);
        setRightAntler(true);
        if (!getWorld().isClient) {
            playSound(SoundEvents.ENTITY_GOAT_HORN_BREAK, getSoundVolume(), getSoundPitch());
            emitGameEvent(GBWGameEvents.SHED_ANTLERS);
        }
    }

    @Nullable @Override
    public MooseEntity createChild(ServerWorld world, PassiveEntity entity) {
        MooseEntity mooseEntity = GBWEntityTypes.MOOSE.create(world);
        if (mooseEntity != null) {
            mooseEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(25f);
            mooseEntity.setLeftAntler(false);
            mooseEntity.setRightAntler(false);
        }
        return mooseEntity;
    }

    @Override
    protected void onGrowUp() {
        if (isBaby()) {
            getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(25f);
            getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(.3000000119209290d);
            setLeftAntler(false);
            setRightAntler(false);
        } else {
            getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(50f);
            getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(.6000000238418579d);
            setLeftAntler(true);
            setRightAntler(true);
        }
        super.onGrowUp();
    }

    @Override
    public void onEatingGrass() {
        ramCooldown = 30;
        super.onEatingGrass();
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(Tags.ItemTags.MOOSE_FOOD_LIKES) || stack.isIn(Tags.ItemTags.MOOSE_FOOD_LOVES);
    }

    public boolean tryAttack(Entity target) {
        if (hasLeftAntler() || hasRightAntler()) {
            ramCooldown = 30;
            setRamming(true);
            return super.tryAttack(target);
        }
        return false;
    }

    protected void knockback(LivingEntity target) {
        if (!isBaby() && (hasLeftAntler() || hasRightAntler())) {
            ramKnockback(target, getJumpStrength());
        }
    }

    @Override
    public void handleStatus(byte status) {
        if (status == 7) {
            showEmoteParticle(true);
        } else if (status == 6) {
            showEmoteParticle(false);
        } else {
            super.handleStatus(status);
        }
    }

    protected void showEmoteParticle(boolean positive) {
        ParticleEffect particleEffect = ParticleTypes.HEART;
        if (!positive) {
            particleEffect = ParticleTypes.SMOKE;
        }

        for (int i = 0; i < 7; ++i) {
            double d = random.nextGaussian() * .02d;
            double e = random.nextGaussian() * .02d;
            double f = random.nextGaussian() * .02d;
            getWorld().addParticle(particleEffect, getParticleX(1d), getRandomBodyY() + .5d, getParticleZ(1d), d, e, f);
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
        nbt.putInt("ShedTime", getShedTime());
        nbt.putInt("RegrowTime", getRegrowTime());
        writeAngerToNbt(nbt);
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        setLeftAntler(nbt.getBoolean("HasLeftAntler"));
        setRightAntler(nbt.getBoolean("HasRightAntler"));
        setShedTime(nbt.getInt("ShedTime"));
        setRegrowTime(nbt.getInt("RegrowTime"));
        readAngerFromNbt(getWorld(), nbt);
    }

    @Override
    public double getMountedHeightOffset() {
        return 1.75d;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        Entity entity = source.getAttacker();
        if (entity != null) {
            setAngryAt(entity.getUuid());
            if (entity instanceof LivingEntity livingEntity) setTarget(livingEntity);
            if (getOwnerUuid() != null && getOwnerUuid().equals(entity.getUuid())) {
                setOwnerUuid(null);
                setTame(false);
                if (isSaddled()) {
                    dropInventory();
                    items.setStack(0, ItemStack.EMPTY);
                    updateSaddle();
                }
                if (getFirstPassenger() != null) {
                    getFirstPassenger().dismountVehicle();
                }
            }
        }
        return super.damage(source, amount);
    }

    @Override
    public void tick() {
        updateAnimations();
        super.tick();
        if (isRamming() && ramCooldown < 30) {
            setRamming(false);
        }
        if (ramCooldown > 0) {
            --ramCooldown;
            if (ramCooldown == 0) {
                getWorld().playSound(null, getBlockPos(), GBWSoundEvents.ENTITY_MOOSE_WARNING, SoundCategory.PLAYERS, 1f, 1f);
            }
        }

        int value = GreatBigWorld.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.shedAntlerBaseRegrowTime.intValue() : 4800;
        if (isAlive() && !isBaby() && value != -1) {
            if (!hasRightAntler() || !hasLeftAntler()) {
                if (getRegrowTime() > 0) {
                    decrementRegrowTime();
                } else regrowAntlers();
            } else {
                if (getShedTime() > 0) {
                    decrementShedTime();
                } else shedAntlers();
            }
        }
    }

    private void updateAnimations() {
        if (getWorld().isClient) {
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
    }

    public boolean canBreatheInWater() {
        return true;
    }

    @Override
    public boolean canBreedWith(AnimalEntity other) {
        if (other == this) {
            return false;
        } else if (other.getClass() != getClass()) {
            return false;
        } else {
            return isInLove() && other.isInLove();
        }
    }

    private boolean shouldWalk() {
        return isOnGround() && getVelocity().horizontalLengthSquared() > 1e-6d && !isInsideWaterOrBubbleColumn();
    }

    private boolean shouldSwim() {
        return getVelocity().horizontalLengthSquared() > 1e-6d && isInsideWaterOrBubbleColumn();
    }

    @Override
    public void updateSwimming() {
        if (isSwimming()) {
            setSwimming(isSprinting() && isTouchingWater());
        } else {
            setSwimming(isSprinting() && isSubmergedInWater() && getWorld().getFluidState(getBlockPos()).isIn(FluidTags.WATER));
        }
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack held = player.getStackInHand(hand);
        if (!isBaby()) {
            if (isBreedingItem(held) && !hasAngerTime() && !isInLove()) {
                return interactHorse(player, held);
            }
            if (hasAngerTime()) {
                showEmoteParticle(false);
                if (!getWorld().isClient) {
                    playSound(GBWSoundEvents.ENTITY_MOOSE_WARNING, getSoundVolume(), getSoundPitch());
                }
                return ActionResult.success(getWorld().isClient);
            }
        }
        return super.interactMob(player, hand);
    }

    @Override
    public ActionResult interactHorse(PlayerEntity player, ItemStack stack) {
        boolean bl = false;
        float f = 0f;
        int i = 0;
        int j = 0;
        if (stack.isIn(Tags.ItemTags.MOOSE_FOOD_LIKES)) {
            f = 2f;
            i = 30;
            j = 2;
        } else if (stack.isIn(Tags.ItemTags.MOOSE_FOOD_LOVES)) {
            f = 4f;
            i = 60;
            j = 5;
            if (!getWorld().isClient) {
                if (hasAngerTime()) {
                    if (random.nextInt(12) == 0) {
                        setTame(true);
                        forgive(player);
                        getWorld().sendEntityStatus(this, (byte) 7);
                    } else {
                        getWorld().sendEntityStatus(this, (byte)6);
                    }
                } else if (isTame() && getBreedingAge() == 0 && !isInLove()) {
                    lovePlayer(player);
                }
                bl = true;
            }
        }

        if (getHealth() < getMaxHealth() && f > 0f) {
            heal(f);
            bl = true;
        }

        if (isBaby() && i > 0) {
            getWorld().addParticle(ParticleTypes.HAPPY_VILLAGER, getParticleX(1d), getRandomBodyY() + .5d, getParticleZ(1d), 0d, 0d, 0d);
            if (!getWorld().isClient) {
                growUp(i);
            }

            bl = true;
        }

        if (j > 0 && (bl || !isTame()) && getTemper() < getMaxTemper()) {
            bl = true;
            if (!getWorld().isClient) {
                addTemper(j * 2);
            }
        }

        if (bl) {
            setEating();
            if (!isSilent()) {
                SoundEvent soundEvent = getEatSound();
                if (soundEvent != null) {
                    getWorld().playSound(null, getX(), getY(), getZ(), soundEvent, getSoundCategory(), 1f, 1f + (random.nextFloat() - random.nextFloat()) * .2f);
                }
            }
            emitGameEvent(GameEvent.EAT);
        }

        if (!player.getAbilities().creativeMode) {
            stack.decrement(1);
        }

        if (getWorld().isClient) {
            return ActionResult.CONSUME;
        } else return bl ? ActionResult.SUCCESS : ActionResult.PASS;
    }

    protected EntityNavigation createNavigation(World world) {
        return new AmphibiousSwimNavigation(this, world);
    }

    public void travel(Vec3d movementInput) {
        if (canMoveVoluntarily() && isSubmergedInWater()) {
            updateVelocity(.01f, movementInput);
            move(MovementType.SELF, getVelocity());
            setVelocity(getVelocity().multiply(1d, .75d, 1d));
        } else {
            super.travel(movementInput);
        }
    }

    @Override
    public void saddle(@Nullable SoundCategory sound) {
        if (sound != null) {
            getWorld().playSoundFromEntity(null, this, SoundEvents.ENTITY_HORSE_SADDLE, sound, .5f, 1f);
        }
    }

    public int getAngerTime() {
        return dataTracker.get(ANGER_TIME);
    }

    public void setAngerTime(int angerTime) {
        dataTracker.set(ANGER_TIME, angerTime);
    }

    public void chooseRandomAngerTime() {
        setAngerTime(ANGER_TIME_RANGE.get(random));
    }

    @Nullable
    public UUID getAngryAt() {
        return angryAt;
    }

    public void setAngryAt(@Nullable UUID angryAt) {
        this.angryAt = angryAt;
        if (!getWorld().isClient) {
            playSound(GBWSoundEvents.ENTITY_MOOSE_WARNING, 1f, getSoundPitch());
        }
        updateAnger();
        resetLoveTicks();
        chooseRandomAngerTime();
    }

    public boolean isAngryAt(Entity entity) {
        return entity.getUuid().equals(getAngryAt());
    }

    @Override
    public boolean shouldAngerAt(LivingEntity entity) {
        if (entity instanceof MooseEntity moose && moose.getOwnerUuid() != null && entity.getType() == getType() && getOwnerUuid() != null) {
            return !moose.getOwnerUuid().equals(getOwnerUuid());
        }
        return isAngryAt(entity) && squaredDistanceTo(entity) <= 3d && entity.getType() != getType();
    }

    @Override
    public boolean isUniversallyAngry(World world) {
        return Angerable.super.isUniversallyAngry(world) && !isTame();
    }

    public boolean canBeLeashedBy(PlayerEntity player) {
        return !hasAngerTime() && super.canBeLeashedBy(player);
    }

    @Override
    protected Vec3d getLeashOffset() {
        return new Vec3d(0d, getStandingEyeHeight() - .25d, getWidth() * .4d);
    }

    public boolean canJump(PlayerEntity player) {
        return getFirstPassenger() == player && super.canJump() && (hasRightAntler() || hasLeftAntler());
    }

    public void setJumpStrength(int strength) {
        if (isSaddled() && ramCooldown <= 0 && isOnGround()) {
            super.setJumpStrength(strength);
        }
    }

    private void ram() {
        List<Entity> entities = getWorld().getOtherEntities(this, getBoundingBox().offset(getRotationVec(1f).multiply(1.5d)), livingEntity -> {
            return livingEntity.getVehicle() != this && livingEntity.isAlive();
        });
        if (!entities.isEmpty()) {
            setAttacking(true);
            for (Entity entity : entities) {
                if ((entity.getType() == GBWEntityTypes.MOOSE && getOwnerUuid() != null) && entity instanceof LivingEntity livingEntity) {
                    tryAttack(livingEntity);
                }
            }
            setRamming(false);
            setAttacking(false);
        }
    }

    public void ramKnockback(LivingEntity target, double ramStrength) {
        double e = target.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE);
        double strength = ramStrength - e;
        if (strength > 0d) {
            double g = target.getX() - getX();
            double h = target.getZ() - getZ();
            float i = (float)(getWorld().random.nextInt(11) - 20);
            double j = strength * (double)(getWorld().random.nextFloat() * .5f + .2f);
            Vec3d vec3d = (new Vec3d(g, 0d, h)).normalize().multiply(j).rotateY(i);
            double k = strength * (double)getWorld().random.nextFloat() * .5d;
            target.addVelocity(vec3d.x, k, vec3d.z);
            target.velocityModified = true;
        }
    }

    @Override
    protected void jump(float strength, Vec3d movementInput) {
        ramCooldown = 30;
        setRamming(true);
    }

    public boolean isRamming() {
        return dataTracker.get(RAMMING);
    }

    public void setRamming(boolean ramming) {
        dataTracker.set(RAMMING, ramming);
    }

    public void startJumping(int height) {
        setRamming(true);
    }

    public void stopJumping() {
        headPitch -= 3;
    }

    public int getJumpCooldown() {
        return ramCooldown;
    }

    @Override
    public boolean disablesShield() {
        return true;
    }

    @Override
    public EntityView method_48926() {
        return getWorld();
    }

    public class ProtectBabiesGoal extends ActiveTargetGoal<LivingEntity> {
        public ProtectBabiesGoal() {
            super(MooseEntity.this, LivingEntity.class, 20, true, true, livingEntity -> {
                return !livingEntity.isBaby() && livingEntity.getType() != GBWEntityTypes.MOOSE && !MooseEntity.this.isOwner(livingEntity);
            });
        }

        public boolean canStart() {
            if (!MooseEntity.this.isBaby()) {
                if (super.canStart()) {
                    List<MooseEntity> list = MooseEntity.this.getWorld().getNonSpectatingEntities(MooseEntity.class, MooseEntity.this.getBoundingBox().expand(8d, 4d, 8d));
                    for (MooseEntity mooseEntity : list) {
                        if (mooseEntity.isBaby()) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        protected double getFollowRange() {
            return super.getFollowRange() * .5d;
        }
    }

    public static class MooseBondOrAngerAtPlayerGoal extends Goal {
        private final MooseEntity moose;
        private double targetX;
        private double targetY;
        private double targetZ;

        public MooseBondOrAngerAtPlayerGoal(MooseEntity moose) {
            this.moose = moose;
            setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            if (!moose.isTame() && moose.hasPassengers()) {
                Vec3d vec3d = NoPenaltyTargeting.find(moose, 5, 4);
                if (vec3d == null) {
                    return false;
                } else {
                    this.targetX = vec3d.x;
                    this.targetY = vec3d.y;
                    this.targetZ = vec3d.z;
                    return true;
                }
            } else {
                return false;
            }
        }

        public void start() {
            moose.getNavigation().startMovingTo(targetX, targetY, targetZ, 1.2d);
        }

        public boolean shouldContinue() {
            return !moose.getNavigation().isIdle() && moose.hasPassengers();
        }

        public void tick() {
            if (!moose.hasAngerTime() && moose.getRandom().nextInt(getTickCount(50)) == 0) {
                if (moose.getPassengerList().get(0) instanceof PlayerEntity playerEntity) {
                    int i = moose.getTemper();
                    int j = moose.getMaxTemper();
                    if (j > 0 && moose.getRandom().nextInt(j) < i) {
                        moose.bondWithPlayer(playerEntity);
                        moose.setTame(true);
                        return;
                    } else if (j > 0 && moose.getRandom().nextInt(j - 1) < i + 2) {
                        moose.setTame(false);
                        moose.setAngryAt(playerEntity.getUuid());
                        moose.setTarget(playerEntity);
                    }
                    moose.addTemper(5);
                }
                moose.removeAllPassengers();
                moose.getWorld().sendEntityStatus(moose, (byte)6);
            }
        }
    }

    public static class MooseEscapeDangerGoal extends EscapeDangerGoal {
        public MooseEscapeDangerGoal(PathAwareEntity mob) {
            super(mob, 1.5d);
        }

        @Override
        protected boolean isInDanger() {
            return mob.shouldEscapePowderSnow() || mob.isOnFire();
        }
    }
}
