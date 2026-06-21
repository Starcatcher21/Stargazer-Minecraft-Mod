package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.Helpers;
import com.github.starcatcher21.stargazer.block.clases.CosmicFlower;
import com.github.starcatcher21.stargazer.block.clases.CustomLeaves;
import com.github.starcatcher21.stargazer.block.clases.CustomSapling;
import com.github.starcatcher21.stargazer.block.clases.CustomWanderSapling;
import com.github.starcatcher21.stargazer.block.clases.moon.ForgetMeNow;
import com.github.starcatcher21.stargazer.block.clases.moon.MoonRock;
import com.github.starcatcher21.stargazer.block.clases.moon.leaves.MoonLeaves;
import com.github.starcatcher21.stargazer.block.clases.moon.log.MoonLog;
import com.github.starcatcher21.stargazer.block.clases.moon.log.StrippedMoonLog;
import com.github.starcatcher21.stargazer.block.clases.wander.Boril;
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

public class Wander {
    public static final Block BORIL = register("boril", Boril::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.GRASS)
            .ticksRandomly()
            .strength(0.5F, 6.0F)
            .mapColor(MapColor.CYAN)
    );
    public static final Block PUROIL = register("puroil", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.GRASS)
            .strength(0.5F, 6.0F)
            .mapColor(MapColor.PURPLE)
    );

    public static final Block TRUNN_LOG = register("trunn_log", (settings) -> new MoonLog(null, settings), AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0F)
            .mapColor(blockState -> blockState.get(Properties.AXIS).equals(Direction.Axis.Y) ? MapColor.BLUE : MapColor.PALE_PURPLE)
    );
    public static final Block TRUNN_LEAVES = register("trunn_leaves", (settings) -> new CustomLeaves(0x3ffcaa, settings), AbstractBlock.Settings.create()
            .solid()
            .nonOpaque()
            .ticksRandomly()
            .sounds(BlockSoundGroup.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.EMERALD_GREEN)
    );

    public static final Block TRUNN_SAPLING = register("trunn_sapling", (AbstractBlock.Settings settings) -> new CustomWanderSapling(Helpers.configuredFeatureOf("trunn_trees"), settings), AbstractBlock.Settings.create()
            .noCollision()
            .sounds(BlockSoundGroup.GRASS)
            .ticksRandomly()
            .breakInstantly()
    );
    public static final Block POTTED_TRUNN_SAPLING = registerWoItem("potted_trunn_sapling", settings -> new FlowerPotBlock(TRUNN_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque());

    public static void init() {}
}
