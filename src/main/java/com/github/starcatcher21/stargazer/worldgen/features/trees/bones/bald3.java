package com.github.starcatcher21.stargazer.worldgen.features.trees.bones;

import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class bald3 {
    public static void CinemaInit(Tree tree) {
        tree.addReplacableBlock(StarBlocks.COSMIC_BLOCK);
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        tree.addLogPos(2, 0, 0);
        tree.addLogPos(2, 1, 0);
        tree.addLogPos(-2, 0, 0);
        tree.addLogPos(-2, 1, 0);
        tree.addLogPos(0, 0, 2);
        tree.addLogPos(0, 1, 2);
        tree.addLogPos(0, 0, -2);
        tree.addLogPos(0, 1, -2);
    }
}
