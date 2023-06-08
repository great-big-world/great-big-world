package com.github.creoii.greatbigworld.main.integration;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.FloatSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.ConfigEntry;
import net.minecraft.text.Text;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.apache.commons.lang3.mutable.MutableInt;

public class GBWConfig {
    @ConfigEntry
    public MutableBoolean bambooTorchesOnLeaves = new MutableBoolean(true);

    @ConfigEntry
    public MutableBoolean masksAngerEndermen = new MutableBoolean(false);

    @ConfigEntry
    public MutableBoolean pillagersAttackMoose = new MutableBoolean(true);

    @ConfigEntry
    public MutableBoolean wolvesAttackMoose = new MutableBoolean(true);

    @ConfigEntry
    public MutableInt maxDistanceForRootConversion = new MutableInt(4);

    @ConfigEntry
    public MutableInt shedAntlerBaseRegrowTime = new MutableInt(4800);

    @ConfigEntry
    public MutableInt nautilusOxidizeChance = new MutableInt(1200);

    @ConfigEntry
    public MutableFloat dilutingModifier = new MutableFloat(.5f);

    @ConfigEntry
    public MutableFloat auraEffectTransferModifier = new MutableFloat(.5f);

    @ConfigEntry
    public MutableInt woodenMaskDurability = new MutableInt(140);

    @ConfigEntry
    public MutableInt woodenMaskProtection = new MutableInt(2);

    @ConfigEntry
    public MutableFloat woodenMaskToughness = new MutableFloat(0f);

    @ConfigEntry
    public MutableInt woodenMaskEnchantability = new MutableInt(11);

    public YetAnotherConfigLib getYACL() {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("text.great_big_world.config.title"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("text.great_big_world.config.general"))
                        .option(createBooleanOption(
                                Text.translatable("text.great_big_world.config.option.bambooTorchesOnLeaves"),
                                Text.translatable("text.great_big_world.config.option.bambooTorchesOnLeaves.@Tooltip"),
                                bambooTorchesOnLeaves, true))
                        .option(createBooleanOption(
                                Text.translatable("text.great_big_world.config.option.masksAngerEndermen"),
                                Text.translatable("text.great_big_world.config.option.masksAngerEndermen.@Tooltip"),
                                masksAngerEndermen, false))
                        .option(createBooleanOption(
                                Text.translatable("text.great_big_world.config.option.pillagersAttackMoose"),
                                Text.translatable("text.great_big_world.config.option.pillagersAttackMoose.@Tooltip"),
                                pillagersAttackMoose, true))
                        .option(createBooleanOption(
                                Text.translatable("text.great_big_world.config.option.wolvesAttackMoose"),
                                Text.translatable("text.great_big_world.config.option.wolvesAttackMoose.@Tooltip"),
                                wolvesAttackMoose, true))

                        .option(createIntegerOption(
                                Text.translatable("text.great_big_world.config.option.maxDistanceForRootConversion"),
                                Text.translatable("text.great_big_world.config.option.maxDistanceForRootConversion.@Tooltip"),
                                maxDistanceForRootConversion, 4, -1, 16))
                        .option(createIntegerOption(
                                Text.translatable("text.great_big_world.config.option.shedAntlerBaseRegrowTime"),
                                Text.translatable("text.great_big_world.config.option.shedAntlerBaseRegrowTime.@Tooltip"),
                                shedAntlerBaseRegrowTime, 4800, -1, 24000, 600))
                        .option(createIntegerOption(
                                Text.translatable("text.great_big_world.config.option.nautilusOxidizeChance"),
                                Text.translatable("text.great_big_world.config.option.nautilusOxidizeChance.@Tooltip"),
                                nautilusOxidizeChance, 1200, -1, 12000, 300))

                        .option(createFloatOption(
                                Text.translatable("text.great_big_world.config.option.dilutingModifier"),
                                Text.translatable("text.great_big_world.config.option.dilutingModifier.@Tooltip"),
                                dilutingModifier, .5f, 0, 16f, .5f))
                        .option(createFloatOption(
                                Text.translatable("text.great_big_world.config.option.auraEffectTransferModifier"),
                                Text.translatable("text.great_big_world.config.option.auraEffectTransferModifier.@Tooltip"),
                                auraEffectTransferModifier, .5f, 0, 16f, .5f))

                        .option(createIntegerOption(
                                Text.translatable("text.great_big_world.config.option.woodenMaskDurability"),
                                Text.translatable("text.great_big_world.config.option.woodenMaskDurability.@Tooltip"),
                                woodenMaskDurability, 140, 0, 1200, 20))
                        .option(createIntegerOption(
                                Text.translatable("text.great_big_world.config.option.woodenMaskProtection"),
                                Text.translatable("text.great_big_world.config.option.woodenMaskProtection.@Tooltip"),
                                woodenMaskProtection, 2, 0, 50, 2))
                        .option(createFloatOption(
                                Text.translatable("text.great_big_world.config.option.woodenMaskToughness"),
                                Text.translatable("text.great_big_world.config.option.woodenMaskToughness.@Tooltip"),
                                woodenMaskToughness, 0f, 0, 16, .5f))
                        .option(createIntegerOption(
                                Text.translatable("text.great_big_world.config.option.woodenMaskEnchantability"),
                                Text.translatable("text.great_big_world.config.option.woodenMaskEnchantability.@Tooltip"),
                                woodenMaskEnchantability, 11, 0, 50, 1))
                        .build())
                .build();
    }

    @SuppressWarnings("deprecation")
    public Option<Boolean> createBooleanOption(Text name, Text description, MutableBoolean option, boolean def) {
        return Option.createBuilder(Boolean.class)
                .name(name)
                .description(OptionDescription.createBuilder()
                        .text(description)
                        .build())
                .binding(def, option::booleanValue, option::setValue)
                .controller(TickBoxControllerBuilder::create)
                .build();
    }

    @SuppressWarnings("deprecation")
    public Option<Integer> createIntegerOption(Text name, Text description, MutableInt option, int def, int min, int max, int interval) {
        return Option.createBuilder(Integer.class)
                .name(name)
                .description(OptionDescription.createBuilder()
                        .text(description)
                        .build())
                .binding(def, option::intValue, option::setValue)
                .controller(integerOption -> IntegerSliderControllerBuilder.create(integerOption)
                        .range(min, max)
                        .step(interval))
                .build();
    }

    public Option<Integer> createIntegerOption(Text name, Text description, MutableInt option, int def, int min, int max) {
        return createIntegerOption(name, description, option, def, min, max, 1);
    }

    @SuppressWarnings("deprecation")
    public Option<Float> createFloatOption(Text name, Text description, MutableFloat option, float def, float min, float max, float interval) {
        return Option.createBuilder(Float.class)
                .name(name)
                .description(OptionDescription.createBuilder()
                        .text(description)
                        .build())
                .binding(def, option::floatValue, option::setValue)
                .controller(floatOption -> FloatSliderControllerBuilder.create(floatOption)
                        .range(min, max)
                        .step(interval))
                .build();
    }
}
