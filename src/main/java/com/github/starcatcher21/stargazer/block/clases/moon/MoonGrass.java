package com.github.starcatcher21.stargazer.block.clases.moon;

import com.github.starcatcher21.stargazer.block.clases.CustomSapling;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MoonGrass
        extends VegetationBlock
        implements BonemealableBlock {
    public static final MapCodec<MoonGrass> CODEC = MoonGrass.simpleCodec(MoonGrass::new);
    private static final VoxelShape SHAPE = Block.column(12.0, 0.0, 13.0);

    public MapCodec<MoonGrass> codec() {
        return CODEC;
    }

    public MoonGrass(BlockBehaviour.Properties settings) {
        super(settings);
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return CustomSapling.PLACE.contains(floor.getBlock());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return MoonGrass.getLargeVariant(state).defaultBlockState().canSurvive(world, pos) && world.isEmptyBlock(pos.above());
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        DoublePlantBlock.placeAt(world, getLargeVariant(state).defaultBlockState(), pos, 2);
    }

    private static DoublePlantBlock getLargeVariant(BlockState state) {
        return (DoublePlantBlock) MoonBlocks.TALL_MOON_GRASS;
    }
}

