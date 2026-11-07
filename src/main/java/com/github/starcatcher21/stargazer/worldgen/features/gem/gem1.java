package com.github.starcatcher21.stargazer.worldgen.features.gem;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.RedOrbBlocks;
import com.github.starcatcher21.starlib.worldgen.features.trees.Tree;

public class gem1 {
    public static void init(Tree tree) {
        tree.addReplacableBlock(RedOrbBlocks.RED_ROCK.get());
        tree.addLogPos(0, 0, 0);
        tree.addLogPos(0, 0, 1);
        tree.addLogPos(0, 1, 0);
        tree.addLogPos(0, 1, 1);
        tree.addLogPos(1, 0, 0);
        tree.addLogPos(1, 0, 1);
        tree.addLogPos(1, 1, 0);
        tree.addLogPos(1, 1, 1);
    }
}
