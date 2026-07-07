package com.github.starcatcher21.stargazer.block.clases.nebulas;

import com.github.starcatcher21.stargazer.worldgen.dimensions.Dimensions;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class NebulaCore extends Block {
    private final ResourceKey<ConfiguredFeature<?, ?>> featureKey;
    private final Block flower;

    public NebulaCore(ResourceKey<ConfiguredFeature<?, ?>> featureKey, Block flower, Properties settings) {
        super(settings);
        this.featureKey = featureKey;
        this.flower = flower;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (random.nextInt(7) == 0) {
            if (Dimensions.REG_COSMIC_WORLD.identifier().equals(world.dimension().identifier())) {
                this.instantGrow(world, pos.above(), state, random);
            } else {
                if (world.getBlockState(pos.above()).isAir()) {
                    world.setBlockAndUpdate(pos.above(), flower.defaultBlockState());
                }
            }
        }
    }

    public void instantGrow(ServerLevel world, BlockPos pos, BlockState state, RandomSource random) {
        Optional<Holder.Reference<ConfiguredFeature<?, ?>>> optional = world.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(this.featureKey);
        ((ConfiguredFeature)((Holder)optional.get()).value()).place(world, world.getChunkSource().getGenerator(), random, pos);
    }
}
