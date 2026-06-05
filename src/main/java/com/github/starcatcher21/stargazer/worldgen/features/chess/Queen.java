package com.github.starcatcher21.stargazer.worldgen.features.chess;

import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.Blocks;

public class Queen {
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
        for (int i = 5; i <= 10; i++) {
            for (int j = 5; j <= 10; j++) {
                tree.addLogPos(i, 17, j);
            }
        }
        tree.addLogPos(5, 19, 5);
        tree.addLogPos(10, 19, 10);
        for (int i = 5; i < 10; i++) {
            tree.addLogPos(i, 18, 5);
            tree.addLogPos(i, 18, 10);
            tree.addLogPos(5, 18, i);
            tree.addLogPos(10, 18, i);
        }
        tree.addLogPos(7, 18, 7);
        tree.addLogPos(8, 18, 7);
        tree.addLogPos(7, 18, 8);
        tree.addLogPos(8, 18, 8);
        for (int i = 6; i <= 9; i++) {
            tree.addLogPos(i, 18, 4);
            tree.addLogPos(i, 18, 11);
            tree.addLogPos(4, 18, i);
            tree.addLogPos(11, 18, i);
        }
        tree.addLogPos(7, 19, 4);
        tree.addLogPos(9, 19, 4);
        tree.addLogPos(6, 19, 11);
        tree.addLogPos(8, 19, 11);
        tree.addLogPos(4, 19, 7);
        tree.addLogPos(4, 19, 9);
        tree.addLogPos(11, 19, 6);
        tree.addLogPos(11, 19, 8);
        return tree;
    }
}
