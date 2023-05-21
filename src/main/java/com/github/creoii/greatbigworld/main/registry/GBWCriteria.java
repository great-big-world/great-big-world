package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.advancement.NautilusOxidizeCopperCriterion;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.advancement.criterion.Criteria;

public class GBWCriteria implements Register {
    public static NautilusOxidizeCopperCriterion NAUTILUS_OXIDIZE_COPPER;

    @Override
    public void register() {
        NAUTILUS_OXIDIZE_COPPER = Criteria.register(new NautilusOxidizeCopperCriterion());
    }
}
