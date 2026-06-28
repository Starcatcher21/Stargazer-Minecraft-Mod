package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.block.ModFluids;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;

import static com.github.starcatcher21.stargazer.block.ModBlock.registerWoItem;

public class Fluids {

    public static final Block DREAM = registerWoItem(
            "dream",
            settings -> new FluidBlock(ModFluids.DREAM, settings),
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .replaceable()
                    .noCollision()
                    .strength(100.0F)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .dropsNothing()
                    .liquid()
                    .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)
    );
    public static void init() {}
}
