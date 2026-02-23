package com.github.starcatcher21.stargazer.worldgen.features.trees.prismaticore;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;

public class Prismatic1 {
    public static void init(Tree tree, BlockState middle) {
        tree.addReplacableBlock(MoonBlocks.MOON_ROCK);
        tree.clearFruit();
        tree.addFruit(middle);
        tree.setFruitChange(100);
        tree.addFruitsPos(0, 0, 0);
        tree.addLeavesPos(1, 0, 0);
        tree.addLeavesPos(-1, 0, 0);
        tree.addLeavesPos(0, 0, 1);
        tree.addLeavesPos(0, 0, -1);
        tree.addLeavesPos(0, 1, 0);
        tree.addLeavesPos(0, -1, 0);
        tree.addLogPos(1, 1, 0);
        tree.addLogPos(-1, 1, 0);
        tree.addLogPos(0, 1, 1);
        tree.addLogPos(0, 1, -1);
        tree.addLogPos(0, 2, 0);

        tree.addLogPos(1, 0, 1);
        tree.addLogPos(-1, 0, 1);
        tree.addLogPos(-1, 0, -1);
        tree.addLogPos(1, 0, -1);

        tree.addLogPos(1, -1, 1);
        tree.addLogPos(-1, -1, 1);
        tree.addLogPos(-1, -1, -1);
        tree.addLogPos(1, -1, -1);

        tree.addLogPos(0, -2, 0);

        tree.addLogPos(2, 0, 0);
        tree.addLogPos(3, 0, 0);
        tree.addLogPos(-2, 0, 0);
        tree.addLogPos(-3, 0, 0);

        tree.addLogPos(0, 0, 2);
        tree.addLogPos(0, 0, 3);
        tree.addLogPos(0, 0, -2);
        tree.addLogPos(0, 0, -3);

        tree.addLogPos(1, -1, 0);
        tree.addLogPos(2, -1, 0);
        tree.addLogPos(-1, -1, 0);
        tree.addLogPos(-2, -1, 0);

        tree.addLogPos(0, -1, 1);
        tree.addLogPos(0, -1, 2);
        tree.addLogPos(0, -1, -1);
        tree.addLogPos(0, -1, -2);

        tree.addLogPos(0, -2, 1);
        tree.addLogPos(0, -2, -1);
        tree.addLogPos(1, -2, 0);
        tree.addLogPos(-1, -2, 0);

        tree.addLogPos(0, -3, 1);
        tree.addLogPos(0, -3, 2);
        tree.addLogPos(0, -3, -1);
        tree.addLogPos(0, -3, -2);

        tree.addLogPos(1, -3, 0);
        tree.addLogPos(2, -3, 0);
        tree.addLogPos(-1, -3, 0);
        tree.addLogPos(-2, -3, 0);
    }
}
