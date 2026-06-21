package com.github.starcatcher21.stargazer.worldgen.features.trees.blackstone_pillars;

import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class BlackStonePillar3 {
    public static void init(Tree tree) {
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        for (int h = 0; h <= 7; h++) {
            for (int x = -1; x < 2; x++) {
                for (int z = -1; z < 2; z++) {
                    tree.addLogPos(x, h, z);
                }
            }
        }
        tree.addLogPos(0, 8, 0);
        tree.addLogPos(0, 9, 0);
    }
}
