package com.github.starcatcher21.stargazer.mechanics.advancements;

import com.github.starcatcher21.stargazer.Stargazer;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.advancements.triggers.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;

public class Criterias {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS =
            DeferredRegister.create(Stargazer.MOD_ID, Registries.TRIGGER_TYPE);

    public static final RegistrySupplier<Starcatching> STARCATCHING = register("starcatching", new Starcatching());
    public static final RegistrySupplier<StarModifier> STAR_MODIFIER = register("star_modifier", new StarModifier());
    public static final RegistrySupplier<CosmicPortal> COSMIC_PORTAL = register("cosmic_portal", new CosmicPortal());
    public static final RegistrySupplier<StarTrap> STAR_TRAP = register("star_trap", new StarTrap());
    public static final RegistrySupplier<ForgeCraft> FORGE_CRAFT = register("forge_craft", new ForgeCraft());
    public static final RegistrySupplier<MoonWeld> MOON_WELD = register("moon_weld", new MoonWeld());
    public static final RegistrySupplier<Negative> NEGATIVE = register("negative", new Negative());

    private static <T extends CriterionTrigger<?>> RegistrySupplier<T> register(String name, T criterion) {
        return TRIGGERS.register(name, () -> criterion);
    }

    public static void init() {
        TRIGGERS.register();
    }
}