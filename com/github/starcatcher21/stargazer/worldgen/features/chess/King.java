package com.github.starcatcher21.stargazer.worldgen.features.chess;

import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.Blocks;

public class King {
    public static Tree init(Tree tree) {
        tree.addReplacableBlock(Blocks.AIR);
        Base.init(tree);
        Shaft.init(tree, 11);
        tree.addLogPos(6,12,6);
        tree.addLogPos(6,12,9);
        tree.addLogPos(9,12,6);
        for (int h = 13; h < 17; h++) {
            for (int i= 5; i <= 10; i++) {
                for (int j= 5; j <= 10; j++) {
                    if (i == 5 && j == 5) continue;
                    if (i == 5 && j == 10) continue;
                    if (i == 10 && j == 5) continue;
                    if (i == 10 && j == 10) continue;
                    tree.addLogPos(i, h, j);
                }
            }
        }
        for (int i = 6; i <= 9; i++) {
            for (int j = 6; j <= 9; j++) {
                tree.addLogPos(i, 17, j);
            }
        }
        tree.addLogPos(7, 18, 6);
        tree.addLogPos(7, 19, 6);
        tree.addLogPos(8, 18, 6);
        tree.addLogPos(6, 18, 7);
        tree.addLogPos(6, 18, 8);
        tree.addLogPos(6, 19, 8);
        tree.addLogPos(9, 18, 7);
        tree.addLogPos(9, 18, 8);
        tree.addLogPos(9, 19, 7);
        tree.addLogPos(7, 18, 9);
        tree.addLogPos(8, 18, 9);
        tree.addLogPos(8, 19, 9);
        return tree;
    }
}
