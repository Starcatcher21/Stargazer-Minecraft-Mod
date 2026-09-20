package com.github.starcatcher21.stargazer.worldgen.features.trees.crystals;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.starlib.worldgen.features.trees.Tree;
import net.minecraft.world.level.block.state.BlockState;

public class crystal1 {
    public static void init(Tree tree) {
        tree.addReplacableBlock(MoonBlocks.MOON_ROCK);
        tree.addLogPos(0, 0, 0);
    }
}
