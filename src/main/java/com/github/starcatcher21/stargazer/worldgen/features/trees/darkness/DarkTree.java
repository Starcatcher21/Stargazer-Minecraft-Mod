package com.github.starcatcher21.stargazer.worldgen.features.trees.darkness;

import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import com.github.starcatcher21.stargazer.worldgen.features.trees.moon.MoonBase;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.Direction;

public class DarkTree {
    public static void init(Tree tree) {
        tree.addReplacableBlock(StarBlocks.COSMIC_BLOCK);
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        for (int i = 0; i < 6; i++) {
            tree.addLogPos(0, i, 0);
        }
        tree.addLeavesPos(1, 5, 0);
        tree.addLeavesPos(-1, 5, 0);
        tree.addLeavesPos(0, 5, 1);
        tree.addLeavesPos(0, 5, -1);
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                tree.addLeavesPos(x, 6, z);
            }
        }
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                tree.addLeavesPos(x, 7, z);
            }
        }

        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                for (int y = 3; y <= 5; y++) {
                    if ((x == -1 || x == 1) && y != 5) continue;
                    if ((z == -1 || z == 1 || (x ==0 && z == 0)) && y != 5) continue;
                    if (y == 5 && (x < 2 && x > -2) && (z < 2 && z > -2)) continue;
                    tree.addLeavesPos(x, y, z);
                }
            }
        }
        tree.addLeavesPos(3, 6, 1);
        tree.addLeavesPos(3, 7, 1);
        tree.addLeavesPos(3, 6, -1);
        tree.addLeavesPos(3, 7, -1);
        tree.addLeavesPos(-3, 6, 1);
        tree.addLeavesPos(-3, 7, 1);
        tree.addLeavesPos(-3, 6, -1);
        tree.addLeavesPos(-3, 7, -1);

        tree.addLeavesPos(1, 6, 3);
        tree.addLeavesPos(1, 7, 3);
        tree.addLeavesPos(1, 6, -3);
        tree.addLeavesPos(1, 7, -3);
        tree.addLeavesPos(-1, 6, 3);
        tree.addLeavesPos(-1, 7, 3);
        tree.addLeavesPos(-1, 6, -3);
        tree.addLeavesPos(-1, 7, -3);
    }
}
