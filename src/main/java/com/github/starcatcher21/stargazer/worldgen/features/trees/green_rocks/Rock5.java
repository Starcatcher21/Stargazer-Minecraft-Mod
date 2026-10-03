package com.github.starcatcher21.stargazer.worldgen.features.trees.green_rocks;

import com.github.starcatcher21.starlib.worldgen.features.trees.Tree;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class Rock5 {
    public static void init(Tree tree) {
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        tree.addLogPos(0, 0,0 );
        tree.addLogPos(1, 0,0 );
        tree.addLogPos(0, 0,1 );
        tree.addLogPos(0, 0,2 );
        tree.addLogPos(1, 0,1 );
        tree.addLogPos(1, 0,2 );
        tree.addLogPos(1, 1,0 );
        tree.addLogPos(0, 1,1 );
        tree.addLogPos(0, 1,2 );
        tree.addLogPos(1, 1,1 );
    }
}
