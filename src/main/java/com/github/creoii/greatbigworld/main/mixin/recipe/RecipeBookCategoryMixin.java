package com.github.creoii.greatbigworld.main.mixin.recipe;

import com.github.creoii.greatbigworld.main.util.GBWRecipeBookCategories;
import net.minecraft.recipe.book.RecipeBookCategory;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(RecipeBookCategory.class)
public class RecipeBookCategoryMixin {
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static RecipeBookCategory create(String internalName, int internalId) {
        throw new AssertionError();
    }

    @Shadow
    @Final
    @Mutable
    private static RecipeBookCategory[] field_25767;

    @Inject(method = "<clinit>", at = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lnet/minecraft/recipe/book/RecipeBookCategory;field_25767:[Lnet/minecraft/recipe/book/RecipeBookCategory;", shift = At.Shift.AFTER))
    private static void addCustomRecipeBookCategory(CallbackInfo ci) {
        ArrayList<RecipeBookCategory> types = new ArrayList<>(Arrays.asList(field_25767));
        RecipeBookCategory last = types.get(types.size() - 1);

        RecipeBookCategory firing = create("GBW_KILN", last.ordinal() + 1);
        GBWRecipeBookCategories.KILN = firing;
        types.add(firing);

        field_25767 = types.toArray(new RecipeBookCategory[0]);
    }
}
