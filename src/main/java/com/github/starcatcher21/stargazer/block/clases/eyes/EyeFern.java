package com.github.starcatcher21.stargazer.block.clases.eyes;

import com.github.starcatcher21.stargazer.block.register.EyeBloodBlocks;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EyeFern
        extends VegetationBlock
        implements BonemealableBlock {
    public static final MapCodec<EyeFern> CODEC = EyeFern.simpleCodec(EyeFern::new);
    private static final VoxelShape SHAPE = Block.column(12.0, 0.0, 13.0);
    public static final ImmutableList<Block> PLACE = ImmutableList.of(
            MoonBlocks.MOON_ROCK_NYLIUM,
            MoonBlocks.MOON_ROCK
    );

    public MapCodec<EyeFern> codec() {
        return CODEC;
    }

    public EyeFern(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(BlockStateProperties.EYE, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.EYE);
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return PLACE.contains(floor.getBlock());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (state.getValue(BlockStateProperties.EYE)) {
            world.setBlockAndUpdate(pos, EyeBloodBlocks.EYE_FERN.defaultBlockState().setValue(BlockStateProperties.EYE, false));
        } else {
            world.setBlockAndUpdate(pos, EyeBloodBlocks.EYE_FERN.defaultBlockState().setValue(BlockStateProperties.EYE, true));
        }    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {

    }
}