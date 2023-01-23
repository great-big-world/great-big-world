package com.github.creoii.greatbigworld.main.mixin.client;

import com.github.creoii.greatbigworld.client.GreatBigWorldClient;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow public abstract String getWindowTitle();
    @Shadow @Final private Window window;

    @Inject(method = "onWindowFocusChanged", at = @At("TAIL"))
    private void great_big_world_gbwWindowTitle(boolean focused, CallbackInfo ci) {
        if (GreatBigWorldClient.gbwTitle) {
            if (!focused) {
                window.setTitle(getWindowTitle() + " | " + " Great Big World " + GreatBigWorld.VERSION);
            } else {
                window.setTitle(getWindowTitle());
            }
        }
    }
}
