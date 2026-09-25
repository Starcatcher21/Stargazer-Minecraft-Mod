package com.github.starcatcher21.stargazer.fabric;

import com.github.starcatcher21.stargazer.StargazerClient;
import net.fabricmc.api.ClientModInitializer;

public final class StargazerFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        StargazerClient.initClient();
        FabricSkyStarRenderer.init();
    }
}
