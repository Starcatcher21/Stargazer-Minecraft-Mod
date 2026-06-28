package com.github.starcatcher21.stargazer;

import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public class StargazerDataLoader implements SimpleSynchronousResourceReloadListener {
    public static final Identifier id = Identifier.of(Stargazer.MOD_ID, "starforge_data_loader");
    @Override
    public Identifier getFabricId() {
        return id;
    }

    @Override
    public void reload(ResourceManager manager) {
    }
}
