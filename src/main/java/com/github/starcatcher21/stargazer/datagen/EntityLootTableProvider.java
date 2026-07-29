package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootSubProvider;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.predicates.NbtPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Util;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class EntityLootTableProvider extends FabricEntityLootSubProvider {
    public EntityLootTableProvider(FabricPackOutput output, @NotNull CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generate() {
        this.add(EntityRegistry.GHOST_ENTITY, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(ModItems.ECTOPLASM))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
                        .when(LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity()
                                        .nbt(new NbtPredicate(Util.make(new CompoundTag(), nbt -> {
                                            nbt.putString("tag", "");
                                        })))
                        ))
                ).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(ModItems.COOLER_ECTOPLASM))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
                        .when(LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity()
                                        .nbt(new NbtPredicate(Util.make(new CompoundTag(), nbt -> {
                                            nbt.putString("tag", "pacman");
                                        })))
                        ))
                ));
        this.add(EntityRegistry.EYE_BAT_ENTITY, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(ModItems.DEAD_EYE_BAT))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)))
                ));

        this.add(EntityRegistry.ROOK_ENTITY, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(ModItems.WHITE_BRICK))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 4.0f)))
                ));
        this.add(EntityRegistry.BLACK_ROOK_ENTITY, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(ModItems.BLACK_BRICK))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 4.0f)))
                ));
    }

}
