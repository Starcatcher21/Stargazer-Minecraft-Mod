package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.CreativeTab.ItemGroup;
import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.effects.Potions;
import com.github.starcatcher21.stargazer.effects.StatusEffects;
import com.github.starcatcher21.stargazer.energy.EnergyInit;
import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import com.github.starcatcher21.stargazer.item.ConsumeEffectsRegistry;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.mechanics.DamageTypeRegistry;
import com.github.starcatcher21.stargazer.mechanics.Generators.CobbleGen;
import com.github.starcatcher21.stargazer.mechanics.PlayerCosmicGrav;
import com.github.starcatcher21.stargazer.mechanics.PlayerRedOrbGrav;
import com.github.starcatcher21.stargazer.mechanics.PointOfIntrests;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.github.starcatcher21.stargazer.mechanics.dash.DashClient;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObject;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObjectsList;
import com.github.starcatcher21.stargazer.nbt.ComponentTypes;
import com.github.starcatcher21.stargazer.nbt.Patterns;
import com.github.starcatcher21.stargazer.nbt.StarPattern;
import com.github.starcatcher21.stargazer.particle.Particles;
import com.github.starcatcher21.stargazer.screens.ScreenHandlerTypes;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedMoonWelderRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarCrusherRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarforgeRecipe;
import com.github.starcatcher21.stargazer.sound.SoundEffects;
import com.github.starcatcher21.stargazer.stats.ModStats;
import com.github.starcatcher21.stargazer.villager.ModTraids;
import com.github.starcatcher21.stargazer.worldgen.BiomeReg;
import com.github.starcatcher21.stargazer.worldgen.BiomeTags;
import com.github.starcatcher21.stargazer.worldgen.CustomFeatures;
import com.github.starcatcher21.stargazer.worldgen.features.PlacedFeatures;
import com.github.starcatcher21.stargazer.worldgen.features.trees.TreesRegistry;
import dev.architectury.event.events.common.TickEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.starcatcher21.stargazer.villager.ModVillagers.init;

public class Stargazer implements ModInitializer {
	public static final String MOD_ID = "stargazer";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static void main(String[] string) {}

	public void onInitialize() {
		StargazerNetworking.registerPackets();
		SoundEffects.init();
		RegistryKeys.init();
		ComponentTypes.init();
		StarPattern.init();
		Patterns.init();
		GameRules.init();
		ScreenHandlerTypes.init();
		CustomFeatures.init();
		Potions.init();
		DamageTypeRegistry.init();
		BiomeReg.init();
		BiomeTags.init();
		ConsumeEffectsRegistry.init();
		ModItems.init();
		BlockTypes.init();
		EnergyInit.init();
		ModBlock.init();
		PointOfIntrests.init();
		ItemGroup.init();
		StargazerAttributes.init();
		CustomTags.init();
		Particles.init();
		TreesRegistry.init();
		StatusEffects.init();
		EntityRegistry.init();
		PlacedFeatures.init();
		ModStats.init();
		init();
		Criterias.init();
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			var registryManager = server.registryAccess();

			Registry<Patterns> patterns = registryManager.lookupOrThrow(RegistryKeys.STAR_PATTERN);
			Registry<CobbleGen> cobbleGens = registryManager.lookupOrThrow(RegistryKeys.COBBLE_GEN);
			Registry<FallingObject> fallingObjects = registryManager.lookupOrThrow(RegistryKeys.FALLING_OBJECTS);
			Registry<FallingObjectsList> fallingObjectsList = registryManager.lookupOrThrow(RegistryKeys.FALLING_OBJECTS_LIST);

			LOGGER.info("Loaded: " + patterns.keySet().size() + " Star Patterns");
			LOGGER.info("Loaded: " + cobbleGens.keySet().size() + " Cobble Gens");
			LOGGER.info("Loaded: " + fallingObjects.keySet().size() + " Falling Objects");
			LOGGER.info("Loaded: " + fallingObjectsList.keySet().size() + " Falling Objects Lists");
		});
		ServerTickEvents.END_LEVEL_TICK.register(server -> {
			for (ServerPlayer player : server.players()) {
				PlayerCosmicGrav.tick(player);
				PlayerRedOrbGrav.tick(player);
			}
		});
		ModTraids.init();
		RecipeTypes.init();
		RecipeSynchronization.synchronizeRecipeSerializer(ShapedStarforgeRecipe.SERIALIZER);
		RecipeSynchronization.synchronizeRecipeSerializer(ShapedMoonWelderRecipe.SERIALIZER);
		RecipeSynchronization.synchronizeRecipeSerializer(ShapedStarCrusherRecipe.SERIALIZER);
	}
}
