package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.block.clases.Chessboard;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
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
    public static final Block CHESSBOARD = register("chessboard", Chessboard::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .strength(0.4f)
            .mapColor(MapColor.SPRUCE_BROWN)
    );

    public static void init() {
    }
}
