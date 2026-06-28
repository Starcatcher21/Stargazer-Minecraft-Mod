package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.CreativeTab.ItemGroup;
import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.effects.Potions;
import com.github.starcatcher21.stargazer.effects.StatusEffects;
import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.mechanics.DamageTypeRegistry;
import com.github.starcatcher21.stargazer.mechanics.Generators.CobbleGen;
import com.github.starcatcher21.stargazer.mechanics.PointOfIntrests;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObject;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObjectsList;
import com.github.starcatcher21.stargazer.nbt.ComponentTypes;
import com.github.starcatcher21.stargazer.nbt.Patterns;
import com.github.starcatcher21.stargazer.nbt.StarPattern;
import com.github.starcatcher21.stargazer.particle.Particles;
import com.github.starcatcher21.stargazer.screens.ScreenHandlerTypes;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.github.starcatcher21.stargazer.villager.ModTraids;
import com.github.starcatcher21.stargazer.worldgen.BiomeReg;
import com.github.starcatcher21.stargazer.worldgen.BiomeTags;
import com.github.starcatcher21.stargazer.worldgen.CustomFeatures;
import com.github.starcatcher21.stargazer.worldgen.features.PlacedFeatures;
import com.github.starcatcher21.stargazer.worldgen.features.trees.TreesRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.registry.Registry;
import net.minecraft.resource.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.starcatcher21.stargazer.villager.ModVillagers.init;

public class Stargazer implements ModInitializer {
	public static final String MOD_ID = "stargazer";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static void main(String[] string) {}

	public void onInitialize() {
		RegistryKeys.init();
		ComponentTypes.init();
		StarPattern.init();
		Patterns.init();
		GameRules.init();
		ScreenHandlerTypes.init();
		RecipeTypes.init();
		CustomFeatures.init();
		Potions.init();
		DamageTypeRegistry.init();
		BiomeReg.init();
		BiomeTags.init();
		ModItems.init();
		BlockTypes.init();
		ModBlock.init();
		PointOfIntrests.init();
		ItemGroup.init();
		Keybinds.init();
		StargazerAttributes.init();
		CustomTags.init();
		Particles.init();
		TreesRegistry.init();
		StatusEffects.init();
		EntityRegistry.init();
		PlacedFeatures.init();
		init();
		Criterias.init();
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new StargazerDataLoader());
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			var registryManager = server.getRegistryManager();

			Registry<Patterns> patterns = registryManager.getOrThrow(RegistryKeys.STAR_PATTERN);
			Registry<CobbleGen> cobbleGens = registryManager.getOrThrow(RegistryKeys.COBBLE_GEN);
			Registry<FallingObject> fallingObjects = registryManager.getOrThrow(RegistryKeys.FALLING_OBJECTS);
			Registry<FallingObjectsList> fallingObjectsList = registryManager.getOrThrow(RegistryKeys.FALLING_OBJECTS_LIST);

			LOGGER.info("Loaded: " + patterns.getIds().size() + " Star Patterns");
			LOGGER.info("Loaded: " + cobbleGens.getIds().size() + " Cobble Gens");
			LOGGER.info("Loaded: " + fallingObjects.getIds().size() + " Falling Objects");
			LOGGER.info("Loaded: " + fallingObjectsList.getIds().size() + " Falling Objects Lists");
		});
		ModTraids.init();
	}
}
