package com.github.starcatcher21.stargazer.worldgen.features.trees.nebulas;

import com.github.starcatcher21.stargazer.block.register.Nebulas;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class Blue {
    public static void init(Tree tree) {
        tree.addReplacableBlock(Blocks.AIR);
        for (BlockState state : tree.leave) {
            tree.addReplacableBlock(state.getBlock());
        }
        // logs
        tree.addLogPos(0,0,0);
        tree.addLogPos(0,1,0);
        tree.addLogPos(0,2,0);
        tree.addLogPos(0,3,0);
        tree.addLogPos(1,3,0);
        tree.addLogPos(-1,3,0);
        tree.addLogPos(0,4,0);
        tree.addLogPos(0,5,0);
        tree.addLogPos(0,6,0);
        tree.addLogPos(0,6,1);
        tree.addLogPos(0,6,-1);
        tree.addLogPos(0,7,0);
        tree.addLogPos(0,8,0);

        tree.addLeavesPos(1, 1, 0);
        tree.addLeavesPos(-1, 1, 0);
        tree.addLeavesPos(2, 1, 0);
        tree.addLeavesPos(-2, 1, 0);
        tree.addLeavesPos(2, 1, 1);
        tree.addLeavesPos(-2, 1, 1);
        tree.addLeavesPos(2, 1, -1);
        tree.addLeavesPos(-2, 1, -1);
        for (int i = 1; i <=7; i++) {
            tree.addLeavesPos(2, i, 2);
        }
        for (int i = 1; i <=7; i++) {
            tree.addLeavesPos(-2, i, 2);
        }
        for (int i = 1; i <=7; i++) {
            tree.addLeavesPos(2, i, -2);
        }
        for (int i = 1; i <=7; i++) {
            tree.addLeavesPos(-2, i, -2);
        }
        tree.addLeavesPos(2, 3, 0);
        tree.addLeavesPos(-2, 3, 0);
        tree.addLeavesPos(2, 4, 0);
        tree.addLeavesPos(-2, 4, 0);
        tree.addLeavesPos(0, 4, 1);
        tree.addLeavesPos(0, 4, -1);
        tree.addLeavesPos(0, 4, 2);
        tree.addLeavesPos(0, 4, -2);
        tree.addLeavesPos(1, 4, 2);
        tree.addLeavesPos(1, 4, -2);
        tree.addLeavesPos(-1, 4, 2);
        tree.addLeavesPos(-1, 4, -2);
        tree.addLeavesPos(-2, 5, 0);
        tree.addLeavesPos(-2, 5, -1);
        tree.addLeavesPos(2, 5, 0);
        tree.addLeavesPos(2, 5, 1);
        tree.addLeavesPos(0, 6, 2);
        tree.addLeavesPos(0, 6, -2);
        tree.addLeavesPos(0, 7, 2);
        tree.addLeavesPos(0, 7, -2);
        tree.addLeavesPos(1, 7, 2);
        tree.addLeavesPos(-1, 7, -2);
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                tree.addLeavesPos(i, 9, j);
            }
        }
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                tree.addLeavesPos(i, 10, j);
            }
        }
    }
}
