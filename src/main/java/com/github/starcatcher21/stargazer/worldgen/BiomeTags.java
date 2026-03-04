package com.github.starcatcher21.stargazer.worldgen;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.List;

public class BiomeTags {
    public static final TagKey<Biome> MOON = register("moon");

    private static TagKey<Biome> register(String name) {
        return TagKey.of(RegistryKeys.BIOME, Identifier.of(Stargazer.MOD_ID, name));
    }
    public static void init() {
    }
}
