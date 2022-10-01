package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PotionRegistry implements Register {
    public static final Potion GLOWING = new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 3600));
    public static final Potion LONG_GLOWING = new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 9600));
    public static final Potion STRONG_GLOWING = new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 1800, 1));
    public static final Potion BLINDNESS = new Potion(new StatusEffectInstance(StatusEffects.BLINDNESS, 3600));
    public static final Potion LONG_BLINDNESS = new Potion(new StatusEffectInstance(StatusEffects.BLINDNESS, 9600));
    public static final Potion STRONG_BLINDNESS = new Potion(new StatusEffectInstance(StatusEffects.BLINDNESS, 1800, 1));
    public static final Potion DARKNESS = new Potion(new StatusEffectInstance(StatusEffects.DARKNESS, 1800));
    public static final Potion LONG_DARKNESS = new Potion(new StatusEffectInstance(StatusEffects.DARKNESS, 9600));
    public static final Potion STRONG_DARKNESS = new Potion(new StatusEffectInstance(StatusEffects.DARKNESS, 800, 1));

    @Override
    public void register() {
        Registry.register(Registry.POTION, new Identifier(GreatBigWorld.NAMESPACE, "glowing"), GLOWING);
        Registry.register(Registry.POTION, new Identifier(GreatBigWorld.NAMESPACE, "long_glowing"), LONG_GLOWING);
        Registry.register(Registry.POTION, new Identifier(GreatBigWorld.NAMESPACE, "strong_glowing"), STRONG_GLOWING);
        Registry.register(Registry.POTION, new Identifier(GreatBigWorld.NAMESPACE, "blindness"), BLINDNESS);
        Registry.register(Registry.POTION, new Identifier(GreatBigWorld.NAMESPACE, "long_blindness"), LONG_BLINDNESS);
        Registry.register(Registry.POTION, new Identifier(GreatBigWorld.NAMESPACE, "strong_blindness"), STRONG_BLINDNESS);
        Registry.register(Registry.POTION, new Identifier(GreatBigWorld.NAMESPACE, "darkness"), DARKNESS);
        Registry.register(Registry.POTION, new Identifier(GreatBigWorld.NAMESPACE, "long_darkness"), LONG_DARKNESS);
        Registry.register(Registry.POTION, new Identifier(GreatBigWorld.NAMESPACE, "strong_darkness"), STRONG_DARKNESS);

        registerPotionRecipes();
    }

    public void registerPotionRecipes() {
        BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, BlockRegistry.DAYLIGHT_MUSHROOM.asItem(), GLOWING);
        BrewingRecipeRegistry.registerPotionRecipe(GLOWING, Items.REDSTONE, LONG_GLOWING);
        BrewingRecipeRegistry.registerPotionRecipe(GLOWING, Items.GLOWSTONE_DUST, STRONG_GLOWING);
        BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, BlockRegistry.MIDNIGHT_MUSHROOM.asItem(), BLINDNESS);
        BrewingRecipeRegistry.registerPotionRecipe(BLINDNESS, Items.REDSTONE, LONG_BLINDNESS);
        BrewingRecipeRegistry.registerPotionRecipe(BLINDNESS, Items.GLOWSTONE_DUST, STRONG_BLINDNESS);
        BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, BlockRegistry.DARKBLIGHT_MUSHROOM.asItem(), DARKNESS);
        BrewingRecipeRegistry.registerPotionRecipe(DARKNESS, Items.REDSTONE, LONG_DARKNESS);
        BrewingRecipeRegistry.registerPotionRecipe(DARKNESS, Items.GLOWSTONE_DUST, STRONG_DARKNESS);
    }
}
