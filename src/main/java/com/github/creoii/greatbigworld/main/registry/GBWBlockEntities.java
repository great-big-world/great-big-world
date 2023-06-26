package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.block.entity.KilnBlockEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class GBWBlockEntities implements Register {
    public static final BlockEntityType<KilnBlockEntity> KILN = FabricBlockEntityTypeBuilder.create(KilnBlockEntity::new, GBWBlocks.KILN).build();

    @Override
    public void register() {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "kiln"), KILN);
    }
}
