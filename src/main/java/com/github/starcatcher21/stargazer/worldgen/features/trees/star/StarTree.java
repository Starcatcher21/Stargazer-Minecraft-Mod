package com.github.starcatcher21.stargazer.worldgen.features.trees.star;

import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.Direction;

public class StarTree {
    public static Tree NewStarTree(Boolean rotatable, String name, int height) {
        Tree tree = new Tree(rotatable, name, StarBlocks.STAR_LOG.getDefaultState(), StarBlocks.STAR_LEAVES.getDefaultState());
        init(tree, height);
        return tree;
    }

    public static void addBrandchesType1(Tree tree, int y) {
        tree.addLogPos(1,  y, 0);
        tree.addLogPos(2, y, 0);
        tree.addLogPos(3, y+1, 0);
        tree.addLogPos(-1, y, 0);
        tree.addLogPos(-2, y, 0);
        tree.addLogPos(-3, y+1, 0);
        tree.addLogPos(0, y, 1);
        tree.addLogPos(0, y, 2);
        tree.addLogPos(0, y+1, 3);
        tree.addLogPos(0, y, -1);
        tree.addLogPos(0, y, -2);
        tree.addLogPos(0, y+1, -3);

        // 1 l
        tree.addLeavesPos(1, y, 1);
        tree.addLeavesPos(2, y, 1);
        tree.addLeavesPos(1, y, 2);

        tree.addLeavesPos(0, y, 3);
        tree.addLeavesPos(0, y, 4);

        tree.addLeavesPos(-1, y, 1);
        tree.addLeavesPos(-2, y, 1);
        tree.addLeavesPos(-1, y, 2);

        tree.addLeavesPos(0, y, -3);
        tree.addLeavesPos(0, y, -4);

        tree.addLeavesPos(1, y, -1);
        tree.addLeavesPos(2, y, -1);
        tree.addLeavesPos(1, y, -2);

        tree.addLeavesPos(3, y, 0);
        tree.addLeavesPos(4, y, 0);

        tree.addLeavesPos(-1, y, -1);
        tree.addLeavesPos(-2, y, -1);
        tree.addLeavesPos(-1, y, -2);

        tree.addLeavesPos(-3, y, 0);
        tree.addLeavesPos(-4, y, 0);

        // 2 l
        tree.addLeavesPos(1, y+1, 0);
        tree.addLeavesPos(2, y+1, 0);
        tree.addLeavesPos(-1, y+1, 0);
        tree.addLeavesPos(-2, y+1, 0);
        tree.addLeavesPos(0, y+1, 1);
        tree.addLeavesPos(0, y+1, 2);
        tree.addLeavesPos(0, y+1, -1);
        tree.addLeavesPos(0, y+1, -2);
        tree.addLeavesPos(4, y+1, 0);
        tree.addLeavesPos(5, y+1, 0);
        tree.addLeavesPos(-4, y+1, 0);
        tree.addLeavesPos(-5, y+1, 0);
        tree.addLeavesPos(0, y+1, 4);
        tree.addLeavesPos(0, y+1, 5);
        tree.addLeavesPos(0, y+1, -4);
        tree.addLeavesPos(0, y+1, -5);

        tree.addLeavesPos(1, y+1, 1);
        tree.addLeavesPos(1, y+1, 2);
        tree.addLeavesPos(2, y+1, 1);
        tree.addLeavesPos(2, y+1, 2);

        tree.addLeavesPos(1, y+1, -1);
        tree.addLeavesPos(1, y+1, -2);
        tree.addLeavesPos(2, y+1, -1);
        tree.addLeavesPos(2, y+1, -2);

        tree.addLeavesPos(-1, y+1, 1);
        tree.addLeavesPos(-1, y+1, 2);
        tree.addLeavesPos(-2, y+1, 1);
        tree.addLeavesPos(-2, y+1, 2);

        tree.addLeavesPos(-1, y+1, -1);
        tree.addLeavesPos(-1, y+1, -2);
        tree.addLeavesPos(-2, y+1, -1);
        tree.addLeavesPos(-2, y+1, -2);

        tree.addLeavesPos(3, y+1, 1);
        tree.addLeavesPos(4, y+1, 1);
        tree.addLeavesPos(3, y+1, -1);
        tree.addLeavesPos(4, y+1, -1);

        tree.addLeavesPos(-3, y+1, 1);
        tree.addLeavesPos(-4, y+1, 1);
        tree.addLeavesPos(-3, y+1, -1);
        tree.addLeavesPos(-4, y+1, -1);

        tree.addLeavesPos(1, y+1, 3);
        tree.addLeavesPos(1, y+1, 4);
        tree.addLeavesPos(-1, y+1, 3);
        tree.addLeavesPos(-1, y+1, 4);

        tree.addLeavesPos(1, y+1, -3);
        tree.addLeavesPos(1, y+1, -4);
        tree.addLeavesPos(-1, y+1, -3);
        tree.addLeavesPos(-1, y+1, -4);

        // 3 l
        tree.addLeavesPos(1, y+2, 1);
        tree.addLeavesPos(2, y+2, 1);
        tree.addLeavesPos(1, y+2, 2);

        tree.addLeavesPos(0, y+2, 3);
        tree.addLeavesPos(0, y+2, 4);

        tree.addLeavesPos(-1, y+2, 1);
        tree.addLeavesPos(-2, y+2, 1);
        tree.addLeavesPos(-1, y+2, 2);

        tree.addLeavesPos(0, y+2, -3);
        tree.addLeavesPos(0, y+2, -4);

        tree.addLeavesPos(1, y+2, -1);
        tree.addLeavesPos(2, y+2, -1);
        tree.addLeavesPos(1, y+2, -2);

        tree.addLeavesPos(3, y+2, 0);
        tree.addLeavesPos(4, y+2, 0);

        tree.addLeavesPos(-1, y+2, -1);
        tree.addLeavesPos(-2, y+2, -1);
        tree.addLeavesPos(-1, y+2, -2);

        tree.addLeavesPos(-3, y+2, 0);
        tree.addLeavesPos(-4, y+2, 0);

        tree.addLeavesPos(1, y+2, 0);
        tree.addLeavesPos(2, y+2, 0);
        tree.addLeavesPos(-1, y+2, 0);
        tree.addLeavesPos(-2, y+2, 0);
        tree.addLeavesPos(0, y+2, 1);
        tree.addLeavesPos(0, y+2, 2);
        tree.addLeavesPos(0, y+2, -1);
        tree.addLeavesPos(0, y+2, -2);
    }
    public static void addBrandchesType2(Tree tree, int y) {
        tree.addLogPos(1, y, 0);
        tree.addLogPos(-1, y, 0);
        tree.addLogPos(0, y, 1);
        tree.addLogPos(0, y, -1);

        // 4 l
        tree.addLeavesPos(0, y, 2);
        tree.addLeavesPos(0, y, -2);
        tree.addLeavesPos(2, y, 0);
        tree.addLeavesPos(-2, y, 0);

        tree.addLeavesPos(1, y, 1);
        tree.addLeavesPos(-1, y, 1);
        tree.addLeavesPos(1, y, -1);
        tree.addLeavesPos(-1, y, -1);
    }

