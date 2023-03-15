package com.github.creoii.greatbigworld.main.mixin;

import com.github.creoii.greatbigworld.main.util.AuraEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(StatusEffectInstance.class)
public class StatusEffectInstanceMixin implements AuraEffect {
    private boolean aura;

    @Override
    public boolean isAura() {
        return aura;
    }

    @Override
    public void setAura(boolean isAura) {
        aura = isAura;
    }
}
