package com.github.starcatcher21.stargazer.worldgen.features.trees.moon;

import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;

public class MoonBase {
    public static Tree leavepattern = new Tree(false, "moonLeaves");
    public static void leavesInit() {
        // leaves base
        leavepattern.addLeavesPos(0, -2, 1);
        leavepattern.addLeavesPos(0, -1, 1);
        leavepattern.addLeavesPos(1, -2, 0);
        leavepattern.addLeavesPos(1, -1, 0);
        leavepattern.addLeavesPos(-1, -2, 0);
        leavepattern.addLeavesPos(-1, -1, 0);
        leavepattern.addLeavesPos(0, -2, -1);
        leavepattern.addLeavesPos(0, -1, -1);
        // fruit
        leavepattern.addFruitsPos(0, -3, 1);
        leavepattern.addFruitsPos(0, -3, -1);
        leavepattern.addFruitsPos(1, -3, 0);
        leavepattern.addFruitsPos(-1, -3, 0);
        // leaves top ring 1
        leavepattern.addLeavesPos(0, 0, -2);
        leavepattern.addLeavesPos(0, 0, -1);
        leavepattern.addLeavesPos(0, 0, 0);
        leavepattern.addLeavesPos(0, 0, 1);
        leavepattern.addLeavesPos(0, 0, 2);
        leavepattern.addLeavesPos(-2, 0, 0);
        leavepattern.addLeavesPos(-1, 0, 0);
        leavepattern.addLeavesPos(1, 0, 0);
        leavepattern.addLeavesPos(2, 0, 0);
        leavepattern.addLeavesPos(1, 0, 1);
        leavepattern.addLeavesPos(-1, 0, 1);
        leavepattern.addLeavesPos(1, 0, -1);
        leavepattern.addLeavesPos(-1, 0, -1);
        // fruit
        leavepattern.addFruitsPos(0, -1, -2);
        leavepattern.addFruitsPos(-2, -1, 0);
        leavepattern.addFruitsPos(0, -1, 2);
        leavepattern.addFruitsPos(2, -1, 0);
        leavepattern.addFruitsPos(1, -1, 1);
        leavepattern.addFruitsPos(-1, -1, 1);
        leavepattern.addFruitsPos(1, -1, -1);
        leavepattern.addFruitsPos(-1, -1, -1);
        // leaves top ring 2
        leavepattern.addLeavesPos(0, 1, -1);
        leavepattern.addLeavesPos(0, 1, 0);
        leavepattern.addLeavesPos(0, 1, 1);
        leavepattern.addLeavesPos(1, 1,  0);
        leavepattern.addLeavesPos(-1, 1, 0);
        // top
        leavepattern.addLeavesPos(0, 2, 0);
    }
}
