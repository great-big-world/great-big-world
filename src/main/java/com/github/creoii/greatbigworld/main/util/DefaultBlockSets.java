package com.github.creoii.greatbigworld.main.util;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.GBWBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import static com.github.creoii.greatbigworld.main.registry.GBWBlocks.ExtendedBlockSettings;

public class DefaultBlockSets {
    /**
     * Registers most blocks required in a wood set.
     *      Does not register signs, leaves, saplings, or boats.
     *
     * @param name - The name of the blocks, used for ids.
     * @param barkColor - The MapColor of the wood set bark.
     * @param woodColor - The MapColor of the wood set wood.
     */
    public static WoodSet createWoodSet(String name, MapColor barkColor, MapColor woodColor) {
        return createWoodSet(name, barkColor, woodColor, true);
    }

    public static WoodSet createWoodSet(String name, MapColor barkColor, MapColor woodColor, boolean includeLogs) {
        Block strippedLog = null;
        Block log = null;
        Block strippedWood = null;
        Block wood = null;
        if (includeLogs) {
            strippedLog = new PillarBlock(FabricBlockSettings.of(Material.WOOD).strength(2f).sounds(BlockSoundGroup.WOOD).mapColor(woodColor)) {
                @Override
                public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
                    ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.STRIPPED_MANGROVE_LOG);
                }
            };
            log = new PillarBlock(FabricBlockSettings.of(Material.WOOD, (state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? woodColor : barkColor).strength(2f).sounds(BlockSoundGroup.WOOD)) {
                @Override
                public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
                    ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_LOG);
                }
            };
            strippedWood = new PillarBlock(FabricBlockSettings.copy(strippedLog)) {
                @Override
                public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
                    ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.STRIPPED_MANGROVE_WOOD);
                }
            };
            wood = new PillarBlock(FabricBlockSettings.copy(strippedLog).mapColor(barkColor)) {
                @Override
                public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
                    ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_WOOD);
                }
            };
        }
        Block planks = new Block(FabricBlockSettings.of(Material.WOOD).strength(2f, 3f).sounds(BlockSoundGroup.WOOD).mapColor(woodColor)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
                ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_PLANKS);
            }
        };
        Block slab = new SlabBlock(FabricBlockSettings.copy(planks)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
                ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_SLAB);
            }
        };
        Block stairs = new StairsBlock(planks.getDefaultState(), FabricBlockSettings.copy(planks)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
                ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_STAIRS);
            }
        };
        Block fence = new FenceBlock(FabricBlockSettings.copy(planks)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
                ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_FENCE);
            }
        };
        Block fenceGate = new FenceGateBlock(FabricBlockSettings.copy(planks)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
                ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_FENCE_GATE);
            }
        };
        Block button = new WoodenButtonBlock(FabricBlockSettings.copy(planks)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
                ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_BUTTON);
            }
        };
        Block pressurePlate = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(planks)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
                ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_PRESSURE_PLATE);
            }
        };
        Block door = new DoorBlock(FabricBlockSettings.copy(planks).strength(3f).sounds(BlockSoundGroup.WOOD).nonOpaque()) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
                ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_DOOR);
            }
        };
        Block trapdoor = new TrapdoorBlock(FabricBlockSettings.copy(planks).strength(3f).nonOpaque().allowsSpawning((state, world, pos, type) -> false)) {
            @Override
            public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
                ItemUtil.appendStackInGroup(stacks, new ItemStack(this), Items.MANGROVE_TRAPDOOR);
            }
        };
        return new WoodSet(name, log, strippedLog, wood, strippedWood, planks, stairs, slab, fence, fenceGate, button, pressurePlate, door, trapdoor);
    }

    public static record WoodSet(String name, @Nullable Block log, @Nullable Block strippedLog, @Nullable Block wood, @Nullable Block strippedWood, Block planks, Block stairs, Block slab, Block fence, Block fenceGate, Block button, Block pressurePlate, Block door, Block trapdoor) {
        public void register() {
            if (log != null)
                GBWBlocks.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_log"), log, new ExtendedBlockSettings(0f, 0, 0, strippedLog), ItemGroup.BUILDING_BLOCKS);
            if (strippedLog != null)
                GBWBlocks.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, "stripped_" + name + "_log"), strippedLog, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroup.BUILDING_BLOCKS);
            if (wood != null)
                GBWBlocks.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_wood"), wood, new ExtendedBlockSettings(0f, 0, 0, strippedWood), ItemGroup.BUILDING_BLOCKS);
            if (strippedWood != null)
                GBWBlocks.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, "stripped_" + name + "_wood"), strippedWood, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroup.BUILDING_BLOCKS);
            GBWBlocks.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_planks"), planks, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroup.BUILDING_BLOCKS);
            GBWBlocks.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_stairs"), stairs, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroup.BUILDING_BLOCKS);
            GBWBlocks.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_slab"), slab, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroup.BUILDING_BLOCKS);
            GBWBlocks.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_fence"), fence, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroup.DECORATIONS);
            GBWBlocks.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_fence_gate"), fenceGate, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroup.REDSTONE);
            GBWBlocks.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_door"), door, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroup.REDSTONE);
            GBWBlocks.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_trapdoor"), trapdoor, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroup.REDSTONE);
            GBWBlocks.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_pressure_plate"), pressurePlate, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroup.REDSTONE);
            GBWBlocks.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_button"), button, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroup.REDSTONE);
        }
    }
}
