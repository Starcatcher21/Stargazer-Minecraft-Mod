package com.github.starcatcher21.stargazer.block;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.fluids.DreamFlowable;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

public class ModFluids {
    public static final FlowingFluid DREAM_FLOWING = register("dream_flowing", new DreamFlowable.Flowing());
    public static final FlowingFluid DREAM = register("dream", new DreamFlowable.Still());

    private static <T extends Fluid> T register(String id, T value) {
        return Registry.register(BuiltInRegistries.FLUID, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, id), value);
    }

    static {
        for (Fluid fluid : BuiltInRegistries.FLUID) {
            for (FluidState fluidState : fluid.getStateDefinition().getPossibleStates()) {
                Fluid.FLUID_STATE_REGISTRY.add(fluidState);
            }
        }
    }
}
