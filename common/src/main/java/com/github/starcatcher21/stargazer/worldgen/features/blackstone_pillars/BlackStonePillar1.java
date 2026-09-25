package com.github.starcatcher21.stargazer.worldgen.features.blackstone_pillars;

import com.github.starcatcher21.starlib.worldgen.features.trees.Tree;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class BlackStonePillar1 {
    public static void init(Tree tree) {
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        for (int h = 0; h <= 7; h++) {
            tree.addLogPos(0, h, 0);
        }
    }
}
