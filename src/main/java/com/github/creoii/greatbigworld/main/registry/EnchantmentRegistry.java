package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.enchantment.AbsolutionCurseEnchantment;
import com.github.creoii.greatbigworld.enchantment.KineticProtectionEnchantment;
import com.github.creoii.greatbigworld.enchantment.PoweredEnchantment;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EnchantmentRegistry {
    public static final Enchantment POWERED = new PoweredEnchantment();
    public static final Enchantment KINETIC_PROTECTION = new KineticProtectionEnchantment();
    public static final Enchantment ABSOLUTION_CURSE = new AbsolutionCurseEnchantment();

    public static void register() {
        Registry.register(Registry.ENCHANTMENT, new Identifier(GreatBigWorld.NAMESPACE, "powered"), POWERED);
        Registry.register(Registry.ENCHANTMENT, new Identifier(GreatBigWorld.NAMESPACE, "kinetic_protection"), KINETIC_PROTECTION);
        Registry.register(Registry.ENCHANTMENT, new Identifier(GreatBigWorld.NAMESPACE, "absolution_curse"), ABSOLUTION_CURSE);
    }
}
