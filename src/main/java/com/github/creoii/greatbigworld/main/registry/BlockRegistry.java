package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.DefaultBlockSets;
import com.github.creoii.greatbigworld.main.util.ItemUtil;
import com.github.creoii.greatbigworld.main.util.Register;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class BlockRegistry implements Register {
    private static final Map<Block, Block> STRIPPED_BLOCKS = new HashMap<>();
    private static final FireBlock FIRE = (FireBlock) Blocks.FIRE;

    //region Mahogany Wood
    public static DefaultBlockSets.WoodSet MAHOGANY;
    public static final Block MAHOGANY_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
    public static final Block MAHOGANY_SAPLING = new SaplingBlock(new SaplingGenerator() {
        @Nullable @Override
        protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
            return ConfiguredFeatureRegistry.MAHOGANY;
        }
    }, FabricBlockSettings.copy(Blocks.JUNGLE_SAPLING)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_PROPAGULE); }
    };
    public static final Block POTTED_MAHOGANY_SAPLING = new FlowerPotBlock(MAHOGANY_SAPLING, FabricBlockSettings.copy(Blocks.FLOWER_POT));
    public static final SignType MAHOGANY_TYPE = SignType.register(new SignType("mahogany"));
    public static final Block MAHOGANY_SIGN = new SignBlock(AbstractBlock.Settings.of(Material.WOOD).noCollision().strength(1f).sounds(BlockSoundGroup.WOOD), MAHOGANY_TYPE) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_SIGN);
        }
    };
    public static final Block MAHOGANY_WALL_SIGN = new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD).noCollision().strength(1f).sounds(BlockSoundGroup.WOOD).dropsLike(MAHOGANY_SIGN), MAHOGANY_TYPE);
    //endregion

    public void register() {
        MAHOGANY = DefaultBlockSets.createWoodSet(new Identifier(NAMESPACE, "mahogany"), MapColor.TERRACOTTA_BROWN, MapColor.TERRACOTTA_ORANGE);
        registerBlock(new Identifier(NAMESPACE, "mahogany_leaves"), MAHOGANY_LEAVES, null, new ExtendedBlockSettings(.3f, 30, 60, null));
        registerBlock(new Identifier(NAMESPACE, "mahogany_sapling"), MAHOGANY_SAPLING, ItemGroup.DECORATIONS, new ExtendedBlockSettings(.3f, 0, 0, null));
        registerBlock(new Identifier(NAMESPACE, "potted_mahogany_sapling"), POTTED_MAHOGANY_SAPLING, null, null);
        registerBlock(new Identifier(NAMESPACE, "mahogany_sign"), MAHOGANY_SIGN, null, null);
        registerBlock(new Identifier(NAMESPACE, "mahogany_wall_sign"), MAHOGANY_WALL_SIGN, null, null);

        AxeItem.STRIPPED_BLOCKS = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPED_BLOCKS).putAll(STRIPPED_BLOCKS).build();
        BlockEntityType.SIGN.blocks = new ImmutableSet.Builder<Block>().addAll(BlockEntityType.SIGN.blocks).add(MAHOGANY_SIGN).add(MAHOGANY_WALL_SIGN).build();
    }

    @Override
    public void registerClient() {
        RenderLayers.BLOCKS.put(BlockRegistry.MAHOGANY.door(), RenderLayer.getCutout());
        RenderLayers.BLOCKS.put(BlockRegistry.MAHOGANY.trapdoor(), RenderLayer.getCutout());
        RenderLayers.BLOCKS.put(BlockRegistry.MAHOGANY_SAPLING, RenderLayer.getCutout());
        RenderLayers.BLOCKS.put(BlockRegistry.POTTED_MAHOGANY_SAPLING, RenderLayer.getCutout());
        RenderLayers.BLOCKS.put(BlockRegistry.MAHOGANY_LEAVES, RenderLayer.getCutoutMipped());
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(), BlockRegistry.MAHOGANY_LEAVES);
    }

    public static Block registerBlock(Identifier id, Block block, @Nullable ItemGroup group, @Nullable ExtendedBlockSettings extension) {
        Registry.register(Registry.BLOCK, id, block);
        if (group != null) Registry.register(Registry.ITEM, id, new BlockItem(block, new Item.Settings().group(group)));
        if (extension != null) {
            if (extension.compostChance() > 0f)
                ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(block, extension.compostChance());
            if (extension.burnChance() > 0 && extension.spreadChance() > 0)
                FIRE.registerFlammableBlock(block, extension.burnChance(), extension.spreadChance());
            if (extension.strippedBlock() != null)
                BlockRegistry.STRIPPED_BLOCKS.put(block, extension.strippedBlock());
        }
        return block;
    }

    public static record ExtendedBlockSettings(float compostChance, int burnChance, int spreadChance, @Nullable Block strippedBlock) { }
}
