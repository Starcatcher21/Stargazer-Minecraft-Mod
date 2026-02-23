package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.CreativeTab.ItemGroup;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.effects.Potions;
import com.github.starcatcher21.stargazer.effects.StatusEffects;
import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import com.github.starcatcher21.stargazer.worldgen.CustomFeatures;
import com.github.starcatcher21.stargazer.mechanics.DamageTypeRegistry;
import com.github.starcatcher21.stargazer.mechanics.PointOfIntrests;
import com.github.starcatcher21.stargazer.worldgen.features.PlacedFeatures;
import com.github.starcatcher21.stargazer.worldgen.features.trees.TreesRegistry;
import com.github.starcatcher21.stargazer.particle.Particles;
import com.github.starcatcher21.stargazer.screens.ScreenHandlerTypes;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.github.starcatcher21.stargazer.worldgen.BiomeReg;
import com.github.starcatcher21.stargazer.worldgen.BiomeTags;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.starcatcher21.stargazer.item.ModItems;

public class Stargazer implements ModInitializer {
	public static final String MOD_ID = "stargazer";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static void main(String[] string) {}

	public void onInitialize() {
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
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new StargazerDataLoader());
	}
}
