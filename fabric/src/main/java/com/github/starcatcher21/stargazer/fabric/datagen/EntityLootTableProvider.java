package com.github.starcatcher21.stargazer.fabric.datagen;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
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
        this.add(EntityRegistry.GHOST_ENTITY.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(ModItems.ECTOPLASM.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
                        .when(LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity()
                                        .nbt(new NbtPredicate(Util.make(new CompoundTag(), nbt -> {
                                            nbt.putString("tag", "");
                                        })))
                        ))
                ).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(ModItems.COOLER_ECTOPLASM.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
                        .when(LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity()
                                        .nbt(new NbtPredicate(Util.make(new CompoundTag(), nbt -> {
                                            nbt.putString("tag", "pacman");
                                        })))
                        ))
                ));
        this.add(EntityRegistry.EYE_BAT_ENTITY.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(ModItems.DEAD_EYE_BAT.get()))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)))
                ));

        this.add(EntityRegistry.ROOK_ENTITY.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(ModItems.WHITE_BRICK.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 4.0f)))
                ));
        this.add(EntityRegistry.BLACK_ROOK_ENTITY.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(ModItems.BLACK_BRICK.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 4.0f)))
                ));
        this.add(EntityRegistry.AMETHYST_TURTLE_ENTITY.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(0.0f)).add(LootItem.lootTableItem(MoonBlocks.FORGET_ME_NOW.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 0.0f)))
                ));
        this.add(EntityRegistry.THROWABLE_STAR_ENTITY.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(0.0f)).add(LootItem.lootTableItem(MoonBlocks.FORGET_ME_NOW.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 0.0f)))
                ));
        this.add(EntityRegistry.BLACK_FOX_ENTITY.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(0.0f)).add(LootItem.lootTableItem(MoonBlocks.FORGET_ME_NOW.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 0.0f)))
                ));
        this.add(EntityRegistry.SCRUBY_ENTITY.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(0.0f)).add(LootItem.lootTableItem(MoonBlocks.FORGET_ME_NOW.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 0.0f)))
                ));
        this.add(EntityRegistry.STAR_ENTITY.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(0.0f)).add(LootItem.lootTableItem(MoonBlocks.FORGET_ME_NOW.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 0.0f)))
                ));
    }

}
