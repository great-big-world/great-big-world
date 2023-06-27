package com.github.creoii.greatbigworld.recipe;

import com.github.creoii.greatbigworld.main.registry.GBWBlocks;
import com.github.creoii.greatbigworld.main.registry.GBWRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.util.Identifier;

public class FiringRecipe extends AbstractCookingRecipe {
    public FiringRecipe(Identifier id, String group, CookingRecipeCategory category, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(GBWRecipes.FIRING, id, group, category, input, output, experience, cookTime);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(GBWBlocks.KILN);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return GBWRecipes.FIRING_SERIALIZER;
    }
}
