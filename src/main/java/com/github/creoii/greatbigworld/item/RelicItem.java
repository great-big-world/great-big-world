package com.github.creoii.greatbigworld.item;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RelicItem extends Item {
    private final GearType type;

    private static final List<Item> PICKAXE_RELICS = new ArrayList<>();
    private static final List<Item> AXE_RELICS = new ArrayList<>();
    private static final List<Item> SHOVEL_RELICS = new ArrayList<>();
    private static final List<Item> HOE_RELICS = new ArrayList<>();
    private static final List<Item> SWORD_RELICS = new ArrayList<>();
    private static final List<Item> HELMET_RELICS = new ArrayList<>();
    private static final List<Item> CHESTPLATE_RELICS = new ArrayList<>();
    private static final List<Item> LEGGING_RELICS = new ArrayList<>();
    private static final List<Item> BOOT_RELICS = new ArrayList<>();

    public RelicItem(GearType type) {
        super(new FabricItemSettings().group(ItemGroup.MISC));
        this.type = type;
    }

    public GearType getGearType() {
        return type;
    }

    public static ItemStack discover(GearType gearType) {
        switch (gearType) {
            case PICKAXE -> new ItemStack(PICKAXE_RELICS.get(GreatBigWorld.RANDOM.nextInt(PICKAXE_RELICS.size())));
            case AXE -> new ItemStack(AXE_RELICS.get(GreatBigWorld.RANDOM.nextInt(AXE_RELICS.size())));
            case SHOVEL -> new ItemStack(SHOVEL_RELICS.get(GreatBigWorld.RANDOM.nextInt(SHOVEL_RELICS.size())));
            case HOE -> new ItemStack(HOE_RELICS.get(GreatBigWorld.RANDOM.nextInt(HOE_RELICS.size())));
            case SWORD -> new ItemStack(SWORD_RELICS.get(GreatBigWorld.RANDOM.nextInt(SWORD_RELICS.size())));
            case HELMET -> new ItemStack(HELMET_RELICS.get(GreatBigWorld.RANDOM.nextInt(HELMET_RELICS.size())));
            case CHESTPLATE -> new ItemStack(CHESTPLATE_RELICS.get(GreatBigWorld.RANDOM.nextInt(CHESTPLATE_RELICS.size())));
            case LEGGINGS -> new ItemStack(LEGGING_RELICS.get(GreatBigWorld.RANDOM.nextInt(LEGGING_RELICS.size())));
            case BOOTS -> new ItemStack(BOOT_RELICS.get(GreatBigWorld.RANDOM.nextInt(BOOT_RELICS.size())));
        }

        return ItemStack.EMPTY;
    }

    public enum GearType {
        PICKAXE,
        AXE,
        SHOVEL,
        HOE,
        SWORD,
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS
    }
}
