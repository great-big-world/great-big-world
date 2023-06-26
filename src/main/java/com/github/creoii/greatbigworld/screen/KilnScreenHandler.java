package com.github.creoii.greatbigworld.screen;

import com.github.creoii.greatbigworld.main.registry.GBWRecipes;
import com.github.creoii.greatbigworld.main.registry.GBWScreens;
import com.github.creoii.greatbigworld.main.util.GBWRecipeBookCategories;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;

public class KilnScreenHandler extends AbstractFurnaceScreenHandler {
    public KilnScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(GBWScreens.KILN, GBWRecipes.FIRING, GBWRecipeBookCategories.KILN, syncId, playerInventory);
    }

    public KilnScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(GBWScreens.KILN, GBWRecipes.FIRING, GBWRecipeBookCategories.KILN, syncId, playerInventory, inventory, propertyDelegate);
    }
}
