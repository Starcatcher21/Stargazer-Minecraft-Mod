package com.github.starcatcher21.stargazer.worldgen.features.trees.glass;

import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.starlib.worldgen.features.trees.Tree;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class GlassTree1 {
    public static void init(Tree tree) {
        tree.addReplacableBlock(StarBlocks.COSMIC_BLOCK);
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        for (int i = 0; i <= 8; i++) {
            tree.addLogPos(0, i, 0);
        }
        tree.addLogPos(1, 3, 0);
        tree.addLogPos(-1, 3, 0);
        tree.addLogPos(0, 3, 1);
        tree.addLogPos(0, 3, -1);
        tree.addLogPos(1, 6, 0);
        tree.addLogPos(-1, 6, 0);
        tree.addLogPos(0, 6, 1);
        tree.addLogPos(0, 6, -1);
        leav(tree, 3);
        leav(tree, 6);
        tree.addLeavesPos(0, 9, 0);
        tree.addLeavesPos(0, 10, 0);
    }
    private static void leav(Tree tree, int height) {
        for (int i = 2; i <= 3; i++) {
            tree.addLeavesPos(i, height, 0);
            tree.addLeavesPos(0, height, i);
            tree.addLeavesPos(-i, height, 0);
            tree.addLeavesPos(0, height, -i);
        }
        tree.addLeavesPos(1, height, 1);
        tree.addLeavesPos(-1, height, 1);
        tree.addLeavesPos(-1, height,-1);
        tree.addLeavesPos(1, height, -1);
        tree.addLeavesPos(2, height, 1);
        tree.addLeavesPos(-2, height, 1);
        tree.addLeavesPos(-2, height,-1);
        tree.addLeavesPos(2, height, -1);
        tree.addLeavesPos(1, height, 2);
        tree.addLeavesPos(-1, height, 2);
        tree.addLeavesPos(-1, height,-2);
        tree.addLeavesPos(1, height, -2);
        for (int i = 1; i <= 2; i++) {
            tree.addLeavesPos(i, height+1, 0);
            tree.addLeavesPos(0, height+1, i);
            tree.addLeavesPos(-i, height+1, 0);
            tree.addLeavesPos(0, height+1, -i);
        }
        tree.addLeavesPos(1, height+1, 1);
        tree.addLeavesPos(-1, height+1, 1);
        tree.addLeavesPos(-1, height+1,-1);
        tree.addLeavesPos(1, height+1, -1);

        tree.addLeavesPos(1, height+2, 0);
        tree.addLeavesPos(0, height+2, 1);
        tree.addLeavesPos(-1, height+2, 0);
        tree.addLeavesPos(0, height+2, -1);
    }
}
