package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.statuseffect.SickenedStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class StatusEffectRegistry {
    public static final StatusEffect SICKENED = new SickenedStatusEffect();

    public static void register() {
        Registry.register(Registry.STATUS_EFFECT, new Identifier(GreatBigWorld.NAMESPACE, "sickened"), SICKENED);
    }
}
