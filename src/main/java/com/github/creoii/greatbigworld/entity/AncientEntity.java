package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

public class AncientEntity extends HostileEntity {
    public AncientEntity(EntityType<? extends AncientEntity> entityType, World world) {
        super(entityType, world);
    }

    public AncientEntity(World world) {
        super(EntityRegistry.THICKET, world);
    }

    protected void initGoals() {
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
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    public EntityGroup getGroup() {
        return EntityGroup.UNDEAD;
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
}
