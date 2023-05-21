package com.github.creoii.greatbigworld.main.util;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public interface AuraEffect {
    boolean isAura();

    void setAura(boolean isAura);

    static StatusEffectInstance createAuraStatusEffectInstance(StatusEffect type) {
        StatusEffectInstance instance = new StatusEffectInstance(type, 0, 0);
        ((AuraEffect) instance).setAura(true);
        return instance;
    }

    static StatusEffectInstance createAuraStatusEffectInstance(StatusEffect type, int duration) {
        StatusEffectInstance instance = new StatusEffectInstance(type, duration, 0);
        ((AuraEffect) instance).setAura(true);
        return instance;
    }

    static StatusEffectInstance createAuraStatusEffectInstance(StatusEffect type, int duration, int amplifier) {
        StatusEffectInstance instance = new StatusEffectInstance(type, duration, amplifier);
        ((AuraEffect) instance).setAura(true);
        return instance;
    }

    static StatusEffectInstance createAuraStatusEffectInstance(StatusEffect type, int duration, int amplifier, boolean ambient, boolean visible) {
        StatusEffectInstance instance = new StatusEffectInstance(type, duration, amplifier, ambient, visible);
        ((AuraEffect) instance).setAura(true);
        return instance;
    }

    static StatusEffectInstance createAuraStatusEffectInstance(StatusEffect type, int duration, int amplifier, boolean ambient, boolean showParticles, boolean showIcon) {
        StatusEffectInstance instance = new StatusEffectInstance(type, duration, amplifier, ambient, showParticles, showIcon);
        ((AuraEffect) instance).setAura(true);
        return instance;
    }

    static StatusEffectInstance createAuraStatusEffectInstance(StatusEffect type, int duration, int amplifier, boolean ambient, boolean showParticles, boolean showIcon, @Nullable StatusEffectInstance hiddenEffect, Optional<StatusEffectInstance.FactorCalculationData> factorCalculationData) {
        StatusEffectInstance instance = new StatusEffectInstance(type, duration, amplifier, ambient, showParticles, showIcon, hiddenEffect, factorCalculationData);
        ((AuraEffect) instance).setAura(true);
        return instance;
    }

    static StatusEffectInstance createAuraStatusEffectInstance(StatusEffectInstance instance) {
        StatusEffectInstance ret = new StatusEffectInstance(instance);
        ((AuraEffect) ret).setAura(true);
        return ret;
    }

    static StatusEffectInstance transferAura(StatusEffectInstance o) {
        return createAuraStatusEffectInstance(o.getEffectType(), (int) (o.getDuration()  * GreatBigWorld.CONFIG.auraEffectTransferModifier), o.getAmplifier(), o.isAmbient(), o.shouldShowParticles(), o.shouldShowIcon());
    }
}
