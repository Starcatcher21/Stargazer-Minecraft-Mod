package com.github.starcatcher21.stargazer.worldgen.features.chess;

import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.Blocks;

public class Rook {
    public static Tree init(Tree tree) {
        tree.addReplacableBlock(Blocks.AIR);
        Base.init(tree);
        for (int h = 1; h <= 11; h++) {
            for (int i = 5; i <= 10; i++){
                for (int j = 5; j <= 10; j++) {
                    if (i == 5 && j == 5) continue;
                    if (i == 10 && j == 5) continue;
                    if (i == 5 && j == 10) continue;
                    if (i == 10 && j == 10) continue;
                    tree.addLogPos(i, h, j);
                }
            }
        }
        tree.addLogPos(5, 12, 6);
        tree.addLogPos(5, 13, 7);
        tree.addLogPos(5, 12, 7);
        tree.addLogPos(5, 12, 8);
        tree.addLogPos(5, 13, 9);
        tree.addLogPos(5, 12, 9);
        tree.addLogPos(10, 12, 6);
        tree.addLogPos(10, 13, 6);
        tree.addLogPos(10, 12, 7);
        tree.addLogPos(10, 12, 8);
        tree.addLogPos(10, 13, 8);
        tree.addLogPos(10, 12, 9);
        tree.addLogPos(6, 12, 5);
        tree.addLogPos(6, 13, 5);
        tree.addLogPos(7, 12, 5);
        tree.addLogPos(8, 12, 5);
        tree.addLogPos(8, 13, 5);
        tree.addLogPos(9, 12, 5);
        tree.addLogPos(6, 12, 10);
        tree.addLogPos(7, 13, 10);
        tree.addLogPos(7, 12, 10);
        tree.addLogPos(8, 12, 10);
        tree.addLogPos(9, 13, 10);
        tree.addLogPos(9, 12, 10);
        return tree;
    }
}
