package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Tags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class ButterflyEntity extends PathAwareEntity {
    private static final TrackedData<Byte> BUTTERFLY_FLAGS = DataTracker.registerData(ButterflyEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final TrackedData<Integer> VARIANT = DataTracker.registerData(ButterflyEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final Identifier[] PATTERN_INNER_IDS = new Identifier[]{
            new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/butterfly/inner/pattern_1.png"),
            new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/butterfly/inner/pattern_2.png"),
            new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/butterfly/inner/pattern_3.png"),
            new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/butterfly/inner/pattern_4.png"),
            new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/butterfly/inner/pattern_5.png"),
            new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/butterfly/inner/pattern_6.png")};
    private static final Identifier[] PATTERN_OUTLINE_IDS = new Identifier[]{
            new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/butterfly/outline/pattern_1.png"),
            new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/butterfly/outline/pattern_2.png"),
            new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/butterfly/outline/pattern_3.png"),
            new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/butterfly/outline/pattern_4.png"),
            new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/butterfly/outline/pattern_5.png"),
            new Identifier(GreatBigWorld.NAMESPACE, "textures/entity/butterfly/outline/pattern_6.png")};

    public static final int[] COMMON_VARIANTS = new int[]{
            toVariant(Variety.SUNSTREAK, DyeColor.BLUE, DyeColor.GRAY),
            toVariant(Variety.KOB, DyeColor.ORANGE, DyeColor.WHITE),
            toVariant(Variety.SPOTTY, DyeColor.PINK, DyeColor.LIGHT_BLUE),
            toVariant(Variety.SPOTTY, DyeColor.WHITE, DyeColor.YELLOW),
            toVariant(Variety.DASHER, DyeColor.CYAN, DyeColor.PINK),
            toVariant(Variety.BRINELY, DyeColor.LIME, DyeColor.LIGHT_BLUE),
            toVariant(Variety.SNOOPER, DyeColor.GRAY, DyeColor.RED),
            toVariant(Variety.KOB, DyeColor.RED, DyeColor.WHITE),
            toVariant(Variety.SUNSTREAK, DyeColor.GRAY, DyeColor.WHITE),
            toVariant(Variety.DASHER, DyeColor.CYAN, DyeColor.YELLOW)};
    @Nullable private BlockPos sittingPosition;

    public ButterflyEntity(EntityType<? extends ButterflyEntity> entityType, World world) {
        super(entityType, world);
        moveControl = new FlightMoveControl(this, 15, true);
    }

    private static int toVariant(ButterflyEntity.Variety variety, DyeColor baseColor, DyeColor patternColor) {
        return variety.getShape() & 255 | (variety.getPattern() & 255) << 8 | (baseColor.getId() & 255) << 16 | (patternColor.getId() & 255) << 24;
    }

    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(BUTTERFLY_FLAGS, (byte)0);
        dataTracker.startTracking(VARIANT, 0);
    }

    public static DefaultAttributeContainer.Builder createButterflyAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 6d).add(EntityAttributes.GENERIC_FLYING_SPEED, .10000000149011612d).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .10000000149011612d).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16d);
    }

    public static boolean canSpawn(EntityType<? extends ButterflyEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.isSkyVisible(pos);
    }

    public boolean isPushable() {
        return false;
    }

    protected void pushAway(Entity entity) {
    }

    protected void tickCramming() {
    }

    public boolean isSitting() {
        return (dataTracker.get(BUTTERFLY_FLAGS) & 1) != 0;
    }

    public void setSitting(boolean sitting) {
        byte b = dataTracker.get(BUTTERFLY_FLAGS);
        if (sitting) {
            dataTracker.set(BUTTERFLY_FLAGS, (byte)(b | 1));
        } else {
            dataTracker.set(BUTTERFLY_FLAGS, (byte)(b & -2));
        }
    }

    public void setVariant(int variant) {
        dataTracker.set(VARIANT, variant);
    }

    public int getVariant() {
        return dataTracker.get(VARIANT);
    }

    private static int getPattern(int variant) {
        return Math.min((variant & '\uff00') >> 8, 5);
    }

    public Identifier getPatternOutlineId() {
        return PATTERN_OUTLINE_IDS[getPattern(getVariant())];
    }

    public Identifier getPatternInnerId() {
        return PATTERN_INNER_IDS[getPattern(getVariant())];
    }

    private static int getBaseDyeColorIndex(int variant) {
        return (variant & 16711680) >> 16;
    }

    public float[] getBaseColorComponents() {
        return DyeColor.byId(getBaseDyeColorIndex(getVariant())).getColorComponents();
    }

    private static int getPatternDyeColorIndex(int variant) {
        return (variant & -16777216) >> 24;
    }

    public float[] getPatternColorComponents() {
        return DyeColor.byId(getPatternDyeColorIndex(getVariant())).getColorComponents();
    }

    public void tick() {
        super.tick();
        if (isSitting()) {
            setVelocity(Vec3d.ZERO);
            setPos(getX(), (double) MathHelper.floor(getY()) + 1.0D - (double)getHeight(), getZ());
        }
    }

    public void travel(Vec3d movementInput) {
        if (this.canMoveVoluntarily() || this.isLogicalSideForUpdatingMovement()) {
            if (isTouchingWater()) {
                updateVelocity(.02f, movementInput);
                move(MovementType.SELF, this.getVelocity());
                setVelocity(this.getVelocity().multiply(.800000011920929d));
            } else if (this.isInLava()) {
                updateVelocity(.02f, movementInput);
                move(MovementType.SELF, this.getVelocity());
                setVelocity(this.getVelocity().multiply(.5d));
            } else {
                updateVelocity(this.getMovementSpeed(), movementInput);
                move(MovementType.SELF, this.getVelocity());
                setVelocity(this.getVelocity().multiply(.9100000262260437d));
            }
        }

        updateLimbs(this, false);
    }

    protected void mobTick() {
        super.mobTick();
        BlockPos blockPos = getBlockPos();
        BlockPos blockPos2 = blockPos.up();
        if (isSitting()) {
            boolean bl = isSilent();
            if (world.getBlockState(blockPos2).isIn(Tags.Blocks.BUTTERFLY_SITTABLE)) {
                if (this.random.nextInt(200) == 0) {
                    this.headYaw = (float)this.random.nextInt(360);
                }
            } else {
                this.setSitting(false);
                if (!bl) {
                    this.world.syncWorldEvent(null, 1025, blockPos, 0);
                }
            }
        } else {
            if (this.sittingPosition != null && (!this.world.isAir(this.sittingPosition) || this.sittingPosition.getY() <= this.world.getBottomY())) {
                this.sittingPosition = null;
            }

            if (this.sittingPosition == null || this.random.nextInt(30) == 0 || this.sittingPosition.isWithinDistance(this.getPos(), 2.0D)) {
                this.sittingPosition = new BlockPos(this.getX() + (double)this.random.nextInt(7) - (double)this.random.nextInt(7), this.getY() + (double)this.random.nextInt(6) - 2.0D, this.getZ() + (double)this.random.nextInt(7) - (double)this.random.nextInt(7));
            }

            double d = (double)this.sittingPosition.getX() + .5d - this.getX();
            double e = (double)this.sittingPosition.getY() + .1d - this.getY();
            double f = (double)this.sittingPosition.getZ() + .5d - this.getZ();
            Vec3d vec3d = this.getVelocity();
            Vec3d vec3d2 = vec3d.add((Math.signum(d) * 0.5D - vec3d.x) * 0.10000000149011612D, (Math.signum(e) * 0.699999988079071D - vec3d.y) * 0.10000000149011612D, (Math.signum(f) * 0.5D - vec3d.z) * 0.10000000149011612D);
            this.setVelocity(vec3d2);
            float g = (float)(MathHelper.atan2(vec3d2.z, vec3d2.x) * 57.2957763671875D) - 90f;
            float h = MathHelper.wrapDegrees(g - this.getYaw());
            forwardSpeed = .5f;
            this.setYaw(this.getYaw() + h);
            if (this.random.nextInt(100) == 0 && this.world.getBlockState(blockPos2).isIn(Tags.Blocks.BUTTERFLY_SITTABLE)) {
                this.setSitting(true);
            }
        }
    }

    protected MoveEffect getMoveEffect() {
        return MoveEffect.EVENTS;
    }

    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }

    public boolean canAvoidTraps() {
        return true;
    }

    public boolean damage(DamageSource source, float amount) {
        if (isInvulnerableTo(source)) {
            return false;
        } else {
            if (!world.isClient && isSitting()) {
                setSitting(false);
            }

            return super.damage(source, amount);
        }
    }

    public boolean hasWings() {
        return !isOnGround();
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height / 2f;
    }

    public boolean canBeLeashedBy(PlayerEntity player) {
        return false;
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        dataTracker.set(BUTTERFLY_FLAGS, nbt.getByte("ButterflyFlags"));
        setVariant(nbt.getInt("Variant"));
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putByte("ButterflyFlags", dataTracker.get(BUTTERFLY_FLAGS));
        nbt.putInt("Variant", this.getVariant());
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        Random random = world.getRandom();
        int i;
        int j;
        int k;
        int l;
        if (entityData instanceof ButterflyEntity.ButterflyData butterflyData) {
            i = butterflyData.shape;
            j = butterflyData.pattern;
            k = butterflyData.baseColor;
            l = butterflyData.patternColor;
        } else if ((double)random.nextFloat() < .9d) {
            int m = Util.getRandom(COMMON_VARIANTS, random);
            i = m & 255;
            j = (m & '\uff00') >> 8;
            k = (m & 16711680) >> 16;
            l = (m & -16777216) >> 24;
            entityData = new ButterflyEntity.ButterflyData(i, j, k, l);
        } else {
            i = random.nextInt(2);
            j = random.nextInt(6);
            k = random.nextInt(15);
            l = random.nextInt(15);
        }

        this.setVariant(i | j << 8 | k << 16 | l << 24);
        return entityData;
    }

    private enum Variety {
        KOB(0, 0),
        SUNSTREAK(0, 1),
        SNOOPER(0, 2),
        DASHER(0, 3),
        BRINELY(0, 4),
        SPOTTY(0, 5);

        private final int shape;
        private final int pattern;

        Variety(int shape, int pattern) {
            this.shape = shape;
            this.pattern = pattern;
        }

        public int getShape() {
            return this.shape;
        }

        public int getPattern() {
            return this.pattern;
        }
    }

    private record ButterflyData(int shape, int pattern, int baseColor, int patternColor) implements EntityData {
    }
}
