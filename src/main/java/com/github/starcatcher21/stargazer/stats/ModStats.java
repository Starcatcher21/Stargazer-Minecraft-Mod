package com.github.starcatcher21.stargazer.stats;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

public class ModStats {
    public static final Identifier STAR_CATCHED = register("star_catched", StatFormatter.DEFAULT);

    private static Identifier register(String id, StatFormatter formatter) {
        Identifier identifier = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, id);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, id, identifier);
        Stats.CUSTOM.get(identifier, formatter);
        return identifier;
    }
    public static void init() {}
}
