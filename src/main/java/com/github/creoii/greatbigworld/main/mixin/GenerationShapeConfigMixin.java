package com.github.creoii.greatbigworld.main.mixin;

import net.minecraft.world.gen.chunk.GenerationShapeConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GenerationShapeConfig.class)
public abstract class GenerationShapeConfigMixin {
    @Mutable @Shadow @Final protected static GenerationShapeConfig NETHER;
    @Shadow public static GenerationShapeConfig create(int minimumY, int height, int horizontalSize, int verticalSize) { return null; }

    static {
        NETHER = create(0, 256, 1, 2);
    }
}
