package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.block.clases.moon.leaves.MoonLeaves;
import com.github.starcatcher21.stargazer.block.clases.moon.log.MoonLog;
import com.github.starcatcher21.stargazer.block.clases.moon.log.StrippedMoonLog;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Colors;
import net.minecraft.util.math.Direction;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;

public class Darkness {
    public static final Block STRIPPED_LOG_OF_DARKNESS = register("stripped_log_of_darkness", StrippedMoonLog::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.GRAY)
    );
    public static final Block LOG_OF_DARKNESS = register("log_of_darkness", (settings) -> new MoonLog(STRIPPED_LOG_OF_DARKNESS, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(blockState -> blockState.get(Properties.AXIS).equals(Direction.Axis.Y) ? MapColor.DEEPSLATE_GRAY : MapColor.BLACK)
    );

    public static final Block DARKNESS_LEAVES = register("darkness_leaves", (settings) -> new MoonLeaves(Colors.BLACK, settings), AbstractBlock.Settings.create()
            .solid()
            .nonOpaque()
            .ticksRandomly()
            .sounds(BlockSoundGroup.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.BLACK)
    );

    public static final Block DARKNESS_PLANKS = register("darkness_planks", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.GRAY)
    );
    public static final Block DARKNESS_PLANKS_SLAB = register("darkness_planks_slab", SlabBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.GRAY)
    );
    public static final Block DARKNESS_PLANKS_STAIRS = register("darkness_planks_stairs", (AbstractBlock.Settings settings) -> new StairsBlock(DARKNESS_PLANKS.getDefaultState(), (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.GRAY)
    );
    public static final Block DARKNESS_PLANKS_FENCE = register("darkness_planks_fence", FenceBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.GRAY)
    );
    public static final Block DARKNESS_PLANKS_FENCE_GATE = register("darkness_planks_fence_gate", (AbstractBlock.Settings settings) -> new FenceGateBlock(WoodType.OAK, (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.GRAY)
    );
    public static final Block DARKNESS_PLANKS_BUTTON = register("darkness_planks_button", (AbstractBlock.Settings settings) -> new ButtonBlock(BlockSetType.OAK, 30, (AbstractBlock.Settings) settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.GRAY)
    );

    public static void init() {}
}
