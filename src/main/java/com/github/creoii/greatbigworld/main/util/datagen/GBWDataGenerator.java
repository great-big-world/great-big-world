package com.github.creoii.greatbigworld.main.util.datagen;

import com.github.creoii.creolib.api.util.registry.RegistrySets;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.GBWBlocks;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
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
                        translationBuilder.add(block, StringUtils.remove(capitalizeAfterAll(field.getName(), '_'), '_'));
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
