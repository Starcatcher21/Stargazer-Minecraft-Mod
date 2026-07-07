package com.github.starcatcher21.stargazer.block.clases.negative;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class NegativeBlock extends BaseEntityBlock {
    @Override
    protected MapCodec<? extends NegativeBlock> codec() {
        return simpleCodec(NegativeBlock::new);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NegativeBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext context) {
        if (context.isHoldingItem(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "negative_block"))) || context.isHoldingItem(ModItems.STAR_HAMMER)) {
            return Shapes.block();
        } else {
            return Shapes.box(0.0, 0.0, 0.0, 0.01, 0.01, 0.01);
        }
    }

    public NegativeBlock(Properties settings) {
        super(settings.replaceable());
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (placer != null) {
            if (placer instanceof ServerPlayer spe) {
                Criterias.negative.trigger(spe);
            }
        }
        super.setPlacedBy(world, pos, state, placer, itemStack);
    }

}
