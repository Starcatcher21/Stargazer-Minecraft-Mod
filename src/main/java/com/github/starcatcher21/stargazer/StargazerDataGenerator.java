/*? if fabric {*/
/*package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.datagen.*;
import com.github.starcatcher21.stargazer.datagen.lang.ModEngLangProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;


public class StargazerDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(BlockTagProvider::new);
		pack.addProvider(ItemTagProvider::new);
		pack.addProvider(FluidTagProvider::new);
		pack.addProvider(LootTableProvider::new);
		pack.addProvider(FishingLootPrivider::new);
		pack.addProvider(ChestLootPrivider::new);
        pack.addProvider(GiftLootPrivider::new);
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
		WorldgenDataBootstrap.addTo(registryBuilder);
	}
}
*//*?}*/
