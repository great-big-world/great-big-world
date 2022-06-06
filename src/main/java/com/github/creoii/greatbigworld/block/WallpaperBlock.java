package com.github.creoii.greatbigworld.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.Map;

public class WallpaperBlock extends AbstractLichenBlock {
    private static final VoxelShape UP_SHAPE = Block.createCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape DOWN_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    private static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    private static final VoxelShape WEST_SHAPE = Block.createCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
    private static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
    private static final Map<Direction, VoxelShape> SHAPES_FOR_DIRECTIONS = Util.make(Maps.newEnumMap(Direction.class), (shapes) -> {
        shapes.put(Direction.NORTH, SOUTH_SHAPE);
        shapes.put(Direction.EAST, WEST_SHAPE);
        shapes.put(Direction.SOUTH, NORTH_SHAPE);
        shapes.put(Direction.WEST, EAST_SHAPE);
        shapes.put(Direction.UP, UP_SHAPE);
        shapes.put(Direction.DOWN, DOWN_SHAPE);
    });;
    protected static final Direction[] DIRECTIONS = Direction.values();
    private final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public WallpaperBlock(DyeColor color) {
        super(FabricBlockSettings.of(Material.WOOD, color).hardness(.5f).sounds(BlockSoundGroup.WOOD));
        SHAPES = this.getShapesForStates(WallpaperBlock::getShapeForState);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES.get(state);
    }

    private static VoxelShape getShapeForState(BlockState state) {
        VoxelShape voxelShape = VoxelShapes.empty();

        for (Direction direction : DIRECTIONS) {
            if (hasDirection(state, direction)) {
                voxelShape = VoxelShapes.union(voxelShape, SHAPES_FOR_DIRECTIONS.get(direction));
            }
        }

        return voxelShape.isEmpty() ? VoxelShapes.fullCube() : voxelShape;
    }

    private static boolean hasDirection(BlockState state, Direction direction) {
        BooleanProperty booleanProperty = getProperty(direction);
        return state.contains(booleanProperty) && state.get(booleanProperty);
    }

    @Override
    protected boolean canSpread(BlockState state, BlockView world, BlockPos pos, Direction from) {
        return false;
    }
}
