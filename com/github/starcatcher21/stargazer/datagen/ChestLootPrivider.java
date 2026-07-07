package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ChestLootPrivider extends SimpleFabricLootTableProvider {
    public ChestLootPrivider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.CHEST);
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {

        RegistryKey<LootTable> cometKey = RegistryKey.of(
                RegistryKeys.LOOT_TABLE,
                Identifier.of(Stargazer.MOD_ID, "gameplay/comet")
        );

        LootTable.Builder lootTableComet = LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0f, 5.0f))
                                .with(ItemEntry.builder(Items.IRON_NUGGET).weight(60))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(50))
                                .with(ItemEntry.builder(Items.COPPER_NUGGET).weight(50))
                                .with(ItemEntry.builder(Items.RAW_IRON).weight(30))
                                .with(ItemEntry.builder(Items.RAW_GOLD).weight(30))
                                .with(ItemEntry.builder(Items.RAW_COPPER).weight(30))
                                .with(ItemEntry.builder(Items.COBBLESTONE).weight(70))
                                .with(ItemEntry.builder(Items.AMETHYST_SHARD).weight(45))
                                .with(ItemEntry.builder(ModItems.COMET_FRAGMENT).weight(10))
                                .with(ItemEntry.builder(ModItems.AURORA_FRAGMENT).weight(5))
                );

        lootTableBiConsumer.accept(cometKey, lootTableComet);
    }
}
