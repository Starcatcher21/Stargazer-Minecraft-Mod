package com.github.starcatcher21.stargazer.worldgen.features.trees.nebulas;

import com.github.starcatcher21.stargazer.block.register.Nebulas;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class Purple {
    public static void init(Tree tree) {
        tree.clearLeave();
        tree.clearLog();
        tree.addLeave(Nebulas.PURPLE_NEBULA_LEAVES.getDefaultState());
        tree.addLog(Nebulas.PURPLE_NEBULA_LOG.getDefaultState().with(Properties.AXIS, Direction.Axis.Y));
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        tree.addLogPos(0,0,0);
        tree.addLogPos(0,1,0);
        tree.addLogPos(0,2,0);
        tree.addLogPos(0,3,0);
        tree.addLogPos(0,4,0);
        tree.addLogPos(1,2,0);
        tree.addLogPos(2,3,0);
        tree.addLogPos(-1,2,0);
        tree.addLogPos(-2,3,0);

        tree.addLeavesPos(-2,1,0);
        tree.addLeavesPos(2,1,0);
        tree.addLeavesPos(-3,1,0);
        tree.addLeavesPos(3,1,0);
        tree.addLeavesPos(-3,2,0);
        tree.addLeavesPos(3,2,0);
        tree.addLeavesPos(-3,2,1);
        tree.addLeavesPos(3,2,1);
        tree.addLeavesPos(-3,2,-1);
        tree.addLeavesPos(3,2,-1);
        tree.addLeavesPos(-3,3,0);
        tree.addLeavesPos(3,3,0);
        tree.addLeavesPos(-3,3,1);
        tree.addLeavesPos(3,3,1);
        tree.addLeavesPos(-3,3,-1);
        tree.addLeavesPos(3,3,-1);
        tree.addLeavesPos(-4,3,0);
        tree.addLeavesPos(4,3,0);
        tree.addLeavesPos(-3,4,0);
        tree.addLeavesPos(3,4,0);
        tree.addLeavesPos(-3,4,1);
        tree.addLeavesPos(3,4,1);
        tree.addLeavesPos(-3,4,-1);
        tree.addLeavesPos(3,4,-1);
        tree.addLeavesPos(0,4,3);
        tree.addLeavesPos(0,4,-3);
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                tree.addLeavesPos(i, 5, j);
            }
        }
        tree.addLeavesPos(0,5,-3);
        tree.addLeavesPos(0,5,3);
        tree.addLeavesPos(3,5,0);
        tree.addLeavesPos(-3,5,0);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                tree.addLeavesPos(i, 6, j);
            }
        }
    }
}
