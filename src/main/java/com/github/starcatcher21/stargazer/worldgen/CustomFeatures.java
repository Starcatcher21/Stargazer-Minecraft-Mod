package com.github.starcatcher21.stargazer.worldgen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.worldgen.features.Eyes;
import com.github.starcatcher21.stargazer.worldgen.features.ForgetMeNow;
import com.github.starcatcher21.stargazer.worldgen.features.Gradi;
import com.github.starcatcher21.stargazer.worldgen.features.chess.ChessTrees;
import com.github.starcatcher21.stargazer.worldgen.features.amertylst.Amertylst;
import com.github.starcatcher21.stargazer.worldgen.features.amertylst.AmertylstConfig;
import com.github.starcatcher21.stargazer.worldgen.features.blackstone_pillars.BlackStonePillars;
import com.github.starcatcher21.stargazer.worldgen.features.trees.bones.BoneTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.bubbles.Bubbles;
import com.github.starcatcher21.stargazer.worldgen.features.trees.curve.CurveTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.darkness.DarknessTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.eyeblodbirch.EyeBirchTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.fullmoon.FullMoonTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.glass.GlassTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.green_rocks.GreenRocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.moon.MoonTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.nebulas.NebulaTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.prismaticore.PrismaticOre;
import com.github.starcatcher21.stargazer.worldgen.features.trees.purple_shroom.PurpleShrooms;
import com.github.starcatcher21.stargazer.worldgen.features.trees.spiro.SpiroTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.star.StarTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.trunn.TrunnTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.yeri.YeriTrees;
import com.github.starcatcher21.starlib.worldgen.features.trees.TreeConfig;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CustomFeatures {
    public static final Feature spike = register("spikes", new Amertylst(AmertylstConfig.CODEC));
    public static final Feature moon_trees = register("moon_trees", new MoonTrees(TreeConfig.CODEC));
    public static final Feature star_trees = register("star_trees", new StarTrees(TreeConfig.CODEC));
    public static final Feature eye_birch_trees = register("eye_birch_trees", new EyeBirchTrees(TreeConfig.CODEC));
    public static final Feature curve_trees = register("curve_trees", new CurveTrees(TreeConfig.CODEC));
    public static final Feature purple_shroom = register("purple_shroom", new PurpleShrooms(TreeConfig.CODEC));
    public static final Feature bone_trees = register("bone_trees", new BoneTrees(TreeConfig.CODEC));
    public static final Feature prismatic_ore = register("prismatic_ore", new PrismaticOre(TreeConfig.CODEC));
    public static final Feature forget_me_now = register("forget_me_now", new ForgetMeNow(NoneFeatureConfiguration.CODEC));
    public static final Feature gradi = register("gradi", new Gradi(NoneFeatureConfiguration.CODEC));
    public static final Feature eyes = register("eyes", new Eyes(NoneFeatureConfiguration.CODEC));
    public static final Feature darkness_trees = register("darkness_trees", new DarknessTrees(TreeConfig.CODEC));
    public static final Feature nebula_trees = register("nebula_trees", new NebulaTrees(TreeConfig.CODEC));
    public static final Feature chess_trees = register("chess_trees", new ChessTrees(TreeConfig.CODEC));
    public static final Feature yeri_trees = register("yeri_trees", new YeriTrees(TreeConfig.CODEC));
    public static final Feature green_rocks = register("green_rocks", new GreenRocks(TreeConfig.CODEC));
    public static final Feature full_moon_trees = register("full_moon_trees", new FullMoonTrees(TreeConfig.CODEC));
    public static final Feature spiro_trees = register("spiro_trees", new SpiroTrees(TreeConfig.CODEC));
    public static final Feature blackstone_pillars = register("blackstone_pillars", new BlackStonePillars(TreeConfig.CODEC));
    public static final Feature bubbles = register("bubbles", new Bubbles(TreeConfig.CODEC));
    public static final Feature trunn_trees = register("trunn_trees", new TrunnTrees(TreeConfig.CODEC));
    public static final Feature glass_trees = register("glass_trees", new GlassTrees(TreeConfig.CODEC));

    public static Feature register(String id, Feature<?> entry) {
        return Registry.register(
                BuiltInRegistries.FEATURE,
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, id),
                entry
        );
    }

    public static void init() {
    }
}
