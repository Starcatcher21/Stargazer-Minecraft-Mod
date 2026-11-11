package com.github.starcatcher21.stargazer.energy;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.starlib.energy.EnergyHooks;
//? if neoforge {
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
//? }

public class EnergyCapabilities {

    //? if neoforge {
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        //? if >= 26.1.2 {
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.STAR_GENERATOR.get(), (be, dir) -> EnergyHooks.adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.NIGHT_WATCHER.get(), (be, dir) -> EnergyHooks.adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.STAR_CRUSHER.get(),   (be, dir) -> EnergyHooks.adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.BLUE_CABLE.get(),    (be, dir) -> EnergyHooks.adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.YELLOW_CABLE.get(),  (be, dir) -> EnergyHooks.adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.RED_CABLE.get(),     (be, dir) -> EnergyHooks.adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.Energy.BLOCK, BlockTypes.PURPLE_CABLE.get(),  (be, dir) -> EnergyHooks.adapt(be.energyStorage));
        //?} else {
        /*event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, BlockTypes.STAR_GENERATOR.get(), (be, dir) -> EnergyHooks.adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, BlockTypes.NIGHT_WATCHER.get(), (be, dir) -> EnergyHooks.adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, BlockTypes.STAR_CRUSHER.get(),   (be, dir) -> EnergyHooks.adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, BlockTypes.BLUE_CABLE.get(),    (be, dir) -> EnergyHooks.adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, BlockTypes.YELLOW_CABLE.get(),  (be, dir) -> EnergyHooks.adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, BlockTypes.RED_CABLE.get(),     (be, dir) -> EnergyHooks.adapt(be.energyStorage));
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, BlockTypes.PURPLE_CABLE.get(),  (be, dir) -> EnergyHooks.adapt(be.energyStorage));
        *///?}
    }
    //? }

    public static void registerEnergyStorages() {
    }

    public static void init(/*? if neoforge {*/ IEventBus modEventBus /*?}*/) {
        //? if neoforge {
        modEventBus.addListener(EnergyCapabilities::onRegisterCapabilities);
        //? }
    }
}
