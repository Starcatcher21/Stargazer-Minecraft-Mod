package com.github.starcatcher21.stargazer.block.clases.noblue;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NoBlueBlock extends BaseEntityBlock {
    @Override
    protected MapCodec<? extends NoBlueBlock> codec() {
        return simpleCodec(NoBlueBlock::new);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NoBlueBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext context) {
        if (context.isHoldingItem(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "no_blue_block"))) || context.isHoldingItem(ModItems.STAR_HAMMER)) {
            return Shapes.block();
        } else {
            return Shapes.box(0.0, 0.0, 0.0, 0.01, 0.01, 0.01);
        }
    }

    public NoBlueBlock(Properties settings) {
        super(settings.replaceable());
    }
}
