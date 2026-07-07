package com.github.starcatcher21.stargazer.worldgen.features.trees.green_rocks;

import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class Rock3 {
    public static void init(Tree tree) {
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                tree.addLogPos(i, 0, j);
                tree.addLogPos(i, 1, j);
                if (j == 1 && i == 1) continue;
                tree.addLogPos(i, 2, j);
            }
        }
    }
}
