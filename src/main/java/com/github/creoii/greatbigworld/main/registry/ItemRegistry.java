package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Foods;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
    //region Cavier Caves
    public static final Item QUICKSAND_BUCKET = new BucketItem(Fluids.EMPTY, new FabricItemSettings().group(ItemGroup.MISC));
    //endregion

    //region Honeycomb
    public static final Item BEAR_SPAWN_EGG = new SpawnEggItem(EntityRegistry.BEAR, 0, 0, new FabricItemSettings().group(ItemGroup.MISC));
    //endregion

    //region Magic V Melee
    public static final Item SOUR_BERRY = new AliasedBlockItem(BlockRegistry.SOUR_BERRY_BUSH, new FabricItemSettings().food(Foods.SOUR_BERRIES).group(ItemGroup.FOOD));
    public static final Item BITTER_BERRY = new AliasedBlockItem(BlockRegistry.SOUR_BERRY_BUSH, new FabricItemSettings().food(Foods.SOUR_BERRIES).group(ItemGroup.FOOD));
    public static final Item PUNGENT_BERRY = new AliasedBlockItem(BlockRegistry.SOUR_BERRY_BUSH, new FabricItemSettings().food(Foods.SOUR_BERRIES).group(ItemGroup.FOOD));
    //endregion

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.MOD_ID, "bear_spawn_egg"), BEAR_SPAWN_EGG);
    }
}
