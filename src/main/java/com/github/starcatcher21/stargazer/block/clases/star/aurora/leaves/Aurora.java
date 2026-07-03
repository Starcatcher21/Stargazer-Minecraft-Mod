package com.github.starcatcher21.stargazer.block.clases.star.aurora.leaves;

import com.github.starcatcher21.stargazer.Stargazer;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class Aurora extends BlockWithEntity implements Waterloggable {
    public static final Identifier TEXTURE = Identifier.of(Stargazer.MOD_ID, "textures/block/aurora.png");
    @Override
    public MapCodec<? extends Aurora> getCodec() {
        return null;
    }

    public Aurora(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AuroraEntity(pos, state);
    }
}
