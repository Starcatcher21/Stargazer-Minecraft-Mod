package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.Helpers;
import com.github.starcatcher21.stargazer.block.clases.CustomLeaves;
import com.github.starcatcher21.stargazer.block.clases.moon.*;
import com.github.starcatcher21.stargazer.block.clases.moon.geode_fruit.GeodeFruit;
import com.github.starcatcher21.stargazer.block.clases.moon.leaves.MoonLeaves;
import com.github.starcatcher21.stargazer.block.clases.moon.log.MoonLog;
import com.github.starcatcher21.stargazer.block.clases.moon.log.StrippedMoonLog;
import com.github.starcatcher21.stargazer.block.clases.moon.star_stone.StarStone;
import com.github.starcatcher21.stargazer.block.clases.moon.star_trap.StarTrap;
import com.github.starcatcher21.stargazer.block.clases.moon.starforge.Starforge;
import com.github.starcatcher21.stargazer.block.clases.CustomSapling;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Colors;
import net.minecraft.util.math.Direction;

import java.util.HashMap;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;
import static com.github.starcatcher21.stargazer.block.ModBlock.registerWoItem;

public class MoonBlocks {
    public static final HashMap<Item, BlockState> COLORED_PLANKS = new HashMap<>();
    public static final Block MOON_ROCK = register("moon_rock", MoonRock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .strength(0.5f)
            .requiresTool()
            .mapColor(MapColor.WHITE)
    );
    public static final Block PRISMATIC_ORE = register("prismatic_ore", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .strength(1.25f, 7.5f)
            .requiresTool()
            .mapColor(MapColor.DIAMOND_BLUE)
    );
    public static final Block POLISHED_MOON_ROCK = register("polished_moon_rock", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .strength(0.75f)
            .requiresTool()
            .mapColor(MapColor.WHITE)
    );
    public static final Block MOON_FARMLAND = register("moon_farmland", MoonFarmland::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .ticksRandomly()
            .strength(0.5f)
            .requiresTool()
            .mapColor(MapColor.WHITE)
    );
    public static final Block MOON_ROCK_NYLIUM = register("moon_rock_nylium", MoonRock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.NYLIUM)
            .strength(0.5f)
            .requiresTool()
            .strength(1.5F, 6.0F)
            .mapColor(MapColor.PALE_PURPLE)
    );
    public static final Block MOON_ROCK_BRICKS = register("moon_rock_bricks", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .requiresTool()
            .strength(0.75f)
            .mapColor(MapColor.WHITE)
    );
    public static final Block MOON_ROCK_BRICKS_SLAB = register("moon_rock_bricks_slab", SlabBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .requiresTool()
            .strength(0.75F)
            .mapColor(MapColor.WHITE)
    );
    public static final Block MOON_ROCK_BRICKS_STAIRS = register("moon_rock_bricks_stairs", (AbstractBlock.Settings settings) -> new StairsBlock(MOON_ROCK_BRICKS.getDefaultState(), (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .requiresTool()
            .strength(0.75f)
            .mapColor(MapColor.WHITE)
    );
    public static final Block CRACKED_MOON_ROCK_BRICKS = register("cracked_moon_rock_bricks", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .requiresTool()
            .strength(0.75f)
            .mapColor(MapColor.WHITE)
    );
    public static final Block CHISELED_MOON_ROCK_BRICKS = register("chiseled_moon_rock_bricks", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .requiresTool()
            .strength(0.75f)
            .mapColor(MapColor.WHITE)
    );
    public static final Block BLACK_MOON_ROCK = register("black_moon_rock", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .requiresTool()
            .strength(1f)
            .mapColor(MapColor.PURPLE)
    );
    public static final Block POLISHED_BLACK_MOON_ROCK = register("polished_black_moon_rock", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .requiresTool()
            .strength(1f)
            .mapColor(MapColor.DULL_RED)
    );
    public static final Block POLISHED_BLACK_MOON_ROCK_PURPLE = register("polished_black_moon_rock_purple", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .requiresTool()
            .strength(1f)
            .mapColor(MapColor.PURPLE)
    );
    public static final Block MOON_ROCK_TILES = register("moon_rock_tiles", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .requiresTool()
            .strength(0.75f)
            .mapColor(MapColor.DULL_RED)
    );
    public static final Block PURPLE_MOON_ROCK_TILES = register("purple_moon_rock_tiles", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .requiresTool()
            .strength(0.75f)
            .mapColor(MapColor.PURPLE)
    );
    public static final Block STAR_FORGE = register("star_forge", Starforge::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .requiresTool()
            .strength(0.75f)
            .mapColor(MapColor.WHITE)
    );
    public static final Block STRIPPED_MOON_LOG = register("stripped_moon_log", StrippedMoonLog::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.WHITE)
    );
    public static final Block STRIPPED_CURVE_LOG = register("stripped_curve_log", StrippedMoonLog::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.PALE_PURPLE)
    );
    public static final Block MOON_LOG = register("moon_log", (settings) -> new MoonLog(STRIPPED_MOON_LOG, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(blockState -> blockState.get(Properties.AXIS).equals(Direction.Axis.Y) ? MapColor.WHITE : MapColor.PURPLE)
    );
    public static final Block CURVE_LOG = register("curve_log", (settings) -> new MoonLog(STRIPPED_CURVE_LOG, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(blockState -> blockState.get(Properties.AXIS).equals(Direction.Axis.Y) ? MapColor.WHITE : MapColor.PURPLE)
    );
    public static final Block CURVE_LEAVES = register("curve_leaves", (settings) -> new CustomLeaves(Colors.LIGHT_RED, settings), AbstractBlock.Settings.create()
            .solid()
            .nonOpaque()
            .sounds(BlockSoundGroup.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.PINK)
    );
    // MOON_PLANKS
    public static final Block MOON_PLANKS = register("moon_planks", MoonPlanks::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.WHITE)
    );
    public static final Block MOON_PLANKS_DOOR = register("moon_planks_door", (settings) -> new DoorBlock(BlockSetType.OAK, (AbstractBlock.Settings)  settings), AbstractBlock.Settings.create()
            .solid()
            .nonOpaque()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.WHITE)
    );
    public static final Block MOON_PLANKS_SLAB = register("moon_planks_slab", SlabBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.WHITE)
    );
    public static final Block MOON_PLANKS_STAIRS = register("moon_planks_stairs", (AbstractBlock.Settings settings) -> new StairsBlock(MOON_PLANKS.getDefaultState(), (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.WHITE)
    );
    public static final Block MOON_PLANKS_FENCE = register("moon_planks_fence", FenceBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.WHITE)
    );
    public static final Block MOON_PLANKS_FENCE_GATE = register("moon_planks_fence_gate", (AbstractBlock.Settings settings) -> new FenceGateBlock(WoodType.OAK, (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.WHITE)
    );
    public static final Block MOON_PLANKS_BUTTON = register("moon_planks_button", (AbstractBlock.Settings settings) -> new ButtonBlock(BlockSetType.OAK, 30, (AbstractBlock.Settings) settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.WHITE)
    );
    public static final Block RED_MOON_PLANKS = register("red_moon_planks", MoonPlanks::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.RED)
    );
    public static final Block RED_MOON_PLANKS_SLAB = register("red_moon_planks_slab", SlabBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.RED)
    );
    public static final Block RED_MOON_PLANKS_STAIRS = register("red_moon_planks_stairs", (AbstractBlock.Settings settings) -> new StairsBlock(RED_MOON_PLANKS.getDefaultState(), (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.RED)
    );
    public static final Block RED_MOON_PLANKS_FENCE = register("red_moon_planks_fence", FenceBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.RED)
    );
    public static final Block RED_MOON_PLANKS_FENCE_GATE = register("red_moon_planks_fence_gate", (AbstractBlock.Settings settings) -> new FenceGateBlock(WoodType.OAK, (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.RED)
    );
    public static final Block RED_MOON_PLANKS_BUTTON = register("red_moon_planks_button", (AbstractBlock.Settings settings) -> new ButtonBlock(BlockSetType.OAK, 30, (AbstractBlock.Settings) settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.RED)
    );
    public static final Block BLUE_MOON_PLANKS = register("blue_moon_planks", MoonPlanks::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.BLUE)
    );
    public static final Block BLUE_MOON_PLANKS_SLAB = register("blue_moon_planks_slab", SlabBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.BLUE)
    );
    public static final Block BLUE_MOON_PLANKS_STAIRS = register("blue_moon_planks_stairs", (AbstractBlock.Settings settings) -> new StairsBlock(BLUE_MOON_PLANKS.getDefaultState(), (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.BLUE)
    );
    public static final Block BLUE_MOON_PLANKS_FENCE = register("blue_moon_planks_fence", FenceBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.BLUE)
    );
    public static final Block BLUE_MOON_PLANKS_FENCE_GATE = register("blue_moon_planks_fence_gate", (AbstractBlock.Settings settings) -> new FenceGateBlock(WoodType.OAK, (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.BLUE)
    );
    public static final Block BLUE_MOON_PLANKS_BUTTON = register("blue_moon_planks_button", (AbstractBlock.Settings settings) -> new ButtonBlock(BlockSetType.OAK, 30, (AbstractBlock.Settings) settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.BLUE)
    );
    public static final Block YELLOW_MOON_PLANKS = register("yellow_moon_planks", MoonPlanks::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YELLOW_MOON_PLANKS_SLAB = register("yellow_moon_planks_slab", SlabBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YELLOW_MOON_PLANKS_STAIRS = register("yellow_moon_planks_stairs", (AbstractBlock.Settings settings) -> new StairsBlock(YELLOW_MOON_PLANKS.getDefaultState(), (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YELLOW_MOON_PLANKS_FENCE = register("yellow_moon_planks_fence", FenceBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YELLOW_MOON_PLANKS_FENCE_GATE = register("yellow_moon_planks_fence_gate", (AbstractBlock.Settings settings) -> new FenceGateBlock(WoodType.OAK, (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YELLOW_MOON_PLANKS_BUTTON = register("yellow_moon_planks_button", (AbstractBlock.Settings settings) -> new ButtonBlock(BlockSetType.OAK, 30, (AbstractBlock.Settings) settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block PURPLE_MOON_PLANKS = register("purple_moon_planks", MoonPlanks::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.PALE_PURPLE)
    );
    public static final Block PURPLE_MOON_PLANKS_SLAB = register("purple_moon_planks_slab", SlabBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.PALE_PURPLE)
    );
    public static final Block PURPLE_MOON_PLANKS_STAIRS = register("purple_moon_planks_stairs", (AbstractBlock.Settings settings) -> new StairsBlock(PURPLE_MOON_PLANKS.getDefaultState(), (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.PALE_PURPLE)
    );
    public static final Block PURPLE_MOON_PLANKS_FENCE = register("purple_moon_planks_fence", FenceBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.PALE_PURPLE)
    );
    public static final Block PURPLE_MOON_PLANKS_FENCE_GATE = register("purple_moon_planks_fence_gate", (AbstractBlock.Settings settings) -> new FenceGateBlock(WoodType.OAK, (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.PALE_PURPLE)
    );
    public static final Block PURPLE_MOON_PLANKS_BUTTON = register("purple_moon_planks_button", (AbstractBlock.Settings settings) -> new ButtonBlock(BlockSetType.OAK, 30, (AbstractBlock.Settings) settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.PALE_PURPLE)
    );
    public static final Block MOON_LEAVES = register("moon_leaves", (settings) -> new MoonLeaves(Colors.PURPLE, settings), AbstractBlock.Settings.create()
            .solid()
            .nonOpaque()
            .ticksRandomly()
            .sounds(BlockSoundGroup.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.PURPLE)
    );
    public static final Block STAR_STONE = register("star_stone", StarStone::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.GLASS)
            .luminance(StarStone::getLuminance)
            .strength(1.0F)
            .mapColor(MapColor.BRIGHT_RED)
    );
    public static final Block MOON_SAPLING = register("moon_sapling", (AbstractBlock.Settings settings) -> new CustomSapling(Helpers.configuredFeatureOf("moon_trees"), settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.GRASS)
            .ticksRandomly()
            .breakInstantly()
    );
    public static final Block POTTED_MOON_SAPLING = registerWoItem("potted_moon_sapling", settings -> new FlowerPotBlock(MOON_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque());
    public static final Block CURVE_SAPLING = register("curve_sapling", (AbstractBlock.Settings settings) -> new CustomSapling(Helpers.configuredFeatureOf("curve_trees"), settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.GRASS)
            .ticksRandomly()
            .breakInstantly()
    );
    public static final Block POTTED_CURVE_SAPLING = registerWoItem("potted_curve_sapling", settings -> new FlowerPotBlock(CURVE_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque());
    public static final Block MOON_GRASS = register("moon_grass", MoonGrass::new, AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE)
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .offset(AbstractBlock.OffsetType.XZ)
            .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block MOON_FERN = register("moon_fern", MoonFern::new, AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE)
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .offset(AbstractBlock.OffsetType.XZ)
            .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block TALL_MOON_GRASS = register("tall_moon_grass", TallMoonGrass::new, AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE)
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .offset(AbstractBlock.OffsetType.XZ)
            .pistonBehavior(PistonBehavior.DESTROY)
    );
    // CURVE PLANKS
    public static final Block CURVE_PLANKS = register("curve_planks", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.PURPLE)
    );
    public static final Block CURVE_PLANKS_SLAB = register("curve_planks_slab", SlabBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.PURPLE)
    );
    public static final Block CURVE_PLANKS_STAIRS = register("curve_planks_stairs", (AbstractBlock.Settings settings) -> new StairsBlock(CURVE_PLANKS.getDefaultState(), (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.PURPLE)
    );
    public static final Block CURVE_PLANKS_FENCE = register("curve_planks_fence", FenceBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.PURPLE)
    );
    public static final Block CURVE_PLANKS_FENCE_GATE = register("curve_planks_fence_gate", (AbstractBlock.Settings settings) -> new FenceGateBlock(WoodType.OAK, (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.PURPLE)
    );
    public static final Block CURVE_PLANKS_BUTTON = register("curve_planks_button", (AbstractBlock.Settings settings) -> new ButtonBlock(BlockSetType.OAK, 30, (AbstractBlock.Settings) settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.PURPLE)
    );
    public static final Block PURPLE_MUSHROOM_BLOCK = register("purple_mushroom_block", MushroomBlock::new, AbstractBlock.Settings.create()
            .mapColor(MapColor.RED)
            .instrument(NoteBlockInstrument.BASS)
            .strength(0.2f)
            .sounds(BlockSoundGroup.WOOD)
            .burnable()
    );
    public static final Block PURPLE_MUSHROOM = register("purple_mushroom", (AbstractBlock.Settings settings) -> new MushroomPlantBlock(Helpers.configuredFeatureOf("purple_shroom"), settings), AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE)
            .strength(0.2f)
            .sounds(BlockSoundGroup.FLOWERBED)
            .burnable()
            .ticksRandomly()
            .noCollision()
    );
    public static final Block POTTED_PURPLE_MUSHROOM = registerWoItem("potted_purple_mushroom", settings -> new FlowerPotBlock(PURPLE_MUSHROOM, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque());
    public static final Block GEODE_FRUIT = register("geode_fruit_block", GeodeFruit::new, AbstractBlock.Settings.create()
            .strength(1.0f)
            .noCollision()
            .ticksRandomly()
            .sounds(BlockSoundGroup.STONE)
    );
    public static final Block STAR_TRAP = register("star_trap", StarTrap::new, AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE)
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .pistonBehavior(PistonBehavior.DESTROY)
    );

    public static final Block FORGET_ME_NOW = register("forget_me_now", ForgetMeNow::new, AbstractBlock.Settings.create()
            .mapColor(MapColor.DARK_RED)
            .noCollision()
            .sounds(BlockSoundGroup.FLOWERBED)
            .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block POTTED_FORGET_ME_NOW = registerWoItem("potted_forget_me_now", (AbstractBlock.Settings settings) -> new FlowerPotBlock(FORGET_ME_NOW, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque());
    public static void init() {
        COLORED_PLANKS.put(ModItems.RED_STAR, MoonBlocks.RED_MOON_PLANKS.getDefaultState());
        COLORED_PLANKS.put(ModItems.BLUE_STAR, MoonBlocks.BLUE_MOON_PLANKS.getDefaultState());
        COLORED_PLANKS.put(ModItems.YELLOW_STAR, MoonBlocks.YELLOW_MOON_PLANKS.getDefaultState());
        COLORED_PLANKS.put(ModItems.PURPLE_STAR, MoonBlocks.PURPLE_MOON_PLANKS.getDefaultState());
    }
}
