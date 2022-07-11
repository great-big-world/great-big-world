package com.github.creoii.greatbigworld.main.mixin.block;

import com.github.creoii.greatbigworld.main.registry.EnchantmentRegistry;
import com.github.creoii.greatbigworld.main.util.Tags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RedstoneWireBlock.class)
public class RedstoneWireBlockMixin extends Block {
    @Shadow @Final public static IntProperty POWER;

    public RedstoneWireBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);

        if (entity instanceof LivingEntity living) {
            ItemStack feet = living.getEquippedStack(EquipmentSlot.FEET);
            if (feet != null) {
                int level = EnchantmentHelper.getLevel(EnchantmentRegistry.POWERED, feet) * 3;
                if (level > 0) {
                    world.setBlockState(pos, state.with(POWER, Math.max(level, state.get(POWER) + level)), 3);
                    state.updateNeighbors(world, pos, 3);
                }
            }
        }
    }
}
