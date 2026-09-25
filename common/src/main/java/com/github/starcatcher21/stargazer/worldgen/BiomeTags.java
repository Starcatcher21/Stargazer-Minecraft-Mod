package com.github.starcatcher21.stargazer.worldgen;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class BiomeTags {
    public static final TagKey<Biome> MOON = register("moon");
    public static final TagKey<Biome> RED_ORB = register("red_orb");

    private static TagKey<Biome> register(String name) {
        return TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, name));
    }
    public static void init() {
    }
}
