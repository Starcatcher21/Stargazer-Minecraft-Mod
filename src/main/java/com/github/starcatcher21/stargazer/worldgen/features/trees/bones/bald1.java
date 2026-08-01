package com.github.starcatcher21.stargazer.worldgen.features.trees.bones;

import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.starlib.worldgen.features.trees.Tree;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class bald1 {
    public static void CinemaInit(Tree tree) {
        tree.addReplacableBlock(StarBlocks.COSMIC_BLOCK);
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        tree.addLogPos(0, 0, 0);
        tree.addLogPos(0, 1, 0);
        tree.addLogPos(0, 2, 0);
        tree.addLogPos(1, 2, 0);
        tree.addLogPos(1, 3, 0);
        tree.addLogPos(2, 4, 0);
        tree.addLogPos(2, 5, 0);
    }
}
