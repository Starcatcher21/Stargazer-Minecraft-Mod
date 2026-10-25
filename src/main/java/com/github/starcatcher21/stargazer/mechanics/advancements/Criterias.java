package com.github.starcatcher21.stargazer.mechanics.advancements;

import com.github.starcatcher21.stargazer.Stargazer;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
//? if >= 26.2 {
import net.minecraft.advancements.triggers.CriterionTrigger;
//? } else {
/*import net.minecraft.advancements.CriteriaTriggers;
*///? }
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
//? if < 26.2 {
/*import net.minecraft.advancements.CriterionTrigger;
import net.neoforged.bus.api.IEventBus;
*///?}

public class Criterias {
    //? if >= 26.2 {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS =
            DeferredRegister.create(Stargazer.MOD_ID, Registries.TRIGGER_TYPE);
    //? }

    //? if >= 26.2 {
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
    //? } else {
    /*public static final Starcatching STARCATCHING = new Starcatching();
    public static final StarModifier STAR_MODIFIER = new StarModifier();
    public static final CosmicPortal COSMIC_PORTAL = new CosmicPortal();
    public static final StarTrap STAR_TRAP = new StarTrap();
    public static final ForgeCraft FORGE_CRAFT = new ForgeCraft();
    public static final MoonWeld MOON_WELD = new MoonWeld();
    public static final Negative NEGATIVE = new Negative();
    *///? }

    //? if < 26.2 {
    /*private static final net.neoforged.neoforge.registries.DeferredRegister<CriterionTrigger<?>> TRIGGERS =
            net.neoforged.neoforge.registries.DeferredRegister.create(Registries.TRIGGER_TYPE, Stargazer.MOD_ID);

    static {
        TRIGGERS.register("starcatching", () -> STARCATCHING);
        TRIGGERS.register("star_modifier", () -> STAR_MODIFIER);
        TRIGGERS.register("cosmic_portal", () -> COSMIC_PORTAL);
        TRIGGERS.register("star_trap", () -> STAR_TRAP);
        TRIGGERS.register("forge_craft", () -> FORGE_CRAFT);
        TRIGGERS.register("moon_weld", () -> MOON_WELD);
        TRIGGERS.register("negative", () -> NEGATIVE);
    }
    *///?}

    public static void init() {
        //? if >= 26.2 {
        TRIGGERS.register();
        //? } else {
        //? }
    }

    //? if < 26.2 {
    /*public static void init(IEventBus modEventBus) {
        TRIGGERS.register(modEventBus);
    }
    *///?}
}
