package com.github.creoii.greatbigworld.main.mixin.entity;

import com.github.creoii.greatbigworld.main.registry.EnchantmentRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AbstractSkeletonEntity.class)
public abstract class AbstractSkeletonEntityMixin extends HostileEntity implements RangedAttackMob {
    protected AbstractSkeletonEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void great_big_world_applyPoisonGlaze(LivingEntity target, float pullProgress, CallbackInfo ci, ItemStack itemStack, PersistentProjectileEntity persistentProjectileEntity, double d, double e, double f, double g) {
        int level = EnchantmentHelper.getEquipmentLevel(EnchantmentRegistry.POISON_GLAZE, this);
        if (level > 0 && persistentProjectileEntity instanceof ArrowEntity arrowEntity) {
            arrowEntity.addEffect(new StatusEffectInstance(StatusEffects.POISON, level * 250, level));
        }
        world.spawnEntity(persistentProjectileEntity);
        ci.cancel();
    }
}
