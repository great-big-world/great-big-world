package com.github.creoii.greatbigworld.main.util.datagen;

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
        public void generateTranslations(TranslationBuilder translationBuilder) {}
    }

    private static class ModelProvider extends FabricModelProvider {
        public ModelProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

        @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        }
    }

    public static class LootTableProvider extends SimpleFabricLootTableProvider {
        public LootTableProvider(FabricDataOutput output) {
            super(output, LootContextTypes.BLOCK);
        }

        @Override
        public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {}

        public LootTable.Builder drops(ItemConvertible drop) {
            return LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1f)).with(ItemEntry.builder(drop)).getThisConditionConsumingBuilder());
        }
    }
}
