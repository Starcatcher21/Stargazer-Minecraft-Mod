package com.github.starcatcher21.stargazer.worldgen.features.trees.nebulas;

import com.github.starcatcher21.stargazer.block.register.Nebulas;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class Yellow {
    public static void init(Tree tree) {
        tree.clearLeave();
        tree.clearLog();
        tree.addLeave(Nebulas.YELLOW_NEBULA_LEAVES.getDefaultState());
        tree.addLog(Nebulas.YELLOW_NEBULA_LOG.getDefaultState().with(Properties.AXIS, Direction.Axis.Y));
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        for (int i = 0; i <= 7; i++) {
            tree.addLogPos(0, i, 0);
        }
        for (int i = -1; i<= 1; i++) {
            tree.addLogPos(i, 1, 2);
            tree.addLogPos(i, 1, -2);
            tree.addLogPos(2, 1, i);
            tree.addLogPos(-2, 1, i);
            tree.addLogPos(i, 5, 2);
            tree.addLogPos(i, 5, -2);
            tree.addLogPos(2, 5, i);
            tree.addLogPos(-2, 5, i);
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                tree.addLeavesPos(0, i+1, j+2);
                tree.addLeavesPos(0, i+1, j-2);
                tree.addLeavesPos(j+2, i+1, 0);
                tree.addLeavesPos(j-2, i+1, 0);

                tree.addLeavesPos(0, i+5, j+2);
                tree.addLeavesPos(0, i+5, j-2);
                tree.addLeavesPos(j+2, i+5, 0);
                tree.addLeavesPos(j-2, i+5, 0);
            }
        }
        tree.addLeavesPos(2, 1, 2);
        tree.addLeavesPos(2, 2, 2);
        tree.addLeavesPos(2, 0, 2);
        tree.addLeavesPos(3, 1, 2);
        tree.addLeavesPos(2, 1, 3);
        tree.addLeavesPos(2, 5, 2);
        tree.addLeavesPos(2, 6, 2);
        tree.addLeavesPos(2, 4, 2);
        tree.addLeavesPos(3, 5, 2);
        tree.addLeavesPos(2, 5, 3);
        tree.addLeavesPos(2, 1, -2);
        tree.addLeavesPos(2, 2, -2);
        tree.addLeavesPos(2, 0, -2);
        tree.addLeavesPos(3, 1, -2);
        tree.addLeavesPos(2, 1, -3);
        tree.addLeavesPos(2, 5, -2);
        tree.addLeavesPos(2, 6, -2);
        tree.addLeavesPos(2, 4, -2);
        tree.addLeavesPos(3, 5, -2);
        tree.addLeavesPos(2, 5, -3);
        tree.addLeavesPos(-2, 1, 2);
        tree.addLeavesPos(-2, 2, 2);
        tree.addLeavesPos(-2, 0, 2);
        tree.addLeavesPos(-3, 1, 2);
        tree.addLeavesPos(-2, 1, 3);
        tree.addLeavesPos(-2, 5, 2);
        tree.addLeavesPos(-2, 6, 2);
        tree.addLeavesPos(-2, 4, 2);
        tree.addLeavesPos(-3, 5, 2);
        tree.addLeavesPos(-2, 5, 3);
        tree.addLeavesPos(-2, 1, -2);
        tree.addLeavesPos(-2, 2, -2);
        tree.addLeavesPos(-2, 0, -2);
        tree.addLeavesPos(-3, 1, -2);
        tree.addLeavesPos(-2, 1, -3);
        tree.addLeavesPos(-2, 5, -2);
        tree.addLeavesPos(-2, 6, -2);
        tree.addLeavesPos(-2, 4, -2);
        tree.addLeavesPos(-3, 5, -2);
        tree.addLeavesPos(-2, 5, -3);
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                tree.addLeavesPos(i, 8, j);
            }
        }
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                tree.addLeavesPos(i, 9, j);
            }
        }
    }
}
