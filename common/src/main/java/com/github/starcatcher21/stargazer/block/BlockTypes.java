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
import com.github.starcatcher21.stargazer.block.clases.star.aurora.AuroraEntity;
import com.github.starcatcher21.stargazer.block.clases.star.barrier.StarBarrierBlockEntity;
import com.github.starcatcher21.stargazer.block.clases.star.border.BorderBlockEntity;
import com.github.starcatcher21.stargazer.block.clases.star.cosmic.CosmicBlockEntity;
import com.github.starcatcher21.stargazer.block.clases.star.leaves.StarLeavesEntity;
import com.github.starcatcher21.stargazer.block.clases.star.star_display.StarDisplayEntity;
import com.github.starcatcher21.stargazer.block.register.Energy;
import com.github.starcatcher21.stargazer.block.register.EyeBloodBlocks;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Collections;
import java.util.function.Supplier;

public class BlockTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Stargazer.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    @SuppressWarnings("unchecked")
    private static <T extends BlockEntity> RegistrySupplier<BlockEntityType<T>> registerBE(
            String name, BlockEntityType.BlockEntitySupplier<T> factory, Supplier<? extends Block> blockSupplier) {
        return BLOCK_ENTITIES.register(name, () -> {
            Block block = (blockSupplier instanceof RegistrySupplier<?> regSup)
                    ? ModBlock.getRaw((RegistrySupplier<Block>) regSup)
                    : blockSupplier.get();
            return new BlockEntityType<>(factory, Collections.singleton(block));
        });
    }

    public static final RegistrySupplier<BlockEntityType<GraveEntity>> GRAVE =
            registerBE("grave", GraveEntity::new, ModBlock.GRAVE);

    public static final RegistrySupplier<BlockEntityType<StarGeneratorEntity>> STAR_GENERATOR =
            registerBE("stargenerator", StarGeneratorEntity::new, Energy.STARGENERATOR);

    public static final RegistrySupplier<BlockEntityType<NightWatcherEntity>> NIGHT_WATCHER =
            registerBE("night_watcher", NightWatcherEntity::new, Energy.NIGHT_WATCHER);

    public static final RegistrySupplier<BlockEntityType<StarCrusherEntity>> STAR_CRUSHER =
            registerBE("star_crusher", StarCrusherEntity::new, Energy.STAR_CRUSHER);

    public static final RegistrySupplier<BlockEntityType<YellowCableEntity>> YELLOW_CABLE =
            registerBE("yellowcable", YellowCableEntity::new, Energy.YELLOW_CABLE);

    public static final RegistrySupplier<BlockEntityType<BlueCableEntity>> BLUE_CABLE =
            registerBE("bluecable", BlueCableEntity::new, Energy.BLUE_CABLE);

    public static final RegistrySupplier<BlockEntityType<RedCableEntity>> RED_CABLE =
            registerBE("redcable", RedCableEntity::new, Energy.RED_CABLE);

    public static final RegistrySupplier<BlockEntityType<PurpleCableEntity>> PURPLE_CABLE =
            registerBE("purplecable", PurpleCableEntity::new, Energy.PURPLE_CABLE);

    public static final RegistrySupplier<BlockEntityType<NegativeBlockEntity>> NEGATIVE_BLOCK =
            registerBE("negativeblock", NegativeBlockEntity::new, ModBlock.NEGATIVE_BLOCK);

    public static final RegistrySupplier<BlockEntityType<NoRedBlockEntity>> NORED_BLOCK =
            registerBE("noredblock", NoRedBlockEntity::new, ModBlock.NORED_BLOCK);

    public static final RegistrySupplier<BlockEntityType<NoGreenBlockEntity>> NOGREEN_BLOCK =
            registerBE("nogreenblock", NoGreenBlockEntity::new, ModBlock.NOGREEN_BLOCK);

    public static final RegistrySupplier<BlockEntityType<NoBlueBlockEntity>> NOBLUE_BLOCK =
            registerBE("noblueblock", NoBlueBlockEntity::new, ModBlock.NOBLUE_BLOCK);

    public static final RegistrySupplier<BlockEntityType<CosmicBlockEntity>> COSMIC_BLOCK =
            registerBE("cosmicblock", CosmicBlockEntity::new, StarBlocks.COSMIC_BLOCK);

    public static final RegistrySupplier<BlockEntityType<StarLeavesEntity>> STAR_LEAVES =
            registerBE("star_leaves", StarLeavesEntity::new, StarBlocks.STAR_LEAVES);

    public static final RegistrySupplier<BlockEntityType<AuroraEntity>> AURORA =
            registerBE("aurora", AuroraEntity::new, StarBlocks.AURORA);

    public static final RegistrySupplier<BlockEntityType<StarBarrierBlockEntity>> STAR_BARRIER_BLOCK =
            registerBE("starbarrierblock", StarBarrierBlockEntity::new, StarBlocks.STAR_BARRIER_BLOCK);

    public static final RegistrySupplier<BlockEntityType<BorderBlockEntity>> BORDER_BLOCK =
            registerBE("borderblock", BorderBlockEntity::new, StarBlocks.BORDER_BLOCK);

    public static final RegistrySupplier<BlockEntityType<StarTrapEntity>> STAR_TRAP =
            registerBE("startrap", StarTrapEntity::new, MoonBlocks.STAR_TRAP);

    public static final RegistrySupplier<BlockEntityType<EyeJarEntity>> EYE_JAR =
            registerBE("eyejar", EyeJarEntity::new, EyeBloodBlocks.EYE_JAR);

    public static final RegistrySupplier<BlockEntityType<StarDisplayEntity>> STAR_DISPLAY =
            registerBE("stardisplay", StarDisplayEntity::new, StarBlocks.STAR_DISPLAY);

    public static void init() {
        BLOCK_ENTITIES.register();
    }
}