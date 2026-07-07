package com.github.starcatcher21.stargazer.energy;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.BlueCableEntity;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.PurpleCableEntity;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.RedCableEntity;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.YellowCableEntity;
import com.github.starcatcher21.stargazer.block.clases.energy.generators.StarGeneratorEntity;
import com.github.starcatcher21.stargazer.block.clases.energy.machines.NightWatcherEntity;
import com.github.starcatcher21.stargazer.block.clases.energy.machines.StarCrusherEntity;
import team.reborn.energy.api.EnergyStorage;

public class EnergyInit {
    public static void init() {
        EnergyStorage.SIDED.registerForBlockEntities((blockEntity, direction) -> {
            return switch (blockEntity) {
                case StarGeneratorEntity block -> block.energyStorage;
                case YellowCableEntity block -> block.energyStorage;
                case NightWatcherEntity block -> block.energyStorage;
                case PurpleCableEntity block -> block.energyStorage;
                case BlueCableEntity block -> block.energyStorage;
                case RedCableEntity block -> block.energyStorage;
                case StarCrusherEntity block -> block.energyStorage;
                default -> null;
            };
        }, BlockTypes.STAR_GENERATOR, BlockTypes.YELLOW_CABLE, BlockTypes.RED_CABLE, BlockTypes.BLUE_CABLE, BlockTypes.PURPLE_CABLE, BlockTypes.NIGHT_WATCHER, BlockTypes.STAR_CRUSHER);
    }
}
