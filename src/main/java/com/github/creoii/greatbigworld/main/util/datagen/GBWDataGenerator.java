package com.github.creoii.greatbigworld.main.util.datagen;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.GBWBlocks;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

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
            translationBuilder.add(GBWBlocks.BROWN_STAINED_CALCITE, "Brown Stained Calcite");
            translationBuilder.add(GBWBlocks.RED_STAINED_CALCITE, "Red Stained Calcite");
            translationBuilder.add(GBWBlocks.ORANGE_STAINED_CALCITE, "Orange Stained Calcite");
            translationBuilder.add(GBWBlocks.YELLOW_STAINED_CALCITE, "Yellow Stained Calcite");
            translationBuilder.add(GBWBlocks.LIME_STAINED_CALCITE, "Lime Stained Calcite");
            translationBuilder.add(GBWBlocks.GREEN_STAINED_CALCITE, "Green Stained Calcite");
            translationBuilder.add(GBWBlocks.CYAN_STAINED_CALCITE, "Cyan Stained Calcite");
            translationBuilder.add(GBWBlocks.BLUE_STAINED_CALCITE, "Blue Stained Calcite");
            translationBuilder.add(GBWBlocks.LIGHT_BLUE_STAINED_CALCITE, "Light Blue Stained Calcite");
            translationBuilder.add(GBWBlocks.PINK_STAINED_CALCITE, "Pink Stained Calcite");
            translationBuilder.add(GBWBlocks.MAGENTA_STAINED_CALCITE, "Magenta Stained Calcite");
            translationBuilder.add(GBWBlocks.PURPLE_STAINED_CALCITE, "Purple Stained Calcite");
            translationBuilder.add(GBWBlocks.BLACK_STAINED_CALCITE, "Black Stained Calcite");
            translationBuilder.add(GBWBlocks.GRAY_STAINED_CALCITE, "Gray Stained Calcite");
            translationBuilder.add(GBWBlocks.LIGHT_GRAY_STAINED_CALCITE, "Light Gray Stained Calcite");
            translationBuilder.add(GBWBlocks.WHITE_STAINED_CALCITE, "White Stained Calcite");
        }
    }

    private static class ModelProvider extends FabricModelProvider {
        public ModelProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
            blockStateModelGenerator.registerSimpleCubeAll(GBWBlocks.BROWN_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(GBWBlocks.RED_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(GBWBlocks.ORANGE_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(GBWBlocks.YELLOW_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(GBWBlocks.LIME_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(GBWBlocks.GREEN_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(GBWBlocks.CYAN_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(GBWBlocks.BLUE_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(GBWBlocks.LIGHT_BLUE_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(GBWBlocks.PINK_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(GBWBlocks.MAGENTA_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(GBWBlocks.PURPLE_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(GBWBlocks.BLACK_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(GBWBlocks.GRAY_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(GBWBlocks.LIGHT_GRAY_STAINED_CALCITE);
            blockStateModelGenerator.registerSimpleCubeAll(GBWBlocks.WHITE_STAINED_CALCITE);
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
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "brown_stained_calcite"), drops(GBWBlocks.BROWN_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "red_stained_calcite"), drops(GBWBlocks.RED_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "orange_stained_calcite"), drops(GBWBlocks.ORANGE_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "yellow_stained_calcite"), drops(GBWBlocks.YELLOW_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "lime_stained_calcite"), drops(GBWBlocks.LIME_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "green_stained_calcite"), drops(GBWBlocks.GREEN_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "cyan_stained_calcite"), drops(GBWBlocks.CYAN_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "blue_stained_calcite"), drops(GBWBlocks.BLUE_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "light_blue_stained_calcite"), drops(GBWBlocks.LIGHT_BLUE_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "pink_stained_calcite"), drops(GBWBlocks.PINK_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "magenta_stained_calcite"), drops(GBWBlocks.MAGENTA_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "purple_stained_calcite"), drops(GBWBlocks.PURPLE_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "black_stained_calcite"), drops(GBWBlocks.BLACK_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "gray_stained_calcite"), drops(GBWBlocks.GRAY_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "light_gray_stained_calcite"), drops(GBWBlocks.LIGHT_GRAY_STAINED_CALCITE));
            biConsumer.accept(new Identifier(GreatBigWorld.NAMESPACE, "white_stained_calcite"), drops(GBWBlocks.WHITE_STAINED_CALCITE));
        }

        public LootTable.Builder drops(ItemConvertible drop) {
            return LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1f)).with(ItemEntry.builder(drop)).getThisConditionConsumingBuilder());
        }
    }
}
