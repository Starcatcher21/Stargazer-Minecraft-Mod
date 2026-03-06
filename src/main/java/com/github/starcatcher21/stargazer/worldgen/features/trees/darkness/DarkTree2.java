package com.github.starcatcher21.stargazer.worldgen.features.trees.darkness;

import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class DarkTree2 {
    public static void init(Tree tree) {
        tree.addReplacableBlock(StarBlocks.COSMIC_BLOCK);
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        tree.addLogPos(0, 0, 0);
        tree.addLogPos(0, 1, 0);
        tree.addLogPos(-1, 1, 0);
        tree.addLogPos(0, 2, 0);
        tree.addLogPos(1, 2, 0);
        tree.addLogPos(0, 3, 0);
        tree.addLogPos(0, 3, 1);
        tree.addLogPos(0, 4, 0);
        tree.addLogPos(0, 4, -1);
        tree.addLogPos(0, 5, 0);
        tree.addLeavesPos(1, 5, 0);
        tree.addLeavesPos(-1, 5, 0);
        tree.addLeavesPos(0, 5, 1);
        tree.addLeavesPos(0, 5, -1);
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                tree.addLeavesPos(x, 6, z);
            }
        }
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                tree.addLeavesPos(x, 7, z);
            }
        }
        tree.addLeavesPos(2, 7, 1);
        tree.addLeavesPos(2, 7, -1);
        tree.addLeavesPos(-2, 7, 1);
        tree.addLeavesPos(-2, 7, -1);

        tree.addLeavesPos(1, 7, 2);
        tree.addLeavesPos(1, 7, -2);
        tree.addLeavesPos(-1, 7, 2);
        tree.addLeavesPos(-1, 7, -2);
        for (int y = 4; y < 7; y++) {
            tree.addLeavesPos(3, y, 1);
            tree.addLeavesPos(3, y, -1);
            tree.addLeavesPos(-3, y, 1);
            tree.addLeavesPos(-3, y, -1);

            tree.addLeavesPos(1, y, 3);
            tree.addLeavesPos(1, y, -3);
            tree.addLeavesPos(-1, y, 3);
            tree.addLeavesPos(-1, y, -3);
        }

        tree.addLeavesPos(2, 4, 1);
        tree.addLeavesPos(2, 4, -1);
        tree.addLeavesPos(-2, 4, 1);
        tree.addLeavesPos(-2, 4, -1);

        tree.addLeavesPos(1, 4, 2);
        tree.addLeavesPos(1, 4, -2);
        tree.addLeavesPos(-1, 4, 2);
        tree.addLeavesPos(-1, 4, -2);
    }
}
