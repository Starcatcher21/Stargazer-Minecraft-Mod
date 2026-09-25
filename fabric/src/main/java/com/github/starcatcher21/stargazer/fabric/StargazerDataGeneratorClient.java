package com.github.starcatcher21.stargazer.fabric;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.fabric.datagen.*;
import com.github.starcatcher21.stargazer.fabric.datagen.lang.ModEngLangProvider;
import com.github.starcatcher21.stargazer.worldgen.BiomeReg;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StargazerDataGeneratorClient implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(BlockTagProvider::new);
		pack.addProvider(ItemTagProvider::new);
		pack.addProvider(FluidTagProvider::new);
		pack.addProvider(LootTableProvider::new);
		pack.addProvider(FishingLootPrivider::new);
		pack.addProvider(ChestLootPrivider::new);
		pack.addProvider(RecipeProvider::new);
		pack.addProvider(WorldGenerator::new);
		pack.addProvider(ModelProvider::new);
		pack.addProvider(AdvancementsProvider::new);
		pack.addProvider(EntityLootTableProvider::new);
		pack.addProvider(POITagProvider::new);
		pack.addProvider(EquipmentAssetProvider::new);
		// Lang
		pack.addProvider(ModEngLangProvider::new);

		pack.addProvider(DynamicRegistriesProvider::new);
		// Mod
		pack.addProvider(FallingObjectProvider::new);
		pack.addProvider(FallingObjectListProvider::new);
		pack.addProvider(CobbleGenProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		DataGeneratorEntrypoint.super.buildRegistry(registryBuilder);
		registryBuilder.add(Registries.BIOME, StargazerDataGeneratorClient::biomeBootstrap);
	}

	public static void biomeBootstrap(BootstrapContext<Biome> registerable) {

		for (ResourceKey<Biome> key : BiomeReg.MoonList) {
			Biome myBiome = loadBiomeFromJson(key.identifier().getPath()+".json");
			registerable.register(key, myBiome);
		}
	}


	private static Biome loadBiomeFromJson(String fileName) {
		Path root = Path.of("").toAbsolutePath().getParent().getParent().getParent();
		Path path = Paths.get(root + "/common/src/main/resources/data/stargazer/worldgen/biome/" + fileName);
		Stargazer.LOGGER.info("Parsing: " + path);

		try (Reader reader = Files.newBufferedReader(path)) {
			JsonElement json = JsonParser.parseReader(reader);
			return Biome.DIRECT_CODEC.parse(JsonOps.INSTANCE, json)
					.getPartialOrThrow(error -> new RuntimeException("Failed to parse biome: " + error));
		} catch (IOException e) {
			throw new RuntimeException("Could not find biome file: " + path, e);
		}
	}
}
