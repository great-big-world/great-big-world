package com.github.creoii.greatbigworld.main.mixin.entity;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.GBWEnchantments;
import com.github.creoii.greatbigworld.main.util.AuraEffect;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow protected abstract void onStatusEffectApplied(StatusEffectInstance effect, @Nullable Entity source);
    @Shadow @Final private Map<StatusEffect, StatusEffectInstance> activeStatusEffects;
    @Shadow public abstract float getHealth();

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"), cancellable = true)
    private void great_big_world_applyDiluting(StatusEffectInstance effect, Entity source, CallbackInfoReturnable<Boolean> cir) {
        int i = EnchantmentHelper.getEquipmentLevel(GBWEnchantments.DILUTING, (LivingEntity) (Object) this);
        if (i > 0) {
            StatusEffectInstance statusEffect = new StatusEffectInstance(effect.getEffectType(), (int)(effect.getDuration() / (i + (i * GreatBigWorld.CONFIG.dilutingModifier))), effect.getAmplifier(), effect.isAmbient(), effect.shouldShowParticles(), effect.shouldShowIcon(), null, effect.getFactorCalculationData());
            activeStatusEffects.put(statusEffect.getEffectType(), statusEffect);
            onStatusEffectApplied(statusEffect, source);
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "tickMovement", at = @At("TAIL"))
    private void great_big_world_applyAuraPotions(CallbackInfo ci) {
        if (getHealth() > 0f) {
            for (Entity entity : world.getOtherEntities(this, getBoundingBox().expand(.5d, .25d, .5d))) {
                if (entity.isRemoved() || !entity.isLiving()) continue;
                activeStatusEffects.forEach((effect, instance) -> {
                    StatusEffectInstance transferred = AuraEffect.transferAura(instance);
                    if (((AuraEffect) instance).isAura()) {
                        ((LivingEntity) entity).addStatusEffect(transferred, this);
                    }
                });
            }
        }
    }
}
