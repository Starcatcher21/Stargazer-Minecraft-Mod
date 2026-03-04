package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.datagen.*;
import com.github.starcatcher21.stargazer.datagen.lang.ModEngLangProvider;
import com.github.starcatcher21.stargazer.worldgen.BiomeReg;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.registry.*;
import net.minecraft.world.biome.Biome;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StargazerDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModWorldGenerator::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModAdvancementsProvider::new);
		// Lang
		pack.addProvider(ModEngLangProvider::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		DataGeneratorEntrypoint.super.buildRegistry(registryBuilder);
		registryBuilder.addRegistry(RegistryKeys.BIOME, StargazerDataGenerator::biomeBootstrap);
	}

	public static void biomeBootstrap(Registerable<Biome> registerable) {

		for (RegistryKey<Biome> key : BiomeReg.MoonList) {
			Biome myBiome = loadBiomeFromJson(key.getValue().getPath()+".json");
			registerable.register(key, myBiome);
		}
	}

	private static Biome loadBiomeFromJson(String fileName) {
		// Path to your manual JSON files
		Path root = Path.of("").toAbsolutePath().getParent().getParent();
		Path path = Paths.get(root + "/src/main/resources/data/stargazer/worldgen/biome/" + fileName);
		Stargazer.LOGGER.info("Parsing: " + path);

		try (Reader reader = Files.newBufferedReader(path)) {
			JsonElement json = JsonParser.parseReader(reader);
			// Use Minecraft's Biome Codec to turn JSON into a Biome Object
			return Biome.CODEC.parse(JsonOps.INSTANCE, json)
					.getPartialOrThrow(error -> new RuntimeException("Failed to parse biome: " + error));
		} catch (IOException e) {
			throw new RuntimeException("Could not find biome file: " + path, e);
		}
	}
}
