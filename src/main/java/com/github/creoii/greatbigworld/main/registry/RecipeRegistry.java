package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.recipe.SawmillingRecipe;
import net.minecraft.recipe.CuttingRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RecipeRegistry {
    public static final RecipeType<SawmillingRecipe> SAWMILLING = new RecipeType<SawmillingRecipe>() {
        public String toString() {
            return "sawmilling";
        }
    };

    public static final RecipeSerializer<SawmillingRecipe> SAWMILLING_SERIALIZER = new CuttingRecipe.Serializer<>(SawmillingRecipe::new);

    public static void register() {
        Registry.register(Registry.RECIPE_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "sawmilling"), SAWMILLING);
    }
}
