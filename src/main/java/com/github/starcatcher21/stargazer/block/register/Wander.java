// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.starlib.Helpers;
import com.github.starcatcher21.stargazer.block.clases.CustomLeaves;
import com.github.starcatcher21.stargazer.block.clases.CustomWanderSapling;
import com.github.starcatcher21.stargazer.block.clases.moon.log.MoonLog;
import com.github.starcatcher21.stargazer.block.clases.wander.Boril;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;
import static com.github.starcatcher21.stargazer.block.ModBlock.registerWoItem;

public class Wander {
    public static final Block BORIL = register("boril", Boril::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.GRASS)
            .randomTicks()
            .strength(0.5F, 6.0F)
            .mapColor(MapColor.COLOR_CYAN)
    );
    public static final Block PUROIL = register("puroil", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.GRASS)
            .strength(0.5F, 6.0F)
            .mapColor(MapColor.COLOR_PURPLE)
    );

    public static final Block TRUNN_LOG = register("trunn_log", (settings) -> new MoonLog(null, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(2.0F)
            .mapColor(blockState -> blockState.getValue(BlockStateProperties.AXIS).equals(Direction.Axis.Y) ? MapColor.COLOR_BLUE : MapColor.ICE)
    );
    public static final Block TRUNN_LEAVES = register("trunn_leaves", (settings) -> new CustomLeaves(0x3ffcaa, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .randomTicks()
            .sound(SoundType.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.EMERALD)
    );

    public static final Block TRUNN_SAPLING = register("trunn_sapling", (BlockBehaviour.Properties settings) -> new CustomWanderSapling(Helpers.configuredFeatureOf("trunn_trees"), settings), BlockBehaviour.Properties.of()
            .noCollision()
            .sound(SoundType.GRASS)
            .randomTicks()
            .instabreak()
    );
    public static final Block POTTED_TRUNN_SAPLING = registerWoItem("potted_trunn_sapling", settings -> new FlowerPotBlock(TRUNN_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());

    public static void init() {}
}
