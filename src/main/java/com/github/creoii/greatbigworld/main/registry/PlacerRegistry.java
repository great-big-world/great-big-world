package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.mixin.world.FoliagePlacerTypeInvoker;
import com.github.creoii.greatbigworld.main.mixin.world.TreeDecoratorTypeInvoker;
import com.github.creoii.greatbigworld.main.mixin.world.TrunkPlacerTypeInvoker;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.decorator.BranchTreeDecorator;
import com.github.creoii.greatbigworld.world.placer.AspenFoliagePlacer;
import com.github.creoii.greatbigworld.world.placer.TwistingTrunkPlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class PlacerRegistry implements Register {
    public static TrunkPlacerType<TwistingTrunkPlacer> TWISTING_TRUNK_PLACER;
    public static FoliagePlacerType<AspenFoliagePlacer> ASPEN_FOLIAGE_PLACER;
    public static TreeDecoratorType<BranchTreeDecorator> BRANCH_TREE_DECORATOR;

    public void register() {
        TWISTING_TRUNK_PLACER = TrunkPlacerTypeInvoker.callRegister("twisting_trunk_placer", TwistingTrunkPlacer.CODEC);
        ASPEN_FOLIAGE_PLACER = FoliagePlacerTypeInvoker.callRegister("aspen_foliage_placer", AspenFoliagePlacer.CODEC);
        BRANCH_TREE_DECORATOR = TreeDecoratorTypeInvoker.callRegister("branch", BranchTreeDecorator.CODEC);
    }
}
