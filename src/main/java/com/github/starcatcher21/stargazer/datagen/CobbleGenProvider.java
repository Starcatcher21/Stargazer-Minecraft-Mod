/*? if fabric {*/
package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.RedOrbBlocks;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.starlib.datagen.provider.CobblegenDataProvider;
import com.github.starcatcher21.starlib.mechanics.Generators.CobbleGen;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class CobbleGenProvider extends CobblegenDataProvider {
    public CobbleGenProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final CobbleGen BLUE = new CobbleGen(
            FluidTags.WATER,
            ModBlock.NOBLUE_BLOCK.get().defaultBlockState(),
            Blocks.LAPIS_BLOCK.defaultBlockState(),
            Blocks.LAPIS_ORE.defaultBlockState()
    );
    public static final CobbleGen GREEN = new CobbleGen(
            FluidTags.WATER,
            ModBlock.NOGREEN_BLOCK.get().defaultBlockState(),
            RedOrbBlocks.GREEN_ROCK.get().defaultBlockState(),
            RedOrbBlocks.GREEN_ROCK.get().defaultBlockState()
    );
    public static final CobbleGen RED = new CobbleGen(
            FluidTags.WATER,
            ModBlock.NORED_BLOCK.get().defaultBlockState(),
            Blocks.REDSTONE_BLOCK.defaultBlockState(),
            Blocks.REDSTONE_ORE.defaultBlockState()
    );
    public static final CobbleGen COSMIC_LAVA = new CobbleGen(
            FluidTags.LAVA,
            StarBlocks.COSMIC_BLOCK.get().defaultBlockState(),
            Blocks.DARK_PRISMARINE.defaultBlockState(),
            MoonBlocks.STAR_STONE.get().defaultBlockState()
    );
    public static final CobbleGen COSMIC_WATER = new CobbleGen(
            FluidTags.WATER,
            StarBlocks.COSMIC_BLOCK.get().defaultBlockState(),
            MoonBlocks.BLACK_MOON_ROCK.get().defaultBlockState(),
            MoonBlocks.MOON_ROCK.get().defaultBlockState()
    );
    public static final CobbleGen LAVA_WATER = new CobbleGen(
            FluidTags.WATER,
            Blocks.LAVA.defaultBlockState(),
            Blocks.OBSIDIAN.defaultBlockState(),
            Blocks.COBBLESTONE.defaultBlockState()
    );
    public static final CobbleGen NEGATIVE_WATER = new CobbleGen(
            FluidTags.WATER,
            ModBlock.NEGATIVE_BLOCK.get().defaultBlockState(),
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
              Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "lava_water"),
                LAVA_WATER
        );
        exporter.accept(
              Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "negative_water"),
                NEGATIVE_WATER
        );
    }
}
//? }
