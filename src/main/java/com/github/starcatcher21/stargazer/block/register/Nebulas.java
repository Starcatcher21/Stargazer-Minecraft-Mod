// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.clases.CosmicFlower;
import com.github.starcatcher21.stargazer.block.clases.nebulas.NebulaCore;
import com.github.starcatcher21.stargazer.block.clases.nebulas.NebulaLeaves;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.CommonColors;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;
import static com.github.starcatcher21.stargazer.block.ModBlock.registerWoItem;

public class Nebulas {
    public static final Block RED_TENTACLE_FLOWER = register("red_tentacle_flower", settings -> new CosmicFlower(MobEffects.INSTANT_DAMAGE, 5.0f, settings), BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_RED)
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
    );
    public static final Block POTTED_RED_TENTACLE_FLOWER = registerWoItem("potted_red_tentacle_flower", settings -> new FlowerPotBlock(RED_TENTACLE_FLOWER, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());
    public static final Block BLUE_TENTACLE_FLOWER = register("blue_tentacle_flower", settings -> new CosmicFlower(MobEffects.INSTANT_DAMAGE, 5.0f, settings), BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_CYAN)
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
    );
    public static final Block POTTED_BLUE_TENTACLE_FLOWER = registerWoItem("potted_blue_tentacle_flower", settings -> new FlowerPotBlock(BLUE_TENTACLE_FLOWER, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());
    public static final Block PURPLE_TENTACLE_FLOWER = register("purple_tentacle_flower", settings -> new CosmicFlower(MobEffects.INSTANT_DAMAGE, 5.0f, settings), BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE)
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
    );
    public static final Block POTTED_PURPLE_TENTACLE_FLOWER = registerWoItem("potted_purple_tentacle_flower", settings -> new FlowerPotBlock(PURPLE_TENTACLE_FLOWER, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());
    public static final Block YELLOW_TENTACLE_FLOWER = register("yellow_tentacle_flower", settings -> new CosmicFlower(MobEffects.INSTANT_DAMAGE, 5.0f, settings), BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_YELLOW)
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
    );
    public static final Block POTTED_YELLOW_TENTACLE_FLOWER = registerWoItem("potted_yellow_tentacle_flower", settings -> new FlowerPotBlock(YELLOW_TENTACLE_FLOWER, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());

    public static final Block BLUE_NEBULA_LOG = register("blue_nebula_log", RotatedPillarBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_CYAN)
    );
    public static final Block BLUE_NEBULA_PLANKS = register("blue_nebula_planks", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_CYAN)
    );
    public static final Block BLUE_NEBULA_PLANKS_SLAB = register("blue_nebula_planks_slab", SlabBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_CYAN)
    );
    public static final Block BLUE_NEBULA_PLANKS_STAIRS = register("blue_nebula_planks_stairs", (BlockBehaviour.Properties settings) -> new StairBlock(BLUE_NEBULA_PLANKS.defaultBlockState(), (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_CYAN)
    );
    public static final Block BLUE_NEBULA_PLANKS_FENCE = register("blue_nebula_planks_fence", FenceBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_CYAN)
    );
    public static final Block BLUE_NEBULA_PLANKS_FENCE_GATE = register("blue_nebula_planks_fence_gate", (BlockBehaviour.Properties settings) -> new FenceGateBlock(WoodType.OAK, (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_CYAN)
    );
    public static final Block BLUE_NEBULA_PLANKS_BUTTON = register("blue_nebula_planks_button", (BlockBehaviour.Properties settings) -> new ButtonBlock(BlockSetType.OAK, 30, (BlockBehaviour.Properties) settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.COLOR_CYAN)
    );

    public static final Block BLUE_NEBULA_REGROW_CORE = register("blue_nebula_regrow_core", settings -> new NebulaCore(ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "blue_nebula_trees")), BLUE_TENTACLE_FLOWER, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .randomTicks()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_CYAN)
    );

    public static final Block BLUE_NEBULA_LEAVES = register("blue_nebula_leaves", (settings) -> new NebulaLeaves(CommonColors.HIGH_CONTRAST_DIAMOND, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .randomTicks()
            .sound(SoundType.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.COLOR_CYAN)
    );

    public static final Block RED_NEBULA_LOG = register("red_nebula_log", RotatedPillarBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_RED)
    );
    public static final Block RED_NEBULA_PLANKS = register("red_nebula_planks", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_RED)
    );
    public static final Block RED_NEBULA_PLANKS_SLAB = register("red_nebula_planks_slab", SlabBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_RED)
    );
    public static final Block RED_NEBULA_PLANKS_STAIRS = register("red_nebula_planks_stairs", (BlockBehaviour.Properties settings) -> new StairBlock(RED_NEBULA_PLANKS.defaultBlockState(), (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_RED)
    );
    public static final Block RED_NEBULA_PLANKS_FENCE = register("red_nebula_planks_fence", FenceBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_RED)
    );
    public static final Block RED_NEBULA_PLANKS_FENCE_GATE = register("red_nebula_planks_fence_gate", (BlockBehaviour.Properties settings) -> new FenceGateBlock(WoodType.OAK, (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_RED)
    );
    public static final Block RED_NEBULA_PLANKS_BUTTON = register("red_nebula_planks_button", (BlockBehaviour.Properties settings) -> new ButtonBlock(BlockSetType.OAK, 30, (BlockBehaviour.Properties) settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.COLOR_RED)
    );

    public static final Block RED_NEBULA_LEAVES = register("red_nebula_leaves", (settings) -> new NebulaLeaves(CommonColors.RED, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .randomTicks()
            .sound(SoundType.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.COLOR_RED)
    );

    public static final Block RED_NEBULA_REGROW_CORE = register("red_nebula_regrow_core", settings -> new NebulaCore(ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "red_nebula_trees")), RED_TENTACLE_FLOWER, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .randomTicks()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_RED)
    );

    public static final Block PURPLE_NEBULA_LOG = register("purple_nebula_log", RotatedPillarBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block PURPLE_NEBULA_PLANKS = register("purple_nebula_planks", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block PURPLE_NEBULA_PLANKS_SLAB = register("purple_nebula_planks_slab", SlabBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block PURPLE_NEBULA_PLANKS_STAIRS = register("purple_nebula_planks_stairs", (BlockBehaviour.Properties settings) -> new StairBlock(PURPLE_NEBULA_PLANKS.defaultBlockState(), (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block PURPLE_NEBULA_PLANKS_FENCE = register("purple_nebula_planks_fence", FenceBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block PURPLE_NEBULA_PLANKS_FENCE_GATE = register("purple_nebula_planks_fence_gate", (BlockBehaviour.Properties settings) -> new FenceGateBlock(WoodType.OAK, (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block PURPLE_NEBULA_PLANKS_BUTTON = register("purple_nebula_planks_button", (BlockBehaviour.Properties settings) -> new ButtonBlock(BlockSetType.OAK, 30, (BlockBehaviour.Properties) settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.COLOR_PURPLE)
    );

    public static final Block PURPLE_NEBULA_REGROW_CORE = register("purple_nebula_regrow_core", settings -> new NebulaCore(ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "purple_nebula_trees")), PURPLE_TENTACLE_FLOWER, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .randomTicks()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_PURPLE)
    );

    public static final Block PURPLE_NEBULA_LEAVES = register("purple_nebula_leaves", (settings) -> new NebulaLeaves(CommonColors.DARK_PURPLE, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .randomTicks()
            .sound(SoundType.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.COLOR_PURPLE)
    );

    public static final Block YELLOW_NEBULA_LOG = register("yellow_nebula_log", RotatedPillarBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YELLOW_NEBULA_PLANKS = register("yellow_nebula_planks", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YELLOW_NEBULA_PLANKS_SLAB = register("yellow_nebula_planks_slab", SlabBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YELLOW_NEBULA_PLANKS_STAIRS = register("yellow_nebula_planks_stairs", (BlockBehaviour.Properties settings) -> new StairBlock(YELLOW_NEBULA_PLANKS.defaultBlockState(), (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YELLOW_NEBULA_PLANKS_FENCE = register("yellow_nebula_planks_fence", FenceBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YELLOW_NEBULA_PLANKS_FENCE_GATE = register("yellow_nebula_planks_fence_gate", (BlockBehaviour.Properties settings) -> new FenceGateBlock(WoodType.OAK, (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YELLOW_NEBULA_PLANKS_BUTTON = register("yellow_nebula_planks_button", (BlockBehaviour.Properties settings) -> new ButtonBlock(BlockSetType.OAK, 30, (BlockBehaviour.Properties) settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );

    public static final Block YELLOW_NEBULA_LEAVES = register("yellow_nebula_leaves", (settings) -> new NebulaLeaves(CommonColors.YELLOW, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .randomTicks()
            .sound(SoundType.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.COLOR_YELLOW)
    );

    public static final Block YELLOW_NEBULA_REGROW_CORE = register("yellow_nebula_regrow_core", settings -> new NebulaCore(ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "yellow_nebula_trees")), YELLOW_TENTACLE_FLOWER, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .randomTicks()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );

    public static void init() {};
}
