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
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EntryGroup;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ChestLootPrivider
//? if fabric {
        /*extends SimpleFabricLootTableSubProvider {
    public ChestLootPrivider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.CHEST);
    }
*///?} else {
        implements LootTableSubProvider {
    public ChestLootPrivider(HolderLookup.Provider registryLookup) {}
//?}

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {

        ResourceKey<LootTable> lootholeKey = ResourceKey.create(
                Registries.LOOT_TABLE,
                Identifier.fromNamespaceAndPath("stargazer", "chests/loothole"));

        ResourceKey<LootTable> cometKey = ResourceKey.create(
                Registries.LOOT_TABLE,
              Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "gameplay/comet")
        );

        ResourceKey<LootTable> seedKey = ResourceKey.create(
                Registries.LOOT_TABLE,
              Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "gameplay/seed_packet")
        );

        ResourceKey<LootTable> graveYardKey = ResourceKey.create(
                Registries.LOOT_TABLE,
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "chests/graveyard")
        );

        LootTable.Builder lootTableLoothole = LootTable.lootTable()
                .setParamSet(LootContextParamSets.CHEST)
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(6))
                        .setBonusRolls(ConstantValue.exactly(4))
                        .add(LootItem.lootTableItem(ModItems.STARDUST.get())
                                .setWeight(2)
                                .setQuality(7)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 12))))
                        .add(EntryGroup.list(
                                LootItem.lootTableItem(ModItems.RED_STAR.get())
                                        .setWeight(1)
                                        .setQuality(16)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 4))),
                                LootItem.lootTableItem(ModItems.BLUE_STAR.get())
                                        .setWeight(1)
                                        .setQuality(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 6))),
                                LootItem.lootTableItem(ModItems.PURPLE_STAR.get())
                                        .setWeight(1)
                                        .setQuality(19)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 3))),
                                LootItem.lootTableItem(ModItems.YELLOW_STAR.get())
                                        .setWeight(1)
                                        .setQuality(8)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 6)))
                        ))
                        .add(LootItem.lootTableItem(ModItems.PRISMATIC_SHARD.get())
                                .setWeight(3)
                                .setQuality(7)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT)
                                .setWeight(4)
                                .setQuality(7)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
                        .add(LootItem.lootTableItem(MoonBlocks.MOON_ROCK.get())
                                .setWeight(5)
                                .setQuality(2)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 14))))
                );

        LootTable.Builder lootTableComet = LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0f, 5.0f))
                                .add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(60))
                                .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(50))
                                //? if >= 26.2 {
                                .add(LootItem.lootTableItem(Items.COPPER_NUGGET).setWeight(50))
                                //?} else {
                                /*.add(LootItem.lootTableItem(Items.COPPER_INGOT).setWeight(50))
                                *///?}
                                .add(LootItem.lootTableItem(Items.RAW_IRON).setWeight(30))
                                .add(LootItem.lootTableItem(Items.RAW_GOLD).setWeight(30))
                                .add(LootItem.lootTableItem(Items.RAW_COPPER).setWeight(30))
                                .add(LootItem.lootTableItem(Items.COBBLESTONE).setWeight(70))
                                .add(LootItem.lootTableItem(Items.AMETHYST_SHARD).setWeight(45))
                                .add(LootItem.lootTableItem(ModItems.COMET_FRAGMENT.get()).setWeight(10))
                                .add(LootItem.lootTableItem(ModItems.AURORA_FRAGMENT.get()).setWeight(5))
                );
        LootTable.Builder lootTableSeedPacket = LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0f, 3.0f))
                                .add(LootItem.lootTableItem(Crops.EYE_BALLS.get()).setWeight(20))
                                .add(LootItem.lootTableItem(Crops.BROODY.get()).setWeight(20))
                                .add(LootItem.lootTableItem(Crops.DRAGON_CARROT.get()).setWeight(50))
                );

        LootTable.Builder lootTableGraveYard = LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(0.0f, 3.0f))
                                .add(LootItem.lootTableItem(Items.BONE).setWeight(40))
                                .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(25))
                                .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(8))
                                .add(LootItem.lootTableItem(ModItems.RED_GEM.get()).setWeight(6))
                                .add(LootItem.lootTableItem(ModItems.GREEN_GEM.get()).setWeight(6))
                                .add(LootItem.lootTableItem(ModItems.PURPLE_GEM.get()).setWeight(2))
                                .add(LootItem.lootTableItem(Items.ZOMBIE_SPAWN_EGG).setWeight(3))
                                .add(LootItem.lootTableItem(Items.SKELETON_SPAWN_EGG).setWeight(3))
                                .add(LootItem.lootTableItem(ModItems.GHOST_SPAWN_EGG.get()).setWeight(4))
                );

        lootTableBiConsumer.accept(lootholeKey, lootTableLoothole);
        lootTableBiConsumer.accept(cometKey, lootTableComet);
        lootTableBiConsumer.accept(seedKey, lootTableSeedPacket);
        lootTableBiConsumer.accept(graveYardKey, lootTableGraveYard);
    }
}
//? }
