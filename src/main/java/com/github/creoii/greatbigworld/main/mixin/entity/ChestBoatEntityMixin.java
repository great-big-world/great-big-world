package com.github.creoii.greatbigworld.main.mixin.entity;

import com.github.creoii.greatbigworld.main.registry.ItemRegistry;
import com.github.creoii.greatbigworld.main.util.GBWBoatTypes;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoatEntity.class)
public class ChestBoatEntityMixin {
    @Inject(method = "asItem", at = @At("HEAD"), cancellable = true)
    public void asItem(CallbackInfoReturnable<Item> ci) {
        if (((BoatEntity)(Object)this).getBoatType() == GBWBoatTypes.MAHOGANY)
            ci.setReturnValue(ItemRegistry.MAHOGANY_BOAT);
        if (((BoatEntity)(Object)this).getBoatType() == GBWBoatTypes.ASPEN)
            ci.setReturnValue(ItemRegistry.ASPEN_BOAT);
    }
}
