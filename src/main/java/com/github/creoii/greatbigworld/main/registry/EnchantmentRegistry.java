package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.enchantment.DilutingEnchantment;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EnchantmentRegistry implements Register {
    public static final Enchantment DILUTING = new DilutingEnchantment();

    @Override
    public void register() {
        Registry.register(Registries.ENCHANTMENT, new Identifier(GreatBigWorld.NAMESPACE, "diluting"), DILUTING);
    }
}
