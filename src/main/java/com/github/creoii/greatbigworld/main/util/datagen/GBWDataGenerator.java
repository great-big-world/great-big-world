package com.github.creoii.greatbigworld.main.util.datagen;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class GBWDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(LangProvider::new);
        pack.addProvider(ModelProvider::new);
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
}
