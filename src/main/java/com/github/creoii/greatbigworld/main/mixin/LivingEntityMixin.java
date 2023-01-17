package com.github.creoii.greatbigworld.main.mixin;

import com.github.creoii.greatbigworld.main.registry.EnchantmentRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow protected abstract void onStatusEffectApplied(StatusEffectInstance effect, @Nullable Entity source);

    @Shadow @Final private Map<StatusEffect, StatusEffectInstance> activeStatusEffects;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"), cancellable = true)
    private void great_big_world_applyDiluting(StatusEffectInstance effect, Entity source, CallbackInfoReturnable<Boolean> cir) {
        int i = EnchantmentHelper.getEquipmentLevel(EnchantmentRegistry.DILUTING, (LivingEntity) (Object) this);
        if (i > 0) {
            StatusEffectInstance statusEffect = new StatusEffectInstance(effect.getEffectType(), (int)(effect.getDuration() / (i + (i * .5f))), effect.getAmplifier(), effect.isAmbient(), effect.shouldShowParticles(), effect.shouldShowIcon(), null, effect.getFactorCalculationData());
            activeStatusEffects.put(statusEffect.getEffectType(), statusEffect);
            onStatusEffectApplied(statusEffect, source);
            cir.setReturnValue(true);
        }
    }
}
