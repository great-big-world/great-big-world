package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.entity.LocationPredicate;

public class GBWLootTables implements Register {
    @Override
    public void register() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (id.equals(LootTables.BURIED_TREASURE_CHEST) && source.isBuiltin()) {
                tableBuilder.pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1f))
                        .with(ItemEntry.builder(GBWItems.MUSIC_DISC_PINA_COLADA))
                        .conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder.create().biome(GBWBiomes.ISLAND_BEACH)))
                );
            }
        });
    }
}
