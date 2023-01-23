package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.decorator.BranchTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class DecoratorRegistry implements Register {
    public static TreeDecoratorType<BranchTreeDecorator> BRANCH_TREE_DECORATOR;

    @Override
    public void register() {
        BRANCH_TREE_DECORATOR = TreeDecoratorType.register("branch", BranchTreeDecorator.CODEC);
    }
}
