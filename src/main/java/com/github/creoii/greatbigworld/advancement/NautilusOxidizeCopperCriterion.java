package com.github.creoii.greatbigworld.advancement;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.google.gson.JsonObject;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class NautilusOxidizeCopperCriterion extends AbstractCriterion<NautilusOxidizeCopperCriterion.Conditions> {
    static final Identifier IDENTIFIER = new Identifier(GreatBigWorld.NAMESPACE, "nautilus_oxidize_copper");

    @Override
    protected Conditions conditionsFromJson(JsonObject obj, EntityPredicate.Extended playerPredicate, AdvancementEntityPredicateDeserializer predicateDeserializer) {
        return new Conditions(playerPredicate);
    }

    public void trigger(ServerPlayerEntity player) {
        super.trigger(player, conditions -> true);
    }

    @Override
    public Identifier getId() {
        return IDENTIFIER;
    }

    public static class Conditions extends AbstractCriterionConditions {
        public Conditions(EntityPredicate.Extended player) {
            super(IDENTIFIER, player);
        }
    }
}