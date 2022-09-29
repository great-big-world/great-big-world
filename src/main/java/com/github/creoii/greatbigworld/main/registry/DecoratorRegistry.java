package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.decorator.BranchTreeDecorator;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class DecoratorRegistry implements Register {
    public static final TreeDecoratorType<BranchTreeDecorator> BRANCH_TREE_DECORATOR = new TreeDecoratorType<>(BranchTreeDecorator.CODEC);

    @Override
    public void register() {
        Registry.register(Registry.TREE_DECORATOR_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "branch_tree_decorator"), BRANCH_TREE_DECORATOR);
    }
}
