package com.github.starcatcher21.stargazer.worldgen;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.List;

public class BiomeReg {
    public static final RegistryKey<Biome> MOON_FOREST = register("moon_forest");
    public static final RegistryKey<Biome> MOON_WASTE = register("moon_waste");
    public static final RegistryKey<Biome> AMETHYST_SPIKES = register("amethyst_spikes");
    public static final RegistryKey<Biome> BONE_FOREST = register("bone_forest");
    public static final RegistryKey<Biome> EYE_BIRCH_FOREST = register("eye_birch_forest");
    public static final RegistryKey<Biome> PURPLE_SHROOM_FOREST = register("purple_shroom_forest");

    public static final List<RegistryKey<Biome>> MoonList = List.of(MOON_FOREST, MOON_WASTE, AMETHYST_SPIKES, BONE_FOREST, EYE_BIRCH_FOREST, PURPLE_SHROOM_FOREST);

    public static RegistryKey<Biome> register(String path) {
        return RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Stargazer.MOD_ID, path));
    }
    public static void init() {}
}
