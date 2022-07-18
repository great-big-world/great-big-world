package com.github.creoii.greatbigworld.main.util;

import com.github.creoii.greatbigworld.main.registry.StatusEffectRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class Foods {
    public static final FoodComponent BERRIES = new FoodComponent.Builder().hunger(2).build();
    public static final FoodComponent CHEESE = new FoodComponent.Builder().hunger(2).saturationModifier(.25f).build();
    public static final FoodComponent MOLDY_CHEESE = new FoodComponent.Builder().hunger(1).saturationModifier(.0f).statusEffect(new StatusEffectInstance(StatusEffectRegistry.SICKENED, 8), .95f).build();
    public static final FoodComponent SHROOMLIGHT_FRUIT = new FoodComponent.Builder().hunger(3).saturationModifier(.3f).snack().build();
    public static final FoodComponent HEARTBEET = new FoodComponent.Builder().hunger(1).saturationModifier(.65f).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2, 9), 1f).build();
}
