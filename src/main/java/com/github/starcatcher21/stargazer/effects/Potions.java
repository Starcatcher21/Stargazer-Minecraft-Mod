package com.github.starcatcher21.stargazer.effects;

import com.github.starcatcher21.stargazer.Stargazer;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class Potions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(Stargazer.MOD_ID, Registries.POTION);

    public static final RegistrySupplier<Potion> CosmoFeel =
            register("cosmofeeling", StatusEffects.COSMO, 3600);
    public static final RegistrySupplier<Potion> GlassHands =
            register("glasshands", StatusEffects.GLASS, 3600);
    public static final RegistrySupplier<Potion> Hydro =
            register("hydrophobic", StatusEffects.HYDRO, 3600);

    private static RegistrySupplier<Potion> register(
            String name, RegistrySupplier<MobEffect> effect, int duration) {
        return POTIONS.register(
              ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, name),
                () -> new Potion(name, new MobEffectInstance(effect.asHolder(), duration))
        );
    }

    public static void init() {
        POTIONS.register();
    }
}