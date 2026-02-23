package com.github.starcatcher21.stargazer.worldgen.features.trees.moon;

import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.Direction;

public class MoonTree3 {
    public static void init(Tree tree) {
        tree.addReplacableBlock(StarBlocks.COSMIC_BLOCK);
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        tree.addLogPos(0,0,0);
        tree.addLogPos(0,1,0);
        Tree branch = Tree.offset(Tree.genBranch(3), Direction.NORTH, 1);
        Tree branc = Tree.offset(branch, Direction.UP, 2);
        Tree leaves = Tree.offset(MoonBase.leavepattern, Direction.NORTH, 1);
        Tree leave = Tree.offset(leaves, Direction.UP, 5);
        Tree.addBranch(tree, branc, Direction.NORTH);
        Tree.addBranch(tree, leave, Direction.NORTH);
        Tree.addBranch(tree, branc, Direction.SOUTH);
        Tree.addBranch(tree, leave, Direction.SOUTH);
    }
}
