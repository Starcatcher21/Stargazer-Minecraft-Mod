package com.github.starcatcher21.stargazer.block.clases.nebulas;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
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

    public NebulaCore(RegistryKey<ConfiguredFeature<?, ?>> featureKey, Settings settings) {
        super(settings);
        this.featureKey = featureKey;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (Dimensions.REG_COSMIC_WORLD.getValue().equals(world.getRegistryKey().getValue()) && random.nextInt(7) == 0) {
            Stargazer.LOGGER.error("try to spawn");
            this.instantGrow(world, pos.up(), state, random);
        }
    }

    public void instantGrow(ServerWorld world, BlockPos pos, BlockState state, Random random) {
        Optional<RegistryEntry.Reference<ConfiguredFeature<?, ?>>> optional = world.getRegistryManager().getOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOptional(this.featureKey);
        ((ConfiguredFeature)((RegistryEntry)optional.get()).value()).generate(world, world.getChunkManager().getChunkGenerator(), random, pos);
    }
}
