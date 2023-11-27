package com.github.creoii.greatbigworld.main.mixin.entity;

import com.github.creoii.greatbigworld.main.registry.GBWItems;
import com.github.creoii.greatbigworld.main.util.GBWBoatTypes;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChestBoatEntity.class)
public class ChestBoatEntityMixin {
    @Inject(method = "asItem", at = @At("HEAD"), cancellable = true)
    public void gbw_injectGBWChestBoatItems(CallbackInfoReturnable<Item> ci) {
        if (((BoatEntity)(Object)this).getVariant() == GBWBoatTypes.MAHOGANY)
            ci.setReturnValue(GBWItems.MAHOGANY_CHEST_BOAT);
        if (((BoatEntity)(Object)this).getVariant() == GBWBoatTypes.ASPEN)
            ci.setReturnValue(GBWItems.ASPEN_CHEST_BOAT);
        if (((BoatEntity)(Object)this).getVariant() == GBWBoatTypes.ACAI)
            ci.setReturnValue(GBWItems.ACAI_CHEST_BOAT);
        if (((BoatEntity)(Object)this).getVariant() == GBWBoatTypes.WISTERIA)
            ci.setReturnValue(GBWItems.WISTERIA_CHEST_BOAT);
        if (((BoatEntity)(Object)this).getVariant() == GBWBoatTypes.PINE)
            ci.setReturnValue(GBWItems.PINE_CHEST_BOAT);
        if (((BoatEntity)(Object)this).getVariant() == GBWBoatTypes.PALO_VERDE)
            ci.setReturnValue(GBWItems.PALO_VERDE_CHEST_BOAT);

    }
}
