package com.github.starcatcher21.stargazer.worldgen.features.chess;

import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;

public class Base {
    public static Tree init(Tree tree) {
        for (int i = 4; i <= 11; i++) {
            for (int j = 4; j <= 11; j++) {
                if (i == 4 && j == 4) continue;
                if (i == 4 && j == 11) continue;
                if (i == 11 && j == 4) continue;
                if (i == 11 && j == 11) continue;
                tree.addLogPos(i, 0, j);
            }
        }
        for (int i = 5; i <= 10; i++ ) {
            for (int j = 5; j <= 10; j++) {
                if (i == 5 && j == 5) continue;
                if (i == 5 && j == 10) continue;
                if (i == 10 && j == 5) continue;
                if (i == 10 && j == 10) continue;
                tree.addLogPos(i, 1, j);
            }
        }
        return tree;
    }
}
