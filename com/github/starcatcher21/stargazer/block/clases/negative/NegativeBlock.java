package com.github.starcatcher21.stargazer.block.clases.negative;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NegativeBlock extends BlockWithEntity {
    @Override
    protected MapCodec<? extends NegativeBlock> getCodec() {
        return createCodec(NegativeBlock::new);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NegativeBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        if (context.isHolding(Registries.ITEM.get(Identifier.of(Stargazer.MOD_ID, "negative_block"))) || context.isHolding(ModItems.STAR_HAMMER)) {
            return VoxelShapes.fullCube();
        } else {
            return VoxelShapes.cuboid(0.0, 0.0, 0.0, 0.01, 0.01, 0.01);
        }
    }

    public NegativeBlock(Settings settings) {
        super(settings.replaceable());
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (placer != null) {
            if (placer instanceof ServerPlayerEntity spe) {
                Criterias.negative.trigger(spe);
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

}
