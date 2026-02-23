package com.github.starcatcher21.stargazer.worldgen.features.trees.purple_shroom;

import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.Direction;

public class Purple1 {
    public static void init(Tree tree) {
        tree.addReplacableBlock(StarBlocks.COSMIC_BLOCK);
        tree.addReplacableBlock(Blocks.AIR);
        Tree base = PurpleBase.base;
        Tree cap = PurpleBase.cap;
        Tree.addBranch(tree, base, Direction.NORTH);
        Tree.addBranch(tree, Tree.offset(Tree.offset(cap, Direction.NORTH, 2), Direction.UP, 9), Direction.NORTH);
    }
}
