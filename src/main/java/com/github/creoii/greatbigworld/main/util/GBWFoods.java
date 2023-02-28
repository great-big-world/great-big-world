package com.github.creoii.greatbigworld.main.util;

import net.minecraft.item.FoodComponent;

public final class GBWFoods {
    public static final FoodComponent VENISON = new FoodComponent.Builder().hunger(3).saturationModifier(.3f).build();
    public static final FoodComponent COOKED_VENISON = new FoodComponent.Builder().hunger(7).saturationModifier(.7f).build();
    public static final FoodComponent ACAI_BERRIES = new FoodComponent.Builder().hunger(2).saturationModifier(.4f).snack().build();
    public static final FoodComponent ACAI_BOWL = new FoodComponent.Builder().hunger(4).saturationModifier(.6f).build();
}
