package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class FishingLootPrivider extends SimpleFabricLootTableProvider {
    public FishingLootPrivider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.FISHING);
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {
        RegistryKey<LootTable> customFishingKey = RegistryKey.of(
                RegistryKeys.LOOT_TABLE,
                Identifier.of(Stargazer.MOD_ID, "gameplay/fishing/dream")
        );

        // Build the loot table
        LootTable.Builder lootTable = LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(ModItems.GUMMY_FISH).weight(60))
                                .with(ItemEntry.builder(ModItems.GUMMY_WORM).weight(40))
                                .with(ItemEntry.builder(ModItems.COSMO_FISH).weight(30))
                                .with(ItemEntry.builder(ModItems.ENDER_FISH).weight(30))
                                .with(ItemEntry.builder(ModItems.GOLDEN_CRUCIAN).weight(20))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(0f, 1.0f))
                                .with(EmptyEntry.builder().weight(40))
                                .with(ItemEntry.builder(ModItems.RED_STAR).weight(10))
                                .with(ItemEntry.builder(ModItems.YELLOW_STAR).weight(15))
                                .with(ItemEntry.builder(ModItems.PURPLE_STAR).weight(10))
                                .with(ItemEntry.builder(ModItems.BLUE_STAR).weight(10))
                                .with(ItemEntry.builder(ModItems.DREAM_STAR).weight(5))
                );

        lootTableBiConsumer.accept(customFishingKey, lootTable);
    }
}
