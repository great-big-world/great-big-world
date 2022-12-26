package com.github.creoii.greatbigworld.main.util;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.ItemGroups;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import static com.github.creoii.greatbigworld.main.registry.BlockRegistry.ExtendedBlockSettings;

public class DefaultBlockSets {
    /**
     * Registers most blocks required in a wood set.
     *      Does not register signs, leaves, saplings, or boats.
     *
     * @param name - The name of the blocks, used for ids.
     * @param barkColor - The MapColor of the wood set bark.
     * @param woodColor - The MapColor of the wood set wood.
     * @param includeLogs - Whether or not to include logs in the wood set.
     */
    public static WoodSet createWoodSet(String name, MapColor barkColor, MapColor woodColor, boolean includeLogs) {
        Block strippedLog = null;
        Block log = null;
        Block strippedWood = null;
        Block wood = null;
        if (includeLogs) {
            strippedLog = new PillarBlock(FabricBlockSettings.of(Material.WOOD).strength(2f).sounds(BlockSoundGroup.WOOD).mapColor(woodColor));
            log = new PillarBlock(FabricBlockSettings.of(Material.WOOD, (state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? woodColor : barkColor).strength(2f).sounds(BlockSoundGroup.WOOD));
            strippedWood = new PillarBlock(FabricBlockSettings.copy(strippedLog));
            wood = new PillarBlock(FabricBlockSettings.copy(strippedLog).mapColor(barkColor));
        }
        Block planks = new Block(FabricBlockSettings.of(Material.WOOD).strength(2f, 3f).sounds(BlockSoundGroup.WOOD).mapColor(woodColor));
        Block slab = new SlabBlock(FabricBlockSettings.copy(planks));
        Block stairs = new StairsBlock(planks.getDefaultState(), FabricBlockSettings.copy(planks));
        Block fence = new FenceBlock(FabricBlockSettings.copy(planks));
        Block fenceGate = new FenceGateBlock(FabricBlockSettings.copy(planks), SoundEvents.BLOCK_FENCE_GATE_CLOSE, SoundEvents.BLOCK_FENCE_GATE_OPEN);
        Block button = new ButtonBlock(FabricBlockSettings.copy(planks), 30, true, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON);
        Block pressurePlate = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(planks), SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON);
        Block door = new DoorBlock(FabricBlockSettings.copy(planks).strength(3f).sounds(BlockSoundGroup.WOOD).nonOpaque(), SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundEvents.BLOCK_WOODEN_DOOR_OPEN);
        Block trapdoor = new TrapdoorBlock(FabricBlockSettings.copy(planks).strength(3f).nonOpaque().allowsSpawning((state, world, pos, type) -> false), SoundEvents.BLOCK_WOODEN_TRAPDOOR_CLOSE, SoundEvents.BLOCK_WOODEN_TRAPDOOR_OPEN);
        return new WoodSet(name, log, strippedLog, wood, strippedWood, planks, stairs, slab, fence, fenceGate, button, pressurePlate, door, trapdoor);
    }

    public static record WoodSet(String name, @Nullable Block log, @Nullable Block strippedLog, @Nullable Block wood, @Nullable Block strippedWood, Block planks, Block stairs, Block slab, Block fence, Block fenceGate, Block button, Block pressurePlate, Block door, Block trapdoor) {
        public void register() {
            if (log != null) BlockRegistry.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_log"), log, new ExtendedBlockSettings(0f, 0, 0, strippedLog), ItemGroups.BUILDING_BLOCKS, ItemGroups.NATURAL);
            if (strippedLog != null) BlockRegistry.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, "stripped_" + name + "_log"), strippedLog, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroups.BUILDING_BLOCKS, ItemGroups.NATURAL);
            if (wood != null) BlockRegistry.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_wood"), wood, new ExtendedBlockSettings(0f, 0, 0, strippedWood), ItemGroups.BUILDING_BLOCKS, ItemGroups.NATURAL);
            if (strippedWood != null) BlockRegistry.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, "stripped_" + name + "_wood"), strippedWood, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroups.BUILDING_BLOCKS, ItemGroups.NATURAL);
            BlockRegistry.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_planks"), planks, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroups.BUILDING_BLOCKS);
            BlockRegistry.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_stairs"), stairs, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroups.BUILDING_BLOCKS);
            BlockRegistry.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_slab"), slab, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroups.BUILDING_BLOCKS);
            BlockRegistry.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_fence"), fence, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroups.BUILDING_BLOCKS);
            BlockRegistry.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_fence_gate"), fenceGate, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroups.BUILDING_BLOCKS);
            BlockRegistry.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_button"), button, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroups.BUILDING_BLOCKS);
            BlockRegistry.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_pressure_plate"), pressurePlate, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroups.BUILDING_BLOCKS);
            BlockRegistry.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_door"), door, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroups.BUILDING_BLOCKS);
            BlockRegistry.registerBlock(new Identifier(GreatBigWorld.NAMESPACE, name + "_trapdoor"), trapdoor, new ExtendedBlockSettings(0f, 0, 0, null), ItemGroups.BUILDING_BLOCKS);
        }
    }
}
