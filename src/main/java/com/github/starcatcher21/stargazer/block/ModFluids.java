package com.github.starcatcher21.stargazer.block;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.fluids.DreamFlowable;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFluids {
    public static final FlowableFluid DREAM_FLOWING = register("dream_flowing", new DreamFlowable.Flowing());
    public static final FlowableFluid DREAM = register("dream", new DreamFlowable.Still());

    private static <T extends Fluid> T register(String id, T value) {
        return Registry.register(Registries.FLUID, Identifier.of(Stargazer.MOD_ID, id), value);
    }

    static {
        for (Fluid fluid : Registries.FLUID) {
            for (FluidState fluidState : fluid.getStateManager().getStates()) {
                Fluid.STATE_IDS.add(fluidState);
            }
        }
    }
}
