package com.github.starcatcher21.stargazer.energy.fabric;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.BlueCableEntity;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.PurpleCableEntity;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.RedCableEntity;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.YellowCableEntity;
import com.github.starcatcher21.stargazer.block.clases.energy.generators.StarGeneratorEntity;
import com.github.starcatcher21.stargazer.block.clases.energy.machines.NightWatcherEntity;
import com.github.starcatcher21.stargazer.block.clases.energy.machines.StarCrusherEntity;
import com.github.starcatcher21.stargazer.energy.ModEnergyStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import team.reborn.energy.api.EnergyStorage;

public class EnergyHooksImpl {

    public static long pushEnergy(Level level, BlockPos pos, Direction direction, ModEnergyStorage source, long maxAmount) {
        EnergyStorage target = EnergyStorage.SIDED.find(level, pos.relative(direction), direction.getOpposite());
        if (target == null) return 0;

        try (Transaction transaction = Transaction.openOuter()) {
            long extracted = source.extractEnergy(maxAmount, true);
            if (extracted <= 0) return 0;

            long inserted = target.insert(extracted, transaction);
            if (inserted > 0) {
                source.extractEnergy(inserted, false);
                transaction.commit();
                return inserted;
            }
        }
        return 0;
    }

    public static void registerEnergyStorages() {
        BlockTypes.STAR_GENERATOR.listen(type ->
            EnergyStorage.SIDED.registerForBlockEntity((be, dir) -> adapt(((StarGeneratorEntity) be).energyStorage), type));
        BlockTypes.NIGHT_WATCHER.listen(type -> 
            EnergyStorage.SIDED.registerForBlockEntity((be, dir) -> adapt(((NightWatcherEntity) be).energyStorage), type));
        BlockTypes.STAR_CRUSHER.listen(type -> 
            EnergyStorage.SIDED.registerForBlockEntity((be, dir) -> adapt(((StarCrusherEntity) be).energyStorage), type));
        BlockTypes.BLUE_CABLE.listen(type ->
                EnergyStorage.SIDED.registerForBlockEntity((be, dir) -> adapt(((BlueCableEntity) be).energyStorage), type));
        BlockTypes.YELLOW_CABLE.listen(type ->
                EnergyStorage.SIDED.registerForBlockEntity((be, dir) -> adapt(((YellowCableEntity) be).energyStorage), type));
        BlockTypes.RED_CABLE.listen(type ->
                EnergyStorage.SIDED.registerForBlockEntity((be, dir) -> adapt(((RedCableEntity) be).energyStorage), type));
        BlockTypes.PURPLE_CABLE.listen(type ->
                EnergyStorage.SIDED.registerForBlockEntity((be, dir) -> adapt(((PurpleCableEntity) be).energyStorage), type));
    }

    private static EnergyStorage adapt(ModEnergyStorage storage) {
        return new EnergyStorage() {
            @Override
            public long insert(long maxAmount, TransactionContext transaction) {
                long inserted = storage.receiveEnergy(maxAmount, false);
                transaction.addCloseCallback((tx, result) -> { if (result.wasAborted()) storage.extractEnergy(inserted, false); });
                return inserted;
            }

            @Override
            public long extract(long maxAmount, TransactionContext transaction) {
                long extracted = storage.extractEnergy(maxAmount, false);
                transaction.addCloseCallback((tx, result) -> { if (result.wasAborted()) storage.receiveEnergy(extracted, false); });
                return extracted;
            }

            @Override public long getAmount() { return storage.getAmount(); }
            @Override public long getCapacity() { return storage.getCapacity(); }
        };
    }

    public static boolean hasEnergyCapability(Level level, BlockPos pos, Direction side) {
        return EnergyStorage.SIDED.find(level, pos, side) != null;
    }
}