package com.github.creoii.greatbigworld.recipe;

import com.github.creoii.greatbigworld.main.registry.GBWBlocks;
import com.github.creoii.greatbigworld.main.registry.GBWRecipes;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CuttingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class SawmillingRecipe extends CuttingRecipe {
    public SawmillingRecipe(Identifier id, String group, Ingredient input, ItemStack output) {
        super(GBWRecipes.SAWMILLING, GBWRecipes.SAWMLLING_SERIALIZER, id, group, input, output);
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        return input.test(inventory.getStack(0));
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(GBWBlocks.SAWMILL);
    }
}
