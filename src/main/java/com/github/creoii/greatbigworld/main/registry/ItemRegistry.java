package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.item.DaggerItem;
import com.github.creoii.greatbigworld.item.RelicItem;
import com.github.creoii.greatbigworld.item.ShroomlightFruitItem;
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
    public static final Item QUICKSAND_BUCKET = new BucketItem(Fluids.EMPTY, new FabricItemSettings().group(ItemGroup.MISC).maxCount(1));
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
    public static final Item HYENA_SPAWN_EGG = new SpawnEggItem(EntityRegistry.HYENA, 1, 1, new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item ZEBRA_SPAWN_EGG = new SpawnEggItem(EntityRegistry.ZEBRA, 1, 1, new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item WRAPPED_SPAWN_EGG = new SpawnEggItem(EntityRegistry.WRAPPED, 1, 1, new FabricItemSettings().group(ItemGroup.MISC));

    public static final Item GRASSY_STONE = new BlockItem(BlockRegistry.GRASSY_STONE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item GRASSY_DEEPSLATE = new BlockItem(BlockRegistry.GRASSY_DEEPSLATE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));

    public static final Item VENISON = new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(Foods.VENISON));
    public static final Item DEER_HIDE = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));

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
    public static final Item PEAT = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    //endregion

    //region Cloak And Dagger
    public static final Item WOODEN_DAGGER = new DaggerItem(ToolMaterials.WOOD, 3, -1f);
    public static final Item STONE_DAGGER = new DaggerItem(ToolMaterials.STONE, 3, -1f);
    public static final Item IRON_DAGGER = new DaggerItem(ToolMaterials.IRON, 3, -1f);
    public static final Item GOLD_DAGGER = new DaggerItem(ToolMaterials.GOLD, 3, -1f);
    public static final Item DIAMOND_DAGGER = new DaggerItem(ToolMaterials.DIAMOND, 3, -1f);
    public static final Item NETHERITE_DAGGER = new DaggerItem(ToolMaterials.NETHERITE, 3, -1f);
    //endregion

    //region Honeycomb
    //public static final Item BEAR_SPAWN_EGG = new SpawnEggItem(EntityRegistry.BEAR, 0, 0, new FabricItemSettings().group(ItemGroup.MISC));
    //endregion

    //region High Seas
    public static final Item CHEESE = new Item(new FabricItemSettings().food(Foods.CHEESE).group(ItemGroup.FOOD));
    public static final Item MOLDY_CHEESE = new Item(new FabricItemSettings().food(Foods.MOLDY_CHEESE).group(ItemGroup.FOOD));
    //endregion

    //region Magic V Melee
    public static final Item SOUR_BERRIES = new AliasedBlockItem(BlockRegistry.SOUR_BERRY_BUSH, new FabricItemSettings().food(Foods.BERRIES).group(ItemGroup.FOOD));
    public static final Item BITTER_BERRIES = new AliasedBlockItem(BlockRegistry.BITTER_BERRY_BUSH, new FabricItemSettings().food(Foods.BERRIES).group(ItemGroup.FOOD));
    public static final Item PUNGENT_BERRIES = new AliasedBlockItem(BlockRegistry.PUNGENT_BERRY_BUSH, new FabricItemSettings().food(Foods.BERRIES).group(ItemGroup.FOOD));
    //endregion

    //region Pet And Cattle
    public static final Item HEARTBEET = new Item(new FabricItemSettings().food(Foods.HEARTBEET).group(ItemGroup.FOOD));
    //endregion

    //region Twisted Nether
    public static final Item CRIMSON_SHROOMLIGHT_FRUIT = new ShroomlightFruitItem(ShroomlightFruitItem.ShroomlightType.CRIMSON);
    public static final Item WARPED_SHROOMLIGHT_FRUIT = new ShroomlightFruitItem(ShroomlightFruitItem.ShroomlightType.WARPED);
    public static final Item TWISTED_SHROOMLIGHT_FRUIT = new ShroomlightFruitItem(ShroomlightFruitItem.ShroomlightType.TWISTED);
    //endregion

    //region Venture N Voyage
    public static final Item RAGS = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    //endregion

    //region Seecret
    public static final Item PICKAXE_RELIC = new RelicItem(RelicItem.GearType.PICKAXE);
    public static final Item AXE_RELIC = new RelicItem(RelicItem.GearType.AXE);
    public static final Item SHOVEL_RELIC = new RelicItem(RelicItem.GearType.SHOVEL);
    public static final Item HOE_RELIC = new RelicItem(RelicItem.GearType.HOE);
    public static final Item SWORD_RELIC = new RelicItem(RelicItem.GearType.SWORD);
    public static final Item HELMET_RELIC = new RelicItem(RelicItem.GearType.HELMET);
    public static final Item CHESTPLATE_RELIC = new RelicItem(RelicItem.GearType.CHESTPLATE);
    public static final Item LEGGINGS_RELIC = new RelicItem(RelicItem.GearType.LEGGINGS);
    public static final Item BOOTS_RELIC = new RelicItem(RelicItem.GearType.BOOTS);
    //endregion

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "quicksand_bucket"), QUICKSAND_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "malachite"), MALACHITE);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "topaz"), TOPAZ);

        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "hyena_spawn_egg"), HYENA_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "zebra_spawn_egg"), ZEBRA_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "wrapped_spawn_egg"), WRAPPED_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "grassy_stone"), GRASSY_STONE);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "grassy_deepslate"), GRASSY_DEEPSLATE);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "venison"), VENISON);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "deer_hide"), DEER_HIDE);
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
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "peat"), PEAT);

        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "wooden_dagger"), WOODEN_DAGGER);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "stone_dagger"), STONE_DAGGER);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "iron_dagger"), IRON_DAGGER);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "gold_dagger"), GOLD_DAGGER);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "diamond_dagger"), DIAMOND_DAGGER);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "netherite_dagger"), NETHERITE_DAGGER);

        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "cheese"), CHEESE);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "moldy_cheese"), MOLDY_CHEESE);

        //Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "bear_spawn_egg"), BEAR_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "sour_berries"), SOUR_BERRIES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "bitter_berries"), BITTER_BERRIES);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "pungent_berries"), PUNGENT_BERRIES);

        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "heartbeet"), HEARTBEET);

        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "crimson_shroomlight_fruit"), CRIMSON_SHROOMLIGHT_FRUIT);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "warped_shroomlight_fruit"), WARPED_SHROOMLIGHT_FRUIT);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "twisted_shroomlight_fruit"), TWISTED_SHROOMLIGHT_FRUIT);

        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "rags"), RAGS);

        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "pickaxe_relic"), PICKAXE_RELIC);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "axe_relic"), AXE_RELIC);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "shovel_relic"), SHOVEL_RELIC);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "hoe_relic"), HOE_RELIC);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "sword_relic"), SWORD_RELIC);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "helmet_relic"), HELMET_RELIC);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "chestplate_relic"), CHESTPLATE_RELIC);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "leggings_relic"), LEGGINGS_RELIC);
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, "boots_relic"), BOOTS_RELIC);
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
