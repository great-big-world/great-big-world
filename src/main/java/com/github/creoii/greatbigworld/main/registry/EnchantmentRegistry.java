package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.enchantment.DilutingEnchantment;
import com.github.creoii.greatbigworld.enchantment.PoisonGlazeEnchantment;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EnchantmentRegistry implements Register {
    public static final Enchantment DILUTING = new DilutingEnchantment();
    public static final Enchantment POISON_GLAZE = new PoisonGlazeEnchantment();

    @Override
    public void register() {
        Registry.register(Registry.ENCHANTMENT, new Identifier(GreatBigWorld.NAMESPACE, "diluting"), DILUTING);
        Registry.register(Registry.ENCHANTMENT, new Identifier(GreatBigWorld.NAMESPACE, "poison_glaze"), POISON_GLAZE);
    }
}
