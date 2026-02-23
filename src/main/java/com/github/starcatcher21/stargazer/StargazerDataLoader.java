package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.mechanics.Generators.CobbleGen;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObject;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObjectsList;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StargazerDataLoader implements SimpleSynchronousResourceReloadListener {
    public static final Identifier id = Identifier.of(Stargazer.MOD_ID, "starforge_data_loader");
    @Override
    public Identifier getFabricId() {
        return id;
    }

    private static Map<Identifier, CobbleGen> cobbelgenData = new HashMap<>();
    private static Map<Identifier, FallingObject> fallingObjectData = new HashMap<>();
    private static Map<Identifier, FallingObjectsList> fallingObjectsListData = new HashMap<>();

    @Override
    public void reload(ResourceManager manager) {
        cobbelgenData.clear();
        fallingObjectData.clear();

        manager.findResources("stargazer/cobblegen", path -> path.getPath().endsWith(".json"))
                .forEach((id, resource) -> {
                    try (InputStream stream = resource.getInputStream();
                         InputStreamReader reader = new InputStreamReader(stream)) {
                        JsonElement jsonElement = com.google.gson.JsonParser.parseReader(reader);

                        CobbleGen.CODEC.parse(JsonOps.INSTANCE, jsonElement)
                                .resultOrPartial(error -> Stargazer.LOGGER.error("Failed to parse CobbleGen data {}: {}", id, error))
                                .ifPresent(data -> cobbelgenData.put(id, data));
                    } catch (Exception e) {
                        Stargazer.LOGGER.error("Error occurred while loading CobbleGen data {}: {}", id, e.getMessage());
                    }
                });

        manager.findResources("stargazer/falling/objects", path -> path.getPath().endsWith(".json"))
                .forEach((id, resource) -> {
                    try (InputStream stream = resource.getInputStream();
                         InputStreamReader reader = new InputStreamReader(stream)) {
                        JsonElement jsonElement = com.google.gson.JsonParser.parseReader(reader);

                        FallingObject.CODEC.parse(JsonOps.INSTANCE, jsonElement)
                                .resultOrPartial(error -> Stargazer.LOGGER.error("Failed to parse Falling Object data {}: {}", id, error))
                                .ifPresent(data -> fallingObjectData.put(Identifier.of(id.getNamespace(), Arrays.stream(id.getPath().split("/")).toList().getLast().replace(".json", "")), data));

                    } catch (Exception e) {
                        Stargazer.LOGGER.error("Error occurred while loading Falling Object data {}: {}", id, e.getMessage());
                    }
                });
        manager.findResources("stargazer/falling/list", path -> path.getPath().endsWith(".json"))
                .forEach((id, resource) -> {
                    try (InputStream stream = resource.getInputStream();
                         InputStreamReader reader = new InputStreamReader(stream)) {
                        JsonElement jsonElement = JsonParser.parseReader(reader);

                        FallingObjectsList.CODEC.parse(JsonOps.INSTANCE, jsonElement)
                                .resultOrPartial(error -> Stargazer.LOGGER.error("Failed to parse Falling Object List data {}: {}", id, error))
                                .ifPresent(data -> fallingObjectsListData.put(id, data));
                    } catch (Exception e) {
                        Stargazer.LOGGER.error("Error occurred while loading Falling Object List data {}: {}", id, e.getMessage());
                    }
                });
    }

    public static Map<Identifier, CobbleGen> getCobbelgenData() {
        return cobbelgenData;
    }
    public static Map<Identifier, FallingObject> getFallingObjectData() {
        return fallingObjectData;
    }
    public static Map<Identifier, FallingObjectsList> getFallingObjectsListData() {
        return fallingObjectsListData;
    }
}
