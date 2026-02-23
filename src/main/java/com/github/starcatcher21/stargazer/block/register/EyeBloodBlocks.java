package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.block.clases.CustomLeaves;
import com.github.starcatcher21.stargazer.block.clases.eyes.EyeFern;
import com.github.starcatcher21.stargazer.block.clases.eyes.EyeLog;
import com.github.starcatcher21.stargazer.block.clases.eyes.Eyes;
import com.github.starcatcher21.stargazer.block.clases.eyes.StrippedEyeLog;
import com.github.starcatcher21.stargazer.block.clases.eyes.eyejar.EyeJar;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Colors;
import net.minecraft.util.math.Direction;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;

public class EyeBloodBlocks {

    public static final Block STRIPPED_EYE_LOG = register("stripped_eye_log", StrippedEyeLog::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.15F)
            .mapColor(MapColor.PALE_YELLOW)
    );
    public static final Block EYE_LOG = register("eye_log", (settings) -> new EyeLog(STRIPPED_EYE_LOG, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.15F)
            .ticksRandomly()
            .mapColor(blockState -> blockState.get(Properties.AXIS).equals(Direction.Axis.Y) ? MapColor.YELLOW : MapColor.WHITE)
    );

    public static final Block EYE_LEAVES = register("eye_leaves", (settings) -> new CustomLeaves(Colors.RED, settings), AbstractBlock.Settings.create()
            .solid()
            .nonOpaque()
            .sounds(BlockSoundGroup.GRASS)
            .strength(0.175F)
            .mapColor(MapColor.RED)
    );

    public static final Block EYE_JAR = register("eye_jar", EyeJar::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.GRASS)
            .strength(0.175F)
    );

    public static final Block EYE_FERN = register("eye_fern", EyeFern::new, AbstractBlock.Settings.create()
            .mapColor(MapColor.BRIGHT_RED)
            .noCollision()
            .breakInstantly()
            .ticksRandomly()
            .sounds(BlockSoundGroup.GRASS)
            .offset(AbstractBlock.OffsetType.XZ)
            .pistonBehavior(PistonBehavior.DESTROY)
    );

    public static final Block EYES = register("eyes", Eyes::new, AbstractBlock.Settings.create()
            .mapColor(MapColor.DARK_RED)
            .noCollision()
            .sounds(BlockSoundGroup.FLOWERBED)
            .pistonBehavior(PistonBehavior.DESTROY)
    );

    public static void init() {}
}
