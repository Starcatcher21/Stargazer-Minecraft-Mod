package com.github.starcatcher21.stargazer.fabric;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.StargazerClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;

public final class StargazerFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        StargazerClient.initClient();
        FabricSkyStarRenderer.init();
    }
}
