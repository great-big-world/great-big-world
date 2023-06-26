package com.github.creoii.greatbigworld.main.mixin.recipe;

import com.github.creoii.greatbigworld.main.util.GBWRecipeBookGroups;
import com.github.creoii.greatbigworld.recipe.FiringRecipe;
import com.github.creoii.greatbigworld.recipe.SawmillingRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.book.CookingRecipeCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientRecipeBook.class)
@Environment(EnvType.CLIENT)
public class ClientRecipeBookMixin {
    @Inject(method = "getGroupForRecipe", at = @At("HEAD"), cancellable = true)
    private static void gbw_injectCustomRecipeGroups(Recipe<?> recipe, CallbackInfoReturnable<RecipeBookGroup> cir) {
        if (recipe instanceof FiringRecipe firingRecipe) {
            cir.setReturnValue(firingRecipe.getCategory() == CookingRecipeCategory.BLOCKS ? GBWRecipeBookGroups.KILN_BLOCKS : GBWRecipeBookGroups.KILN_MISC);
        }
        if (recipe instanceof SawmillingRecipe) {
            cir.setReturnValue(GBWRecipeBookGroups.SAWMILL);
        }
    }
}
