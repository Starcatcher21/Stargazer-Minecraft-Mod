package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.RedOrbBlocks;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.block.register.Wander;
import com.github.starcatcher21.stargazer.datagen.DataProviders.CobblegenDataProvider;
import com.github.starcatcher21.stargazer.datagen.DataProviders.FallingObjectDataProvider;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.mechanics.Generators.CobbleGen;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObject;
import com.github.starcatcher21.stargazer.particle.Particles;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.jpountz.lz4.LZ4FrameOutputStream;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class CobbleGenProvider extends CobblegenDataProvider {
    public CobbleGenProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final CobbleGen BLUE = new CobbleGen(
            FluidTags.WATER,
            ModBlock.NOBLUE_BLOCK.defaultBlockState(),
            Blocks.LAPIS_BLOCK.defaultBlockState(),
            Blocks.LAPIS_ORE.defaultBlockState()
    );
    public static final CobbleGen GREEN = new CobbleGen(
            FluidTags.WATER,
            ModBlock.NOGREEN_BLOCK.defaultBlockState(),
            RedOrbBlocks.GREEN_ROCK.defaultBlockState(),
            RedOrbBlocks.GREEN_ROCK.defaultBlockState()
    );
    public static final CobbleGen RED = new CobbleGen(
            FluidTags.WATER,
            ModBlock.NORED_BLOCK.defaultBlockState(),
            Blocks.REDSTONE_BLOCK.defaultBlockState(),
            Blocks.REDSTONE_ORE.defaultBlockState()
    );
    public static final CobbleGen COSMIC_LAVA = new CobbleGen(
            FluidTags.LAVA,
            StarBlocks.COSMIC_BLOCK.defaultBlockState(),
            Blocks.DARK_PRISMARINE.defaultBlockState(),
            MoonBlocks.STAR_STONE.defaultBlockState()
    );
    public static final CobbleGen COSMIC_WATER = new CobbleGen(
            FluidTags.WATER,
            StarBlocks.COSMIC_BLOCK.defaultBlockState(),
            MoonBlocks.BLACK_MOON_ROCK.defaultBlockState(),
            MoonBlocks.MOON_ROCK.defaultBlockState()
    );
    public static final CobbleGen DREAM = new CobbleGen(
            ModFluidTagProvider.DREAM,
            Blocks.WATER.defaultBlockState(),
            MoonBlocks.MOON_ROCK.defaultBlockState(),
            MoonBlocks.MOON_ROCK.defaultBlockState()
    );
    public static final CobbleGen LAVA_WATER = new CobbleGen(
            FluidTags.WATER,
            Blocks.LAVA.defaultBlockState(),
            Blocks.OBSIDIAN.defaultBlockState(),
            Blocks.COBBLESTONE.defaultBlockState()
    );
    public static final CobbleGen NEGATIVE_WATER = new CobbleGen(
            FluidTags.WATER,
            ModBlock.NEGATIVE_BLOCK.defaultBlockState(),
            Blocks.AMETHYST_BLOCK.defaultBlockState(),
            Blocks.END_STONE.defaultBlockState()
    );

    @Override
    protected void addCobblegen(BiConsumer<Identifier, CobbleGen> exporter, HolderLookup.Provider registries) {
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "blue"),
                BLUE
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "red"),
                RED
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "green"),
                GREEN
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic_lava"),
                COSMIC_LAVA
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic_water"),
                COSMIC_WATER
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "dream"),
                DREAM
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "lava_water"),
                LAVA_WATER
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "negative_water"),
                NEGATIVE_WATER
        );
    }
}