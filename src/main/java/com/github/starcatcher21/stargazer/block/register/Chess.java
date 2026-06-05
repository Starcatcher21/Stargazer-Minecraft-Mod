package com.github.starcatcher21.stargazer.block.register;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.sound.BlockSoundGroup;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;

public class Chess {
    public static final Block WHITE_CHESSBOARD = register("white_chessboard", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .strength(2.0F)
            .mapColor(MapColor.WHITE)
    );
    public static final Block BLACK_CHESSBOARD = register("black_chessboard", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .strength(2.0F)
            .mapColor(MapColor.WHITE)
    );

    public static void init() {
    }
}
