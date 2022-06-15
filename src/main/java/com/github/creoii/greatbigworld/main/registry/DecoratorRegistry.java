package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.world.decorator.HangingLeavesTreeDecorator;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class DecoratorRegistry {
    public static TreeDecoratorType<?> HANGING_LEAVES_DECORATOR;

    public static void register() {
        HANGING_LEAVES_DECORATOR = Registry.register(Registry.TREE_DECORATOR_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "hanging_leaves"), new TreeDecoratorType<HangingLeavesTreeDecorator>(HangingLeavesTreeDecorator.CODEC));
    }
}
