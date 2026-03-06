package com.github.starcatcher21.stargazer.worldgen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.worldgen.features.ForgetMeNow;
import com.github.starcatcher21.stargazer.worldgen.features.trees.TreeConfig;
import com.github.starcatcher21.stargazer.worldgen.features.trees.amertylst.Amertylst;
import com.github.starcatcher21.stargazer.worldgen.features.trees.amertylst.AmertylstConfig;
import com.github.starcatcher21.stargazer.worldgen.features.trees.bones.BoneTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.curve.CurveTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.darkness.DarknessTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.eyeblodbirch.EyeBirchTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.moon.MoonTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.prismaticore.PrismaticOre;
import com.github.starcatcher21.stargazer.worldgen.features.trees.purple_shroom.PurpleShrooms;
import com.github.starcatcher21.stargazer.worldgen.features.trees.star.StarTrees;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class CustomFeatures {
    public static final Feature moon_trees = register("moon_trees", new MoonTrees(TreeConfig.CODEC));
    public static final Feature star_trees = register("star_trees", new StarTrees(TreeConfig.CODEC));
    public static final Feature eye_birch_trees = register("eye_birch_trees", new EyeBirchTrees(TreeConfig.CODEC));
    public static final Feature curve_trees = register("curve_trees", new CurveTrees(TreeConfig.CODEC));
    public static final Feature purple_shroom = register("purple_shroom", new PurpleShrooms(TreeConfig.CODEC));
    public static final Feature bone_trees = register("bone_trees", new BoneTrees(TreeConfig.CODEC));
    public static final Feature spike = register("spike", new Amertylst(AmertylstConfig.CODEC));
    public static final Feature prismatic_ore = register("prismatic_ore", new PrismaticOre(TreeConfig.CODEC));
    public static final Feature forget_me_now = register("forget_me_now", new ForgetMeNow(DefaultFeatureConfig.CODEC));
    public static final Feature darkness_trees = register("darkness_trees", new DarknessTrees(TreeConfig.CODEC));

    public static Feature register(String id, Feature<?> entry) {
        return Registry.register(
                Registries.FEATURE,
                Identifier.of(Stargazer.MOD_ID, id),
                entry
        );
    }

    public static void init() {
    }
}
