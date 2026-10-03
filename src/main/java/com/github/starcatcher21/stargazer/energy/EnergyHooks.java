package com.github.starcatcher21.stargazer.energy;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
//? if neoforge {
/*import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
*///? } else {
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import team.reborn.energy.api.EnergyStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
//? }

public class EnergyHooks {
    public static long pushEnergy(Level level, BlockPos pos, Direction direction, ModEnergyStorage source, long maxAmount) {
        //? if neoforge {
        /*EnergyHandler targetHandler = level.getCapability(
                Capabilities.Energy.BLOCK,
                pos.relative(direction),
                direction.getOpposite()
        );
        if (targetHandler == null) return 0L;

        long simulateExtract = source.extractEnergy(maxAmount, true);
        if (simulateExtract <= 0L) return 0L;

        int toInsert = (int) Math.min(simulateExtract, Integer.MAX_VALUE);

        long inserted = 0L;
        try (Transaction tx = Transaction.openRoot()) {
            inserted = targetHandler.insert(toInsert, tx);
            if (inserted > 0L) {
                source.extractEnergy(inserted, false);
                tx.commit();
            }
        }
        return inserted;
        *///? } else {
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
        //? }

    }
    
    public static void registerEnergyStorages() {
        //? if fabric {

        //? }
    }

    public static boolean hasEnergyCapability(Level level, BlockPos pos, Direction side) {
        //? if neoforge {
        /*return level.getCapability(Capabilities.Energy.BLOCK, pos, side) != null;
        *///? } else {
        return EnergyStorage.SIDED.find(level, pos, side) != null;
        //? }
    }

    //? if neoforge {
    /*public static EnergyHandler adapt(ModEnergyStorage storage) {
        return new EnergyHandler() {
            @Override
            public long getCapacityAsLong() {
                return storage.getCapacity();
            }

            @Override
            public int insert(int i, TransactionContext transactionContext) {
                return Math.toIntExact(storage.receiveEnergy(i, false));
            }

            @Override
            public int extract(int i, TransactionContext transactionContext) {
                return Math.toIntExact(storage.extractEnergy(i, false));
            }

            @Override
            public long getAmountAsLong() {
                return storage.getAmount();
            }
        };
    }
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.STAR_GENERATOR.get(), (be, dir) -> adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.NIGHT_WATCHER.get(), (be, dir) -> adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.STAR_CRUSHER.get(), (be, dir) -> adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.BLUE_CABLE.get(), (be, dir) -> adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.YELLOW_CABLE.get(), (be, dir) -> adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.RED_CABLE.get(), (be, dir) -> adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.PURPLE_CABLE.get(), (be, dir) -> adapt(be.energyStorage));
    }

    *///? } else {
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

    //? }

    public static void init(/*? if neoforge {*//*IEventBus modEventBus *//*? } */) {
        //? if neoforge {
        /*modEventBus.addListener(EnergyHooks::onRegisterCapabilities);
        *///? }
    }
}
