package com.github.starcatcher21.stargazer.block.register;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;

public class RedOrbBlocks {
    public static final Block RED_ROCK = register("red_rock", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .strength(0.5f)
            .requiresTool()
            .mapColor(MapColor.ORANGE)
    );

    public static void init() {}
}
