// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.block.clases.CustomLeaves;
import com.github.starcatcher21.stargazer.block.clases.eyes.EyeFern;
import com.github.starcatcher21.stargazer.block.clases.eyes.EyeLog;
import com.github.starcatcher21.stargazer.block.clases.eyes.Eyes;
import com.github.starcatcher21.stargazer.block.clases.eyes.StrippedEyeLog;
import com.github.starcatcher21.stargazer.block.clases.eyes.eyejar.EyeJar;
import net.minecraft.core.Direction;
import net.minecraft.util.CommonColors;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;

public class EyeBloodBlocks {

    public static final Block STRIPPED_EYE_LOG = register("stripped_eye_log", StrippedEyeLog::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.15F)
            .mapColor(MapColor.SAND)
    );
    public static final Block EYE_LOG = register("eye_log", (settings) -> new EyeLog(STRIPPED_EYE_LOG, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.15F)
            .randomTicks()
            .mapColor(blockState -> blockState.getValue(BlockStateProperties.AXIS).equals(Direction.Axis.Y) ? MapColor.COLOR_YELLOW : MapColor.SNOW)
    );

    public static final Block EYE_LEAVES = register("eye_leaves", (settings) -> new CustomLeaves(CommonColors.RED, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .sound(SoundType.GRASS)
            .strength(0.175F)
            .mapColor(MapColor.COLOR_RED)
    );

    public static final Block EYE_JAR = register("eye_jar", EyeJar::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.GRASS)
            .strength(0.175F)
    );

    public static final Block EYE_FERN = register("eye_fern", EyeFern::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.FIRE)
            .noCollision()
            .instabreak()
            .randomTicks()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
    );

    public static final Block EYES = register("eyes", Eyes::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.NETHER)
            .noCollision()
            .sound(SoundType.PINK_PETALS)
            .pushReaction(PushReaction.DESTROY)
    );

    public static void init() {}
}
