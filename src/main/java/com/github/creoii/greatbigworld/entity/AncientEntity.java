package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.main.registry.ItemRegistry;
import com.github.creoii.greatbigworld.main.util.Tags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

/**
 * Don't spawn with bows
 * *Shouldn't always spawn with a Bow
 */
public class AncientEntity extends HostileEntity implements RangedAttackMob {
    private final BowAttackGoal<AncientEntity> bowAttackGoal = new BowAttackGoal<>(this, .8d, 18, 18f);
    private final MeleeAttackGoal meleeAttackGoal = new MeleeAttackGoal(this, 1d, false) {
        public void stop() {
            super.stop();
            AncientEntity.this.setAttacking(false);
        }

        public void start() {
            super.start();
            AncientEntity.this.setAttacking(true);
        }
    };

    public AncientEntity(EntityType<? extends AncientEntity> entityType, World world) {
        super(entityType, world);
        updateAttackType();
    }

    protected void initGoals() {
        goalSelector.add(2, new AvoidSunlightGoal(this));
        goalSelector.add(3, new EscapeSunlightGoal(this, 1d));
        goalSelector.add(3, new FleeEntityGoal<>(this, WolfEntity.class, 6f, .75d, 1d));
        goalSelector.add(5, new WanderAroundFarGoal(this, .8d));
        goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8f));
        goalSelector.add(6, new LookAroundGoal(this));
        targetSelector.add(1, new RevengeGoal(this));
        targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        targetSelector.add(3, new ActiveTargetGoal<>(this, IronGolemEntity.class, true));
        targetSelector.add(3, new ActiveTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_TURTLE_ON_LAND_FILTER));
    }

    public static DefaultAttributeContainer.Builder createAbstractSkeletonAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .15d)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 16d)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4d)
                .add(EntityAttributes.GENERIC_ARMOR, 8d)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1d);
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(SoundEvents.ENTITY_STRAY_STEP, .15f, 1f);
    }

    public EntityGroup getGroup() {
        return EntityGroup.UNDEAD;
    }

    public void tickMovement() {
        boolean bl = isAffectedByDaylight();
        if (bl) {
            ItemStack itemStack = getEquippedStack(EquipmentSlot.HEAD);
            if (!itemStack.isEmpty()) {
                if (itemStack.isDamageable()) {
                    itemStack.setDamage(itemStack.getDamage() + random.nextInt(2));
                    if (itemStack.getDamage() >= itemStack.getMaxDamage()) {
                        sendEquipmentBreakStatus(EquipmentSlot.HEAD);
                        equipStack(EquipmentSlot.HEAD, ItemStack.EMPTY);
                    }
                }
                bl = false;
            }
            if (bl) {
                setOnFireFor(8);
            }
        }
        super.tickMovement();
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
        if (random.nextBoolean()) equipStack(EquipmentSlot.MAINHAND, new ItemStack(ItemRegistry.STONE_BOW));
        updateEnchantments(random, difficulty);
        updateAttackType();
        setCanPickUpLoot(random.nextFloat() < .55f * difficulty.getClampedLocalDifficulty());
        if (getEquippedStack(EquipmentSlot.HEAD).isEmpty()) {
            LocalDate localDate = LocalDate.now();
            int i = localDate.get(ChronoField.DAY_OF_MONTH);
            int j = localDate.get(ChronoField.MONTH_OF_YEAR);
            if (j == 10 && i == 31 && random.nextFloat() < .25f) {
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
            Hand hand = ProjectileUtil.getHandPossiblyHolding(this, ItemRegistry.STONE_BOW);
            if (hand != null) {
                ItemStack itemStack = getStackInHand(hand);
                if (itemStack.isOf(ItemRegistry.STONE_BOW)) {
                    int i = 18;
                    if (world.getDifficulty() != Difficulty.HARD) {
                        i = 36;
                    }
                    bowAttackGoal.setAttackInterval(i);
                    goalSelector.add(4, bowAttackGoal);
                } else {
                    goalSelector.add(4, meleeAttackGoal);
                }
            }
        }
    }

    public void attack(LivingEntity target, float pullProgress) {
        PersistentProjectileEntity persistentProjectileEntity = createArrowProjectile(ItemRegistry.STONE_ARROW.getDefaultStack(), pullProgress);
        double d = target.getX() - getX();
        double e = target.getBodyY(.3333333333333333d) - persistentProjectileEntity.getY();
        double f = target.getZ() - getZ();
        double g = Math.sqrt(d * d + f * f);
        persistentProjectileEntity.setVelocity(d, e + g * .20000000298023224d, f, 1.6f, (float)(14 - world.getDifficulty().getId() * 4));
        playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1f, 1f / (getRandom().nextFloat() * .4f + .8f));
        world.spawnEntity(persistentProjectileEntity);
    }

    protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier) {
        return ProjectileUtil.createArrowProjectile(this, arrow, damageModifier);
    }

    public boolean canUseRangedWeapon(RangedWeaponItem weapon) {
        return weapon.getDefaultStack().isIn(Tags.ItemTags.COMMON_BOWS);
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        updateAttackType();
    }

    public void equipStack(EquipmentSlot slot, ItemStack stack) {
        super.equipStack(slot, stack);
        if (!world.isClient) {
            updateAttackType();
        }
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 1.74f;
    }

    public double getHeightOffset() {
        return -.6d;
    }
}
