package com.github.starcatcher21.stargazer.block;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.BlueCableEntity;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.PurpleCableEntity;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.RedCableEntity;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.YellowCableEntity;
import com.github.starcatcher21.stargazer.block.clases.energy.generators.StarGeneratorEntity;
import com.github.starcatcher21.stargazer.block.clases.energy.machines.NightWatcherEntity;
import com.github.starcatcher21.stargazer.block.clases.energy.machines.StarCrusherEntity;
import com.github.starcatcher21.stargazer.block.clases.eyes.eyejar.EyeJarEntity;
import com.github.starcatcher21.stargazer.block.clases.grave.GraveEntity;
import com.github.starcatcher21.stargazer.block.clases.moon.star_trap.StarTrapEntity;
import com.github.starcatcher21.stargazer.block.clases.negative.NegativeBlockEntity;
import com.github.starcatcher21.stargazer.block.clases.noblue.NoBlueBlockEntity;
import com.github.starcatcher21.stargazer.block.clases.nogreen.NoGreenBlockEntity;
import com.github.starcatcher21.stargazer.block.clases.nored.NoRedBlockEntity;
import com.github.starcatcher21.stargazer.block.clases.star.aurora.leaves.AuroraEntity;
import com.github.starcatcher21.stargazer.block.clases.star.barrier.StarBarrierBlockEntity;
import com.github.starcatcher21.stargazer.block.clases.star.border.BorderBlockEntity;
import com.github.starcatcher21.stargazer.block.clases.star.cosmic.CosmicBlockEntity;
import com.github.starcatcher21.stargazer.block.clases.star.leaves.StarLeavesEntity;
import com.github.starcatcher21.stargazer.block.clases.star.star_display.StarDisplayEntity;
import com.github.starcatcher21.stargazer.block.register.Energy;
import com.github.starcatcher21.stargazer.block.register.EyeBloodBlocks;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockTypes {
    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, path), blockEntityType);
    }

    public static final BlockEntityType<GraveEntity> GRAVE = register(
            "grave",
            FabricBlockEntityTypeBuilder.create(GraveEntity::new, ModBlock.GRAVE).build()
    );
    public static final BlockEntityType<StarGeneratorEntity> STAR_GENERATOR = register(
            "stargenerator",
            FabricBlockEntityTypeBuilder.create(StarGeneratorEntity::new, Energy.STARGENERATOR).build()
    );
    public static final BlockEntityType<NightWatcherEntity> NIGHT_WATCHER = register(
            "night_watcher",
            FabricBlockEntityTypeBuilder.create(NightWatcherEntity::new, Energy.NIGHT_WATCHER).build()
    );
    public static final BlockEntityType<StarCrusherEntity> STAR_CRUSHER = register(
            "star_crusher",
            FabricBlockEntityTypeBuilder.create(StarCrusherEntity::new, Energy.STAR_CRUSHER).build()
    );
    public static final BlockEntityType<YellowCableEntity> YELLOW_CABLE = register(
            "yellowcable",
            FabricBlockEntityTypeBuilder.create(YellowCableEntity::new, Energy.YELLOW_CABLE).build()
    );
    public static final BlockEntityType<BlueCableEntity> BLUE_CABLE = register(
            "bluecable",
            FabricBlockEntityTypeBuilder.create(BlueCableEntity::new, Energy.BLUE_CABLE).build()
    );
    public static final BlockEntityType<RedCableEntity> RED_CABLE = register(
            "redcable",
            FabricBlockEntityTypeBuilder.create(RedCableEntity::new, Energy.RED_CABLE).build()
    );
    public static final BlockEntityType<PurpleCableEntity> PURPLE_CABLE = register(
            "purplecable",
            FabricBlockEntityTypeBuilder.create(PurpleCableEntity::new, Energy.PURPLE_CABLE).build()
    );
    public static final BlockEntityType<NegativeBlockEntity> NEGATIVE_BLOCK = register(
            "negativeblock",
            FabricBlockEntityTypeBuilder.create(NegativeBlockEntity::new, ModBlock.NEGATIVE_BLOCK).build()
    );
    public static final BlockEntityType<NoRedBlockEntity> NORED_BLOCK = register(
            "noredblock",
            FabricBlockEntityTypeBuilder.create(NoRedBlockEntity::new, ModBlock.NORED_BLOCK).build()
    );
    public static final BlockEntityType<NoGreenBlockEntity> NOGREEN_BLOCK = register(
            "nogreenblock",
            FabricBlockEntityTypeBuilder.create(NoGreenBlockEntity::new, ModBlock.NOGREEN_BLOCK).build()
    );
    public static final BlockEntityType<NoBlueBlockEntity> NOBLUE_BLOCK = register(
            "noblueblock",
            FabricBlockEntityTypeBuilder.create(NoBlueBlockEntity::new, ModBlock.NOBLUE_BLOCK).build()
    );
    public static final BlockEntityType<CosmicBlockEntity> COSMIC_BLOCK = register(
            "cosmicblock",
            FabricBlockEntityTypeBuilder.create(CosmicBlockEntity::new, StarBlocks.COSMIC_BLOCK).build()
    );
    public static final BlockEntityType<StarLeavesEntity> STAR_LEAVES = register(
            "star_leaves",
            FabricBlockEntityTypeBuilder.create(StarLeavesEntity::new, StarBlocks.STAR_LEAVES).build()
    );
    public static final BlockEntityType<AuroraEntity> AURORA = register(
            "aurora",
            FabricBlockEntityTypeBuilder.create(AuroraEntity::new, StarBlocks.AURORA).build()
    );
    public static final BlockEntityType<StarBarrierBlockEntity> STAR_BARRIER_BLOCK = register(
            "starbarrierblock",
            FabricBlockEntityTypeBuilder.create(StarBarrierBlockEntity::new, StarBlocks.STAR_BARRIER_BLOCK).build()
    );
    public static final BlockEntityType<BorderBlockEntity> BORDER_BLOCK = register(
            "borderblock",
            FabricBlockEntityTypeBuilder.create(BorderBlockEntity::new, StarBlocks.BORDER_BLOCK).build()
    );
    public static final BlockEntityType<StarTrapEntity> STAR_TRAP = register(
            "startrap",
            FabricBlockEntityTypeBuilder.create(StarTrapEntity::new, MoonBlocks.STAR_TRAP).build()
    );
    public static final BlockEntityType<EyeJarEntity> EYE_JAR = register(
            "eyejar",
            FabricBlockEntityTypeBuilder.create(EyeJarEntity::new, EyeBloodBlocks.EYE_JAR).build()
    );
    public static final BlockEntityType<StarDisplayEntity> STAR_DISPLAY = register(
            "stardisplay",
            FabricBlockEntityTypeBuilder.create(StarDisplayEntity::new, StarBlocks.STAR_DISPLAY).build()
    );
    public static void init() {
    }
}
