package com.github.creoii.greatbigworld.main.mixin.recipe;

import com.github.creoii.greatbigworld.main.util.GBWRecipeBookCategories;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.book.RecipeBookOptions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(RecipeBookOptions.class)
public class RecipeBookOptionsMixin {
    @Mutable
    @Shadow @Final private static Map<RecipeBookCategory, Pair<String, String>> CATEGORY_OPTION_NAMES;

    static {
        CATEGORY_OPTION_NAMES = new ImmutableMap.Builder<RecipeBookCategory, Pair<String, String>>()
                .putAll(CATEGORY_OPTION_NAMES)
                .put(GBWRecipeBookCategories.KILN, Pair.of("isGBWKilnGuiOpen", "isGBWKilnFilteringCraftable"))
                .build();
    }
}
