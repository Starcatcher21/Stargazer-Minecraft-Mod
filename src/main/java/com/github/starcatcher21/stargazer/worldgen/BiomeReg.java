package com.github.starcatcher21.stargazer.worldgen;

import com.github.starcatcher21.stargazer.Stargazer;
import java.util.List;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class BiomeReg {
    public static final ResourceKey<Biome> MOON_FOREST = register("moon_forest");
    public static final ResourceKey<Biome> MOON_WASTE = register("moon_waste");
    public static final ResourceKey<Biome> AMETHYST_SPIKES = register("amethyst_spikes");
    public static final ResourceKey<Biome> BONE_FOREST = register("bone_forest");
    public static final ResourceKey<Biome> EYE_BIRCH_FOREST = register("eye_birch_forest");
    public static final ResourceKey<Biome> PURPLE_SHROOM_FOREST = register("purple_shroom_forest");
    public static final ResourceKey<Biome> DARKNESS_FOREST = register("darkness_forest");
    public static final ResourceKey<Biome> MOON_FIELD = register("moon_field");
    public static final ResourceKey<Biome> MOON_VOID = register("moon_void");

    public static final ResourceKey<Biome> REDNESS = register("redness");
    public static final ResourceKey<Biome> YERI_FOREST = register("yeri_forest");
    public static final ResourceKey<Biome> GREEN_ROCK_VALLEY = register("green_rock_valley");

    public static final ResourceKey<Biome> BUBBLES_FIELD = register("bubbles_field");

    public static final List<ResourceKey<Biome>> MoonList = List.of(MOON_FOREST, MOON_WASTE, AMETHYST_SPIKES, BONE_FOREST, EYE_BIRCH_FOREST, PURPLE_SHROOM_FOREST, DARKNESS_FOREST, MOON_FIELD, MOON_VOID);
    public static final List<ResourceKey<Biome>> RedList = List.of(REDNESS, YERI_FOREST, GREEN_ROCK_VALLEY);

    public static ResourceKey<Biome> register(String path) {
        return ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, path));
    }
    public static void init() {}
}
