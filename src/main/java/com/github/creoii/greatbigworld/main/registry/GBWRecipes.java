package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.recipe.FiringRecipe;
import com.github.creoii.greatbigworld.recipe.SawmillingRecipe;
import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class GBWRecipes implements Register {
    public static final RecipeType<SawmillingRecipe> SAWMILLING = new RecipeType<>() {
        @Override
        public String toString() {
            return "sawmilling";
        }
    };
    public static final RecipeType<FiringRecipe> FIRING = new RecipeType<>() {
        @Override
        public String toString() {
            return "firing";
        }
    };

    public static final RecipeSerializer<SawmillingRecipe> SAWMLLING_SERIALIZER = new CuttingRecipe.Serializer<>(SawmillingRecipe::new);
    public static final RecipeSerializer<FiringRecipe> FIRING_SERIALIZER = new CookingRecipeSerializer<>(FiringRecipe::new, 100);

    @Override
    public void register() {
        Registry.register(Registries.RECIPE_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "sawmilling"), SAWMILLING);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "firing"), FIRING);

        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(GreatBigWorld.NAMESPACE, "sawmilling"), SAWMLLING_SERIALIZER);
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(GreatBigWorld.NAMESPACE, "firing"), FIRING_SERIALIZER);
    }
}
