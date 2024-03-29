package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.creolib.api.util.item.CItemSettings;
import com.github.creoii.creolib.api.util.item.ItemRegistryHelper;
import com.github.creoii.greatbigworld.item.AuraPotionItem;
import com.github.creoii.greatbigworld.item.WoodenMaskItem;
import com.github.creoii.greatbigworld.main.util.AuraEffect;
import com.github.creoii.greatbigworld.main.util.GBWBoatTypes;
import com.github.creoii.greatbigworld.main.util.GBWFoods;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.main.util.material.WoodenMaskArmorMaterial;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.potion.PotionUtil;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Direction;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;
import static com.github.creoii.greatbigworld.main.registry.GBWBlocks.*;

public class GBWItems implements Register {
    //region Mahogany Wood
    public static final Item MAHOGANY_SIGN = new SignItem((new CItemSettings()).maxCount(16), GBWBlocks.MAHOGANY_SIGN, MAHOGANY_WALL_SIGN);
    public static final Item MAHOGANY_HANGING_SIGN = new HangingSignItem(GBWBlocks.MAHOGANY_HANGING_SIGN, MAHOGANY_WALL_HANGING_SIGN, (new CItemSettings()).maxCount(16));
    public static final Item MAHOGANY_LEAVES = new BlockItem(GBWBlocks.MAHOGANY_LEAVES, new CItemSettings().compostingChance(.3f));
    public static final Item MAHOGANY_BOAT = new BoatItem(false, GBWBoatTypes.MAHOGANY, new CItemSettings().maxCount(1));
    public static final Item MAHOGANY_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.MAHOGANY, new CItemSettings().maxCount(1));
    //endregion
    //region Aspen Wood
    public static final Item ASPEN_SIGN = new SignItem((new CItemSettings()).maxCount(16), GBWBlocks.ASPEN_SIGN, ASPEN_WALL_SIGN);
    public static final Item ASPEN_HANGING_SIGN = new HangingSignItem(GBWBlocks.ASPEN_HANGING_SIGN, ASPEN_WALL_HANGING_SIGN, (new CItemSettings()).maxCount(16));
    public static final Item GREEN_ASPEN_LEAVES = new BlockItem(GBWBlocks.GREEN_ASPEN_LEAVES, new CItemSettings().compostingChance(.3f));
    public static final Item GREEN_ASPEN_LEAF_PILE = new BlockItem(GBWBlocks.GREEN_ASPEN_LEAF_PILE, new CItemSettings().compostingChance(.1f));
    public static final Item ASPEN_BOAT = new BoatItem(false, GBWBoatTypes.ASPEN, new CItemSettings().maxCount(1));
    public static final Item ASPEN_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.ASPEN, new CItemSettings().maxCount(1));
    //endregion
    //region Acai Wood
    public static final Item ACAI_SIGN = new SignItem((new CItemSettings()).maxCount(16), GBWBlocks.ACAI_SIGN, ACAI_WALL_SIGN);
    public static final Item ACAI_HANGING_SIGN = new HangingSignItem(GBWBlocks.ACAI_HANGING_SIGN, ACAI_WALL_HANGING_SIGN, (new CItemSettings()).maxCount(16));
    public static final Item ACAI_LEAVES = new BlockItem(GBWBlocks.ACAI_LEAVES, new CItemSettings().compostingChance(.3f));
    public static final Item HANGING_ACAI_LEAVES = new BlockItem(GBWBlocks.HANGING_ACAI_LEAVES, new CItemSettings().compostingChance(.2f));
    public static final Item ACAI_BOAT = new BoatItem(false, GBWBoatTypes.ACAI, new CItemSettings().maxCount(1));
    public static final Item ACAI_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.ACAI, new CItemSettings().maxCount(1));
    public static final Item ACAI_BERRIES = new AliasedBlockItem(ACAI_BERRY_CLUMP, new CItemSettings().compostingChance(.3f).food(GBWFoods.ACAI_BERRIES));
    //endregion
    //region Wisteria Wood
    public static final Item WISTERIA_SIGN = new SignItem((new CItemSettings()).maxCount(16), GBWBlocks.WISTERIA_SIGN, WISTERIA_WALL_SIGN);
    public static final Item WISTERIA_HANGING_SIGN = new HangingSignItem(GBWBlocks.WISTERIA_HANGING_SIGN, WISTERIA_WALL_HANGING_SIGN, (new CItemSettings()).maxCount(16));
    public static final Item WISTERIA_BOAT = new BoatItem(false, GBWBoatTypes.WISTERIA, new FabricItemSettings().maxCount(1));
    public static final Item WISTERIA_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.WISTERIA, new FabricItemSettings().maxCount(1));
    //endregion
    //region Bamboo
    public static final Item BAMBOO_TORCH = new VerticallyAttachableBlockItem(GBWBlocks.BAMBOO_TORCH, BAMBOO_WALL_TORCH, new CItemSettings(), Direction.DOWN);
    public static final Item SOUL_BAMBOO_TORCH = new VerticallyAttachableBlockItem(GBWBlocks.SOUL_BAMBOO_TORCH, SOUL_BAMBOO_WALL_TORCH, new CItemSettings(), Direction.DOWN);
    //endregion
    //region Foods
    public static final Item VENISON = new Item(new CItemSettings().food(GBWFoods.VENISON));
    public static final Item COOKED_VENISON = new Item(new CItemSettings().food(GBWFoods.COOKED_VENISON));
    public static final Item ACAI_BOWL = new Item(new CItemSettings().food(GBWFoods.ACAI_BOWL));
    //endregion
    //region Wooden Masks
    public static final Item OAK_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.OAK_PLANKS));
    public static final Item SPRUCE_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.SPRUCE_PLANKS));
    public static final Item PINE_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(PINE_PLANKS));
    public static final Item BIRCH_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.BIRCH_PLANKS));
    public static final Item WISTERIA_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(WISTERIA_PLANKS));
    public static final Item ASPEN_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(ASPEN_PLANKS));
    public static final Item JUNGLE_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.JUNGLE_PLANKS));
    public static final Item MAHOGANY_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(MAHOGANY_PLANKS));
    public static final Item ACAI_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(ACAI_PLANKS));
    public static final Item ACACIA_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.ACACIA_PLANKS));
    public static final Item PALO_VERDE_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(PALO_VERDE_PLANKS));
    public static final Item DARK_OAK_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.DARK_OAK_PLANKS));
    public static final Item MANGROVE_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.MANGROVE_PLANKS));
    public static final Item CHERRY_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.CHERRY_PLANKS));
    public static final Item BAMBOO_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.BAMBOO_PLANKS));
    public static final Item CRIMSON_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.CRIMSON_PLANKS));
    public static final Item WARPED_MASK = new WoodenMaskItem(new WoodenMaskArmorMaterial(Items.WARPED_PLANKS));
    //endregion
    //region Thatch
    public static final Item GRASS_THATCH = new BlockItem(GBWBlocks.GRASS_THATCH, new CItemSettings().compostingChance(.35f));
    public static final Item GRASS_THATCH_SLAB = new BlockItem(GBWBlocks.GRASS_THATCH_SLAB, new CItemSettings().compostingChance(.35f));
    public static final Item GRASS_THATCH_STAIRS = new BlockItem(GBWBlocks.GRASS_THATCH_STAIRS, new CItemSettings().compostingChance(.35f));
    public static final Item TRIMMED_GRASS_THATCH = new BlockItem(GBWBlocks.TRIMMED_GRASS_THATCH, new CItemSettings().compostingChance(.3f));
    public static final Item TRIMMED_GRASS_THATCH_SLAB = new BlockItem(GBWBlocks.TRIMMED_GRASS_THATCH_SLAB, new CItemSettings().compostingChance(.3f));
    public static final Item TRIMMED_GRASS_THATCH_STAIRS = new BlockItem(GBWBlocks.TRIMMED_GRASS_THATCH_STAIRS, new CItemSettings().compostingChance(.3f));
    //endregion
    //region Pine Wood
    public static final Item PINE_SIGN = new SignItem((new CItemSettings()).maxCount(16), GBWBlocks.PINE_SIGN, PINE_WALL_SIGN);
    public static final Item PINE_HANGING_SIGN = new HangingSignItem(GBWBlocks.PINE_HANGING_SIGN, PINE_WALL_HANGING_SIGN, (new CItemSettings()).maxCount(16));
    public static final Item PINE_LEAVES = new BlockItem(GBWBlocks.PINE_LEAVES, new CItemSettings().compostingChance(.3f));
    public static final Item PINE_BOAT = new BoatItem(false, GBWBoatTypes.PINE, new CItemSettings().maxCount(1));
    public static final Item PINE_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.PINE, new CItemSettings().maxCount(1));
    //endregion
    //region Palo Verde Wood
    public static final Item PALO_VERDE_SIGN = new SignItem((new CItemSettings()).maxCount(16), GBWBlocks.PALO_VERDE_SIGN, PALO_VERDE_WALL_SIGN);
    public static final Item PALO_VERDE_HANGING_SIGN = new HangingSignItem(GBWBlocks.PALO_VERDE_HANGING_SIGN, PALO_VERDE_WALL_HANGING_SIGN, (new CItemSettings()).maxCount(16));
    public static final Item PALO_VERDE_LEAVES = new BlockItem(GBWBlocks.PALO_VERDE_LEAVES, new CItemSettings().compostingChance(.3f));
    public static final Item PALO_VERDE_BOAT = new BoatItem(false, GBWBoatTypes.PALO_VERDE, new CItemSettings().maxCount(1));
    public static final Item PALO_VERDE_CHEST_BOAT = new BoatItem(true, GBWBoatTypes.PALO_VERDE, new CItemSettings().maxCount(1));
    //endregion
    //region Miscellaneous
    public static final Item MUSIC_DISC_SUNRISE = new MusicDiscItem(4, GBWSoundEvents.MUSIC_DISC_SUNRISE, new CItemSettings().maxCount(1).rarity(Rarity.RARE), 70);
    public static final Item MUSIC_DISC_PINA_COLADA = new MusicDiscItem(2, GBWSoundEvents.MUSIC_DISC_PINA_COLADA, new CItemSettings().maxCount(1).rarity(Rarity.RARE), 86);
    public static final Item GRASSY_LAVAROCK = new BlockItem(GBWBlocks.GRASSY_LAVAROCK, new CItemSettings());
    public static final Item TROPICAL_FERN = new BlockItem(GBWBlocks.TROPICAL_FERN, new CItemSettings().compostingChance(.65f));
    public static final Item LARGE_TROPICAL_FERN = new BlockItem(GBWBlocks.LARGE_TROPICAL_FERN, new CItemSettings().compostingChance(.65f));
    public static final Item NAUTILUS_BUCKET = new EntityBucketItem(GBWEntityTypes.NAUTILUS, Fluids.WATER, SoundEvents.ITEM_BUCKET_FILL_FISH, new CItemSettings());
    public static final Item AURA_POTION = new AuraPotionItem(new CItemSettings().maxCount(1));
    //endregion

    @Override
    public void register() {
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "mahogany_sign"), MAHOGANY_SIGN, Items.JUNGLE_HANGING_SIGN, ItemGroups.FUNCTIONAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "mahogany_hanging_sign"), MAHOGANY_HANGING_SIGN, MAHOGANY_SIGN, ItemGroups.FUNCTIONAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "mahogany_leaves"), MAHOGANY_LEAVES, Items.JUNGLE_LEAVES, ItemGroups.NATURAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "mahogany_chest_boat"), MAHOGANY_CHEST_BOAT, Items.JUNGLE_CHEST_BOAT, ItemGroups.TOOLS);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "mahogany_boat"), MAHOGANY_BOAT, Items.JUNGLE_CHEST_BOAT, ItemGroups.TOOLS);

        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "aspen_sign"), ASPEN_SIGN, Items.BIRCH_HANGING_SIGN, ItemGroups.FUNCTIONAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "aspen_hanging_sign"), ASPEN_HANGING_SIGN, ASPEN_SIGN, ItemGroups.FUNCTIONAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "green_aspen_leaves"), GREEN_ASPEN_LEAVES, YELLOW_ASPEN_LEAF_PILE, ItemGroups.NATURAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "green_aspen_leaf_pile"), GREEN_ASPEN_LEAF_PILE, GREEN_ASPEN_LEAVES, ItemGroups.NATURAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "aspen_chest_boat"), ASPEN_CHEST_BOAT, Items.BIRCH_CHEST_BOAT, ItemGroups.TOOLS);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "aspen_boat"), ASPEN_BOAT, Items.BIRCH_CHEST_BOAT, ItemGroups.TOOLS);

        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "acai_sign"), ACAI_SIGN, MAHOGANY_HANGING_SIGN, ItemGroups.FUNCTIONAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "acai_hanging_sign"), ACAI_HANGING_SIGN, ACAI_SIGN, ItemGroups.FUNCTIONAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "acai_leaves"), ACAI_LEAVES, MAHOGANY_LEAVES, ItemGroups.NATURAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "hanging_acai_leaves"), HANGING_ACAI_LEAVES, ACAI_LEAVES, ItemGroups.NATURAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "acai_boat"), ACAI_BOAT, MAHOGANY_CHEST_BOAT, ItemGroups.TOOLS);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "acai_chest_boat"), ACAI_CHEST_BOAT, MAHOGANY_CHEST_BOAT, ItemGroups.TOOLS);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "acai_berries"), ACAI_BERRIES, new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, Items.GLOW_BERRIES), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.FOOD_AND_DRINK, Items.GLOW_BERRIES));

        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "wisteria_sign"), WISTERIA_SIGN, Items.BIRCH_HANGING_SIGN, ItemGroups.FUNCTIONAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "wisteria_hanging_sign"), WISTERIA_HANGING_SIGN, WISTERIA_SIGN, ItemGroups.FUNCTIONAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "wisteria_boat"), WISTERIA_BOAT, ASPEN_CHEST_BOAT, ItemGroups.TOOLS);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "wisteria_chest_boat"), WISTERIA_CHEST_BOAT, ASPEN_CHEST_BOAT, ItemGroups.TOOLS);

        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "bamboo_torch"), BAMBOO_TORCH, Items.REDSTONE_TORCH, ItemGroups.FUNCTIONAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "soul_bamboo_torch"), SOUL_BAMBOO_TORCH, Items.REDSTONE_TORCH, ItemGroups.FUNCTIONAL);

        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "venison"), VENISON, Items.COOKED_RABBIT, ItemGroups.FOOD_AND_DRINK);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "cooked_venison"), COOKED_VENISON, Items.COOKED_RABBIT, ItemGroups.FOOD_AND_DRINK);

        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "acai_bowl"), ACAI_BOWL, Items.RABBIT_STEW, ItemGroups.FOOD_AND_DRINK);

        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "oak_mask"), OAK_MASK, Items.TURTLE_HELMET, ItemGroups.COMBAT);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "spruce_mask"), SPRUCE_MASK, OAK_MASK, ItemGroups.COMBAT);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "pine_mask"), PINE_MASK, SPRUCE_MASK, ItemGroups.COMBAT);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "birch_mask"), BIRCH_MASK, PINE_MASK, ItemGroups.COMBAT);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "wisteria_mask"), WISTERIA_MASK, BIRCH_MASK, ItemGroups.COMBAT);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "aspen_mask"), ASPEN_MASK, WISTERIA_MASK, ItemGroups.COMBAT);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "jungle_mask"), JUNGLE_MASK, ASPEN_MASK, ItemGroups.COMBAT);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "mahogany_mask"), MAHOGANY_MASK, JUNGLE_MASK, ItemGroups.COMBAT);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "acai_mask"), ACAI_MASK, MAHOGANY_MASK, ItemGroups.COMBAT);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "acacia_mask"), ACACIA_MASK, ACAI_MASK, ItemGroups.COMBAT);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "palo_verde_mask"), PALO_VERDE_MASK, ACACIA_MASK, ItemGroups.COMBAT);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "dark_oak_mask"), DARK_OAK_MASK, PALO_VERDE_MASK, ItemGroups.COMBAT);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "mangrove_mask"), MANGROVE_MASK, DARK_OAK_MASK, ItemGroups.COMBAT);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "cherry_mask"), CHERRY_MASK, MANGROVE_MASK, ItemGroups.COMBAT);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "bamboo_mask"), BAMBOO_MASK, CHERRY_MASK, ItemGroups.COMBAT);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "crimson_mask"), CRIMSON_MASK, BAMBOO_MASK, ItemGroups.COMBAT);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "warped_mask"), WARPED_MASK, CRIMSON_MASK, ItemGroups.COMBAT);

        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "grass_thatch"), GRASS_THATCH, TRIMMED_BAMBOO_THATCH_SLAB, ItemGroups.BUILDING_BLOCKS);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "grass_thatch_slab"), GRASS_THATCH_SLAB, GRASS_THATCH, ItemGroups.BUILDING_BLOCKS);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "grass_thatch_stairs"), GRASS_THATCH_STAIRS, GRASS_THATCH_SLAB, ItemGroups.BUILDING_BLOCKS);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "trimmed_grass_thatch"), TRIMMED_GRASS_THATCH, GRASS_THATCH_STAIRS, ItemGroups.BUILDING_BLOCKS);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "trimmed_grass_thatch_slab"), TRIMMED_GRASS_THATCH_SLAB, TRIMMED_GRASS_THATCH, ItemGroups.BUILDING_BLOCKS);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "trimmed_grass_thatch_stairs"), TRIMMED_GRASS_THATCH_STAIRS, TRIMMED_GRASS_THATCH_SLAB, ItemGroups.BUILDING_BLOCKS);

        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "pine_sign"), PINE_SIGN, Items.SPRUCE_HANGING_SIGN, ItemGroups.FUNCTIONAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "pine_hanging_sign"), PINE_HANGING_SIGN, PINE_SIGN, ItemGroups.FUNCTIONAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "pine_leaves"), PINE_LEAVES, Items.SPRUCE_LEAVES, ItemGroups.NATURAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "pine_boat"), PINE_BOAT, Items.SPRUCE_BOAT, ItemGroups.TOOLS);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "pine_chest_boat"), PINE_CHEST_BOAT, Items.SPRUCE_CHEST_BOAT, ItemGroups.TOOLS);

        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "palo_verde_sign"), PALO_VERDE_SIGN, Items.ACACIA_HANGING_SIGN, ItemGroups.FUNCTIONAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "palo_verde_hanging_sign"), PALO_VERDE_HANGING_SIGN, PALO_VERDE_SIGN, ItemGroups.FUNCTIONAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "palo_verde_leaves"), PALO_VERDE_LEAVES, Items.ACACIA_LEAVES, ItemGroups.NATURAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "palo_verde_boat"), PALO_VERDE_BOAT, Items.ACACIA_BOAT, ItemGroups.TOOLS);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "palo_verde_chest_boat"), PALO_VERDE_CHEST_BOAT, Items.ACACIA_CHEST_BOAT, ItemGroups.TOOLS);

        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "music_disc_sunrise"), MUSIC_DISC_SUNRISE, Items.MUSIC_DISC_OTHERSIDE, ItemGroups.TOOLS);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "music_disc_pina_colada"), MUSIC_DISC_PINA_COLADA, MUSIC_DISC_SUNRISE, ItemGroups.TOOLS);

        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "grassy_lavarock"), GRASSY_LAVAROCK, GBWBlocks.VOLCANIC_SAND, ItemGroups.NATURAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "tropical_fern"), TROPICAL_FERN, Items.FERN, ItemGroups.NATURAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "large_tropical_fern"), LARGE_TROPICAL_FERN, Items.LARGE_FERN, ItemGroups.NATURAL);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "nautilus_bucket"), NAUTILUS_BUCKET, Items.TROPICAL_FISH_BUCKET, ItemGroups.TOOLS);
        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "aura_potion"), AURA_POTION, ItemGroups.FOOD_AND_DRINK, AuraEffect::addAuraPotions);
        trades();
    }

    private void trades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.BUTCHER, 3, factories -> new TradeOffers.BuyForOneEmeraldFactory(VENISON, 5, 16, 20));
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> new TradeOffers.SellItemFactory(MAHOGANY_SAPLING, 5, 1, 8, 1));
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> new TradeOffers.SellItemFactory(YELLOW_ASPEN_SAPLING, 5, 1, 8, 1));
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> new TradeOffers.SellItemFactory(GREEN_ASPEN_SAPLING, 5, 1, 8, 1));
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> new TradeOffers.SellItemFactory(ACAI_SAPLING, 5, 1, 8, 1));
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> new TradeOffers.SellItemFactory(BLUE_WISTERIA_SAPLING, 5, 1, 8, 1));
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> new TradeOffers.SellItemFactory(PINK_WISTERIA_SAPLING, 5, 1, 8, 1));
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> new TradeOffers.SellItemFactory(PURPLE_WISTERIA_SAPLING, 5, 1, 8, 1));
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> new TradeOffers.SellItemFactory(WHITE_WISTERIA_SAPLING, 5, 1, 8, 1));
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> new TradeOffers.SellItemFactory(YELLOW_WISTERIA_SAPLING, 5, 1, 8, 1));
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> new TradeOffers.SellItemFactory(PINE_SAPLING, 5, 1, 8, 1));
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> new TradeOffers.SellItemFactory(PALO_VERDE_SAPLING, 5, 1, 8, 1));
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> new TradeOffers.SellItemFactory(HEATHER, 1, 1, 12, 1));
    }

    @Override
    public void registerClient() {
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> FoliageColors.getDefaultColor(), MAHOGANY_LEAVES, GREEN_ASPEN_LEAVES, GREEN_ASPEN_LEAF_PILE, ACAI_LEAVES, HANGING_ACAI_LEAVES, PINE_LEAVES);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColors.getColor(.5d, 1d), TROPICAL_FERN, LARGE_TROPICAL_FERN);
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> FoliageColors.getColor(.5d, 1d), GRASSY_LAVAROCK, GRASS_THATCH, GRASS_THATCH_SLAB, GRASS_THATCH_STAIRS, TRIMMED_GRASS_THATCH, TRIMMED_GRASS_THATCH_SLAB, TRIMMED_GRASS_THATCH_STAIRS);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 0 ? PotionUtil.getColor(stack) : -1, AURA_POTION);
    }
}
