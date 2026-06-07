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
import net.minecraft.block.FlowerbedBlock;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
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

    public LootTable.Builder cropDrops(Block leaves, Item crop, float ... cropChance) {
        RegistryEntryLookup impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouchOrShears(leaves, (LootPoolEntry.Builder<?>)((LeafEntry.Builder)this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(crop))).conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), cropChance))).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).conditionally(this.createWithoutShearsOrSilkTouchCondition()));
    }


    @Override
    public void generate() {
        addDrop(ModBlock.NEGATIVE_BLOCK);
        addDrop(ModBlock.GRAVE);
        addDrop(ModBlock.INFESTED_CALCITE, oreDrops(ModBlock.INFESTED_CALCITE, Blocks.CALCITE.asItem(), 1.0f, 1.0f));
        addDrop(ModBlock.BONE_LEAVES, customLeavesDrop(ModBlock.BONE_LEAVES, Items.BONE, 0f, 3.0f));
        addDrop(ModBlock.BONEFLOWER);
        addDrop(ModBlock.SPRINKLER);
        addDrop(MoonBlocks.STAR_FORGE);
        addDrop(MoonBlocks.STAR_STONE);
        addDrop(MoonBlocks.FORGET_ME_NOW, addFlowerbedDrop(MoonBlocks.FORGET_ME_NOW));
        addDrop(EyeBloodBlocks.EYES, addFlowerbedDrop(EyeBloodBlocks.EYES));
        addPottedPlantDrops(MoonBlocks.POTTED_FORGET_ME_NOW);
        addPottedPlantDrops(StarBlocks.POTTED_CELESTIAL_STAR_FLOWER);
        addPottedPlantDrops(StarBlocks.POTTED_STAR_FLOWER);
        addPottedPlantDrops(MoonBlocks.POTTED_MOON_SAPLING);
        addPottedPlantDrops(MoonBlocks.POTTED_FULL_MOON_SAPLING);
        addPottedPlantDrops(MoonBlocks.POTTED_CURVE_SAPLING);
        addPottedPlantDrops(MoonBlocks.POTTED_PURPLE_MUSHROOM);
        addPottedPlantDrops(StarBlocks.POTTED_STAR_SAPLING);
        addPottedPlantDrops(MoonBlocks.POTTED_SPRUNGUS);
        addDrop(MoonBlocks.SPRUNGUS);
        addDrop(ModBlock.MOON_WELDER);
        // Moon
        addDrop(MoonBlocks.MOON_LEAVES, leavesDrops(MoonBlocks.MOON_LEAVES, MoonBlocks.MOON_SAPLING, 0.035F));
        addDrop(MoonBlocks.FULL_MOON_LEAVES, leavesDrops(MoonBlocks.FULL_MOON_LEAVES, MoonBlocks.FULL_MOON_SAPLING, 0.035F));
        addDrop(MoonBlocks.SUN_ENRICHED_MOON_ROCK);
        addDrop(MoonBlocks.POLISHED_SUN_ENRICHED_MOON_ROCK);
        addDrop(MoonBlocks.CURVE_LEAVES, leavesDrops(MoonBlocks.CURVE_LEAVES, MoonBlocks.CURVE_SAPLING, 0.035F));
        addDrop(MoonBlocks.MOON_ROCK_NYLIUM, drops(MoonBlocks.MOON_ROCK_NYLIUM, MoonBlocks.MOON_ROCK));
        addDrop(Darkness.DYLIUM, drops(Darkness.DYLIUM, MoonBlocks.MOON_ROCK));
        addDrop(Darkness.ROSE_OF_PAIN);
        addPottedPlantDrops(Darkness.POTTED_ROSE_OF_PAIN);
        addDrop(MoonBlocks.MOON_GRASS, cropDrops(MoonBlocks.MOON_GRASS, Crops.DRAGON_CARROT, 0.035F));
        addDrop(MoonBlocks.TALL_MOON_GRASS, dropsWithSilkTouchOrShears(MoonBlocks.TALL_MOON_GRASS));
        addDrop(MoonBlocks.MOON_FERN, cropDrops(MoonBlocks.MOON_FERN, Crops.BROODY, 0.035F));
        addDrop(EyeBloodBlocks.EYE_FERN, cropDrops(EyeBloodBlocks.EYE_FERN, Crops.EYE_BALLS, 0.035F));
        addDrop(MoonBlocks.STAR_TRAP, dropsWithSilkTouchOrShears(MoonBlocks.STAR_TRAP));
        addDrop(MoonBlocks.GEODE_FRUIT, conditionDrop(MoonBlocks.GEODE_FRUIT, ModItems.GEODE_FRUIT, BlockStatePropertyLootCondition.builder(MoonBlocks.GEODE_FRUIT).properties(StatePredicate.Builder.create().exactMatch(GeodeFruit.STAGE, GeodeFruitStage.grown))));
        addDrop(Crops.DRAGON_CARROT_BLOCK, cropDrops(Crops.DRAGON_CARROT_BLOCK, Crops.DRAGON_CARROT, Crops.DRAGON_CARROT, BlockStatePropertyLootCondition.builder(Crops.DRAGON_CARROT_BLOCK).properties(StatePredicate.Builder.create().exactMatch(MoonCrop.AGE, 7))));
        addDrop(Crops.BROODY_BLOCK, cropDrops(Crops.BROODY_BLOCK, Crops.BROODY, Crops.BROODY, BlockStatePropertyLootCondition.builder(Crops.BROODY_BLOCK).properties(StatePredicate.Builder.create().exactMatch(MoonCrop.AGE, 7))));
        addDrop(Crops.EYE_BALLS_BLOCK, cropDrops(Crops.EYE_BALLS_BLOCK, Crops.EYE_BALLS, Crops.EYE_BALLS, BlockStatePropertyLootCondition.builder(Crops.EYE_BALLS_BLOCK).properties(StatePredicate.Builder.create().exactMatch(MoonCrop.AGE, 7))));
        addDrop(EyeBloodBlocks.EYE_JAR);
        addDrop(MoonBlocks.MOON_LOG);
        addDrop(MoonBlocks.FULL_MOON_CORE);
        addDrop(MoonBlocks.FULL_MOON_LOG);
        addDrop(MoonBlocks.MOON_SAPLING);
        addDrop(MoonBlocks.FULL_MOON_SAPLING);
        addDrop(MoonBlocks.STRIPPED_MOON_LOG);
        addDrop(MoonBlocks.MOON_PLANKS_DOOR, doorDrops(MoonBlocks.MOON_PLANKS_DOOR));
        addDrop(EyeBloodBlocks.EYE_LOG);
        addDrop(EyeBloodBlocks.STRIPPED_EYE_LOG);
        addDrop(MoonBlocks.CURVE_LOG);
        addDrop(MoonBlocks.STRIPPED_CURVE_LOG);
        addDrop(MoonBlocks.CURVE_PLANKS);
        addDrop(MoonBlocks.CURVE_PLANKS_DOOR, doorDrops(MoonBlocks.CURVE_PLANKS_DOOR));
        addDrop(MoonBlocks.CURVE_PLANKS_SLAB, slabDrops(MoonBlocks.CURVE_PLANKS_SLAB));
        addDrop(MoonBlocks.CURVE_PLANKS_STAIRS);
        addDrop(MoonBlocks.CURVE_PLANKS_BUTTON);
        addDrop(MoonBlocks.CURVE_PLANKS_FENCE);
        addDrop(MoonBlocks.CURVE_PLANKS_FENCE_GATE);
        addDrop(MoonBlocks.MOON_PLANKS);
        addDrop(MoonBlocks.MOON_PLANKS_STAIRS);
        addDrop(MoonBlocks.MOON_PLANKS_SLAB, slabDrops(MoonBlocks.MOON_PLANKS_SLAB));
        addDrop(MoonBlocks.MOON_PLANKS_BUTTON);
        addDrop(MoonBlocks.MOON_PLANKS_FENCE);
        addDrop(MoonBlocks.MOON_PLANKS_FENCE_GATE);
        addDrop(MoonBlocks.RED_MOON_PLANKS);
        addDrop(MoonBlocks.RED_MOON_PLANKS_STAIRS);
        addDrop(MoonBlocks.RED_MOON_PLANKS_SLAB, slabDrops(MoonBlocks.RED_MOON_PLANKS_SLAB));
        addDrop(MoonBlocks.RED_MOON_PLANKS_BUTTON);
        addDrop(MoonBlocks.RED_MOON_PLANKS_FENCE);
        addDrop(MoonBlocks.RED_MOON_PLANKS_FENCE_GATE);
        addDrop(MoonBlocks.BLUE_MOON_PLANKS);
        addDrop(MoonBlocks.BLUE_MOON_PLANKS_STAIRS);
        addDrop(MoonBlocks.BLUE_MOON_PLANKS_SLAB, slabDrops(MoonBlocks.BLUE_MOON_PLANKS_SLAB));
        addDrop(MoonBlocks.BLUE_MOON_PLANKS_BUTTON);
        addDrop(MoonBlocks.BLUE_MOON_PLANKS_FENCE);
        addDrop(MoonBlocks.BLUE_MOON_PLANKS_FENCE_GATE);
        addDrop(MoonBlocks.PURPLE_MOON_PLANKS);
        addDrop(MoonBlocks.PURPLE_MOON_PLANKS_STAIRS);
        addDrop(MoonBlocks.PURPLE_MOON_PLANKS_SLAB, slabDrops(MoonBlocks.PURPLE_MOON_PLANKS_SLAB));
        addDrop(MoonBlocks.PURPLE_MOON_PLANKS_BUTTON);
        addDrop(MoonBlocks.PURPLE_MOON_PLANKS_FENCE);
        addDrop(MoonBlocks.PURPLE_MOON_PLANKS_FENCE_GATE);
        addDrop(MoonBlocks.YELLOW_MOON_PLANKS);
        addDrop(MoonBlocks.YELLOW_MOON_PLANKS_STAIRS);
        addDrop(MoonBlocks.YELLOW_MOON_PLANKS_SLAB, slabDrops(MoonBlocks.YELLOW_MOON_PLANKS_SLAB));
        addDrop(MoonBlocks.YELLOW_MOON_PLANKS_BUTTON);
        addDrop(MoonBlocks.YELLOW_MOON_PLANKS_FENCE);
        addDrop(MoonBlocks.YELLOW_MOON_PLANKS_FENCE_GATE);
        addDrop(MoonBlocks.MOON_ROCK);
        addDrop(MoonBlocks.POLISHED_MOON_ROCK);
        addDrop(MoonBlocks.MOON_ROCK_TILES);
        addDrop(MoonBlocks.PURPLE_MOON_ROCK_TILES);
        addDrop(MoonBlocks.MOON_FARMLAND, MoonBlocks.MOON_ROCK);
        addDrop(MoonBlocks.MOON_ROCK_BRICKS);
        addDrop(MoonBlocks.MOON_ROCK_BRICKS_SLAB, slabDrops(MoonBlocks.MOON_ROCK_BRICKS_SLAB));
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
        addDrop(MoonBlocks.PRISMATIC_SHARD_BLOCK);
        addDrop(ModBlock.BONEFLOWER);
        addPottedPlantDrops(ModBlock.POTTED_BONEFLOWER);
        addDrop(Nebulas.RED_TENTACLE_FLOWER);
        addDrop(Nebulas.BLUE_TENTACLE_FLOWER);
        addDrop(Nebulas.YELLOW_TENTACLE_FLOWER);
        addDrop(Nebulas.PURPLE_TENTACLE_FLOWER);
        addPottedPlantDrops(Nebulas.POTTED_BLUE_TENTACLE_FLOWER);
        addPottedPlantDrops(Nebulas.POTTED_RED_TENTACLE_FLOWER);
        addPottedPlantDrops(Nebulas.POTTED_YELLOW_TENTACLE_FLOWER);
        addPottedPlantDrops(Nebulas.POTTED_PURPLE_TENTACLE_FLOWER);
        // Star
        addDrop(StarBlocks.COSMIC_BLOCK);
        addDrop(StarBlocks.STAR_LEAVES, leavesDrops(StarBlocks.STAR_LEAVES, StarBlocks.STAR_SAPLING, 0.035F));
        addDrop(StarBlocks.STAR_LOG);
        addDrop(StarBlocks.STRIPPED_STAR_LOG);
        addDrop(StarBlocks.STAR_PLANKS);
        addDrop(StarBlocks.STAR_PLANKS_DOOR, doorDrops(StarBlocks.STAR_PLANKS_DOOR));
        addDrop(StarBlocks.STAR_PLANKS_STAIRS);
        addDrop(StarBlocks.STAR_PLANKS_SLAB, slabDrops(StarBlocks.STAR_PLANKS_SLAB));
        addDrop(StarBlocks.STAR_PLANKS_BUTTON);
        addDrop(StarBlocks.STAR_PLANKS_FENCE);
        addDrop(StarBlocks.STAR_PLANKS_FENCE_GATE);
        addDrop(StarBlocks.STAR_SAPLING);
        addDrop(StarBlocks.STAR_FLOWER);
        addDrop(StarBlocks.CELESTIAL_STAR_FLOWER);
        // Darkness
        addDrop(Darkness.DARKNESS_PLANKS_DOOR, doorDrops(Darkness.DARKNESS_PLANKS_DOOR));
        addDrop(Darkness.LOG_OF_DARKNESS);
        addDrop(Darkness.STRIPPED_LOG_OF_DARKNESS);
        addDrop(Darkness.DARKNESS_PLANKS);
        addDrop(Darkness.DARKNESS_PLANKS_STAIRS);
        addDrop(Darkness.DARKNESS_PLANKS_SLAB, slabDrops(Darkness.DARKNESS_PLANKS_SLAB));
        addDrop(Darkness.DARKNESS_PLANKS_BUTTON);
        addDrop(Darkness.DARKNESS_PLANKS_FENCE);
        addDrop(Darkness.DARKNESS_PLANKS_FENCE_GATE);
        addDrop(Darkness.DARKNESS_LEAVES, leavesDrops(Darkness.DARKNESS_LEAVES, Darkness.DARKNESS_SAPLING, 0.035F));
        // Nebulas
        addDrop(Nebulas.YELLOW_NEBULA_LOG);
        addDrop(Nebulas.BLUE_NEBULA_LOG);
        addDrop(Nebulas.RED_NEBULA_LOG);
        addDrop(Nebulas.PURPLE_NEBULA_LOG);
        addDrop(Nebulas.YELLOW_NEBULA_PLANKS);
        addDrop(Nebulas.BLUE_NEBULA_PLANKS);
        addDrop(Nebulas.RED_NEBULA_PLANKS);
        addDrop(Nebulas.PURPLE_NEBULA_PLANKS);
        addDrop(Nebulas.YELLOW_NEBULA_PLANKS_BUTTON);
        addDrop(Nebulas.BLUE_NEBULA_PLANKS_BUTTON);
        addDrop(Nebulas.RED_NEBULA_PLANKS_BUTTON);
        addDrop(Nebulas.PURPLE_NEBULA_PLANKS_BUTTON);
        addDrop(Nebulas.YELLOW_NEBULA_PLANKS_FENCE);
        addDrop(Nebulas.BLUE_NEBULA_PLANKS_FENCE);
        addDrop(Nebulas.RED_NEBULA_PLANKS_FENCE);
        addDrop(Nebulas.PURPLE_NEBULA_PLANKS_FENCE);
        addDrop(Nebulas.YELLOW_NEBULA_PLANKS_FENCE_GATE);
        addDrop(Nebulas.BLUE_NEBULA_PLANKS_FENCE_GATE);
        addDrop(Nebulas.RED_NEBULA_PLANKS_FENCE_GATE);
        addDrop(Nebulas.PURPLE_NEBULA_PLANKS_FENCE_GATE);
        addDrop(Nebulas.YELLOW_NEBULA_PLANKS_SLAB, slabDrops(Nebulas.YELLOW_NEBULA_PLANKS_SLAB));
        addDrop(Nebulas.BLUE_NEBULA_PLANKS_SLAB, slabDrops(Nebulas.BLUE_NEBULA_PLANKS_SLAB));
        addDrop(Nebulas.RED_NEBULA_PLANKS_SLAB, slabDrops(Nebulas.RED_NEBULA_PLANKS_SLAB));
        addDrop(Nebulas.PURPLE_NEBULA_PLANKS_SLAB, slabDrops(Nebulas.PURPLE_NEBULA_PLANKS_SLAB));
        addDrop(Nebulas.YELLOW_NEBULA_PLANKS_STAIRS);
        addDrop(Nebulas.BLUE_NEBULA_PLANKS_STAIRS);
        addDrop(Nebulas.RED_NEBULA_PLANKS_STAIRS);
        addDrop(Nebulas.PURPLE_NEBULA_PLANKS_STAIRS);
        addDrop(Nebulas.RED_NEBULA_LEAVES, customLeavesDrop(Nebulas.RED_NEBULA_LEAVES, ModItems.RED_STAR, 0f, 3.0f));
        addDrop(Nebulas.BLUE_NEBULA_LEAVES, customLeavesDrop(Nebulas.BLUE_NEBULA_LEAVES, ModItems.BLUE_STAR, 0f, 3.0f));
        addDrop(Nebulas.YELLOW_NEBULA_LEAVES, customLeavesDrop(Nebulas.YELLOW_NEBULA_LEAVES, ModItems.YELLOW_STAR, 0f, 3.0f));
        addDrop(Nebulas.PURPLE_NEBULA_LEAVES, customLeavesDrop(Nebulas.PURPLE_NEBULA_LEAVES, ModItems.PURPLE_STAR, 0f, 3.0f));
        // Hedge
        addDrop(Hedges.ACACIA_HEDGE);
        addDrop(Hedges.BIRCH_HEDGE);
        addDrop(Hedges.CHERRY_HEDGE);
        addDrop(Hedges.CURVE_HEDGE);
        addDrop(Hedges.DARK_OAK_HEDGE);
        addDrop(Hedges.DARKNESS_HEDGE);
        addDrop(Hedges.JUNGLE_HEDGE);
        addDrop(Hedges.MANGROVE_HEDGE);
        addDrop(Hedges.MOON_HEDGE);
        addDrop(Hedges.OAK_HEDGE);
        addDrop(Hedges.PALE_HEDGE);
        addDrop(Hedges.SPRUCE_HEDGE);
        addDrop(Hedges.STAR_HEDGE);
        addDrop(Hedges.YERI_HEDGE);
        addDrop(StarBlocks.RED_STAR_BLOCK, drops(ModItems.RED_STAR, ConstantLootNumberProvider.create(14)));
        addDrop(StarBlocks.BLUE_STAR_BLOCK, drops(ModItems.BLUE_STAR, ConstantLootNumberProvider.create(14)));
        addDrop(StarBlocks.YELLOW_STAR_BLOCK, drops(ModItems.YELLOW_STAR, ConstantLootNumberProvider.create(14)));
        addDrop(StarBlocks.PURPLE_STAR_BLOCK, drops(ModItems.PURPLE_STAR, ConstantLootNumberProvider.create(14)));
        // Chess
        addDrop(Chess.CHESSBOARD);
        addDrop(Chess.BLACK_CHESSBOARD);
        addDrop(Chess.WHITE_CHESSBOARD);
        // Red Orb
        addDrop(RedOrbBlocks.RED_ROCK);
        addDrop(RedOrbBlocks.RED_ROCK_SLAB, slabDrops(RedOrbBlocks.RED_ROCK_SLAB));
        addDrop(RedOrbBlocks.RED_ROCK_STAIRS);
        addDrop(RedOrbBlocks.POLISHED_RED_ROCK);
        addDrop(RedOrbBlocks.YERI_LOG);
        addDrop(RedOrbBlocks.YERI_SAPLING);
        addPottedPlantDrops(RedOrbBlocks.POTTED_YERI_SAPLING);
        addDrop(RedOrbBlocks.YERI_LEAVES, leavesDrops(RedOrbBlocks.YERI_LEAVES, RedOrbBlocks.YERI_SAPLING, 0.035F));
        addDrop(RedOrbBlocks.YERI_PLANKS);
        addDrop(RedOrbBlocks.YERI_PLANKS_SLAB, slabDrops(RedOrbBlocks.YERI_PLANKS_SLAB));
        addDrop(RedOrbBlocks.YERI_PLANKS_STAIRS);
        addDrop(RedOrbBlocks.YERI_PLANKS_FENCE);
        addDrop(RedOrbBlocks.YERI_PLANKS_FENCE_GATE);
        addDrop(RedOrbBlocks.YERI_PLANKS_BUTTON);
        addDrop(RedOrbBlocks.RED_ORB_PLATFORM);
        addDrop(RedOrbBlocks.GREEN_ROCK);
    }

    public LootTable.Builder leavesDrops(Block leaves, Block sapling, Item itemDrop, float ... saplingChance) {
        RegistryEntryLookup impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.leavesDrops(leaves, sapling, saplingChance).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).conditionally(this.createWithoutShearsOrSilkTouchCondition()).with((LootPoolEntry.Builder<?>)((LeafEntry.Builder)this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(itemDrop))).conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), 0.005f, 0.0055555557f, 0.00625f, 0.008333334f, 0.025f))));
    }

    public LootTable.Builder addFlowerbedDrop(net.minecraft.block.Block block) {
        return LootTable.builder().pool(
                LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(
                                ItemEntry.builder(block)
                                        .apply(
                                                // This function checks the FLOWER_AMOUNT property and scales the drop count accordingly
                                                SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))
                                                        .conditionally(BlockStatePropertyLootCondition.builder(block)
                                                                .properties(net.minecraft.predicate.StatePredicate.Builder.create()
                                                                        .exactMatch(FlowerbedBlock.FLOWER_AMOUNT, 1)))
                                        )
                                        .apply(
                                                SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))
                                                        .conditionally(BlockStatePropertyLootCondition.builder(block)
                                                                .properties(net.minecraft.predicate.StatePredicate.Builder.create()
                                                                        .exactMatch(FlowerbedBlock.FLOWER_AMOUNT, 2)))
                                        )
                                        .apply(
                                                SetCountLootFunction.builder(ConstantLootNumberProvider.create(3.0F))
                                                        .conditionally(BlockStatePropertyLootCondition.builder(block)
                                                                .properties(net.minecraft.predicate.StatePredicate.Builder.create()
                                                                        .exactMatch(FlowerbedBlock.FLOWER_AMOUNT, 3)))
                                        )
                                        .apply(
                                                SetCountLootFunction.builder(ConstantLootNumberProvider.create(4.0F))
                                                        .conditionally(BlockStatePropertyLootCondition.builder(block)
                                                                .properties(net.minecraft.predicate.StatePredicate.Builder.create()
                                                                        .exactMatch(FlowerbedBlock.FLOWER_AMOUNT, 4)))
                                        )
                        )
        );
    }
}
