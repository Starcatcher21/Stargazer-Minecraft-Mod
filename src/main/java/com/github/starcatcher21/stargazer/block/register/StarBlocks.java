package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.Helpers;
import com.github.starcatcher21.stargazer.block.clases.CustomSapling;
import com.github.starcatcher21.stargazer.block.clases.star.StarFlower;
import com.github.starcatcher21.stargazer.block.clases.star.celestial.CelestialFlowerBlock;
import com.github.starcatcher21.stargazer.block.clases.star.barrier.StarBarrierBlock;
import com.github.starcatcher21.stargazer.block.clases.star.border.BorderBlock;
import com.github.starcatcher21.stargazer.block.clases.star.cosmic.CosmicBlock;
import com.github.starcatcher21.stargazer.block.clases.star.leaves.StarLeaves;
import com.github.starcatcher21.stargazer.block.clases.star.log.StarLog;
import com.github.starcatcher21.stargazer.block.clases.star.log.StrippedStarLog;
import com.github.starcatcher21.stargazer.effects.StatusEffects;
import com.github.starcatcher21.stargazer.sound.SoundGroups;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Direction;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;
import static com.github.starcatcher21.stargazer.block.ModBlock.registerWoItem;

public class StarBlocks {
    public static final Block COSMIC_BLOCK = register("cosmic_block", CosmicBlock::new, AbstractBlock.Settings.create()
            .nonOpaque()
            .sounds(SoundGroups.STAR)
            .pistonBehavior(PistonBehavior.BLOCK)
    );
    public static final Block STAR_BARRIER_BLOCK = register("star_barrier_block", StarBarrierBlock::new, AbstractBlock.Settings.create()
            .nonOpaque()
            .sounds(SoundGroups.STAR)
            .pistonBehavior(PistonBehavior.BLOCK)
    );
    public static final Block BORDER_BLOCK = register("border_block", BorderBlock::new, AbstractBlock.Settings.create()
            .nonOpaque()
            .solid()
            .pistonBehavior(PistonBehavior.BLOCK)
    );
    public static final Block STAR_LOG = register("star_log", StarLog::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(blockState -> blockState.get(Properties.AXIS).equals(Direction.Axis.Y) ? MapColor.WHITE : MapColor.PURPLE)
    );
    public static final Block STRIPPED_STAR_LOG = register("stripped_star_log", StrippedStarLog::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(DyeColor.BLACK)
    );
    public static final Block STAR_PLANKS = register("star_planks", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.BLACK)
    );
    public static final Block STAR_PLANKS_SLAB = register("star_planks_slab", SlabBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.BLACK)
    );
    public static final Block STAR_PLANKS_STAIRS = register("star_planks_stairs", (AbstractBlock.Settings settings) -> new StairsBlock(STAR_PLANKS.getDefaultState(), (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.BLACK)
    );
    public static final Block STAR_PLANKS_FENCE = register("star_planks_fence", FenceBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.BLACK)
    );
    public static final Block STAR_PLANKS_FENCE_GATE = register("star_planks_fence_gate", (AbstractBlock.Settings settings) -> new FenceGateBlock(WoodType.OAK, (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.BLACK)
    );
    public static final Block STAR_PLANKS_BUTTON = register("star_planks_button", (AbstractBlock.Settings settings) -> new ButtonBlock(BlockSetType.OAK, 30, (AbstractBlock.Settings) settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.BLACK)
    );
    public static final Block STAR_LEAVES = register("star_leaves", StarLeaves::new, AbstractBlock.Settings.create()
            .nonOpaque()
            .sounds(BlockSoundGroup.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block STAR_SAPLING = register("star_sapling", (AbstractBlock.Settings settings) -> new CustomSapling(Helpers.configuredFeatureOf("star_trees"), settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.GRASS)
            .ticksRandomly()
            .breakInstantly()
    );
    public static final Block POTTED_STAR_SAPLING = registerWoItem("potted_star_sapling", settings -> new FlowerPotBlock(STAR_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque());
    public static final Block STAR_FLOWER = register("star_flower", settings -> new StarFlower(StatusEffects.COSMO, 5.0f, settings), AbstractBlock.Settings.create()
            .mapColor(MapColor.YELLOW)
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .offset(AbstractBlock.OffsetType.XZ)
            .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block POTTED_STAR_FLOWER = register("potted_star_flower", settings -> new FlowerPotBlock(STAR_FLOWER, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque());
    public static final Block CELESTIAL_STAR_FLOWER = register("celestial_star_flower", settings -> new CelestialFlowerBlock(StatusEffects.COSMO, 5.0f, settings), AbstractBlock.Settings.create()
            .mapColor(MapColor.YELLOW)
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .offset(AbstractBlock.OffsetType.XZ)
            .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block POTTED_CELESTIAL_STAR_FLOWER = register("potted_celestial_star_flower", settings -> new FlowerPotBlock(CELESTIAL_STAR_FLOWER, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque());
    public static void init() {
    }
}
