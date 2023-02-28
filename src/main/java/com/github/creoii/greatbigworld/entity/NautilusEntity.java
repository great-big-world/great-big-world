package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.main.registry.GameEventRegistry;
import com.github.creoii.greatbigworld.main.registry.ItemRegistry;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

import java.util.*;

public class NautilusEntity extends FishEntity {
    private static final Map<Block, Block> NAUTILUS_OXIDIZABLES = ImmutableMap.<Block, Block>builder()
            .put(Blocks.COPPER_BLOCK, Blocks.WEATHERED_COPPER)
            .put(Blocks.WEATHERED_COPPER, Blocks.EXPOSED_COPPER)
            .put(Blocks.EXPOSED_COPPER, Blocks.OXIDIZED_COPPER)
            .put(Blocks.CUT_COPPER, Blocks.WEATHERED_CUT_COPPER)
            .put(Blocks.WEATHERED_CUT_COPPER, Blocks.EXPOSED_CUT_COPPER)
            .put(Blocks.EXPOSED_CUT_COPPER, Blocks.OXIDIZED_CUT_COPPER)
            .put(Blocks.PRISMARINE, BlockRegistry.ELDER_PRISMARINE)
            .put(Blocks.PRISMARINE_BRICKS, BlockRegistry.ELDER_PRISMARINE_BRICKS)
            .put(Blocks.SEA_LANTERN, BlockRegistry.ELDER_SEA_LANTERN)
            .build();
    private int oxidizeTimer;
    private NautilusOxidizeGoal oxidizeGoal;

    public NautilusEntity(EntityType<? extends NautilusEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createNautilusAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 16d).add(EntityAttributes.GENERIC_ARMOR, 4d);
    }

    @Override
    protected void initGoals() {
        goalSelector.add(0, new EscapeDangerGoal(this, 1.5d));
        goalSelector.add(2, new FleeEntityGoal<>(this, PlayerEntity.class, 8f, 1.5d, 1.75d, EntityPredicates.EXCEPT_SPECTATOR::test));
        goalSelector.add(3, oxidizeGoal = new NautilusOxidizeGoal(this));
        goalSelector.add(4, new SwimAroundGoal(this, 1d, 40));
    }

    @Override
    protected void mobTick() {
        oxidizeTimer = oxidizeGoal.getTimer();
        super.mobTick();
    }

    @Override
    public void tickMovement() {
        if (world.isClient) {
            if (oxidizeTimer > 0)  --oxidizeTimer;
        }
        super.tickMovement();
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_PUFFER_FISH_FLOP;
    }

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(ItemRegistry.NAUTILUS_BUCKET);
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height * .35f;
    }

    @SuppressWarnings("deprecation")
    public static boolean canSpawn(EntityType<? extends WaterCreatureEntity> type, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        return pos.getY() <= world.getSeaLevel() - 17 && world.getBlockState(pos).isOf(Blocks.WATER);
    }

    public void onOxidizing() {
        emitGameEvent(GameEventRegistry.NAUTILUS_OXIDIZE);
    }

    public static class NautilusOxidizeGoal extends Goal {
        private final NautilusEntity nautilus;
        private final World world;
        private int timer;

        public NautilusOxidizeGoal(NautilusEntity nautilus) {
            this.nautilus = nautilus;
            world = nautilus.world;
            setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK, Goal.Control.JUMP));
        }

        @Override
        public boolean canStart() {
            if (nautilus.getRandom().nextInt(100) != 0) {
                return false;
            }
            return NAUTILUS_OXIDIZABLES.containsKey(world.getBlockState(nautilus.getBlockPos().down()).getBlock());
        }

        @Override
        public void start() {
            timer = getTickCount(40);
            world.sendEntityStatus(nautilus, EntityStatuses.SET_SHEEP_EAT_GRASS_TIMER_OR_PRIME_TNT_MINECART);
            nautilus.getNavigation().stop();
        }

        @Override
        public void stop() {
            timer = 0;
        }

        @Override
        public boolean shouldContinue() {
            return timer > 0;
        }

        public int getTimer() {
            return timer;
        }

        @Override
        public void tick() {
            timer = Math.max(0, timer - 1);
            if (timer != getTickCount(4)) {
                return;
            }
            BlockPos blockPos = nautilus.getBlockPos().down();
            Block block = world.getBlockState(blockPos).getBlock();
            if (NAUTILUS_OXIDIZABLES.containsKey(block)) {
                if (world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                    world.setBlockState(blockPos, NAUTILUS_OXIDIZABLES.get(block).getDefaultState());
                }
                nautilus.onOxidizing();
            }
        }
    }
}
