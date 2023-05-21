package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.mixin.world.FoliagePlacerTypeInvoker;
import com.github.creoii.greatbigworld.main.mixin.world.TreeDecoratorTypeInvoker;
import com.github.creoii.greatbigworld.main.mixin.world.TrunkPlacerTypeInvoker;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.decorator.AcaiBerriesTreeDecorator;
import com.github.creoii.greatbigworld.world.decorator.BranchTreeDecorator;
import com.github.creoii.greatbigworld.world.decorator.HangingLeavesTreeDecorator;
import com.github.creoii.greatbigworld.world.placer.AspenFoliagePlacer;
import com.github.creoii.greatbigworld.world.placer.PalmFoliagePlacer;
import com.github.creoii.greatbigworld.world.placer.TwistingTrunkPlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class GBWPlacers implements Register {
    public static TrunkPlacerType<TwistingTrunkPlacer> TWISTING_TRUNK_PLACER;
    public static FoliagePlacerType<AspenFoliagePlacer> ASPEN_FOLIAGE_PLACER;
    public static FoliagePlacerType<PalmFoliagePlacer> PALM_FOLIAGE_PLACER;
    public static TreeDecoratorType<BranchTreeDecorator> BRANCH_TREE_DECORATOR;
    public static TreeDecoratorType<AcaiBerriesTreeDecorator> ACAI_BERRIES_TREE_DECORATOR;
    public static TreeDecoratorType<HangingLeavesTreeDecorator> HANGING_LEAVES_TREE_DECORATOR;

    public void register() {
        TWISTING_TRUNK_PLACER = TrunkPlacerTypeInvoker.callRegister("twisting_trunk_placer", TwistingTrunkPlacer.CODEC);
        ASPEN_FOLIAGE_PLACER = FoliagePlacerTypeInvoker.callRegister("aspen_foliage_placer", AspenFoliagePlacer.CODEC);
        PALM_FOLIAGE_PLACER = FoliagePlacerTypeInvoker.callRegister("palm_foliage_placer", PalmFoliagePlacer.CODEC);
        BRANCH_TREE_DECORATOR = TreeDecoratorTypeInvoker.callRegister("branch", BranchTreeDecorator.CODEC);
        ACAI_BERRIES_TREE_DECORATOR = TreeDecoratorTypeInvoker.callRegister("acai_berries", AcaiBerriesTreeDecorator.CODEC);
        HANGING_LEAVES_TREE_DECORATOR = TreeDecoratorTypeInvoker.callRegister("hanging_leaves", HangingLeavesTreeDecorator.CODEC);
    }
}
