package com.github.starcatcher21.stargazer.stats;

import com.github.starcatcher21.stargazer.Stargazer;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

public class ModStats {
    public static final DeferredRegister<Identifier> STATS =
            DeferredRegister.create(Stargazer.MOD_ID, Registries.CUSTOM_STAT);

    public static final RegistrySupplier<Identifier> STAR_CATCHED = register("star_catched");

    private static RegistrySupplier<Identifier> register(String id) {
        ResourceLocation identifier =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, id);
        return STATS.register(id, () -> identifier);
    }

    public static void init() {
        STATS.register();
    }

    public static void setup() {
        Stats.CUSTOM.get(STAR_CATCHED.get(), StatFormatter.DEFAULT);
    }
}
