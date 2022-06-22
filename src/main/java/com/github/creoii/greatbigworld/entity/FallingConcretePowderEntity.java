package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.block.LayerConcreteBlock;
import com.github.creoii.greatbigworld.block.LayerConcretePowderBlock;
import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AutomaticItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameRules;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class FallingConcretePowderEntity extends Entity {
    public int timeFalling;
    private int layers;
    public boolean shouldDropItem = true;
    protected static final TrackedData<BlockPos> BLOCK_POS = DataTracker.registerData(FallingConcretePowderEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);
    private static final TrackedData<Integer> LAYERS = DataTracker.registerData(FallingConcretePowderEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private BlockState fallState;
    //private final EntitySize size;

    public FallingConcretePowderEntity(EntityType<? extends FallingConcretePowderEntity> entity, World world) {
        super(entity, world);
        layers = 1;
    }

    public FallingConcretePowderEntity(World world, double x, double y, double z, int layers, BlockState state) {
        super(EntityRegistry.FALLING_CONCRETE, world);
        this.setPosition(x, y, z);
        this.setVelocity(Vec3d.ZERO);
        this.intersectionChecked = true;
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.layers = layers;
        this.setData(getBlockPos(), layers);
        if (state.getBlock() instanceof ConcretePowderBlock) {
            fallState = LayerConcretePowderBlock.POWDER_TO_LAYERED.get(state.getBlock()).getDefaultState();
        }
        else fallState = state;
        //size = new EntitySize(0.98f, 0.1225f * layers, true);
    }

    public static void spawnFromBlock(World world, BlockPos pos, BlockState state) {
        if (state.getBlock() instanceof ConcretePowderBlock) {
            state = LayerConcretePowderBlock.POWDER_TO_LAYERED.get(state.getBlock()).getDefaultState();
        }

        FallingConcretePowderEntity fallingBlockEntity = new FallingConcretePowderEntity(world, (double)pos.getX() + .5d, pos.getY(), (double)pos.getZ() + .5d, state.get(LayerConcretePowderBlock.LAYERS), state.contains(Properties.WATERLOGGED) ? state.with(Properties.WATERLOGGED, false) : state);
        world.setBlockState(pos, state.getFluidState().getBlockState(), 3);
        world.spawnEntity(fallingBlockEntity);
    }

    public void tick() {
        if (fallState.isAir() || !(fallState.getBlock() instanceof LayerConcretePowderBlock || fallState.getBlock() instanceof ConcretePowderBlock)) {
            discard();
        } else {
            Block block;
            if (fallState.getBlock() instanceof ConcretePowderBlock) block = LayerConcretePowderBlock.POWDER_TO_LAYERED.get(fallState.getBlock());
            else block = fallState.getBlock();
            if (timeFalling++ == 0) {
                BlockPos blockpos = getBlockPos();
                if (world.getBlockState(blockpos).isOf(block)) {
                    world.removeBlock(blockpos, false);
                } else if (!world.isClient) {
                    return;
                }
            }

            if (!hasNoGravity()) {
                setVelocity(getVelocity().add(0d, -.04d, 0d));
            }

            move(MovementType.SELF, getVelocity());
            if (!world.isClient) {
                BlockPos blockpos1 = getBlockPos();
                boolean flag = fallState.getBlock() instanceof ConcretePowderBlock;
                boolean flag1 = flag && world.getFluidState(blockpos1).isIn(FluidTags.WATER);
                double d0 = getVelocity().lengthSquared();
                if (flag && d0 > 1.0D) {
                    BlockHitResult hit = world.raycast(new RaycastContext(new Vec3d(prevX, prevY, prevZ), getPos(), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.SOURCE_ONLY, this));
                    if (hit.getType() != HitResult.Type.MISS && world.getFluidState(hit.getBlockPos()).isIn(FluidTags.WATER)) {
                        blockpos1 = hit.getBlockPos();
                        flag1 = true;
                    }
                }

                if (!onGround && !flag1) {
                    if (!world.isClient && (timeFalling > 100 && (blockpos1.getY() < 1 || blockpos1.getY() > 256) || timeFalling > 600)) {
                        if (world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                            dropItem(block);
                        }

                        discard();
                    }
                } else {
                    BlockState hitState = world.getBlockState(blockpos1);
                    System.out.println(Registry.BLOCK.getId(hitState.getBlock()));
                    setVelocity(getVelocity().multiply(.7d, -.5d, .7d));
                    if (!hitState.isOf(Blocks.MOVING_PISTON)) {
                        discard();
                        boolean flag2 = hitState.canReplace(new AutomaticItemPlacementContext(world, blockpos1, Direction.DOWN, ItemStack.EMPTY, Direction.UP));
                        boolean flag3 = LayerConcretePowderBlock.canFallThrough(world.getBlockState(blockpos1.down()));
                        boolean flag4 = fallState.canPlaceAt(world, blockpos1) && !flag3;
                        if ((flag2 || (hitState.getBlock() instanceof LayerConcretePowderBlock || hitState.getBlock() instanceof LayerConcreteBlock)) && flag4) {
                            if (fallState.contains(Properties.WATERLOGGED) && world.getFluidState(blockpos1).getFluid() == Fluids.WATER) {
                                fallState = fallState.with(Properties.WATERLOGGED, true);
                            }

                            if (hitState.getBlock() instanceof LayerConcretePowderBlock) {
                                if (((LayerConcretePowderBlock) block).color == ((LayerConcretePowderBlock) hitState.getBlock()).color) {
                                    shouldDropItem = false;
                                    if (hitState.get(LayerConcretePowderBlock.LAYERS) == 8)
                                        world.setBlockState(blockpos1.up(), fallState, 3);

                                    else {
                                        int totalLayers = hitState.get(LayerConcretePowderBlock.LAYERS) + this.fallState.get(LayerConcretePowderBlock.LAYERS);

                                        if (totalLayers <= 8)
                                            world.setBlockState(blockpos1, hitState.with(LayerConcretePowderBlock.LAYERS, totalLayers), 3);
                                        else {
                                            world.setBlockState(blockpos1, fallState.with(LayerConcretePowderBlock.LAYERS, 8), 3);
                                            world.setBlockState(blockpos1.up(), fallState.with(LayerConcretePowderBlock.LAYERS, totalLayers - 8), 3);
                                        }
                                    }
                                } else shouldDropItem = true;
                            } else if (hitState.getBlock() instanceof LayerConcreteBlock) {
                                if (((LayerConcretePowderBlock) block).color == ((LayerConcretePowderBlock) hitState.getBlock()).color) {
                                    shouldDropItem = false;
                                    if (hitState.get(LayerConcreteBlock.WATERLOGGED) && hitState.get(LayerConcreteBlock.LAYERS) < 7) {
                                        int totalLayers = hitState.get(LayerConcreteBlock.LAYERS) + fallState.get(LayerConcretePowderBlock.LAYERS);

                                        if (totalLayers <= 8)
                                            world.setBlockState(blockpos1, hitState.with(LayerConcreteBlock.LAYERS, totalLayers).with(LayerConcreteBlock.WATERLOGGED, totalLayers < 8), 3);
                                        else {
                                            world.setBlockState(blockpos1, hitState.with(LayerConcreteBlock.LAYERS, 8).with(LayerConcreteBlock.WATERLOGGED, false), 3);
                                            world.setBlockState(blockpos1.up(), fallState.with(LayerConcretePowderBlock.LAYERS, totalLayers - 8).with(LayerConcretePowderBlock.WATERLOGGED, false), 3);
                                        }
                                    }
                                } else shouldDropItem = true;
                            } else if (!(hitState.getBlock() instanceof LayerConcretePowderBlock)) {
                                if (world.setBlockState(blockpos1, fallState, 3)) {
                                    ((LayerConcretePowderBlock) block).onLanding(world, blockpos1, fallState, hitState, this);
                                }
                            } else if (shouldDropItem && world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS) && layers == 8) {
                                this.dropItem(block);
                            }
                        } else if (shouldDropItem && world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS) && layers == 8) {
                            dropItem(block);
                        }
                    }
                }
            }
            setVelocity(getVelocity().multiply(.98d));
        }
    }

    public void setData(BlockPos pos, int layers) {
        dataTracker.set(BLOCK_POS, pos);
        dataTracker.set(LAYERS, layers);
    }

    public BlockPos getFallingBlockPos() {
        return dataTracker.get(BLOCK_POS);
    }

    public int getLayers() {
        return dataTracker.get(LAYERS);
    }

    public boolean collides() {
        return !isRemoved();
    }

    @Override
    public boolean doesRenderOnFire() {
        return false;
    }

    protected MoveEffect getMoveEffect() {
        return MoveEffect.NONE;
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    protected void initDataTracker() {
        dataTracker.startTracking(BLOCK_POS, BlockPos.ORIGIN);
        dataTracker.startTracking(LAYERS, 1);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        BlockState state = NbtHelper.toBlockState(nbt.getCompound("BlockState"));
        if (state.getBlock() instanceof LayerConcretePowderBlock) fallState = state;
        else if (state.getBlock() instanceof ConcretePowderBlock) fallState = LayerConcretePowderBlock.POWDER_TO_LAYERED.get(state.getBlock()).getDefaultState();
        else fallState = BlockRegistry.WHITE_CONCRETE_POWDER.getDefaultState();
        timeFalling = nbt.getInt("Time");
        if (nbt.contains("Layers", NbtElement.INT_TYPE)) {
            layers = nbt.getInt("Layers");
        }
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.put("BlockState", NbtHelper.fromBlockState(fallState));
        nbt.putInt("Time", timeFalling);
        nbt.putInt("Layers", layers);
    }

    public BlockState getBlockState() {
        return fallState;
    }

    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this, Block.getRawIdFromState(getBlockState()));
    }

    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        fallState = Block.getStateFromRawId(packet.getEntityData());
        intersectionChecked = true;
        double d = packet.getX();
        double e = packet.getY();
        double f = packet.getZ();
        setPosition(d, e + (double)((1.0F - getHeight()) / 2.0F), f);
    }
}