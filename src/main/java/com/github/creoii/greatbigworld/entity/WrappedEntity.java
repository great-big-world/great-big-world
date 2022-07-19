package com.github.creoii.greatbigworld.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class WrappedEntity extends HostileEntity {
    private static final TrackedData<Boolean> SHEARED = DataTracker.registerData(WrappedEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int sunlightTimer = 200;

    public WrappedEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(SHEARED, false);
    }

    public static DefaultAttributeContainer.Builder createWrappedAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 24.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.13d).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5d);
    }

    public void tickMovement() {
        if (world.isClient) {
            if (world.isSkyVisible(getBlockPos())) sunlightTimer = Math.max(0, sunlightTimer - 1);
            else sunlightTimer = 200;

            if (sunlightTimer == 0) {
                world.setBlockState(getBlockPos(), Blocks.SANDSTONE.getDefaultState(), 3);
                remove(RemovalReason.DISCARDED);
            }
        }

        super.tickMovement();
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack held = player.getStackInHand(hand);
        if (held.isOf(Items.SHEARS)) {
            if (!world.isClient && isShearable()) {
                setSheared(true);

                for (int j = 0; j < random.nextInt(2) + 1; ++j) {
                    ItemEntity itemEntity = dropItem(Items.PAPER, 1);
                    if (itemEntity != null) {
                        itemEntity.setVelocity(itemEntity.getVelocity().add((random.nextFloat() - random.nextFloat()) * .1f, random.nextFloat() * .05f, (random.nextFloat() - random.nextFloat()) * .1f));
                    }
                }

                emitGameEvent(GameEvent.SHEAR, player);
                held.damage(1, player, player1 -> player1.sendToolBreakStatus(hand));
                return ActionResult.SUCCESS;
            } else return ActionResult.CONSUME;
        } else return super.interactMob(player, hand);
    }

    public boolean isSheared() {
        return dataTracker.get(SHEARED);
    }

    public void setSheared(boolean sheared) {
        dataTracker.set(SHEARED, sheared);
    }

    public boolean isShearable() {
        return isAlive() && !isSheared() && !isBaby();
    }
}
