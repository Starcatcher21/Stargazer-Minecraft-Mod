package com.github.starcatcher21.stargazer.effects;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class Potions {
    public static final Holder<Potion> CosmoFeel = register("cosmofeeling", new Potion("Cosmo Feeling", new MobEffectInstance(StatusEffects.COSMO, 3600)));
    public static final Holder<Potion> GlassHands = register("glasshands", new Potion("Glass Hands", new MobEffectInstance(StatusEffects.GLASS, 3600)));
    public static final Holder<Potion> Hydro = register("hydrophobic", new Potion("HydroPhobia", new MobEffectInstance(StatusEffects.HYDRO, 3600)));

    private static Holder<Potion> register(String name, Potion potion) {
        return Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, name), potion);
    }

    public static void init() {}
}
