// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.starlib.Helpers;
import com.github.starcatcher21.stargazer.block.clases.CosmicFlower;
import com.github.starcatcher21.stargazer.block.clases.CustomLeaves;
import com.github.starcatcher21.stargazer.block.clases.CustomSapling;
import com.github.starcatcher21.stargazer.block.clases.moon.ForgetMeNow;
import com.github.starcatcher21.stargazer.block.clases.moon.MoonRock;
import com.github.starcatcher21.stargazer.block.clases.moon.log.MoonLog;
import com.github.starcatcher21.stargazer.block.clases.moon.log.StrippedMoonLog;
import net.minecraft.core.Direction;
import net.minecraft.util.CommonColors;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
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

public class Darkness {
    public static final Block DYLIUM = register("dylium", MoonRock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.NYLIUM)
            .strength(0.5f)
            .randomTicks()
            .requiresCorrectToolForDrops()
            .strength(1.5F, 6.0F)
            .mapColor(MapColor.DEEPSLATE)
    );
    public static final Block ROSE_OF_PAIN = register("rose_of_pain", settings -> new CosmicFlower(MobEffects.DARKNESS, 5.0f, settings), BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_GRAY)
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
    );
    public static final Block POTTED_ROSE_OF_PAIN = registerWoItem("potted_rose_of_pain", settings -> new FlowerPotBlock(ROSE_OF_PAIN, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());
    public static final Block STRIPPED_LOG_OF_DARKNESS = register("stripped_log_of_darkness", StrippedMoonLog::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_GRAY)
    );
    public static final Block DARKNESS_SAPLING = register("darkness_sapling", (BlockBehaviour.Properties settings) -> new CustomSapling(Helpers.configuredFeatureOf("darkness_trees"), settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.GRASS)
            .randomTicks()
            .instabreak()
    );
    public static final Block POTTED_DARKNESS_SAPLING = registerWoItem("potted_darkness_sapling", settings -> new FlowerPotBlock(DARKNESS_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());
    public static final Block LOG_OF_DARKNESS = register("log_of_darkness", (settings) -> new MoonLog(STRIPPED_LOG_OF_DARKNESS, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(blockState -> blockState.getValue(BlockStateProperties.AXIS).equals(Direction.Axis.Y) ? MapColor.DEEPSLATE : MapColor.COLOR_BLACK)
    );

    public static final Block DARKNESS_LEAVES = register("darkness_leaves", (settings) -> new CustomLeaves(CommonColors.BLACK, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .randomTicks()
            .sound(SoundType.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.COLOR_BLACK)
    );

    public static final Block DARKNESS_PLANKS = register("darkness_planks", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_GRAY)
    );
    public static final Block DARKNESS_PLANKS_DOOR = register("darkness_planks_door", (settings) -> new DoorBlock(BlockSetType.OAK, (BlockBehaviour.Properties)  settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BLACK)
    );
    public static final Block DARKNESS_PLANKS_SLAB = register("darkness_planks_slab", SlabBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_GRAY)
    );
    public static final Block DARKNESS_PLANKS_STAIRS = register("darkness_planks_stairs", (BlockBehaviour.Properties settings) -> new StairBlock(DARKNESS_PLANKS.defaultBlockState(), (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_GRAY)
    );
    public static final Block DARKNESS_PLANKS_FENCE = register("darkness_planks_fence", FenceBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_GRAY)
    );
    public static final Block DARKNESS_PLANKS_FENCE_GATE = register("darkness_planks_fence_gate", (BlockBehaviour.Properties settings) -> new FenceGateBlock(WoodType.OAK, (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_GRAY)
    );
    public static final Block DARKNESS_PLANKS_BUTTON = register("darkness_planks_button", (BlockBehaviour.Properties settings) -> new ButtonBlock(BlockSetType.OAK, 30, (BlockBehaviour.Properties) settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.COLOR_GRAY)
    );

    public static final Block GRADI = register("gradi", ForgetMeNow::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.SNOW)
            .noCollision()
            .sound(SoundType.PINK_PETALS)
            .pushReaction(PushReaction.DESTROY)
    );
    public static final Block POTTED_GRADI = registerWoItem("potted_gradi", (BlockBehaviour.Properties settings) -> new FlowerPotBlock(GRADI, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());

    public static void init() {}
}
