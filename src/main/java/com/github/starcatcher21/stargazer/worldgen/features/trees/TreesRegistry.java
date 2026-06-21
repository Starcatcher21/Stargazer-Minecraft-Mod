package com.github.starcatcher21.stargazer.worldgen.features.trees;

import com.github.starcatcher21.stargazer.worldgen.features.chess.ChessTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.blackstone_pillars.BlackStonePillars;
import com.github.starcatcher21.stargazer.worldgen.features.trees.bones.BoneTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.bubbles.Bubbles;
import com.github.starcatcher21.stargazer.worldgen.features.trees.curve.CurveTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.darkness.DarknessTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.eyeblodbirch.EyeBirchTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.fullmoon.FullMoonTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.green_rocks.GreenRocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.moon.MoonTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.nebulas.NebulaTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.prismaticore.PrismaticOre;
import com.github.starcatcher21.stargazer.worldgen.features.trees.purple_shroom.PurpleShrooms;
import com.github.starcatcher21.stargazer.worldgen.features.trees.spiro.SpiroTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.star.StarTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.trunn.TrunnTree;
import com.github.starcatcher21.stargazer.worldgen.features.trees.trunn.TrunnTrees;
import com.github.starcatcher21.stargazer.worldgen.features.trees.yeri.YeriTrees;

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
        NebulaTrees.init();
        ChessTrees.init();
        YeriTrees.init();
        GreenRocks.init();
        FullMoonTrees.init();
        SpiroTrees.init();
        BlackStonePillars.init();
        Bubbles.init();
        TrunnTrees.init();
    }
}
