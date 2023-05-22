package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.mixin.world.TreeDecoratorTypeInvoker;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.decorator.AcaiBerriesTreeDecorator;
import com.github.creoii.greatbigworld.world.decorator.HangingLeavesTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class GBWDecorators implements Register {
    public static TreeDecoratorType<AcaiBerriesTreeDecorator> ACAI_BERRIES_TREE_DECORATOR;
    public static TreeDecoratorType<HangingLeavesTreeDecorator> HANGING_LEAVES_TREE_DECORATOR;

    @Override
    public void register() {
        ACAI_BERRIES_TREE_DECORATOR = TreeDecoratorTypeInvoker.callRegister("acai_berries", AcaiBerriesTreeDecorator.CODEC);
        HANGING_LEAVES_TREE_DECORATOR = TreeDecoratorTypeInvoker.callRegister("hanging_leaves", HangingLeavesTreeDecorator.CODEC);
    }
}
