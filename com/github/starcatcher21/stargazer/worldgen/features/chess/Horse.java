package com.github.starcatcher21.stargazer.worldgen.features.chess;

import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.Blocks;

public class Horse {
    public static Tree init(Tree tree) {
        tree.addReplacableBlock(Blocks.AIR);
        Base.init(tree);
        for (int i = 6; i <= 9; i++) {
            for (int j = 6; j <= 9; j++) {
                if (i == 6 && j == 6) continue;
                if (i == 9 && j == 6) continue;
                if (i == 6 && j == 9) continue;
                if (i == 9 && j == 9) continue;
                tree.addLogPos(i, 2, j);
            }
        }
        for (int i = 5; i <= 8; i++) {
            for (int j = 6; j <= 9; j++) {
                if (i == 5 && j == 6) continue;
                if (i == 8 && j == 6) continue;
                if (i == 5 && j == 9) continue;
                if (i == 8 && j == 9) continue;
                tree.addLogPos(i, 3, j);
                tree.addLogPos(i, 4, j);
            }
        }
        for (int i = 4; i <= 7; i++) {
            for (int j = 6; j <= 9; j++) {
                if (i == 4 && j == 6) continue;
                if (i == 7 && j == 6) continue;
                if (i == 4 && j == 9) continue;
                if (i == 7 && j == 9) continue;
                tree.addLogPos(i, 5, j);
                tree.addLogPos(i, 6, j);
                tree.addLogPos(i, 7, j);
            }
        }
        for (int i = 5; i <= 8; i++) {
            for (int j = 6; j <= 9; j++) {
                if (i == 5 && j == 6) continue;
                if (i == 8 && j == 6) continue;
                if (i == 5 && j == 9) continue;
                if (i == 8 && j == 9) continue;
                tree.addLogPos(i, 8, j);
                tree.addLogPos(i, 9, j);
            }
        }
        tree.addLogPos(6, 10, 6);
        tree.addLogPos(6, 10, 9);
        tree.addLogPos(9, 9, 7);
        tree.addLogPos(9, 9, 8);
        tree.addLogPos(9, 8, 7);
        tree.addLogPos(9, 8, 8);
        tree.addLogPos(9, 7, 7);
        tree.addLogPos(9, 7, 8);
        tree.addLogPos(10, 8, 7);
        tree.addLogPos(10, 8, 8);
        tree.addLogPos(10, 7, 7);
        tree.addLogPos(10, 7, 8);
        return tree;
    }
}
