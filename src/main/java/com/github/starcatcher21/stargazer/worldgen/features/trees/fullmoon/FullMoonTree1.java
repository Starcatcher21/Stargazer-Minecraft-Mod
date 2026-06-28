package com.github.starcatcher21.stargazer.worldgen.features.trees.fullmoon;

import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class FullMoonTree1 {
    public static void init(Tree tree) {
        tree.addReplacableBlock(StarBlocks.COSMIC_BLOCK);
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        tree.addLogPos(0,0,0);
        tree.addLogPos(0,1,0);
        tree.addLogPos(0,2,0);
        tree.addLogPos(0,3,0);
        tree.addLogPos(0,4,0);
        tree.addFruitsPos(0, 5, 0);

        tree.addLeavesPos(0, 6, 0);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                tree.addLeavesPos(i, 4, j);
                tree.addLeavesPos(i, 5, j);
                tree.addLeavesPos(i, 6, j);
            }
        }
    }
}
