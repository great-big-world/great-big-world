package com.github.creoii.greatbigworld.item;

import com.github.creoii.greatbigworld.entity.StoneArrowEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class StoneArrowItem extends ArrowItem {
    public StoneArrowItem() {
        super(new FabricItemSettings());
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        return new StoneArrowEntity(shooter, world);
    }
}
