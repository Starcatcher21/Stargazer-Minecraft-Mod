package com.github.starcatcher21.stargazer.mechanics.advancements;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.advancements.triggers.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class Criterias {
    public static final Starcatching starcatching = register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "starcatching").toString(), new Starcatching());
    public static final StarModifier starModifier = register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "star_modifier").toString(), new StarModifier());
    public static final CosmicPortal cosmicPortal = register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic_portal").toString(), new CosmicPortal());
    public static final StarTrap starTrap = register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "star_trap").toString(), new StarTrap());
    public static final ForgeCraft forgeCraft = register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "forge_craft").toString(), new ForgeCraft());
    public static final MoonWeld moonWeld = register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "moon_weld").toString(), new MoonWeld());
    public static final Negative negative = register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "negative").toString(), new Negative());

    private static <T extends CriterionTrigger<?>> T register(final String name, final T criterion) {
        return (T)(Registry.register(BuiltInRegistries.TRIGGER_TYPES, name, criterion));
    }

    public static void init() {
    }
}
