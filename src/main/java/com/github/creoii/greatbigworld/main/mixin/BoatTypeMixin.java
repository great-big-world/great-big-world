package com.github.creoii.greatbigworld.main.mixin;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.main.util.GBWBoatTypes;
import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.BoatEntity;
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

@Mixin(BoatEntity.Type.class)
public class BoatTypeMixin {
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static BoatEntity.Type create(String internalName, int internalId, Block baseBlock, String name) {
        throw new AssertionError();
    }

    @Shadow @Final @Mutable
    private static BoatEntity.Type[] field_7724;

    @Inject(method = "<clinit>", at = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lnet/minecraft/entity/vehicle/BoatEntity$Type;field_7724:[Lnet/minecraft/entity/vehicle/BoatEntity$Type;", shift = At.Shift.AFTER))
    private static void addCustomBoatType(CallbackInfo ci) {
        ArrayList<BoatEntity.Type> types = new ArrayList<>(Arrays.asList(field_7724));
        BoatEntity.Type last = types.get(types.size() - 1);

        BoatEntity.Type mahogany = create("GBW_MAHOGANY", last.ordinal() + 1, BlockRegistry.MAHOGANY.planks(), "gbw_mahogany");
        BoatEntity.Type aspen = create("GBW_ASPEN", last.ordinal() + 3, BlockRegistry.ASPEN.planks(), "gbw_aspen");
        GBWBoatTypes.MAHOGANY = mahogany;
        GBWBoatTypes.ASPEN = aspen;
        types.add(mahogany);
        types.add(aspen);

        field_7724 = types.toArray(new BoatEntity.Type[0]);
    }
}