package com.github.starcatcher21.stargazer.worldgen.features.trees.fuzzy;

import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.starlib.worldgen.features.trees.Tree;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class FuzzyTree1 {
    public static void init(Tree tree) {
        StarBlocks.COSMIC_BLOCK.listen(tree::addReplacableBlock);
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        tree.addLogPos(0, 0, 0);
        tree.addLogPos(0, 1, 0);
        tree.addLogPos(0, 2, 0);
        tree.addLogPos(0, 3, 0);
        tree.addLogPos(0, 4, 0);
        tree.addLogPos(0, 5, 0);
        tree.addLogPos(0, 6, 0);
        tree.addLogPos(1, 4, 0);
        tree.addLogPos(2, 4, 0);
        tree.addLogPos(-1, 4, 0);
        tree.addLogPos(-2, 4, 0);
        tree.addLogPos(0, 4, 1);
        tree.addLogPos(0, 4, 2);
        tree.addLogPos(0, 4, -1);
        tree.addLogPos(0, 4, -2);

        tree.addLeavesPos(1, 1, 0);
        tree.addLeavesPos(0, 1, 1);
        tree.addLeavesPos(-1, 1, 0);
        tree.addLeavesPos(0, 1, -1);
        tree.addLeavesPos(1, 2, 0);
        tree.addLeavesPos(0, 2, 1);
        tree.addLeavesPos(-1, 2, 0);
        tree.addLeavesPos(0, 2, -1);
        tree.addLeavesPos(2, 2, 0);
        tree.addLeavesPos(0, 2, 2);
        tree.addLeavesPos(-2, 2, 0);
        tree.addLeavesPos(0, 2, -2);
        tree.addLeavesPos(2, 3, 0);
        tree.addLeavesPos(0, 3, 2);
        tree.addLeavesPos(-2, 3, 0);
        tree.addLeavesPos(0, 3, -2);
        tree.addLeavesPos(3, 3, 0);
        tree.addLeavesPos(0, 3, 3);
        tree.addLeavesPos(-3, 3, 0);
        tree.addLeavesPos(0, 3, -3);

        tree.addLeavesPos(0, 8, 0);
        tree.addLeavesPos(0, 7, 0);
        tree.addLeavesPos(1, 7, 0);
        tree.addLeavesPos(0, 7, 1);
        tree.addLeavesPos(-1, 7, 0);
        tree.addLeavesPos(0, 7, -1);
        tree.addLeavesPos(1, 6, 0);
        tree.addLeavesPos(0, 6, 1);
        tree.addLeavesPos(-1, 6, 0);
        tree.addLeavesPos(0, 6, -1);
        tree.addLeavesPos(2, 6, 0);
        tree.addLeavesPos(0, 6, 2);
        tree.addLeavesPos(-2, 6, 0);
        tree.addLeavesPos(0, 6, -2);
        tree.addLeavesPos(2, 5, 0);
        tree.addLeavesPos(0, 5, 2);
        tree.addLeavesPos(-2, 5, 0);
        tree.addLeavesPos(0, 5, -2);
        tree.addLeavesPos(3, 5, 0);
        tree.addLeavesPos(0, 5, 3);
        tree.addLeavesPos(-3, 5, 0);
        tree.addLeavesPos(0, 5, -3);

        tree.addLeavesPos(3, 4, 0);
        tree.addLeavesPos(4, 4, 0);
        tree.addLeavesPos(-3, 4, 0);
        tree.addLeavesPos(-4, 4, 0);
        tree.addLeavesPos(0, 4, 3);
        tree.addLeavesPos(0, 4, 4);
        tree.addLeavesPos(0, 4, -3);
        tree.addLeavesPos(0, 4, -4);
        tree.addLeavesPos(1, 4, 3);
        tree.addLeavesPos(-1, 4, 3);
        tree.addLeavesPos(3, 4, 1);
        tree.addLeavesPos(3, 4, -1);
        tree.addLeavesPos(1, 4, -3);
        tree.addLeavesPos(-1, 4, -3);
        tree.addLeavesPos(-3, 4, 1);
        tree.addLeavesPos(-3, 4, -1);

        tree.addLeavesPos(1, 4, 2);
        tree.addLeavesPos(-1, 4, 2);
        tree.addLeavesPos(1, 4, -2);
        tree.addLeavesPos(-1, 4, -2);
        tree.addLeavesPos(2, 4, 1);
        tree.addLeavesPos(2, 4, -1);
        tree.addLeavesPos(-2, 4, 1);
        tree.addLeavesPos(-2, 4, -1);

        tree.addLeavesPos(2, 4, 2);
        tree.addLeavesPos(-2, 4, 2);
        tree.addLeavesPos(2, 4, -2);
        tree.addLeavesPos(-2, 4, -2);
    }
}
