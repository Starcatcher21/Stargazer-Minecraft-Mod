// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.starlib.Helpers;
import com.github.starcatcher21.stargazer.block.clases.CosmicFlower;
import com.github.starcatcher21.stargazer.block.clases.CustomLeaves;
import com.github.starcatcher21.stargazer.block.clases.CustomSapling;
import com.github.starcatcher21.stargazer.block.clases.moon.*;
import com.github.starcatcher21.stargazer.block.clases.moon.geode_fruit.GeodeFruit;
import com.github.starcatcher21.stargazer.block.clases.moon.leaves.MoonLeaves;
import com.github.starcatcher21.stargazer.block.clases.moon.log.MoonLog;
import com.github.starcatcher21.stargazer.block.clases.moon.log.StrippedMoonLog;
import com.github.starcatcher21.stargazer.block.clases.moon.star_stone.StarStone;
import com.github.starcatcher21.stargazer.block.clases.moon.star_trap.StarTrap;
import com.github.starcatcher21.stargazer.block.clases.moon.starforge.Starforge;
import com.github.starcatcher21.stargazer.effects.StatusEffects;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.util.CommonColors;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import java.util.HashMap;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;
import static com.github.starcatcher21.stargazer.block.ModBlock.registerWoItem;

public class MoonBlocks {
    public static final HashMap<Item, BlockState> COLORED_PLANKS = new HashMap<>();
    public static final Block MOON_ROCK = register("moon_rock", MoonRock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .strength(0.5f)
            .requiresCorrectToolForDrops()
            .mapColor(MapColor.SNOW)
    );
    public static final Block COMET_BLOCK = register("comet_block", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .strength(0.5f)
            .requiresCorrectToolForDrops()
            .mapColor(MapColor.SNOW)
    );
    public static final Block SUN_ENRICHED_MOON_ROCK = register("sun_enriched_moon_rock", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .strength(1.5f)
            .requiresCorrectToolForDrops()
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block POLISHED_SUN_ENRICHED_MOON_ROCK = register("polished_sun_enriched_moon_rock", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .strength(1.5f)
            .requiresCorrectToolForDrops()
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block PRISMATIC_ORE = register("prismatic_ore", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .strength(1.25f, 7.5f)
            .requiresCorrectToolForDrops()
            .mapColor(MapColor.DIAMOND)
    );
    public static final Block PRISMATIC_SHARD_BLOCK = register("prismatic_shard_block", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .strength(1.25f, 7.5f)
            .requiresCorrectToolForDrops()
            .mapColor(MapColor.DIAMOND)
    );
    public static final Block POLISHED_MOON_ROCK = register("polished_moon_rock", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .strength(0.75f)
            .requiresCorrectToolForDrops()
            .mapColor(MapColor.SNOW)
    );
    public static final Block MOON_FARMLAND = register("moon_farmland", MoonFarmland::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .randomTicks()
            .strength(0.5f)
            .requiresCorrectToolForDrops()
            .mapColor(MapColor.SNOW)
    );
    public static final Block MOON_ROCK_NYLIUM = register("moon_rock_nylium", MoonRockNylium::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.NYLIUM)
            .strength(0.5f)
            .requiresCorrectToolForDrops()
            .randomTicks()
            .strength(1.5F, 6.0F)
            .mapColor(MapColor.ICE)
    );
    public static final Block MOON_ROCK_BRICKS = register("moon_rock_bricks", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(0.75f)
            .mapColor(MapColor.SNOW)
    );
    public static final Block MOON_ROCK_BRICKS_SLAB = register("moon_rock_bricks_slab", SlabBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(0.75F)
            .mapColor(MapColor.SNOW)
    );
    public static final Block MOON_ROCK_BRICKS_STAIRS = register("moon_rock_bricks_stairs", (BlockBehaviour.Properties settings) -> new StairBlock(MOON_ROCK_BRICKS.defaultBlockState(), (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(0.75f)
            .mapColor(MapColor.SNOW)
    );
    public static final Block CRACKED_MOON_ROCK_BRICKS = register("cracked_moon_rock_bricks", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(0.75f)
            .mapColor(MapColor.SNOW)
    );
    public static final Block CHISELED_MOON_ROCK_BRICKS = register("chiseled_moon_rock_bricks", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(0.75f)
            .mapColor(MapColor.SNOW)
    );
    public static final Block BLACK_MOON_ROCK = register("black_moon_rock", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(1f)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block POLISHED_BLACK_MOON_ROCK = register("polished_black_moon_rock", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(1f)
            .mapColor(MapColor.CRIMSON_NYLIUM)
    );
    public static final Block POLISHED_BLACK_MOON_ROCK_PURPLE = register("polished_black_moon_rock_purple", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(1f)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block MOON_ROCK_TILES = register("moon_rock_tiles", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(0.75f)
            .mapColor(MapColor.CRIMSON_NYLIUM)
    );
    public static final Block PURPLE_MOON_ROCK_TILES = register("purple_moon_rock_tiles", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(0.75f)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block STAR_FORGE = register("star_forge", Starforge::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(0.75f)
            .mapColor(MapColor.SNOW)
    );
    public static final Block STRIPPED_MOON_LOG = register("stripped_moon_log", StrippedMoonLog::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.SNOW)
    );
    public static final Block STRIPPED_CURVE_LOG = register("stripped_curve_log", StrippedMoonLog::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.ICE)
    );
    public static final Block MOON_LOG = register("moon_log", (settings) -> new MoonLog(STRIPPED_MOON_LOG, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(blockState -> blockState.getValue(BlockStateProperties.AXIS).equals(Direction.Axis.Y) ? MapColor.SNOW : MapColor.COLOR_PURPLE)
    );
    public static final Block FULL_MOON_LOG = register("full_moon_log", (settings) -> new MoonLog(null, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(blockState -> blockState.getValue(BlockStateProperties.AXIS).equals(Direction.Axis.Y) ? MapColor.SNOW : MapColor.COLOR_PURPLE)
    );
    public static final Block FULL_MOON_LEAVES = register("full_moon_leaves", (settings) -> new CustomLeaves(0xffffff, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .sound(SoundType.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.COLOR_PINK)
    );
    public static final Block FULL_MOON_CORE = register("full_moon_core", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block CURVE_LOG = register("curve_log", (settings) -> new MoonLog(STRIPPED_CURVE_LOG, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(blockState -> blockState.getValue(BlockStateProperties.AXIS).equals(Direction.Axis.Y) ? MapColor.SNOW : MapColor.COLOR_PURPLE)
    );
    public static final Block CURVE_LEAVES = register("curve_leaves", (settings) -> new CustomLeaves(CommonColors.SOFT_RED, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .sound(SoundType.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.COLOR_PINK)
    );
    // MOON_PLANKS
    public static final Block MOON_PLANKS = register("moon_planks", MoonPlanks::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.SNOW)
    );
    public static final Block MOON_PLANKS_DOOR = register("moon_planks_door", (settings) -> new DoorBlock(BlockSetType.OAK, (BlockBehaviour.Properties)  settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.SNOW)
    );
    public static final Block MOON_PLANKS_SLAB = register("moon_planks_slab", SlabBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.SNOW)
    );
    public static final Block MOON_PLANKS_STAIRS = register("moon_planks_stairs", (BlockBehaviour.Properties settings) -> new StairBlock(MOON_PLANKS.defaultBlockState(), (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.SNOW)
    );
    public static final Block MOON_PLANKS_FENCE = register("moon_planks_fence", FenceBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.SNOW)
    );
    public static final Block MOON_PLANKS_FENCE_GATE = register("moon_planks_fence_gate", (BlockBehaviour.Properties settings) -> new FenceGateBlock(WoodType.OAK, (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.SNOW)
    );
    public static final Block MOON_PLANKS_BUTTON = register("moon_planks_button", (BlockBehaviour.Properties settings) -> new ButtonBlock(BlockSetType.OAK, 30, (BlockBehaviour.Properties) settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.SNOW)
    );
    public static final Block RED_MOON_PLANKS = register("red_moon_planks", MoonPlanks::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_RED)
    );
    public static final Block RED_MOON_PLANKS_SLAB = register("red_moon_planks_slab", SlabBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_RED)
    );
    public static final Block RED_MOON_PLANKS_STAIRS = register("red_moon_planks_stairs", (BlockBehaviour.Properties settings) -> new StairBlock(RED_MOON_PLANKS.defaultBlockState(), (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_RED)
    );
    public static final Block RED_MOON_PLANKS_FENCE = register("red_moon_planks_fence", FenceBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_RED)
    );
    public static final Block RED_MOON_PLANKS_FENCE_GATE = register("red_moon_planks_fence_gate", (BlockBehaviour.Properties settings) -> new FenceGateBlock(WoodType.OAK, (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_RED)
    );
    public static final Block RED_MOON_PLANKS_BUTTON = register("red_moon_planks_button", (BlockBehaviour.Properties settings) -> new ButtonBlock(BlockSetType.OAK, 30, (BlockBehaviour.Properties) settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.COLOR_RED)
    );
    public static final Block BLUE_MOON_PLANKS = register("blue_moon_planks", MoonPlanks::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BLUE)
    );
    public static final Block BLUE_MOON_PLANKS_SLAB = register("blue_moon_planks_slab", SlabBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BLUE)
    );
    public static final Block BLUE_MOON_PLANKS_STAIRS = register("blue_moon_planks_stairs", (BlockBehaviour.Properties settings) -> new StairBlock(BLUE_MOON_PLANKS.defaultBlockState(), (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BLUE)
    );
    public static final Block BLUE_MOON_PLANKS_FENCE = register("blue_moon_planks_fence", FenceBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BLUE)
    );
    public static final Block BLUE_MOON_PLANKS_FENCE_GATE = register("blue_moon_planks_fence_gate", (BlockBehaviour.Properties settings) -> new FenceGateBlock(WoodType.OAK, (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BLUE)
    );
    public static final Block BLUE_MOON_PLANKS_BUTTON = register("blue_moon_planks_button", (BlockBehaviour.Properties settings) -> new ButtonBlock(BlockSetType.OAK, 30, (BlockBehaviour.Properties) settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.COLOR_BLUE)
    );
    public static final Block YELLOW_MOON_PLANKS = register("yellow_moon_planks", MoonPlanks::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YELLOW_MOON_PLANKS_SLAB = register("yellow_moon_planks_slab", SlabBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YELLOW_MOON_PLANKS_STAIRS = register("yellow_moon_planks_stairs", (BlockBehaviour.Properties settings) -> new StairBlock(YELLOW_MOON_PLANKS.defaultBlockState(), (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YELLOW_MOON_PLANKS_FENCE = register("yellow_moon_planks_fence", FenceBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YELLOW_MOON_PLANKS_FENCE_GATE = register("yellow_moon_planks_fence_gate", (BlockBehaviour.Properties settings) -> new FenceGateBlock(WoodType.OAK, (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YELLOW_MOON_PLANKS_BUTTON = register("yellow_moon_planks_button", (BlockBehaviour.Properties settings) -> new ButtonBlock(BlockSetType.OAK, 30, (BlockBehaviour.Properties) settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block PURPLE_MOON_PLANKS = register("purple_moon_planks", MoonPlanks::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.ICE)
    );
    public static final Block PURPLE_MOON_PLANKS_SLAB = register("purple_moon_planks_slab", SlabBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.ICE)
    );
    public static final Block PURPLE_MOON_PLANKS_STAIRS = register("purple_moon_planks_stairs", (BlockBehaviour.Properties settings) -> new StairBlock(PURPLE_MOON_PLANKS.defaultBlockState(), (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.ICE)
    );
    public static final Block PURPLE_MOON_PLANKS_FENCE = register("purple_moon_planks_fence", FenceBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.ICE)
    );
    public static final Block PURPLE_MOON_PLANKS_FENCE_GATE = register("purple_moon_planks_fence_gate", (BlockBehaviour.Properties settings) -> new FenceGateBlock(WoodType.OAK, (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.ICE)
    );
    public static final Block PURPLE_MOON_PLANKS_BUTTON = register("purple_moon_planks_button", (BlockBehaviour.Properties settings) -> new ButtonBlock(BlockSetType.OAK, 30, (BlockBehaviour.Properties) settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.ICE)
    );
    public static final Block MOON_LEAVES = register("moon_leaves", (settings) -> new MoonLeaves(CommonColors.DARK_PURPLE, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .randomTicks()
            .sound(SoundType.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block STAR_STONE = register("star_stone", StarStone::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.GLASS)
            .lightLevel(StarStone::getLuminance)
            .strength(1.0F)
            .mapColor(MapColor.FIRE)
    );
    public static final Block MOON_SAPLING = register("moon_sapling", (BlockBehaviour.Properties settings) -> new CustomSapling(Helpers.configuredFeatureOf("moon_trees"), settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.GRASS)
            .randomTicks()
            .instabreak()
    );
    public static final Block POTTED_MOON_SAPLING = registerWoItem("potted_moon_sapling", settings -> new FlowerPotBlock(MOON_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());

    public static final Block FULL_MOON_SAPLING = register("full_moon_sapling", (BlockBehaviour.Properties settings) -> new CustomSapling(Helpers.configuredFeatureOf("full_moon_trees"), settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.GRASS)
            .randomTicks()
            .instabreak()
    );
    public static final Block POTTED_FULL_MOON_SAPLING = registerWoItem("potted_full_moon_sapling", settings -> new FlowerPotBlock(FULL_MOON_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());
    public static final Block CURVE_SAPLING = register("curve_sapling", (BlockBehaviour.Properties settings) -> new CustomSapling(Helpers.configuredFeatureOf("curve_trees"), settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.GRASS)
            .randomTicks()
            .instabreak()
    );
    public static final Block POTTED_CURVE_SAPLING = registerWoItem("potted_curve_sapling", settings -> new FlowerPotBlock(CURVE_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());
    public static final Block MOON_GRASS = register("moon_grass", MoonGrass::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE)
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
    );
    public static final Block MOON_FERN = register("moon_fern", MoonFern::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE)
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
    );
    public static final Block TALL_MOON_GRASS = register("tall_moon_grass", TallMoonGrass::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE)
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
    );
    // CURVE PLANKS
    public static final Block CURVE_PLANKS = register("curve_planks", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block CURVE_PLANKS_DOOR = register("curve_planks_door", (settings) -> new DoorBlock(BlockSetType.OAK, (BlockBehaviour.Properties)  settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block CURVE_PLANKS_SLAB = register("curve_planks_slab", SlabBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block CURVE_PLANKS_STAIRS = register("curve_planks_stairs", (BlockBehaviour.Properties settings) -> new StairBlock(CURVE_PLANKS.defaultBlockState(), (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block CURVE_PLANKS_FENCE = register("curve_planks_fence", FenceBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block CURVE_PLANKS_FENCE_GATE = register("curve_planks_fence_gate", (BlockBehaviour.Properties settings) -> new FenceGateBlock(WoodType.OAK, (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block CURVE_PLANKS_BUTTON = register("curve_planks_button", (BlockBehaviour.Properties settings) -> new ButtonBlock(BlockSetType.OAK, 30, (BlockBehaviour.Properties) settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block PURPLE_MUSHROOM_BLOCK = register("purple_mushroom_block", HugeMushroomBlock::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_RED)
            .instrument(NoteBlockInstrument.BASS)
            .strength(0.2f)
            .sound(SoundType.WOOD)
            .ignitedByLava()
    );
    public static final Block PURPLE_MUSHROOM = register("purple_mushroom", (BlockBehaviour.Properties settings) -> new MushroomBlock(Helpers.configuredFeatureOf("purple_shroom"), settings), BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE)
            .strength(0.2f)
            .sound(SoundType.PINK_PETALS)
            .ignitedByLava()
            .randomTicks()
            .noCollision()
    );
    public static final Block POTTED_PURPLE_MUSHROOM = registerWoItem("potted_purple_mushroom", settings -> new FlowerPotBlock(PURPLE_MUSHROOM, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());
    public static final Block GEODE_FRUIT = registerWoItem("geode_fruit_block", GeodeFruit::new, BlockBehaviour.Properties.of()
            .strength(1.0f)
            .noCollision()
            .randomTicks()
            .sound(SoundType.STONE)
    );
    public static final Block STAR_TRAP = register("star_trap", StarTrap::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE)
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .pushReaction(PushReaction.DESTROY)
    );

    public static final Block FORGET_ME_NOW = register("forget_me_now", ForgetMeNow::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.NETHER)
            .noCollision()
            .sound(SoundType.PINK_PETALS)
            .pushReaction(PushReaction.DESTROY)
    );
    public static final Block POTTED_FORGET_ME_NOW = registerWoItem("potted_forget_me_now", (BlockBehaviour.Properties settings) -> new FlowerPotBlock(FORGET_ME_NOW, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());

    public static final Block SPRUNGUS = register("sprungus", settings -> new CosmicFlower(StatusEffects.COSMO, 5.0f, settings), BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_CYAN)
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
    );
    public static final Block POTTED_SPRUNGUS = registerWoItem("potted_sprungus", settings -> new FlowerPotBlock(SPRUNGUS, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());
    public static void init() {
        COLORED_PLANKS.put(ModItems.RED_STAR, MoonBlocks.RED_MOON_PLANKS.defaultBlockState());
        COLORED_PLANKS.put(ModItems.BLUE_STAR, MoonBlocks.BLUE_MOON_PLANKS.defaultBlockState());
        COLORED_PLANKS.put(ModItems.YELLOW_STAR, MoonBlocks.YELLOW_MOON_PLANKS.defaultBlockState());
        COLORED_PLANKS.put(ModItems.PURPLE_STAR, MoonBlocks.PURPLE_MOON_PLANKS.defaultBlockState());
    }
}
