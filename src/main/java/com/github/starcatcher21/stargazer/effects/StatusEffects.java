package com.github.starcatcher21.stargazer.effects;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class StatusEffects {
    public static Holder<MobEffect> HYDRO = register("hydrophobic", new Hydrophobic(MobEffectCategory.HARMFUL, 521));
    public static Holder<MobEffect> COSMO = register("cosmofeeling", new CosmoFeeling(MobEffectCategory.BENEFICIAL, 3500));
    public static Holder<MobEffect> GLASS = register("glasshands", new GlassHands(MobEffectCategory.HARMFUL, 60460));

    public static Holder<MobEffect> register(String path, MobEffect status) {
        final Identifier identifier = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, path);
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, identifier, status);
    }

    public static void init() {

    }
}
