package com.github.creoii.greatbigworld.item;

import com.github.creoii.greatbigworld.main.util.Foods;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.LightBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ShroomlightFruitItem extends Item {
    private final ShroomlightType type;
    private BlockPos prevLightPos = null;

    public ShroomlightFruitItem(ShroomlightType type) {
        super(new FabricItemSettings().group(ItemGroup.FOOD).food(Foods.SHROOMLIGHT_FRUIT));
        this.type = type;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            BlockPos place = null;
            if (type == ShroomlightType.CRIMSON) {
                place = player.getBlockPos();
            } else if (type == ShroomlightType.WARPED) {
                HitResult hitResult = player.raycast(player.isCreative() ? 5d : 4.5d, 0f, true);
                if (hitResult.getType() == HitResult.Type.BLOCK) {
                    place = new BlockPos(hitResult.getPos());
                }
            } else if (type == ShroomlightType.TWISTED) {
                place = BlockPos.iterateRandomly(world.getRandom(), 1, player.getBlockPos(), 5).iterator().next();
            }

            if (createLight(world, place, type) && prevLightPos != null && world.getBlockState(prevLightPos).isOf(Blocks.LIGHT)) {
                world.setBlockState(prevLightPos, Blocks.AIR.getDefaultState(), 3);
            }

            prevLightPos = place;
        }
        return super.finishUsing(stack, world, user);
    }

    private static boolean createLight(World world, BlockPos pos, ShroomlightType type) {
        FluidState fluidState = world.getFluidState(pos);
        if (world.getBlockState(pos).isAir() || fluidState.isOf(Fluids.WATER)) {
            return world.setBlockState(pos, Blocks.LIGHT.getDefaultState().with(LightBlock.LEVEL_15, type.getLight()).with(LightBlock.WATERLOGGED, fluidState.isOf(Fluids.WATER)), 3);
        }
        return false;
    }

    public enum ShroomlightType {
        CRIMSON(14),
        WARPED(10),
        TWISTED(6);

        private final int light;

        ShroomlightType(int light) {
            this.light = light;
        }

        public int getLight() {
            return light;
        }
    }
}
