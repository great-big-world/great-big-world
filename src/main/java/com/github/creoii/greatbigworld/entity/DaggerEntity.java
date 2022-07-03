package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.item.DaggerItem;
import com.github.creoii.greatbigworld.main.registry.EntityRegistry;
import com.github.creoii.greatbigworld.main.util.Tags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.EndGatewayBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Util;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DaggerEntity extends PersistentProjectileEntity implements FlyingItemEntity {
    private static final TrackedData<ItemStack> ITEM = DataTracker.registerData(DaggerEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);

    public DaggerEntity(EntityType<? extends DaggerEntity> entityType, World world) {
        super(entityType, world);
    }

    public DaggerEntity(World world, LivingEntity owner) {
        super(EntityRegistry.DAGGER, owner, world);
    }

    public DaggerEntity(World world, double x, double y, double z) {
        super(EntityRegistry.DAGGER, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return getItem().getItem();
    }

    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = getItem();
        return itemStack.isEmpty() ? ParticleTypes.CRIT : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack);
    }

    public void handleStatus(byte status) {
        if (status == 3) {
            for (int i = 0; i < 8; ++i) {
                world.addParticle(getParticleParameters(), getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (getItem().isIn(Tags.Items.DAGGERS)) {
            entityHitResult.getEntity().damage(DamageSource.thrownProjectile(this, getOwner()), ((DaggerItem) getItem().getItem()).getAttackDamage());
        }
    }

    @Override
    protected ItemStack asItemStack() {
        ItemStack itemStack = getItem();
        return itemStack.isEmpty() ? new ItemStack(getDefaultItem()) : itemStack;
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!world.isClient) {
            world.sendEntityStatus(this, (byte)3);
            discard();
        }
    }

    public void tick() {
        super.tick();
        HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
        boolean bl = false;
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = ((BlockHitResult)hitResult).getBlockPos();
            BlockState blockState = this.world.getBlockState(blockPos);
            if (blockState.isOf(Blocks.NETHER_PORTAL)) {
                setInNetherPortal(blockPos);
                bl = true;
            } else if (blockState.isOf(Blocks.END_GATEWAY)) {
                BlockEntity blockEntity = this.world.getBlockEntity(blockPos);
                if (blockEntity instanceof EndGatewayBlockEntity && EndGatewayBlockEntity.canTeleport(this)) {
                    EndGatewayBlockEntity.tryTeleportingEntity(this.world, blockPos, blockState, this, (EndGatewayBlockEntity)blockEntity);
                }

                bl = true;
            }
        }

        if (hitResult.getType() != HitResult.Type.MISS && !bl) {
            this.onCollision(hitResult);
        }

        checkBlockCollision();
        Vec3d vec3d = this.getVelocity();
        double d = this.getX() + vec3d.x;
        double e = this.getY() + vec3d.y;
        double f = this.getZ() + vec3d.z;
        this.updateRotation();
        float h;
        if (this.isTouchingWater()) {
            for(int i = 0; i < 4; ++i) {
                world.addParticle(ParticleTypes.BUBBLE, d - vec3d.x * 0.25D, e - vec3d.y * 0.25D, f - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
            }

            h = 0.8F;
        } else {
            h = 0.99F;
        }

        setVelocity(vec3d.multiply(h));
        if (!hasNoGravity()) {
            Vec3d vec3d2 = getVelocity();
            setVelocity(vec3d2.x, vec3d2.y - (double)getGravity(), vec3d2.z);
        }

        setPosition(d, e, f);
    }

    protected float getGravity() {
        return .03f;
    }

    public void setItem(ItemStack item) {
        if (!item.isOf(getDefaultItem()) || item.hasNbt()) {
            getDataTracker().set(ITEM, Util.make(item.copy(), stack -> stack.setCount(1)));
        }
    }

    protected ItemStack getItem() {
        return getDataTracker().get(ITEM);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        getDataTracker().startTracking(ITEM, ItemStack.EMPTY);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        ItemStack itemStack = getItem();
        if (!itemStack.isEmpty()) {
            nbt.put("Item", itemStack.writeNbt(new NbtCompound()));
        }

    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        ItemStack itemStack = ItemStack.fromNbt(nbt.getCompound("Item"));
        setItem(itemStack);
    }

    @Override
    public boolean cannotBeSilenced() {
        return super.cannotBeSilenced();
    }

    @Override
    public ItemStack getStack() {
        ItemStack itemStack = getItem();
        return itemStack.isEmpty() ? new ItemStack(getDefaultItem()) : itemStack;
    }
}
