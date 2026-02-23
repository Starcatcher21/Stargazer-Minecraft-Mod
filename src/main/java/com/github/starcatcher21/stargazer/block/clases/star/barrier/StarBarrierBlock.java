package com.github.starcatcher21.stargazer.block.clases.star.barrier;

import com.github.starcatcher21.stargazer.Stargazer;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class StarBarrierBlock extends BlockWithEntity {
    public static final Identifier TEXTURE = Identifier.of(Stargazer.MOD_ID, "textures/block/star_barrier.png");
    @Override
    protected MapCodec<? extends StarBarrierBlock> getCodec() {
        return createCodec(StarBarrierBlock::new);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StarBarrierBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    public StarBarrierBlock(Settings settings) {
        super(settings);
    }

//    @Override
//    protected BlockRenderType getRenderType(BlockState state) {
//        return BlockRenderType.INVISIBLE;
//    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        if (context.isHolding(Registries.ITEM.get(Identifier.of(Stargazer.MOD_ID, "star_barrier_block")))) {
            return VoxelShapes.fullCube();
        } else {
            return VoxelShapes.empty();
        }
    }
}