    public static void init(Tree tree, int HEIGHT) {
        tree.addReplacableBlock(StarBlocks.COSMIC_BLOCK);
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        Tree branch = Tree.genBranch(HEIGHT);
        Tree.addBranch(tree, branch, Direction.NORTH);

        int y = HEIGHT - 5;
//        tree.addBrandchesType1(y);
        if (HEIGHT - 4 > 0) {
            for (int i = 0; i < (HEIGHT-4)/4; i ++) {
                addBrandchesType1(tree, y - 3*i);
            }
        }
        addBrandchesType2(tree, y+3);

        // top leaves
        // 5/6 l
        tree.addLeavesPos(1, y+4, 0);
        tree.addLeavesPos(1, y+5, 0);
        tree.addLeavesPos(-1, y+4, 0);
        tree.addLeavesPos(-1, y+5, 0);
        tree.addLeavesPos(0, y+4, 1);
        tree.addLeavesPos(0, y+5, 1);
        tree.addLeavesPos(0, y+4, -1);
        tree.addLeavesPos(0, y+5, -1);

        // 6/y+4/y+5 l
        tree.addLeavesPos(0, y+5, 0);
        tree.addLeavesPos(0, y+6, 0);
        tree.addLeavesPos(0, y+7, 0);
    }
}
