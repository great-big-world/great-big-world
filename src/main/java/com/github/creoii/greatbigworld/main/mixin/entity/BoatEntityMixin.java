package com.github.creoii.greatbigworld.main.mixin.entity;

import com.github.creoii.greatbigworld.main.registry.GBWItems;
import com.github.creoii.greatbigworld.main.util.GBWBoatTypes;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoatEntity.class)
public class BoatEntityMixin {
    @Inject(method = "asItem", at = @At("HEAD"), cancellable = true)
    public void gbw_injectGBWBoatItems(CallbackInfoReturnable<Item> ci) {
        if (((BoatEntity)(Object)this).getVariant() == GBWBoatTypes.MAHOGANY)
            ci.setReturnValue(GBWItems.MAHOGANY_BOAT);
        if (((BoatEntity)(Object)this).getVariant() == GBWBoatTypes.ASPEN)
            ci.setReturnValue(GBWItems.ASPEN_BOAT);
        if (((BoatEntity)(Object)this).getVariant() == GBWBoatTypes.ACAI)
            ci.setReturnValue(GBWItems.ACAI_BOAT);
        if (((BoatEntity)(Object)this).getVariant() == GBWBoatTypes.WISTERIA)
            ci.setReturnValue(GBWItems.WISTERIA_BOAT);
        if (((BoatEntity)(Object)this).getVariant() == GBWBoatTypes.PINE)
            ci.setReturnValue(GBWItems.PINE_BOAT);
        if (((BoatEntity)(Object)this).getVariant() == GBWBoatTypes.PALO_VERDE)
            ci.setReturnValue(GBWItems.PALO_VERDE_BOAT);
    }
}
