package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.creolib.api.util.advancement.AdvancementInjection;
import com.github.creoii.creolib.api.util.advancement.CriteriaInjector;
import com.github.creoii.creolib.api.util.advancement.RequirementsInjector;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.advancement.criterion.BredAnimalsCriterion;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.util.Identifier;

public class GBWAdvancements implements Register {
    @Override
    public void register() {
        AdvancementInjection.register(new Identifier("minecraft", "husbandry/bred_all_animals"),
                new CriteriaInjector(EntityType.getId(GBWEntityTypes.MOOSE).toString(), BredAnimalsCriterion.Conditions.create(EntityPredicate.Builder.create().type(GBWEntityTypes.MOOSE))),
                new RequirementsInjector(EntityType.getId(GBWEntityTypes.MOOSE).toString())
        );
    }
}
