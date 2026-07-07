// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.Helpers;
import com.github.starcatcher21.stargazer.block.clases.CosmicFlower;
import com.github.starcatcher21.stargazer.block.clases.CustomSapling;
import com.github.starcatcher21.stargazer.block.clases.star.aurora.Aurora;
import com.github.starcatcher21.stargazer.block.clases.star.barrier.StarBarrierBlock;
import com.github.starcatcher21.stargazer.block.clases.star.border.BorderBlock;
import com.github.starcatcher21.stargazer.block.clases.star.cosmic.CosmicBlock;
import com.github.starcatcher21.stargazer.block.clases.star.leaves.StarLeaves;
import com.github.starcatcher21.stargazer.block.clases.star.log.StarLog;
import com.github.starcatcher21.stargazer.block.clases.star.log.StrippedStarLog;
import com.github.starcatcher21.stargazer.block.clases.star.star_display.StarDisplay;
import com.github.starcatcher21.stargazer.effects.StatusEffects;
import com.github.starcatcher21.stargazer.sound.SoundGroups;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
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

public class StarBlocks {
    public static final Block COSMIC_BLOCK = register("cosmic_block", CosmicBlock::new, BlockBehaviour.Properties.of()
            .noOcclusion()
            .requiresCorrectToolForDrops()
            .strength(0.2f)
            .sound(SoundGroups.STAR)
            .pushReaction(PushReaction.BLOCK)
    );
    public static final Block STAR_BARRIER_BLOCK = register("star_barrier_block", StarBarrierBlock::new, BlockBehaviour.Properties.of()
            .noOcclusion()
            .requiresCorrectToolForDrops()
            .sound(SoundGroups.STAR)
            .strength(0.2f)
            .pushReaction(PushReaction.BLOCK)
    );
    public static final Block BORDER_BLOCK = register("border_block", BorderBlock::new, BlockBehaviour.Properties.of()
            .noOcclusion()
            .requiresCorrectToolForDrops()
            .forceSolidOn()
            .strength(0.2f)
            .pushReaction(PushReaction.BLOCK)
    );
    public static final Block STAR_LOG = register("star_log", StarLog::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(blockState -> blockState.getValue(BlockStateProperties.AXIS).equals(Direction.Axis.Y) ? MapColor.SNOW : MapColor.COLOR_PURPLE)
    );
    public static final Block STRIPPED_STAR_LOG = register("stripped_star_log", StrippedStarLog::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(DyeColor.BLACK)
    );
    public static final Block STAR_PLANKS = register("star_planks", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BLACK)
    );
    public static final Block STAR_PLANKS_DOOR = register("star_planks_door", (settings) -> new DoorBlock(BlockSetType.OAK, (BlockBehaviour.Properties)  settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.SNOW)
    );

    public static final Block STAR_PLANKS_SLAB = register("star_planks_slab", SlabBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BLACK)
    );
    public static final Block STAR_PLANKS_STAIRS = register("star_planks_stairs", (BlockBehaviour.Properties settings) -> new StairBlock(STAR_PLANKS.defaultBlockState(), (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BLACK)
    );
    public static final Block STAR_PLANKS_FENCE = register("star_planks_fence", FenceBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BLACK)
    );
    public static final Block STAR_PLANKS_FENCE_GATE = register("star_planks_fence_gate", (BlockBehaviour.Properties settings) -> new FenceGateBlock(WoodType.OAK, (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BLACK)
    );
    public static final Block STAR_PLANKS_BUTTON = register("star_planks_button", (BlockBehaviour.Properties settings) -> new ButtonBlock(BlockSetType.OAK, 30, (BlockBehaviour.Properties) settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.COLOR_BLACK)
    );
    public static final Block STAR_LEAVES = register("star_leaves", StarLeaves::new, BlockBehaviour.Properties.of()
            .noOcclusion()
            .sound(SoundType.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block AURORA = register("aurora", Aurora::new, BlockBehaviour.Properties.of()
            .noOcclusion()
            .sound(SoundType.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.COLOR_CYAN)
    );
    public static final Block STAR_SAPLING = register("star_sapling", (BlockBehaviour.Properties settings) -> new CustomSapling(Helpers.configuredFeatureOf("star_trees"), settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.GRASS)
            .randomTicks()
            .instabreak()
    );
    public static final Block POTTED_STAR_SAPLING = registerWoItem("potted_star_sapling", settings -> new FlowerPotBlock(STAR_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());
    public static final Block STAR_FLOWER = register("star_flower", settings -> new CosmicFlower(StatusEffects.COSMO, 5.0f, settings), BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_YELLOW)
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
    );
    public static final Block POTTED_STAR_FLOWER = registerWoItem("potted_star_flower", settings -> new FlowerPotBlock(STAR_FLOWER, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());
    public static final Block CELESTIAL_STAR_FLOWER = register("celestial_star_flower", settings -> new CosmicFlower(StatusEffects.COSMO, 5.0f, settings), BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_YELLOW)
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
    );
    public static final Block POTTED_CELESTIAL_STAR_FLOWER = registerWoItem("potted_celestial_star_flower", settings -> new FlowerPotBlock(CELESTIAL_STAR_FLOWER, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());

    public static final Block RED_STAR_BLOCK = register("red_star_block", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(0.75f)
            .mapColor(MapColor.FIRE)
    );

    public static final Block BLUE_STAR_BLOCK = register("blue_star_block", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(0.75f)
            .mapColor(MapColor.COLOR_CYAN)
    );

    public static final Block YELLOW_STAR_BLOCK = register("yellow_star_block", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(0.75f)
            .mapColor(MapColor.COLOR_YELLOW)
    );

    public static final Block PURPLE_STAR_BLOCK = register("purple_star_block", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(0.75f)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block STAR_DISPLAY = register("star_display", StarDisplay::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.METAL)
            .strength(0.175F)
    );
    public static void init() {
    }
}
