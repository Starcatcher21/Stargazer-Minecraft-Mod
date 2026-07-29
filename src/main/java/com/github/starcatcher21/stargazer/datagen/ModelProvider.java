package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.YellowCable;
import com.github.starcatcher21.stargazer.block.clases.moon.plants.MoonCrop;
import com.github.starcatcher21.stargazer.block.register.*;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.item.WishingStars;
import com.mojang.math.Quadrant;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.ConditionBuilder;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import java.util.Optional;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(ModBlock.BONE_LEAVES);
        // planks
        blockStateModelGenerator.family(MoonBlocks.MOON_PLANKS)
                .stairs(MoonBlocks.MOON_PLANKS_STAIRS)
                .button(MoonBlocks.MOON_PLANKS_BUTTON)
                .fence(MoonBlocks.MOON_PLANKS_FENCE)
                .fenceGate(MoonBlocks.MOON_PLANKS_FENCE_GATE)
                .slab(MoonBlocks.MOON_PLANKS_SLAB);
        blockStateModelGenerator.family(MoonBlocks.YELLOW_MOON_PLANKS)
                .stairs(MoonBlocks.YELLOW_MOON_PLANKS_STAIRS)
                .button(MoonBlocks.YELLOW_MOON_PLANKS_BUTTON)
                .fence(MoonBlocks.YELLOW_MOON_PLANKS_FENCE)
                .fenceGate(MoonBlocks.YELLOW_MOON_PLANKS_FENCE_GATE)
                .slab(MoonBlocks.YELLOW_MOON_PLANKS_SLAB);
        blockStateModelGenerator.family(MoonBlocks.BLUE_MOON_PLANKS)
                .stairs(MoonBlocks.BLUE_MOON_PLANKS_STAIRS)
                .button(MoonBlocks.BLUE_MOON_PLANKS_BUTTON)
                .fence(MoonBlocks.BLUE_MOON_PLANKS_FENCE)
                .fenceGate(MoonBlocks.BLUE_MOON_PLANKS_FENCE_GATE)
                .slab(MoonBlocks.BLUE_MOON_PLANKS_SLAB);
        blockStateModelGenerator.family(MoonBlocks.RED_MOON_PLANKS)
                .stairs(MoonBlocks.RED_MOON_PLANKS_STAIRS)
                .button(MoonBlocks.RED_MOON_PLANKS_BUTTON)
                .fence(MoonBlocks.RED_MOON_PLANKS_FENCE)
                .fenceGate(MoonBlocks.RED_MOON_PLANKS_FENCE_GATE)
                .slab(MoonBlocks.RED_MOON_PLANKS_SLAB);
        blockStateModelGenerator.family(MoonBlocks.PURPLE_MOON_PLANKS)
                .stairs(MoonBlocks.PURPLE_MOON_PLANKS_STAIRS)
                .button(MoonBlocks.PURPLE_MOON_PLANKS_BUTTON)
                .fence(MoonBlocks.PURPLE_MOON_PLANKS_FENCE)
                .fenceGate(MoonBlocks.PURPLE_MOON_PLANKS_FENCE_GATE)
                .slab(MoonBlocks.PURPLE_MOON_PLANKS_SLAB);
        // STAR
        blockStateModelGenerator.family(StarBlocks.STAR_PLANKS)
                .stairs(StarBlocks.STAR_PLANKS_STAIRS)
                .button(StarBlocks.STAR_PLANKS_BUTTON)
                .fence(StarBlocks.STAR_PLANKS_FENCE)
                .fenceGate(StarBlocks.STAR_PLANKS_FENCE_GATE)
                .slab(StarBlocks.STAR_PLANKS_SLAB);
        blockStateModelGenerator.createDoor(StarBlocks.STAR_PLANKS_DOOR);
        // CURVE
        blockStateModelGenerator.family(MoonBlocks.CURVE_PLANKS)
                .stairs(MoonBlocks.CURVE_PLANKS_STAIRS)
                .button(MoonBlocks.CURVE_PLANKS_BUTTON)
                .fence(MoonBlocks.CURVE_PLANKS_FENCE)
                .fenceGate(MoonBlocks.CURVE_PLANKS_FENCE_GATE)
                .slab(MoonBlocks.CURVE_PLANKS_SLAB);
        blockStateModelGenerator.createDoor(MoonBlocks.CURVE_PLANKS_DOOR);
        // rock
        blockStateModelGenerator.family(MoonBlocks.MOON_ROCK);
        blockStateModelGenerator.family(MoonBlocks.POLISHED_MOON_ROCK);
        blockStateModelGenerator.family(MoonBlocks.MOON_ROCK_BRICKS)
                .stairs(MoonBlocks.MOON_ROCK_BRICKS_STAIRS)
                .slab(MoonBlocks.MOON_ROCK_BRICKS_SLAB);
        blockStateModelGenerator.createTrivialCube(MoonBlocks.CHISELED_MOON_ROCK_BRICKS);
        blockStateModelGenerator.createTrivialCube(MoonBlocks.CRACKED_MOON_ROCK_BRICKS);
        blockStateModelGenerator.family(MoonBlocks.BLACK_MOON_ROCK);
        blockStateModelGenerator.family(MoonBlocks.POLISHED_BLACK_MOON_ROCK);
        blockStateModelGenerator.family(MoonBlocks.POLISHED_BLACK_MOON_ROCK_PURPLE);
        blockStateModelGenerator.family(MoonBlocks.PRISMATIC_ORE);
        blockStateModelGenerator.family(MoonBlocks.PRISMATIC_SHARD_BLOCK);
        // tree
        blockStateModelGenerator.createTrivialCube(MoonBlocks.MOON_LEAVES);
        blockStateModelGenerator.createAxisAlignedPillarBlock(MoonBlocks.MOON_LOG, TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(MoonBlocks.FULL_MOON_LOG, TexturedModel.COLUMN);
        blockStateModelGenerator.createTrivialCube(MoonBlocks.FULL_MOON_LEAVES);
        blockStateModelGenerator.createTrivialCube(MoonBlocks.FULL_MOON_CORE);
        blockStateModelGenerator.createAxisAlignedPillarBlock(MoonBlocks.STRIPPED_MOON_LOG, TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(MoonBlocks.CURVE_LOG, TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(MoonBlocks.STRIPPED_CURVE_LOG, TexturedModel.COLUMN);
        blockStateModelGenerator.createTrivialCube(MoonBlocks.CURVE_LEAVES);
        blockStateModelGenerator.createAxisAlignedPillarBlock(StarBlocks.STAR_LOG, TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(StarBlocks.STRIPPED_STAR_LOG, TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(Darkness.LOG_OF_DARKNESS, TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(Darkness.STRIPPED_LOG_OF_DARKNESS, TexturedModel.COLUMN);
        blockStateModelGenerator.createTrivialCube(Darkness.DARKNESS_LEAVES);
        blockStateModelGenerator.createTrivialCube(Wander.TRUNN_LEAVES);

        blockStateModelGenerator.family(Darkness.DARKNESS_PLANKS)
                .stairs(Darkness.DARKNESS_PLANKS_STAIRS)
                .button(Darkness.DARKNESS_PLANKS_BUTTON)
                .fence(Darkness.DARKNESS_PLANKS_FENCE)
                .fenceGate(Darkness.DARKNESS_PLANKS_FENCE_GATE)
                .slab(Darkness.DARKNESS_PLANKS_SLAB);
        blockStateModelGenerator.createDoor(Darkness.DARKNESS_PLANKS_DOOR);
        // Eye blood
        blockStateModelGenerator.createAxisAlignedPillarBlock(EyeBloodBlocks.STRIPPED_EYE_LOG, TexturedModel.COLUMN);
        blockStateModelGenerator.createTrivialCube(EyeBloodBlocks.EYE_LEAVES);
        // mushroom
        registerCustomFlowerPotPlant(blockStateModelGenerator, MoonBlocks.PURPLE_MUSHROOM, MoonBlocks.POTTED_PURPLE_MUSHROOM, MoonBlocks.MOON_ROCK, BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.createMushroomBlock(MoonBlocks.PURPLE_MUSHROOM_BLOCK);
        // saplings
        registerCustomFlowerPotPlant(blockStateModelGenerator, MoonBlocks.MOON_SAPLING, MoonBlocks.POTTED_MOON_SAPLING, MoonBlocks.MOON_ROCK, BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, MoonBlocks.CURVE_SAPLING, MoonBlocks.POTTED_CURVE_SAPLING, MoonBlocks.MOON_ROCK, BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, StarBlocks.STAR_SAPLING, StarBlocks.POTTED_STAR_SAPLING, MoonBlocks.MOON_ROCK, BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, Darkness.DARKNESS_SAPLING, Darkness.POTTED_DARKNESS_SAPLING, MoonBlocks.MOON_ROCK, BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, RedOrbBlocks.YERI_SAPLING, RedOrbBlocks.POTTED_YERI_SAPLING, RedOrbBlocks.RED_ROCK, BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, MoonBlocks.FULL_MOON_SAPLING, MoonBlocks.POTTED_FULL_MOON_SAPLING, MoonBlocks.MOON_ROCK, BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, RedOrbBlocks.SPIRO_SAPLING, RedOrbBlocks.POTTED_SPIRO_SAPLING, RedOrbBlocks.RED_ROCK, BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, Wander.TRUNN_SAPLING, Wander.POTTED_TRUNN_SAPLING, Wander.PUROIL, BlockModelGenerators.PlantType.NOT_TINTED);
        // flowers
        registerCustomFlowerPotPlant(blockStateModelGenerator, StarBlocks.STAR_FLOWER, StarBlocks.POTTED_STAR_FLOWER, MoonBlocks.MOON_ROCK, BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, StarBlocks.CELESTIAL_STAR_FLOWER, StarBlocks.POTTED_CELESTIAL_STAR_FLOWER, MoonBlocks.MOON_ROCK, BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, ModBlock.BONEFLOWER, ModBlock.POTTED_BONEFLOWER, MoonBlocks.MOON_ROCK, BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, Nebulas.RED_TENTACLE_FLOWER, Nebulas.POTTED_RED_TENTACLE_FLOWER, MoonBlocks.MOON_ROCK, BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, Nebulas.YELLOW_TENTACLE_FLOWER, Nebulas.POTTED_YELLOW_TENTACLE_FLOWER, MoonBlocks.MOON_ROCK, BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, Nebulas.BLUE_TENTACLE_FLOWER, Nebulas.POTTED_BLUE_TENTACLE_FLOWER, MoonBlocks.MOON_ROCK, BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, Nebulas.PURPLE_TENTACLE_FLOWER, Nebulas.POTTED_PURPLE_TENTACLE_FLOWER, MoonBlocks.MOON_ROCK, BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.createCrossBlockWithDefaultItem(MoonBlocks.MOON_GRASS, BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.createDoublePlant(MoonBlocks.TALL_MOON_GRASS, BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.createCrossBlockWithDefaultItem(MoonBlocks.MOON_FERN, BlockModelGenerators.PlantType.NOT_TINTED);
        // crops
        blockStateModelGenerator.createCropBlock(Crops.DRAGON_CARROT_BLOCK, MoonCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.createCropBlock(Crops.BROODY_BLOCK, MoonCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.createCropBlock(Crops.EYE_BALLS_BLOCK, MoonCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        // Nebulas
        blockStateModelGenerator.createAxisAlignedPillarBlock(Nebulas.BLUE_NEBULA_LOG, TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(Nebulas.RED_NEBULA_LOG, TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(Nebulas.PURPLE_NEBULA_LOG, TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(Nebulas.YELLOW_NEBULA_LOG, TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(Wander.TRUNN_LOG, TexturedModel.COLUMN);
        blockStateModelGenerator.createTrivialCube(Nebulas.BLUE_NEBULA_LEAVES);
        blockStateModelGenerator.family(Nebulas.BLUE_NEBULA_PLANKS)
                .stairs(Nebulas.BLUE_NEBULA_PLANKS_STAIRS)
                .button(Nebulas.BLUE_NEBULA_PLANKS_BUTTON)
                .fence(Nebulas.BLUE_NEBULA_PLANKS_FENCE)
                .fenceGate(Nebulas.BLUE_NEBULA_PLANKS_FENCE_GATE)
                .slab(Nebulas.BLUE_NEBULA_PLANKS_SLAB);
        blockStateModelGenerator.createTrivialCube(Nebulas.PURPLE_NEBULA_LEAVES);
        blockStateModelGenerator.family(Nebulas.PURPLE_NEBULA_PLANKS)
                .stairs(Nebulas.PURPLE_NEBULA_PLANKS_STAIRS)
                .button(Nebulas.PURPLE_NEBULA_PLANKS_BUTTON)
                .fence(Nebulas.PURPLE_NEBULA_PLANKS_FENCE)
                .fenceGate(Nebulas.PURPLE_NEBULA_PLANKS_FENCE_GATE)
                .slab(Nebulas.PURPLE_NEBULA_PLANKS_SLAB);
        blockStateModelGenerator.createTrivialCube(Nebulas.RED_NEBULA_LEAVES);
        blockStateModelGenerator.family(Nebulas.RED_NEBULA_PLANKS)
                .stairs(Nebulas.RED_NEBULA_PLANKS_STAIRS)
                .button(Nebulas.RED_NEBULA_PLANKS_BUTTON)
                .fence(Nebulas.RED_NEBULA_PLANKS_FENCE)
                .fenceGate(Nebulas.RED_NEBULA_PLANKS_FENCE_GATE)
                .slab(Nebulas.RED_NEBULA_PLANKS_SLAB);
        blockStateModelGenerator.createTrivialCube(Nebulas.YELLOW_NEBULA_LEAVES);
        blockStateModelGenerator.family(Nebulas.YELLOW_NEBULA_PLANKS)
                .stairs(Nebulas.YELLOW_NEBULA_PLANKS_STAIRS)
                .button(Nebulas.YELLOW_NEBULA_PLANKS_BUTTON)
                .fence(Nebulas.YELLOW_NEBULA_PLANKS_FENCE)
                .fenceGate(Nebulas.YELLOW_NEBULA_PLANKS_FENCE_GATE)
                .slab(Nebulas.YELLOW_NEBULA_PLANKS_SLAB);
        blockStateModelGenerator.createTrivialCube(Nebulas.BLUE_NEBULA_REGROW_CORE);
        blockStateModelGenerator.createTrivialCube(Nebulas.PURPLE_NEBULA_REGROW_CORE);
        blockStateModelGenerator.createTrivialCube(Nebulas.RED_NEBULA_REGROW_CORE);
        blockStateModelGenerator.createTrivialCube(Nebulas.YELLOW_NEBULA_REGROW_CORE);
        blockStateModelGenerator.createTrivialCube(MoonBlocks.SUN_ENRICHED_MOON_ROCK);
        blockStateModelGenerator.createTrivialCube(MoonBlocks.POLISHED_SUN_ENRICHED_MOON_ROCK);
        TextureMapping dyliumMap = new TextureMapping()
                .put(TextureSlot.TOP, new Material(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/dylium_top")))
                .put(TextureSlot.BOTTOM, new Material(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/moon_rock")))
                .put(TextureSlot.SIDE, new Material(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/dylium_side")));

        registerTopBottom(blockStateModelGenerator, Darkness.DYLIUM, dyliumMap);
        TextureMapping BorilMap = new TextureMapping()
                .put(TextureSlot.TOP, new Material(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/boril_top")))
                .put(TextureSlot.BOTTOM, new Material(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/puroil")))
                .put(TextureSlot.SIDE, new Material(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/boril_side")));

        registerTopBottom(blockStateModelGenerator, Wander.BORIL, BorilMap);
        blockStateModelGenerator.createTrivialCube(Wander.PUROIL);
        registerCustomFlowerPotPlant(blockStateModelGenerator, Darkness.ROSE_OF_PAIN, Darkness.POTTED_ROSE_OF_PAIN, MoonBlocks.MOON_ROCK, BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, MoonBlocks.SPRUNGUS, MoonBlocks.POTTED_SPRUNGUS, MoonBlocks.MOON_ROCK, BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, RedOrbBlocks.POINTY, RedOrbBlocks.POTTED_POINTY, RedOrbBlocks.RED_ROCK, BlockModelGenerators.PlantType.NOT_TINTED);
        // Chess
        blockStateModelGenerator.createTrivialCube(Chess.BLACK_CHESSBOARD);
        blockStateModelGenerator.createTrivialCube(Chess.WHITE_CHESSBOARD);
        blockStateModelGenerator.createTrivialCube(Chess.BLACK_BRICKS);
        blockStateModelGenerator.createTrivialCube(Chess.WHITE_BRICKS);
        // Other
        blockStateModelGenerator.createTrivialCube(StarBlocks.RED_STAR_BLOCK);
        blockStateModelGenerator.createTrivialCube(StarBlocks.BLUE_STAR_BLOCK);
        blockStateModelGenerator.createTrivialCube(StarBlocks.YELLOW_STAR_BLOCK);
        blockStateModelGenerator.createTrivialCube(StarBlocks.PURPLE_STAR_BLOCK);
        // Hedge
        registerHedge(blockStateModelGenerator, Hedges.OAK_HEDGE, Blocks.OAK_LOG);
        registerHedge(blockStateModelGenerator, Hedges.BIRCH_HEDGE, Blocks.BIRCH_LOG);
        registerHedge(blockStateModelGenerator, Hedges.JUNGLE_HEDGE, Blocks.JUNGLE_LOG);
        registerHedge(blockStateModelGenerator, Hedges.MANGROVE_HEDGE, Blocks.MANGROVE_LOG);
        registerHedge(blockStateModelGenerator, Hedges.DARK_OAK_HEDGE, Blocks.DARK_OAK_LOG);
        registerHedge(blockStateModelGenerator, Hedges.ACACIA_HEDGE, Blocks.ACACIA_LOG);
        registerHedge(blockStateModelGenerator, Hedges.PALE_HEDGE, Blocks.PALE_OAK_LOG);
        registerHedge(blockStateModelGenerator, Hedges.CHERRY_HEDGE, Blocks.CHERRY_LOG);
        registerHedge(blockStateModelGenerator, Hedges.SPRUCE_HEDGE, Blocks.SPRUCE_LOG);
        registerHedgeSide(blockStateModelGenerator, Hedges.MOON_HEDGE, MoonBlocks.MOON_LOG);
        registerHedgeSide(blockStateModelGenerator, Hedges.CURVE_HEDGE, MoonBlocks.CURVE_LOG);
        registerHedgeSide(blockStateModelGenerator, Hedges.STAR_HEDGE, StarBlocks.STAR_LOG);
        registerHedgeSide(blockStateModelGenerator, Hedges.DARKNESS_HEDGE, Darkness.LOG_OF_DARKNESS);
        registerHedgeSide(blockStateModelGenerator, Hedges.YERI_HEDGE, RedOrbBlocks.YERI_LOG);
        registerHedgeSide(blockStateModelGenerator, Hedges.FULL_MOON_HEDGE, MoonBlocks.FULL_MOON_LOG);
        registerHedgeSide(blockStateModelGenerator, Hedges.SPIRO_HEDGE, RedOrbBlocks.SPIRO_LOG);
        registerHedgeSide(blockStateModelGenerator, Hedges.TRUNN_HEDGE, Wander.TRUNN_LOG);
        // Red Orb
        blockStateModelGenerator.family(RedOrbBlocks.RED_ROCK)
                .stairs(RedOrbBlocks.RED_ROCK_STAIRS)
                .slab(RedOrbBlocks.RED_ROCK_SLAB);
        blockStateModelGenerator.createTrivialCube(RedOrbBlocks.POLISHED_RED_ROCK);
        blockStateModelGenerator.createAxisAlignedPillarBlock(RedOrbBlocks.YERI_LOG, TexturedModel.COLUMN);
        blockStateModelGenerator.createTrivialCube(RedOrbBlocks.YERI_LEAVES);
        blockStateModelGenerator.family(RedOrbBlocks.YERI_PLANKS)
                .stairs(RedOrbBlocks.YERI_PLANKS_STAIRS)
                .button(RedOrbBlocks.YERI_PLANKS_BUTTON)
                .fence(RedOrbBlocks.YERI_PLANKS_FENCE)
                .fenceGate(RedOrbBlocks.YERI_PLANKS_FENCE_GATE)
                .slab(RedOrbBlocks.YERI_PLANKS_SLAB);
        blockStateModelGenerator.createTrivialCube(RedOrbBlocks.GREEN_ROCK);
        blockStateModelGenerator.createCrossBlockWithDefaultItem(RedOrbBlocks.BLUE_GRASS, BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.createAxisAlignedPillarBlock(RedOrbBlocks.SPIRO_LOG, TexturedModel.COLUMN);
        blockStateModelGenerator.createTrivialCube(RedOrbBlocks.SPIRO_LEAVES);
        blockStateModelGenerator.createTrivialCube(MoonBlocks.COMET_BLOCK);
        blockStateModelGenerator.createTrivialCube(Energy.STARGENERATOR);
        registerCable(blockStateModelGenerator, Energy.YELLOW_CABLE);
        registerCable(blockStateModelGenerator, Energy.BLUE_CABLE);
        registerCable(blockStateModelGenerator, Energy.RED_CABLE);
        registerCable(blockStateModelGenerator, Energy.PURPLE_CABLE);
        blockStateModelGenerator.createTrivialCube(Energy.STARMACHINE_BLOCK);
        blockStateModelGenerator.createTrivialCube(Energy.NIGHT_WATCHER);
        blockStateModelGenerator.createTrivialCube(Energy.STAR_CRUSHER);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.LODESTAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.IRON_DUST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLD_DUST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COPPER_DUST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SUPERNOVA, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DREAM_BUCKET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DARKSTAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STARDUST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GEODE_FRUIT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COOKED_GEODE_FRUIT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.FULL_COOKED_GEODE_FRUIT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BLACK_COOKED_GEODE_FRUIT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BLUE_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RED_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RED_ORB_PLATFORM_BASE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.YELLOW_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PURPLE_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DREAM_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MOON_GLASS_SHARD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PRISMATIC_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.WISHING_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.WHITE_WISHING_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.LIGHT_GRAY_WISHING_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.GRAY_WISHING_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.BLACK_WISHING_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.BROWN_WISHING_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.RED_WISHING_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.ORANGE_WISHING_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.LIME_WISHING_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.GREEN_WISHING_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.CYAN_WISHING_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.LIGHT_BLUE_WISHING_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.BLUE_WISHING_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.PURPLE_WISHING_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.MAGENTA_WISHING_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.PINK_WISHING_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.THROWABLE_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STAR_BANNER_PATTERN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STAR_HAMMER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SUN_ENRICHED_YELLOW_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.WINGED_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GUMMY_FISH, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GUMMY_WORM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COSMO_FISH, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ENDER_FISH, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_CRUCIAN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LUCKY_COMET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COMET_FRAGMENT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.AURORA_FRAGMENT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STAR_BOOK.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(MoonBlocks.TALL_MOON_GRASS.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MOON_COOKIE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STAR_COOKIE, ModelTemplates.FLAT_ITEM);
        blockGeneratedItem(itemModelGenerator, StarBlocks.STAR_FLOWER);
        blockGeneratedItem(itemModelGenerator, StarBlocks.CELESTIAL_STAR_FLOWER);
        blockGeneratedItem(itemModelGenerator, ModBlock.BONEFLOWER);
        blockGeneratedItem(itemModelGenerator, RedOrbBlocks.POINTY);
        blockGeneratedItem(itemModelGenerator, Nebulas.RED_TENTACLE_FLOWER);
        blockGeneratedItem(itemModelGenerator, Nebulas.BLUE_TENTACLE_FLOWER);
        blockGeneratedItem(itemModelGenerator, Nebulas.PURPLE_TENTACLE_FLOWER);
        blockGeneratedItem(itemModelGenerator, Nebulas.YELLOW_TENTACLE_FLOWER);
        blockGeneratedItem(itemModelGenerator, MoonBlocks.PURPLE_MUSHROOM);
        blockGeneratedItem(itemModelGenerator, MoonBlocks.MOON_SAPLING);
        blockGeneratedItem(itemModelGenerator, MoonBlocks.CURVE_SAPLING);
        blockGeneratedItem(itemModelGenerator, StarBlocks.STAR_SAPLING);
        blockGeneratedItem(itemModelGenerator, RedOrbBlocks.YERI_SAPLING);
        blockGeneratedItem(itemModelGenerator, RedOrbBlocks.SPIRO_SAPLING);
        blockGeneratedItem(itemModelGenerator, Darkness.DARKNESS_SAPLING);
        blockGeneratedItem(itemModelGenerator, MoonBlocks.FULL_MOON_SAPLING);
        blockGeneratedItem(itemModelGenerator, Darkness.ROSE_OF_PAIN);
        blockGeneratedItem(itemModelGenerator, MoonBlocks.SPRUNGUS);

        itemModelGenerator.generateFlatItem(ModItems.DEAD_EYE_BAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LIVING_EYE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PRISMATIC_SHARD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ECTOPLASM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COOLER_ECTOPLASM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BLACK_BRICK, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.WHITE_BRICK, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(StarBlocks.STAR_DISPLAY.asItem(), ModelTemplates.FLAT_ITEM);

        // Spawn Eggs
        itemModelGenerator.generateFlatItem(ModItems.GHOST_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.AMETHYST_TURTLE_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.EYE_BAT_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SCRUBY_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ROOK_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BLACK_ROOK_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BLACK_FOX_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
    }

    public static final ModelTemplate CUSTOM_POT_CROSS = new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/potted_plant_custom")), Optional.empty(), TextureSlot.PLANT, TextureSlot.DIRT);
    public static final ModelTemplate HEDGE = new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/hedge")), Optional.empty(), TextureSlot.TOP, TextureSlot.SIDE);

    public final void registerCustomFlowerPotPlant(BlockModelGenerators blockStateModelGenerator, Block plantBlock, Block flowerPotBlock, Block dirt, BlockModelGenerators.PlantType tintType) {
        blockStateModelGenerator.createCrossBlock(plantBlock, tintType);
        TextureMapping potTextureMap = getCustomPotTextureMap(plantBlock, dirt);
        MultiVariant weightedVariant = BlockModelGenerators.plainVariant(CUSTOM_POT_CROSS.create(flowerPotBlock, potTextureMap, blockStateModelGenerator.modelOutput));
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(flowerPotBlock, weightedVariant));
    }

    public final void registerHedge(BlockModelGenerators blockStateModelGenerator, Block hedge, Block log) {
        TextureMapping potTextureMap = getHedgeMap(log);
        MultiVariant weightedVariant = BlockModelGenerators.plainVariant(HEDGE.create(hedge, potTextureMap, blockStateModelGenerator.modelOutput));
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(hedge, weightedVariant));
    }
    public final void registerHedgeSide(BlockModelGenerators blockStateModelGenerator, Block hedge, Block log) {
        TextureMapping potTextureMap = getHedgeSideMap(log);
        MultiVariant weightedVariant = BlockModelGenerators.plainVariant(HEDGE.create(hedge, potTextureMap, blockStateModelGenerator.modelOutput));
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(hedge, weightedVariant));
    }

    public final void registerTopBottom(BlockModelGenerators blockStateModelGenerator, Block block, TextureMapping texureMap) {
        MultiVariant weightedVariant = BlockModelGenerators.plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.create(block, texureMap, blockStateModelGenerator.modelOutput));
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, weightedVariant));
    }

    public TextureMapping getCustomPotTextureMap(Block block, Block dirt) {
        TextureMapping map = new TextureMapping();
        map.put(TextureSlot.PLANT, new Material(getBlockTexture(block)));
        map.put(TextureSlot.DIRT, new Material(getBlockTexture(dirt)));
        return map;
    }

    public TextureMapping getHedgeMap(Block block) {
        TextureMapping map = new TextureMapping();
        map.put(TextureSlot.TOP, new Material(getBlockTexture(block).withSuffix("_top")));
        map.put(TextureSlot.SIDE, new Material(getBlockTexture(block)));
        return map;
    }

    public TextureMapping getHedgeSideMap(Block block) {
        TextureMapping map = new TextureMapping();
        map.put(TextureSlot.TOP, new Material(getBlockTexture(block).withSuffix("_top")));
        map.put(TextureSlot.SIDE, new Material(getBlockTexture(block).withSuffix("_side")));
        return map;
    }

    public void blockGeneratedItem(ItemModelGenerators itemModelGenerator, Block item) {
        itemModelGenerator.itemModelOutput.accept(item.asItem(), ItemModelUtils.plainModel(uploadWithTexture(itemModelGenerator, item.asItem(), getBlockTexture(item), ModelTemplates.FLAT_ITEM)));
    }

    public final Identifier uploadWithTexture(ItemModelGenerators itemModelGenerator, Item item, Identifier texture, ModelTemplate model) {
        return model.create(ModelLocationUtils.getModelLocation(item), TextureMapping.layer0(new Material(texture)), itemModelGenerator.modelOutput);
    }

    public Identifier getBlockTexture(Block block) {
        Identifier id = BuiltInRegistries.BLOCK.getKey(block);
        return Identifier.fromNamespaceAndPath(id.getNamespace(), "block/" + id.getPath());
    }

    public static final ModelTemplate CABLE_CORE_TEMPLATE = block("cable_core_template", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate CABLE_SIDE_TEMPLATE = block("cable_side_template", TextureSlot.ALL, TextureSlot.PARTICLE);

    // helper method for creating Models
    private static ModelTemplate block(String parent, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    private BlockModelDefinitionGenerator createCableBlockStates(Block cableBlock, Identifier core, Identifier side) {
        // 1. Core and Base Side Models
        Variant cableCoreModel = BlockModelGenerators.plainModel(core);
        Variant cableSideModel = BlockModelGenerators.plainModel(side);

        // 2. Wrap Core in a WeightedVariant (Always Active)
        MultiVariant empty = new MultiVariant(WeightedList.<Variant>builder().add(cableCoreModel).build());

        // 3. Define WeightedVariants for all 6 directions with their respective rotations
        // Horizontal Rotations (Y-Axis)
        MultiVariant north = new MultiVariant(WeightedList.<Variant>builder().add(cableSideModel).build()); // 0 degrees
        MultiVariant east  = new MultiVariant(WeightedList.<Variant>builder().add(cableSideModel.withYRot(Quadrant.R90)).build());
        MultiVariant south = new MultiVariant(WeightedList.<Variant>builder().add(cableSideModel.withYRot(Quadrant.R180)).build());
        MultiVariant west  = new MultiVariant(WeightedList.<Variant>builder().add(cableSideModel.withYRot(Quadrant.R270)).build());

        // Vertical Rotations (X-Axis)
        // Note: Depending on your template model, UP/DOWN might also require Y rotation to align textures perfectly.
        MultiVariant up    = new MultiVariant(WeightedList.<Variant>builder().add(cableSideModel.withXRot(Quadrant.R270)).build());
        MultiVariant down  = new MultiVariant(WeightedList.<Variant>builder().add(cableSideModel.withXRot(Quadrant.R90)).build());

        // 4. Build and return the Multipart Definition
        return MultiPartGenerator.multiPart(cableBlock)
                // Core is unconditional (always renders)
                .with(empty)

                // Conditional sides
                .with(new ConditionBuilder().term(YellowCable.NORTH, true).build(), north)
                .with(new ConditionBuilder().term(YellowCable.EAST, true).build(), east)
                .with(new ConditionBuilder().term(YellowCable.SOUTH, true).build(), south)
                .with(new ConditionBuilder().term(YellowCable.WEST, true).build(), west)
                .with(new ConditionBuilder().term(YellowCable.UP, true).build(), up)
                .with(new ConditionBuilder().term(YellowCable.DOWN, true).build(), down);
    }
    public void registerCable(BlockModelGenerators generator, Block block) {
        TextureMapping map = getCableMap(block);
        Identifier core = CABLE_CORE_TEMPLATE.create(block, map, generator.modelOutput);
        Identifier side = CABLE_SIDE_TEMPLATE.create(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID,"block/"+BuiltInRegistries.BLOCK.getKey(block).getPath()+"_side"), map, generator.modelOutput);
        generator.blockStateOutput.accept(createCableBlockStates(block, core, side));
        generator.registerSimpleItemModel(block.asItem(), core);
    }
    public TextureMapping getCableMap(Block block) {
        TextureMapping map = new TextureMapping();
        map.put(TextureSlot.ALL, new Material(getBlockTexture(block)));
        map.put(TextureSlot.PARTICLE, new Material(getBlockTexture(block)));
        return map;
    }
}
