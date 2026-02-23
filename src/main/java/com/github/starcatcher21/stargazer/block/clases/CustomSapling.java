package com.github.starcatcher21.stargazer.block.clases;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Optional;

public class CustomSapling extends PlantBlock implements Fertilizable {
    public static final ImmutableList<Block> PLACE = ImmutableList.of(
            MoonBlocks.MOON_ROCK, Blocks.END_STONE, MoonBlocks.MOON_ROCK_NYLIUM
    );

    private final RegistryKey<ConfiguredFeature<?, ?>> featureKey;

    public CustomSapling(RegistryKey<ConfiguredFeature<?, ?>> featureKey, AbstractBlock.Settings settings) {
        super(settings);
        this.featureKey = featureKey;
    }

    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return null;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return PLACE.contains(floor.getBlock());
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 12.0, 14.0);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(7) == 0) {
            this.instantGrow(world, pos, state, random);
        } else {
            if (world.getBlockState(pos.up()).getBlock().equals(StarBlocks.COSMIC_BLOCK) && random.nextInt(3) == 0) {
                this.instantGrow(world, pos, state, random);
            }
        }
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if (random.nextInt(15) > 3) {
            return;
        }
        this.instantGrow(world, pos, state, random);
    }

    public void instantGrow(ServerWorld world, BlockPos pos, BlockState state, Random random) {
        Optional<RegistryEntry.Reference<ConfiguredFeature<?, ?>>> optional = world.getRegistryManager().getOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOptional(this.featureKey);
        ((ConfiguredFeature)((RegistryEntry)optional.get()).value()).generate(world, world.getChunkManager().getChunkGenerator(), random, pos);
    }
}
