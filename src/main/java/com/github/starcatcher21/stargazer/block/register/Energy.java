// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.block.clases.energy.cables.BlueCable;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.PurpleCable;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.RedCable;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.YellowCable;
import com.github.starcatcher21.stargazer.block.clases.energy.generators.StarGenerator;
import com.github.starcatcher21.stargazer.block.clases.energy.machines.NightWatcher;
import com.github.starcatcher21.stargazer.block.clases.energy.machines.StarCrusher;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;

public class Energy {
    public static final Block STARMACHINE_BLOCK = register("star_machine_block", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.METAL)
            .strength(0.75f)
            .mapColor(MapColor.COLOR_BLACK)
    );
    public static final Block STARGENERATOR = register("star_generator", StarGenerator::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.METAL)
            .strength(0.75f)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block YELLOW_CABLE = register("yellow_cable", YellowCable::new, BlockBehaviour.Properties.of()
            .sound(SoundType.METAL)
            .dynamicShape()
            .noOcclusion()
            .strength(0.75f)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block BLUE_CABLE = register("blue_cable", BlueCable::new, BlockBehaviour.Properties.of()
            .sound(SoundType.METAL)
            .dynamicShape()
            .noOcclusion()
            .strength(0.75f)
            .mapColor(MapColor.COLOR_BLUE)
    );
    public static final Block RED_CABLE = register("red_cable", RedCable::new, BlockBehaviour.Properties.of()
            .sound(SoundType.METAL)
            .dynamicShape()
            .noOcclusion()
            .strength(0.75f)
            .mapColor(MapColor.COLOR_RED)
    );
    public static final Block PURPLE_CABLE = register("purple_cable", PurpleCable::new, BlockBehaviour.Properties.of()
            .sound(SoundType.METAL)
            .dynamicShape()
            .noOcclusion()
            .strength(0.75f)
            .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block NIGHT_WATCHER = register("night_watcher", NightWatcher::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.METAL)
            .strength(0.75f)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static final Block STAR_CRUSHER = register("star_crusher", StarCrusher::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.METAL)
            .strength(0.75f)
            .mapColor(MapColor.COLOR_YELLOW)
    );
    public static void init() {}
}
