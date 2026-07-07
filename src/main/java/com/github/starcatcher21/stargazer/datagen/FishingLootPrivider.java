package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class FishingLootPrivider extends SimpleFabricLootTableProvider {
    public FishingLootPrivider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.FISHING);
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {
        ResourceKey<LootTable> customFishingKey = ResourceKey.create(
                Registries.LOOT_TABLE,
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "gameplay/fishing/dream")
        );

        // Build the loot table
        LootTable.Builder lootTable = LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItems.GUMMY_FISH).setWeight(60))
                                .add(LootItem.lootTableItem(ModItems.GUMMY_WORM).setWeight(40))
                                .add(LootItem.lootTableItem(ModItems.COSMO_FISH).setWeight(30))
                                .add(LootItem.lootTableItem(ModItems.ENDER_FISH).setWeight(30))
                                .add(LootItem.lootTableItem(ModItems.GOLDEN_CRUCIAN).setWeight(20))
                )
                .withPool(
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(0f, 1.0f))
                                .add(EmptyLootItem.emptyItem().setWeight(40))
                                .add(LootItem.lootTableItem(ModItems.RED_STAR).setWeight(10))
                                .add(LootItem.lootTableItem(ModItems.YELLOW_STAR).setWeight(15))
                                .add(LootItem.lootTableItem(ModItems.PURPLE_STAR).setWeight(10))
                                .add(LootItem.lootTableItem(ModItems.BLUE_STAR).setWeight(10))
                                .add(LootItem.lootTableItem(ModItems.DREAM_STAR).setWeight(5))
                );

        lootTableBiConsumer.accept(customFishingKey, lootTable);
    }
}
