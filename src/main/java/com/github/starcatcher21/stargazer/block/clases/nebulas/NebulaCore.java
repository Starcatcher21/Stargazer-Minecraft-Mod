package com.github.starcatcher21.stargazer.block.clases.nebulas;

import com.github.starcatcher21.stargazer.worldgen.dimensions.Dimensions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Optional;

public class NebulaCore extends Block {
    private final RegistryKey<ConfiguredFeature<?, ?>> featureKey;
    private final Block flower;

    public NebulaCore(RegistryKey<ConfiguredFeature<?, ?>> featureKey, Block flower, Settings settings) {
        super(settings);
        this.featureKey = featureKey;
        this.flower = flower;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(7) == 0) {
            if (Dimensions.REG_COSMIC_WORLD.getValue().equals(world.getRegistryKey().getValue())) {
                this.instantGrow(world, pos.up(), state, random);
            } else {
                if (world.getBlockState(pos.up()).isAir()) {
                    world.setBlockState(pos.up(), flower.getDefaultState());
                }
            }
        }
    }

    public void instantGrow(ServerWorld world, BlockPos pos, BlockState state, Random random) {
        Optional<RegistryEntry.Reference<ConfiguredFeature<?, ?>>> optional = world.getRegistryManager().getOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOptional(this.featureKey);
        ((ConfiguredFeature)((RegistryEntry)optional.get()).value()).generate(world, world.getChunkManager().getChunkGenerator(), random, pos);
    }
}
