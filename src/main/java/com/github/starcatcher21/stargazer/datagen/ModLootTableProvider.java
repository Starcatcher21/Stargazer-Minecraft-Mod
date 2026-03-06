package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.clases.moon.geode_fruit.GeodeFruit;
import com.github.starcatcher21.stargazer.block.clases.moon.geode_fruit.GeodeFruitStage;
import com.github.starcatcher21.stargazer.block.clases.moon.plants.MoonCrop;
import com.github.starcatcher21.stargazer.block.register.*;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public LootTable.Builder oreDrops(Block drop, Item raw, float min, float max) {
        RegistryEntryLookup impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, (LootPoolEntry.Builder)this.applyExplosionDecay(drop, ((LeafEntry.Builder)ItemEntry.builder(raw).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max)))).apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
    public LootTable.Builder customLeavesDrop(Block drop, Item raw, float min, float max) {
        RegistryEntryLookup impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouchOrShears(drop, (LootPoolEntry.Builder)this.applyExplosionDecay(drop, ((LeafEntry.Builder)ItemEntry.builder(raw).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max)))).apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }

    public LootTable.Builder conditionDrop(Block crop, Item product, LootCondition.Builder condition) {
        return this.applyExplosionDecay(crop, LootTable.builder().pool(LootPool.builder().with(((LeafEntry.Builder)ItemEntry.builder(product).conditionally(condition)))));
    }

    @Override
    public void generate() {
        addDrop(ModBlock.NEGATIVE_BLOCK);
        addDrop(ModBlock.GRAVE);
        addDrop(ModBlock.INFESTED_CALCITE, oreDrops(ModBlock.INFESTED_CALCITE, Blocks.CALCITE.asItem(), 1.0f, 1.0f));
        addDrop(ModBlock.BONE_LEAVES, customLeavesDrop(ModBlock.BONE_LEAVES, Items.BONE, 0f, 3.0f));
        addDrop(ModBlock.SPRINKLER);
        addDrop(MoonBlocks.STAR_FORGE);
        addDrop(MoonBlocks.STAR_STONE);
        addDrop(MoonBlocks.FORGET_ME_NOW);
        addPottedPlantDrops(MoonBlocks.POTTED_FORGET_ME_NOW);
        addPottedPlantDrops(StarBlocks.POTTED_CELESTIAL_STAR_FLOWER);
        addPottedPlantDrops(StarBlocks.POTTED_STAR_FLOWER);
        addPottedPlantDrops(MoonBlocks.POTTED_MOON_SAPLING);
        addPottedPlantDrops(MoonBlocks.POTTED_CURVE_SAPLING);
        addPottedPlantDrops(MoonBlocks.POTTED_PURPLE_MUSHROOM);
        addPottedPlantDrops(StarBlocks.POTTED_STAR_SAPLING);
        // Moon
        addDrop(MoonBlocks.MOON_LEAVES, leavesDrops(MoonBlocks.MOON_LEAVES, MoonBlocks.MOON_SAPLING, 0.035F));
        addDrop(MoonBlocks.CURVE_LEAVES, leavesDrops(MoonBlocks.CURVE_LEAVES, MoonBlocks.CURVE_SAPLING, 0.035F));
        addDrop(MoonBlocks.MOON_ROCK_NYLIUM, drops(MoonBlocks.MOON_ROCK_NYLIUM, MoonBlocks.MOON_ROCK));
        addDrop(MoonBlocks.MOON_GRASS, dropsWithSilkTouchOrShears(MoonBlocks.MOON_GRASS));
        addDrop(MoonBlocks.TALL_MOON_GRASS, dropsWithSilkTouchOrShears(MoonBlocks.TALL_MOON_GRASS));
        addDrop(MoonBlocks.MOON_FERN, dropsWithSilkTouchOrShears(MoonBlocks.MOON_FERN));
        addDrop(MoonBlocks.STAR_TRAP, dropsWithSilkTouchOrShears(MoonBlocks.STAR_TRAP));
        addDrop(MoonBlocks.GEODE_FRUIT, conditionDrop(MoonBlocks.GEODE_FRUIT, ModItems.GEODE_FRUIT, BlockStatePropertyLootCondition.builder(MoonBlocks.GEODE_FRUIT).properties(StatePredicate.Builder.create().exactMatch(GeodeFruit.STAGE, GeodeFruitStage.grown))));
        addDrop(Crops.DRAGON_CARROT_BLOCK, cropDrops(Crops.DRAGON_CARROT_BLOCK, Crops.DRAGON_CARROT, Crops.DRAGON_CARROT, BlockStatePropertyLootCondition.builder(Crops.DRAGON_CARROT_BLOCK).properties(StatePredicate.Builder.create().exactMatch(MoonCrop.AGE, 7))));
        addDrop(EyeBloodBlocks.EYE_JAR);
        addDrop(MoonBlocks.MOON_LOG);
        addDrop(MoonBlocks.MOON_SAPLING);
        addDrop(MoonBlocks.STRIPPED_MOON_LOG);
        addDrop(MoonBlocks.MOON_PLANKS_DOOR, doorDrops(MoonBlocks.MOON_PLANKS_DOOR));
        addDrop(EyeBloodBlocks.EYE_LOG);
        addDrop(EyeBloodBlocks.STRIPPED_EYE_LOG);
        addDrop(MoonBlocks.CURVE_LOG);
        addDrop(MoonBlocks.STRIPPED_CURVE_LOG);
        addDrop(MoonBlocks.CURVE_PLANKS);
        addDrop(MoonBlocks.CURVE_PLANKS_SLAB);
        addDrop(MoonBlocks.CURVE_PLANKS_STAIRS);
        addDrop(MoonBlocks.CURVE_PLANKS_BUTTON);
        addDrop(MoonBlocks.CURVE_PLANKS_FENCE);
        addDrop(MoonBlocks.CURVE_PLANKS_FENCE_GATE);
        addDrop(MoonBlocks.MOON_PLANKS);
        addDrop(MoonBlocks.MOON_PLANKS_STAIRS);
        addDrop(MoonBlocks.MOON_PLANKS_SLAB);
        addDrop(MoonBlocks.MOON_PLANKS_BUTTON);
        addDrop(MoonBlocks.MOON_PLANKS_FENCE);
        addDrop(MoonBlocks.MOON_PLANKS_FENCE_GATE);
        addDrop(MoonBlocks.RED_MOON_PLANKS);
        addDrop(MoonBlocks.RED_MOON_PLANKS_STAIRS);
        addDrop(MoonBlocks.RED_MOON_PLANKS_SLAB);
        addDrop(MoonBlocks.RED_MOON_PLANKS_BUTTON);
        addDrop(MoonBlocks.RED_MOON_PLANKS_FENCE);
        addDrop(MoonBlocks.RED_MOON_PLANKS_FENCE_GATE);
        addDrop(MoonBlocks.BLUE_MOON_PLANKS);
        addDrop(MoonBlocks.BLUE_MOON_PLANKS_STAIRS);
        addDrop(MoonBlocks.BLUE_MOON_PLANKS_SLAB);
        addDrop(MoonBlocks.BLUE_MOON_PLANKS_BUTTON);
        addDrop(MoonBlocks.BLUE_MOON_PLANKS_FENCE);
        addDrop(MoonBlocks.BLUE_MOON_PLANKS_FENCE_GATE);
        addDrop(MoonBlocks.PURPLE_MOON_PLANKS);
        addDrop(MoonBlocks.PURPLE_MOON_PLANKS_STAIRS);
        addDrop(MoonBlocks.PURPLE_MOON_PLANKS_SLAB);
        addDrop(MoonBlocks.PURPLE_MOON_PLANKS_BUTTON);
        addDrop(MoonBlocks.PURPLE_MOON_PLANKS_FENCE);
        addDrop(MoonBlocks.PURPLE_MOON_PLANKS_FENCE_GATE);
        addDrop(MoonBlocks.YELLOW_MOON_PLANKS);
        addDrop(MoonBlocks.YELLOW_MOON_PLANKS_STAIRS);
        addDrop(MoonBlocks.YELLOW_MOON_PLANKS_SLAB);
        addDrop(MoonBlocks.YELLOW_MOON_PLANKS_BUTTON);
        addDrop(MoonBlocks.YELLOW_MOON_PLANKS_FENCE);
        addDrop(MoonBlocks.YELLOW_MOON_PLANKS_FENCE_GATE);
        addDrop(MoonBlocks.MOON_ROCK);
        addDrop(MoonBlocks.POLISHED_MOON_ROCK);
        addDrop(MoonBlocks.MOON_ROCK_TILES);
        addDrop(MoonBlocks.PURPLE_MOON_ROCK_TILES);
        addDrop(MoonBlocks.MOON_FARMLAND, MoonBlocks.MOON_ROCK);
        addDrop(MoonBlocks.MOON_ROCK_BRICKS);
        addDrop(MoonBlocks.MOON_ROCK_BRICKS_SLAB);
        addDrop(MoonBlocks.MOON_ROCK_BRICKS_STAIRS);
        addDrop(MoonBlocks.CHISELED_MOON_ROCK_BRICKS);
        addDrop(MoonBlocks.CRACKED_MOON_ROCK_BRICKS);
        addDrop(MoonBlocks.BLACK_MOON_ROCK);
        addDrop(MoonBlocks.POLISHED_BLACK_MOON_ROCK);
        addDrop(MoonBlocks.POLISHED_BLACK_MOON_ROCK_PURPLE);
        addDrop(MoonBlocks.PURPLE_MUSHROOM);
        addDrop(MoonBlocks.PURPLE_MUSHROOM_BLOCK, block ->
                mushroomBlockDrops(MoonBlocks.PURPLE_MUSHROOM_BLOCK, MoonBlocks.PURPLE_MUSHROOM)
        );
        addDrop(MoonBlocks.PRISMATIC_ORE, oreDrops(MoonBlocks.PRISMATIC_ORE, ModItems.PRISMATIC_SHARD, 1, 2));
        // Star
        addDrop(StarBlocks.COSMIC_BLOCK);
        addDrop(StarBlocks.STAR_LEAVES, leavesDrops(StarBlocks.STAR_LEAVES, StarBlocks.STAR_SAPLING, 0.035F));
        addDrop(StarBlocks.STAR_LOG);
        addDrop(StarBlocks.STRIPPED_STAR_LOG);
        addDrop(StarBlocks.STAR_PLANKS);
        addDrop(StarBlocks.STAR_PLANKS_STAIRS);
        addDrop(StarBlocks.STAR_PLANKS_SLAB);
        addDrop(StarBlocks.STAR_PLANKS_BUTTON);
        addDrop(StarBlocks.STAR_PLANKS_FENCE);
        addDrop(StarBlocks.STAR_PLANKS_FENCE_GATE);
        addDrop(StarBlocks.STAR_SAPLING);
        addDrop(StarBlocks.STAR_FLOWER);
        addDrop(StarBlocks.CELESTIAL_STAR_FLOWER);
        // Darkness
        addDrop(Darkness.LOG_OF_DARKNESS);
        addDrop(Darkness.STRIPPED_LOG_OF_DARKNESS);
        addDrop(Darkness.DARKNESS_PLANKS);
        addDrop(Darkness.DARKNESS_PLANKS_STAIRS);
        addDrop(Darkness.DARKNESS_PLANKS_SLAB);
        addDrop(Darkness.DARKNESS_PLANKS_BUTTON);
        addDrop(Darkness.DARKNESS_PLANKS_FENCE);
        addDrop(Darkness.DARKNESS_PLANKS_FENCE_GATE);
    }
}
