package com.github.creoii.greatbigworld.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.potion.PotionUtil;
import net.minecraft.registry.Registries;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SulfurBlock extends Block {
    public static final IntProperty EFFECT = IntProperty.of("effect", 1, Integer.MAX_VALUE);

    public SulfurBlock() {
        super(FabricBlockSettings.copy(Blocks.YELLOW_CONCRETE_POWDER));
        setDefaultState(getDefaultState().with(EFFECT, 1));
    }

    public static StatusEffect getEffectFromProperty(int i) {
        return Registries.STATUS_EFFECT.get(i);
    }

    public static int getPropertyFromEffect(StatusEffect effect) {
        return Registries.STATUS_EFFECT.getRawId(effect);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        if (projectile instanceof PotionEntity potionEntity) {
            StatusEffectInstance effect = PotionUtil.getPotion(potionEntity.getStack()).getEffects().get(0);
            if (effect != null) {
                world.setBlockState(hit.getBlockPos(), state.with(EFFECT, getPropertyFromEffect(effect.getEffectType())), 3);
            }
        }
        super.onProjectileHit(world, state, hit, projectile);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(world, pos.getX(), pos.getY(), pos.getZ());
        areaEffectCloudEntity.setRadius(1.5f);
        areaEffectCloudEntity.setRadiusOnUse(-.5f);
        areaEffectCloudEntity.setWaitTime(0);
        areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
        areaEffectCloudEntity.setPotion(Registries.POTION.get(Registries.STATUS_EFFECT.getId(getEffectFromProperty(state.get(EFFECT)))));
        world.spawnEntity(areaEffectCloudEntity);
        super.onBreak(world, pos, state, player);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(EFFECT);
    }
}
