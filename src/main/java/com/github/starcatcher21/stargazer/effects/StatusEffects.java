package com.github.starcatcher21.stargazer.effects;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class StatusEffects {
    public static RegistryEntry<StatusEffect> HYDRO = register("hydrophobic", new Hydrophobic(StatusEffectCategory.HARMFUL, 521));
    public static RegistryEntry<StatusEffect> COSMO = register("cosmofeeling", new CosmoFeeling(StatusEffectCategory.BENEFICIAL, 3500));
    public static RegistryEntry<StatusEffect> GLASS = register("glasshands", new GlassHands(StatusEffectCategory.HARMFUL, 60460));

    public static RegistryEntry<StatusEffect> register(String path, StatusEffect status) {
        final Identifier identifier = Identifier.of(Stargazer.MOD_ID, path);
        return Registry.registerReference(Registries.STATUS_EFFECT, identifier, status);
    }

    public static void init() {

    }
}
