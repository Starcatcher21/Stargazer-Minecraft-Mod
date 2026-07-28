package com.github.starcatcher21.stargazer.datagen.DataProviders;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObject;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagFile;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Util;
import net.minecraft.util.filefix.fixes.ResourcePackLocationFileFix;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.DataPackConfig;

import java.io.DataOutput;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FallingObjectDataProvider implements DataProvider {

    private final FabricPackOutput output;
    private final CompletableFuture<HolderLookup.Provider> registriesFuture;

    public FallingObjectDataProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        this.output = output;
        this.registriesFuture = registriesFuture;
    }

    /**
     * Define all your falling object JSON entries here.
     */
    protected void addFallingObject(BiConsumer<Identifier, FallingObject> exporter, HolderLookup.Provider registries) {

    }

    @Override
    public CompletableFuture<?> run(CachedOutput writer) {
        return this.registriesFuture.thenCompose(registries -> {
            List<CompletableFuture<?>> futures = new ArrayList<>();
            var pathResolver = this.output.createPathProvider(
                    PackOutput.Target.DATA_PACK,
                    "stargazer/falling/objects"
            );

            addFallingObject((id, fallingObject) -> {
                Path path = pathResolver.json(id);

                futures.add(DataProvider.saveStable(writer, FallingObject.CODEC, fallingObject, path));
            }, registries);

            return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        });
    }

    @Override
    public String getName() {
        return "Stargazer Falling Objects Generator";
    }
}