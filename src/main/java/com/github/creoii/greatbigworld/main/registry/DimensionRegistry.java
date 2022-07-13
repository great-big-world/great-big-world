package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class DimensionRegistry {
    public static void register() {
        DimensionEffects.BY_IDENTIFIER.put(new Identifier(GreatBigWorld.NAMESPACE, "the_hallow"), new HallowEffects());
    }

    @Environment(EnvType.CLIENT)
    public static class HallowEffects extends DimensionEffects {
        public HallowEffects() {
            super(0f, true, SkyType.NONE, false, true);
        }

        public Vec3d adjustFogColor(Vec3d color, float sunHeight) {
            return color;
        }

        public boolean useThickFog(int camX, int camY) {
            return true;
        }
    }
}
