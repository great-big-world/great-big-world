package com.github.creoii.greatbigworld.main.util.datagen;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.main.util.DefaultBlockSets;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class GBWDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(LangProvider::new);
        pack.addProvider(ModelProvider::new);
        pack.addProvider(LootTableProvider::new);
    }

    private static class LangProvider extends FabricLanguageProvider {
        protected LangProvider(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        @Override
        public void generateTranslations(TranslationBuilder translationBuilder) {
            Arrays.stream(BlockRegistry.class.getDeclaredFields()).forEach(field -> {
                try {
                    Object obj = field.get(null);
                    if (obj instanceof Block block) {
                        //translationBuilder.add(block, StringUtils.remove(capitalizeAfterAll(field.getName(), '_'), '_'));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private static class ModelProvider extends FabricModelProvider {
        public ModelProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
            blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.BROWN_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.RED_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.ORANGE_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.YELLOW_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.LIME_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.GREEN_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.CYAN_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.BLUE_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.LIGHT_BLUE_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.PINK_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.MAGENTA_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.PURPLE_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.BLACK_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.GRAY_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.LIGHT_GRAY_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.WHITE_STAINED_CALCITE);
            blockStateModelGenerator.registerCubeAllModelTexturePool(BlockRegistry.MAHOGANY.planks()).family(BlockRegistry.MAHOGANY.family());
            blockStateModelGenerator.registerCubeAllModelTexturePool(BlockRegistry.ASPEN.planks()).family(BlockRegistry.ASPEN.family());
            blockStateModelGenerator.registerCubeAllModelTexturePool(BlockRegistry.ACAI.planks()).family(BlockRegistry.ACAI.family());
            blockStateModelGenerator.registerCubeAllModelTexturePool(BlockRegistry.WISTERIA.planks()).family(BlockRegistry.WISTERIA.family());
            blockStateModelGenerator.registerCubeAllModelTexturePool(BlockRegistry.COBBLESTONE_BRICKS).family(DefaultBlockSets.COBBLESTONE_BRICKS);
            blockStateModelGenerator.registerCubeAllModelTexturePool(BlockRegistry.MOSSY_COBBLESTONE_BRICKS).family(DefaultBlockSets.MOSSY_COBBLESTONE_BRICKS);
            blockStateModelGenerator.registerCubeAllModelTexturePool(BlockRegistry.LAVAROCK).family(DefaultBlockSets.LAVAROCK);
            blockStateModelGenerator.registerCubeAllModelTexturePool(BlockRegistry.LAVAROCK_BRICKS).family(DefaultBlockSets.LAVAROCK_BRICKS);
            blockStateModelGenerator.registerCubeAllModelTexturePool(BlockRegistry.ELDER_PRISMARINE).family(DefaultBlockSets.ELDER_PRISMARINE);
            blockStateModelGenerator.registerCubeAllModelTexturePool(BlockRegistry.ELDER_PRISMARINE_BRICKS).family(DefaultBlockSets.ELDER_PRISMARINE_BRICKS);
        }

        @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        }
    }

    public static class LootTableProvider extends SimpleFabricLootTableProvider {
        public LootTableProvider(FabricDataOutput output) {
            super(output, LootContextTypes.BLOCK);
        }

        @Override
        public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "brown_stained_calcite"), drops(BlockRegistry.BROWN_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "red_stained_calcite"), drops(BlockRegistry.RED_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "orange_stained_calcite"), drops(BlockRegistry.ORANGE_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "yellow_stained_calcite"), drops(BlockRegistry.YELLOW_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "lime_stained_calcite"), drops(BlockRegistry.LIME_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "green_stained_calcite"), drops(BlockRegistry.GREEN_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "cyan_stained_calcite"), drops(BlockRegistry.CYAN_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "blue_stained_calcite"), drops(BlockRegistry.BLUE_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "light_blue_stained_calcite"), drops(BlockRegistry.LIGHT_BLUE_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "pink_stained_calcite"), drops(BlockRegistry.PINK_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "magenta_stained_calcite"), drops(BlockRegistry.MAGENTA_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "purple_stained_calcite"), drops(BlockRegistry.PURPLE_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "black_stained_calcite"), drops(BlockRegistry.BLACK_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "gray_stained_calcite"), drops(BlockRegistry.GRAY_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "light_gray_stained_calcite"), drops(BlockRegistry.LIGHT_GRAY_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "white_stained_calcite"), drops(BlockRegistry.WHITE_STAINED_CALCITE));
        }

        public LootTable.Builder drops(ItemConvertible drop) {
            return LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1f)).with(ItemEntry.builder(drop)).getThisConditionConsumingBuilder());
        }
    }

    public static String capitalizeAfterAll(String str, char after) {
        str = StringUtils.capitalize(str);
        StringBuilder builder = new StringBuilder();
        boolean capitalize = false;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == after) {
                builder.append(str.charAt(i));
                capitalize = true;
            } else if (capitalize) {
                builder.append(Character.toUpperCase(str.charAt(i)));
                capitalize = false;
            } else {
                builder.append(str.charAt(i));
            }
        }
        return builder.toString();
    }
}
