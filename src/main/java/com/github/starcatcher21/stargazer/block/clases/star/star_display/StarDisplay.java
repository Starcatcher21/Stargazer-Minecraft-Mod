package com.github.starcatcher21.stargazer.block.clases.star.star_display;

import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class StarDisplay extends BlockWithEntity {
    @Override
    protected MapCodec<? extends StarDisplay> getCodec() {
        return createCodec(StarDisplay::new);
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        this.spawnBreakParticles(world, player, pos, state);

        BlockEntity be = world.getBlockEntity(pos);
        if (!player.isCreative() && be instanceof StarDisplayEntity ge) {
            world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), ge.getItem()));
        }
        world.emitGameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Emitter.of(player, StarBlocks.STAR_DISPLAY.getDefaultState()));
        return StarBlocks.STAR_DISPLAY.getDefaultState();
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!player.getAbilities().allowModifyWorld) {
            return ActionResult.PASS;
        } else {
                BlockEntity be = world.getBlockEntity(pos);
                if (be instanceof StarDisplayEntity ge) {
                    player.giveItemStack(ge.getItem());
                    ge.setItems(DefaultedList.ofSize(1, ItemStack.EMPTY));
                }
            return ActionResult.SUCCESS;
        }
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.getAbilities().allowModifyWorld) {
            return ActionResult.PASS;
        } else {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof StarDisplayEntity ge) {
                if (ge.getItem().isEmpty()) {
                    ge.setItems(DefaultedList.ofSize(1, stack.copy()));
                    if (!player.isCreative()) {
                        stack.setCount(0);
                    }
                } else {
                    if (!player.isInCreativeMode()) {
                        player.giveItemStack(ge.getItem());
                    }
                    ge.setItems(DefaultedList.ofSize(1, stack.copy()));
                    if (!player.isCreative()) {
                        stack.setCount(0);
                    }
                }
            }
            return ActionResult.SUCCESS;
        }
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.union(
                VoxelShapes.cuboid(0.1875, 0, 0.1875, 0.8125, 0.2, 0.8125)
        );
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.getOutlineShape(state, world, pos, context);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StarDisplayEntity(pos, state);
    }

    public StarDisplay(Settings settings) {
        super(settings.replaceable());
    }

}
