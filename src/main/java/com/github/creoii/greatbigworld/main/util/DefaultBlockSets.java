package com.github.creoii.greatbigworld.main.util;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;

import static com.github.creoii.greatbigworld.main.registry.BlockRegistry.ExtendedBlockSettings;
import static com.github.creoii.greatbigworld.main.registry.BlockRegistry.registerBlock;

public class DefaultBlockSets {
    /**
     * Registers most blocks required in a wood set.
     *      Does not register signs, leaves, saplings, or boats.
     *
     * @param id - The namespace and name of the blocks. eg new Identifier("great_big_world", "mahogany");
     * @param barkColor - The MapColor of the wood set bark.
     * @param woodColor - The MapColor of the wood set wood.
     * @return - The Log block, to be used in tree generation.
     */
    public static WoodSet createWoodSet(Identifier id, MapColor barkColor, MapColor woodColor) {
        Block strippedLog = registerBlock(new Identifier(id.getNamespace(), "stripped_".concat(id.getPath()).concat("_log")), new PillarBlock(FabricBlockSettings.of(Material.WOOD).strength(2f).sounds(BlockSoundGroup.WOOD).mapColor(woodColor)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.STRIPPED_MANGROVE_LOG); }
        }, ItemGroup.BUILDING_BLOCKS, new ExtendedBlockSettings(0f, 5, 5, null));
        Block log = registerBlock(new Identifier(id.getNamespace(), id.getPath().concat("_log")), new PillarBlock(FabricBlockSettings.of(Material.WOOD, (state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? woodColor : barkColor).strength(2f).sounds(BlockSoundGroup.WOOD)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MUDDY_MANGROVE_ROOTS); }
        }, ItemGroup.BUILDING_BLOCKS, new ExtendedBlockSettings(0f, 5, 5, strippedLog));
        Block strippedWood = registerBlock(new Identifier(id.getNamespace(), "stripped_".concat(id.getPath()).concat("_wood")), new PillarBlock(FabricBlockSettings.copy(strippedLog)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.STRIPPED_MANGROVE_WOOD); }
        }, ItemGroup.BUILDING_BLOCKS, new ExtendedBlockSettings(0f, 5, 5, null));
        Block wood = registerBlock(new Identifier(id.getNamespace(), id.getPath().concat("_wood")), new PillarBlock(FabricBlockSettings.copy(strippedLog).mapColor(barkColor)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_WOOD); }
        }, ItemGroup.BUILDING_BLOCKS, new ExtendedBlockSettings(0f, 5, 5, strippedWood));
        Block planks = registerBlock(new Identifier(id.getNamespace(), id.getPath().concat("_planks")), new Block(FabricBlockSettings.copy(strippedLog).strength(2f, 3f)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_PLANKS); }
        }, ItemGroup.BUILDING_BLOCKS, new ExtendedBlockSettings(0f, 5, 20, null));
        Block slab = registerBlock(new Identifier(id.getNamespace(), id.getPath().concat("_slab")), new SlabBlock(FabricBlockSettings.copy(planks)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_SLAB); }
        }, ItemGroup.BUILDING_BLOCKS, new ExtendedBlockSettings(0f, 5, 20, null));
        Block stairs = registerBlock(new Identifier(id.getNamespace(), id.getPath().concat("_stairs")), new StairsBlock(planks.getDefaultState(), FabricBlockSettings.copy(planks)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_STAIRS); }
        }, ItemGroup.BUILDING_BLOCKS, new ExtendedBlockSettings(0f, 5, 20, null));
        Block fence = registerBlock(new Identifier(id.getNamespace(), id.getPath().concat("_fence")), new FenceBlock(FabricBlockSettings.copy(planks)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_FENCE); }
        }, ItemGroup.DECORATIONS, new ExtendedBlockSettings(0f, 5, 20, null));
        Block fenceGate = registerBlock(new Identifier(id.getNamespace(), id.getPath().concat("_fence_gate")), new FenceGateBlock(FabricBlockSettings.copy(planks)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_FENCE_GATE); }
        }, ItemGroup.REDSTONE, new ExtendedBlockSettings(0f, 0, 0, null));
        Block button = registerBlock(new Identifier(id.getNamespace(), id.getPath().concat("_button")), new WoodenButtonBlock(FabricBlockSettings.copy(planks)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_BUTTON); }
        }, ItemGroup.REDSTONE, new ExtendedBlockSettings(0f, 0, 0, null));
        Block pressurePlate = registerBlock(new Identifier(id.getNamespace(), id.getPath().concat("_pressure_plate")), new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(planks)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_PRESSURE_PLATE); }
        }, ItemGroup.REDSTONE, new ExtendedBlockSettings(0f, 0, 0, null));
        Block door = registerBlock(new Identifier(id.getNamespace(), id.getPath().concat("_door")), new DoorBlock(FabricBlockSettings.copy(planks).strength(3f).sounds(BlockSoundGroup.WOOD).nonOpaque()) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_DOOR); }
        }, ItemGroup.REDSTONE, new ExtendedBlockSettings(0f, 0, 0, null));
        Block trapdoor = registerBlock(new Identifier(id.getNamespace(), id.getPath().concat("_trapdoor")), new TrapdoorBlock(FabricBlockSettings.copy(planks).strength(3f).nonOpaque().allowsSpawning((state, world, pos, type) -> false)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) { ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_TRAPDOOR); }
        }, ItemGroup.REDSTONE, new ExtendedBlockSettings(0f, 0, 0, null));

        return new WoodSet(log, strippedLog, wood, strippedWood, planks, stairs, slab, fence, fenceGate, button, pressurePlate, door, trapdoor);
    }

    public static record WoodSet(Block log, Block strippedLog, Block wood, Block strippedWood, Block planks, Block stairs, Block slab, Block fence, Block fenceGate, Block button, Block pressurePlate, Block door, Block trapdoor) {}
}
