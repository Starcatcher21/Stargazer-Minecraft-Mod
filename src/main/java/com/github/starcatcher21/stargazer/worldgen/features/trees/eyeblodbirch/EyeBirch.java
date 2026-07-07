package com.github.starcatcher21.stargazer.worldgen.features.trees.eyeblodbirch;

import com.github.starcatcher21.stargazer.block.register.EyeBloodBlocks;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class EyeBirch {
    public static Tree NewStarTree(Boolean rotatable, String name, int height) {
        Tree tree = new Tree(rotatable, name, EyeBloodBlocks.EYE_LOG.defaultBlockState(), EyeBloodBlocks.EYE_LEAVES.defaultBlockState());
        tree.addLog(EyeBloodBlocks.EYE_LOG.defaultBlockState().setValue(BlockStateProperties.EYE, true));
        tree.addLog(EyeBloodBlocks.STRIPPED_EYE_LOG.defaultBlockState());
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
