package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ChestLootPrivider extends SimpleFabricLootTableSubProvider {
    public ChestLootPrivider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.CHEST);
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {

        ResourceKey<LootTable> cometKey = ResourceKey.create(
                Registries.LOOT_TABLE,
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "gameplay/comet")
        );

        LootTable.Builder lootTableComet = LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0f, 5.0f))
                                .add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(60))
                                .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(50))
                                .add(LootItem.lootTableItem(Items.COPPER_NUGGET).setWeight(50))
                                .add(LootItem.lootTableItem(Items.RAW_IRON).setWeight(30))
                                .add(LootItem.lootTableItem(Items.RAW_GOLD).setWeight(30))
                                .add(LootItem.lootTableItem(Items.RAW_COPPER).setWeight(30))
                                .add(LootItem.lootTableItem(Items.COBBLESTONE).setWeight(70))
                                .add(LootItem.lootTableItem(Items.AMETHYST_SHARD).setWeight(45))
                                .add(LootItem.lootTableItem(ModItems.COMET_FRAGMENT).setWeight(10))
                                .add(LootItem.lootTableItem(ModItems.AURORA_FRAGMENT).setWeight(5))
                );

        lootTableBiConsumer.accept(cometKey, lootTableComet);
    }
}
