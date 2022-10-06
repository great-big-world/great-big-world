package com.github.creoii.greatbigworld.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BallistaEntity extends Entity {
    private final SimpleInventory arrows;

    public BallistaEntity(EntityType<?> type, World world) {
        super(type, world);
        arrows = new SimpleInventory(2);
    }

    @Override
    protected void initDataTracker() {
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
    }

    // Used in the renderer to render the left-side quiver
    public boolean hasArrowsInFirstSlot() {
        return !arrows.getStack(0).isEmpty();
    }

    // Used in the renderer to render the right-side quiver
    public boolean hasArrowsInSecondSlot() {
        return !arrows.getStack(1).isEmpty();
    }

    public boolean isCollidable() {
        return true;
    }

    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (player.shouldCancelInteraction()) {
            return ActionResult.PASS;
        } else if (!world.isClient) {
            // Inserting Arrows into the Ballista
            ItemStack held = player.getStackInHand(hand);
            if (held.isIn(ItemTags.ARROWS) && arrows.canInsert(held)) {
                arrows.addStack(held);
                // Taking Arrows from the Ballista
            } else if (held.isEmpty() && player.isSneaking() && !arrows.isEmpty()) {
                if (arrows.getStack(0).isEmpty()) {
                    player.setStackInHand(hand, arrows.removeStack(1));
                } else if (arrows.getStack(1).isEmpty()) {
                    player.setStackInHand(hand, arrows.removeStack(0));
                }
                // Riding the Ballista
            } else return player.startRiding(this) ? ActionResult.CONSUME : ActionResult.PASS;
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.PASS;
        }
    }

    @Override
    public Vec3d getVelocity() {
        return Vec3d.ZERO;
    }

    @Override
    public void tick() {
        if (!isRemoved()) {
            if (isAlive()) {
                Entity primary = getPrimaryPassenger();
                if (hasPassengers() && primary instanceof LivingEntity living) {
                    setYaw(living.getYaw());
                    prevYaw = getYaw();
                    setPitch(living.getPitch());
                    setRotation(getYaw(), getPitch());
                }
            }
        }
        super.tick();
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }
}
