package com.github.creoii.greatbigworld.item;

import com.github.creoii.greatbigworld.main.util.Foods;
import com.github.creoii.greatbigworld.main.util.GenerationUtil;
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

    public ShroomlightFruitItem(ShroomlightType type) {
        super(new FabricItemSettings().group(ItemGroup.FOOD).food(Foods.SHROOMLIGHT_FRUIT));
        this.type = type;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            if (type == ShroomlightType.CRIMSON) {
                createLight(world, player.getBlockPos(), type);
            } else if (type == ShroomlightType.WARPED) {
                HitResult hitResult = player.raycast(player.isCreative() ? 5d : 4.5d, 0f, true);
                if (hitResult.getType() == HitResult.Type.BLOCK) {
                    //System.out.println(((BlockHitResult) hitResult).getBlockPos().toShortString());
                    createLight(world, ((BlockHitResult) hitResult).getBlockPos(), type);
                }
            } else if (type == ShroomlightType.TWISTED) {
                BlockPos place = GenerationUtil.randomInRange(world.getRandom(), player.getBlockPos(), 5, 3, 5);
                //System.out.println(place.toShortString());
                createLight(world, place, type);
            }
        }
        return super.finishUsing(stack, world, user);
    }

    private static void createLight(World world, BlockPos pos, ShroomlightType type) {
        FluidState fluidState = world.getFluidState(pos);
        if (world.getBlockState(pos).isAir() || fluidState.isOf(Fluids.WATER)) {
            world.setBlockState(pos, Blocks.LIGHT.getDefaultState().with(LightBlock.LEVEL_15, type.getLight()).with(LightBlock.WATERLOGGED, fluidState.isOf(Fluids.WATER)), 3);
        }
    }

    public enum ShroomlightType {
        CRIMSON(15),
        WARPED(10),
        TWISTED(5);

        private final int light;

        ShroomlightType(int light) {
            this.light = light;
        }

        public int getLight() {
            return light;
        }
    }
}
