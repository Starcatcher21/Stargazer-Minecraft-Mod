package com.github.starcatcher21.stargazer.worldgen.features.trees.curve;

import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;

public class CurveBase {
    public static Tree CurveLog = new Tree(false, "curveLogs");
    public static Tree SmallCurlR = new Tree(false, "smallCurlR");
    public static Tree SmallCurlL = new Tree(false, "smallCurlL");
    public static Tree SmallCurlTopR = new Tree(false, "smallCurlTopR");
    public static Tree SmallCurlTopL = new Tree(false, "smallCurlTopL");
    public static Tree MedCurlR = new Tree(false, "medCurlR");
    public static Tree MedCurlL = new Tree(false, "medCurlL");
    public static void CurveBaseInit() {
        // Logs
        CurveLog.addLogPos(0,0,0);
        CurveLog.addLogPos(0,1,0);
        CurveLog.addLogPos(0,2,0);
        CurveLog.addLogPos(0,3,-1);
        CurveLog.addLogPos(0,3,-2);
        CurveLog.addLogPos(0,3,-3);
        CurveLog.addLogPos(0,2,-4);
        CurveLog.addLogPos(0,1,-4);
        // Small Curl
        SmallCurlR.addLeavesPos(0, 0, 1);
        SmallCurlR.addLeavesPos(1, 0, 1);
        SmallCurlR.addLeavesPos(1, 0, 0);
        SmallCurlL.addLeavesPos(0, 0, 1);
        SmallCurlL.addLeavesPos(-1, 0, 1);
        SmallCurlL.addLeavesPos(-1, 0, 0);
        // Small Curl Top
        SmallCurlTopR.addLeavesPos(0, 1, 0);
        SmallCurlTopR.addLeavesPos(1, 1, 0);
        SmallCurlTopR.addLeavesPos(1, 0, 0);
        SmallCurlTopL.addLeavesPos(0, 1, 0);
        SmallCurlTopL.addLeavesPos(-1, 1, 0);
        SmallCurlTopL.addLeavesPos(-1, 0, 0);
        // Medium Curl
        MedCurlR.addLeavesPos(0, 0, 1);
        MedCurlR.addLeavesPos(1, 0, 1);
        MedCurlR.addLeavesPos(2, 0, 1);
        MedCurlR.addLeavesPos(2, 0, 0);
        MedCurlL.addLeavesPos(0, 0, 1);
        MedCurlL.addLeavesPos(-1, 0, 1);
        MedCurlL.addLeavesPos(-2, 0, 1);
        MedCurlL.addLeavesPos(-2, 0, 0);
    }
}
