package com.github.starcatcher21.stargazer;

import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;

public class StargazerDataLoader implements SimpleSynchronousResourceReloadListener {
    public static final Identifier id = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "starforge_data_loader");
    @Override
    public Identifier getFabricId() {
        return id;
    }

    @Override
    public void onResourceManagerReload(ResourceManager manager) {
    }
}
