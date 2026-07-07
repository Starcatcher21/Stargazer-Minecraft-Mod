package com.github.starcatcher21.stargazer.worldgen.features.trees.purple_shroom;

import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;

public class PurpleBase {
    public static Tree base = new Tree(false, "purple_base");
    public static Tree cap = new Tree(false, "purple_cap");
    public static void purpleInit() {
        base.addLogPos(0,0,0);
        base.addLogPos(0,1,0);
        base.addLogPos(0,2,0);
        base.addLogPos(0,2,-1);
        base.addLogPos(0,3,-1);
        base.addLogPos(0,4,-1);
        base.addLogPos(0,4,-2);
        base.addLogPos(0,5,-2);
        base.addLogPos(0,6,-2);
        base.addLogPos(0,7,-2);
        base.addLogPos(0,8,-2);

        // layer top
        cap.addLeavesPos(0,1,0);
        cap.addLeavesPos(0,2,0);
        cap.addLeavesPos(0,0,0);
        cap.addLeavesPos(1,0,0);
        cap.addLeavesPos(1,0,1);
        cap.addLeavesPos(0,0,1);
        cap.addLeavesPos(0,0,-1);
        cap.addLeavesPos(1,0,-1);
        cap.addLeavesPos(-1,0,-1);
        cap.addLeavesPos(-1,0,0);
        cap.addLeavesPos(-1,0,1);
        // layer middle north
        cap.addLeavesPos(0,-1,-2);
        cap.addLeavesPos(0,-2,-2);
        cap.addLeavesPos(1,-1,-2);
        cap.addLeavesPos(1,-2,-2);
        cap.addLeavesPos(-1,-1,-2);
        cap.addLeavesPos(-1,-2,-2);
        // layer middle south
        cap.addLeavesPos(0,-1,2);
        cap.addLeavesPos(0,-2,2);
        cap.addLeavesPos(1,-1,2);
        cap.addLeavesPos(1,-2,2);
        cap.addLeavesPos(-1,-1,2);
        cap.addLeavesPos(-1,-2,2);
        // layer middle east
        cap.addLeavesPos(2,-1,0);
        cap.addLeavesPos(2,-2,0);
        cap.addLeavesPos(2,-1,1);
        cap.addLeavesPos(2,-2,1);
        cap.addLeavesPos(2,-1,-1);
        cap.addLeavesPos(2,-2,-1);
        // layer middle west
        cap.addLeavesPos(-2,-1,0);
        cap.addLeavesPos(-2,-2,0);
        cap.addLeavesPos(-2,-1,1);
        cap.addLeavesPos(-2,-2,1);
        cap.addLeavesPos(-2,-1,-1);
        cap.addLeavesPos(-2,-2,-1);
        // layer bottom north
        cap.addLeavesPos(-3, -3, 0);
        cap.addLeavesPos(-3, -4, 0);
        cap.addLeavesPos(-3, -3, 1);
        cap.addLeavesPos(-3, -4, 1);
        cap.addLeavesPos(-3, -3, -1);
        cap.addLeavesPos(-3, -4, -1);
        cap.addLeavesPos(-2, -3, 2);
        cap.addLeavesPos(-2, -4, 2);
        // layer bottom south
        cap.addLeavesPos(3, -3, 0);
        cap.addLeavesPos(3, -4, 0);
        cap.addLeavesPos(3, -3, 1);
        cap.addLeavesPos(3, -4, 1);
        cap.addLeavesPos(3, -3, -1);
        cap.addLeavesPos(3, -4, -1);
        cap.addLeavesPos(2, -3, -2);
        cap.addLeavesPos(2, -4, -2);
        // layer bottom west
        cap.addLeavesPos(0, -3, -3);
        cap.addLeavesPos(0, -4, -3);
        cap.addLeavesPos(1, -3, -3);
        cap.addLeavesPos(1, -4, -3);
        cap.addLeavesPos(-1, -3, -3);
        cap.addLeavesPos(-1, -4, -3);
        cap.addLeavesPos(-2, -3, -2);
        cap.addLeavesPos(-2, -4, -2);
        // layer bottom east
        cap.addLeavesPos(0, -3, 3);
        cap.addLeavesPos(0, -4, 3);
        cap.addLeavesPos(1, -3, 3);
        cap.addLeavesPos(1, -4, 3);
        cap.addLeavesPos(-1, -3, 3);
        cap.addLeavesPos(-1, -4, 3);
        cap.addLeavesPos(2, -3, 2);
        cap.addLeavesPos(2, -4, 2);
    }
}
