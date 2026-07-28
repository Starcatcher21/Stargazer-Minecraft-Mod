package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.CustomWorlds;
import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.datagen.DataProviders.FallingObjectListDataProvider;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObjectDayState;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObjectsList;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class FallingObjectListProvider extends FallingObjectListDataProvider {
    public FallingObjectListProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final FallingObjectsList OVERWORLD = new FallingObjectsList(
            Level.OVERWORLD,
            List.of(
                    Holder.direct(FallingObjectProvider.YELLOW_STAR),
                    Holder.direct(FallingObjectProvider.BLUE_STAR),
                    Holder.direct(FallingObjectProvider.RED_STAR),
                    Holder.direct(FallingObjectProvider.PURPLE_STAR),
                    Holder.direct(FallingObjectProvider.MOON_ROCK),
                    Holder.direct(FallingObjectProvider.COMET_FRAGMENT),
                    Holder.direct(FallingObjectProvider.STAR_BOOK)
            ),
            List.of(20, 10, 10, 5, 10, 5, 1),
            Optional.empty(),
            Optional.of(FallingObjectDayState.Night)
    );

    public static final FallingObjectsList CHESS = new FallingObjectsList(
            CustomWorlds.CHESS,
            List.of(
                    Holder.direct(FallingObjectProvider.YELLOW_STAR),
                    Holder.direct(FallingObjectProvider.BLUE_STAR),
                    Holder.direct(FallingObjectProvider.RED_STAR),
                    Holder.direct(FallingObjectProvider.PURPLE_STAR),
                    Holder.direct(FallingObjectProvider.WHITE_BRICK),
                    Holder.direct(FallingObjectProvider.BLACK_BRICK),
                    Holder.direct(FallingObjectProvider.COMET_FRAGMENT)
            ),
            List.of(20, 10, 10, 5, 5, 5, 5),
            Optional.empty(),
            Optional.of(FallingObjectDayState.Night)
    );

    public static final FallingObjectsList COSMIC = new FallingObjectsList(
            CustomWorlds.COSMIC,
            List.of(
                    Holder.direct(FallingObjectProvider.YELLOW_STAR),
                    Holder.direct(FallingObjectProvider.BLUE_STAR),
                    Holder.direct(FallingObjectProvider.RED_STAR),
                    Holder.direct(FallingObjectProvider.PURPLE_STAR),
                    Holder.direct(FallingObjectProvider.RED_ROCK),
                    Holder.direct(FallingObjectProvider.COMET_FRAGMENT)
            ),
            List.of(20, 10, 10, 5, 10, 5),
            Optional.of(0),
            Optional.of(FallingObjectDayState.Both)
    );

    public static final FallingObjectsList RED_ORB = new FallingObjectsList(
            CustomWorlds.RED_ORB,
            List.of(
                    Holder.direct(FallingObjectProvider.YELLOW_STAR),
                    Holder.direct(FallingObjectProvider.BLUE_STAR),
                    Holder.direct(FallingObjectProvider.RED_STAR),
                    Holder.direct(FallingObjectProvider.PURPLE_STAR),
                    Holder.direct(FallingObjectProvider.BLACK_STAR),
                    Holder.direct(FallingObjectProvider.COMET_FRAGMENT)
            ),
            List.of(20, 10, 10, 5, 1, 5),
            Optional.of(0),
            Optional.of(FallingObjectDayState.Both)
    );

    @Override
    protected void addFallingObjectList(BiConsumer<Identifier, FallingObjectsList> exporter, HolderLookup.Provider registries) {
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "overworld"),
                OVERWORLD
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "chess"),
                CHESS
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic"),
                COSMIC
        );
        exporter.accept(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "red_orb"),
                RED_ORB
        );
    }
}