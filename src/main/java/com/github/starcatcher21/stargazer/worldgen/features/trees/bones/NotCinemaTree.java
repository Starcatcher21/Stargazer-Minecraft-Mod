package com.github.starcatcher21.stargazer.worldgen.features.trees.bones;

import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class NotCinemaTree {
    public static void CinemaInit(Tree tree) {
        tree.addReplacableBlock(StarBlocks.COSMIC_BLOCK);
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        tree.addLogPos(0,0,0);
        tree.addLogPos(0,1,0);
        tree.addLogPos(1,1,0);
        tree.addLogPos(-1,1,0);
        tree.addLogPos(1,2,0);
        tree.addLogPos(-1,2,0);
        tree.addLogPos(1,3,0);
        tree.addLogPos(-1,3,0);
        tree.addLogPos(2,3,0);
        tree.addLogPos(-2,3,0);
        tree.addLogPos(2,4,0);
        tree.addLogPos(-2,4,0);
        tree.addLogPos(2,5,0);
        tree.addLogPos(-2,5,0);
        tree.addLogPos(2,6,0);
        tree.addLogPos(-2,6,0);
        // leaves
        tree.addLeavesPos(-3, 6, 0);
        tree.addLeavesPos(-4, 6, 0);
        tree.addLeavesPos(-4, 5, 0);
        tree.addLeavesPos(-2, 6, 1);
        tree.addLeavesPos(-2, 6, 2);
        tree.addLeavesPos(-2, 5, 2);
        tree.addLeavesPos(-2, 6, -1);
        tree.addLeavesPos(-2, 6, -2);
        tree.addLeavesPos(-2, 5, -2);
        tree.addLeavesPos(-3, 6, -1);
        tree.addLeavesPos(-3, 6, 1);
        tree.addLeavesPos(-1, 6, -1);
        tree.addLeavesPos(-1, 6, 0);
        tree.addLeavesPos(-1, 6, 1);
        tree.addLeavesPos(-2, 7, 0);
        tree.addLeavesPos(-2, 7, -1);
        tree.addLeavesPos(-2, 7, 1);
        tree.addLeavesPos(-3, 7, 0);
        tree.addLeavesPos(-1, 7, 0);
        tree.addLeavesPos(-2, 8, 0);
        tree.addLeavesPos(-2, 9, 0);
        // l2
        tree.addLeavesPos(3, 6, 0);
        tree.addLeavesPos(4, 6, 0);
        tree.addLeavesPos(4, 5, 0);
        tree.addLeavesPos(2, 6, 1);
        tree.addLeavesPos(2, 6, 2);
        tree.addLeavesPos(2, 5, 2);
        tree.addLeavesPos(2, 6, -1);
        tree.addLeavesPos(2, 6, -2);
        tree.addLeavesPos(2, 5, -2);
        tree.addLeavesPos(3, 6, -1);
        tree.addLeavesPos(3, 6, 1);
        tree.addLeavesPos(1, 6, -1);
        tree.addLeavesPos(1, 6, 0);
        tree.addLeavesPos(0, 6, 0);
        tree.addLeavesPos(0, 5, 0);
        tree.addLeavesPos(1, 6, 1);
        tree.addLeavesPos(2, 7, 0);
        tree.addLeavesPos(2, 7, -1);
        tree.addLeavesPos(2, 7, 1);
        tree.addLeavesPos(3, 7, 0);
        tree.addLeavesPos(1, 7, 0);
        tree.addLeavesPos(2, 8, 0);
        tree.addLeavesPos(2, 9, 0);
    }
}
