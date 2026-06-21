package com.github.starcatcher21.stargazer.block;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.clases.eyes.eyejar.EyeJarEntity;
import com.github.starcatcher21.stargazer.block.clases.grave.GraveEntity;
import com.github.starcatcher21.stargazer.block.clases.moon.star_trap.StarTrapEntity;
import com.github.starcatcher21.stargazer.block.clases.negative.NegativeBlockEntity;
import com.github.starcatcher21.stargazer.block.clases.noblue.NoBlueBlockEntity;
import com.github.starcatcher21.stargazer.block.clases.nogreen.NoGreenBlockEntity;
import com.github.starcatcher21.stargazer.block.clases.nored.NoRedBlockEntity;
import com.github.starcatcher21.stargazer.block.clases.star.barrier.StarBarrierBlockEntity;
import com.github.starcatcher21.stargazer.block.clases.star.border.BorderBlockEntity;
import com.github.starcatcher21.stargazer.block.clases.star.cosmic.CosmicBlockEntity;
import com.github.starcatcher21.stargazer.block.clases.star.leaves.StarLeavesEntity;
import com.github.starcatcher21.stargazer.block.register.EyeBloodBlocks;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockTypes {
    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Stargazer.MOD_ID, path), blockEntityType);
    }

    public static final BlockEntityType<GraveEntity> GRAVE = register(
            "grave",
            FabricBlockEntityTypeBuilder.create(GraveEntity::new, ModBlock.GRAVE).build()
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
    public static void init() {
    }
}
