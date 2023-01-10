package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import com.github.creoii.greatbigworld.main.registry.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class StoneArrowEntity extends PersistentProjectileEntity {
    public StoneArrowEntity(EntityType<? extends StoneArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public StoneArrowEntity(double x, double y, double z, World world) {
        super(EntityRegistry.STONE_ARROW, x, y, z, world);
    }

    public StoneArrowEntity(LivingEntity owner, World world) {
        super(EntityRegistry.STONE_ARROW, owner, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ItemRegistry.STONE_ARROW);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.getArmorItems().forEach(stack -> {
            if (stack.isDamageable()) {
                stack.damage(random.nextInt(3) + 1, random, null);
            }
        });
    }
}
