package com.github.starcatcher21.stargazer.effects;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class Potions {
    public static final RegistryEntry<Potion> CosmoFeel = register("cosmofeeling", new Potion("Cosmo Feeling", new StatusEffectInstance(StatusEffects.COSMO, 3600)));
    public static final RegistryEntry<Potion> GlassHands = register("glasshands", new Potion("Glass Hands", new StatusEffectInstance(StatusEffects.GLASS, 3600)));
    public static final RegistryEntry<Potion> Hydro = register("hydrophobic", new Potion("HydroPhobia", new StatusEffectInstance(StatusEffects.HYDRO, 3600)));

    private static RegistryEntry<Potion> register(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(Stargazer.MOD_ID, name), potion);
    }

    public static void init() {}
}
