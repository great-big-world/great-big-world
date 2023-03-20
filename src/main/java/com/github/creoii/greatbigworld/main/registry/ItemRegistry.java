package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.item.AuraPotionItem;
import com.github.creoii.greatbigworld.item.WoodenMaskItem;
import com.github.creoii.greatbigworld.main.util.AuraEffect;
import com.github.creoii.greatbigworld.main.util.GBWBoatTypes;
import com.github.creoii.greatbigworld.main.util.GBWFoods;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.main.util.material.WoodenMaskArmorMaterial;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;
import static com.github.creoii.greatbigworld.main.registry.BlockRegistry.*;

public class ItemRegistry implements Register {
    //region Mahogany Wood
    public static final Item MAHOGANY_LEAVES = new BlockItem(BlockRegistry.MAHOGANY_LEAVES, new FabricItemSettings());
    public static final Item MAHOGANY_BOAT = new BoatItem(false, GBWBoatTypes.MAHOGANY, new FabricItemSettings().maxCount(1));
    public static final Item MAHOGANY_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.MAHOGANY, new FabricItemSettings().maxCount(1));
    //endregion
    //region Aspen Wood
    public static final Item GREEN_ASPEN_LEAVES = new BlockItem(BlockRegistry.GREEN_ASPEN_LEAVES, new FabricItemSettings());
    public static final Item GREEN_ASPEN_LEAF_PILE = new BlockItem(BlockRegistry.GREEN_ASPEN_LEAF_PILE, new FabricItemSettings());
    public static final Item ASPEN_BOAT = new BoatItem(false, GBWBoatTypes.ASPEN, new FabricItemSettings().maxCount(1));
    public static final Item ASPEN_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.ASPEN, new FabricItemSettings().maxCount(1));
    //endregion
    //region Acai Wood
    public static final Item ACAI_LEAVES = new BlockItem(BlockRegistry.ACAI_LEAVES, new FabricItemSettings());
    public static final Item HANGING_ACAI_LEAVES = new BlockItem(BlockRegistry.HANGING_ACAI_LEAVES, new FabricItemSettings());
    public static final Item ACAI_BOAT = new BoatItem(false, GBWBoatTypes.ACAI, new FabricItemSettings().maxCount(1));
    public static final Item ACAI_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.ACAI, new FabricItemSettings().maxCount(1));
    public static final Item ACAI_BERRIES = new AliasedBlockItem(ACAI_BERRY_CLUMP, new FabricItemSettings().food(GBWFoods.ACAI_BERRIES));
    //endregion
    //region Bamboo
    public static final Item BAMBOO_TORCH = new VerticallyAttachableBlockItem(BlockRegistry.BAMBOO_TORCH, BAMBOO_WALL_TORCH, new FabricItemSettings(), Direction.DOWN);
    public static final Item SOUL_BAMBOO_TORCH = new VerticallyAttachableBlockItem(BlockRegistry.SOUL_BAMBOO_TORCH, SOUL_BAMBOO_WALL_TORCH, new FabricItemSettings(), Direction.DOWN);
    //endregion
    //region Foods
    public static final Item VENISON = new Item(new FabricItemSettings().food(GBWFoods.VENISON));
    public static final Item COOKED_VENISON = new Item(new FabricItemSettings().food(GBWFoods.COOKED_VENISON));
    public static final Item ACAI_BOWL = new Item(new FabricItemSettings().food(GBWFoods.ACAI_BOWL));
    //endregion
    //region Wooden Masks
    public static final Item OAK_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.OAK_PLANKS));
    public static final Item SPRUCE_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.SPRUCE_PLANKS));
    public static final Item BIRCH_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.BIRCH_PLANKS));
    public static final Item ASPEN_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(ASPEN.planks()));
    public static final Item JUNGLE_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.JUNGLE_PLANKS));
    public static final Item MAHOGANY_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(MAHOGANY.planks()));
    public static final Item ACAI_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(ACAI.planks()));
    public static final Item ACACIA_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.ACACIA_PLANKS));
    public static final Item DARK_OAK_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.DARK_OAK_PLANKS));
    public static final Item MANGROVE_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.MANGROVE_PLANKS));
    public static final Item CRIMSON_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.CRIMSON_PLANKS));
    public static final Item WARPED_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.WARPED_PLANKS));
    //endregion
    //region Thatch
    public static final Item GRASS_THATCH = new BlockItem(BlockRegistry.GRASS_THATCH, new FabricItemSettings());
    public static final Item GRASS_THATCH_SLAB = new BlockItem(BlockRegistry.GRASS_THATCH_SLAB, new FabricItemSettings());
    public static final Item GRASS_THATCH_STAIRS = new BlockItem(BlockRegistry.GRASS_THATCH_STAIRS, new FabricItemSettings());
    public static final Item TRIMMED_GRASS_THATCH = new BlockItem(BlockRegistry.TRIMMED_GRASS_THATCH, new FabricItemSettings());
    public static final Item TRIMMED_GRASS_THATCH_SLAB = new BlockItem(BlockRegistry.TRIMMED_GRASS_THATCH_SLAB, new FabricItemSettings());
    public static final Item TRIMMED_GRASS_THATCH_STAIRS = new BlockItem(BlockRegistry.TRIMMED_GRASS_THATCH_STAIRS, new FabricItemSettings());
    //endregion
    //region Miscellaneous
    public static final Item MUSIC_DISC_SUNRISE = new MusicDiscItem(4, SoundRegistry.MUSIC_DISC_SUNRISE, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 70);
    public static final Item MUSIC_DISC_PINA_COLADA = new MusicDiscItem(2, SoundRegistry.MUSIC_DISC_PINA_COLADA, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 86);
    public static final Item GRASSY_LAVAROCK = new BlockItem(BlockRegistry.GRASSY_LAVAROCK, new FabricItemSettings());
    public static final Item TROPICAL_FERN = new BlockItem(BlockRegistry.TROPICAL_FERN, new FabricItemSettings());
    public static final Item LARGE_TROPICAL_FERN = new BlockItem(BlockRegistry.LARGE_TROPICAL_FERN, new FabricItemSettings());
    public static final Item NAUTILUS_BUCKET = new EntityBucketItem(EntityRegistry.NAUTILUS, Fluids.WATER, SoundEvents.ITEM_BUCKET_FILL_FISH, new FabricItemSettings());
    public static final Item AURA_POTION = new AuraPotionItem(new FabricItemSettings().maxCount(1));
    //endregion

    @Override
    public void register() {
        registerItem(new Identifier(NAMESPACE, "mahogany_leaves"), MAHOGANY_LEAVES, Items.JUNGLE_LEAVES, ItemGroups.NATURAL);
        registerItem(new Identifier(NAMESPACE, "mahogany_chest_boat"), MAHOGANY_CHEST_BOAT, Items.JUNGLE_CHEST_BOAT, ItemGroups.TOOLS);
        registerItem(new Identifier(NAMESPACE, "mahogany_boat"), MAHOGANY_BOAT, Items.JUNGLE_CHEST_BOAT, ItemGroups.TOOLS);
        registerItem(new Identifier(NAMESPACE, "green_aspen_leaves"), GREEN_ASPEN_LEAVES, Items.BIRCH_LEAVES, ItemGroups.NATURAL);
        registerItem(new Identifier(NAMESPACE, "green_aspen_leaf_pile"), GREEN_ASPEN_LEAF_PILE, Items.BIRCH_LEAVES, ItemGroups.NATURAL);
        registerItem(new Identifier(NAMESPACE, "aspen_chest_boat"), ASPEN_CHEST_BOAT, Items.BIRCH_CHEST_BOAT, ItemGroups.TOOLS);
        registerItem(new Identifier(NAMESPACE, "aspen_boat"), ASPEN_BOAT, Items.BIRCH_CHEST_BOAT, ItemGroups.TOOLS);
        registerItem(new Identifier(NAMESPACE, "acai_leaves"), ACAI_LEAVES, MAHOGANY_LEAVES, ItemGroups.NATURAL);
        registerItem(new Identifier(NAMESPACE, "hanging_acai_leaves"), HANGING_ACAI_LEAVES, ACAI_LEAVES, ItemGroups.NATURAL);
        registerItem(new Identifier(NAMESPACE, "acai_boat"), ACAI_BOAT, MAHOGANY_CHEST_BOAT, ItemGroups.TOOLS);
        registerItem(new Identifier(NAMESPACE, "acai_chest_boat"), ACAI_CHEST_BOAT, MAHOGANY_CHEST_BOAT, ItemGroups.TOOLS);
        registerItem(new Identifier(NAMESPACE, "acai_berries"), ACAI_BERRIES, new ItemGroupSettings(ItemGroups.NATURAL, Items.GLOW_BERRIES), new ItemGroupSettings(ItemGroups.FOOD_AND_DRINK, Items.GLOW_BERRIES));
        registerItem(new Identifier(NAMESPACE, "bamboo_torch"), BAMBOO_TORCH, Items.REDSTONE_TORCH, ItemGroups.FUNCTIONAL);
        registerItem(new Identifier(NAMESPACE, "soul_bamboo_torch"), SOUL_BAMBOO_TORCH, Items.REDSTONE_TORCH, ItemGroups.FUNCTIONAL);
        registerItem(new Identifier(NAMESPACE, "venison"), VENISON, Items.COOKED_RABBIT, ItemGroups.FOOD_AND_DRINK);
        registerItem(new Identifier(NAMESPACE, "cooked_venison"), COOKED_VENISON, Items.COOKED_RABBIT, ItemGroups.FOOD_AND_DRINK);
        registerItem(new Identifier(NAMESPACE, "acai_bowl"), ACAI_BOWL, Items.RABBIT_STEW, ItemGroups.FOOD_AND_DRINK);
        registerItem(new Identifier(NAMESPACE, "oak_mask"), OAK_MASK, new ItemGroupSettings(ItemGroups.COMBAT, Items.TURTLE_HELMET));
        registerItem(new Identifier(NAMESPACE, "spruce_mask"), SPRUCE_MASK, new ItemGroupSettings(ItemGroups.COMBAT, OAK_MASK));
        registerItem(new Identifier(NAMESPACE, "birch_mask"), BIRCH_MASK, new ItemGroupSettings(ItemGroups.COMBAT, SPRUCE_MASK));
        registerItem(new Identifier(NAMESPACE, "aspen_mask"), ASPEN_MASK, new ItemGroupSettings(ItemGroups.COMBAT, BIRCH_MASK));
        registerItem(new Identifier(NAMESPACE, "jungle_mask"), JUNGLE_MASK, new ItemGroupSettings(ItemGroups.COMBAT, ASPEN_MASK));
        registerItem(new Identifier(NAMESPACE, "mahogany_mask"), MAHOGANY_MASK, new ItemGroupSettings(ItemGroups.COMBAT, JUNGLE_MASK));
        registerItem(new Identifier(NAMESPACE, "acai_mask"), ACAI_MASK, new ItemGroupSettings(ItemGroups.COMBAT, MAHOGANY_MASK));
        registerItem(new Identifier(NAMESPACE, "acacia_mask"), ACACIA_MASK, new ItemGroupSettings(ItemGroups.COMBAT, ACAI_MASK));
        registerItem(new Identifier(NAMESPACE, "dark_oak_mask"), DARK_OAK_MASK, new ItemGroupSettings(ItemGroups.COMBAT, ACACIA_MASK));
        registerItem(new Identifier(NAMESPACE, "mangrove_mask"), MANGROVE_MASK, new ItemGroupSettings(ItemGroups.COMBAT, DARK_OAK_MASK));
        registerItem(new Identifier(NAMESPACE, "crimson_mask"), CRIMSON_MASK, new ItemGroupSettings(ItemGroups.COMBAT, MANGROVE_MASK));
        registerItem(new Identifier(NAMESPACE, "warped_mask"), WARPED_MASK, new ItemGroupSettings(ItemGroups.COMBAT, CRIMSON_MASK));
        registerItem(new Identifier(NAMESPACE, "grass_thatch"), GRASS_THATCH, new ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, null));
        registerItem(new Identifier(NAMESPACE, "grass_thatch_slab"), GRASS_THATCH_SLAB, new ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, null));
        registerItem(new Identifier(NAMESPACE, "grass_thatch_stairs"), GRASS_THATCH_STAIRS, new ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, null));
        registerItem(new Identifier(NAMESPACE, "trimmed_grass_thatch"), TRIMMED_GRASS_THATCH, new ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, null));
        registerItem(new Identifier(NAMESPACE, "trimmed_grass_thatch_slab"), TRIMMED_GRASS_THATCH_SLAB, new ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, null));
        registerItem(new Identifier(NAMESPACE, "trimmed_grass_thatch_stairs"), TRIMMED_GRASS_THATCH_STAIRS, new ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, null));
        registerItem(new Identifier(NAMESPACE, "music_disc_sunrise"), MUSIC_DISC_SUNRISE, Items.MUSIC_DISC_OTHERSIDE, ItemGroups.TOOLS);
        registerItem(new Identifier(NAMESPACE, "music_disc_pina_colada"), MUSIC_DISC_PINA_COLADA, MUSIC_DISC_SUNRISE, ItemGroups.TOOLS);
        registerItem(new Identifier(NAMESPACE, "grassy_lavarock"), GRASSY_LAVAROCK, new ItemGroupSettings(ItemGroups.NATURAL, BlockRegistry.VOLCANIC_SAND));
        registerItem(new Identifier(NAMESPACE, "tropical_fern"), TROPICAL_FERN, Items.FERN, ItemGroups.NATURAL);
        registerItem(new Identifier(NAMESPACE, "large_tropical_fern"), LARGE_TROPICAL_FERN, Items.LARGE_FERN, ItemGroups.NATURAL);
        registerItem(new Identifier(NAMESPACE, "nautilus_bucket"), NAUTILUS_BUCKET, Items.TROPICAL_FISH_BUCKET, ItemGroups.TOOLS);
        registerItem(new Identifier(NAMESPACE, "aura_potion"), AURA_POTION, ItemGroups.FOOD_AND_DRINK, AuraEffect::addAuraPotions);
        compostables();
    }

    private void compostables() {
        CompostingChanceRegistry.INSTANCE.add(ANTLER, .7f);
        CompostingChanceRegistry.INSTANCE.add(HEATHER, .65f);
        CompostingChanceRegistry.INSTANCE.add(TALL_HEATHER, .65f);
        CompostingChanceRegistry.INSTANCE.add(DAYLIGHT_MUSHROOM, .15f);
        CompostingChanceRegistry.INSTANCE.add(MIDNIGHT_MUSHROOM, .15f);
        CompostingChanceRegistry.INSTANCE.add(DARKBLIGHT_MUSHROOM, .15f);
        CompostingChanceRegistry.INSTANCE.add(YELLOW_ASPEN_LEAVES, .3f);
        CompostingChanceRegistry.INSTANCE.add(YELLOW_ASPEN_LEAF_PILE, .1f);
        CompostingChanceRegistry.INSTANCE.add(YELLOW_ASPEN_SAPLING, .3f);
        CompostingChanceRegistry.INSTANCE.add(GREEN_ASPEN_LEAVES, .3f);
        CompostingChanceRegistry.INSTANCE.add(GREEN_ASPEN_LEAF_PILE, .1f);
        CompostingChanceRegistry.INSTANCE.add(GREEN_ASPEN_SAPLING, .3f);
        CompostingChanceRegistry.INSTANCE.add(MAHOGANY_LEAVES, .3f);
        CompostingChanceRegistry.INSTANCE.add(MAHOGANY_SAPLING, .3f);
        CompostingChanceRegistry.INSTANCE.add(ACAI_LEAVES, .3f);
        CompostingChanceRegistry.INSTANCE.add(HANGING_ACAI_LEAVES, .2f);
        CompostingChanceRegistry.INSTANCE.add(ACAI_SAPLING, .3f);
        CompostingChanceRegistry.INSTANCE.add(TROPICAL_FERN, .65f);
        CompostingChanceRegistry.INSTANCE.add(LARGE_TROPICAL_FERN, .65f);
        CompostingChanceRegistry.INSTANCE.add(ACAI_LEAVES, .3f);
        CompostingChanceRegistry.INSTANCE.add(BEACHGRASS_THATCH, .35f);
        CompostingChanceRegistry.INSTANCE.add(BEACHGRASS_THATCH_SLAB, .35f);
        CompostingChanceRegistry.INSTANCE.add(BEACHGRASS_THATCH_STAIRS, .35f);
        CompostingChanceRegistry.INSTANCE.add(TRIMMED_BEACHGRASS_THATCH, .3f);
        CompostingChanceRegistry.INSTANCE.add(TRIMMED_BEACHGRASS_THATCH_SLAB, .3f);
        CompostingChanceRegistry.INSTANCE.add(TRIMMED_BEACHGRASS_THATCH_STAIRS, .3f);
        CompostingChanceRegistry.INSTANCE.add(BAMBOO_THATCH, .35f);
        CompostingChanceRegistry.INSTANCE.add(BAMBOO_THATCH_SLAB, .35f);
        CompostingChanceRegistry.INSTANCE.add(BAMBOO_THATCH_STAIRS, .35f);
        CompostingChanceRegistry.INSTANCE.add(TRIMMED_BAMBOO_THATCH, .3f);
        CompostingChanceRegistry.INSTANCE.add(TRIMMED_BAMBOO_THATCH_SLAB, .3f);
        CompostingChanceRegistry.INSTANCE.add(TRIMMED_BAMBOO_THATCH_STAIRS, .3f);
        CompostingChanceRegistry.INSTANCE.add(GRASS_THATCH, .35f);
        CompostingChanceRegistry.INSTANCE.add(GRASS_THATCH_SLAB, .35f);
        CompostingChanceRegistry.INSTANCE.add(GRASS_THATCH_STAIRS, .35f);
        CompostingChanceRegistry.INSTANCE.add(TRIMMED_GRASS_THATCH, .3f);
        CompostingChanceRegistry.INSTANCE.add(TRIMMED_GRASS_THATCH_SLAB, .3f);
        CompostingChanceRegistry.INSTANCE.add(TRIMMED_GRASS_THATCH_STAIRS, .3f);
    }

    @Override
    public void registerClient() {
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> FoliageColors.getDefaultColor(), MAHOGANY_LEAVES, GREEN_ASPEN_LEAVES, GREEN_ASPEN_LEAF_PILE, ACAI_LEAVES, HANGING_ACAI_LEAVES);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColors.getColor(.5d, 1d), TROPICAL_FERN, LARGE_TROPICAL_FERN);
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> FoliageColors.getColor(.5d, 1d), GRASSY_LAVAROCK, GRASS_THATCH, GRASS_THATCH_SLAB, GRASS_THATCH_STAIRS, TRIMMED_GRASS_THATCH, TRIMMED_GRASS_THATCH_SLAB, TRIMMED_GRASS_THATCH_STAIRS);
    }

    public static void registerItem(Identifier id, Item item, @Nullable ItemConvertible after, @Nullable ItemGroup group) {
        Registry.register(Registries.ITEM, id, item);
        if (group != null) {
            if (after != null) {
                ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.addAfter(after, item));
            } else {
                ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
            }
        }
    }

    public static void registerItem(Identifier id, Item item, @Nullable ItemGroup group, ItemGroupEvents.ModifyEntries entries) {
        Registry.register(Registries.ITEM, id, item);
        if (group != null) {
            ItemGroupEvents.modifyEntriesEvent(group).register(entries);
        }
    }

    public static void registerItem(Identifier id, Item item, ItemGroupSettings... groups) {
        Registry.register(Registries.ITEM, id, item);
        if (groups != null) {
            for (ItemGroupSettings settings : groups) {
                if (settings.after() != null) {
                    ItemGroupEvents.modifyEntriesEvent(settings.group()).register(entries -> entries.addAfter(settings.after(), item));
                } else {
                    ItemGroupEvents.modifyEntriesEvent(settings.group()).register(entries -> entries.add(item));
                }
            }
        }
    }

    public record ItemGroupSettings(ItemGroup group, @Nullable ItemConvertible after) { }
}
