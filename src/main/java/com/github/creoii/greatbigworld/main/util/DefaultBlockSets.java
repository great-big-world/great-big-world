package com.github.creoii.greatbigworld.main.util;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.main.registry.ItemRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroups;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;
import static com.github.creoii.greatbigworld.main.registry.BlockRegistry.ExtendedBlockSettings;

public class DefaultBlockSets {
    /**
     * Registers most blocks required in a wood set.
     *      Does not register leaves, saplings, or boats.
     *
     * @param name - The name of the blocks, used for ids.
     * @param barkColor - The MapColor of the wood set bark.
     * @param woodColor - The MapColor of the wood set wood.
     * @param after - Item to place after in item groups
     * @param signType - Sign type used to register signs for the wood set
     */
    public static WoodSet createWoodSet(String name, MapColor barkColor, MapColor woodColor, @Nullable ItemConvertible after, @Nullable ItemConvertible logAfter, @Nullable ItemConvertible signAfter, SignType signType) {
        return createWoodSet(name, barkColor, woodColor, after, logAfter, signAfter, signType, true);
    }

    public static WoodSet createWoodSet(String name, MapColor barkColor, MapColor woodColor, @Nullable ItemConvertible after, @Nullable ItemConvertible logAfter, @Nullable ItemConvertible signAfter, SignType signType, boolean includeLogs) {
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
        Block sign = new SignBlock(FabricBlockSettings.copy(Blocks.OAK_SIGN), signType);
        Block wallSign = new WallSignBlock(FabricBlockSettings.copy(Blocks.OAK_WALL_SIGN).mapColor(woodColor).dropsLike(sign), signType);
        //Block hangingSign = new HangingSignBlock(FabricBlockSettings.copy(Blocks.OAK_HANGING_SIGN).mapColor(woodColor), signType);
        //Block wallHangingSign = new WallHangingSignBlock(FabricBlockSettings.copy(Blocks.OAK_WALL_HANGING_SIGN).mapColor(woodColor), signType);
        return new WoodSet(name, after, logAfter, signAfter, log, strippedLog, wood, strippedWood, planks, stairs, slab, fence, fenceGate, button, pressurePlate, door, trapdoor, sign, wallSign, null, null);
    }

