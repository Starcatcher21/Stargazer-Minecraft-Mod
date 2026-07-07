package com.github.starcatcher21.stargazer.block.clases.nogreen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.item.ModItems;
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

public class NoGreenBlock extends BlockWithEntity {
    @Override
    protected MapCodec<? extends NoGreenBlock> getCodec() {
        return createCodec(NoGreenBlock::new);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NoGreenBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        if (context.isHolding(Registries.ITEM.get(Identifier.of(Stargazer.MOD_ID, "no_green_block"))) || context.isHolding(ModItems.STAR_HAMMER)) {
            return VoxelShapes.fullCube();
        } else {
            return VoxelShapes.cuboid(0.0, 0.0, 0.0, 0.01, 0.01, 0.01);
        }
    }

    public NoGreenBlock(Settings settings) {
        super(settings.replaceable());
    }
}
