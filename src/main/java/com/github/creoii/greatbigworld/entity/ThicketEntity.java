package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.item.WoodenMaskItem;
import com.github.creoii.greatbigworld.main.registry.EnchantmentRegistry;
import com.github.creoii.greatbigworld.main.util.Tags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class ThicketEntity extends HostileEntity implements RangedAttackMob {
    private static final TrackedData<Boolean> FROZEN = DataTracker.registerData(ThicketEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private final BowAttackGoal<ThicketEntity> bowAttackGoal = new BowAttackGoal<>(this, .8d, 20, 18f);
    private final MeleeAttackGoal meleeAttackGoal = new MeleeAttackGoal(this, 1d, false) {
        public void stop() {
            super.stop();
            ThicketEntity.this.setAttacking(false);
        }

        public void start() {
            super.start();
            ThicketEntity.this.setAttacking(true);
        }
    };

    public ThicketEntity(EntityType<? extends ThicketEntity> entityType, World world) {
        super(entityType, world);
        updateAttackType();
    }

    @Override
    protected void initDataTracker() {
        dataTracker.startTracking(FROZEN, true);
        super.initDataTracker();
    }

    protected void initGoals() {
        goalSelector.add(2, new WanderAroundFarGoal(this, .8d, isThicketFrozen() ? 0f : .001f));
        goalSelector.add(3, new LookAtEntityGoal(this, LivingEntity.class, 8f, isThicketFrozen() ? 0f : .02f));
        goalSelector.add(3, new LookAroundGoal(this));
        targetSelector.add(1, new RevengeGoal(this));
        targetSelector.add(2, new ActiveTargetGoal<>(this, LivingEntity.class, isThicketFrozen() ? 0 : 10, true, false, livingEntity -> {
            return livingEntity.isPartOfGame() && !livingEntity.getType().isIn(Tags.EntityTypeTags.THICKET_IGNORES);
        }));
    }

    public static DefaultAttributeContainer.Builder createThicketAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .2d)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20d)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4d)
                .add(EntityAttributes.GENERIC_ARMOR, 4d)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1d);
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(SoundEvents.ENTITY_STRAY_STEP, .15f, 1f);
    }

    public boolean isThicketFrozen() {
        return dataTracker.get(FROZEN);
    }

    public void setThicketFrozen(boolean frozen) {
        dataTracker.set(FROZEN, frozen);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("ThicketFrozen", isThicketFrozen());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        setThicketFrozen(nbt.getBoolean("ThicketFrozen"));
        updateAttackType();
    }

    @Override
    public boolean canTarget(LivingEntity target) {
        return !isThicketFrozen() && super.canTarget(target);
    }

    @Override
    public boolean canTarget(EntityType<?> type) {
        return !isThicketFrozen() && super.canTarget(type);
    }

    @Override
    protected boolean isImmobile() {
        return isThicketFrozen() || super.isImmobile();
    }

    @Override
    public boolean isSilent() {
        return isThicketFrozen() || super.isSilent();
    }

    @Override
    public boolean isAttacking() {
        return isThicketFrozen() || super.isAttacking();
    }

    @Override
    public void tick() {
        super.tick();
        if (isThicketFrozen() && (world.getLightLevel(LightType.BLOCK, getBlockPos()) > 1 || getTarget() != null)) {
            setThicketFrozen(false);
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (isThicketFrozen()) {
            setThicketFrozen(false);
        }
        if (source.getSource() instanceof LivingEntity livingEntity) {
            ItemStack stack = livingEntity.getStackInHand(Hand.MAIN_HAND);
            if (!(stack.getItem() instanceof PickaxeItem)) {
                amount *= .9f;
            } else amount *= 1.1f;
        }
        return super.damage(source, amount);
    }

    @Override
    public boolean isPushable() {
        return !isThicketFrozen();
    }

    public void tickMovement() {
        if (isThicketFrozen()) return;
        super.tickMovement();
    }

    @Override
    public float getMovementSpeed() {
        return isThicketFrozen() ? 0f : super.getMovementSpeed();
    }

    public void tickRiding() {
        super.tickRiding();
        if (getVehicle() instanceof PathAwareEntity pathAwareEntity) {
            bodyYaw = pathAwareEntity.bodyYaw;
        }
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        entityData = super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
        Random random = world.getRandom();
        if (spawnReason != SpawnReason.SPAWNER) {
            float yaw = (float)random.nextInt(361) + 1f;
            setYaw(yaw);
            setHeadYaw(yaw);
            if (random.nextInt(4) == 0)
                equipStack(EquipmentSlot.HEAD, new ItemStack(WoodenMaskItem.getRandomMask()));
        } else setThicketFrozen(false);
        updateEnchantments(random, difficulty);
        updateAttackType();
        setCanPickUpLoot(random.nextFloat() < .55f * difficulty.getClampedLocalDifficulty());
        if (getEquippedStack(EquipmentSlot.HEAD).isEmpty()) {
            LocalDate localDate = LocalDate.now();
            int day = localDate.get(ChronoField.DAY_OF_MONTH);
            int month = localDate.get(ChronoField.MONTH_OF_YEAR);
            if (month == 10 && day == 31 && random.nextFloat() < .25f) {
                equipStack(EquipmentSlot.HEAD, new ItemStack(random.nextFloat() < .1f ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
                armorDropChances[EquipmentSlot.HEAD.getEntitySlotId()] = 0f;
            }
        }
        return entityData;
    }

    public void updateAttackType() {
        if (world != null && !world.isClient) {
            goalSelector.remove(meleeAttackGoal);
            goalSelector.remove(bowAttackGoal);
            Hand hand = ProjectileUtil.getHandPossiblyHolding(this, Items.BOW);
            if (hand != null) {
                ItemStack itemStack = getStackInHand(hand);
                if (itemStack.isOf(Items.BOW)) {
                    int i = 18;
                    if (world.getDifficulty() != Difficulty.HARD) {
                        i = 36;
                    }
                    bowAttackGoal.setAttackInterval(i);
                    goalSelector.add(1, bowAttackGoal);
                } else {
                    goalSelector.add(1, meleeAttackGoal);
                }
            }
        }
    }

    public void attack(LivingEntity target, float pullProgress) {
        PersistentProjectileEntity persistentProjectileEntity = createArrowProjectile(Items.ARROW.getDefaultStack(), pullProgress);
        int level = EnchantmentHelper.getEquipmentLevel(EnchantmentRegistry.POISON_GLAZE, this);
        if (level > 0 && persistentProjectileEntity instanceof ArrowEntity arrowEntity) {
            arrowEntity.addEffect(new StatusEffectInstance(StatusEffects.POISON, level * 250, level));
        }
        double d = target.getX() - getX();
        double e = target.getBodyY(.3333333333333333d) - persistentProjectileEntity.getY();
        double f = target.getZ() - getZ();
        double g = Math.sqrt(d * d + f * f);
        persistentProjectileEntity.setVelocity(d, e + g * .20000000298023224d, f, 1.6f, (float) (14 - world.getDifficulty().getId() * 4));
        playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1f, 1f / (getRandom().nextFloat() * .4f + .8f));
        world.spawnEntity(persistentProjectileEntity);
    }

    protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier) {
        return ProjectileUtil.createArrowProjectile(this, arrow, damageModifier);
    }

    public boolean canUseRangedWeapon(RangedWeaponItem weapon) {
        return weapon.getDefaultStack().isIn(Tags.ItemTags.COMMON_BOWS);
    }

    public void equipStack(EquipmentSlot slot, ItemStack stack) {
        super.equipStack(slot, stack);
        if (!world.isClient) {
            updateAttackType();
        }
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 1.9f;
    }

    public double getHeightOffset() {
        return -.6d;
    }

    @Override
    public boolean isFireImmune() {
        return true;
    }
}
