package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.clases.moon.geode_fruit.GeodeFruit;
import com.github.starcatcher21.stargazer.block.clases.moon.geode_fruit.GeodeFruitStage;
import com.github.starcatcher21.stargazer.block.clases.moon.plants.MoonCrop;
import com.github.starcatcher21.stargazer.block.register.*;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
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

    public LootTable.Builder oreDrops(Block drop, Item raw, float min, float max) {
        HolderGetter impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(drop, (LootPoolEntryContainer.Builder)this.applyExplosionDecay(drop, ((LootPoolSingletonContainer.Builder)LootItem.lootTableItem(raw).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))).apply(ApplyBonusCount.addOreBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
    public LootTable.Builder customLeavesDrop(Block drop, Item raw, float min, float max) {
        HolderGetter impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchOrShearsDispatchTable(drop, (LootPoolEntryContainer.Builder)this.applyExplosionDecay(drop, ((LootPoolSingletonContainer.Builder)LootItem.lootTableItem(raw).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))).apply(ApplyBonusCount.addOreBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))));
    }

    public LootTable.Builder conditionDrop(Block crop, Item product, LootItemCondition.Builder condition) {
        return this.applyExplosionDecay(crop, LootTable.lootTable().withPool(LootPool.lootPool().add(((LootPoolSingletonContainer.Builder)LootItem.lootTableItem(product).when(condition)))));
    }

    public LootTable.Builder cropDrops(Block leaves, Item crop, float ... cropChance) {
        HolderGetter impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchOrShearsDispatchTable(leaves, (LootPoolEntryContainer.Builder<?>)((LootPoolSingletonContainer.Builder)this.applyExplosionCondition(leaves, LootItem.lootTableItem(crop))).when(BonusLevelTableCondition.bonusLevelFlatChance(impl.getOrThrow(Enchantments.FORTUNE), cropChance))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).when(this.doesNotHaveShearsOrSilkTouch()));
    }


    @Override
    public void generate() {
        dropSelf(ModBlock.NEGATIVE_BLOCK);
        dropSelf(ModBlock.NORED_BLOCK);
        dropSelf(ModBlock.NOGREEN_BLOCK);
        dropSelf(ModBlock.NOBLUE_BLOCK);
        dropSelf(Wander.PUROIL);
        dropSelf(StarBlocks.AURORA);
        dropSelf(MoonBlocks.COMET_BLOCK);
        dropSelf(Energy.STARGENERATOR);
        dropSelf(Energy.YELLOW_CABLE);
        dropSelf(Energy.BLUE_CABLE);
        dropSelf(Energy.RED_CABLE);
        dropSelf(Energy.PURPLE_CABLE);
        dropSelf(Energy.STARMACHINE_BLOCK);
        dropSelf(Energy.NIGHT_WATCHER);
        dropSelf(Energy.STAR_CRUSHER);
        add(Wander.BORIL, oreDrops(Wander.BORIL, Wander.PUROIL.asItem(), 1.0f, 1.0f));
        dropSelf(Wander.TRUNN_LOG);
        add(Wander.TRUNN_LEAVES, createLeavesDrops(Wander.TRUNN_LEAVES, Wander.TRUNN_SAPLING, 0.035F));
        dropSelf(ModBlock.GRAVE);
        add(ModBlock.INFESTED_CALCITE, oreDrops(ModBlock.INFESTED_CALCITE, Blocks.CALCITE.asItem(), 1.0f, 1.0f));
        add(ModBlock.BONE_LEAVES, customLeavesDrop(ModBlock.BONE_LEAVES, Items.BONE, 0f, 3.0f));
        dropSelf(ModBlock.BONEFLOWER);
        dropSelf(ModBlock.SPRINKLER);
        dropSelf(MoonBlocks.STAR_FORGE);
        dropSelf(MoonBlocks.STAR_STONE);
        dropSelf(StarBlocks.STAR_DISPLAY);
        add(MoonBlocks.FORGET_ME_NOW, addFlowerbedDrop(MoonBlocks.FORGET_ME_NOW));
        add(EyeBloodBlocks.EYES, addFlowerbedDrop(EyeBloodBlocks.EYES));
        dropPottedContents(MoonBlocks.POTTED_FORGET_ME_NOW);
        dropPottedContents(StarBlocks.POTTED_CELESTIAL_STAR_FLOWER);
        dropPottedContents(StarBlocks.POTTED_STAR_FLOWER);
        dropPottedContents(MoonBlocks.POTTED_MOON_SAPLING);
        dropPottedContents(MoonBlocks.POTTED_FULL_MOON_SAPLING);
        dropPottedContents(MoonBlocks.POTTED_CURVE_SAPLING);
        dropPottedContents(MoonBlocks.POTTED_PURPLE_MUSHROOM);
        dropPottedContents(StarBlocks.POTTED_STAR_SAPLING);
        dropPottedContents(MoonBlocks.POTTED_SPRUNGUS);
        dropPottedContents(Darkness.POTTED_GRADI);
        add(Darkness.GRADI, addFlowerbedDrop(Darkness.GRADI));
        dropSelf(MoonBlocks.SPRUNGUS);
        dropSelf(ModBlock.MOON_WELDER);
        // Moon
        add(MoonBlocks.MOON_LEAVES, createLeavesDrops(MoonBlocks.MOON_LEAVES, MoonBlocks.MOON_SAPLING, 0.035F));
        add(MoonBlocks.FULL_MOON_LEAVES, createLeavesDrops(MoonBlocks.FULL_MOON_LEAVES, MoonBlocks.FULL_MOON_SAPLING, 0.035F));
        dropSelf(MoonBlocks.SUN_ENRICHED_MOON_ROCK);
        dropSelf(MoonBlocks.POLISHED_SUN_ENRICHED_MOON_ROCK);
        add(MoonBlocks.CURVE_LEAVES, createLeavesDrops(MoonBlocks.CURVE_LEAVES, MoonBlocks.CURVE_SAPLING, 0.035F));
        add(MoonBlocks.MOON_ROCK_NYLIUM, createSingleItemTableWithSilkTouch(MoonBlocks.MOON_ROCK_NYLIUM, MoonBlocks.MOON_ROCK));
        add(Darkness.DYLIUM, createSingleItemTableWithSilkTouch(Darkness.DYLIUM, MoonBlocks.MOON_ROCK));
        dropSelf(Darkness.ROSE_OF_PAIN);
        dropPottedContents(Darkness.POTTED_ROSE_OF_PAIN);
        add(MoonBlocks.MOON_GRASS, cropDrops(MoonBlocks.MOON_GRASS, Crops.DRAGON_CARROT, 0.035F));
        add(MoonBlocks.TALL_MOON_GRASS, createShearsOrSilkTouchOnlyDrop(MoonBlocks.TALL_MOON_GRASS));
        add(MoonBlocks.MOON_FERN, cropDrops(MoonBlocks.MOON_FERN, Crops.BROODY, 0.035F));
        add(EyeBloodBlocks.EYE_FERN, cropDrops(EyeBloodBlocks.EYE_FERN, Crops.EYE_BALLS, 0.035F));
        add(MoonBlocks.STAR_TRAP, createShearsOrSilkTouchOnlyDrop(MoonBlocks.STAR_TRAP));
        add(MoonBlocks.GEODE_FRUIT, conditionDrop(MoonBlocks.GEODE_FRUIT, ModItems.GEODE_FRUIT, LootItemBlockStatePropertyCondition.hasBlockStateProperties(MoonBlocks.GEODE_FRUIT).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GeodeFruit.STAGE, GeodeFruitStage.grown))));
        add(Crops.DRAGON_CARROT_BLOCK, createCropDrops(Crops.DRAGON_CARROT_BLOCK, Crops.DRAGON_CARROT, Crops.DRAGON_CARROT, LootItemBlockStatePropertyCondition.hasBlockStateProperties(Crops.DRAGON_CARROT_BLOCK).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MoonCrop.AGE, 7))));
        add(Crops.BROODY_BLOCK, createCropDrops(Crops.BROODY_BLOCK, Crops.BROODY, Crops.BROODY, LootItemBlockStatePropertyCondition.hasBlockStateProperties(Crops.BROODY_BLOCK).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MoonCrop.AGE, 7))));
        add(Crops.EYE_BALLS_BLOCK, createCropDrops(Crops.EYE_BALLS_BLOCK, Crops.EYE_BALLS, Crops.EYE_BALLS, LootItemBlockStatePropertyCondition.hasBlockStateProperties(Crops.EYE_BALLS_BLOCK).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MoonCrop.AGE, 7))));
        dropSelf(EyeBloodBlocks.EYE_JAR);
        dropSelf(MoonBlocks.MOON_LOG);
        dropSelf(MoonBlocks.FULL_MOON_CORE);
        dropSelf(MoonBlocks.FULL_MOON_LOG);
        dropSelf(MoonBlocks.MOON_SAPLING);
        dropSelf(MoonBlocks.FULL_MOON_SAPLING);
        dropSelf(RedOrbBlocks.POINTY);
        dropSelf(MoonBlocks.STRIPPED_MOON_LOG);
        add(MoonBlocks.MOON_PLANKS_DOOR, createDoorTable(MoonBlocks.MOON_PLANKS_DOOR));
        dropSelf(EyeBloodBlocks.EYE_LOG);
        dropSelf(EyeBloodBlocks.STRIPPED_EYE_LOG);
        dropSelf(MoonBlocks.CURVE_LOG);
        dropSelf(MoonBlocks.STRIPPED_CURVE_LOG);
        dropSelf(MoonBlocks.CURVE_PLANKS);
        add(MoonBlocks.CURVE_PLANKS_DOOR, createDoorTable(MoonBlocks.CURVE_PLANKS_DOOR));
        add(MoonBlocks.CURVE_PLANKS_SLAB, createSlabItemTable(MoonBlocks.CURVE_PLANKS_SLAB));
        dropSelf(MoonBlocks.CURVE_PLANKS_STAIRS);
        dropSelf(MoonBlocks.CURVE_PLANKS_BUTTON);
        dropSelf(MoonBlocks.CURVE_PLANKS_FENCE);
        dropSelf(MoonBlocks.CURVE_PLANKS_FENCE_GATE);
        dropSelf(MoonBlocks.MOON_PLANKS);
        dropSelf(MoonBlocks.MOON_PLANKS_STAIRS);
        add(MoonBlocks.MOON_PLANKS_SLAB, createSlabItemTable(MoonBlocks.MOON_PLANKS_SLAB));
        dropSelf(MoonBlocks.MOON_PLANKS_BUTTON);
        dropSelf(MoonBlocks.MOON_PLANKS_FENCE);
        dropSelf(MoonBlocks.MOON_PLANKS_FENCE_GATE);
        dropSelf(MoonBlocks.RED_MOON_PLANKS);
        dropSelf(MoonBlocks.RED_MOON_PLANKS_STAIRS);
        add(MoonBlocks.RED_MOON_PLANKS_SLAB, createSlabItemTable(MoonBlocks.RED_MOON_PLANKS_SLAB));
        dropSelf(MoonBlocks.RED_MOON_PLANKS_BUTTON);
        dropSelf(MoonBlocks.RED_MOON_PLANKS_FENCE);
        dropSelf(MoonBlocks.RED_MOON_PLANKS_FENCE_GATE);
        dropSelf(MoonBlocks.BLUE_MOON_PLANKS);
        dropSelf(MoonBlocks.BLUE_MOON_PLANKS_STAIRS);
        add(MoonBlocks.BLUE_MOON_PLANKS_SLAB, createSlabItemTable(MoonBlocks.BLUE_MOON_PLANKS_SLAB));
        dropSelf(MoonBlocks.BLUE_MOON_PLANKS_BUTTON);
        dropSelf(MoonBlocks.BLUE_MOON_PLANKS_FENCE);
        dropSelf(MoonBlocks.BLUE_MOON_PLANKS_FENCE_GATE);
        dropSelf(MoonBlocks.PURPLE_MOON_PLANKS);
        dropSelf(MoonBlocks.PURPLE_MOON_PLANKS_STAIRS);
        add(MoonBlocks.PURPLE_MOON_PLANKS_SLAB, createSlabItemTable(MoonBlocks.PURPLE_MOON_PLANKS_SLAB));
        dropSelf(MoonBlocks.PURPLE_MOON_PLANKS_BUTTON);
        dropSelf(MoonBlocks.PURPLE_MOON_PLANKS_FENCE);
        dropSelf(MoonBlocks.PURPLE_MOON_PLANKS_FENCE_GATE);
        dropSelf(MoonBlocks.YELLOW_MOON_PLANKS);
        dropSelf(MoonBlocks.YELLOW_MOON_PLANKS_STAIRS);
        add(MoonBlocks.YELLOW_MOON_PLANKS_SLAB, createSlabItemTable(MoonBlocks.YELLOW_MOON_PLANKS_SLAB));
        dropSelf(MoonBlocks.YELLOW_MOON_PLANKS_BUTTON);
        dropSelf(MoonBlocks.YELLOW_MOON_PLANKS_FENCE);
        dropSelf(MoonBlocks.YELLOW_MOON_PLANKS_FENCE_GATE);
        dropSelf(MoonBlocks.MOON_ROCK);
        dropSelf(MoonBlocks.POLISHED_MOON_ROCK);
        dropSelf(MoonBlocks.MOON_ROCK_TILES);
        dropSelf(MoonBlocks.PURPLE_MOON_ROCK_TILES);
        dropOther(MoonBlocks.MOON_FARMLAND, MoonBlocks.MOON_ROCK);
        dropSelf(MoonBlocks.MOON_ROCK_BRICKS);
        add(MoonBlocks.MOON_ROCK_BRICKS_SLAB, createSlabItemTable(MoonBlocks.MOON_ROCK_BRICKS_SLAB));
        dropSelf(MoonBlocks.MOON_ROCK_BRICKS_STAIRS);
        dropSelf(MoonBlocks.CHISELED_MOON_ROCK_BRICKS);
        dropSelf(MoonBlocks.CRACKED_MOON_ROCK_BRICKS);
        dropSelf(MoonBlocks.BLACK_MOON_ROCK);
        dropSelf(MoonBlocks.POLISHED_BLACK_MOON_ROCK);
        dropSelf(MoonBlocks.POLISHED_BLACK_MOON_ROCK_PURPLE);
        dropSelf(MoonBlocks.PURPLE_MUSHROOM);
        add(MoonBlocks.PURPLE_MUSHROOM_BLOCK, block ->
                createMushroomBlockDrop(MoonBlocks.PURPLE_MUSHROOM_BLOCK, MoonBlocks.PURPLE_MUSHROOM)
        );
        add(MoonBlocks.PRISMATIC_ORE, oreDrops(MoonBlocks.PRISMATIC_ORE, ModItems.PRISMATIC_SHARD, 1, 2));
        dropSelf(MoonBlocks.PRISMATIC_SHARD_BLOCK);
        dropSelf(ModBlock.BONEFLOWER);
        dropPottedContents(ModBlock.POTTED_BONEFLOWER);
        dropPottedContents(RedOrbBlocks.POTTED_POINTY);
        dropSelf(Nebulas.RED_TENTACLE_FLOWER);
        dropSelf(Nebulas.BLUE_TENTACLE_FLOWER);
        dropSelf(Nebulas.YELLOW_TENTACLE_FLOWER);
        dropSelf(Nebulas.PURPLE_TENTACLE_FLOWER);
        dropPottedContents(Nebulas.POTTED_BLUE_TENTACLE_FLOWER);
        dropPottedContents(Nebulas.POTTED_RED_TENTACLE_FLOWER);
        dropPottedContents(Nebulas.POTTED_YELLOW_TENTACLE_FLOWER);
        dropPottedContents(Nebulas.POTTED_PURPLE_TENTACLE_FLOWER);
        // Star
        dropSelf(StarBlocks.COSMIC_BLOCK);
        add(StarBlocks.STAR_LEAVES, createLeavesDrops(StarBlocks.STAR_LEAVES, StarBlocks.STAR_SAPLING, 0.035F));
        dropSelf(StarBlocks.STAR_LOG);
        dropSelf(StarBlocks.STRIPPED_STAR_LOG);
        dropSelf(StarBlocks.STAR_PLANKS);
        add(StarBlocks.STAR_PLANKS_DOOR, createDoorTable(StarBlocks.STAR_PLANKS_DOOR));
        dropSelf(StarBlocks.STAR_PLANKS_STAIRS);
        add(StarBlocks.STAR_PLANKS_SLAB, createSlabItemTable(StarBlocks.STAR_PLANKS_SLAB));
        dropSelf(StarBlocks.STAR_PLANKS_BUTTON);
        dropSelf(StarBlocks.STAR_PLANKS_FENCE);
        dropSelf(StarBlocks.STAR_PLANKS_FENCE_GATE);
        dropSelf(StarBlocks.STAR_SAPLING);
        dropSelf(StarBlocks.STAR_FLOWER);
        dropSelf(StarBlocks.CELESTIAL_STAR_FLOWER);
        // Darkness
        add(Darkness.DARKNESS_PLANKS_DOOR, createDoorTable(Darkness.DARKNESS_PLANKS_DOOR));
        dropSelf(Darkness.LOG_OF_DARKNESS);
        dropSelf(Darkness.STRIPPED_LOG_OF_DARKNESS);
        dropSelf(Darkness.DARKNESS_PLANKS);
        dropSelf(Darkness.DARKNESS_PLANKS_STAIRS);
        add(Darkness.DARKNESS_PLANKS_SLAB, createSlabItemTable(Darkness.DARKNESS_PLANKS_SLAB));
        dropSelf(Darkness.DARKNESS_PLANKS_BUTTON);
        dropSelf(Darkness.DARKNESS_PLANKS_FENCE);
        dropSelf(Darkness.DARKNESS_PLANKS_FENCE_GATE);
        add(Darkness.DARKNESS_LEAVES, createLeavesDrops(Darkness.DARKNESS_LEAVES, Darkness.DARKNESS_SAPLING, 0.035F));
        // Nebulas
        dropSelf(Nebulas.YELLOW_NEBULA_LOG);
        dropSelf(Nebulas.BLUE_NEBULA_LOG);
        dropSelf(Nebulas.RED_NEBULA_LOG);
        dropSelf(Nebulas.PURPLE_NEBULA_LOG);
        dropSelf(Nebulas.YELLOW_NEBULA_PLANKS);
        dropSelf(Nebulas.BLUE_NEBULA_PLANKS);
        dropSelf(Nebulas.RED_NEBULA_PLANKS);
        dropSelf(Nebulas.PURPLE_NEBULA_PLANKS);
        dropSelf(Nebulas.YELLOW_NEBULA_PLANKS_BUTTON);
        dropSelf(Nebulas.BLUE_NEBULA_PLANKS_BUTTON);
        dropSelf(Nebulas.RED_NEBULA_PLANKS_BUTTON);
        dropSelf(Nebulas.PURPLE_NEBULA_PLANKS_BUTTON);
        dropSelf(Nebulas.YELLOW_NEBULA_PLANKS_FENCE);
        dropSelf(Nebulas.BLUE_NEBULA_PLANKS_FENCE);
        dropSelf(Nebulas.RED_NEBULA_PLANKS_FENCE);
        dropSelf(Nebulas.PURPLE_NEBULA_PLANKS_FENCE);
        dropSelf(Nebulas.YELLOW_NEBULA_PLANKS_FENCE_GATE);
        dropSelf(Nebulas.BLUE_NEBULA_PLANKS_FENCE_GATE);
        dropSelf(Nebulas.RED_NEBULA_PLANKS_FENCE_GATE);
        dropSelf(Nebulas.PURPLE_NEBULA_PLANKS_FENCE_GATE);
        add(Nebulas.YELLOW_NEBULA_PLANKS_SLAB, createSlabItemTable(Nebulas.YELLOW_NEBULA_PLANKS_SLAB));
        add(Nebulas.BLUE_NEBULA_PLANKS_SLAB, createSlabItemTable(Nebulas.BLUE_NEBULA_PLANKS_SLAB));
        add(Nebulas.RED_NEBULA_PLANKS_SLAB, createSlabItemTable(Nebulas.RED_NEBULA_PLANKS_SLAB));
        add(Nebulas.PURPLE_NEBULA_PLANKS_SLAB, createSlabItemTable(Nebulas.PURPLE_NEBULA_PLANKS_SLAB));
        dropSelf(Nebulas.YELLOW_NEBULA_PLANKS_STAIRS);
        dropSelf(Nebulas.BLUE_NEBULA_PLANKS_STAIRS);
        dropSelf(Nebulas.RED_NEBULA_PLANKS_STAIRS);
        dropSelf(Nebulas.PURPLE_NEBULA_PLANKS_STAIRS);
        add(Nebulas.RED_NEBULA_LEAVES, customLeavesDrop(Nebulas.RED_NEBULA_LEAVES, ModItems.RED_STAR, 0f, 3.0f));
        add(Nebulas.BLUE_NEBULA_LEAVES, customLeavesDrop(Nebulas.BLUE_NEBULA_LEAVES, ModItems.BLUE_STAR, 0f, 3.0f));
        add(Nebulas.YELLOW_NEBULA_LEAVES, customLeavesDrop(Nebulas.YELLOW_NEBULA_LEAVES, ModItems.YELLOW_STAR, 0f, 3.0f));
        add(Nebulas.PURPLE_NEBULA_LEAVES, customLeavesDrop(Nebulas.PURPLE_NEBULA_LEAVES, ModItems.PURPLE_STAR, 0f, 3.0f));
        // Hedge
        dropSelf(Hedges.ACACIA_HEDGE);
        dropSelf(Hedges.BIRCH_HEDGE);
        dropSelf(Hedges.CHERRY_HEDGE);
        dropSelf(Hedges.CURVE_HEDGE);
        dropSelf(Hedges.DARK_OAK_HEDGE);
        dropSelf(Hedges.DARKNESS_HEDGE);
        dropSelf(Hedges.JUNGLE_HEDGE);
        dropSelf(Hedges.MANGROVE_HEDGE);
        dropSelf(Hedges.MOON_HEDGE);
        dropSelf(Hedges.OAK_HEDGE);
        dropSelf(Hedges.PALE_HEDGE);
        dropSelf(Hedges.SPRUCE_HEDGE);
        dropSelf(Hedges.STAR_HEDGE);
        dropSelf(Hedges.YERI_HEDGE);
        dropSelf(Hedges.SPIRO_HEDGE);
        dropSelf(Hedges.TRUNN_HEDGE);
        add(StarBlocks.RED_STAR_BLOCK, createSingleItemTable(ModItems.RED_STAR, ConstantValue.exactly(14)));
        add(StarBlocks.BLUE_STAR_BLOCK, createSingleItemTable(ModItems.BLUE_STAR, ConstantValue.exactly(14)));
        add(StarBlocks.YELLOW_STAR_BLOCK, createSingleItemTable(ModItems.YELLOW_STAR, ConstantValue.exactly(14)));
        add(StarBlocks.PURPLE_STAR_BLOCK, createSingleItemTable(ModItems.PURPLE_STAR, ConstantValue.exactly(14)));
        // Chess
        dropSelf(Chess.CHESSBOARD);
        dropSelf(Chess.BLACK_CHESSBOARD);
        dropSelf(Chess.WHITE_CHESSBOARD);
        dropSelf(Chess.BLACK_BRICKS);
        dropSelf(Chess.WHITE_BRICKS);
        // Red Orb
        dropSelf(RedOrbBlocks.RED_ROCK);
        add(RedOrbBlocks.RED_ROCK_SLAB, createSlabItemTable(RedOrbBlocks.RED_ROCK_SLAB));
        dropSelf(RedOrbBlocks.RED_ROCK_STAIRS);
        dropSelf(RedOrbBlocks.POLISHED_RED_ROCK);
        dropSelf(RedOrbBlocks.YERI_LOG);
        dropSelf(RedOrbBlocks.YERI_SAPLING);
        dropPottedContents(RedOrbBlocks.POTTED_YERI_SAPLING);
        add(RedOrbBlocks.YERI_LEAVES, createLeavesDrops(RedOrbBlocks.YERI_LEAVES, RedOrbBlocks.YERI_SAPLING, 0.035F));
        dropSelf(RedOrbBlocks.YERI_PLANKS);
        add(RedOrbBlocks.YERI_PLANKS_SLAB, createSlabItemTable(RedOrbBlocks.YERI_PLANKS_SLAB));
        dropSelf(RedOrbBlocks.YERI_PLANKS_STAIRS);
        dropSelf(RedOrbBlocks.YERI_PLANKS_FENCE);
        dropSelf(RedOrbBlocks.YERI_PLANKS_FENCE_GATE);
        dropSelf(RedOrbBlocks.YERI_PLANKS_BUTTON);
        dropSelf(RedOrbBlocks.RED_ORB_PLATFORM);
        dropSelf(RedOrbBlocks.GREEN_ROCK);
        dropSelf(RedOrbBlocks.SPIRO_LOG);
        dropSelf(RedOrbBlocks.SPIRO_SAPLING);
        dropPottedContents(RedOrbBlocks.POTTED_SPIRO_SAPLING);
        dropSelf(Hedges.SPIRO_HEDGE);
        add(RedOrbBlocks.GLASS_LOG, oreDrops(RedOrbBlocks.GLASS_LOG, RedOrbBlocks.GLASS_LOG.asItem(), 0.0f, 0.0f));
    }

    public LootTable.Builder leavesDrops(Block leaves, Block sapling, Item itemDrop, float ... saplingChance) {
        HolderGetter impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createLeavesDrops(leaves, sapling, saplingChance).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).when(this.doesNotHaveShearsOrSilkTouch()).add((LootPoolEntryContainer.Builder<?>)((LootPoolSingletonContainer.Builder)this.applyExplosionCondition(leaves, LootItem.lootTableItem(itemDrop))).when(BonusLevelTableCondition.bonusLevelFlatChance(impl.getOrThrow(Enchantments.FORTUNE), 0.005f, 0.0055555557f, 0.00625f, 0.008333334f, 0.025f))));
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
