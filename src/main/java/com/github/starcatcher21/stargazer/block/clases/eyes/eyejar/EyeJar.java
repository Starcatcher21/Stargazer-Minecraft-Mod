package com.github.starcatcher21.stargazer.block.clases.eyes.eyejar;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class EyeJar extends BlockWithEntity {
    @Override
    protected MapCodec<? extends EyeJar> getCodec() {
        return createCodec(EyeJar::new);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.union(
                VoxelShapes.cuboid(0.1875, 0.00625, 0.1875, 0.8125, 0.7562500000000001, 0.8125),
                VoxelShapes.cuboid(0.25, 0.7562500000000001, 0.25, 0.75, 0.8187500000000001, 0.75)
        );
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.getOutlineShape(state, world, pos, context);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EyeJarEntity(pos, state);
    }

    public EyeJar(Settings settings) {
        super(settings.replaceable());
    }

}
