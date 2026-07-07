package com.github.starcatcher21.stargazer.mechanics.advancements;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.resources.Identifier;

public class Criterias {
    public static final Starcatching starcatching = CriteriaTriggers.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "starcatching").toString(), new Starcatching());
    public static final StarModifier starModifier = CriteriaTriggers.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "star_modifier").toString(), new StarModifier());
    public static final CosmicPortal cosmicPortal = CriteriaTriggers.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic_portal").toString(), new CosmicPortal());
    public static final StarTrap starTrap = CriteriaTriggers.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "star_trap").toString(), new StarTrap());
    public static final ForgeCraft forgeCraft = CriteriaTriggers.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "forge_craft").toString(), new ForgeCraft());
    public static final MoonWeld moonWeld = CriteriaTriggers.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "moon_weld").toString(), new MoonWeld());
    public static final Negative negative = CriteriaTriggers.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "negative").toString(), new Negative());

    public static void init() {
    }
}
