package com.github.starcatcher21.stargazer.worldgen.features.trees.curve;

import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.DirectionalTree;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.Direction;

public class CurveTree {
    public static void init(Tree tree, Direction stRot, Boolean stLR, Direction ntRot, int ntOff, Boolean ntLR, Boolean stELR, Boolean ntELR) {
        tree.addReplacableBlock(StarBlocks.COSMIC_BLOCK);
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        Tree base = CurveBase.CurveLog;
        Tree stCurlL = Tree.offset(DirectionalTree.getFromNorth(CurveBase.SmallCurlL, stRot), Direction.UP, 1);
        Tree ntCurlL = Tree.offset(Tree.offset(DirectionalTree.getFromNorth(CurveBase.SmallCurlTopL, ntRot), Direction.UP, 3), Direction.NORTH,  ntOff);
        Tree stCurlR = Tree.offset(DirectionalTree.getFromNorth(CurveBase.SmallCurlR, stRot), Direction.UP, 1);
        Tree ntCurlR = Tree.offset(Tree.offset(DirectionalTree.getFromNorth(CurveBase.SmallCurlTopR, ntRot), Direction.UP, 3), Direction.NORTH, ntOff);
        Tree end1 = Tree.offset(Tree.offset(CurveBase.MedCurlL, Direction.UP, 1), Direction.NORTH, 4);
        Tree end3 = Tree.offset(Tree.offset(DirectionalTree.getFromNorth(CurveBase.MedCurlL, Direction.SOUTH), Direction.UP, 1), Direction.NORTH, 4);
        Tree end2 = Tree.offset(Tree.offset(DirectionalTree.getFromNorth(CurveBase.MedCurlR, Direction.WEST), Direction.UP, 2), Direction.NORTH, 4);
        Tree stEndL = Tree.offset(CurveBase.SmallCurlL, Direction.NORTH, 5);
        Tree stEndR = Tree.offset(CurveBase.SmallCurlR, Direction.NORTH, 5);
        Tree ntEndL = Tree.offset(Tree.offset(CurveBase.SmallCurlL, Direction.NORTH, 5), Direction.DOWN, 1);
        Tree ntEndR = Tree.offset(Tree.offset(CurveBase.SmallCurlR, Direction.NORTH, 5), Direction.DOWN, 1);
        Tree.addBranch(tree, base, Direction.NORTH);
        if (stLR) {
            Tree.addBranch(tree, stCurlL, Direction.NORTH);
        } else {
            Tree.addBranch(tree, stCurlR, Direction.NORTH);
        }
        if (ntLR) {
            Tree.addBranch(tree, ntCurlL, Direction.NORTH);
        } else {
            Tree.addBranch(tree, ntCurlR, Direction.NORTH);
        }
        Tree.addBranch(tree, end1, Direction.NORTH);
        Tree.addBranch(tree, end3, Direction.NORTH);
        Tree.addBranch(tree, end2, Direction.NORTH);
        tree.addLeavesPos(0, 3, -4);
        tree.addLeavesPos(1, 3, -4);
        tree.addLeavesPos(1, 3, -3);
        tree.addLeavesPos(-1, 3, -3);
        tree.addLeavesPos(-2, 3, -3);
        tree.addLeavesPos(-2, 3, -4);
        tree.addLeavesPos(0, 4, -3);
        tree.addLeavesPos(1, 4, -3);
        tree.addLeavesPos(2, 4, -3);
        tree.addLeavesPos(2, 4, -2);
        if (stELR) {
            Tree.addBranch(tree, stEndL, Direction.NORTH);
        } else {
            Tree.addBranch(tree, stEndR, Direction.NORTH);
        }
        if (ntELR) {
            Tree.addBranch(tree, ntEndL, Direction.NORTH);
        } else {
            Tree.addBranch(tree, ntEndR, Direction.NORTH);
        }
    }
}
