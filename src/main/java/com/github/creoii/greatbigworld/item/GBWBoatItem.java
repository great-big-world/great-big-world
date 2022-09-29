package com.github.creoii.greatbigworld.item;

import com.github.creoii.greatbigworld.entity.GBWBoatEntity;
import com.github.creoii.greatbigworld.entity.GBWChestBoatEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;

public class GBWBoatItem extends BoatItem {
    private final GBWBoatEntity.GBWType type;

    public GBWBoatItem(boolean chest, GBWBoatEntity.GBWType type, Settings settings) {
        super(chest, BoatEntity.Type.OAK, settings);
        this.type = type;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        HitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.ANY);
        if (hitResult.getType() == net.minecraft.util.hit.HitResult.Type.MISS) {
            return TypedActionResult.pass(itemStack);
        } else {
            Vec3d vec3d = user.getRotationVec(1f);
            List<Entity> list = world.getOtherEntities(user, user.getBoundingBox().stretch(vec3d.multiply(5d)).expand(1d), EntityPredicates.EXCEPT_SPECTATOR.and(Entity::canHit));
            if (!list.isEmpty()) {
                Vec3d vec3d2 = user.getEyePos();

                for (Entity entity : list) {
                    Box box = entity.getBoundingBox().expand(entity.getTargetingMargin());
                    if (box.contains(vec3d2)) {
                        return TypedActionResult.pass(itemStack);
                    }
                }
            }

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BoatEntity boatEntity;
                if (chest) {
                    boatEntity = createChestBoat(world, hitResult);
                    ((GBWChestBoatEntity) boatEntity).setGBWBoatType(type);
                } else {
                    boatEntity = createBoat(world, hitResult);
                    ((GBWBoatEntity) boatEntity).setGBWBoatType(type);
                }
                boatEntity.setYaw(user.getYaw());
                if (!world.isSpaceEmpty(boatEntity, boatEntity.getBoundingBox())) {
                    return TypedActionResult.fail(itemStack);
                } else {
                    if (!world.isClient) {
                        world.spawnEntity(boatEntity);
                        world.emitGameEvent(user, GameEvent.ENTITY_PLACE, hitResult.getPos());
                        if (!user.getAbilities().creativeMode) {
                            itemStack.decrement(1);
                        }
                    }

                    user.incrementStat(Stats.USED.getOrCreateStat(this));
                    return TypedActionResult.success(itemStack, world.isClient());
                }
            } else {
                return TypedActionResult.pass(itemStack);
            }
        }
    }

    private GBWBoatEntity createBoat(World world, HitResult hitResult) {
        return new GBWBoatEntity(world, hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
    }

    private GBWChestBoatEntity createChestBoat(World world, HitResult hitResult) {
        return new GBWChestBoatEntity(world, hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
    }
}
