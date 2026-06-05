package com.github.starcatcher21.stargazer.worldgen.features.chess;

import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.Blocks;

public class Bishop {
    public static Tree init(Tree tree) {
        tree.addReplacableBlock(Blocks.AIR);
        Base.init(tree);
        Shaft.init(tree, 11);
        tree.addLogPos(6,12,6);
        tree.addLogPos(6,12,9);
        tree.addLogPos(9,12,6);
        tree.addLogPos(9,12,9);
        for (int h = 13; h < 16; h++) {
            for (int i = 5; i <= 10; i++) {
                for (int j = 5; j <= 10; j++) {
                    if (i == 5 && j == 5) continue;
                    if (i == 10 && j == 5) continue;
                    if (i == 5 && j == 10) continue;
                    if (i == 10 && j == 10) continue;
                    tree.addLogPos(i, h, j);
                }
            }
        }
        tree.addLogPos(6, 16, 5);
        tree.addLogPos(7, 16, 5);
        tree.addLogPos(8, 16, 5);
        tree.addLogPos(9, 16, 5);
        tree.addLogPos(5, 16, 6);
        tree.addLogPos(6, 16, 6);
        tree.addLogPos(7, 16, 6);
        tree.addLogPos(8, 16, 6);
        tree.addLogPos(9, 16, 6);
        tree.addLogPos(10, 16, 6);

        tree.addLogPos(6, 17, 5);
        tree.addLogPos(7, 17, 5);
        tree.addLogPos(8, 17, 5);
        tree.addLogPos(9, 17, 5);
        tree.addLogPos(5, 17, 6);
        tree.addLogPos(6, 17, 6);
        tree.addLogPos(7, 17, 6);
        tree.addLogPos(8, 17, 6);
        tree.addLogPos(9, 17, 6);
        tree.addLogPos(10, 17, 6);
        tree.addLogPos(5, 17, 7);
        tree.addLogPos(6, 17, 7);
        tree.addLogPos(7, 17, 7);
        tree.addLogPos(8, 17, 7);
        tree.addLogPos(9, 17, 7);
        tree.addLogPos(10, 17, 7);

        tree.addLogPos(6, 18, 5);
        tree.addLogPos(7, 18, 5);
        tree.addLogPos(8, 18, 5);
        tree.addLogPos(9, 18, 5);
        tree.addLogPos(5, 18, 6);
        tree.addLogPos(6, 18, 6);
        tree.addLogPos(7, 18, 6);
        tree.addLogPos(8, 18, 6);
        tree.addLogPos(9, 18, 6);
        tree.addLogPos(10, 18, 6);
        tree.addLogPos(5, 18, 7);
        tree.addLogPos(6, 18, 7);
        tree.addLogPos(7, 18, 7);
        tree.addLogPos(8, 18, 7);
        tree.addLogPos(9, 18, 7);
        tree.addLogPos(10, 18, 7);
        tree.addLogPos(5, 18, 8);
        tree.addLogPos(6, 18, 8);
        tree.addLogPos(7, 18, 8);
        tree.addLogPos(8, 18, 8);
        tree.addLogPos(9, 18, 8);
        tree.addLogPos(10, 18, 8);

        tree.addLogPos(6, 19, 5);
        tree.addLogPos(7, 19, 5);
        tree.addLogPos(8, 19, 5);
        tree.addLogPos(9, 19, 5);
        tree.addLogPos(5, 19, 6);
        tree.addLogPos(6, 19, 6);
        tree.addLogPos(7, 19, 6);
        tree.addLogPos(8, 19, 6);
        tree.addLogPos(9, 19, 6);
        tree.addLogPos(10, 19, 6);
        tree.addLogPos(5, 19, 7);
        tree.addLogPos(6, 19, 7);
        tree.addLogPos(7, 19, 7);
        tree.addLogPos(8, 19, 7);
        tree.addLogPos(9, 19, 7);
        tree.addLogPos(10, 19, 7);
        tree.addLogPos(5, 19, 8);
        tree.addLogPos(6, 19, 8);
        tree.addLogPos(7, 19, 8);
        tree.addLogPos(8, 19, 8);
        tree.addLogPos(9, 19, 8);
        tree.addLogPos(10, 19, 8);
        tree.addLogPos(5, 19, 9);
        tree.addLogPos(6, 19, 9);
        tree.addLogPos(7, 19, 9);
        tree.addLogPos(8, 19, 9);
        tree.addLogPos(9, 19, 9);
        tree.addLogPos(10, 19, 9);

        tree.addLogPos(5, 16, 9);
        tree.addLogPos(6, 16, 9);
        tree.addLogPos(7, 16, 9);
        tree.addLogPos(8, 16, 9);
        tree.addLogPos(9, 16, 9);
        tree.addLogPos(10, 16, 9);
        tree.addLogPos(6, 16, 10);
        tree.addLogPos(7, 16, 10);
        tree.addLogPos(8, 16, 10);
        tree.addLogPos(9, 16, 10);
        tree.addLogPos(6, 17, 10);
        tree.addLogPos(7, 17, 10);
        tree.addLogPos(8, 17, 10);
        tree.addLogPos(9, 17, 10);
        for (int i = 6; i <= 9; i++) {
            for (int j = 6; j <=9; j++) {
                tree.addLogPos(i, 20, j);
            }
        }
        for (int i = 6; i <= 9; i++) {
            for (int j = 6; j <=9; j++) {
                if (i == 6 && j == 6) continue;
                if (i == 9 && j == 6) continue;
                if (i == 6 && j == 9) continue;
                if (i == 9 && j == 9) continue;
                tree.addLogPos(i, 21, j);
            }
        }
        tree.addLogPos(7, 22, 7);
        tree.addLogPos(7, 22, 8);
        tree.addLogPos(8, 22, 7);
        tree.addLogPos(8, 22, 8);
        return tree;
    }
}
