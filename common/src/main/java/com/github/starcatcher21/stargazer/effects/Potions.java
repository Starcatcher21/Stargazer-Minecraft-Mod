package com.github.starcatcher21.stargazer.effects;

import com.github.starcatcher21.stargazer.Stargazer;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class Potions {
    public static final DeferredRegister<Potion> EFFECTS = DeferredRegister.create(Stargazer.MOD_ID, Registries.POTION);

    public static final RegistrySupplier<Potion> CosmoFeel = register("cosmofeeling", new Potion("Cosmo Feeling", new MobEffectInstance(StatusEffects.COSMO.asHolder(), 3600)));
    public static final RegistrySupplier<Potion> GlassHands = register("glasshands", new Potion("Glass Hands", new MobEffectInstance(StatusEffects.GLASS.asHolder(), 3600)));
    public static final RegistrySupplier<Potion> Hydro = register("hydrophobic", new Potion("HydroPhobia", new MobEffectInstance(StatusEffects.HYDRO.asHolder(), 3600)));

    private static RegistrySupplier<Potion> register(String name, Potion potion) {
        return EFFECTS.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, name), () -> potion);
    }

    public static void init() {
        EFFECTS.register();
    }
}
