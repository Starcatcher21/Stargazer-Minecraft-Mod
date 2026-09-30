package com.github.starcatcher21.stargazer.energy.neoforge;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.energy.ModEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;

public class EnergyHooksImpl {

    public static void init(IEventBus modEventBus) {
        modEventBus.addListener(EnergyHooksImpl::onRegisterCapabilities);
    }

    public static boolean hasEnergyCapability(Level level, BlockPos pos, Direction side) {
        return level.getCapability(Capabilities.Energy.BLOCK, pos, side) != null;
    }

    public static long pushEnergy(Level level, BlockPos pos, Direction side, ModEnergyStorage source, long maxAmount) {
        EnergyHandler targetHandler = level.getCapability(
                Capabilities.Energy.BLOCK,
                pos.relative(side),
                side.getOpposite()
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
    }    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.STAR_GENERATOR.get(), (be, dir) -> adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.NIGHT_WATCHER.get(), (be, dir) -> adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.STAR_CRUSHER.get(), (be, dir) -> adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.BLUE_CABLE.get(), (be, dir) -> adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.YELLOW_CABLE.get(), (be, dir) -> adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.RED_CABLE.get(), (be, dir) -> adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.PURPLE_CABLE.get(), (be, dir) -> adapt(be.energyStorage));
    }

    public static EnergyHandler adapt(ModEnergyStorage storage) {
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
    }}