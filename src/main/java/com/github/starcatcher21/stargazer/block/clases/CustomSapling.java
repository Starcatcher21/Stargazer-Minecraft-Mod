package com.github.starcatcher21.stargazer.block.clases;

import com.github.starcatcher21.stargazer.block.register.Darkness;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import java.util.Optional;

public class CustomSapling extends VegetationBlock implements BonemealableBlock {
    public static final MapCodec<CustomSapling> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ResourceKey.codec(Registries.CONFIGURED_FEATURE).fieldOf("feature").forGetter(block -> block.featureKey),
            propertiesCodec()
    ).apply(instance, CustomSapling::new));

    public static final ImmutableList<Block> PLACE = ImmutableList.of(
            MoonBlocks.MOON_ROCK, Blocks.END_STONE, MoonBlocks.MOON_ROCK_NYLIUM, Darkness.DYLIUM
    );

    private final ResourceKey<ConfiguredFeature<?, ?>> featureKey;

    public CustomSapling(ResourceKey<ConfiguredFeature<?, ?>> featureKey, Properties settings) {
        super(settings);
        this.featureKey = featureKey;
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return PLACE.contains(floor.getBlock());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Block.box(2.0, 0.0, 2.0, 14.0, 12.0, 14.0);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (random.nextInt(7) == 0) {
            this.instantGrow(world, pos, state, random);
        } else {
            if (world.getBlockState(pos.above()).getBlock().equals(StarBlocks.COSMIC_BLOCK) && random.nextInt(3) == 0) {
                this.instantGrow(world, pos, state, random);
            }
        }
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        if (random.nextInt(15) > 3) {
            return;
        }
        this.instantGrow(world, pos, state, random);
    }

    public void instantGrow(ServerLevel world, BlockPos pos, BlockState state, RandomSource random) {
        Optional<Holder.Reference<ConfiguredFeature<?, ?>>> optional = world.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(this.featureKey);
        if (optional.isEmpty()) return;
        ((ConfiguredFeature)((Holder)optional.get()).value()).place(world, world.getChunkSource().getGenerator(), random, pos);
    }
}
