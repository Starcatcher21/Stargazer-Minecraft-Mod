package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.clases.CosmicFlower;
import com.github.starcatcher21.stargazer.block.clases.nebulas.NebulaCore;
import com.github.starcatcher21.stargazer.block.clases.nebulas.NebulaLeaves;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;
import static com.github.starcatcher21.stargazer.block.ModBlock.registerWoItem;

public class Nebulas {
    public static final Block RED_TENTACLE_FLOWER = register("red_tentacle_flower", settings -> new CosmicFlower(StatusEffects.INSTANT_DAMAGE, 5.0f, settings), AbstractBlock.Settings.create()
            .mapColor(MapColor.RED)
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .offset(AbstractBlock.OffsetType.XZ)
            .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block POTTED_RED_TENTACLE_FLOWER = registerWoItem("potted_red_tentacle_flower", settings -> new FlowerPotBlock(RED_TENTACLE_FLOWER, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque());
    public static final Block BLUE_TENTACLE_FLOWER = register("blue_tentacle_flower", settings -> new CosmicFlower(StatusEffects.INSTANT_DAMAGE, 5.0f, settings), AbstractBlock.Settings.create()
            .mapColor(MapColor.CYAN)
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .offset(AbstractBlock.OffsetType.XZ)
            .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block POTTED_BLUE_TENTACLE_FLOWER = registerWoItem("potted_blue_tentacle_flower", settings -> new FlowerPotBlock(BLUE_TENTACLE_FLOWER, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque());
    public static final Block PURPLE_TENTACLE_FLOWER = register("purple_tentacle_flower", settings -> new CosmicFlower(StatusEffects.INSTANT_DAMAGE, 5.0f, settings), AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE)
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .offset(AbstractBlock.OffsetType.XZ)
            .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block POTTED_PURPLE_TENTACLE_FLOWER = registerWoItem("potted_purple_tentacle_flower", settings -> new FlowerPotBlock(PURPLE_TENTACLE_FLOWER, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque());
    public static final Block YELLOW_TENTACLE_FLOWER = register("yellow_tentacle_flower", settings -> new CosmicFlower(StatusEffects.INSTANT_DAMAGE, 5.0f, settings), AbstractBlock.Settings.create()
            .mapColor(MapColor.YELLOW)
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .offset(AbstractBlock.OffsetType.XZ)
            .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block POTTED_YELLOW_TENTACLE_FLOWER = registerWoItem("potted_yellow_tentacle_flower", settings -> new FlowerPotBlock(YELLOW_TENTACLE_FLOWER, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque());

    public static final Block BLUE_NEBULA_LOG = register("blue_nebula_log", PillarBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.CYAN)
    );
    public static final Block BLUE_NEBULA_PLANKS = register("blue_nebula_planks", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.CYAN)
    );
    public static final Block BLUE_NEBULA_PLANKS_SLAB = register("blue_nebula_planks_slab", SlabBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.CYAN)
    );
    public static final Block BLUE_NEBULA_PLANKS_STAIRS = register("blue_nebula_planks_stairs", (AbstractBlock.Settings settings) -> new StairsBlock(BLUE_NEBULA_PLANKS.getDefaultState(), (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.CYAN)
    );
    public static final Block BLUE_NEBULA_PLANKS_FENCE = register("blue_nebula_planks_fence", FenceBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.CYAN)
    );
    public static final Block BLUE_NEBULA_PLANKS_FENCE_GATE = register("blue_nebula_planks_fence_gate", (AbstractBlock.Settings settings) -> new FenceGateBlock(WoodType.OAK, (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.CYAN)
    );
    public static final Block BLUE_NEBULA_PLANKS_BUTTON = register("blue_nebula_planks_button", (AbstractBlock.Settings settings) -> new ButtonBlock(BlockSetType.OAK, 30, (AbstractBlock.Settings) settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.CYAN)
    );

    public static final Block BLUE_NEBULA_REGROW_CORE = register("blue_nebula_regrow_core", settings -> new NebulaCore(RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Stargazer.MOD_ID, "blue_nebula_trees")), BLUE_TENTACLE_FLOWER, settings), AbstractBlock.Settings.create()
            .solid()
            .ticksRandomly()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.CYAN)
    );

    public static final Block BLUE_NEBULA_LEAVES = register("blue_nebula_leaves", (settings) -> new NebulaLeaves(Colors.CYAN, settings), AbstractBlock.Settings.create()
            .solid()
            .ticksRandomly()
            .sounds(BlockSoundGroup.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.CYAN)
    );

    public static final Block RED_NEBULA_LOG = register("red_nebula_log", PillarBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.RED)
    );
    public static final Block RED_NEBULA_PLANKS = register("red_nebula_planks", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.RED)
    );
    public static final Block RED_NEBULA_PLANKS_SLAB = register("red_nebula_planks_slab", SlabBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.RED)
    );
    public static final Block RED_NEBULA_PLANKS_STAIRS = register("red_nebula_planks_stairs", (AbstractBlock.Settings settings) -> new StairsBlock(RED_NEBULA_PLANKS.getDefaultState(), (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.RED)
    );
    public static final Block RED_NEBULA_PLANKS_FENCE = register("red_nebula_planks_fence", FenceBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.RED)
    );
    public static final Block RED_NEBULA_PLANKS_FENCE_GATE = register("red_nebula_planks_fence_gate", (AbstractBlock.Settings settings) -> new FenceGateBlock(WoodType.OAK, (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.RED)
    );
    public static final Block RED_NEBULA_PLANKS_BUTTON = register("red_nebula_planks_button", (AbstractBlock.Settings settings) -> new ButtonBlock(BlockSetType.OAK, 30, (AbstractBlock.Settings) settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.RED)
    );

    public static final Block RED_NEBULA_LEAVES = register("red_nebula_leaves", (settings) -> new NebulaLeaves(Colors.RED, settings), AbstractBlock.Settings.create()
            .solid()
            .ticksRandomly()
            .sounds(BlockSoundGroup.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.RED)
    );

    public static final Block RED_NEBULA_REGROW_CORE = register("red_nebula_regrow_core", settings -> new NebulaCore(RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Stargazer.MOD_ID, "red_nebula_trees")), RED_TENTACLE_FLOWER, settings), AbstractBlock.Settings.create()
            .solid()
            .ticksRandomly()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.RED)
    );

    public static final Block PURPLE_NEBULA_LOG = register("purple_nebula_log", PillarBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.PURPLE)
    );
    public static final Block PURPLE_NEBULA_PLANKS = register("purple_nebula_planks", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.PURPLE)
    );
    public static final Block PURPLE_NEBULA_PLANKS_SLAB = register("purple_nebula_planks_slab", SlabBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.PURPLE)
    );
    public static final Block PURPLE_NEBULA_PLANKS_STAIRS = register("purple_nebula_planks_stairs", (AbstractBlock.Settings settings) -> new StairsBlock(PURPLE_NEBULA_PLANKS.getDefaultState(), (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.PURPLE)
    );
    public static final Block PURPLE_NEBULA_PLANKS_FENCE = register("purple_nebula_planks_fence", FenceBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.PURPLE)
    );
    public static final Block PURPLE_NEBULA_PLANKS_FENCE_GATE = register("purple_nebula_planks_fence_gate", (AbstractBlock.Settings settings) -> new FenceGateBlock(WoodType.OAK, (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.PURPLE)
    );
    public static final Block PURPLE_NEBULA_PLANKS_BUTTON = register("purple_nebula_planks_button", (AbstractBlock.Settings settings) -> new ButtonBlock(BlockSetType.OAK, 30, (AbstractBlock.Settings) settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.PURPLE)
    );

    public static final Block PURPLE_NEBULA_REGROW_CORE = register("purple_nebula_regrow_core", settings -> new NebulaCore(RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Stargazer.MOD_ID, "purple_nebula_trees")), PURPLE_TENTACLE_FLOWER, settings), AbstractBlock.Settings.create()
            .solid()
            .ticksRandomly()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.PURPLE)
    );

    public static final Block PURPLE_NEBULA_LEAVES = register("purple_nebula_leaves", (settings) -> new NebulaLeaves(Colors.PURPLE, settings), AbstractBlock.Settings.create()
            .solid()
            .ticksRandomly()
            .sounds(BlockSoundGroup.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.PURPLE)
    );

    public static final Block YELLOW_NEBULA_LOG = register("yellow_nebula_log", PillarBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YELLOW_NEBULA_PLANKS = register("yellow_nebula_planks", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YELLOW_NEBULA_PLANKS_SLAB = register("yellow_nebula_planks_slab", SlabBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YELLOW_NEBULA_PLANKS_STAIRS = register("yellow_nebula_planks_stairs", (AbstractBlock.Settings settings) -> new StairsBlock(YELLOW_NEBULA_PLANKS.getDefaultState(), (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YELLOW_NEBULA_PLANKS_FENCE = register("yellow_nebula_planks_fence", FenceBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YELLOW_NEBULA_PLANKS_FENCE_GATE = register("yellow_nebula_planks_fence_gate", (AbstractBlock.Settings settings) -> new FenceGateBlock(WoodType.OAK, (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YELLOW_NEBULA_PLANKS_BUTTON = register("yellow_nebula_planks_button", (AbstractBlock.Settings settings) -> new ButtonBlock(BlockSetType.OAK, 30, (AbstractBlock.Settings) settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.YELLOW)
    );

    public static final Block YELLOW_NEBULA_LEAVES = register("yellow_nebula_leaves", (settings) -> new NebulaLeaves(Colors.YELLOW, settings), AbstractBlock.Settings.create()
            .solid()
            .ticksRandomly()
            .sounds(BlockSoundGroup.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.YELLOW)
    );

    public static final Block YELLOW_NEBULA_REGROW_CORE = register("yellow_nebula_regrow_core", settings -> new NebulaCore(RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Stargazer.MOD_ID, "yellow_nebula_trees")), YELLOW_TENTACLE_FLOWER, settings), AbstractBlock.Settings.create()
            .solid()
            .ticksRandomly()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.YELLOW)
    );

    public static void init() {};
}