    public record WoodSet(String name, @Nullable ItemConvertible after, @Nullable ItemConvertible logAfter, @Nullable ItemConvertible signAfter, @Nullable Block log, @Nullable Block strippedLog, @Nullable Block wood, @Nullable Block strippedWood, Block planks, Block stairs, Block slab, Block fence, Block fenceGate, Block button, Block pressurePlate, Block door, Block trapdoor, Block sign, Block wallSign, Block hangingSign, Block wallHangingSign) {
        public void register() {
            if (after != null) {
                if (log != null)
                    if (logAfter != null) {
                        BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_log"), log, new ExtendedBlockSettings(0f, 5, 20, strippedLog), new ItemRegistry.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, after), new ItemRegistry.ItemGroupSettings(ItemGroups.NATURAL, logAfter));
                    } else BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_log"), log, new ExtendedBlockSettings(0f, 5, 20, strippedLog), new ItemRegistry.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, after), new ItemRegistry.ItemGroupSettings(ItemGroups.NATURAL, null));
                if (strippedLog != null)
                    BlockRegistry.registerBlock(new Identifier(NAMESPACE, "stripped_" + name + "_log"), strippedLog, new ExtendedBlockSettings(0f, 5, 20, null), log, ItemGroups.BUILDING_BLOCKS);
                if (wood != null)
                    BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_wood"), wood, new ExtendedBlockSettings(0f, 5, 20, strippedWood), strippedLog, ItemGroups.BUILDING_BLOCKS);
                if (strippedWood != null)
                    BlockRegistry.registerBlock(new Identifier(NAMESPACE, "stripped_" + name + "_wood"), strippedWood, new ExtendedBlockSettings(0f, 5, 20, null), wood, ItemGroups.BUILDING_BLOCKS);
                BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_planks"), planks, new ExtendedBlockSettings(0f, 5, 20, null), strippedWood, ItemGroups.BUILDING_BLOCKS);
                BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_stairs"), stairs, new ExtendedBlockSettings(0f, 5, 20, null), planks, ItemGroups.BUILDING_BLOCKS);
                BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_slab"), slab, new ExtendedBlockSettings(0f, 5, 20, null), stairs, ItemGroups.BUILDING_BLOCKS);
                BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_fence"), fence, new ExtendedBlockSettings(0f, 5, 20, null), slab, ItemGroups.BUILDING_BLOCKS);
                BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_fence_gate"), fenceGate, new ExtendedBlockSettings(0f, 5, 20, null), fence, ItemGroups.BUILDING_BLOCKS);
                BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_door"), door, null, fenceGate, ItemGroups.BUILDING_BLOCKS);
                BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_trapdoor"), trapdoor, null, door, ItemGroups.BUILDING_BLOCKS);
                BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_pressure_plate"), pressurePlate, null, trapdoor, ItemGroups.BUILDING_BLOCKS);
                BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_button"), button, null, pressurePlate, ItemGroups.BUILDING_BLOCKS);
            } else {
                if (log != null)
                    if (logAfter != null) {
                        BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_log"), log, new ExtendedBlockSettings(0f, 5, 20, strippedLog), new ItemRegistry.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, null), new ItemRegistry.ItemGroupSettings(ItemGroups.NATURAL, logAfter));
                    } else BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_log"), log, new ExtendedBlockSettings(0f, 5, 20, strippedLog), ItemGroups.BUILDING_BLOCKS);
                if (strippedLog != null)
                    BlockRegistry.registerBlock(new Identifier(NAMESPACE, "stripped_" + name + "_log"), strippedLog, new ExtendedBlockSettings(0f, 5, 20, null), ItemGroups.BUILDING_BLOCKS);
                if (wood != null)
                    BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_wood"), wood, new ExtendedBlockSettings(0f, 5, 20, strippedWood), ItemGroups.BUILDING_BLOCKS);
                if (strippedWood != null)
                    BlockRegistry.registerBlock(new Identifier(NAMESPACE, "stripped_" + name + "_wood"), strippedWood, new ExtendedBlockSettings(0f, 5, 20, null), ItemGroups.BUILDING_BLOCKS);
                BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_planks"), planks, new ExtendedBlockSettings(0f, 5, 20, null), ItemGroups.BUILDING_BLOCKS);
                BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_stairs"), stairs, new ExtendedBlockSettings(0f, 5, 20, null), ItemGroups.BUILDING_BLOCKS);
                BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_slab"), slab, new ExtendedBlockSettings(0f, 5, 20, null), ItemGroups.BUILDING_BLOCKS);
                BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_fence"), fence, new ExtendedBlockSettings(0f, 5, 20, null), ItemGroups.BUILDING_BLOCKS);
                BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_fence_gate"), fenceGate, new ExtendedBlockSettings(0f, 5, 20, null), ItemGroups.BUILDING_BLOCKS);
                BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_door"), door, null, ItemGroups.BUILDING_BLOCKS);
                BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_trapdoor"), trapdoor, null, ItemGroups.BUILDING_BLOCKS);
                BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_pressure_plate"), pressurePlate, null, ItemGroups.BUILDING_BLOCKS);
                BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_button"), button, null, ItemGroups.BUILDING_BLOCKS);
            }
            BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_sign"), sign, null);
            BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_wall_sign"), wallSign, null);
            //BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_hanging_sign"), hangingSign, null);
            //BlockRegistry.registerBlock(new Identifier(NAMESPACE, name + "_wall_hanging_sign"), wallHangingSign, null);
        }
    }
}
