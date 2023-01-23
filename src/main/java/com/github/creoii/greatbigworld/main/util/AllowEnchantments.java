package com.github.creoii.greatbigworld.main.util;

import net.minecraft.enchantment.Enchantment;

import java.util.function.Predicate;

public interface AllowEnchantments {
    Predicate<Enchantment> getAllowedEnchantments();
}
