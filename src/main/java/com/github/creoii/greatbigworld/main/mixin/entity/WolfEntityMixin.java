package com.github.creoii.greatbigworld.main.mixin.entity;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.GBWEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.WolfEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Predicate;

@Mixin(WolfEntity.class)
public class WolfEntityMixin {
    @Mutable @Shadow @Final public static Predicate<LivingEntity> FOLLOW_TAMED_PREDICATE;

    static {
        FOLLOW_TAMED_PREDICATE = entity -> {
            EntityType<?> entityType = entity.getType();
            return entityType == EntityType.SHEEP || entityType == EntityType.RABBIT || entityType == EntityType.FOX || (GreatBigWorld.CONFIG.wolvesAttackMoose && entityType == GBWEntityTypes.MOOSE);
        };
    }
}
