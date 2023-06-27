package com.github.creoii.greatbigworld.main.mixin.recipe;

import com.github.creoii.greatbigworld.main.util.GBWRecipeBookCategories;
import com.github.creoii.greatbigworld.main.util.GBWRecipeBookGroups;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(RecipeBookGroup.class)
public class RecipeBookGroupMixin {
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static RecipeBookGroup create(String internalName, int internalId, ItemStack... entries) {
        throw new AssertionError();
    }

    @Shadow
    @Final
    @Mutable
    private static RecipeBookGroup[] field_1805;

    private static final List<RecipeBookGroup> KILN = ImmutableList.of(GBWRecipeBookGroups.KILN_MISC, GBWRecipeBookGroups.KILN_BLOCKS);

    @Inject(method = "<clinit>", at = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lnet/minecraft/client/recipebook/RecipeBookGroup;field_1805:[Lnet/minecraft/client/recipebook/RecipeBookGroup;", shift = At.Shift.AFTER))
    private static void addCustomRecipeBookGroup(CallbackInfo ci) {
        ArrayList<RecipeBookGroup> types = new ArrayList<>(Arrays.asList(field_1805));
        RecipeBookGroup last = types.get(types.size() - 1);

        RecipeBookGroup kilnMisc = create("GBW_KILN_MISC", last.ordinal() + 1, new ItemStack(Items.BRICK));
        RecipeBookGroup kilnBlocks = create("GBW_KILN_BLOCKS", last.ordinal() + 2, new ItemStack(Blocks.BROWN_GLAZED_TERRACOTTA));
        RecipeBookGroup sawmill = create("GBW_SAWMILL", last.ordinal() + 3, new ItemStack(Blocks.OAK_PLANKS));
        GBWRecipeBookGroups.KILN_MISC = kilnMisc;
        GBWRecipeBookGroups.KILN_BLOCKS = kilnBlocks;
        GBWRecipeBookGroups.SAWMILL = sawmill;
        types.add(kilnMisc);
        types.add(kilnBlocks);
        types.add(sawmill);

        field_1805 = types.toArray(new RecipeBookGroup[0]);
    }

    @Inject(method = "getGroups", at = @At("HEAD"), cancellable = true)
    private static void gbw_injectCustomRecipeBookGroups(RecipeBookCategory category, CallbackInfoReturnable<List<RecipeBookGroup>> cir) {
        if (category == GBWRecipeBookCategories.KILN) {
            cir.setReturnValue(KILN);
        }
    }
}
