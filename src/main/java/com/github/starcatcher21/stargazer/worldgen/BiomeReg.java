package com.github.starcatcher21.stargazer.worldgen;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class BiomeReg {
    public static final RegistryKey<Biome> MOON_FOREST = register("moon_forest");
    public static final RegistryKey<Biome> MOON_WASTE = register("moon_waste");

    public static RegistryKey<Biome> register(String path) {
        return RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Stargazer.MOD_ID, path));
    }
    public static void init() {}
}
