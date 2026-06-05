package com.github.starcatcher21.stargazer.worldgen.features.chess;

import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.Blocks;

public class Pawn {
    public static Tree init(Tree tree) {
        tree.addReplacableBlock(Blocks.AIR);
        Base.init(tree);
        Shaft.init(tree, 6);
        tree.addLogPos(6,7,6);
        tree.addLogPos(6,7,9);
        tree.addLogPos(9,7,6);
        tree.addLogPos(9,7,9);
        for (int h = 8; h <= 11; h++) {
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
                tree.addLogPos(i, 12, j);
            }
        }
        return tree;
    }
}
