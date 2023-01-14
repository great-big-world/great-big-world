package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import com.github.creoii.greatbigworld.main.registry.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class AncientEntity extends HostileEntity {
    public AncientEntity(EntityType<? extends AncientEntity> entityType, World world) {
        super(entityType, world);
    }

    public AncientEntity(World world) {
        super(EntityRegistry.THICKET, world);
    }

    protected void initGoals() {
        goalSelector.add(2, new AvoidSunlightGoal(this));
        goalSelector.add(3, new EscapeSunlightGoal(this, 1d));
        goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8f));
        goalSelector.add(4, new LookAroundGoal(this));
        goalSelector.add(2, new MeleeAttackGoal(this, 1d, false));
        goalSelector.add(6, new MoveThroughVillageGoal(this, 1d, true, 4, () -> false));
        goalSelector.add(7, new WanderAroundFarGoal(this, 1d));
        targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        targetSelector.add(2, new ActiveTargetGoal<>(this, IronGolemEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createAncientAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .12000000417232513d)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20d)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3d)
                .add(EntityAttributes.GENERIC_ARMOR, 4d)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1d);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_ZOMBIE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ZOMBIE_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(getStepSound(), .15f, 1f);
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

    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
        super.initEquipment(random, localDifficulty);
        if (random.nextFloat() < (world.getDifficulty() == Difficulty.HARD ? .05f : .01f)) {
            if (random.nextInt(3) == 0) {
                equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
            } else {
                equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_PICKAXE));
            }
        }
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        entityData = super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
        Random random = world.getRandom();
        if (random.nextInt(8) == 0) {
            equipStack(EquipmentSlot.HEAD, new ItemStack(ItemRegistry.OAK_MASK));
        }
        initEquipment(random, difficulty);
        updateEnchantments(random, difficulty);
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
}
