package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.block.blockentity.ReceptorBlockEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockEntityRegistry {
    public static final BlockEntityType<ReceptorBlockEntity> RECEPTOR = FabricBlockEntityTypeBuilder.create(ReceptorBlockEntity::new, BlockRegistry.RECEPTOR).build();

    public static void register() {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "receptor"), RECEPTOR);
    }
}
