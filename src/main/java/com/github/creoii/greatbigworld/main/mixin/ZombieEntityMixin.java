package com.github.creoii.greatbigworld.main.mixin;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombieHorseEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieEntity.class)
public class ZombieEntityMixin extends HostileEntity {
    protected ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initialize", at = @At("HEAD"))
    private void great_big_world_zombieCavalry(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, NbtCompound entityNbt, CallbackInfoReturnable<EntityData> cir) {
        if (world.getRandom().nextInt(120) == 0) {
            ZombieHorseEntity zombieHorseEntity = EntityType.ZOMBIE_HORSE.create(this.world);
            zombieHorseEntity.refreshPositionAndAngles(getX(), getY(), getZ(), getYaw(), 0f);
            zombieHorseEntity.initialize(world, difficulty, SpawnReason.JOCKEY, null, null);
            zombieHorseEntity.setOwnerUuid(getUuid());
            startRiding(zombieHorseEntity);
            world.spawnEntity(zombieHorseEntity);
        }
    }
}
