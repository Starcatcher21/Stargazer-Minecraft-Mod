package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.RedOrbBlocks;
import com.github.starcatcher21.starlib.datagen.provider.FallingObjectDataProvider;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.particle.Particles;
import com.github.starcatcher21.starlib.mechanics.star.FallingObject;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class FallingObjectProvider extends FallingObjectDataProvider {
    public FallingObjectProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final FallingObject BLACK_BRICK = new FallingObject(
            ModItems.BLACK_BRICK.builtInRegistryHolder(),
            ParticleTypes.CAMPFIRE_COSY_SMOKE,
            ParticleTypes.CAMPFIRE_COSY_SMOKE,
            200,
            0.06f
    );

    public static final FallingObject BLACK_STAR = new FallingObject(
            ModItems.DARKSTAR.builtInRegistryHolder(),
            ParticleTypes.CAMPFIRE_COSY_SMOKE,
            ParticleTypes.CAMPFIRE_COSY_SMOKE,
            200,
            0.06f
    );
    public static final FallingObject END_STAR = new FallingObject(
            ModItems.END_STAR.builtInRegistryHolder(),
            ParticleTypes.END_ROD,
            ParticleTypes.END_ROD,
            200,
            0.06f
    );
    public static final FallingObject BLUE_STAR = new FallingObject(
            ModItems.BLUE_STAR.builtInRegistryHolder(),
            Particles.BLUE_STAR,
            Particles.BLUE_STAR,
            200,
            0.06f
    );
    public static final FallingObject RED_STAR = new FallingObject(
            ModItems.RED_STAR.builtInRegistryHolder(),
            Particles.RED_STAR,
            Particles.RED_STAR,
            200,
            0.06f
    );
    public static final FallingObject PURPLE_STAR = new FallingObject(
            ModItems.PURPLE_STAR.builtInRegistryHolder(),
            Particles.PURPLE_STAR,
            Particles.PURPLE_STAR,
            200,
            0.06f
    );
    public static final FallingObject YELLOW_STAR = new FallingObject(
            ModItems.YELLOW_STAR.builtInRegistryHolder(),
            Particles.YELLOW_STAR,
            Particles.YELLOW_STAR,
            200,
            0.06f
    );
    public static final FallingObject COMET_FRAGMENT = new FallingObject(
            ModItems.COMET_FRAGMENT.builtInRegistryHolder(),
            ParticleTypes.CAMPFIRE_COSY_SMOKE,
            ParticleTypes.CAMPFIRE_COSY_SMOKE,
            200,
            0.06f
    );
    public static final FallingObject MOON_ROCK = new FallingObject(
            MoonBlocks.MOON_ROCK.asItem().builtInRegistryHolder(),
            ParticleTypes.CAMPFIRE_COSY_SMOKE,
            ParticleTypes.CAMPFIRE_COSY_SMOKE,
            200,
            0.06f
    );
    public static final FallingObject RED_ROCK = new FallingObject(
            RedOrbBlocks.RED_ROCK.asItem().builtInRegistryHolder(),
            ParticleTypes.CAMPFIRE_COSY_SMOKE,
            ParticleTypes.CAMPFIRE_COSY_SMOKE,
            200,
            0.06f
    );
    public static final FallingObject STAR_BOOK = new FallingObject(
            ModItems.STAR_BOOK.builtInRegistryHolder(),
            ParticleTypes.CAMPFIRE_COSY_SMOKE,
            ParticleTypes.CAMPFIRE_COSY_SMOKE,
            200,
            0.06f
    );
    public static final FallingObject WHITE_BRICK = new FallingObject(
            ModItems.WHITE_BRICK.builtInRegistryHolder(),
            ParticleTypes.CAMPFIRE_COSY_SMOKE,
            ParticleTypes.CAMPFIRE_COSY_SMOKE,
            200,
            0.06f
    );

    @Override
    protected void addFallingObject(BiConsumer<Identifier, FallingObject> exporter, HolderLookup.Provider registries) {
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "black_brick"),
                BLACK_BRICK
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "black_star"),
                BLACK_STAR
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "blue_star"),
                BLUE_STAR
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "red_star"),
                RED_STAR
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "purple_star"),
                PURPLE_STAR
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "yellow_star"),
                YELLOW_STAR
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "comet_fragment"),
                COMET_FRAGMENT
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "moon_rock"),
                MOON_ROCK
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "red_rock"),
                RED_ROCK
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "star_book"),
                STAR_BOOK
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "white_brick"),
                WHITE_BRICK
        );
    }
}