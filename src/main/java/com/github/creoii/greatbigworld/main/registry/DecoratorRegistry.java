package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.decorator.BranchTreeDecorator;
import com.github.creoii.greatbigworld.world.decorator.HangingLeavesTreeDecorator;
import com.github.creoii.greatbigworld.world.decorator.AcaiBerriesTreeDecorator;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class DecoratorRegistry implements Register {
    public static final TreeDecoratorType<BranchTreeDecorator> BRANCH_TREE_DECORATOR = new TreeDecoratorType<>(BranchTreeDecorator.CODEC);
    public static final TreeDecoratorType<HangingLeavesTreeDecorator> HANGING_LEAVES_DECORATOR = new TreeDecoratorType<>(HangingLeavesTreeDecorator.CODEC);
    public static final TreeDecoratorType<AcaiBerriesTreeDecorator> ACAI_BERRIES_DECORATOR = new TreeDecoratorType<>(AcaiBerriesTreeDecorator.CODEC);

    @Override
    public void register() {
        Registry.register(Registries.TREE_DECORATOR_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "branch"), BRANCH_TREE_DECORATOR);
        Registry.register(Registries.TREE_DECORATOR_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "hanging_leaves"), HANGING_LEAVES_DECORATOR);
        Registry.register(Registries.TREE_DECORATOR_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "acai_berries"), ACAI_BERRIES_DECORATOR);
    }
}
