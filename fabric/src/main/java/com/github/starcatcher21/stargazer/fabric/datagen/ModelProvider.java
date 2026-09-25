package com.github.starcatcher21.stargazer.fabric.datagen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.YellowCable;
import com.github.starcatcher21.stargazer.block.clases.moon.plants.MoonCrop;
import com.github.starcatcher21.stargazer.block.register.*;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.item.WishingStars;
import com.mojang.math.Quadrant;
import dev.architectury.platform.Mod;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.*;
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
import net.minecraft.world.level.block.state.properties.Property;

import java.util.Optional;
import java.util.function.Function;

import static net.minecraft.client.data.models.BlockModelGenerators.plainVariant;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricPackOutput output) {
        super(output);
    }

    public final void createCropBlock(BlockModelGenerators blockModelGenerators, final Block block, final Property<Integer> property, final int... stages) {
        if (property.getPossibleValues().size() != stages.length) {
            throw new IllegalArgumentException();
        } else {
            Int2ObjectMap<Identifier> models = new Int2ObjectOpenHashMap();
            blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(PropertyDispatch.initial(property).generate((i) -> {
                int stage = stages[i];
                return plainVariant((Identifier)models.computeIfAbsent(stage, (s) -> blockModelGenerators.createSuffixedVariant(block, "_stage" + s, ModelTemplates.CROP, TextureMapping::crop)));
            })));
        }
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(ModBlock.BONE_LEAVES.get());
        // planks
        blockStateModelGenerator.family(MoonBlocks.MOON_PLANKS.get())
                .stairs(MoonBlocks.MOON_PLANKS_STAIRS.get())
                .button(MoonBlocks.MOON_PLANKS_BUTTON.get())
                .fence(MoonBlocks.MOON_PLANKS_FENCE.get())
                .fenceGate(MoonBlocks.MOON_PLANKS_FENCE_GATE.get())
                .slab(MoonBlocks.MOON_PLANKS_SLAB.get());
        blockStateModelGenerator.family(MoonBlocks.YELLOW_MOON_PLANKS.get())
                .stairs(MoonBlocks.YELLOW_MOON_PLANKS_STAIRS.get())
                .button(MoonBlocks.YELLOW_MOON_PLANKS_BUTTON.get())
                .fence(MoonBlocks.YELLOW_MOON_PLANKS_FENCE.get())
                .fenceGate(MoonBlocks.YELLOW_MOON_PLANKS_FENCE_GATE.get())
                .slab(MoonBlocks.YELLOW_MOON_PLANKS_SLAB.get());
        blockStateModelGenerator.family(MoonBlocks.BLUE_MOON_PLANKS.get())
                .stairs(MoonBlocks.BLUE_MOON_PLANKS_STAIRS.get())
                .button(MoonBlocks.BLUE_MOON_PLANKS_BUTTON.get())
                .fence(MoonBlocks.BLUE_MOON_PLANKS_FENCE.get())
                .fenceGate(MoonBlocks.BLUE_MOON_PLANKS_FENCE_GATE.get())
                .slab(MoonBlocks.BLUE_MOON_PLANKS_SLAB.get());
        blockStateModelGenerator.family(MoonBlocks.RED_MOON_PLANKS.get())
                .stairs(MoonBlocks.RED_MOON_PLANKS_STAIRS.get())
                .button(MoonBlocks.RED_MOON_PLANKS_BUTTON.get())
                .fence(MoonBlocks.RED_MOON_PLANKS_FENCE.get())
                .fenceGate(MoonBlocks.RED_MOON_PLANKS_FENCE_GATE.get())
                .slab(MoonBlocks.RED_MOON_PLANKS_SLAB.get());
        blockStateModelGenerator.family(MoonBlocks.PURPLE_MOON_PLANKS.get())
                .stairs(MoonBlocks.PURPLE_MOON_PLANKS_STAIRS.get())
                .button(MoonBlocks.PURPLE_MOON_PLANKS_BUTTON.get())
                .fence(MoonBlocks.PURPLE_MOON_PLANKS_FENCE.get())
                .fenceGate(MoonBlocks.PURPLE_MOON_PLANKS_FENCE_GATE.get())
                .slab(MoonBlocks.PURPLE_MOON_PLANKS_SLAB.get());
        // STAR
        blockStateModelGenerator.family(StarBlocks.STAR_PLANKS.get())
                .stairs(StarBlocks.STAR_PLANKS_STAIRS.get())
                .button(StarBlocks.STAR_PLANKS_BUTTON.get())
                .fence(StarBlocks.STAR_PLANKS_FENCE.get())
                .fenceGate(StarBlocks.STAR_PLANKS_FENCE_GATE.get())
                .slab(StarBlocks.STAR_PLANKS_SLAB.get());
        blockStateModelGenerator.createDoor(StarBlocks.STAR_PLANKS_DOOR.get());
        // CURVE
        blockStateModelGenerator.family(MoonBlocks.CURVE_PLANKS.get())
                .stairs(MoonBlocks.CURVE_PLANKS_STAIRS.get())
                .button(MoonBlocks.CURVE_PLANKS_BUTTON.get())
                .fence(MoonBlocks.CURVE_PLANKS_FENCE.get())
                .fenceGate(MoonBlocks.CURVE_PLANKS_FENCE_GATE.get())
                .slab(MoonBlocks.CURVE_PLANKS_SLAB.get());
        blockStateModelGenerator.createDoor(MoonBlocks.CURVE_PLANKS_DOOR.get());
        // rock
        blockStateModelGenerator.family(MoonBlocks.MOON_ROCK.get());
        blockStateModelGenerator.family(MoonBlocks.POLISHED_MOON_ROCK.get());
        blockStateModelGenerator.family(MoonBlocks.MOON_ROCK_BRICKS.get())
                .stairs(MoonBlocks.MOON_ROCK_BRICKS_STAIRS.get())
                .slab(MoonBlocks.MOON_ROCK_BRICKS_SLAB.get());
        blockStateModelGenerator.createTrivialCube(MoonBlocks.CHISELED_MOON_ROCK_BRICKS.get());
        blockStateModelGenerator.createTrivialCube(MoonBlocks.CRACKED_MOON_ROCK_BRICKS.get());
        blockStateModelGenerator.family(MoonBlocks.BLACK_MOON_ROCK.get());
        blockStateModelGenerator.family(MoonBlocks.POLISHED_BLACK_MOON_ROCK.get());
        blockStateModelGenerator.family(MoonBlocks.POLISHED_BLACK_MOON_ROCK_PURPLE.get());
        blockStateModelGenerator.family(MoonBlocks.PRISMATIC_ORE.get());
        blockStateModelGenerator.family(MoonBlocks.PRISMATIC_SHARD_BLOCK.get());
        // tree
        blockStateModelGenerator.createTrivialCube(MoonBlocks.MOON_LEAVES.get());
        blockStateModelGenerator.createAxisAlignedPillarBlock(MoonBlocks.MOON_LOG.get(), TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(MoonBlocks.FULL_MOON_LOG.get(), TexturedModel.COLUMN);
        blockStateModelGenerator.createTrivialCube(MoonBlocks.FULL_MOON_LEAVES.get());
        blockStateModelGenerator.createTrivialCube(MoonBlocks.FULL_MOON_CORE.get());
        blockStateModelGenerator.createAxisAlignedPillarBlock(MoonBlocks.STRIPPED_MOON_LOG.get(), TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(MoonBlocks.CURVE_LOG.get(), TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(MoonBlocks.STRIPPED_CURVE_LOG.get(), TexturedModel.COLUMN);
        blockStateModelGenerator.createTrivialCube(MoonBlocks.CURVE_LEAVES.get());
        blockStateModelGenerator.createAxisAlignedPillarBlock(StarBlocks.STAR_LOG.get(), TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(StarBlocks.STRIPPED_STAR_LOG.get(), TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(Darkness.LOG_OF_DARKNESS.get(), TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(Darkness.STRIPPED_LOG_OF_DARKNESS.get(), TexturedModel.COLUMN);
        blockStateModelGenerator.createTrivialCube(Darkness.DARKNESS_LEAVES.get());
        blockStateModelGenerator.createTrivialCube(Wander.TRUNN_LEAVES.get());

        blockStateModelGenerator.family(Darkness.DARKNESS_PLANKS.get())
                .stairs(Darkness.DARKNESS_PLANKS_STAIRS.get())
                .button(Darkness.DARKNESS_PLANKS_BUTTON.get())
                .fence(Darkness.DARKNESS_PLANKS_FENCE.get())
                .fenceGate(Darkness.DARKNESS_PLANKS_FENCE_GATE.get())
                .slab(Darkness.DARKNESS_PLANKS_SLAB.get());
        blockStateModelGenerator.createDoor(Darkness.DARKNESS_PLANKS_DOOR.get());
        // Eye blood
        blockStateModelGenerator.createAxisAlignedPillarBlock(EyeBloodBlocks.STRIPPED_EYE_LOG.get(), TexturedModel.COLUMN);
        blockStateModelGenerator.createTrivialCube(EyeBloodBlocks.EYE_LEAVES.get());
        // mushroom
        registerCustomFlowerPotPlant(blockStateModelGenerator, MoonBlocks.PURPLE_MUSHROOM.get(), MoonBlocks.POTTED_PURPLE_MUSHROOM.get(), MoonBlocks.MOON_ROCK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.createMushroomBlock(MoonBlocks.PURPLE_MUSHROOM_BLOCK.get());
        // saplings
        registerCustomFlowerPotPlant(blockStateModelGenerator, MoonBlocks.MOON_SAPLING.get(), MoonBlocks.POTTED_MOON_SAPLING.get(), MoonBlocks.MOON_ROCK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, MoonBlocks.CURVE_SAPLING.get(), MoonBlocks.POTTED_CURVE_SAPLING.get(), MoonBlocks.MOON_ROCK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, StarBlocks.STAR_SAPLING.get(), StarBlocks.POTTED_STAR_SAPLING.get(), MoonBlocks.MOON_ROCK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, Darkness.DARKNESS_SAPLING.get(), Darkness.POTTED_DARKNESS_SAPLING.get(), MoonBlocks.MOON_ROCK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, RedOrbBlocks.YERI_SAPLING.get(), RedOrbBlocks.POTTED_YERI_SAPLING.get(), RedOrbBlocks.RED_ROCK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, MoonBlocks.FULL_MOON_SAPLING.get(), MoonBlocks.POTTED_FULL_MOON_SAPLING.get(), MoonBlocks.MOON_ROCK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, RedOrbBlocks.SPIRO_SAPLING.get(), RedOrbBlocks.POTTED_SPIRO_SAPLING.get(), RedOrbBlocks.RED_ROCK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, Wander.TRUNN_SAPLING.get(), Wander.POTTED_TRUNN_SAPLING.get(), Wander.PUROIL.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        // flowers
        registerCustomFlowerPotPlant(blockStateModelGenerator, StarBlocks.STAR_FLOWER.get(), StarBlocks.POTTED_STAR_FLOWER.get(), MoonBlocks.MOON_ROCK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, StarBlocks.CELESTIAL_STAR_FLOWER.get(), StarBlocks.POTTED_CELESTIAL_STAR_FLOWER.get(), MoonBlocks.MOON_ROCK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, ModBlock.BONEFLOWER.get(), ModBlock.POTTED_BONEFLOWER.get(), MoonBlocks.MOON_ROCK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, Nebulas.RED_TENTACLE_FLOWER.get(), Nebulas.POTTED_RED_TENTACLE_FLOWER.get(), MoonBlocks.MOON_ROCK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, Nebulas.YELLOW_TENTACLE_FLOWER.get(), Nebulas.POTTED_YELLOW_TENTACLE_FLOWER.get(), MoonBlocks.MOON_ROCK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, Nebulas.BLUE_TENTACLE_FLOWER.get(), Nebulas.POTTED_BLUE_TENTACLE_FLOWER.get(), MoonBlocks.MOON_ROCK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, Nebulas.PURPLE_TENTACLE_FLOWER.get(), Nebulas.POTTED_PURPLE_TENTACLE_FLOWER.get(), MoonBlocks.MOON_ROCK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.createCrossBlockWithDefaultItem(MoonBlocks.MOON_GRASS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.createDoublePlant(MoonBlocks.TALL_MOON_GRASS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.createCrossBlockWithDefaultItem(MoonBlocks.MOON_FERN.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        // crops
        createCropBlock(blockStateModelGenerator, Crops.DRAGON_CARROT_BLOCK.get(), MoonCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        createCropBlock(blockStateModelGenerator, Crops.BROODY_BLOCK.get(), MoonCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        createCropBlock(blockStateModelGenerator, Crops.EYE_BALLS_BLOCK.get(), MoonCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        // Nebulas
        blockStateModelGenerator.createAxisAlignedPillarBlock(Nebulas.BLUE_NEBULA_LOG.get(), TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(Nebulas.RED_NEBULA_LOG.get(), TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(Nebulas.PURPLE_NEBULA_LOG.get(), TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(Nebulas.YELLOW_NEBULA_LOG.get(), TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(Wander.TRUNN_LOG.get(), TexturedModel.COLUMN);
        blockStateModelGenerator.createTrivialCube(Nebulas.BLUE_NEBULA_LEAVES.get());
        blockStateModelGenerator.family(Nebulas.BLUE_NEBULA_PLANKS.get())
                .stairs(Nebulas.BLUE_NEBULA_PLANKS_STAIRS.get())
                .button(Nebulas.BLUE_NEBULA_PLANKS_BUTTON.get())
                .fence(Nebulas.BLUE_NEBULA_PLANKS_FENCE.get())
                .fenceGate(Nebulas.BLUE_NEBULA_PLANKS_FENCE_GATE.get())
                .slab(Nebulas.BLUE_NEBULA_PLANKS_SLAB.get());
        blockStateModelGenerator.createTrivialCube(Nebulas.PURPLE_NEBULA_LEAVES.get());
        blockStateModelGenerator.family(Nebulas.PURPLE_NEBULA_PLANKS.get())
                .stairs(Nebulas.PURPLE_NEBULA_PLANKS_STAIRS.get())
                .button(Nebulas.PURPLE_NEBULA_PLANKS_BUTTON.get())
                .fence(Nebulas.PURPLE_NEBULA_PLANKS_FENCE.get())
                .fenceGate(Nebulas.PURPLE_NEBULA_PLANKS_FENCE_GATE.get())
                .slab(Nebulas.PURPLE_NEBULA_PLANKS_SLAB.get());
        blockStateModelGenerator.createTrivialCube(Nebulas.RED_NEBULA_LEAVES.get());
        blockStateModelGenerator.family(Nebulas.RED_NEBULA_PLANKS.get())
                .stairs(Nebulas.RED_NEBULA_PLANKS_STAIRS.get())
                .button(Nebulas.RED_NEBULA_PLANKS_BUTTON.get())
                .fence(Nebulas.RED_NEBULA_PLANKS_FENCE.get())
                .fenceGate(Nebulas.RED_NEBULA_PLANKS_FENCE_GATE.get())
                .slab(Nebulas.RED_NEBULA_PLANKS_SLAB.get());
        blockStateModelGenerator.createTrivialCube(Nebulas.YELLOW_NEBULA_LEAVES.get());
        blockStateModelGenerator.family(Nebulas.YELLOW_NEBULA_PLANKS.get())
                .stairs(Nebulas.YELLOW_NEBULA_PLANKS_STAIRS.get())
                .button(Nebulas.YELLOW_NEBULA_PLANKS_BUTTON.get())
                .fence(Nebulas.YELLOW_NEBULA_PLANKS_FENCE.get())
                .fenceGate(Nebulas.YELLOW_NEBULA_PLANKS_FENCE_GATE.get())
                .slab(Nebulas.YELLOW_NEBULA_PLANKS_SLAB.get());
        blockStateModelGenerator.createTrivialCube(Nebulas.BLUE_NEBULA_REGROW_CORE.get());
        blockStateModelGenerator.createTrivialCube(Nebulas.PURPLE_NEBULA_REGROW_CORE.get());
        blockStateModelGenerator.createTrivialCube(Nebulas.RED_NEBULA_REGROW_CORE.get());
        blockStateModelGenerator.createTrivialCube(Nebulas.YELLOW_NEBULA_REGROW_CORE.get());
        blockStateModelGenerator.createTrivialCube(MoonBlocks.SUN_ENRICHED_MOON_ROCK.get());
        blockStateModelGenerator.createTrivialCube(MoonBlocks.POLISHED_SUN_ENRICHED_MOON_ROCK.get());
        TextureMapping dyliumMap = new TextureMapping()
                .put(TextureSlot.TOP, new Material(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/dylium_top")))
                .put(TextureSlot.BOTTOM, new Material(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/moon_rock")))
                .put(TextureSlot.SIDE, new Material(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/dylium_side")));

        registerTopBottom(blockStateModelGenerator, Darkness.DYLIUM.get(), dyliumMap);
        TextureMapping BorilMap = new TextureMapping()
                .put(TextureSlot.TOP, new Material(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/boril_top")))
                .put(TextureSlot.BOTTOM, new Material(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/puroil")))
                .put(TextureSlot.SIDE, new Material(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/boril_side")));

        registerTopBottom(blockStateModelGenerator, Wander.BORIL.get(), BorilMap);
        blockStateModelGenerator.createTrivialCube(Wander.PUROIL.get());
        registerCustomFlowerPotPlant(blockStateModelGenerator, Darkness.ROSE_OF_PAIN.get(), Darkness.POTTED_ROSE_OF_PAIN.get(), MoonBlocks.MOON_ROCK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, MoonBlocks.SPRUNGUS.get(), MoonBlocks.POTTED_SPRUNGUS.get(), MoonBlocks.MOON_ROCK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        registerCustomFlowerPotPlant(blockStateModelGenerator, RedOrbBlocks.POINTY.get(), RedOrbBlocks.POTTED_POINTY.get(), RedOrbBlocks.RED_ROCK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.createCrossBlock(RedOrbBlocks.PETRICY.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        // Chess
        blockStateModelGenerator.createTrivialCube(Chess.BLACK_CHESSBOARD.get());
        blockStateModelGenerator.createTrivialCube(Chess.WHITE_CHESSBOARD.get());
        blockStateModelGenerator.createTrivialCube(Chess.BLACK_BRICKS.get());
        blockStateModelGenerator.createTrivialCube(Chess.WHITE_BRICKS.get());
        // Other
        blockStateModelGenerator.createTrivialCube(StarBlocks.RED_STAR_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(StarBlocks.BLUE_STAR_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(StarBlocks.YELLOW_STAR_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(StarBlocks.PURPLE_STAR_BLOCK.get());
        // Hedge
        registerHedge(blockStateModelGenerator, Hedges.ACACIA_HEDGE.get(), Blocks.ACACIA_LOG);
        registerHedge(blockStateModelGenerator, Hedges.BIRCH_HEDGE.get(), Blocks.BIRCH_LOG);
        registerHedge(blockStateModelGenerator, Hedges.CHERRY_HEDGE.get(), Blocks.CHERRY_LOG);
        registerHedgeSide(blockStateModelGenerator, Hedges.CURVE_HEDGE.get(), MoonBlocks.CURVE_LOG.get());
        registerHedge(blockStateModelGenerator, Hedges.DARK_OAK_HEDGE.get(), Blocks.DARK_OAK_LOG);
        registerHedgeSide(blockStateModelGenerator, Hedges.DARKNESS_HEDGE.get(), Darkness.LOG_OF_DARKNESS.get());
        registerHedge(blockStateModelGenerator, Hedges.JUNGLE_HEDGE.get(), Blocks.JUNGLE_LOG);
        registerHedge(blockStateModelGenerator, Hedges.MANGROVE_HEDGE.get(), Blocks.MANGROVE_LOG);
        registerHedgeSide(blockStateModelGenerator, Hedges.MOON_HEDGE.get(), MoonBlocks.MOON_LOG.get());
        registerHedgeSide(blockStateModelGenerator, Hedges.FULL_MOON_HEDGE.get(), MoonBlocks.FULL_MOON_LOG.get());
        registerHedge(blockStateModelGenerator, Hedges.OAK_HEDGE.get(), Blocks.OAK_LOG);
        registerHedge(blockStateModelGenerator, Hedges.PALE_HEDGE.get(), Blocks.PALE_OAK_LOG);
        registerHedge(blockStateModelGenerator, Hedges.SPRUCE_HEDGE.get(), Blocks.SPRUCE_LOG);
        registerHedgeSide(blockStateModelGenerator, Hedges.STAR_HEDGE.get(), StarBlocks.STAR_LOG.get());
        registerHedgeSide(blockStateModelGenerator, Hedges.YERI_HEDGE.get(), RedOrbBlocks.YERI_LOG.get());
        registerHedgeSide(blockStateModelGenerator, Hedges.SPIRO_HEDGE.get(), RedOrbBlocks.SPIRO_LOG.get());
        registerHedgeSide(blockStateModelGenerator, Hedges.TRUNN_HEDGE.get(), Wander.TRUNN_LOG.get());
        // Red Orb
        blockStateModelGenerator.family(RedOrbBlocks.RED_ROCK.get())
                .stairs(RedOrbBlocks.RED_ROCK_STAIRS.get())
                .slab(RedOrbBlocks.RED_ROCK_SLAB.get());
        blockStateModelGenerator.createTrivialCube(RedOrbBlocks.POLISHED_RED_ROCK.get());
        blockStateModelGenerator.createAxisAlignedPillarBlock(RedOrbBlocks.YERI_LOG.get(), TexturedModel.COLUMN);
        blockStateModelGenerator.createTrivialCube(RedOrbBlocks.YERI_LEAVES.get());
        blockStateModelGenerator.family(RedOrbBlocks.YERI_PLANKS.get())
                .stairs(RedOrbBlocks.YERI_PLANKS_STAIRS.get())
                .button(RedOrbBlocks.YERI_PLANKS_BUTTON.get())
                .fence(RedOrbBlocks.YERI_PLANKS_FENCE.get())
                .fenceGate(RedOrbBlocks.YERI_PLANKS_FENCE_GATE.get())
                .slab(RedOrbBlocks.YERI_PLANKS_SLAB.get());
        blockStateModelGenerator.createTrivialCube(RedOrbBlocks.GREEN_ROCK.get());
        blockStateModelGenerator.createCrossBlockWithDefaultItem(RedOrbBlocks.BLUE_GRASS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.createAxisAlignedPillarBlock(RedOrbBlocks.SPIRO_LOG.get(), TexturedModel.COLUMN);
        blockStateModelGenerator.createAxisAlignedPillarBlock(RedOrbBlocks.GLASS_LOG.get(), TexturedModel.COLUMN);
        blockStateModelGenerator.createTrivialCube(RedOrbBlocks.SPIRO_LEAVES.get());
        blockStateModelGenerator.createTrivialCube(MoonBlocks.COMET_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(Energy.STARGENERATOR.get());
        registerCable(blockStateModelGenerator, Energy.YELLOW_CABLE.get());
        registerCable(blockStateModelGenerator, Energy.BLUE_CABLE.get());
        registerCable(blockStateModelGenerator, Energy.RED_CABLE.get());
        registerCable(blockStateModelGenerator, Energy.PURPLE_CABLE.get());
        blockStateModelGenerator.createTrivialCube(Energy.STARMACHINE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(Energy.NIGHT_WATCHER.get());
        blockStateModelGenerator.createTrivialCube(Energy.STAR_CRUSHER.get());
        blockStateModelGenerator.createTrivialCube(MoonBlocks.MOON_ROCK_CRYSTALS.get());
        blockStateModelGenerator.createTrivialCube(MoonBlocks.CRYSTAL_MOON_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(MoonBlocks.MOON_ROCK_IRON_ORE.get());
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.LODESTAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.IRON_DUST.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLD_DUST.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COPPER_DUST.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SUPERNOVA.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DREAM_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DARKSTAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STARDUST.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GEODE_FRUIT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COOKED_GEODE_FRUIT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.FULL_COOKED_GEODE_FRUIT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BLACK_COOKED_GEODE_FRUIT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BLUE_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RED_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RED_ORB_PLATFORM_BASE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.YELLOW_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.END_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PURPLE_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DREAM_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MOON_GLASS_SHARD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PRISMATIC_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.WISHING_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.WHITE_WISHING_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.LIGHT_GRAY_WISHING_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.GRAY_WISHING_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.BLACK_WISHING_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.BROWN_WISHING_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.RED_WISHING_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.ORANGE_WISHING_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.LIME_WISHING_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.GREEN_WISHING_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.CYAN_WISHING_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.LIGHT_BLUE_WISHING_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.BLUE_WISHING_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.PURPLE_WISHING_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.MAGENTA_WISHING_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(WishingStars.PINK_WISHING_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.THROWABLE_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STAR_BANNER_PATTERN.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STAR_HAMMER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SUN_ENRICHED_YELLOW_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.WINGED_STAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GUMMY_FISH.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GUMMY_WORM.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COSMO_FISH.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ENDER_FISH.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_CRUCIAN.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LUCKY_COMET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COMET_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.AURORA_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STAR_BOOK.get().asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(MoonBlocks.TALL_MOON_GRASS.get().asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MOON_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STAR_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MASK_OF_LUNA.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JESTER_BOOTS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JESTER_CHESTPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JESTER_LEGGINS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JESTER_HELMET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CRYSTAL_MOON.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MOON_BOOTS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MOON_CHESTPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MOON_LEGGINS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MOON_HELMET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SEED_PACKET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Crops.DRAGON_CARROT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Crops.BROODY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Crops.EYE_BALLS.get(), ModelTemplates.FLAT_ITEM);
        blockGeneratedItem(itemModelGenerator, StarBlocks.STAR_FLOWER.get());
        blockGeneratedItem(itemModelGenerator, StarBlocks.CELESTIAL_STAR_FLOWER.get());
        blockGeneratedItem(itemModelGenerator, ModBlock.BONEFLOWER.get());
        blockGeneratedItem(itemModelGenerator, RedOrbBlocks.POINTY.get());
        blockGeneratedItem(itemModelGenerator, RedOrbBlocks.PETRICY.get());
        blockGeneratedItem(itemModelGenerator, Nebulas.RED_TENTACLE_FLOWER.get());
        blockGeneratedItem(itemModelGenerator, Nebulas.BLUE_TENTACLE_FLOWER.get());
        blockGeneratedItem(itemModelGenerator, Nebulas.PURPLE_TENTACLE_FLOWER.get());
        blockGeneratedItem(itemModelGenerator, Nebulas.YELLOW_TENTACLE_FLOWER.get());
        blockGeneratedItem(itemModelGenerator, MoonBlocks.PURPLE_MUSHROOM.get());
        blockGeneratedItem(itemModelGenerator, MoonBlocks.MOON_SAPLING.get());
        blockGeneratedItem(itemModelGenerator, MoonBlocks.CURVE_SAPLING.get());
        blockGeneratedItem(itemModelGenerator, StarBlocks.STAR_SAPLING.get());
        blockGeneratedItem(itemModelGenerator, RedOrbBlocks.YERI_SAPLING.get());
        blockGeneratedItem(itemModelGenerator, RedOrbBlocks.SPIRO_SAPLING.get());
        blockGeneratedItem(itemModelGenerator, Darkness.DARKNESS_SAPLING.get());
        blockGeneratedItem(itemModelGenerator, MoonBlocks.FULL_MOON_SAPLING.get());
        blockGeneratedItem(itemModelGenerator, Darkness.ROSE_OF_PAIN.get());
        blockGeneratedItem(itemModelGenerator, MoonBlocks.SPRUNGUS.get());

        itemModelGenerator.generateFlatItem(ModItems.DEAD_EYE_BAT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LIVING_EYE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PRISMATIC_SHARD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ECTOPLASM.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COOLER_ECTOPLASM.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BLACK_BRICK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.WHITE_BRICK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(StarBlocks.STAR_DISPLAY.get().asItem(), ModelTemplates.FLAT_ITEM);

        // Spawn Eggs
        itemModelGenerator.generateFlatItem(ModItems.GHOST_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.AMETHYST_TURTLE_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.EYE_BAT_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SCRUBY_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ROOK_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BLACK_ROOK_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BLACK_FOX_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
    }

    public static final ModelTemplate CUSTOM_POT_CROSS = new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/potted_plant_custom")), Optional.empty(), TextureSlot.PLANT, TextureSlot.DIRT);
    public static final ModelTemplate HEDGE = new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/hedge")), Optional.empty(), TextureSlot.TOP, TextureSlot.SIDE);

    public final void registerCustomFlowerPotPlant(BlockModelGenerators blockStateModelGenerator, Block plantBlock, Block flowerPotBlock, Block dirt, BlockModelGenerators.PlantType tintType) {
        blockStateModelGenerator.createCrossBlock(plantBlock, tintType);
        TextureMapping potTextureMap = getCustomPotTextureMap(plantBlock, dirt);
        MultiVariant weightedVariant = plainVariant(CUSTOM_POT_CROSS.create(flowerPotBlock, potTextureMap, blockStateModelGenerator.modelOutput));
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(flowerPotBlock, weightedVariant));
    }

    public final void registerHedge(BlockModelGenerators blockStateModelGenerator, Block hedge, Block log) {
        TextureMapping potTextureMap = getHedgeMap(log);
        MultiVariant weightedVariant = plainVariant(HEDGE.create(hedge, potTextureMap, blockStateModelGenerator.modelOutput));
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(hedge, weightedVariant));
    }
    public final void registerHedgeSide(BlockModelGenerators blockStateModelGenerator, Block hedge, Block log) {
        TextureMapping potTextureMap = getHedgeSideMap(log);
        MultiVariant weightedVariant = plainVariant(HEDGE.create(hedge, potTextureMap, blockStateModelGenerator.modelOutput));
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(hedge, weightedVariant));
    }

    public final void registerTopBottom(BlockModelGenerators blockStateModelGenerator, Block block, TextureMapping texureMap) {
        MultiVariant weightedVariant = plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.create(block, texureMap, blockStateModelGenerator.modelOutput));
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
