package com.github.starcatcher21.stargazer.block.clases.star.aurora;

import com.github.starcatcher21.stargazer.Stargazer;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Aurora extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/block/aurora.png");
    @Override
    public MapCodec<? extends Aurora> codec() {
        return null;
    }

    public Aurora(Properties settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getBlockSupportShape(BlockState state, BlockGetter world, BlockPos pos) {
        return Shapes.empty();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AuroraEntity(pos, state);
    }
}
