package com.github.starcatcher21.stargazer.worldgen.features.trees.bones;

import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class CinemaTree {
    public static void CinemaInit(Tree tree) {
        tree.addReplacableBlock(StarBlocks.COSMIC_BLOCK);
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        tree.addLogPos(0,0,0);
        tree.addLogPos(0,1,0);
        tree.addLogPos(0,2,0);
        tree.addLogPos(1,2,0);
        tree.addLogPos(-1,2,0);
        tree.addLogPos(0,3,0);
        tree.addLogPos(0,4,0);
        tree.addLogPos(0,5,0);
        // right
        tree.addLogPos(2,3,0);
        tree.addLogPos(2,4,0);
        tree.addLogPos(2,5,0);
        // left
        tree.addLogPos(-2,3,0);
        tree.addLogPos(-2,4,0);
        tree.addLogPos(-2,5,0);
        // leaves Middle
        tree.addLeavesPos(1,6,0);
        tree.addLeavesPos(-1,6,0);
        tree.addLeavesPos(0,6,0);
        tree.addLeavesPos(0,6,1);
        tree.addLeavesPos(0,6,-1);
        tree.addLeavesPos(0,7,0);
        tree.addLeavesPos(0,8,0);
        // leaves left
        tree.addLeavesPos(-3,6,0);
        tree.addLeavesPos(-2,6,0);
        tree.addLeavesPos(-2,6,1);
        tree.addLeavesPos(-2,6,-1);
        tree.addLeavesPos(-2,7,0);
        tree.addLeavesPos(-2,8,0);
        // leaves right
        tree.addLeavesPos(3,6,0);
        tree.addLeavesPos(2,6,0);
        tree.addLeavesPos(2,6,1);
        tree.addLeavesPos(2,6,-1);
        tree.addLeavesPos(2,7,0);
        tree.addLeavesPos(2,8,0);
        // leaves middle
        tree.addLeavesPos(4,5,0);
        tree.addLeavesPos(4,4,0);
        tree.addLeavesPos(3,5,0);
        tree.addLeavesPos(1,5,0);
        tree.addLeavesPos(-1,5,0);
        tree.addLeavesPos(-3,5,0);
        tree.addLeavesPos(-4,5,0);
        tree.addLeavesPos(-4,4,0);
        tree.addLeavesPos(-3,5,1);
        tree.addLeavesPos(-3,5,-1);
        tree.addLeavesPos(-2,5,1);
        tree.addLeavesPos(-2,5,-1);
        tree.addLeavesPos(-2,5,2);
        tree.addLeavesPos(-2,5,-2);
        tree.addLeavesPos(-2,4,2);
        tree.addLeavesPos(-2,4,-2);
        tree.addLeavesPos(-1,5,1);
        tree.addLeavesPos(-1,5,-1);
        tree.addLeavesPos(0,5,1);
        tree.addLeavesPos(0,5,-1);
        tree.addLeavesPos(0,5,2);
        tree.addLeavesPos(0,5,-2);
        tree.addLeavesPos(0,4,2);
        tree.addLeavesPos(0,4,-2);
        tree.addLeavesPos(1,5,1);
        tree.addLeavesPos(1,5,-1);
        tree.addLeavesPos(2,5,1);
        tree.addLeavesPos(2,5,-1);
        tree.addLeavesPos(2,5,2);
        tree.addLeavesPos(2,5,-2);
        tree.addLeavesPos(2,4,2);
        tree.addLeavesPos(2,4,-2);
        tree.addLeavesPos(3,5,1);
        tree.addLeavesPos(3,5,-1);
    }
}
