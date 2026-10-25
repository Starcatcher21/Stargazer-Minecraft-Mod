/*? if fabric || neoforge {*/
package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import com.github.starcatcher21.stargazer.item.ModItems;
//? if fabric {
/*import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootSubProvider;
*///?} else {
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
//?}
//? if >= 26.2 {
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.predicates.NbtPredicate;
//?} else {
/*import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.NbtPredicate;
*///?}
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
//? if >= 26.2 {
import net.minecraft.util.Util;
//?} else {
/*import net.minecraft.Util;
*///?}
import net.minecraft.world.item.Items;
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
import java.util.stream.Stream;

public class EntityLootTableProvider
//? if fabric {
        /*extends FabricEntityLootSubProvider {
    public EntityLootTableProvider(FabricPackOutput output, @NotNull CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }
*///?} else {
        extends EntityLootSubProvider {
    public EntityLootTableProvider(HolderLookup.Provider registryLookup) {
        super(FeatureFlags.REGISTRY.allFlags(), registryLookup);
    }

    @Override
    protected Stream<net.minecraft.world.entity.EntityType<?>> getKnownEntityTypes() {
        return Stream.of(
                EntityRegistry.GHOST_ENTITY.get(),
                EntityRegistry.EYE_BAT_ENTITY.get(),
                EntityRegistry.ROOK_ENTITY.get(),
                EntityRegistry.BLACK_ROOK_ENTITY.get(),
                EntityRegistry.AMETHYST_TURTLE_ENTITY.get()
        );
    }
//?}

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
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(Items.AMETHYST_SHARD))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 3.0f)))
                ));
    }

}
//? }
