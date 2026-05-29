package com.github.starcatcher21.stargazer.worldgen.features.trees.nebulas;

import com.github.starcatcher21.stargazer.block.register.Nebulas;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class Red {
    public static void init(Tree tree) {
        tree.clearLeave();
        tree.clearLog();
        tree.addLeave(Nebulas.RED_NEBULA_LEAVES.getDefaultState());
        tree.addLog(Nebulas.RED_NEBULA_LOG.getDefaultState().with(Properties.AXIS, Direction.Axis.Y));
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        tree.addLogPos(0,0,0);
        tree.addLogPos(0,1,0);
        tree.addLogPos(0,2,0);
        tree.addLogPos(1,3,0);
        tree.addLogPos(2,3,0);
        tree.addLogPos(3,3,0);
        tree.addLogPos(4,2,1);
        tree.addLogPos(4,2,2);
        tree.addLogPos(4,2,3);
        tree.addLogPos(3,1,4);
        tree.addLogPos(2,1,4);
        tree.addLogPos(1,1,4);
        tree.addLogPos(0,0,3);
        tree.addLogPos(0,0,2);

        tree.addLeavesPos(0, 3, 0);
        tree.addLeavesPos(-1, 3, 0);
        tree.addLeavesPos(0, 3, 1);
        tree.addLeavesPos(0, 3, -1);
        tree.addLeavesPos(0, 4, 0);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                tree.addLeavesPos(2, i+3, j);
            }
        }
        tree.addLeavesPos(4, 3, 0);
        tree.addLeavesPos(4, 4, 0);
        tree.addLeavesPos(5, 3, 0);
        tree.addLeavesPos(4, 2, 0);
        tree.addLeavesPos(4, 3, 1);
        tree.addLeavesPos(4, 3, -1);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                tree.addLeavesPos(i+4, j+2, 2);
            }
        }
        tree.addLeavesPos(4, 2, 4);
        tree.addLeavesPos(4, 3, 4);
        tree.addLeavesPos(4, 1, 4);
        tree.addLeavesPos(4, 2, 5);
        tree.addLeavesPos(5, 2, 4);
        tree.addLeavesPos(3, 2, 4);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                tree.addLeavesPos(2, i+1, j+4);
            }
        }
        tree.addLeavesPos(0,1,4);
        tree.addLeavesPos(0,2,4);
        tree.addLeavesPos(0,0,4);
        tree.addLeavesPos(-1,1,4);
        tree.addLeavesPos(0,1,5);
        tree.addLeavesPos(0,1,3);
        tree.addLeavesPos(-1, 0, 2);
        tree.addLeavesPos(-1, 1, 2);
        tree.addLeavesPos(0, 1, 2);
        tree.addLeavesPos(1, 1, 2);
        tree.addLeavesPos(1, 0, 2);
        tree.addLeavesPos(0, 0, 1);
    }
}
