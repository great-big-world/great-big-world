package com.github.creoii.greatbigworld.entity.navigation;

import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SwimNavigation<E extends MobEntity> extends AmphibiousSwimNavigation {
    private final boolean canEnterDoors;

    public SwimNavigation(E mob, World world, boolean canEnterDoors) {
        super(mob, world);
        this.canEnterDoors = canEnterDoors;
    }

    public PathNodeNavigator createPathNodeNavigator(int range) {
        nodeMaker = new SwimPathNodeMaker(true);
        nodeMaker.setCanEnterOpenDoors(canEnterDoors);
        return new PathNodeNavigator(nodeMaker, range);
    }

    public static class SwimPathNodeMaker extends AmphibiousPathNodeMaker {
        private final BlockPos.Mutable pos = new BlockPos.Mutable();

        public SwimPathNodeMaker(boolean bl) {
            super(bl);
        }

        @Nullable
        public PathNode getStart() {
            return getStart(new BlockPos(MathHelper.floor(entity.getBoundingBox().minX), MathHelper.floor(entity.getBoundingBox().minY), MathHelper.floor(entity.getBoundingBox().minZ)));
        }

        public PathNodeType getDefaultNodeType(BlockView world, int x, int y, int z) {
            pos.set(x, y - 1, z);
            return super.getDefaultNodeType(world, x, y, z);
        }
    }
}
