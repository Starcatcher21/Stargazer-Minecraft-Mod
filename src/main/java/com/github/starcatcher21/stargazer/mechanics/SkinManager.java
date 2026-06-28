package com.github.starcatcher21.stargazer.mechanics;

import com.github.starcatcher21.stargazer.Stargazer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.PlayerSkinTextureDownloader;
import net.minecraft.entity.player.PlayerSkinType;
import net.minecraft.entity.player.SkinTextures;
import net.minecraft.util.AssetInfo;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.net.Proxy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class SkinManager {
    public static final Map<String, SkinTextures> CACHE = new HashMap<>();
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    // Explicitly instantiation allowed in 1.21.11 via TextureManager & single thread execution
    private static final PlayerSkinTextureDownloader pstd = new PlayerSkinTextureDownloader(
            Proxy.NO_PROXY,
            MinecraftClient.getInstance().getTextureManager(),
            MinecraftClient.getInstance()
    );

    public static void fetchRemoteSkins() {
        String url = "https://starcatcher21.github.io/minecraft/skins.json";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(json -> {
                    try {
                        Map<String, PlayerEntry> data = GSON.fromJson(json, new TypeToken<Map<String, PlayerEntry>>(){}.getType());
                        if (data != null) {
                            data.forEach(SkinManager::registerPlayer);
                        }
                    } catch (Exception e) {
                        Stargazer.LOGGER.error("Failed to parse remote skins JSON", e);
                    }
                });
    }

    private static void registerPlayer(String uuid, PlayerEntry entry) {
        Path cacheDir = FabricLoader.getInstance().getGameDir().resolve("cache/custom_skins");
        try {
            Files.createDirectories(cacheDir);
        } catch (IOException e) {
            Stargazer.LOGGER.error("Failed to create skin cache directory", e);
            return;
        }

        Identifier skinIdentifier = Identifier.of(Stargazer.MOD_ID, "skins/" + uuid.toLowerCase());
        Identifier capeIdentifier = Identifier.of(Stargazer.MOD_ID, "capes/" + uuid.toLowerCase());

        CompletableFuture<AssetInfo.TextureAsset> skinFuture = pstd.downloadAndRegisterTexture(
                skinIdentifier,
                cacheDir.resolve(uuid + "_skin.png"),
                entry.skin,
                false // remap legacy skins
        );

        CompletableFuture<AssetInfo.TextureAsset> capeFuture = (entry.cape != null && !entry.cape.isEmpty()) ?
                pstd.downloadAndRegisterTexture(capeIdentifier, cacheDir.resolve(uuid + "_cape.png"), entry.cape, false) :
                CompletableFuture.completedFuture(null);

        PlayerSkinType model = (entry.model == 1) ? PlayerSkinType.SLIM : PlayerSkinType.WIDE;

        CompletableFuture.runAsync(() -> {
            SkinTextures textures = new SkinTextures(
                    skinFuture.join(),    // Skin Asset
                    capeFuture.join(),    // Cape Aasset
                    capeFuture.join(),    // Elytra Asset
                    model,                // PlayerSkinType enum
                    true                  // Secure flag
            );

            CACHE.put(uuid, textures);
        });
    }

    public static class PlayerEntry {
        public String skin, cape;
        public int model;
    }
}