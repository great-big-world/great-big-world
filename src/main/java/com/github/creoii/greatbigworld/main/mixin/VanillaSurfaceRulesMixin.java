package com.github.creoii.greatbigworld.main.mixin;

import com.github.creoii.greatbigworld.main.registry.BiomeRegistry;
import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(VanillaSurfaceRules.class)
public abstract class VanillaSurfaceRulesMixin {
    @Shadow
    protected static MaterialRules.MaterialRule block(Block block) {
        return null;
    }

    /*
        Called after Stony Peaks, Stony Shores, Windswept Hills, & Dripstone Caves, Oceans, Desert
         */
    @Inject(method = "createDefaultRule", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private static void great_big_world_createStoneSurfaces(boolean surface, boolean bedrockRoof, boolean bedrockFloor, CallbackInfoReturnable<MaterialRules.MaterialRule> cir, MaterialRules.MaterialCondition materialCondition, MaterialRules.MaterialCondition materialCondition2, MaterialRules.MaterialCondition materialCondition3, MaterialRules.MaterialCondition materialCondition4, MaterialRules.MaterialCondition materialCondition5, MaterialRules.MaterialCondition materialCondition6, MaterialRules.MaterialCondition materialCondition7, MaterialRules.MaterialCondition materialCondition8, MaterialRules.MaterialCondition materialCondition9, MaterialRules.MaterialCondition materialCondition10, MaterialRules.MaterialCondition materialCondition11, MaterialRules.MaterialCondition materialCondition12, MaterialRules.MaterialCondition materialCondition13, MaterialRules.MaterialRule materialRule4) {
        cir.setReturnValue(MaterialRules.sequence(cir.getReturnValue()));
    }

    /*
    Called after Frozen Peaks, Snowy Slopes, Jagged Peaks, Grove, Windswept Savanna & Gravelly Hills, & Mangrove Swamp
     */
    @Inject(method = "createDefaultRule", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private static void great_big_world_createMountainousSurfaces(boolean surface, boolean bedrockRoof, boolean bedrockFloor, CallbackInfoReturnable<MaterialRules.MaterialRule> cir, MaterialRules.MaterialCondition materialCondition, MaterialRules.MaterialCondition materialCondition2, MaterialRules.MaterialCondition materialCondition3, MaterialRules.MaterialCondition materialCondition4, MaterialRules.MaterialCondition materialCondition5, MaterialRules.MaterialCondition materialCondition6, MaterialRules.MaterialCondition materialCondition7, MaterialRules.MaterialCondition materialCondition8, MaterialRules.MaterialCondition materialCondition9, MaterialRules.MaterialCondition materialCondition10, MaterialRules.MaterialCondition materialCondition11, MaterialRules.MaterialCondition materialCondition12, MaterialRules.MaterialCondition materialCondition13, MaterialRules.MaterialRule materialRule4) {
        cir.setReturnValue(MaterialRules.sequence(cir.getReturnValue()));
    }

    /*
    Called after Frozen Peaks, Snowy Slopes, Jagged Peaks, Grove, Windswept Savanna & Gravelly Hills, Mega Taigas, Ice Spikes, Mangrove Swamp, Mushroom Island
     */
    @Inject(method = "createDefaultRule", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private static void great_big_world_createMountainousSurfacesAgain(boolean surface, boolean bedrockRoof, boolean bedrockFloor, CallbackInfoReturnable<MaterialRules.MaterialRule> cir, MaterialRules.MaterialCondition materialCondition, MaterialRules.MaterialCondition materialCondition2, MaterialRules.MaterialCondition materialCondition3, MaterialRules.MaterialCondition materialCondition4, MaterialRules.MaterialCondition materialCondition5, MaterialRules.MaterialCondition materialCondition6, MaterialRules.MaterialCondition materialCondition7, MaterialRules.MaterialCondition materialCondition8, MaterialRules.MaterialCondition materialCondition9, MaterialRules.MaterialCondition materialCondition10, MaterialRules.MaterialCondition materialCondition11, MaterialRules.MaterialCondition materialCondition12, MaterialRules.MaterialCondition materialCondition13, MaterialRules.MaterialRule materialRule4) {
        cir.setReturnValue(MaterialRules.sequence(cir.getReturnValue()));
    }

    /*
    Called after Badlands, Swamp & Mangrove Swamp, Cold Peaks, Oceans
     */
    @Inject(method = "createDefaultRule", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private static void great_big_world_createBadlandsSurfaces(boolean surface, boolean bedrockRoof, boolean bedrockFloor, CallbackInfoReturnable<MaterialRules.MaterialRule> cir, MaterialRules.MaterialCondition materialCondition, MaterialRules.MaterialCondition materialCondition2, MaterialRules.MaterialCondition materialCondition3, MaterialRules.MaterialCondition materialCondition4, MaterialRules.MaterialCondition materialCondition5, MaterialRules.MaterialCondition materialCondition6, MaterialRules.MaterialCondition materialCondition7, MaterialRules.MaterialCondition materialCondition8, MaterialRules.MaterialCondition materialCondition9, MaterialRules.MaterialCondition materialCondition10, MaterialRules.MaterialCondition materialCondition11, MaterialRules.MaterialCondition materialCondition12, MaterialRules.MaterialCondition materialCondition13, MaterialRules.MaterialRule materialRule4) {
        cir.setReturnValue(MaterialRules.sequence(cir.getReturnValue()));
    }

    @Inject(method = "createNetherSurfaceRule", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private static void great_big_world_createNetherSurfaces(CallbackInfoReturnable<MaterialRules.MaterialRule> cir, MaterialRules.MaterialCondition materialCondition, MaterialRules.MaterialCondition materialCondition2, MaterialRules.MaterialCondition materialCondition3, MaterialRules.MaterialCondition materialCondition4, MaterialRules.MaterialCondition materialCondition5, MaterialRules.MaterialCondition materialCondition6, MaterialRules.MaterialCondition materialCondition7, MaterialRules.MaterialCondition materialCondition8, MaterialRules.MaterialCondition materialCondition9, MaterialRules.MaterialCondition materialCondition10, MaterialRules.MaterialCondition materialCondition11, MaterialRules.MaterialCondition materialCondition12, MaterialRules.MaterialRule materialRule) {
        cir.setReturnValue(MaterialRules.sequence(cir.getReturnValue(), MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(BiomeRegistry.TWISTED_FOREST), MaterialRules.condition(MaterialRules.not(materialCondition10), MaterialRules.condition(materialCondition, MaterialRules.sequence(MaterialRules.condition(materialCondition11, block(BlockRegistry.TWISTED_WART_BLOCK)), block(BlockRegistry.TWISTED_NYLIUM))))))));
    }
}
