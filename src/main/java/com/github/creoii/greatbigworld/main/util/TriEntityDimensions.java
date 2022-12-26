package com.github.creoii.greatbigworld.main.util;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.util.math.Box;

public class TriEntityDimensions extends EntityDimensions {
    public final float length;

    public TriEntityDimensions(float length, float width, float height, boolean fixed) {
        super(width, height, fixed);
        this.length = length;
    }

    public Box getBoxAt(double x, double y, double z) {
        float l = length / 2f;
        float w = width / 2f;
        return new Box(x - (double)l, y, z - (double)w, x + (double)l, y + (double)height, z + (double)w);
    }

    public TriEntityDimensions scaled(float ratio) {
        return scaled(ratio, ratio, ratio);
    }

    public TriEntityDimensions scaled(float lengthRatio, float widthRatio, float heightRatio) {
        return !fixed && (widthRatio != 1f || heightRatio != 1f) ? changing(length * lengthRatio, width * widthRatio, height * heightRatio) : this;
    }

    public static TriEntityDimensions changing(float length, float width, float height) {
        return new TriEntityDimensions(length, width, height, false);
    }

    public static TriEntityDimensions fixed(float length, float width, float height) {
        return new TriEntityDimensions(length, width, height, true);
    }

    public String toString() {
        return "EntityDimensions l=" + length + ", w=" + width + ", h=" + height + ", fixed=" + fixed;
    }
}
