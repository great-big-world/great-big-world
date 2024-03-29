package com.github.creoii.greatbigworld.main.util;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.integration.ModMenuIntegration;
import com.github.creoii.greatbigworld.main.registry.GBWItems;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
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

    static void addAuraPotions(ItemGroup.Entries entries) {
        for (Potion potion : Registries.POTION) {
            if (potion == Potions.EMPTY) continue;
            potion.getEffects().forEach(instance -> ((AuraEffect) instance).setAura(true));
            entries.add(PotionUtil.setPotion(GBWItems.AURA_POTION.getDefaultStack(), potion), ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    static StatusEffectInstance transferAura(StatusEffectInstance o) {
        float value = GreatBigWorld.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.auraEffectTransferModifier.floatValue() : .5f;
        return createAuraStatusEffectInstance(o.getEffectType(), (int) (o.getDuration()  * value), o.getAmplifier(), o.isAmbient(), o.shouldShowParticles(), o.shouldShowIcon());
    }
}
