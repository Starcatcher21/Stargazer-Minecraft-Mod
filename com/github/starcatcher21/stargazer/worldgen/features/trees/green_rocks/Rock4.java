package com.github.starcatcher21.stargazer.worldgen.features.trees.green_rocks;

import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class Rock4 {
    public static void init(Tree tree) {
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                tree.addLogPos(i, j, 0);
                tree.addLogPos(i, j, 1);
                if (j == 1 && i == 1) continue;
                tree.addLogPos(i, j, 2);
            }
        }
    }
}
