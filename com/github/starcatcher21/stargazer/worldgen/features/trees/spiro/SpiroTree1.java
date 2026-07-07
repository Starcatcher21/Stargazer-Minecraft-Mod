package com.github.starcatcher21.stargazer.worldgen.features.trees.spiro;

import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class SpiroTree1 {
    public static void init(Tree tree) {
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        tree.addLogPos(0,0,0);
        tree.addLogPos(1,0,0);
        tree.addLogPos(1,1,1);
        tree.addLogPos(0,2,1);
        tree.addLogPos(0,2,0);
        tree.addLogPos(1,3,0);
        tree.addLogPos(1,4,1);
        tree.addLogPos(0,4,1);
        tree.addLogPos(0,5,0);
        tree.addLogPos(1,6,0);
        tree.addLogPos(1,6,1);
        tree.addLogPos(0,7,1);
        tree.addLogPos(0,8,0);
        tree.addLogPos(1,8,0);
        tree.addLogPos(1,9,1);
        tree.addLogPos(0,10,1);
        tree.addLogPos(0,10,0);
        tree.addLogPos(1,11,0);
        tree.addLogPos(1,12,1);
        tree.addLogPos(0,12,1);

        tree.addLeavesPos(0, 1, 0);
        tree.addLeavesPos(1, 1, 0);
        tree.addLeavesPos(2, 1, 0);
        tree.addLeavesPos(0, 3, 0);
        tree.addLeavesPos(0, 3, 1);
        tree.addLeavesPos(0, 3, 2);
        tree.addLeavesPos(1, 5, 1);
        tree.addLeavesPos(0, 5, 1);
        tree.addLeavesPos(-1, 5, 1);
        tree.addLeavesPos(1, 7, 1);
        tree.addLeavesPos(1, 7, 0);
        tree.addLeavesPos(1, 7, -1);

        tree.addLeavesPos(0, 9, 0);
        tree.addLeavesPos(1, 9, 0);
        tree.addLeavesPos(2, 9, 0);
        tree.addLeavesPos(0, 11, 0);
        tree.addLeavesPos(0, 11, 1);
        tree.addLeavesPos(0, 11, 2);

        tree.addLeavesPos(1, 13, 1);
        tree.addLeavesPos(0, 13, 1);
        tree.addLeavesPos(-1, 13, 1);
    }
}
