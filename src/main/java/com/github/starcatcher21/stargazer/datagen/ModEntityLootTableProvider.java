package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootTableProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Util;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModEntityLootTableProvider extends FabricEntityLootTableProvider {
    public ModEntityLootTableProvider(FabricDataOutput output, @NotNull CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generate() {
        this.register(EntityRegistry.GHOST_ENTITY, LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(ModItems.ECTOPLASM))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)))
                        .conditionally(EntityPropertiesLootCondition.builder(
                                LootContext.EntityTarget.THIS, // Check the entity that died
                                EntityPredicate.Builder.create()
                                        .nbt(new NbtPredicate(Util.make(new NbtCompound(), nbt -> {
                                            nbt.put("tag", Codec.STRING, "");
                                        })))
                        ))
                ).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(ModItems.COOLER_ECTOPLASM))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)))
                        .conditionally(EntityPropertiesLootCondition.builder(
                                LootContext.EntityTarget.THIS, // Check the entity that died
                                EntityPredicate.Builder.create()
                                        .nbt(new NbtPredicate(Util.make(new NbtCompound(), nbt -> {
                                            nbt.put("tag", Codec.STRING, "pacman");
                                        })))
                        ))
                ));
    }
}
