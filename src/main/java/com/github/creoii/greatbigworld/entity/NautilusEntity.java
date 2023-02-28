package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.main.registry.ItemRegistry;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.SwimAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;

import java.util.EnumSet;
import java.util.Map;

public class NautilusEntity extends FishEntity {
    private static final Map<Block, Block> NAUTILUS_OXIDIZABLES = ImmutableMap.<Block, Block>builder()
            .put(Blocks.COPPER_BLOCK, Blocks.EXPOSED_COPPER)
            .put(Blocks.EXPOSED_COPPER, Blocks.WEATHERED_COPPER)
            .put(Blocks.WEATHERED_COPPER, Blocks.OXIDIZED_COPPER)
            .put(Blocks.CUT_COPPER, Blocks.EXPOSED_CUT_COPPER)
            .put(Blocks.EXPOSED_CUT_COPPER, Blocks.WEATHERED_CUT_COPPER)
            .put(Blocks.WEATHERED_CUT_COPPER, Blocks.OXIDIZED_CUT_COPPER)
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
        goalSelector.add(1, new EscapeAttackerGoal());
        goalSelector.add(2, new FleeEntityGoal<>(this, PlayerEntity.class, 10f, 1.5d, 1.25d, EntityPredicates.EXCEPT_SPECTATOR::test));
        goalSelector.add(2, new FleeEntityGoal<>(this, SquidEntity.class, 10f, 1.4d, 1.2d, EntityPredicates.EXCEPT_SPECTATOR::test));
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
    public void tick() {
        super.tick();
        if (random.nextFloat() < .03f && isSubmergedInWater()) {
            Vec3d rotation = getRotationVec(0f);
            if (world.getTime() % 2 == 0) {
                world.addParticle(ParticleTypes.BUBBLE, getX() + (random.nextDouble() - .5d) * (double) getWidth() - rotation.x * .75d, getY() + random.nextDouble() * (double) getHeight() - rotation.y, getZ() + (random.nextDouble() - .5d) * (double) getWidth() - rotation.z * .75d, 0d, 0d, 0d);
            }
        }
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
    public static boolean canSpawn(EntityType<? extends WaterCreatureEntity> type, WorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        return pos.getY() >= world.getBottomY() && pos.getY() <= world.getSeaLevel() - 15 && world.getFluidState(pos).isIn(FluidTags.WATER);
    }

    public void onOxidizing() {
        emitGameEvent(GameEvent.BLOCK_CHANGE);
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
            if (!nautilus.touchingWater) return false;
            if (nautilus.getRandom().nextInt(1200) != 0) return false;
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
            return timer > 0 && nautilus.touchingWater;
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

    private class EscapeAttackerGoal extends Goal {
        private int timer;

        @Override
        public boolean canStart() {
            LivingEntity livingEntity = NautilusEntity.this.getAttacker();
            if (NautilusEntity.this.isTouchingWater() && livingEntity != null) {
                return NautilusEntity.this.squaredDistanceTo(livingEntity) < 100d;
            }
            return false;
        }

        @Override
        public void start() {
            timer = 0;
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            ++timer;
            LivingEntity livingEntity = NautilusEntity.this.getAttacker();
            if (livingEntity == null) {
                return;
            }
            Vec3d vec3d = new Vec3d(NautilusEntity.this.getX() - livingEntity.getX(), NautilusEntity.this.getY() - livingEntity.getY(), NautilusEntity.this.getZ() - livingEntity.getZ());
            BlockState blockState = NautilusEntity.this.world.getBlockState(new BlockPos(NautilusEntity.this.getX() + vec3d.x, NautilusEntity.this.getY() + vec3d.y, NautilusEntity.this.getZ() + vec3d.z));
            FluidState fluidState = NautilusEntity.this.world.getFluidState(new BlockPos(NautilusEntity.this.getX() + vec3d.x, NautilusEntity.this.getY() + vec3d.y, NautilusEntity.this.getZ() + vec3d.z));
            if (fluidState.isIn(FluidTags.WATER) || blockState.isAir()) {
                double d = vec3d.length();
                if (d > 0d) {
                    vec3d.normalize();
                    double e = 3d;
                    if (d > 5d) {
                        e -= (d - 5d) / 5d;
                    }
                    if (e > 0d) {
                        vec3d = vec3d.multiply(e);
                    }
                }
                if (blockState.isAir()) {
                    vec3d = vec3d.subtract(0d, vec3d.y, 0d);
                }
                NautilusEntity.this.setVelocity((float)vec3d.x / 20f, (float)vec3d.y / 20f, (float)vec3d.z / 20f);
            }
            if (timer % 10 == 5) {
                NautilusEntity.this.world.addParticle(ParticleTypes.BUBBLE, NautilusEntity.this.getX(), NautilusEntity.this.getY(), NautilusEntity.this.getZ(), 0d, 0d, 0d);
            }
        }
    }
}
