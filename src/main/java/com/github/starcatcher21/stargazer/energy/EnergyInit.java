package com.github.starcatcher21.stargazer.energy;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import team.reborn.energy.api.EnergyStorage;

public class EnergyInit {
    public static void init() {
        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.energyStorage, BlockTypes.STAR_GENERATOR);
        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.energyStorage, BlockTypes.YELLOW_CABLE);
        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.energyStorage, BlockTypes.RED_CABLE);
        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.energyStorage, BlockTypes.BLUE_CABLE);
        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.energyStorage, BlockTypes.PURPLE_CABLE);
        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.energyStorage, BlockTypes.NIGHT_WATCHER);
        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.energyStorage, BlockTypes.STAR_CRUSHER);
    }
}
