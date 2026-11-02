package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedMoonWelderRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarCrusherRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarforgeRecipe;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;

/**
 * Runtime loader for the mod's recipe JSON, mirroring {@code WorldgenDataBootstrap}.
 *
 * <p>Recipes are not a bootstrapped registry, so we cannot use
 * {@link net.minecraft.data.worldgen.BootstrapContext}. Instead, this class is invoked from a
 * {@code RecipeManager.apply()} mixin after vanilla has parsed its own recipes, and its output is
 * merged into the manager's {@code byName} / {@code byType} maps.
 *
 * <p>Because we run on 1.21.1, we cannot rely on 1.21.2+'s Ingredient string shorthand
 * ({@code "minecraft:stone"} / {@code "#c:ores/gold"}). Every ingredient in the raw JSON is
 * rewritten into the pre-1.21.2 object form ({@code {"item": ...}} / {@code {"tag": ...}}) before
 * being handed to the recipe codec — see {@link #normalizeIngredients(JsonObject)}.
 */
public final class RecipeDataBootstrap {

    private static final Logger LOG = LoggerFactory.getLogger("Stargazer/RecipeDataBootstrap");

    private RecipeDataBootstrap() {}

    /**
     * Enumerates {@code data/stargazer/recipe/<type>/*.json} from the mod jar, normalizes
     * 1.21.2+-style ingredients into the 1.21.1 object form, parses each file with its recipe
     * codec, and returns the resulting recipes keyed by their namespaced id.
     *
     * <p>Recipes that fail to parse (missing items, malformed JSON, …) are logged as warnings
     * and skipped — this method never throws for a single bad file.
     */
    public static Map<ResourceLocation, Recipe<?>> loadAll(HolderLookup.Provider registries) {
        RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, registries);
        Map<ResourceLocation, Recipe<?>> output = new HashMap<>();

        load(ops, output, "starforge",    ShapedStarforgeRecipe.CODEC);
        load(ops, output, "moon_welder",  ShapedMoonWelderRecipe.CODEC);
        load(ops, output, "star_crusher", ShapedStarCrusherRecipe.CODEC);

        LOG.info("Loaded {} custom recipe(s) from data/stargazer/recipe/", output.size());
        return output;
    }

    private static <T extends Recipe<?>> void load(
            RegistryOps<JsonElement> ops,
            Map<ResourceLocation, Recipe<?>> output,
            String directory,
            MapCodec<T> codec) {

        for (String fileName : jsonFileNames("recipe/" + directory)) {
            String id       = fileName.substring(0, fileName.length() - ".json".length());
            String resource = "/data/stargazer/recipe/" + directory + "/" + fileName;

            try (var stream = RecipeDataBootstrap.class.getResourceAsStream(resource)) {
                if (stream == null) {
                    LOG.warn("Missing recipe resource: {}", resource);
                    continue;
                }

                try (Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                    JsonElement json = JsonParser.parseReader(reader);

                    if (!json.isJsonObject()) {
                        LOG.warn("Skipping {}: not a JSON object", resource);
                        continue;
                    }

                    JsonObject root = json.getAsJsonObject();
                    // RecipeSerializer's MapCodec doesn't include the "type" discriminator —
                    // vanilla reads it *before* dispatching to the codec, so strip it here.
                    root.remove("type");
                    normalizeIngredients(root);

                    // resultOrPartial: logs the DataResult.Error as a warning and returns the
                    // partial value if one is available, or Optional.empty() on hard failure.
                    T recipe = codec.codec()
                            .parse(ops, root)
                            .resultOrPartial(err -> LOG.warn("Skipping {}: {}", resource, err))
                            .orElse(null);

                    if (recipe == null) continue;

                    output.put(
                            ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, id),
                            recipe);
                }
            } catch (IOException e) {
                LOG.warn("Could not read recipe resource: {}", resource, e);
            } catch (RuntimeException e) {
                LOG.warn("Unexpected error while loading {}", resource, e);
            }
        }
    }

    private static void normalizeIngredients(JsonObject root) {
        normalizeAtLevel(root);

        // Defensive: also normalize a nested "raw" object if the JSON ever puts it there
        JsonElement rawEl = root.get("raw");
        if (rawEl != null && rawEl.isJsonObject()) {
            normalizeAtLevel(rawEl.getAsJsonObject());
        }
    }

    private static void normalizeAtLevel(JsonObject obj) {
        // Moon Welder / Star Crusher: item1, item2
        for (String field : new String[]{ "item1", "item2" }) {
            if (obj.has(field)) {
                obj.add(field, normalizeIngredient(obj.get(field)));
            }
        }

        // Starforge: key is { char -> Ingredient }
        JsonElement keyEl = obj.get("key");
        if (keyEl != null && keyEl.isJsonObject()) {
            JsonObject keyObj = keyEl.getAsJsonObject();
            for (String symbol : new ArrayList<>(keyObj.keySet())) {
                keyObj.add(symbol, normalizeIngredient(keyObj.get(symbol)));
            }
        }
    }

    private static JsonElement normalizeIngredient(JsonElement el) {
        if (el == null || el.isJsonNull()) return el;

        // String shorthand → object form
        if (el.isJsonPrimitive() && el.getAsJsonPrimitive().isString()) {
            String s = el.getAsString();
            JsonObject out = new JsonObject();
            if (s.startsWith("#")) out.addProperty("tag",  s.substring(1));
            else                   out.addProperty("item", s);
            return out;
        }

        // Array → normalize each element
        if (el.isJsonArray()) {
            JsonArray arr = new JsonArray();
            for (JsonElement inner : el.getAsJsonArray()) {
                arr.add(normalizeIngredient(inner));
            }
            return arr;
        }

        // Already an object (or unexpected) → leave as-is
        return el;
    }

    /* ================================================================
     *  Jar / directory resource enumeration
     * ================================================================ */

    private static List<String> jsonFileNames(String directory) {
        String resource = "/data/stargazer/" + directory;
        var url = RecipeDataBootstrap.class.getResource(resource);
        if (url == null) {
            LOG.warn("Recipe directory not found on classpath: {}", resource);
            return List.of();
        }

        try {
            if ("jar".equals(url.getProtocol())) {
                JarURLConnection connection = (JarURLConnection) url.openConnection();
                try (JarFile jarFile = new JarFile(
                        Path.of(connection.getJarFileURL().toURI()).toFile())) {
                    String prefix = resource.substring(1) + "/";
                    return jarFile.stream()
                            .map(e -> e.getName())
                            .filter(n -> n.startsWith(prefix) && n.endsWith(".json"))
                            // Ignore nested sub-directories — keep the enumeration flat
                            .filter(n -> n.indexOf('/', prefix.length()) == -1)
                            .map(n -> n.substring(n.lastIndexOf('/') + 1))
                            .sorted()
                            .toList();
                }
            }

            try (var paths = Files.list(Path.of(url.toURI()))) {
                return paths
                        .filter(p -> Files.isRegularFile(p))
                        .filter(p -> p.getFileName().toString().endsWith(".json"))
                        .map(p -> p.getFileName().toString())
                        .sorted()
                        .toList();
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Could not list recipe resources: " + resource, e);
        }
    }
}
