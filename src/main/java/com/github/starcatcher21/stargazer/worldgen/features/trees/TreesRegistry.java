package com.github.starcatcher21.stargazer.worldgen.features.trees;

import com.github.starcatcher21.stargazer.worldgen.features.trees.bones.BoneTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.curve.CurveTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.darkness.DarknessTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.eyeblodbirch.EyeBirchTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.moon.MoonTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.prismaticore.PrismaticOre;
import com.github.starcatcher21.stargazer.worldgen.features.trees.purple_shroom.PurpleShrooms;
import com.github.starcatcher21.stargazer.worldgen.features.trees.star.StarTrees;
import net.minecraft.world.gen.structure.StructureType;

// North negative Z
// South positive Z
// West negative X
// east positive X
public class TreesRegistry {
    public static void init() {
        MoonTrees.init();
        StarTrees.init();
        CurveTrees.init();
        PurpleShrooms.init();
        BoneTrees.init();
        EyeBirchTrees.init();
        PrismaticOre.init();
        DarknessTrees.init();
    }
}
