package com.github.creoii.greatbigworld.main.mixin;

import com.github.creoii.greatbigworld.main.integration.TerraBlenderIntegration;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import terrablender.api.SurfaceRuleManager;

@Mixin(ChunkGeneratorSettings.class)
public class ChunkGeneratorSettingsMixin {
    @Inject(method = "surfaceRule", at = @At("HEAD"), cancellable = true)
    private void great_big_world_injectSurfaceRules(CallbackInfoReturnable<MaterialRules.MaterialRule> cir) {
        cir.setReturnValue(MaterialRules.sequence(SurfaceRuleManager.getNamespacedRules(SurfaceRuleManager.RuleCategory.OVERWORLD, TerraBlenderIntegration.createSurfaceRules()), TerraBlenderIntegration.createSurfaceRules()));
    }
}
