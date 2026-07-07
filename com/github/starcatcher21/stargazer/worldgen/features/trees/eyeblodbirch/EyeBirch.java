package com.github.starcatcher21.stargazer.worldgen.features.trees.eyeblodbirch;

import com.github.starcatcher21.stargazer.block.register.EyeBloodBlocks;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class EyeBirch {
    public static Tree NewStarTree(Boolean rotatable, String name, int height) {
        Tree tree = new Tree(rotatable, name, EyeBloodBlocks.EYE_LOG.getDefaultState(), EyeBloodBlocks.EYE_LEAVES.getDefaultState());
        tree.addLog(EyeBloodBlocks.EYE_LOG.getDefaultState().with(Properties.EYE, true));
        tree.addLog(EyeBloodBlocks.STRIPPED_EYE_LOG.getDefaultState());
        init(tree, height);
        return tree;
    }

    public static void init(Tree tree, int HEIGHT) {
        tree.addReplacableBlock(StarBlocks.COSMIC_BLOCK);
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        Tree branch = Tree.genBranch(HEIGHT);
        Tree.addBranch(tree, branch, Direction.NORTH);

        tree.addLeavesPos(0, HEIGHT, 0);
        tree.addLeavesPos(1, HEIGHT, 0);
        tree.addLeavesPos(1, HEIGHT, 1);
        tree.addLeavesPos(0, HEIGHT, 1);
        tree.addLeavesPos(-1, HEIGHT, 1);
        tree.addLeavesPos(-1, HEIGHT, 0);
        tree.addLeavesPos(-1, HEIGHT, -1);
        tree.addLeavesPos(0, HEIGHT, -1);
        tree.addLeavesPos(1, HEIGHT, -1);
        tree.addLeavesPos(0, HEIGHT+1, 0);
        tree.addLeavesPos(1, HEIGHT+1, 0);
        tree.addLeavesPos(-1, HEIGHT+1, 0);
        tree.addLeavesPos(0, HEIGHT+1, 1);
        tree.addLeavesPos(0, HEIGHT+1, -1);
        // Near log
        tree.addLeavesPos(1, HEIGHT-1, -1);
        tree.addLeavesPos(1, HEIGHT-1, 0);
        tree.addLeavesPos(1, HEIGHT-1, 1);
        tree.addLeavesPos(0, HEIGHT-1, 1);
        tree.addLeavesPos(-1, HEIGHT-1, 1);
        tree.addLeavesPos(-1, HEIGHT-1, 0);
        tree.addLeavesPos(-1, HEIGHT-1, -1);
        tree.addLeavesPos(0, HEIGHT-1, -1);
        // Near log 2
        tree.addLeavesPos(1, HEIGHT-2, -1);
        tree.addLeavesPos(1, HEIGHT-2, 0);
        tree.addLeavesPos(1, HEIGHT-2, 1);
        tree.addLeavesPos(0, HEIGHT-2, 1);
        tree.addLeavesPos(-1, HEIGHT-2, 1);
        tree.addLeavesPos(-1, HEIGHT-2, 0);
        tree.addLeavesPos(-1, HEIGHT-2, -1);
        tree.addLeavesPos(0, HEIGHT-2, -1);
        // Far log
        tree.addLeavesPos(2, HEIGHT-1, 0);
        tree.addLeavesPos(2, HEIGHT-1, 1);
        tree.addLeavesPos(2, HEIGHT-1, 2);
        tree.addLeavesPos(2, HEIGHT-1, -1);
//        tree.addLeavesPos(2, HEIGHT-1, -2);
        tree.addLeavesPos(-2, HEIGHT-1, 0);
        tree.addLeavesPos(-2, HEIGHT-1, 1);
//        tree.addLeavesPos(-2, HEIGHT-1, 2);
        tree.addLeavesPos(-2, HEIGHT-1, -1);
        tree.addLeavesPos(-2, HEIGHT-1, -2);
        tree.addLeavesPos(0, HEIGHT-1, 2);
        tree.addLeavesPos(1, HEIGHT-1, 2);
        tree.addLeavesPos(-1, HEIGHT-1, 2);
        tree.addLeavesPos(0, HEIGHT-1, -2);
        tree.addLeavesPos(1, HEIGHT-1, -2);
        tree.addLeavesPos(-1, HEIGHT-1, -2);
        // Far log 2
        tree.addLeavesPos(2, HEIGHT-2, 0);
        tree.addLeavesPos(2, HEIGHT-2, 1);
//        tree.addLeavesPos(2, HEIGHT-2, 2);
        tree.addLeavesPos(2, HEIGHT-2, -1);
        tree.addLeavesPos(2, HEIGHT-2, -2);
        tree.addLeavesPos(-2, HEIGHT-2, 0);
        tree.addLeavesPos(-2, HEIGHT-2, 1);
        tree.addLeavesPos(-2, HEIGHT-2, 2);
        tree.addLeavesPos(-2, HEIGHT-2, -1);
//        tree.addLeavesPos(-2, HEIGHT-2, -2);
        tree.addLeavesPos(0, HEIGHT-2, 2);
        tree.addLeavesPos(1, HEIGHT-2, 2);
        tree.addLeavesPos(-1, HEIGHT-2, 2);
        tree.addLeavesPos(0, HEIGHT-2, -2);
        tree.addLeavesPos(1, HEIGHT-2, -2);
        tree.addLeavesPos(-1, HEIGHT-2, -2);
    }
}
