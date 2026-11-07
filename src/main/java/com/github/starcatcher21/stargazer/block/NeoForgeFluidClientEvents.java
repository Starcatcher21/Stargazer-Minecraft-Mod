//? if neoforge {
package com.github.starcatcher21.stargazer.block;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.resources.ResourceLocation;
//? if >= 26.2 {
/*import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.neoforged.neoforge.client.event.RegisterFluidModelsEvent;
*///? } else {
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
//? }

public class NeoForgeFluidClientEvents {

    //? if >= 26.2 {
    /*public static void registerFluidModels(RegisterFluidModelsEvent event) {
        event.register(
                new FluidModel.Unbaked(
                        new Material(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "block/dream_still")),
                        new Material(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "block/dream_flow")),
                        null,
                        null
                ),
                ModFluids.DREAM_STILL,
                ModFluids.DREAM_FLOWING
        );
    }
    *///? } else {
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getFlowingTexture() {
                return ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "block/dream_flow");
            }

            @Override
            public ResourceLocation getStillTexture() {
                return ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "block/dream_still");
            }
        }, ModFluids.DREAM_STILL.get().getFluidType());
    }
    //? }
}
//? }
