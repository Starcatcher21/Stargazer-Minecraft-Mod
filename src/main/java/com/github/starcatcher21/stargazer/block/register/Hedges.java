package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.block.clases.Hedge;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;

public class Hedges {
    public static final Block OAK_HEDGE = register("oak_hedge", settings -> new Hedge(Blocks.OAK_LEAVES, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .ticksRandomly()
            .strength(2.0F)
            .mapColor(MapColor.BROWN)
    );
    public static final Block BIRCH_HEDGE = register("birch_hedge", settings -> new Hedge(Blocks.BIRCH_LEAVES, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .ticksRandomly()
            .strength(2.0F)
            .mapColor(MapColor.BROWN)
    );
    public static final Block ACACIA_HEDGE = register("acacia_hedge", settings -> new Hedge(Blocks.ACACIA_LEAVES, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .ticksRandomly()
            .strength(2.0F)
            .mapColor(MapColor.BROWN)
    );
    public static final Block CHERRY_HEDGE = register("cherry_hedge", settings -> new Hedge(Blocks.CHERRY_LEAVES, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .ticksRandomly()
            .strength(2.0F)
            .mapColor(MapColor.BROWN)
    );
    public static final Block JUNGLE_HEDGE = register("jungle_hedge", settings -> new Hedge(Blocks.JUNGLE_LEAVES, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .ticksRandomly()
            .strength(2.0F)
            .mapColor(MapColor.BROWN)
    );
    public static final Block MANGROVE_HEDGE = register("mangrove_hedge", settings -> new Hedge(Blocks.MANGROVE_LEAVES, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .ticksRandomly()
            .strength(2.0F)
            .mapColor(MapColor.BROWN)
    );
    public static final Block SPRUCE_HEDGE = register("spruce_hedge", settings -> new Hedge(Blocks.SPRUCE_LEAVES, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .ticksRandomly()
            .strength(2.0F)
            .mapColor(MapColor.BROWN)
    );
    public static final Block DARK_OAK_HEDGE = register("dark_oak_hedge", settings -> new Hedge(Blocks.DARK_OAK_LEAVES, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .ticksRandomly()
            .strength(2.0F)
            .mapColor(MapColor.BROWN)
    );
    public static final Block PALE_HEDGE = register("pale_oak_hedge", settings -> new Hedge(Blocks.PALE_OAK_LEAVES, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .ticksRandomly()
            .strength(2.0F)
            .mapColor(MapColor.BROWN)
    );
    public static final Block MOON_HEDGE = register("moon_hedge", settings -> new Hedge(MoonBlocks.MOON_LEAVES, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .ticksRandomly()
            .strength(2.0F)
            .mapColor(MapColor.BROWN)
    );
    public static final Block CURVE_HEDGE = register("curve_hedge", settings -> new Hedge(MoonBlocks.CURVE_LEAVES, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .ticksRandomly()
            .strength(2.0F)
            .mapColor(MapColor.BROWN)
    );
    public static final Block STAR_HEDGE = register("star_hedge", settings -> new Hedge(StarBlocks.STAR_LEAVES, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .ticksRandomly()
            .strength(2.0F)
            .mapColor(MapColor.BROWN)
    );
    public static final Block DARKNESS_HEDGE = register("darkness_hedge", settings -> new Hedge(Darkness.DARKNESS_LEAVES, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .ticksRandomly()
            .strength(2.0F)
            .mapColor(MapColor.BROWN)
    );
    public static final Block YERI_HEDGE = register("yeri_hedge", settings -> new Hedge(RedOrbBlocks.YERI_LEAVES, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .ticksRandomly()
            .strength(2.0F)
            .mapColor(MapColor.WHITE)
    );
    public static final Block FULL_MOON_HEDGE = register("full_moon_hedge", settings -> new Hedge(MoonBlocks.FULL_MOON_LEAVES, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .ticksRandomly()
            .strength(2.0F)
            .mapColor(MapColor.WHITE)
    );
    public static void init() {
    }
}
