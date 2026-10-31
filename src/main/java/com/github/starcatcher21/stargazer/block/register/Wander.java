package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.clases.moon.MoonPlanks;
import com.github.starcatcher21.starlib.Helpers;
import com.github.starcatcher21.stargazer.block.clases.CustomLeaves;
import com.github.starcatcher21.stargazer.block.clases.CustomWanderSapling;
import com.github.starcatcher21.stargazer.block.clases.moon.log.MoonLog;
import com.github.starcatcher21.stargazer.block.clases.wander.Boril;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;
import static com.github.starcatcher21.stargazer.block.ModBlock.registerWoItem;

public class Wander {
    public static final RegistrySupplier<Block> BORIL = register("boril", Boril::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.GRASS)
            .randomTicks()
            .strength(0.5F, 6.0F)
            .mapColor(MapColor.COLOR_CYAN)
    );
    public static final RegistrySupplier<Block> PUROIL = register("puroil", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.GRASS)
            .strength(0.5F, 6.0F)
            .mapColor(MapColor.COLOR_PURPLE)
    );

    public static final RegistrySupplier<Block> TRUNN_LOG = register("trunn_log", (settings) -> new MoonLog(null, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(blockState -> blockState.getValue(BlockStateProperties.AXIS).equals(Direction.Axis.Y) ? MapColor.COLOR_BLUE : MapColor.ICE)
    );

    public static final RegistrySupplier<Block> TRUNN_PLANKS = register("trunn_planks", MoonPlanks::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_GRAY)
    );
    public static final RegistrySupplier<Block> TRUNN_PLANKS_SLAB = register("trunn_planks_slab", SlabBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_GRAY)
    );
    public static final RegistrySupplier<Block> TRUNN_PLANKS_STAIRS = register("trunn_planks_stairs", (BlockBehaviour.Properties settings) -> new StairBlock(TRUNN_PLANKS.get().defaultBlockState(), (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_GRAY)
    );
    public static final RegistrySupplier<Block> TRUNN_PLANKS_FENCE = register("trunn_planks_fence", FenceBlock::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_GRAY)
    );
    public static final RegistrySupplier<Block> TRUNN_PLANKS_FENCE_GATE = register("trunn_planks_fence_gate", (BlockBehaviour.Properties settings) -> new FenceGateBlock(WoodType.OAK, (BlockBehaviour.Properties)settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_GRAY)
    );
    public static final RegistrySupplier<Block> TRUNN_PLANKS_BUTTON = register("trunn_planks_button", (BlockBehaviour.Properties settings) -> new ButtonBlock(BlockSetType.OAK, 30, (BlockBehaviour.Properties) settings), BlockBehaviour.Properties.of()
            //? if >= 26.2 {
            /*.noCollision()
             *///? } else {
            .noCollission()
            //? }
            .sound(SoundType.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1.0F)
            .mapColor(MapColor.COLOR_GRAY)
    );
    public static final RegistrySupplier<Block> TRUNN_LEAVES = register("trunn_leaves", (settings) -> new CustomLeaves(0x3ffcaa, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .randomTicks()
            .sound(SoundType.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.EMERALD)
    );

    public static final RegistrySupplier<Block> TRUNN_SAPLING = register("trunn_sapling", (BlockBehaviour.Properties settings) -> new CustomWanderSapling(Helpers.configuredFeatureOf(Stargazer.MOD_ID, "trunn_trees"), settings), BlockBehaviour.Properties.of()
            //? if >= 26.2 {
            /*.noCollision()
             *///? } else {
            .noCollission()
            //? }
            .sound(SoundType.GRASS)
            .randomTicks()
            .instabreak()
    );
    public static final RegistrySupplier<Block> POTTED_TRUNN_SAPLING = registerWoItem("potted_trunn_sapling", settings -> new FlowerPotBlock(TRUNN_SAPLING.get(), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());

    public static final RegistrySupplier<Block> UMBRELLA_LOG = register("umbrella_log", (settings) -> new MoonLog(null, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BROWN)
    );
    public static final RegistrySupplier<Block> UMBRELLA_LEAVES = register("umbrella_leaves", (settings) -> new CustomLeaves(0x3ffcaa, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .randomTicks()
            .sound(SoundType.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.EMERALD)
    );
    public static final RegistrySupplier<Block> UMBRELLA_SAPLING = register("umbrella_sapling", (BlockBehaviour.Properties settings) -> new CustomWanderSapling(Helpers.configuredFeatureOf(Stargazer.MOD_ID, "umbrella_trees"), settings), BlockBehaviour.Properties.of()
            //? if >= 26.2 {
            /*.noCollision()
             *///? } else {
            .noCollission()
            //? }
            .sound(SoundType.GRASS)
            .randomTicks()
            .instabreak()
    );
    public static final RegistrySupplier<Block> POTTED_UMBRELLA_SAPLING = registerWoItem("potted_umbrella_sapling", settings -> new FlowerPotBlock(UMBRELLA_SAPLING.get(), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());

    public static void init() {}
}
