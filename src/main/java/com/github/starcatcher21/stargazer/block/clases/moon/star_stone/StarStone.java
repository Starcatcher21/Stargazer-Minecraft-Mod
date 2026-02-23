package com.github.starcatcher21.stargazer.block.clases.moon.star_stone;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

public class StarStone extends Block {
    public StarStone(Settings settings) {
        super(settings);
    }

    public static int getLuminance(BlockState currentBlockState) {
        return 15;
    }

}
