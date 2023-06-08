package com.github.creoii.greatbigworld.main.mixin.entity;

import com.github.creoii.greatbigworld.entity.MooseEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PillagerEntity.class)
public abstract class PillagerEntityMixin extends IllagerEntity implements CrossbowUser, InventoryOwner {
    protected PillagerEntityMixin(EntityType<? extends IllagerEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("TAIL"))
    private void great_big_world_injectMooseTarget(CallbackInfo ci) {
        if (GreatBigWorld.CONFIG.pillagersAttackMoose.booleanValue()) {
            goalSelector.add(10, new LookAtEntityGoal(this, MooseEntity.class, 15f, .75f));
            targetSelector.add(3, new ActiveTargetGoal<>(this, MooseEntity.class, true));
        }
    }
}
