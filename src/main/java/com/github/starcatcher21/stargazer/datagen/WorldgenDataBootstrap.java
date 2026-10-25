package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.worldgen.BiomeReg;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.jar.JarFile;

public final class WorldgenDataBootstrap {
    private WorldgenDataBootstrap() {}

    public static RegistrySetBuilder createRegistrySetBuilder() {
        RegistrySetBuilder registryBuilder = new RegistrySetBuilder();
        addTo(registryBuilder);
        return registryBuilder;
    }

    public static void addTo(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, WorldgenDataBootstrap::configuredFeatures);
        registryBuilder.add(Registries.PLACED_FEATURE, WorldgenDataBootstrap::placedFeatures);
        registryBuilder.add(Registries.BIOME, WorldgenDataBootstrap::biomes);
        registryBuilder.add(Registries.CONFIGURED_CARVER, context -> registerJsonEntries(
                context, Registries.CONFIGURED_CARVER, ConfiguredWorldCarver.DIRECT_CODEC,
                "configured_carver", value -> value));
        registryBuilder.add(Registries.DIMENSION_TYPE, context -> registerJsonEntries(
                context, Registries.DIMENSION_TYPE, DimensionType.DIRECT_CODEC,
                "dimension_type", value -> value, false));
        registryBuilder.add(Registries.LEVEL_STEM, context -> registerJsonEntries(
                context, Registries.LEVEL_STEM, LevelStem.CODEC,
                "dimension", value -> value, false));
        registryBuilder.add(Registries.NOISE, context -> registerJsonEntries(
                context, Registries.NOISE, NormalNoise.NoiseParameters.DIRECT_CODEC,
                "noise", value -> value));
        registryBuilder.add(Registries.DENSITY_FUNCTION, context -> registerJsonEntries(
                context, Registries.DENSITY_FUNCTION, densityFunctionCodec(),
                "density_function", value -> value));
        registryBuilder.add(Registries.NOISE_SETTINGS, context -> registerJsonEntries(
                context, Registries.NOISE_SETTINGS, NoiseGeneratorSettings.DIRECT_CODEC,
                "noise_settings", value -> value));
        registryBuilder.add(Registries.STRUCTURE, context -> registerJsonEntries(
                context, Registries.STRUCTURE, Structure.DIRECT_CODEC,
                "structure", value -> value));
        registryBuilder.add(Registries.STRUCTURE_SET, context -> registerJsonEntries(
                context, Registries.STRUCTURE_SET, StructureSet.DIRECT_CODEC,
                "structure_set", value -> value));
        registryBuilder.add(Registries.TEMPLATE_POOL, context -> registerJsonEntries(
                context, Registries.TEMPLATE_POOL, StructureTemplatePool.DIRECT_CODEC,
                "template_pool", value -> value));
    }

    private static Codec<DensityFunction> densityFunctionCodec() {
        //? if >= 26.2 {
        return net.minecraft.world.level.levelgen.DensityFunctions.DIRECT_CODEC;
        //?} else {
        /*return DensityFunction.DIRECT_CODEC;
        *///?}
    }

    private static void configuredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        registerJsonEntries(context, Registries.CONFIGURED_FEATURE, ConfiguredFeature.DIRECT_CODEC, "configured_feature", value -> value);
    }

    private static void placedFeatures(BootstrapContext<PlacedFeature> context) {
        registerJsonEntries(context, Registries.PLACED_FEATURE, PlacedFeature.DIRECT_CODEC, "placed_feature", value -> {
            if (value.placement().stream().anyMatch(BiomeFilter.class::isInstance)) {
                return value;
            }
            var placement = new java.util.ArrayList<>(value.placement());
            placement.add(BiomeFilter.biome());
            return new PlacedFeature(value.feature(), placement);
        });
    }

    private static void biomes(BootstrapContext<Biome> context) {
        RegistryOps<JsonElement> ops = registryOps(context);
        for (ResourceKey<Biome> key : BiomeReg.ALL) {
            context.register(key, loadBiome(key.identifier().getPath() + ".json", ops));
        }
    }

    private static <T> void registerJsonEntries(
            BootstrapContext<T> context,
            ResourceKey<? extends Registry<T>> registry,
            Codec<T> codec,
            String directory,
            UnaryOperator<T> normalizer) {
        registerJsonEntries(context, registry, codec, directory, normalizer, true);
    }

    private static <T> void registerJsonEntries(
            BootstrapContext<T> context,
            ResourceKey<? extends Registry<T>> registry,
            Codec<T> codec,
            String directory,
            UnaryOperator<T> normalizer,
            boolean worldgenDirectory) {
        RegistryOps<JsonElement> ops = registryOps(context);
        for (String fileName : jsonFileNames(directory, worldgenDirectory)) {
            String id = fileName.substring(0, fileName.length() - ".json".length());
            ResourceKey<T> key = ResourceKey.create(
                    registry, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, id));
            String resource = "/data/stargazer/" + (worldgenDirectory ? "worldgen/" : "") + directory + "/" + fileName;
            try (var stream = WorldgenDataBootstrap.class.getResourceAsStream(resource)) {
                if (stream == null) {
                    throw new IllegalStateException("Missing worldgen data resource: " + resource);
                }
                try (Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                    JsonElement json = JsonParser.parseReader(reader);
                    //? if < 26.2 {
                    /*if (directory.equals("dimension_type")) {
                        json = legacyDimensionType(json.getAsJsonObject(), id);
                    }
                    *///?}
                    T value = codec.parse(ops, json)
                            .getPartialOrThrow(error -> new RuntimeException("Failed to parse " + resource + ": " + error));
                    context.register(key, normalizer.apply(value));
                }
            } catch (IOException e) {
                throw new RuntimeException("Could not read worldgen data: " + resource, e);
            }
        }
    }

    //? if < 26.2 {
    /*private static JsonElement legacyDimensionType(com.google.gson.JsonObject current, String id) {
        com.google.gson.JsonObject legacy = new com.google.gson.JsonObject();
        boolean fixedTime = current.has("has_fixed_time") && current.get("has_fixed_time").getAsBoolean();
        if (fixedTime) {
            legacy.addProperty("fixed_time", 18000L);
        }
        legacy.addProperty("has_skylight", current.get("has_skylight").getAsBoolean());
        legacy.addProperty("has_ceiling", current.get("has_ceiling").getAsBoolean());
        legacy.addProperty("ultrawarm", false);
        legacy.addProperty("natural", false);
        legacy.addProperty("coordinate_scale", current.get("coordinate_scale").getAsDouble());
        legacy.addProperty("bed_works", false);
        legacy.addProperty("respawn_anchor_works", false);
        legacy.addProperty("min_y", current.get("min_y").getAsInt());
        legacy.addProperty("height", current.get("height").getAsInt());
        legacy.addProperty("logical_height", current.get("logical_height").getAsInt());
        legacy.add("infiniburn", current.get("infiniburn").deepCopy());
        legacy.addProperty("effects", Stargazer.MOD_ID + ":" + id);
        legacy.addProperty("ambient_light", current.get("ambient_light").getAsFloat());
        legacy.addProperty("piglin_safe", false);
        legacy.addProperty("has_raids", false);
        legacy.add("monster_spawn_light_level", current.get("monster_spawn_light_level").deepCopy());
        legacy.add("monster_spawn_block_light_limit", current.get("monster_spawn_block_light_limit").deepCopy());
        return legacy;
    }
    *///?}

    private static List<String> jsonFileNames(String registryDirectory, boolean worldgenDirectory) {
        String resource = "/data/stargazer/" + (worldgenDirectory ? "worldgen/" : "") + registryDirectory;
        var url = WorldgenDataBootstrap.class.getResource(resource);
        if (url == null) {
            return List.of();
        }
        try {
            if (url.getProtocol().equals("jar")) {
                JarURLConnection connection = (JarURLConnection) url.openConnection();
                try (JarFile jarFile = new JarFile(Path.of(connection.getJarFileURL().toURI()).toFile())) {
                    String prefix = resource.substring(1) + "/";
                    return jarFile.stream()
                            .map(entry -> entry.getName())
                            .filter(name -> name.startsWith(prefix) && name.endsWith(".json"))
                            .map(name -> name.substring(name.lastIndexOf('/') + 1))
                            .sorted()
                            .toList();
                }
            }
            try (var paths = Files.list(Path.of(url.toURI()))) {
                return paths.filter(path -> path.getFileName().toString().endsWith(".json"))
                        .map(path -> path.getFileName().toString())
                        .sorted()
                        .toList();
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Could not list worldgen data resources: " + resource, e);
        }
    }

    private static <T> RegistryOps.RegistryInfo<T> registryInfo(
            BootstrapContext<?> context, ResourceKey<? extends Registry<? extends T>> key) {
        HolderGetter<T> getter = context.lookup(key);
        return new RegistryOps.RegistryInfo<>(new HolderOwner<T>() {}, getter, Lifecycle.stable());
    }

    private static RegistryOps<JsonElement> registryOps(BootstrapContext<?> context) {
        RegistryOps.RegistryInfoLookup lookups = new RegistryOps.RegistryInfoLookup() {
            @Override
            @SuppressWarnings("unchecked")
            public <T> Optional<RegistryOps.RegistryInfo<T>> lookup(
                    ResourceKey<? extends Registry<? extends T>> registryKey) {
                return Optional.of((RegistryOps.RegistryInfo<T>) registryInfo(context, registryKey));
            }
        };
        return RegistryOps.create(JsonOps.INSTANCE, lookups);
    }

    private static Biome loadBiome(String fileName, RegistryOps<JsonElement> ops) {
        String resource = "/data/stargazer/worldgen/biome/" + fileName;
        try (var stream = WorldgenDataBootstrap.class.getResourceAsStream(resource)) {
            if (stream == null) {
                throw new IllegalStateException("Missing biome data resource: " + resource);
            }
            try (Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                JsonElement json = JsonParser.parseReader(reader);
                //? if < 26.2 {
                /*JsonElement carvers = json.getAsJsonObject().get("carvers");
                if (carvers != null && carvers.isJsonArray() && carvers.getAsJsonArray().isEmpty()) {
                    json.getAsJsonObject().add("carvers", new com.google.gson.JsonObject());
                }
                var effects = json.getAsJsonObject().getAsJsonObject("effects");
                if (!effects.has("sky_color")) {
                    effects.addProperty("sky_color", 7907327);
                }
                if (!effects.has("fog_color")) {
                    effects.addProperty("fog_color", 12638463);
                }
                if (!effects.has("water_fog_color")) {
                    effects.addProperty("water_fog_color", 329011);
                }
                *///?}
                return Biome.DIRECT_CODEC.parse(ops, json)
                        .getPartialOrThrow(error -> new RuntimeException("Failed to parse biome " + resource + ": " + error));
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read biome data resource: " + resource, e);
        }
    }
}
