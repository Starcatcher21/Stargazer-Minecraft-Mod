package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.Helpers;
import com.github.starcatcher21.stargazer.block.clases.*;
import com.github.starcatcher21.stargazer.block.clases.moon.MoonPlanks;
import com.github.starcatcher21.stargazer.block.clases.moon.log.MoonLog;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Colors;
import net.minecraft.util.math.Direction;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;
import static com.github.starcatcher21.stargazer.block.ModBlock.registerWoItem;

public class RedOrbBlocks {
    public static final Block RED_ROCK = register("red_rock", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .strength(0.5f)
            .requiresTool()
            .mapColor(MapColor.ORANGE)
    );
    public static final Block GREEN_ROCK = register("green_rock", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .strength(0.5f)
            .requiresTool()
            .mapColor(MapColor.EMERALD_GREEN)
    );
    public static final Block BLUE_GRASS = register("blue_grass", RedGrass::new, AbstractBlock.Settings.create()
            .mapColor(MapColor.CYAN)
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .offset(AbstractBlock.OffsetType.XZ)
            .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block POINTY = register("pointy", (settings) -> new OrbFlower(StatusEffects.GLOWING, 10.0f, settings), AbstractBlock.Settings.create()
            .mapColor(MapColor.CYAN)
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .offset(AbstractBlock.OffsetType.XZ)
            .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block POTTED_POINTY = registerWoItem("potted_pointy", settings -> new FlowerPotBlock(POINTY, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque());
    public static final Block RED_ROCK_SLAB = register("red_rock_slab", SlabBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .requiresTool()
            .strength(0.75F)
            .mapColor(MapColor.ORANGE)
    );
    public static final Block RED_ROCK_STAIRS = register("red_rock_stairs", (AbstractBlock.Settings settings) -> new StairsBlock(RED_ROCK.getDefaultState(), (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .requiresTool()
            .strength(0.75f)
            .mapColor(MapColor.ORANGE)
    );
    public static final Block POLISHED_RED_ROCK = register("polished_red_rock", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.STONE)
            .strength(0.5f)
            .requiresTool()
            .mapColor(MapColor.ORANGE)
    );

    public static final Block YERI_LOG = register("yeri_log", (settings) -> new MoonLog(null, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(blockState -> blockState.get(Properties.AXIS).equals(Direction.Axis.Y) ? MapColor.WHITE : MapColor.YELLOW)
    );

    public static final Block SPIRO_LOG = register("spiro_log", (settings) -> new MoonLog(null, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(blockState -> blockState.get(Properties.AXIS).equals(Direction.Axis.Y) ? MapColor.PINK : MapColor.PURPLE)
    );
    public static final Block SPIRO_LEAVES = register("spiro_leaves", (settings) -> new CustomLeaves(0x3DFFCB, settings), AbstractBlock.Settings.create()
            .solid()
            .nonOpaque()
            .ticksRandomly()
            .sounds(BlockSoundGroup.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.CYAN)
    );

    public static final Block YERI_PLANKS = register("yeri_planks", MoonPlanks::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YERI_PLANKS_SLAB = register("yeri_planks_slab", SlabBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YERI_PLANKS_STAIRS = register("yeri_planks_stairs", (AbstractBlock.Settings settings) -> new StairsBlock(YERI_PLANKS.getDefaultState(), (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YERI_PLANKS_FENCE = register("yeri_planks_fence", FenceBlock::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YERI_PLANKS_FENCE_GATE = register("yeri_planks_fence_gate", (AbstractBlock.Settings settings) -> new FenceGateBlock(WoodType.OAK, (AbstractBlock.Settings)settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YERI_PLANKS_BUTTON = register("yeri_planks_button", (AbstractBlock.Settings settings) -> new ButtonBlock(BlockSetType.OAK, 30, (AbstractBlock.Settings) settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YERI_LEAVES = register("yeri_leaves", (settings) -> new CustomLeaves(Colors.YELLOW, settings), AbstractBlock.Settings.create()
            .solid()
            .nonOpaque()
            .ticksRandomly()
            .sounds(BlockSoundGroup.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YERI_SAPLING = register("yeri_sapling", (AbstractBlock.Settings settings) -> new CustomRedSapling(Helpers.configuredFeatureOf("yeri_trees"), settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.GRASS)
            .ticksRandomly()
            .breakInstantly()
    );
    public static final Block POTTED_YERI_SAPLING = registerWoItem("potted_yeri_sapling", settings -> new FlowerPotBlock(YERI_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque());

    public static final Block SPIRO_SAPLING = register("spiro_sapling", (AbstractBlock.Settings settings) -> new CustomRedSapling(Helpers.configuredFeatureOf("spiro_trees"), settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.GRASS)
            .ticksRandomly()
            .breakInstantly()
    );
    public static final Block POTTED_SPIRO_SAPLING = registerWoItem("potted_spiro_sapling", settings -> new FlowerPotBlock(SPIRO_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque());

    public static final Block RED_ORB_PLATFORM = register("red_orb_platform", RedOrbPlatform::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.METAL)
            .strength(0.4f)
            .mapColor(MapColor.SPRUCE_BROWN)
    );

    public static void init() {}
}
