package com.github.starcatcher21.stargazer.block.clases.moon;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

import java.util.List;
import java.util.Optional;

public class MoonRockNylium extends SpreadableBlock implements Fertilizable {
    public MoonRockNylium(Settings settings) {
        super(settings);
    }

    public static final MapCodec<MoonRockNylium> CODEC = GrassBlock.createCodec(MoonRockNylium::new);

    @Override
    protected MapCodec<? extends SpreadableBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.isIn(ItemTags.HOES)) {
            world.playSound((Entity)player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
            if (!world.isClient) {
                if (player != null) {
                    stack.damage(1, player, LivingEntity.getSlotForHand(hand));
                }
            }
            world.setBlockState(pos, MoonBlocks.MOON_FARMLAND.getDefaultState());
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getLightLevel(pos.up()) >= 9) {
            BlockState blockState = this.getDefaultState();
            for (int i = 0; i < 4; ++i) {
                BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if (!world.getBlockState(blockPos).isOf(MoonBlocks.MOON_ROCK)) continue;
                world.setBlockState(blockPos, (BlockState)blockState);
            }
        }
        if (!world.getBlockState(pos.up()).isTransparent()) {
            world.setBlockState(pos, MoonBlocks.MOON_ROCK.getDefaultState());
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(state));
        }
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return world.getBlockState(pos.up()).isAir();
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos.up();
        BlockState blockState = MoonBlocks.MOON_GRASS.getDefaultState();
        Optional<RegistryEntry.Reference<PlacedFeature>> optional = world.getRegistryManager().getOrThrow(RegistryKeys.PLACED_FEATURE).getOptional(RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Stargazer.MOD_ID, "moon_grass_bone")));
        block0: for (int i = 0; i < 128; ++i) {
            RegistryEntry<PlacedFeature> registryEntry;
            Fertilizable fertilizable;
            BlockPos blockPos2 = blockPos;
            for (int j = 0; j < i / 16; ++j) {
                if (!world.getBlockState((blockPos2 = blockPos2.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1)).down()).isOf(this) || world.getBlockState(blockPos2).isFullCube(world, blockPos2)) continue block0;
            }
            BlockState blockState2 = world.getBlockState(blockPos2);
            if (blockState2.isOf(blockState.getBlock()) && random.nextInt(10) == 0 && (fertilizable = (Fertilizable)((Object)blockState.getBlock())).isFertilizable(world, blockPos2, blockState2)) {
                fertilizable.grow(world, random, blockPos2, blockState2);
            }
            if (!blockState2.isAir()) continue;
            if (random.nextInt(8) == 0) {
                List<ConfiguredFeature<?, ?>> list = world.getBiome(blockPos2).value().getGenerationSettings().getFlowerFeatures();
                if (list.isEmpty()) continue;
                int k = random.nextInt(list.size());
                registryEntry = ((RandomPatchFeatureConfig)list.get(k).config()).feature();
            } else {
                if (!optional.isPresent()) continue;
                registryEntry = (RegistryEntry<PlacedFeature>)optional.get();
            }
            ((PlacedFeature)registryEntry.value()).generateUnregistered(world, world.getChunkManager().getChunkGenerator(), random, blockPos2);
        }
    }

    @Override
    public Fertilizable.FertilizableType getFertilizableType() {
        return Fertilizable.FertilizableType.NEIGHBOR_SPREADER;
    }}
