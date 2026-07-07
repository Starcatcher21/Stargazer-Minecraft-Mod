package com.github.starcatcher21.stargazer.worldgen.features.trees.bubbles;

import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class dxd {
    public static void init(Tree tree) {
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                for (int h = 0; h <= 1; h++) {
                    tree.addLeavesPos(i, h, j);
                }
            }
        }
    }
}
