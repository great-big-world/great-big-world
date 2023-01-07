package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

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
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "glowing"), GLOWING);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "long_glowing"), LONG_GLOWING);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "strong_glowing"), STRONG_GLOWING);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "blindness"), BLINDNESS);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "long_blindness"), LONG_BLINDNESS);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "strong_blindness"), STRONG_BLINDNESS);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "darkness"), DARKNESS);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "long_darkness"), LONG_DARKNESS);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "strong_darkness"), STRONG_DARKNESS);

        registerPotionRecipes();
    }

    public void registerPotionRecipes() {
        FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Ingredient.ofItems(BlockRegistry.DAYLIGHT_MUSHROOM), GLOWING);
        FabricBrewingRecipeRegistry.registerPotionRecipe(GLOWING, Ingredient.ofItems(Items.REDSTONE), LONG_GLOWING);
        FabricBrewingRecipeRegistry.registerPotionRecipe(GLOWING, Ingredient.ofItems(Items.GLOWSTONE_DUST), STRONG_GLOWING);
        FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Ingredient.ofItems(BlockRegistry.MIDNIGHT_MUSHROOM), BLINDNESS);
        FabricBrewingRecipeRegistry.registerPotionRecipe(BLINDNESS, Ingredient.ofItems(Items.REDSTONE), LONG_BLINDNESS);
        FabricBrewingRecipeRegistry.registerPotionRecipe(BLINDNESS, Ingredient.ofItems(Items.GLOWSTONE_DUST), STRONG_BLINDNESS);
        FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Ingredient.ofItems(BlockRegistry.DARKBLIGHT_MUSHROOM), DARKNESS);
        FabricBrewingRecipeRegistry.registerPotionRecipe(DARKNESS, Ingredient.ofItems(Items.REDSTONE), LONG_DARKNESS);
        FabricBrewingRecipeRegistry.registerPotionRecipe(DARKNESS, Ingredient.ofItems(Items.GLOWSTONE_DUST), STRONG_DARKNESS);
    }
}
