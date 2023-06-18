package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.item.AuraPotionItem;
import com.github.creoii.greatbigworld.item.WoodenMaskItem;
import com.github.creoii.greatbigworld.main.util.GBWBoatTypes;
import com.github.creoii.greatbigworld.main.util.GBWFoods;
import com.github.creoii.greatbigworld.main.util.ItemUtil;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.main.util.material.WoodenMaskArmorMaterial;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public class GBWItems implements Register {
    //region Mahogany Wood
    public static final Item MAHOGANY_LEAVES = new BlockItem(GBWBlocks.MAHOGANY_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.JUNGLE_LEAVES);
        }
    };
    public static final Item MAHOGANY_SIGN = new SignItem(new FabricItemSettings().group(ItemGroup.DECORATIONS).maxCount(16), GBWBlocks.MAHOGANY_SIGN, GBWBlocks.MAHOGANY_WALL_SIGN) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.JUNGLE_SIGN);
        }
    };
    public static final Item MAHOGANY_BOAT = new BoatItem(false, GBWBoatTypes.MAHOGANY, new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_CHEST_BOAT);
        }
    };
    public static final Item MAHOGANY_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.MAHOGANY, new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_CHEST_BOAT);
        }
    };
    //endregion
    //region Aspen Wood
    public static final Item GREEN_ASPEN_LEAVES = new BlockItem(GBWBlocks.GREEN_ASPEN_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.BIRCH_LEAVES);
        }
    };
    public static final Item GREEN_ASPEN_LEAF_PILE = new BlockItem(GBWBlocks.GREEN_ASPEN_LEAF_PILE, new FabricItemSettings().group(ItemGroup.DECORATIONS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.BIRCH_LEAVES);
        }
    };
    public static final Item ASPEN_SIGN = new SignItem(new FabricItemSettings().group(ItemGroup.DECORATIONS).maxCount(16), GBWBlocks.ASPEN_SIGN, GBWBlocks.ASPEN_WALL_SIGN) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.BIRCH_SIGN);
        }
    };
    public static final Item ASPEN_BOAT = new BoatItem(false, GBWBoatTypes.ASPEN, new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_CHEST_BOAT);
        }
    };
    public static final Item ASPEN_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.ASPEN, new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_CHEST_BOAT);
        }
    };
    //endregion
    //region Acai Wood
    public static final Item ACAI_LEAVES = new BlockItem(GBWBlocks.ACAI_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), MAHOGANY_LEAVES);
        }
    };
    public static final Item HANGING_ACAI_LEAVES = new BlockItem(GBWBlocks.HANGING_ACAI_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), ACAI_LEAVES);
        }
    };
    public static final Item ACAI_SIGN = new SignItem(new FabricItemSettings().group(ItemGroup.DECORATIONS).maxCount(16), GBWBlocks.ACAI_SIGN, GBWBlocks.ACAI_WALL_SIGN) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.JUNGLE_SIGN);
        }
    };
    public static final Item ACAI_BOAT = new BoatItem(false, GBWBoatTypes.ACAI, new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.JUNGLE_BOAT);
        }
    };
    public static final Item ACAI_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.ACAI, new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), ACAI_BOAT);
        }
    };
    public static final Item ACAI_BERRIES = new AliasedBlockItem(GBWBlocks.ACAI_BERRY_CLUMP, new FabricItemSettings().group(ItemGroup.FOOD).food(GBWFoods.ACAI_BERRIES)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.GLOW_BERRIES);
        }
    };
    //region Bamboo Torches
    public static final Item BAMBOO_TORCH = new WallStandingBlockItem(GBWBlocks.BAMBOO_TORCH, GBWBlocks.BAMBOO_WALL_TORCH, new FabricItemSettings().group(ItemGroup.DECORATIONS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.TORCH);
        }
    };
    public static final Item SOUL_BAMBOO_TORCH = new WallStandingBlockItem(GBWBlocks.SOUL_BAMBOO_TORCH, GBWBlocks.SOUL_BAMBOO_WALL_TORCH, new FabricItemSettings().group(ItemGroup.DECORATIONS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.TORCH);
        }
    };
    //endregion
    //region Foods
    public static final Item VENISON = new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(GBWFoods.VENISON)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.COOKED_RABBIT);
        }
    };
    public static final Item COOKED_VENISON = new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(GBWFoods.COOKED_VENISON)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.COOKED_RABBIT);
        }
    };
    public static final Item ACAI_BOWL = new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(GBWFoods.ACAI_BOWL)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.RABBIT_STEW);
        }
    };
    //endregion
    //region Wooden Masks
    public static final Item OAK_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.OAK_PLANKS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.TURTLE_HELMET);
        }
    };
    public static final Item SPRUCE_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.SPRUCE_PLANKS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.TURTLE_HELMET);
        }
    };
    public static final Item BIRCH_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.BIRCH_PLANKS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.TURTLE_HELMET);
        }
    };
    public static final Item ASPEN_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(GBWBlocks.ASPEN.planks())) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.TURTLE_HELMET);
        }
    };
    public static final Item JUNGLE_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.JUNGLE_PLANKS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.TURTLE_HELMET);
        }
    };
    public static final Item MAHOGANY_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(GBWBlocks.MAHOGANY.planks())) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.TURTLE_HELMET);
        }
    };
    public static final Item ACAI_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(GBWBlocks.ACAI.planks())) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.TURTLE_HELMET);
        }
    };
    public static final Item ACACIA_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.ACACIA_PLANKS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.TURTLE_HELMET);
        }
    };
    public static final Item DARK_OAK_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.DARK_OAK_PLANKS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.TURTLE_HELMET);
        }
    };
    public static final Item MANGROVE_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.MANGROVE_PLANKS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.TURTLE_HELMET);
        }
    };
    public static final Item CRIMSON_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.CRIMSON_PLANKS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.TURTLE_HELMET);
        }
    };
    public static final Item WARPED_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.WARPED_PLANKS)) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.TURTLE_HELMET);
        }
    };
    //endregion
    //region Thatch
    public static final Item GRASS_THATCH = new BlockItem(GBWBlocks.GRASS_THATCH, new FabricItemSettings());
    public static final Item GRASS_THATCH_SLAB = new BlockItem(GBWBlocks.GRASS_THATCH_SLAB, new FabricItemSettings());
    public static final Item GRASS_THATCH_STAIRS = new BlockItem(GBWBlocks.GRASS_THATCH_STAIRS, new FabricItemSettings());
    public static final Item TRIMMED_GRASS_THATCH = new BlockItem(GBWBlocks.TRIMMED_GRASS_THATCH, new FabricItemSettings());
    public static final Item TRIMMED_GRASS_THATCH_SLAB = new BlockItem(GBWBlocks.TRIMMED_GRASS_THATCH_SLAB, new FabricItemSettings());
    public static final Item TRIMMED_GRASS_THATCH_STAIRS = new BlockItem(GBWBlocks.TRIMMED_GRASS_THATCH_STAIRS, new FabricItemSettings());
    //endregion
    //region Miscellaneous
    public static final Item MUSIC_DISC_SUNRISE = new MusicDiscItem(4, GBWSoundEvents.MUSIC_DISC_SUNRISE, new FabricItemSettings().group(ItemGroup.MISC).maxCount(1).rarity(Rarity.RARE), 70) {
        @Override
        public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
            ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MUSIC_DISC_OTHERSIDE);
        }
    };
    public static final Item MUSIC_DISC_PINA_COLADA = new MusicDiscItem(2, GBWSoundEvents.MUSIC_DISC_PINA_COLADA, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 86);
    public static final Item GRASSY_LAVAROCK = new BlockItem(GBWBlocks.GRASSY_LAVAROCK, new FabricItemSettings());
    public static final Item TROPICAL_FERN = new BlockItem(GBWBlocks.TROPICAL_FERN, new FabricItemSettings());
    public static final Item LARGE_TROPICAL_FERN = new BlockItem(GBWBlocks.LARGE_TROPICAL_FERN, new FabricItemSettings());
    public static final Item NAUTILUS_BUCKET = new EntityBucketItem(GBWEntityTypes.NAUTILUS, Fluids.WATER, SoundEvents.ITEM_BUCKET_FILL_FISH, new FabricItemSettings());
    public static final Item AURA_POTION = new AuraPotionItem(new FabricItemSettings().maxCount(1));
    //endregion

    @Override
    public void register() {
        registerItem(new Identifier(NAMESPACE, "mahogany_leaves"), MAHOGANY_LEAVES);
        registerItem(new Identifier(NAMESPACE, "mahogany_sign"), MAHOGANY_SIGN);
        registerItem(new Identifier(NAMESPACE, "mahogany_chest_boat"), MAHOGANY_CHEST_BOAT);
        registerItem(new Identifier(NAMESPACE, "mahogany_boat"), MAHOGANY_BOAT);
        registerItem(new Identifier(NAMESPACE, "green_aspen_leaves"), GREEN_ASPEN_LEAVES);
        registerItem(new Identifier(NAMESPACE, "green_aspen_leaf_pile"), GREEN_ASPEN_LEAF_PILE);
        registerItem(new Identifier(NAMESPACE, "aspen_sign"), ASPEN_SIGN);
        registerItem(new Identifier(NAMESPACE, "aspen_chest_boat"), ASPEN_CHEST_BOAT);
        registerItem(new Identifier(NAMESPACE, "aspen_boat"), ASPEN_BOAT);
        registerItem(new Identifier(NAMESPACE, "acai_leaves"), ACAI_LEAVES);
        registerItem(new Identifier(NAMESPACE, "hanging_acai_leaves"), HANGING_ACAI_LEAVES);
        registerItem(new Identifier(NAMESPACE, "acai_sign"), ACAI_SIGN);
        registerItem(new Identifier(NAMESPACE, "acai_boat"), ACAI_BOAT);
        registerItem(new Identifier(NAMESPACE, "acai_chest_boat"), ACAI_CHEST_BOAT);
        registerItem(new Identifier(NAMESPACE, "acai_berries"), ACAI_BERRIES);
        registerItem(new Identifier(NAMESPACE, "bamboo_torch"), BAMBOO_TORCH);
        registerItem(new Identifier(NAMESPACE, "soul_bamboo_torch"), SOUL_BAMBOO_TORCH);
        registerItem(new Identifier(NAMESPACE, "venison"), VENISON);
        registerItem(new Identifier(NAMESPACE, "cooked_venison"), COOKED_VENISON);
        registerItem(new Identifier(NAMESPACE, "acai_bowl"), ACAI_BOWL);
        registerItem(new Identifier(NAMESPACE, "oak_mask"), OAK_MASK);
        registerItem(new Identifier(NAMESPACE, "spruce_mask"), SPRUCE_MASK);
        registerItem(new Identifier(NAMESPACE, "birch_mask"), BIRCH_MASK);
        registerItem(new Identifier(NAMESPACE, "aspen_mask"), ASPEN_MASK);
        registerItem(new Identifier(NAMESPACE, "jungle_mask"), JUNGLE_MASK);
        registerItem(new Identifier(NAMESPACE, "mahogany_mask"), MAHOGANY_MASK);
        registerItem(new Identifier(NAMESPACE, "acai_mask"), ACAI_MASK);
        registerItem(new Identifier(NAMESPACE, "acacia_mask"), ACACIA_MASK);
        registerItem(new Identifier(NAMESPACE, "dark_oak_mask"), DARK_OAK_MASK);
        registerItem(new Identifier(NAMESPACE, "mangrove_mask"), MANGROVE_MASK);
        registerItem(new Identifier(NAMESPACE, "crimson_mask"), CRIMSON_MASK);
        registerItem(new Identifier(NAMESPACE, "warped_mask"), WARPED_MASK);
        registerItem(new Identifier(NAMESPACE, "grass_thatch"), GRASS_THATCH);
        registerItem(new Identifier(NAMESPACE, "grass_thatch_slab"), GRASS_THATCH_SLAB);
        registerItem(new Identifier(NAMESPACE, "grass_thatch_stairs"), GRASS_THATCH_STAIRS);
        registerItem(new Identifier(NAMESPACE, "trimmed_grass_thatch"), TRIMMED_GRASS_THATCH);
        registerItem(new Identifier(NAMESPACE, "trimmed_grass_thatch_slab"), TRIMMED_GRASS_THATCH_SLAB);
        registerItem(new Identifier(NAMESPACE, "trimmed_grass_thatch_stairs"), TRIMMED_GRASS_THATCH_STAIRS);
        registerItem(new Identifier(NAMESPACE, "music_disc_sunrise"), MUSIC_DISC_SUNRISE);
        registerItem(new Identifier(NAMESPACE, "music_disc_pina_colada"), MUSIC_DISC_PINA_COLADA);
        registerItem(new Identifier(NAMESPACE, "grassy_lavarock"), GRASSY_LAVAROCK);
        registerItem(new Identifier(NAMESPACE, "tropical_fern"), TROPICAL_FERN);
        registerItem(new Identifier(NAMESPACE, "large_tropical_fern"), LARGE_TROPICAL_FERN);
        registerItem(new Identifier(NAMESPACE, "nautilus_bucket"), NAUTILUS_BUCKET);
        registerItem(new Identifier(NAMESPACE, "aura_potion"), AURA_POTION);
        compostables();
    }

    private void compostables() {
        CompostingChanceRegistry.INSTANCE.add(GBWBlocks.ANTLER, .7f);
        CompostingChanceRegistry.INSTANCE.add(GBWBlocks.HEATHER, .65f);
        CompostingChanceRegistry.INSTANCE.add(GBWBlocks.TALL_HEATHER, .65f);
        CompostingChanceRegistry.INSTANCE.add(GBWBlocks.DAYLIGHT_MUSHROOM, .15f);
        CompostingChanceRegistry.INSTANCE.add(GBWBlocks.MIDNIGHT_MUSHROOM, .15f);
        CompostingChanceRegistry.INSTANCE.add(GBWBlocks.DARKBLIGHT_MUSHROOM, .15f);
        CompostingChanceRegistry.INSTANCE.add(GBWBlocks.YELLOW_ASPEN_LEAVES, .3f);
        CompostingChanceRegistry.INSTANCE.add(GBWBlocks.YELLOW_ASPEN_LEAF_PILE, .1f);
        CompostingChanceRegistry.INSTANCE.add(GBWBlocks.YELLOW_ASPEN_SAPLING, .3f);
        CompostingChanceRegistry.INSTANCE.add(GREEN_ASPEN_LEAVES, .3f);
        CompostingChanceRegistry.INSTANCE.add(GREEN_ASPEN_LEAF_PILE, .1f);
        CompostingChanceRegistry.INSTANCE.add(GBWBlocks.GREEN_ASPEN_SAPLING, .3f);
        CompostingChanceRegistry.INSTANCE.add(MAHOGANY_LEAVES, .3f);
        CompostingChanceRegistry.INSTANCE.add(GBWBlocks.MAHOGANY_SAPLING, .3f);

        CompostingChanceRegistry.INSTANCE.add(TROPICAL_FERN, .65f);
        CompostingChanceRegistry.INSTANCE.add(LARGE_TROPICAL_FERN, .65f);

        CompostingChanceRegistry.INSTANCE.add(GRASS_THATCH, .35f);
        CompostingChanceRegistry.INSTANCE.add(GRASS_THATCH_SLAB, .35f);
        CompostingChanceRegistry.INSTANCE.add(GRASS_THATCH_STAIRS, .35f);
        CompostingChanceRegistry.INSTANCE.add(TRIMMED_GRASS_THATCH, .3f);
        CompostingChanceRegistry.INSTANCE.add(TRIMMED_GRASS_THATCH_SLAB, .3f);
        CompostingChanceRegistry.INSTANCE.add(TRIMMED_GRASS_THATCH_STAIRS, .3f);

        CompostingChanceRegistry.INSTANCE.add(ACAI_LEAVES, .3f);
        CompostingChanceRegistry.INSTANCE.add(HANGING_ACAI_LEAVES, .2f);
    }

    @Override
    public void registerClient() {
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> FoliageColors.getDefaultColor(), MAHOGANY_LEAVES, GREEN_ASPEN_LEAVES, GREEN_ASPEN_LEAF_PILE);
    }

    public static void registerItem(Identifier id, Item item) {
        Registry.register(Registry.ITEM, id, item);
    }
}
