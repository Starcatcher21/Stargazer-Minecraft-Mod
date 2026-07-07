package com.github.starcatcher21.stargazer.block.clases.star.star_display;

import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StarDisplay extends BaseEntityBlock {
    @Override
    protected MapCodec<? extends StarDisplay> codec() {
        return simpleCodec(StarDisplay::new);
    }

    @Override
    public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        this.spawnDestroyParticles(world, player, pos, state);

        BlockEntity be = world.getBlockEntity(pos);
        if (!player.isCreative() && be instanceof StarDisplayEntity ge) {
            world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), ge.getItem()));
        }
        world.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(player, StarBlocks.STAR_DISPLAY.defaultBlockState()));
        return StarBlocks.STAR_DISPLAY.defaultBlockState();
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (!player.getAbilities().mayBuild) {
            return InteractionResult.PASS;
        } else {
                BlockEntity be = world.getBlockEntity(pos);
                if (be instanceof StarDisplayEntity ge) {
                    player.addItem(ge.getItem());
                    ge.setItems(NonNullList.withSize(1, ItemStack.EMPTY));
                }
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!player.getAbilities().mayBuild) {
            return InteractionResult.PASS;
        } else {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof StarDisplayEntity ge) {
                if (ge.getItem().isEmpty()) {
                    ge.setItems(NonNullList.withSize(1, stack.copy()));
                    if (!player.isCreative()) {
                        stack.setCount(0);
                    }
                } else {
                    if (!player.hasInfiniteMaterials()) {
                        player.addItem(ge.getItem());
                    }
                    ge.setItems(NonNullList.withSize(1, stack.copy()));
                    if (!player.isCreative()) {
                        stack.setCount(0);
                    }
                }
            }
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.or(
                Shapes.box(0.1875, 0, 0.1875, 0.8125, 0.2, 0.8125)
        );
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return this.getShape(state, world, pos, context);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new StarDisplayEntity(pos, state);
    }

    public StarDisplay(Properties settings) {
        super(settings.replaceable());
    }

}
