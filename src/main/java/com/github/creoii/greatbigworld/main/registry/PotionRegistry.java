package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.AuraEffect;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.main.util.Tags;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class PotionRegistry implements Register {
    public static final Potion GLOWING = new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 3600));
    public static final Potion LONG_GLOWING = new Potion("glowing", new StatusEffectInstance(StatusEffects.GLOWING, 9600));
    public static final Potion STRONG_GLOWING = new Potion("glowing", new StatusEffectInstance(StatusEffects.GLOWING, 1800, 1));
    public static final Potion BLINDNESS = new Potion(new StatusEffectInstance(StatusEffects.BLINDNESS, 3600));
    public static final Potion LONG_BLINDNESS = new Potion("blindness", new StatusEffectInstance(StatusEffects.BLINDNESS, 9600));
    public static final Potion STRONG_BLINDNESS = new Potion("blindness", new StatusEffectInstance(StatusEffects.BLINDNESS, 1800, 1));
    public static final Potion DARKNESS = new Potion(new StatusEffectInstance(StatusEffects.DARKNESS, 1800));
    public static final Potion LONG_DARKNESS = new Potion("darkness", new StatusEffectInstance(StatusEffects.DARKNESS, 9600));
    public static final Potion STRONG_DARKNESS = new Potion("darkness", new StatusEffectInstance(StatusEffects.DARKNESS, 800, 1));

    public static final Potion AURA_NIGHT_VISION = new Potion("night_vision", AuraEffect.createAuraStatusEffectInstance(StatusEffects.NIGHT_VISION, 1800, 1));
    public static final Potion AURA_INVISIBILITY = new Potion("invisibility", AuraEffect.createAuraStatusEffectInstance(StatusEffects.INVISIBILITY, 1800, 1));
    public static final Potion AURA_LEAPING = new Potion("leaping", AuraEffect.createAuraStatusEffectInstance(StatusEffects.JUMP_BOOST, 1800, 1));
    public static final Potion AURA_FIRE_RESISTANCE = new Potion("fire_resistance", AuraEffect.createAuraStatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1800, 1));
    public static final Potion AURA_SWIFTNESS = new Potion("swiftness", AuraEffect.createAuraStatusEffectInstance(StatusEffects.SPEED, 1800, 1));
    public static final Potion AURA_SLOWNESS = new Potion("slowness", AuraEffect.createAuraStatusEffectInstance(StatusEffects.SLOWNESS, 900, 1));
    public static final Potion AURA_TURTLE_MASTER = new Potion("turtle_master", AuraEffect.createAuraStatusEffectInstance(StatusEffects.SLOWNESS, 300, 3), new StatusEffectInstance(StatusEffects.RESISTANCE, 300, 2));
    public static final Potion AURA_WATER_BREATHING = new Potion("water_breathing", AuraEffect.createAuraStatusEffectInstance(StatusEffects.WATER_BREATHING, 1800, 1));
    public static final Potion AURA_HEALING = new Potion("healing", AuraEffect.createAuraStatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 1));
    public static final Potion AURA_HARMING = new Potion("harming", AuraEffect.createAuraStatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 11, 1));
    public static final Potion AURA_POISON = new Potion("poison", AuraEffect.createAuraStatusEffectInstance(StatusEffects.POISON, 800, 1));
    public static final Potion AURA_REGENERATION = new Potion("regeneration", AuraEffect.createAuraStatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
    public static final Potion AURA_STRENGTH = new Potion("strength", AuraEffect.createAuraStatusEffectInstance(StatusEffects.STRENGTH, 1800, 1));
    public static final Potion AURA_WEAKNESS = new Potion("weakness", AuraEffect.createAuraStatusEffectInstance(StatusEffects.WEAKNESS, 900, 1));
    public static final Potion AURA_LUCK = new Potion("luck", AuraEffect.createAuraStatusEffectInstance(StatusEffects.LUCK, 2500, 1));
    public static final Potion AURA_SLOW_FALLING = new Potion("slow_falling", AuraEffect.createAuraStatusEffectInstance(StatusEffects.SLOW_FALLING, 900, 1));
    public static final Potion AURA_GLOWING = new Potion("glowing", AuraEffect.createAuraStatusEffectInstance(StatusEffects.GLOWING, 1800, 1));
    public static final Potion AURA_BLINDNESS = new Potion("blindness", AuraEffect.createAuraStatusEffectInstance(StatusEffects.BLINDNESS, 1800, 1));
    public static final Potion AURA_DARKNESS = new Potion("darkness", AuraEffect.createAuraStatusEffectInstance(StatusEffects.DARKNESS, 900, 1));

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

        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_night_vision"), AURA_NIGHT_VISION);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_invisibility"), AURA_INVISIBILITY);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_leaping"), AURA_LEAPING);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_fire_resistance"), AURA_FIRE_RESISTANCE);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_swiftness"), AURA_SWIFTNESS);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_slowness"), AURA_SLOWNESS);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_turtle_master"), AURA_TURTLE_MASTER);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_water_breathing"), AURA_WATER_BREATHING);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_healing"), AURA_HEALING);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_harming"), AURA_HARMING);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_poison"), AURA_POISON);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_regeneration"), AURA_REGENERATION);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_strength"), AURA_STRENGTH);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_weakness"), AURA_WEAKNESS);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_luck"), AURA_LUCK);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_slow_falling"), AURA_SLOW_FALLING);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_glowing"), AURA_GLOWING);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_blindness"), AURA_BLINDNESS);
        Registry.register(Registries.POTION, new Identifier(GreatBigWorld.NAMESPACE, "aura_darkness"), AURA_DARKNESS);
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

        BrewingRecipeRegistry.registerPotionType(ItemRegistry.AURA_POTION);
        FabricBrewingRecipeRegistry.registerItemRecipe((PotionItem) Items.POTION, Ingredient.fromTag(Tags.ItemTags.GLIMMERING_MUSHROOMS), (PotionItem) ItemRegistry.AURA_POTION);
    }
}
