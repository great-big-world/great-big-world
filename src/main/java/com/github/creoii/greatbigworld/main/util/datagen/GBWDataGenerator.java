package com.github.creoii.greatbigworld.main.util.datagen;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
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
            translationBuilder.add(BlockRegistry.BROWN_STAINED_CALCITE, "Brown Stained Calcite");
            translationBuilder.add(BlockRegistry.RED_STAINED_CALCITE, "Red Stained Calcite");
            translationBuilder.add(BlockRegistry.ORANGE_STAINED_CALCITE, "Orange Stained Calcite");
            translationBuilder.add(BlockRegistry.YELLOW_STAINED_CALCITE, "Yellow Stained Calcite");
            translationBuilder.add(BlockRegistry.LIME_STAINED_CALCITE, "Lime Stained Calcite");
            translationBuilder.add(BlockRegistry.GREEN_STAINED_CALCITE, "Green Stained Calcite");
            translationBuilder.add(BlockRegistry.CYAN_STAINED_CALCITE, "Cyan Stained Calcite");
            translationBuilder.add(BlockRegistry.BLUE_STAINED_CALCITE, "Blue Stained Calcite");
            translationBuilder.add(BlockRegistry.LIGHT_BLUE_STAINED_CALCITE, "Light Blue Stained Calcite");
            translationBuilder.add(BlockRegistry.PINK_STAINED_CALCITE, "Pink Stained Calcite");
            translationBuilder.add(BlockRegistry.MAGENTA_STAINED_CALCITE, "Magenta Stained Calcite");
            translationBuilder.add(BlockRegistry.PURPLE_STAINED_CALCITE, "Purple Stained Calcite");
            translationBuilder.add(BlockRegistry.BLACK_STAINED_CALCITE, "Black Stained Calcite");
            translationBuilder.add(BlockRegistry.GRAY_STAINED_CALCITE, "Gray Stained Calcite");
            translationBuilder.add(BlockRegistry.LIGHT_GRAY_STAINED_CALCITE, "Light Gray Stained Calcite");
            translationBuilder.add(BlockRegistry.WHITE_STAINED_CALCITE, "White Stained Calcite");
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
}
