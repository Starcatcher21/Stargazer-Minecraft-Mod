package com.github.starcatcher21.stargazer.worldgen.features.trees.trunn;

import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class TrunnTree {
    public static void init(Tree tree) {
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        for (int i = 0; i <= 6; i++) {
            tree.addLogPos(0, i, 0);
        }
        tree.addLogPos(1, 6, 0);
        tree.addLogPos(-1, 6, 0);
        tree.addLogPos(2, 6, 0);
        tree.addLogPos(-2, 6, 0);
        tree.addLogPos(0, 6,1);
        tree.addLogPos(0, 6,-1);
        tree.addLogPos(0, 6,2);
        tree.addLogPos(0, 6,-2);
        tree.addLogPos(3, 5, 0);
        tree.addLogPos(3, 4, 0);
        tree.addLogPos(-3, 5, 0);
        tree.addLogPos(-3, 4, 0);
        tree.addLogPos(0, 5, 3);
        tree.addLogPos(0, 4, 3);
        tree.addLogPos(0, 5, -3);
        tree.addLogPos(0, 4, -3);

        tree.addLogPos(1, 3, 0);
        tree.addLogPos(-1, 3, 0);
        tree.addLogPos(2, 3,0);
        tree.addLogPos(-2, 3, 0);
        tree.addLogPos(0, 3,1);
        tree.addLogPos(0, 3,-1);
        tree.addLogPos(0, 3,2);
        tree.addLogPos(0, 3,-2);

        tree.addLeavesPos(1,3 , 1);
        tree.addLeavesPos(1,3 , -1);
        tree.addLeavesPos(-1,3 , 1);
        tree.addLeavesPos(-1,3 , -1);
        tree.addLeavesPos(1,6 , 1);
        tree.addLeavesPos(1,6 , -1);
        tree.addLeavesPos(-1,6 , 1);
        tree.addLeavesPos(-1,6 , -1);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int h = 4; h <= 5; h++) {
                    if (i == 0 && j == 0) continue;
                    tree.addLeavesPos(i, h, j);
                }
            }
        }
        for (int i = -1; i <= 1; i++) {
            tree.addLeavesPos(2, 4, i);
            tree.addLeavesPos(2, 5, i);
            tree.addLeavesPos(-2, 4, i);
            tree.addLeavesPos(-2, 5, i);
            tree.addLeavesPos(i, 4, 2);
            tree.addLeavesPos(i, 5, 2);
            tree.addLeavesPos(i, 4, -2);
            tree.addLeavesPos(i, 5, -2);
        }
        tree.addLeavesPos(3, 4,1);
        tree.addLeavesPos(3, 4,-1);
        tree.addLeavesPos(3, 5,1);
        tree.addLeavesPos(3, 5,-1);
        tree.addLeavesPos(-3, 4,1);
        tree.addLeavesPos(-3, 4,-1);
        tree.addLeavesPos(-3, 5,1);
        tree.addLeavesPos(-3, 5,-1);
        tree.addLeavesPos(1, 4,3);
        tree.addLeavesPos(-1, 4,3);
        tree.addLeavesPos(1, 5,3);
        tree.addLeavesPos(-1, 5,3);
        tree.addLeavesPos(1, 4,-3);
        tree.addLeavesPos(-1, 4,-3);
        tree.addLeavesPos(1, 5,-3);
        tree.addLeavesPos(-1, 5,-3);

        tree.addLeavesPos(0, 4, 4);
        tree.addLeavesPos(0, 5, 4);
        tree.addLeavesPos(0, 4, -4);
        tree.addLeavesPos(0, 5, -4);
        tree.addLeavesPos(4, 4, 0);
        tree.addLeavesPos(4, 5, 0);
        tree.addLeavesPos(-4, 4, 0);
        tree.addLeavesPos(-4, 5, 0);
    }
}
