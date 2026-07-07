// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.block.clases.Hedge;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;

public class Hedges {
    public static final Block OAK_HEDGE = register("oak_hedge", settings -> new Hedge(Blocks.OAK_LEAVES, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .randomTicks()
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BROWN)
    );
    public static final Block BIRCH_HEDGE = register("birch_hedge", settings -> new Hedge(Blocks.BIRCH_LEAVES, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .randomTicks()
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BROWN)
    );
    public static final Block ACACIA_HEDGE = register("acacia_hedge", settings -> new Hedge(Blocks.ACACIA_LEAVES, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .randomTicks()
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BROWN)
    );
    public static final Block CHERRY_HEDGE = register("cherry_hedge", settings -> new Hedge(Blocks.CHERRY_LEAVES, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .randomTicks()
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BROWN)
    );
    public static final Block JUNGLE_HEDGE = register("jungle_hedge", settings -> new Hedge(Blocks.JUNGLE_LEAVES, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .randomTicks()
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BROWN)
    );
    public static final Block MANGROVE_HEDGE = register("mangrove_hedge", settings -> new Hedge(Blocks.MANGROVE_LEAVES, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .randomTicks()
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BROWN)
    );
    public static final Block SPRUCE_HEDGE = register("spruce_hedge", settings -> new Hedge(Blocks.SPRUCE_LEAVES, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .randomTicks()
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BROWN)
    );
    public static final Block DARK_OAK_HEDGE = register("dark_oak_hedge", settings -> new Hedge(Blocks.DARK_OAK_LEAVES, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .randomTicks()
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BROWN)
    );
    public static final Block PALE_HEDGE = register("pale_oak_hedge", settings -> new Hedge(Blocks.PALE_OAK_LEAVES, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .randomTicks()
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BROWN)
    );
    public static final Block MOON_HEDGE = register("moon_hedge", settings -> new Hedge(MoonBlocks.MOON_LEAVES, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .randomTicks()
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BROWN)
    );
    public static final Block CURVE_HEDGE = register("curve_hedge", settings -> new Hedge(MoonBlocks.CURVE_LEAVES, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .randomTicks()
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BROWN)
    );
    public static final Block STAR_HEDGE = register("star_hedge", settings -> new Hedge(StarBlocks.STAR_LEAVES, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .randomTicks()
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BROWN)
    );
    public static final Block DARKNESS_HEDGE = register("darkness_hedge", settings -> new Hedge(Darkness.DARKNESS_LEAVES, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .randomTicks()
            .strength(2.0F)
            .mapColor(MapColor.COLOR_BROWN)
    );
    public static final Block YERI_HEDGE = register("yeri_hedge", settings -> new Hedge(RedOrbBlocks.YERI_LEAVES, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .randomTicks()
            .strength(2.0F)
            .mapColor(MapColor.SNOW)
    );
    public static final Block FULL_MOON_HEDGE = register("full_moon_hedge", settings -> new Hedge(MoonBlocks.FULL_MOON_LEAVES, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .randomTicks()
            .strength(2.0F)
            .mapColor(MapColor.SNOW)
    );
    public static final Block SPIRO_HEDGE = register("spiro_hedge", settings -> new Hedge(RedOrbBlocks.SPIRO_LEAVES, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .randomTicks()
            .strength(2.0F)
            .mapColor(MapColor.COLOR_PINK)
    );
    public static final Block TRUNN_HEDGE = register("trunn_hedge", settings -> new Hedge(Wander.TRUNN_LEAVES, settings), BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .randomTicks()
            .strength(2.0F)
            .mapColor(MapColor.COLOR_PINK)
    );
    public static void init() {
    }
}
