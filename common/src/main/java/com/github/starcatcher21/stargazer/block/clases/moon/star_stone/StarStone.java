package com.github.starcatcher21.stargazer.block.clases.moon.star_stone;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class StarStone extends Block {
    public StarStone(Properties settings) {
        super(settings);
    }

    public static int getLuminance(BlockState currentBlockState) {
        return 15;
    }

}
