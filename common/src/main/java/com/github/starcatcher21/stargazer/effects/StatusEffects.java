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
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.Item;

public class StatusEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Stargazer.MOD_ID, Registries.MOB_EFFECT);

    public static RegistrySupplier<MobEffect> HYDRO = register("hydrophobic", new Hydrophobic(MobEffectCategory.HARMFUL, 521));
    public static RegistrySupplier<MobEffect> COSMO = register("cosmofeeling", new CosmoFeeling(MobEffectCategory.BENEFICIAL, 3500));
    public static RegistrySupplier<MobEffect> GLASS = register("glasshands", new GlassHands(MobEffectCategory.HARMFUL, 60460));

    public static RegistrySupplier<MobEffect> register(String path, MobEffect status) {
        final Identifier identifier = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, path);
        return EFFECTS.register(identifier, () -> status);
    }

    public static void init() {
        EFFECTS.register();
    }
}
