package com.github.starcatcher21.stargazer.block.clases.eyes.eyejar;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EyeJar extends BaseEntityBlock {
    @Override
    protected MapCodec<? extends EyeJar> codec() {
        return simpleCodec(EyeJar::new);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.or(
                Shapes.box(0.1875, 0.00625, 0.1875, 0.8125, 0.7562500000000001, 0.8125),
                Shapes.box(0.25, 0.7562500000000001, 0.25, 0.75, 0.8187500000000001, 0.75)
        );
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return this.getShape(state, world, pos, context);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EyeJarEntity(pos, state);
    }

    public EyeJar(Properties settings) {
        super(settings.replaceable());
    }

}
