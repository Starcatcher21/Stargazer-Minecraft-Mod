package com.github.starcatcher21.stargazer.worldgen.features.trees.bones;

import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class crown {
    public static void CinemaInit(Tree tree, Block fruit) {
        tree.addReplacableBlock(StarBlocks.COSMIC_BLOCK);
        tree.addReplacableBlock(Blocks.AIR);
        tree.addFruit(fruit.getDefaultState());
        tree.setFruitChange(100);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        tree.addLogPos(2, 0, 0);
        tree.addFruitsPos(2, 1, 0);
        tree.addLogPos(2, 0, 1);
        tree.addLogPos(2, 1, 1);
        tree.addLogPos(2, 0, -1);
        tree.addLogPos(2, 1, -1);
        tree.addLogPos(2, 2, 0);
        tree.addLogPos(-2, 0, 0);
        tree.addFruitsPos(-2, 1, 0);
        tree.addLogPos(-2, 2, 0);
        tree.addLogPos(-2, 0, 1);
        tree.addLogPos(-2, 1, 1);
        tree.addLogPos(-2, 0, -1);
        tree.addLogPos(-2, 1, -1);
        tree.addLogPos(0, 0, 2);
        tree.addFruitsPos(0, 1, 2);
        tree.addLogPos(0, 2, 2);
        tree.addLogPos(1, 0, 2);
        tree.addLogPos(1, 1, 2);
        tree.addLogPos(-1, 0, 2);
        tree.addLogPos(-1, 1, 2);
        tree.addLogPos(0, 0, -2);
        tree.addFruitsPos(0, 1, -2);
        tree.addLogPos(0, 2, -2);
        tree.addLogPos(1, 0, -2);
        tree.addLogPos(1, 1, -2);
        tree.addLogPos(-1, 0, -2);
        tree.addLogPos(-1, 1, -2);
    }
}
