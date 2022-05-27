package com.github.creoii.greatbigworld.main.mixin;

import com.mojang.serialization.Lifecycle;
import net.minecraft.world.gen.GeneratorOptions;
import net.minecraft.world.level.LevelInfo;
import net.minecraft.world.level.LevelProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(LevelProperties.class)
public class LevelPropertiesMixin {
    private static final Random RANDOM = new Random();

    @Shadow private int spawnX;
    @Shadow private int spawnZ;
    @Shadow private float spawnAngle;
    @Shadow private long timeOfDay;
    @Shadow private int rainTime;
    @Shadow private boolean raining;
    @Shadow private int thunderTime;
    @Shadow private boolean thundering;

    @Inject(method = "<init>(Lnet/minecraft/world/level/LevelInfo;Lnet/minecraft/world/gen/GeneratorOptions;Lcom/mojang/serialization/Lifecycle;)V", at = @At("TAIL"))
    private void change_the_world$startWorldRandomly(LevelInfo levelInfo, GeneratorOptions generatorOptions, Lifecycle lifecycle, CallbackInfo ci) {
        spawnX = RANDOM.nextInt(24000);
        spawnZ = RANDOM.nextInt(24000);
        spawnAngle = RANDOM.nextInt(360);
        timeOfDay = RANDOM.nextInt(24000);
        rainTime = RANDOM.nextInt(180000);
        raining = RANDOM.nextInt(5) == 0;
        thunderTime = RANDOM.nextInt(180000);
        thundering = RANDOM.nextInt(5) == 0;
    }
}
