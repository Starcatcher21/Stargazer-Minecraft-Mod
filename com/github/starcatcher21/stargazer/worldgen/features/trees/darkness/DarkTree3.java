package com.github.starcatcher21.stargazer.worldgen.features.trees.darkness;

import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class DarkTree3 {
    public static void init(Tree tree) {
        tree.addReplacableBlock(StarBlocks.COSMIC_BLOCK);
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        for (int i = 0; i < 8; i++) {
            tree.addLogPos(0, i, 0);
        }
//        tree.addLeavesPos(1, 5, 0);
//        tree.addLeavesPos(-1, 5, 0);
//        tree.addLeavesPos(0, 5, 1);
//        tree.addLeavesPos(0, 5, -1);
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                tree.addLeavesPos(x, 8, z);
            }
        }
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                tree.addLeavesPos(x, 9, z);
            }
        }

        for (int y = 7; y < 9; y++) {
            tree.addLeavesPos(3, y, 1);
            tree.addLeavesPos(3, y, -1);
            tree.addLeavesPos(-3, y, 1);
            tree.addLeavesPos(-3, y, -1);

            tree.addLeavesPos(1, y, 3);
            tree.addLeavesPos(1, y, -3);
            tree.addLeavesPos(-1, y, 3);
            tree.addLeavesPos(-1, y, -3);
        }
        tree.addLeavesPos(1, 6, 0);
        tree.addLeavesPos(-1, 6, 0);
        tree.addLeavesPos(1, 7, 0);
        tree.addLeavesPos(-1, 7, 0);
        tree.addLeavesPos(0, 6, 1);
        tree.addLeavesPos(0, 6, -1);
        tree.addLeavesPos(0, 7, 1);
        tree.addLeavesPos(0, 7, -1);

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (x == 0 && z == 0) continue;
                tree.addLeavesPos(x, 5, z);
            }
        }
        for (int y = 4; y < 6; y++) {
            tree.addLeavesPos(2, y, 1);
            tree.addLeavesPos(2, y, -1);
            tree.addLeavesPos(-2, y, 1);
            tree.addLeavesPos(-2, y, -1);

            tree.addLeavesPos(1, y, 2);
            tree.addLeavesPos(1, y, -2);
            tree.addLeavesPos(-1, y, 2);
            tree.addLeavesPos(-1, y, -2);
        }
        tree.addLeavesPos(1, 2, 0);
        tree.addLeavesPos(-1, 2, 0);
        tree.addLeavesPos(0, 2, 1);
        tree.addLeavesPos(0, 2, -1);
    }
}
