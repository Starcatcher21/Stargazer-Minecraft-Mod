package com.github.starcatcher21.stargazer.worldgen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.worldgen.features.Eyes;
import com.github.starcatcher21.stargazer.worldgen.features.ForgetMeNow;
import com.github.starcatcher21.stargazer.worldgen.features.Gradi;
import com.github.starcatcher21.stargazer.worldgen.features.chess.ChessTrees;
import com.github.starcatcher21.stargazer.worldgen.features.amertylst.Amertylst;
import com.github.starcatcher21.stargazer.worldgen.features.amertylst.AmertylstConfig;
import com.github.starcatcher21.stargazer.worldgen.features.blackstone_pillars.BlackStonePillars;
import com.github.starcatcher21.stargazer.worldgen.features.iron.IronOre;
import com.github.starcatcher21.stargazer.worldgen.features.trees.bones.BoneTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.bubbles.Bubbles;
import com.github.starcatcher21.stargazer.worldgen.features.crystals.CrystalOre;
import com.github.starcatcher21.stargazer.worldgen.features.trees.curve.CurveTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.darkness.DarknessTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.eyeblodbirch.EyeBirchTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.fullmoon.FullMoonTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.glass.GlassTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.green_rocks.GreenRocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.moon.MoonTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.nebulas.NebulaTrees;
import com.github.starcatcher21.stargazer.worldgen.features.prismaticore.PrismaticOre;
import com.github.starcatcher21.stargazer.worldgen.features.trees.purple_shroom.PurpleShrooms;
import com.github.starcatcher21.stargazer.worldgen.features.trees.spiro.SpiroTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.star.StarTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.trunn.TrunnTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.umbrella.UmbrellaTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.yeri.YeriTrees;
import com.github.starcatcher21.starlib.worldgen.features.trees.TreeConfig;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
//? if < 26.3 {
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
//? }

import java.util.function.Supplier;

public class CustomFeatures {
    public static final DeferredRegister<Feature/*? if < 26.3 { */ <?> /*? } */> FEATURES =
            DeferredRegister.create(Stargazer.MOD_ID, Registries.FEATURE);

    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> spike = register("spikes", () -> new Amertylst(AmertylstConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> moon_trees = register("moon_trees", () -> new MoonTrees(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> star_trees = register("star_trees", () -> new StarTrees(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> eye_birch_trees = register("eye_birch_trees", () -> new EyeBirchTrees(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> curve_trees = register("curve_trees", () -> new CurveTrees(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> purple_shroom = register("purple_shroom", () -> new PurpleShrooms(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> bone_trees = register("bone_trees", () -> new BoneTrees(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> prismatic_ore = register("prismatic_ore", () -> new PrismaticOre(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> crystal_ore = register("crystal_ore", () -> new CrystalOre(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> iron_ore = register("iron_ore", () -> new IronOre(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> forget_me_now = register("forget_me_now", () -> new ForgetMeNow(/*? if < 26.3 { */NoneFeatureConfiguration.CODEC/*? } */));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> gradi = register("gradi", () -> new Gradi(/*? if < 26.3 { */NoneFeatureConfiguration.CODEC/*? } */));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> eyes = register("eyes", () -> new Eyes(/*? if < 26.3 { */NoneFeatureConfiguration.CODEC/*? } */));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> darkness_trees = register("darkness_trees", () -> new DarknessTrees(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> nebula_trees = register("nebula_trees", () -> new NebulaTrees(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> chess_trees = register("chess_trees", () -> new ChessTrees(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> yeri_trees = register("yeri_trees", () -> new YeriTrees(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> green_rocks = register("green_rocks", () -> new GreenRocks(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> full_moon_trees = register("full_moon_trees", () -> new FullMoonTrees(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> spiro_trees = register("spiro_trees", () -> new SpiroTrees(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> blackstone_pillars = register("blackstone_pillars", () -> new BlackStonePillars(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> bubbles = register("bubbles", () -> new Bubbles(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> trunn_trees = register("trunn_trees", () -> new TrunnTrees(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> glass_trees = register("glass_trees", () -> new GlassTrees(TreeConfig.CODEC));
    public static final RegistrySupplier<Feature/*? if < 26.3 { */ <?> /*? } */> umbrella_trees = register("umbrella_trees", () -> new UmbrellaTrees(TreeConfig.CODEC));

    public static <T extends Feature/*? if < 26.3 { */ <?> /*? } */> RegistrySupplier<T> register(String id, Supplier<T> entry) {
        return FEATURES.register(id, entry);
    }

    public static void init() {
        FEATURES.register();
    }
}
