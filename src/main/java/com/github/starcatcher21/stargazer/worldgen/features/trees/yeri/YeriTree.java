package com.github.starcatcher21.stargazer.worldgen.features.trees.yeri;

import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class YeriTree {
    public static void init(Tree tree) {
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        for (int i = 0; i <= 5; i++) {
            tree.addLogPos(0, i, 0);
        }
        int h = 5;
        for (int i = 1; i <= 3; i++) {
            tree.addLogPos(i, h, 0);
            tree.addLogPos(-i, h, 0);
            h++;
        }
        tree.addLeavesPos(0, 5, 0);
        tree.addLeavesPos(0, 4, 1);
        tree.addLeavesPos(0, 3, -1);
        tree.addLeavesPos(0, 4, -1);

        tree.addLeavesPos(1, 5, 1);
        tree.addLeavesPos(1, 6, 0);
        tree.addLeavesPos(1, 5, -1);
        tree.addLeavesPos(1, 4, 1);
        tree.addLeavesPos(1, 4, -1);
        tree.addLeavesPos(1, 4, 0);

        tree.addLeavesPos(-1, 5, 1);
        tree.addLeavesPos(-1, 6, 0);
        tree.addLeavesPos(-1, 5, -1);
        tree.addLeavesPos(-1, 4, 1);
        tree.addLeavesPos(-1, 4, -1);
        tree.addLeavesPos(-1, 4, 0);

        tree.addLeavesPos(2, 6, 1);
        tree.addLeavesPos(2, 7, 0);
        tree.addLeavesPos(2, 6, -1);
        tree.addLeavesPos(2, 5, 1);
        tree.addLeavesPos(2, 5, -1);
        tree.addLeavesPos(2, 5, 0);

        tree.addLeavesPos(-2, 6, 1);
        tree.addLeavesPos(-2, 7, 0);
        tree.addLeavesPos(-2, 6, -1);
        tree.addLeavesPos(-2, 5, 1);
        tree.addLeavesPos(-2, 5, -1);
        tree.addLeavesPos(-2, 5, 0);

        tree.addLeavesPos(3, 7, 1);
        tree.addLeavesPos(3, 8, 0);
        tree.addLeavesPos(3, 7, -1);
        tree.addLeavesPos(3, 6, 1);
        tree.addLeavesPos(3, 6, -1);
        tree.addLeavesPos(3, 6, 0);

        tree.addLeavesPos(-3, 7, 1);
        tree.addLeavesPos(-3, 8, 0);
        tree.addLeavesPos(-3, 7, -1);
        tree.addLeavesPos(-3, 6, 1);
        tree.addLeavesPos(-3, 6, -1);
        tree.addLeavesPos(-3, 6, 0);

        tree.addLeavesPos(4, 6, 0);
        tree.addLeavesPos(4, 6, 1);
        tree.addLeavesPos(4, 6, -1);
        tree.addLeavesPos(4, 7, 0);

        tree.addLeavesPos(-4, 6, 0);
        tree.addLeavesPos(-4, 6, 1);
        tree.addLeavesPos(-4, 6, -1);
        tree.addLeavesPos(-4, 7, 0);
    }
}
