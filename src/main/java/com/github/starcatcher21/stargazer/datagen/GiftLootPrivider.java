/*? if fabric || neoforge {*/
package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.register.Crops;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.item.ModItems;
//? if fabric {
/*import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
*///?} else {
import net.minecraft.data.loot.LootTableSubProvider;
//?}
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class GiftLootPrivider
//? if fabric {
        /*extends SimpleFabricLootTableSubProvider {
    public GiftLootPrivider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.GIFT);
    }
*///?} else {
        implements LootTableSubProvider {
    public GiftLootPrivider(HolderLookup.Provider registryLookup) {}
//?}

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {

        ResourceKey<LootTable> snifferKey = ResourceKey.create(
                Registries.LOOT_TABLE,
                ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "sniffer_moon")
        );

        LootTable.Builder lootTableSnifferMoon = LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0f, 1.0f))
                                .add(LootItem.lootTableItem(Crops.BRUNT_SEEDS.get()).setWeight(20))
                                .add(LootItem.lootTableItem(Crops.SPINNY_SEEDS.get()).setWeight(20))
                );

        lootTableBiConsumer.accept(snifferKey, lootTableSnifferMoon);
    }
}
//? }
