package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.block.clases.energy.cables.BlueCable;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.PurpleCable;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.RedCable;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.YellowCable;
import com.github.starcatcher21.stargazer.block.clases.energy.generators.StarGenerator;
import com.github.starcatcher21.stargazer.block.clases.energy.machines.NightWatcher;
import com.github.starcatcher21.stargazer.block.clases.energy.machines.StarCrusher;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;

public class Energy {
    public static final Block STARMACHINE_BLOCK = register("star_machine_block", Block::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.METAL)
            .strength(0.75f)
            .mapColor(MapColor.BLACK)
    );
    public static final Block STARGENERATOR = register("star_generator", StarGenerator::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.METAL)
            .strength(0.75f)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block YELLOW_CABLE = register("yellow_cable", YellowCable::new, AbstractBlock.Settings.create()
            .sounds(BlockSoundGroup.METAL)
            .dynamicBounds()
            .nonOpaque()
            .strength(0.75f)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block BLUE_CABLE = register("blue_cable", BlueCable::new, AbstractBlock.Settings.create()
            .sounds(BlockSoundGroup.METAL)
            .dynamicBounds()
            .nonOpaque()
            .strength(0.75f)
            .mapColor(MapColor.BLUE)
    );
    public static final Block RED_CABLE = register("red_cable", RedCable::new, AbstractBlock.Settings.create()
            .sounds(BlockSoundGroup.METAL)
            .dynamicBounds()
            .nonOpaque()
            .strength(0.75f)
            .mapColor(MapColor.RED)
    );
    public static final Block PURPLE_CABLE = register("purple_cable", PurpleCable::new, AbstractBlock.Settings.create()
            .sounds(BlockSoundGroup.METAL)
            .dynamicBounds()
            .nonOpaque()
            .strength(0.75f)
            .mapColor(MapColor.PURPLE)
    );
    public static final Block NIGHT_WATCHER = register("night_watcher", NightWatcher::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.METAL)
            .strength(0.75f)
            .mapColor(MapColor.YELLOW)
    );
    public static final Block STAR_CRUSHER = register("star_crusher", StarCrusher::new, AbstractBlock.Settings.create()
            .solid()
            .sounds(BlockSoundGroup.METAL)
            .strength(0.75f)
            .mapColor(MapColor.YELLOW)
    );
    public static void init() {}
}
