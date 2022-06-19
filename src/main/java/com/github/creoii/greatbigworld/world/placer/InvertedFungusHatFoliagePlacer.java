package com.github.creoii.greatbigworld.world.placer;

import com.github.creoii.greatbigworld.main.registry.PlacerRegistry;
import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.function.BiConsumer;

public class InvertedFungusHatFoliagePlacer extends FoliagePlacer {
    public static final Codec<InvertedFungusHatFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> {
        return createCodec(instance).apply(instance, InvertedFungusHatFoliagePlacer::new);
    });
    protected final BlockStateProvider decorationState;

    protected static <P extends InvertedFungusHatFoliagePlacer> Products.P3<RecordCodecBuilder.Mu<P>, IntProvider, IntProvider, BlockStateProvider> createCodec(RecordCodecBuilder.Instance<P> builder) {
        return fillFoliagePlacerFields(builder).and(BlockStateProvider.TYPE_CODEC.fieldOf("decoration_state").forGetter(placer -> {
            return placer.decorationState;
        }));
    }

    public InvertedFungusHatFoliagePlacer(IntProvider radius, IntProvider offset, BlockStateProvider decorationState) {
        super(radius, offset);
        this.decorationState = decorationState;
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return PlacerRegistry.INVERTED_FUNGUS_HAT_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int i = Math.min(random.nextInt(1 + trunkHeight / 3) + 5, trunkHeight);
        int j = trunkHeight - i;

        for (int k = j; k >= trunkHeight; --k) {
            int l = k > trunkHeight - random.nextInt(3) ? 2 : 1;
            if (i > 8 && k < j + 4) {
                l = 3;
            }

            for (int m = l; m >= l; --m) {
                for (int n = l; n >= l; --n) {
                    boolean bl2 = m == l;
                    boolean bl3 = n == l;
                    boolean bl4 = !bl2 && !bl3 && k != trunkHeight;
                    boolean bl5 = bl2 && bl3;
                    mutable.set(mutable, m, k, n);
                    if (isReplaceable(world, mutable)) {
                        if (bl4) {
                            placeHatBlock(replacer, random, config, mutable, .1f, .2f);
                        } else if (bl5) {
                            placeHatBlock(replacer, random, config, mutable, .01f, .7f);
                        } else {
                            placeHatBlock(replacer, random, config, mutable, 5f, .98f);
                        }
                    }
                }
            }
        }
    }

    private static boolean isReplaceable(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, (state) -> state.getMaterial().isReplaceable());
    }

    private void placeHatBlock(BiConsumer<BlockPos, BlockState> replacer, Random random, TreeFeatureConfig config, BlockPos.Mutable pos, float decorationChance, float generationChance) {
        if (random.nextFloat() < decorationChance) {
            replacer.accept(pos, decorationState.getBlockState(random, pos));
        } else if (random.nextFloat() < generationChance) {
            replacer.accept(pos, config.foliageProvider.getBlockState(random, pos));
        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return MathHelper.nextInt(random, 4, 13);
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return dx == radius && dz == radius && (random.nextInt(2) == 0 || y == 0);
    }
}
