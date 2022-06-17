package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.item.SpawnEggItem;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Foods;
import com.github.creoii.greatbigworld.main.util.ItemUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
    //region Cavier Caves
    public static final Item QUICKSAND_BUCKET = new BucketItem(Fluids.EMPTY, new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item MALACHITE = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, getDefaultStack(), Items.AMETHYST_SHARD);
        }
    };
    public static final Item TOPAZ = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, getDefaultStack(), Items.AMETHYST_SHARD);
        }
    };
    //endregion

    //region Change The World
    public static final Item GRASSY_STONE = new BlockItem(BlockRegistry.GRASSY_STONE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item GRASSY_DEEPSLATE = new BlockItem(BlockRegistry.GRASSY_DEEPSLATE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));

    public static final Item MAHOGANY_LEAVES = new BlockItem(BlockRegistry.MAHOGANY_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.FLOWERING_AZALEA_LEAVES);
        }
    };
    //endregion

    //region Colormatic
    public static final Item HANGING_OAK_LEAVES = new BlockItem(BlockRegistry.HANGING_OAK_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item HANGING_SPRUCE_LEAVES = new BlockItem(BlockRegistry.HANGING_SPRUCE_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item HANGING_BIRCH_LEAVES = new BlockItem(BlockRegistry.HANGING_BIRCH_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item HANGING_JUNGLE_LEAVES = new BlockItem(BlockRegistry.HANGING_JUNGLE_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item HANGING_DARK_OAK_LEAVES = new BlockItem(BlockRegistry.HANGING_DARK_OAK_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item HANGING_ACACIA_LEAVES = new BlockItem(BlockRegistry.HANGING_ACACIA_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item HANGING_MANGROVE_LEAVES = new BlockItem(BlockRegistry.HANGING_MANGROVE_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item HANGING_MAHOGANY_LEAVES = new BlockItem(BlockRegistry.HANGING_MAHOGANY_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item BUTTERFLY_SPAWN_EGG = new SpawnEggItem(EntityRegistry.BUTTERFLY, 0, 0, new FabricItemSettings().group(ItemGroup.MISC));
    //endregion

    //region Cornucopia
    public static final Item COPPER_NUGGET = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, getDefaultStack(), Items.IRON_NUGGET);
        }
    };
    //endregion

    //region Honeycomb
    public static final Item BEAR_SPAWN_EGG = new SpawnEggItem(EntityRegistry.BEAR, 0, 0, new FabricItemSettings().group(ItemGroup.MISC));
    //endregion

    //region Magic V Melee
    public static final Item SOUR_BERRIES = new AliasedBlockItem(BlockRegistry.SOUR_BERRY_BUSH, new FabricItemSettings().food(Foods.SOUR_BERRIES).group(ItemGroup.FOOD));
    public static final Item BITTER_BERRIES = new AliasedBlockItem(BlockRegistry.BITTER_BERRY_BUSH, new FabricItemSettings().food(Foods.SOUR_BERRIES).group(ItemGroup.FOOD));
    public static final Item PUNGENT_BERRIES = new AliasedBlockItem(BlockRegistry.PUNGENT_BERRY_BUSH, new FabricItemSettings().food(Foods.SOUR_BERRIES).group(ItemGroup.FOOD));
    //endregion

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "quicksand_bucket"), QUICKSAND_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "malachite"), MALACHITE);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "topaz"), TOPAZ);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "grassy_stone"), GRASSY_STONE);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "grassy_deepslate"), GRASSY_DEEPSLATE);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "mahogany_leaves"), MAHOGANY_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "hanging_oak_leaves"), HANGING_OAK_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "hanging_spruce_leaves"), HANGING_SPRUCE_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "hanging_birch_leaves"), HANGING_BIRCH_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "hanging_jungle_leaves"), HANGING_JUNGLE_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "hanging_dark_oak_leaves"), HANGING_DARK_OAK_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "hanging_acacia_leaves"), HANGING_ACACIA_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "hanging_mangrove_leaves"), HANGING_MANGROVE_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "hanging_mahogany_leaves"), HANGING_MAHOGANY_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "butterfly_spawn_egg"), BUTTERFLY_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "copper_nugget"), COPPER_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "bear_spawn_egg"), BEAR_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "sour_berries"), SOUR_BERRIES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "bitter_berries"), BITTER_BERRIES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "pungent_berries"), PUNGENT_BERRIES);
    }

    @Environment(EnvType.CLIENT)
    public static void tintItems() {
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> GrassColors.getColor(.5d, 1d), GRASSY_STONE, GRASSY_DEEPSLATE);
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> FoliageColors.getDefaultColor(), MAHOGANY_LEAVES, HANGING_OAK_LEAVES, HANGING_JUNGLE_LEAVES, HANGING_ACACIA_LEAVES, HANGING_DARK_OAK_LEAVES, HANGING_MANGROVE_LEAVES, HANGING_MAHOGANY_LEAVES);
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> FoliageColors.getSpruceColor(), HANGING_SPRUCE_LEAVES);
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> FoliageColors.getBirchColor(), HANGING_BIRCH_LEAVES);
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> FoliageColors.method_43717(), HANGING_MANGROVE_LEAVES);
    }
}
