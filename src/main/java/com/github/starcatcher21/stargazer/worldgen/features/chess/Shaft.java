package com.github.starcatcher21.stargazer.worldgen.features.chess;

import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;

public class Shaft {
    public static void init(Tree tree, int height) {
        for (int h = 2; h <= height+1; h++) {
            for (int i = 6; i < 10; i++) {
                for (int j = 6; j < 10; j++) {
                    if (i == 6 && j == 9) continue;
                    if (i == 6 && j == 6) continue;
                    if (i == 9 && j == 6) continue;
                    if (i == 9 && j == 9) continue;
                    tree.addLogPos(i, h, j);
                }
            }
        }
    }
}
