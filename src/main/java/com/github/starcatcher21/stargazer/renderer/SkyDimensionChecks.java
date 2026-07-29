package com.github.starcatcher21.stargazer.renderer;

import com.github.starcatcher21.stargazer.CustomWorlds;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.trading.TradeSets;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.Optional;

public final class SkyDimensionChecks {
    private SkyDimensionChecks() {
    }

    public static boolean isStargazerSkyDimension(Level level) {
        Optional<ResourceKey<DimensionType>> dimensionType = level.dimensionTypeRegistration().unwrapKey();

        return dimensionType.isPresent()
                && (dimensionType.get().equals(CustomWorlds.COSMIC_TYPE)
                || dimensionType.get().equals(CustomWorlds.RED_ORB_TYPE)
                || dimensionType.get().equals(CustomWorlds.WANDER_TYPE));
    }
}
