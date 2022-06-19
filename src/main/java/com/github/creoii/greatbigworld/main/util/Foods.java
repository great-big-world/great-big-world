package com.github.creoii.greatbigworld.main.util;

import net.minecraft.item.FoodComponent;

public class Foods {
    public static final FoodComponent BERRIES = new FoodComponent.Builder().hunger(2).build();
    public static final FoodComponent CHEESE = new FoodComponent.Builder().hunger(2).saturationModifier(.25f).build();
    public static final FoodComponent SHROOMLIGHT_FRUIT = new FoodComponent.Builder().hunger(3).saturationModifier(.3f).snack().build();
}
