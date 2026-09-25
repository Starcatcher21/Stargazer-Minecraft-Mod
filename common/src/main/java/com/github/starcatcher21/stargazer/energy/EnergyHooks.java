package com.github.starcatcher21.stargazer.energy;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public class EnergyHooks {
    @ExpectPlatform
    public static long pushEnergy(Level level, BlockPos pos, Direction direction, ModEnergyStorage source, long maxAmount) {
        throw new AssertionError();
    }
    
    @ExpectPlatform
    public static void registerEnergyStorages() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean hasEnergyCapability(Level level, BlockPos pos, Direction side) {
        throw new AssertionError();
    }

    public static void init() {
        // Common init logic if needed
    }
}