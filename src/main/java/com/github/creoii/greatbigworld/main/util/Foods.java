package com.github.creoii.greatbigworld.main.util;

import net.minecraft.item.FoodComponent;

public class Foods {
    public static final FoodComponent VENISON = new FoodComponent.Builder().hunger(3).saturationModifier(.3f).build();
    public static final FoodComponent COOKED_VENISON = new FoodComponent.Builder().hunger(7).saturationModifier(.7f).build();
}
