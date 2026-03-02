package com.github.starcatcher21.stargazer.mechanics;

import com.github.starcatcher21.stargazer.Stargazer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.texture.PlayerSkinTextureDownloader;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.util.Identifier;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class SkinManager {
    public static final Map<String, SkinTextures> CACHE = new HashMap<>();
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    public static void fetchRemoteSkins() {
        String url = "https://starcatcher21.github.io/minecraft/skins.json";

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(json -> {
                    Map<String, PlayerEntry> data = GSON.fromJson(json, new TypeToken<Map<String, PlayerEntry>>(){}.getType());
                    data.forEach(SkinManager::registerPlayer);
                });
    }

    private static void registerPlayer(String uuid, PlayerEntry entry) {
        Path cacheDir = FabricLoader.getInstance().getGameDir().resolve("cache/custom_skins");

        // 1. Download Skin
        CompletableFuture<Identifier> skinFuture = PlayerSkinTextureDownloader.downloadAndRegisterTexture(
                Identifier.of(Stargazer.MOD_ID, "skins/" + uuid),
                cacheDir.resolve(uuid + "_skin.png"),
                entry.skin,
                false // remap (true if you want to fix legacy 64x32 skins to 64x64)
        );

        // 2. Download Cape
        CompletableFuture<Identifier> capeFuture = PlayerSkinTextureDownloader.downloadAndRegisterTexture(
                Identifier.of(Stargazer.MOD_ID, "capes/" + uuid),
                cacheDir.resolve(uuid + "_cape.png"),
                entry.cape,
                false
                );

        // 3. Combine into SkinTextures when both are ready
        CompletableFuture.allOf(skinFuture, capeFuture).thenAccept(v -> {
            SkinTextures.Model model = (entry.model == 1) ? SkinTextures.Model.SLIM : SkinTextures.Model.WIDE;

            SkinTextures textures = new SkinTextures(
                    skinFuture.join(),
                    null,
                    capeFuture.join(),
                    capeFuture.join(),
                    model,
                    true
            );

            CACHE.put(uuid, textures);
        });
    }

    public static class PlayerEntry {
        public String skin, cape;
        public int model;
    }
}
