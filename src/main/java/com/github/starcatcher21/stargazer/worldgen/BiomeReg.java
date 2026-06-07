package com.github.starcatcher21.stargazer.worldgen;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.List;

public class BiomeReg {
    public static final RegistryKey<Biome> MOON_FOREST = register("moon_forest");
    public static final RegistryKey<Biome> MOON_WASTE = register("moon_waste");
    public static final RegistryKey<Biome> AMETHYST_SPIKES = register("amethyst_spikes");
    public static final RegistryKey<Biome> BONE_FOREST = register("bone_forest");
    public static final RegistryKey<Biome> EYE_BIRCH_FOREST = register("eye_birch_forest");
    public static final RegistryKey<Biome> PURPLE_SHROOM_FOREST = register("purple_shroom_forest");
    public static final RegistryKey<Biome> DARKNESS_FOREST = register("darkness_forest");
    public static final RegistryKey<Biome> MOON_FIELD = register("moon_field");
    public static final RegistryKey<Biome> MOON_VOID = register("moon_void");

    public static final RegistryKey<Biome> REDNESS = register("redness");
    public static final RegistryKey<Biome> YERI_FOREST = register("yeri_forest");
    public static final RegistryKey<Biome> GREEN_ROCK_VALLEY = register("green_rock_valley");

    public static final List<RegistryKey<Biome>> MoonList = List.of(MOON_FOREST, MOON_WASTE, AMETHYST_SPIKES, BONE_FOREST, EYE_BIRCH_FOREST, PURPLE_SHROOM_FOREST, DARKNESS_FOREST, MOON_FIELD, MOON_VOID);
    public static final List<RegistryKey<Biome>> RedList = List.of(REDNESS, YERI_FOREST, GREEN_ROCK_VALLEY);

    public static RegistryKey<Biome> register(String path) {
        return RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Stargazer.MOD_ID, path));
    }
    public static void init() {}
}
