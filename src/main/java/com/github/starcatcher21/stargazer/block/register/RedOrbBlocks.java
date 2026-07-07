// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.Helpers;
import com.github.starcatcher21.stargazer.block.clases.*;
import com.github.starcatcher21.stargazer.block.clases.moon.MoonPlanks;
import com.github.starcatcher21.stargazer.block.clases.moon.log.MoonLog;
import net.minecraft.core.Direction;
import net.minecraft.util.CommonColors;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;
import static com.github.starcatcher21.stargazer.block.ModBlock.registerWoItem;

public class RedOrbBlocks {
    public static final Block RED_ROCK = register("red_rock", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .strength(0.5f)
            .requiresCorrectToolForDrops()
            .mapColor(MapColor.COLOR_ORANGE)
    );
    public static final Block GREEN_ROCK = register("green_rock", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .strength(0.5f)
            .requiresCorrectToolForDrops()
            .mapColor(MapColor.EMERALD)
    );
    public static final Block BLUE_GRASS = register("blue_grass", RedGrass::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_CYAN)
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
    );
    public static final Block POINTY = register("pointy", (settings) -> new OrbFlower(MobEffects.GLOWING, 10.0f, settings), BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_CYAN)
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
    );
    public static final Block POTTED_POINTY = registerWoItem("potted_pointy", settings -> new FlowerPotBlock(POINTY, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());
    public static final Block RED_ROCK_SLAB = register("red_rock_slab", SlabBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(0.75F)
            .mapColor(MapColor.COLOR_ORANGE)
    );
    public static final Block RED_ROCK_STAIRS = register("red_rock_stairs", (BlockBehaviour.Properties settings) -> new StairBlock(RED_ROCK.defaultBlockState(), (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(0.75f)
            .mapColor(MapColor.COLOR_ORANGE)
    );
    public static final Block POLISHED_RED_ROCK = register("polished_red_rock", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .strength(0.5f)
            .requiresCorrectToolForDrops()
            .mapColor(MapColor.COLOR_ORANGE)
    );

    public static final Block YERI_LOG = register("yeri_log", (settings) -> new MoonLog(null, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(blockState -> blockState.getValue(BlockStateProperties.AXIS).equals(Direction.Axis.Y) ? MapColor.SNOW : MapColor.COLOR_YELLOW)
    );

    public static final Block SPIRO_LOG = register("spiro_log", (settings) -> new MoonLog(null, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(blockState -> blockState.getValue(BlockStateProperties.AXIS).equals(Direction.Axis.Y) ? MapColor.COLOR_PINK : MapColor.COLOR_PURPLE)
    );
    public static final Block SPIRO_LEAVES = register("spiro_leaves", (settings) -> new CustomLeaves(0x3DFFCB, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .randomTicks()
            .sound(SoundType.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.COLOR_CYAN)
    );

    public static final Block YERI_PLANKS = register("yeri_planks", MoonPlanks::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YERI_PLANKS_SLAB = register("yeri_planks_slab", SlabBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YERI_PLANKS_STAIRS = register("yeri_planks_stairs", (BlockBehaviour.Properties settings) -> new StairBlock(YERI_PLANKS.defaultBlockState(), (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YERI_PLANKS_FENCE = register("yeri_planks_fence", FenceBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YERI_PLANKS_FENCE_GATE = register("yeri_planks_fence_gate", (BlockBehaviour.Properties settings) -> new FenceGateBlock(WoodType.OAK, (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YERI_PLANKS_BUTTON = register("yeri_planks_button", (BlockBehaviour.Properties settings) -> new ButtonBlock(BlockSetType.OAK, 30, (BlockBehaviour.Properties) settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YERI_LEAVES = register("yeri_leaves", (settings) -> new CustomLeaves(CommonColors.YELLOW, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .randomTicks()
            .sound(SoundType.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YERI_SAPLING = register("yeri_sapling", (BlockBehaviour.Properties settings) -> new CustomRedSapling(Helpers.configuredFeatureOf("yeri_trees"), settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.GRASS)
            .randomTicks()
            .instabreak()
    );
    public static final Block POTTED_YERI_SAPLING = registerWoItem("potted_yeri_sapling", settings -> new FlowerPotBlock(YERI_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());

    public static final Block SPIRO_SAPLING = register("spiro_sapling", (BlockBehaviour.Properties settings) -> new CustomRedSapling(Helpers.configuredFeatureOf("spiro_trees"), settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.GRASS)
            .randomTicks()
            .instabreak()
    );
    public static final Block POTTED_SPIRO_SAPLING = registerWoItem("potted_spiro_sapling", settings -> new FlowerPotBlock(SPIRO_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());

    public static final Block RED_ORB_PLATFORM = register("red_orb_platform", RedOrbPlatform::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.METAL)
            .strength(0.4f)
            .mapColor(MapColor.PODZOL)
    );

    public static void init() {}
}
