/*? if fabric {*/
/*package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.clases.Hedge;
import com.github.starcatcher21.stargazer.block.clases.moon.geode_fruit.GeodeFruit;
import com.github.starcatcher21.stargazer.block.clases.moon.geode_fruit.GeodeFruitStage;
import com.github.starcatcher21.stargazer.block.clases.moon.plants.MoonCrop;
import com.github.starcatcher21.stargazer.block.clases.star.barrier.StarBarrierBlock;
import com.github.starcatcher21.stargazer.block.register.*;
import com.github.starcatcher21.stargazer.entity.Star;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.worldgen.features.trees.nebulas.Red;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBedBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import java.util.concurrent.CompletableFuture;

public class LootTableProvider extends FabricBlockLootSubProvider {
    public LootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public LootTable.Builder oreDrops(Block drop, ItemLike raw, float min, float max) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(drop, (LootPoolEntryContainer.Builder)this.applyExplosionDecay(drop, ((LootPoolSingletonContainer.Builder)LootItem.lootTableItem(raw).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))).apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }
    public LootTable.Builder customLeavesDrop(Block drop, Item raw, float min, float max) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchOrShearsDispatchTable(drop, (LootPoolEntryContainer.Builder)this.applyExplosionDecay(drop, ((LootPoolSingletonContainer.Builder)LootItem.lootTableItem(raw).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))).apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }

    public LootTable.Builder conditionDrop(Block crop, Item product, LootItemCondition.Builder condition) {
        return this.applyExplosionDecay(crop, LootTable.lootTable().withPool(LootPool.lootPool().add(((LootPoolSingletonContainer.Builder)LootItem.lootTableItem(product).when(condition)))));
    }

    public LootTable.Builder cropDrops(Block leaves, Item crop, float ... cropChance) {
        HolderLookup.RegistryLookup<Enchantment> impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchOrShearsDispatchTable(leaves, (LootPoolEntryContainer.Builder<?>)((LootPoolSingletonContainer.Builder)this.applyExplosionCondition(leaves, LootItem.lootTableItem(crop))).when(BonusLevelTableCondition.bonusLevelFlatChance(impl.getOrThrow(Enchantments.FORTUNE), cropChance))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).when(this.doesNotHaveSilkTouch()));
    }


    @Override
    public void generate() {
        dropSelf(ModBlock.NEGATIVE_BLOCK.get());
        dropSelf(ModBlock.NORED_BLOCK.get());
        dropSelf(ModBlock.NOGREEN_BLOCK.get());
        dropSelf(ModBlock.NOBLUE_BLOCK.get());
        dropSelf(Wander.PUROIL.get());
        dropSelf(StarBlocks.AURORA.get());
        dropSelf(MoonBlocks.COMET_BLOCK.get());
        dropSelf(Energy.STARGENERATOR.get());
        dropSelf(Energy.YELLOW_CABLE.get());
        dropSelf(Energy.BLUE_CABLE.get());
        dropSelf(Energy.RED_CABLE.get());
        dropSelf(Energy.PURPLE_CABLE.get());
        dropSelf(Energy.STARMACHINE_BLOCK.get());
        dropSelf(Energy.NIGHT_WATCHER.get());
        dropSelf(Energy.STAR_CRUSHER.get());
        dropSelf(ModBlock.AMETHYST_DUST_BLOCK.get());
        add(Wander.BORIL.get(), oreDrops(Wander.BORIL.get(), Wander.PUROIL.get(), 1.0f, 1.0f));
        dropSelf(Wander.TRUNN_LOG.get());
        dropSelf(Wander.UMBRELLA_LOG.get());
        add(Wander.TRUNN_LEAVES.get(), createLeavesDrops(Wander.TRUNN_LEAVES.get(), Wander.TRUNN_SAPLING.get(), 0.035F));
        add(Wander.UMBRELLA_LEAVES.get(), createLeavesDrops(Wander.UMBRELLA_LEAVES.get(), Wander.UMBRELLA_SAPLING.get(), 0.035F));
        dropSelf(ModBlock.GRAVE.get());
        add(ModBlock.INFESTED_CALCITE.get(), oreDrops(ModBlock.INFESTED_CALCITE.get(), Blocks.CALCITE, 1.0f, 1.0f));
        add(ModBlock.BONE_LEAVES.get(), customLeavesDrop(ModBlock.BONE_LEAVES.get(), Items.BONE, 0f, 3.0f));
        dropSelf(ModBlock.BONEFLOWER.get());
        dropSelf(ModBlock.SPRINKLER.get());
        dropSelf(MoonBlocks.STAR_FORGE.get());
        dropSelf(MoonBlocks.STAR_STONE.get());
        dropSelf(StarBlocks.STAR_DISPLAY.get());
        dropSelf(Wander.UMBRELLA_SAPLING.get());
        dropSelf(ModBlock.PURE_AMETHYST_DUST_BLOCK.get());
        add(MoonBlocks.FORGET_ME_NOW.get(), addFlowerbedDrop(MoonBlocks.FORGET_ME_NOW.get()));
        add(EyeBloodBlocks.EYES.get(), addFlowerbedDrop(EyeBloodBlocks.EYES.get()));
        dropPottedContents(MoonBlocks.POTTED_FORGET_ME_NOW.get());
        dropPottedContents(StarBlocks.POTTED_CELESTIAL_STAR_FLOWER.get());
        dropPottedContents(StarBlocks.POTTED_STAR_FLOWER.get());
        dropPottedContents(MoonBlocks.POTTED_MOON_SAPLING.get());
        dropPottedContents(Wander.POTTED_UMBRELLA_SAPLING.get());
        dropPottedContents(MoonBlocks.POTTED_FULL_MOON_SAPLING.get());
        dropPottedContents(MoonBlocks.POTTED_CURVE_SAPLING.get());
        dropPottedContents(MoonBlocks.POTTED_PURPLE_MUSHROOM.get());
        dropPottedContents(StarBlocks.POTTED_STAR_SAPLING.get());
        dropPottedContents(MoonBlocks.POTTED_SPRUNGUS.get());
        dropPottedContents(Darkness.POTTED_GRADI.get());
        add(Darkness.GRADI.get(), addFlowerbedDrop(Darkness.GRADI.get()));
        dropSelf(MoonBlocks.SPRUNGUS.get());
        dropSelf(ModBlock.MOON_WELDER.get());

        // Moon
        add(MoonBlocks.MOON_LEAVES.get(), createLeavesDrops(MoonBlocks.MOON_LEAVES.get(), MoonBlocks.MOON_SAPLING.get(), 0.035F));
        add(MoonBlocks.FULL_MOON_LEAVES.get(), createLeavesDrops(MoonBlocks.FULL_MOON_LEAVES.get(), MoonBlocks.FULL_MOON_SAPLING.get(), 0.035F));
        dropSelf(MoonBlocks.SUN_ENRICHED_MOON_ROCK.get());
        dropSelf(MoonBlocks.POLISHED_SUN_ENRICHED_MOON_ROCK.get());
        add(MoonBlocks.CURVE_LEAVES.get(), createLeavesDrops(MoonBlocks.CURVE_LEAVES.get(), MoonBlocks.CURVE_SAPLING.get(), 0.035F));
        add(MoonBlocks.MOON_ROCK_NYLIUM.get(), createSingleItemTableWithSilkTouch(MoonBlocks.MOON_ROCK_NYLIUM.get(), MoonBlocks.MOON_ROCK.get()));
        add(Darkness.DYLIUM.get(), createSingleItemTableWithSilkTouch(Darkness.DYLIUM.get(), MoonBlocks.MOON_ROCK.get()));
        dropSelf(Darkness.ROSE_OF_PAIN.get());
        dropPottedContents(Darkness.POTTED_ROSE_OF_PAIN.get());
        add(MoonBlocks.MOON_GRASS.get(), cropDrops(MoonBlocks.MOON_GRASS.get(), Crops.DRAGON_CARROT.get(), 0.035F));
        add(MoonBlocks.TALL_MOON_GRASS.get(), createShearsOrSilkTouchOnlyDrop(MoonBlocks.TALL_MOON_GRASS.get()));
        add(MoonBlocks.MOON_FERN.get(), cropDrops(MoonBlocks.MOON_FERN.get(), Crops.BROODY.get(), 0.035F));
        add(EyeBloodBlocks.EYE_FERN.get(), cropDrops(EyeBloodBlocks.EYE_FERN.get(), Crops.EYE_BALLS.get(), 0.035F));
        add(MoonBlocks.STAR_TRAP.get(), createShearsOrSilkTouchOnlyDrop(MoonBlocks.STAR_TRAP.get()));
        add(MoonBlocks.GEODE_FRUIT.get(), conditionDrop(MoonBlocks.GEODE_FRUIT.get(), ModItems.GEODE_FRUIT.get(), LootItemBlockStatePropertyCondition.hasBlockStateProperties(MoonBlocks.GEODE_FRUIT.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GeodeFruit.STAGE, GeodeFruitStage.grown))));
        add(Crops.DRAGON_CARROT_BLOCK.get(), createCropDrops(Crops.DRAGON_CARROT_BLOCK.get(), Crops.DRAGON_CARROT.get(), Crops.DRAGON_CARROT.get(), LootItemBlockStatePropertyCondition.hasBlockStateProperties(Crops.DRAGON_CARROT_BLOCK.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MoonCrop.AGE, 7))));
        add(Crops.BROODY_BLOCK.get(), createCropDrops(Crops.BROODY_BLOCK.get(), Crops.BROODY.get(), Crops.BROODY.get(), LootItemBlockStatePropertyCondition.hasBlockStateProperties(Crops.BROODY_BLOCK.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MoonCrop.AGE, 7))));
        add(Crops.EYE_BALLS_BLOCK.get(), createCropDrops(Crops.EYE_BALLS_BLOCK.get(), Crops.EYE_BALLS.get(), Crops.EYE_BALLS.get(), LootItemBlockStatePropertyCondition.hasBlockStateProperties(Crops.EYE_BALLS_BLOCK.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MoonCrop.AGE, 7))));
        dropSelf(EyeBloodBlocks.EYE_JAR.get());
        dropSelf(MoonBlocks.MOON_LOG.get());
        dropSelf(MoonBlocks.FULL_MOON_CORE.get());
        dropSelf(MoonBlocks.FULL_MOON_LOG.get());
        dropSelf(MoonBlocks.MOON_SAPLING.get());
        dropSelf(MoonBlocks.FULL_MOON_SAPLING.get());
        dropSelf(RedOrbBlocks.POINTY.get());
        dropSelf(MoonBlocks.STRIPPED_MOON_LOG.get());
        add(MoonBlocks.MOON_PLANKS_DOOR.get(), createDoorTable(MoonBlocks.MOON_PLANKS_DOOR.get()));
        add(RedOrbBlocks.YERI_PLANKS_DOOR.get(), createDoorTable(RedOrbBlocks.YERI_PLANKS_DOOR.get()));
        dropSelf(EyeBloodBlocks.EYE_LOG.get());
        dropSelf(EyeBloodBlocks.STRIPPED_EYE_LOG.get());
        dropSelf(MoonBlocks.CURVE_LOG.get());
        dropSelf(MoonBlocks.STRIPPED_CURVE_LOG.get());
        dropSelf(MoonBlocks.CURVE_PLANKS.get());
        add(MoonBlocks.CURVE_PLANKS_DOOR.get(), createDoorTable(MoonBlocks.CURVE_PLANKS_DOOR.get()));
        add(MoonBlocks.CURVE_PLANKS_SLAB.get(), createSlabItemTable(MoonBlocks.CURVE_PLANKS_SLAB.get()));
        dropSelf(MoonBlocks.CURVE_PLANKS_STAIRS.get());
        dropSelf(MoonBlocks.CURVE_PLANKS_BUTTON.get());
        dropSelf(MoonBlocks.CURVE_PLANKS_FENCE.get());
        dropSelf(MoonBlocks.CURVE_PLANKS_FENCE_GATE.get());
        dropSelf(MoonBlocks.MOON_PLANKS.get());
        dropSelf(MoonBlocks.MOON_PLANKS_STAIRS.get());
        add(MoonBlocks.MOON_PLANKS_SLAB.get(), createSlabItemTable(MoonBlocks.MOON_PLANKS_SLAB.get()));
        dropSelf(MoonBlocks.MOON_PLANKS_BUTTON.get());
        dropSelf(MoonBlocks.MOON_PLANKS_FENCE.get());
        dropSelf(MoonBlocks.MOON_PLANKS_FENCE_GATE.get());
        dropSelf(MoonBlocks.RED_MOON_PLANKS.get());
        dropSelf(MoonBlocks.RED_MOON_PLANKS_STAIRS.get());
        add(MoonBlocks.RED_MOON_PLANKS_SLAB.get(), createSlabItemTable(MoonBlocks.RED_MOON_PLANKS_SLAB.get()));
        dropSelf(MoonBlocks.RED_MOON_PLANKS_BUTTON.get());
        dropSelf(MoonBlocks.RED_MOON_PLANKS_FENCE.get());
        dropSelf(MoonBlocks.RED_MOON_PLANKS_FENCE_GATE.get());
        dropSelf(MoonBlocks.BLUE_MOON_PLANKS.get());
        dropSelf(MoonBlocks.BLUE_MOON_PLANKS_STAIRS.get());
        add(MoonBlocks.BLUE_MOON_PLANKS_SLAB.get(), createSlabItemTable(MoonBlocks.BLUE_MOON_PLANKS_SLAB.get()));
        dropSelf(MoonBlocks.BLUE_MOON_PLANKS_BUTTON.get());
        dropSelf(MoonBlocks.BLUE_MOON_PLANKS_FENCE.get());
        dropSelf(MoonBlocks.BLUE_MOON_PLANKS_FENCE_GATE.get());
        dropSelf(MoonBlocks.PURPLE_MOON_PLANKS.get());
        dropSelf(MoonBlocks.PURPLE_MOON_PLANKS_STAIRS.get());
        add(MoonBlocks.PURPLE_MOON_PLANKS_SLAB.get(), createSlabItemTable(MoonBlocks.PURPLE_MOON_PLANKS_SLAB.get()));
        dropSelf(MoonBlocks.PURPLE_MOON_PLANKS_BUTTON.get());
        dropSelf(MoonBlocks.PURPLE_MOON_PLANKS_FENCE.get());
        dropSelf(MoonBlocks.PURPLE_MOON_PLANKS_FENCE_GATE.get());
        dropSelf(MoonBlocks.YELLOW_MOON_PLANKS.get());
        dropSelf(MoonBlocks.YELLOW_MOON_PLANKS_STAIRS.get());
        add(MoonBlocks.YELLOW_MOON_PLANKS_SLAB.get(), createSlabItemTable(MoonBlocks.YELLOW_MOON_PLANKS_SLAB.get()));
        dropSelf(MoonBlocks.YELLOW_MOON_PLANKS_BUTTON.get());
        dropSelf(MoonBlocks.YELLOW_MOON_PLANKS_FENCE.get());
        dropSelf(MoonBlocks.YELLOW_MOON_PLANKS_FENCE_GATE.get());
        dropSelf(MoonBlocks.MOON_ROCK.get());
        dropSelf(MoonBlocks.POLISHED_MOON_ROCK.get());
        dropSelf(MoonBlocks.MOON_ROCK_TILES.get());
        dropSelf(MoonBlocks.PURPLE_MOON_ROCK_TILES.get());
        dropOther(MoonBlocks.MOON_FARMLAND.get(), MoonBlocks.MOON_ROCK.get());
        dropSelf(MoonBlocks.MOON_ROCK_BRICKS.get());
        add(MoonBlocks.MOON_ROCK_BRICKS_SLAB.get(), createSlabItemTable(MoonBlocks.MOON_ROCK_BRICKS_SLAB.get()));
        dropSelf(MoonBlocks.MOON_ROCK_BRICKS_STAIRS.get());
        dropSelf(MoonBlocks.CHISELED_MOON_ROCK_BRICKS.get());
        dropSelf(MoonBlocks.CRACKED_MOON_ROCK_BRICKS.get());
        dropSelf(MoonBlocks.BLACK_MOON_ROCK.get());
        dropSelf(MoonBlocks.POLISHED_BLACK_MOON_ROCK.get());
        dropSelf(MoonBlocks.POLISHED_BLACK_MOON_ROCK_PURPLE.get());
        dropSelf(MoonBlocks.PURPLE_MUSHROOM.get());
        add(MoonBlocks.PURPLE_MUSHROOM_BLOCK.get(), createMushroomBlockDrop(MoonBlocks.PURPLE_MUSHROOM_BLOCK.get(), MoonBlocks.PURPLE_MUSHROOM.get()));
        dropSelf(MoonBlocks.CRYSTAL_MOON_BLOCK.get());
        add(MoonBlocks.PRISMATIC_ORE.get(), oreDrops(MoonBlocks.PRISMATIC_ORE.get(), ModItems.PRISMATIC_SHARD.get(), 1, 2));
        add(MoonBlocks.MOON_ROCK_IRON_ORE.get(), oreDrops(MoonBlocks.MOON_ROCK_IRON_ORE.get(), Items.RAW_IRON, 1, 4));
        add(MoonBlocks.MOON_ROCK_CRYSTALS.get(), oreDrops(MoonBlocks.MOON_ROCK_CRYSTALS.get(), ModItems.CRYSTAL_MOON.get(), 1, 2));
        dropSelf(MoonBlocks.PRISMATIC_SHARD_BLOCK.get());
        dropSelf(ModBlock.BONEFLOWER.get());
        dropPottedContents(ModBlock.POTTED_BONEFLOWER.get());
        dropPottedContents(RedOrbBlocks.POTTED_POINTY.get());
        dropSelf(Nebulas.RED_TENTACLE_FLOWER.get());
        dropSelf(Nebulas.BLUE_TENTACLE_FLOWER.get());
        dropSelf(Nebulas.YELLOW_TENTACLE_FLOWER.get());
        dropSelf(Nebulas.PURPLE_TENTACLE_FLOWER.get());
        dropPottedContents(Nebulas.POTTED_BLUE_TENTACLE_FLOWER.get());
        dropPottedContents(Nebulas.POTTED_RED_TENTACLE_FLOWER.get());
        dropPottedContents(Nebulas.POTTED_YELLOW_TENTACLE_FLOWER.get());
        dropPottedContents(Nebulas.POTTED_PURPLE_TENTACLE_FLOWER.get());

        // Star
        dropSelf(StarBlocks.COSMIC_BLOCK.get());
        add(StarBlocks.STAR_LEAVES.get(), createLeavesDrops(StarBlocks.STAR_LEAVES.get(), StarBlocks.STAR_SAPLING.get(), 0.035F));
        dropSelf(StarBlocks.STAR_LOG.get());
        dropSelf(StarBlocks.STRIPPED_STAR_LOG.get());
        dropSelf(StarBlocks.STAR_PLANKS.get());
        add(StarBlocks.STAR_PLANKS_DOOR.get(), createDoorTable(StarBlocks.STAR_PLANKS_DOOR.get()));
        dropSelf(StarBlocks.STAR_PLANKS_STAIRS.get());
        add(StarBlocks.STAR_PLANKS_SLAB.get(), createSlabItemTable(StarBlocks.STAR_PLANKS_SLAB.get()));
        dropSelf(StarBlocks.STAR_PLANKS_BUTTON.get());
        dropSelf(StarBlocks.STAR_PLANKS_FENCE.get());
        dropSelf(StarBlocks.STAR_PLANKS_FENCE_GATE.get());
        dropSelf(StarBlocks.STAR_SAPLING.get());
        dropSelf(StarBlocks.STAR_FLOWER.get());
        dropSelf(StarBlocks.CELESTIAL_STAR_FLOWER.get());

        // Darkness
        add(Darkness.DARKNESS_PLANKS_DOOR.get(), createDoorTable(Darkness.DARKNESS_PLANKS_DOOR.get()));
        dropSelf(Darkness.LOG_OF_DARKNESS.get());
        dropSelf(Darkness.STRIPPED_LOG_OF_DARKNESS.get());
        dropSelf(Darkness.DARKNESS_PLANKS.get());
        dropSelf(Darkness.DARKNESS_PLANKS_STAIRS.get());
        add(Darkness.DARKNESS_PLANKS_SLAB.get(), createSlabItemTable(Darkness.DARKNESS_PLANKS_SLAB.get()));
        dropSelf(Darkness.DARKNESS_PLANKS_BUTTON.get());
        dropSelf(Darkness.DARKNESS_PLANKS_FENCE.get());
        dropSelf(Darkness.DARKNESS_PLANKS_FENCE_GATE.get());
        add(Darkness.DARKNESS_LEAVES.get(), createLeavesDrops(Darkness.DARKNESS_LEAVES.get(), Darkness.DARKNESS_SAPLING.get(), 0.035F));
        dropPottedContents(Darkness.POTTED_DARKNESS_SAPLING.get());
        dropPottedContents(Wander.POTTED_TRUNN_SAPLING.get());
        dropSelf(Darkness.DARKNESS_SAPLING.get());
        dropSelf(Wander.TRUNN_SAPLING.get());
        dropSelf(MoonBlocks.CURVE_SAPLING.get());
        dropSelf(Hedges.FULL_MOON_HEDGE.get());
        dropSelf(RedOrbBlocks.PETRICY.get());
        dropSelf(Nebulas.PURPLE_NEBULA_REGROW_CORE.get());
        dropSelf(Nebulas.BLUE_NEBULA_REGROW_CORE.get());
        dropSelf(Nebulas.YELLOW_NEBULA_REGROW_CORE.get());
        dropSelf(Nebulas.RED_NEBULA_REGROW_CORE.get());

        add(EyeBloodBlocks.EYE_LEAVES.get(), createShearsOrSilkTouchOnlyDrop(EyeBloodBlocks.EYE_LEAVES.get()));
        add(RedOrbBlocks.SPIRO_LEAVES.get(), createLeavesDrops(RedOrbBlocks.SPIRO_LEAVES.get(), RedOrbBlocks.SPIRO_SAPLING.get(), 0.035F));

        // Nebulas
        dropSelf(Nebulas.YELLOW_NEBULA_LOG.get());
        dropSelf(Nebulas.BLUE_NEBULA_LOG.get());
        dropSelf(Nebulas.RED_NEBULA_LOG.get());
        dropSelf(Nebulas.PURPLE_NEBULA_LOG.get());
        dropSelf(Nebulas.YELLOW_NEBULA_PLANKS.get());
        dropSelf(Nebulas.BLUE_NEBULA_PLANKS.get());
        dropSelf(Nebulas.RED_NEBULA_PLANKS.get());
        dropSelf(Nebulas.PURPLE_NEBULA_PLANKS.get());
        dropSelf(Nebulas.YELLOW_NEBULA_PLANKS_BUTTON.get());
        dropSelf(Nebulas.BLUE_NEBULA_PLANKS_BUTTON.get());
        dropSelf(Nebulas.RED_NEBULA_PLANKS_BUTTON.get());
        dropSelf(Nebulas.PURPLE_NEBULA_PLANKS_BUTTON.get());
        dropSelf(Nebulas.YELLOW_NEBULA_PLANKS_FENCE.get());
        dropSelf(Nebulas.BLUE_NEBULA_PLANKS_FENCE.get());
        dropSelf(Nebulas.RED_NEBULA_PLANKS_FENCE.get());
        dropSelf(Nebulas.PURPLE_NEBULA_PLANKS_FENCE.get());
        dropSelf(Nebulas.YELLOW_NEBULA_PLANKS_FENCE_GATE.get());
        dropSelf(Nebulas.BLUE_NEBULA_PLANKS_FENCE_GATE.get());
        dropSelf(Nebulas.RED_NEBULA_PLANKS_FENCE_GATE.get());
        dropSelf(Nebulas.PURPLE_NEBULA_PLANKS_FENCE_GATE.get());
        add(Nebulas.YELLOW_NEBULA_PLANKS_SLAB.get(), createSlabItemTable(Nebulas.YELLOW_NEBULA_PLANKS_SLAB.get()));
        add(Nebulas.BLUE_NEBULA_PLANKS_SLAB.get(), createSlabItemTable(Nebulas.BLUE_NEBULA_PLANKS_SLAB.get()));
        add(Nebulas.RED_NEBULA_PLANKS_SLAB.get(), createSlabItemTable(Nebulas.RED_NEBULA_PLANKS_SLAB.get()));
        add(Nebulas.PURPLE_NEBULA_PLANKS_SLAB.get(), createSlabItemTable(Nebulas.PURPLE_NEBULA_PLANKS_SLAB.get()));
        dropSelf(Nebulas.YELLOW_NEBULA_PLANKS_STAIRS.get());
        dropSelf(Nebulas.BLUE_NEBULA_PLANKS_STAIRS.get());
        dropSelf(Nebulas.RED_NEBULA_PLANKS_STAIRS.get());
        dropSelf(Nebulas.PURPLE_NEBULA_PLANKS_STAIRS.get());
        add(Nebulas.RED_NEBULA_LEAVES.get(), customLeavesDrop(Nebulas.RED_NEBULA_LEAVES.get(), ModItems.RED_STAR.get(), 0f, 3.0f));
        add(Nebulas.BLUE_NEBULA_LEAVES.get(), customLeavesDrop(Nebulas.BLUE_NEBULA_LEAVES.get(), ModItems.BLUE_STAR.get(), 0f, 3.0f));
        add(Nebulas.YELLOW_NEBULA_LEAVES.get(), customLeavesDrop(Nebulas.YELLOW_NEBULA_LEAVES.get(), ModItems.YELLOW_STAR.get(), 0f, 3.0f));
        add(Nebulas.PURPLE_NEBULA_LEAVES.get(), customLeavesDrop(Nebulas.PURPLE_NEBULA_LEAVES.get(), ModItems.PURPLE_STAR.get(), 0f, 3.0f));

        // Hedge
        dropSelf(Hedges.ACACIA_HEDGE.get());
        dropSelf(Hedges.BIRCH_HEDGE.get());
        dropSelf(Hedges.CHERRY_HEDGE.get());
        dropSelf(Hedges.CURVE_HEDGE.get());
        dropSelf(Hedges.DARK_OAK_HEDGE.get());
        dropSelf(Hedges.DARKNESS_HEDGE.get());
        dropSelf(Hedges.JUNGLE_HEDGE.get());
        dropSelf(Hedges.MANGROVE_HEDGE.get());
        dropSelf(Hedges.MOON_HEDGE.get());
        dropSelf(Hedges.OAK_HEDGE.get());
        dropSelf(Hedges.PALE_HEDGE.get());
        dropSelf(Hedges.SPRUCE_HEDGE.get());
        dropSelf(Hedges.STAR_HEDGE.get());
        dropSelf(Hedges.YERI_HEDGE.get());
        dropSelf(Hedges.SPIRO_HEDGE.get());
        dropSelf(Hedges.TRUNN_HEDGE.get());
        add(StarBlocks.RED_STAR_BLOCK.get(), createSingleItemTable(ModItems.RED_STAR.get(), ConstantValue.exactly(14)));
        add(StarBlocks.BLUE_STAR_BLOCK.get(), createSingleItemTable(ModItems.BLUE_STAR.get(), ConstantValue.exactly(14)));
        add(StarBlocks.YELLOW_STAR_BLOCK.get(), createSingleItemTable(ModItems.YELLOW_STAR.get(), ConstantValue.exactly(14)));
        add(StarBlocks.PURPLE_STAR_BLOCK.get(), createSingleItemTable(ModItems.PURPLE_STAR.get(), ConstantValue.exactly(14)));

        // Chess
        dropSelf(Chess.CHESSBOARD.get());
        dropSelf(Chess.BLACK_CHESSBOARD.get());
        dropSelf(Chess.WHITE_CHESSBOARD.get());
        dropSelf(Chess.BLACK_BRICKS.get());
        dropSelf(Chess.WHITE_BRICKS.get());

        // Red Orb
        dropSelf(RedOrbBlocks.RED_ROCK.get());
        add(RedOrbBlocks.RED_ROCK_SLAB.get(), createSlabItemTable(RedOrbBlocks.RED_ROCK_SLAB.get()));
        dropSelf(RedOrbBlocks.RED_ROCK_STAIRS.get());
        dropSelf(RedOrbBlocks.POLISHED_RED_ROCK.get());
        dropSelf(RedOrbBlocks.YERI_LOG.get());
        dropSelf(RedOrbBlocks.YERI_SAPLING.get());
        dropPottedContents(RedOrbBlocks.POTTED_YERI_SAPLING.get());
        add(RedOrbBlocks.YERI_LEAVES.get(), createLeavesDrops(RedOrbBlocks.YERI_LEAVES.get(), RedOrbBlocks.YERI_SAPLING.get(), 0.035F));
        dropSelf(RedOrbBlocks.YERI_PLANKS.get());
        add(RedOrbBlocks.YERI_PLANKS_SLAB.get(), createSlabItemTable(RedOrbBlocks.YERI_PLANKS_SLAB.get()));
        dropSelf(RedOrbBlocks.YERI_PLANKS_STAIRS.get());
        dropSelf(RedOrbBlocks.YERI_PLANKS_FENCE.get());
        dropSelf(RedOrbBlocks.YERI_PLANKS_FENCE_GATE.get());
        dropSelf(RedOrbBlocks.YERI_PLANKS_BUTTON.get());
        dropSelf(RedOrbBlocks.RED_ORB_PLATFORM.get());
        dropSelf(RedOrbBlocks.GREEN_ROCK.get());
        dropSelf(RedOrbBlocks.SPIRO_LOG.get());
        dropSelf(RedOrbBlocks.SPIRO_SAPLING.get());
        dropPottedContents(RedOrbBlocks.POTTED_SPIRO_SAPLING.get());
        dropSelf(Hedges.SPIRO_HEDGE.get());
        add(RedOrbBlocks.GLASS_LOG.get(), createSingleItemTableWithSilkTouch(RedOrbBlocks.GLASS_LOG.get(), RedOrbBlocks.GLASS_LOG.get()));
    }

    public LootTable.Builder leavesDrops(Block leaves, Block sapling, Item itemDrop, float ... saplingChance) {
        HolderGetter impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createLeavesDrops(leaves, sapling, saplingChance).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).when(this.doesNotHaveSilkTouch()).add((LootPoolEntryContainer.Builder<?>)((LootPoolSingletonContainer.Builder)this.applyExplosionCondition(leaves, LootItem.lootTableItem(itemDrop))).when(BonusLevelTableCondition.bonusLevelFlatChance(impl.getOrThrow(Enchantments.FORTUNE), 0.005f, 0.0055555557f, 0.00625f, 0.008333334f, 0.025f))));
    }

    public LootTable.Builder addFlowerbedDrop(Block block) {
        return LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(
                                LootItem.lootTableItem(block)
                                        .apply(
                                                // This function checks the FLOWER_AMOUNT property and scales the drop count accordingly
                                                SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))
                                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                        .hasProperty(FlowerBedBlock.AMOUNT, 1)))
                                        )
                                        .apply(
                                                SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                        .hasProperty(FlowerBedBlock.AMOUNT, 2)))
                                        )
                                        .apply(
                                                SetItemCountFunction.setCount(ConstantValue.exactly(3.0F))
                                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                        .hasProperty(FlowerBedBlock.AMOUNT, 3)))
                                        )
                                        .apply(
                                                SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))
                                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                        .hasProperty(FlowerBedBlock.AMOUNT, 4)))
                                        )
                        )
        );
    }
}
*///? }
