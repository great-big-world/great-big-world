package com.github.creoii.greatbigworld.block.base;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VariantBlock extends Block {
    private final Variant variant;

    public VariantBlock(Variant variant, Settings settings) {
        super(settings);
        this.variant = variant;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable(variant.translationKey).formatted(Formatting.GRAY));
    }

    public enum Variant {
        CHISELED("block_variant.chiseled"),
        PILLAR("block_variant.chiseled"),
        TILES("block_variant.tiles");

        private final String translationKey;

        Variant(String translationKey) {
            this.translationKey = translationKey;
        }
    }
}
