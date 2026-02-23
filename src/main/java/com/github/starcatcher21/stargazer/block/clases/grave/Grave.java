package com.github.starcatcher21.stargazer.block.clases.grave;

import com.github.starcatcher21.stargazer.Helpers;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Optional;

public class Grave extends BlockWithEntity {

    public static final MapCodec<Grave> CODEC = Block.createCodec(Grave::new);
    public static final BooleanProperty ACTIVATED = BooleanProperty.of("activated");

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GraveEntity(pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
        builder.add(ACTIVATED);
    }

    public Grave(Settings settings) {
        super(settings);

        setDefaultState(getDefaultState().with(ACTIVATED, false));
    }

    @Override
    protected MapCodec<? extends Grave> getCodec() {
        return CODEC;
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        this.spawnBreakParticles(world, player, pos, state);

        BlockEntity be = world.getBlockEntity(pos);
        if (!player.isCreative() && be instanceof GraveEntity ge) {
            if (ge.TYPE.equals(Optional.of("item"))) {
                world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), ge.items.getFirst()));
            }
        }
        world.emitGameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Emitter.of(player, ModBlock.GRAVE.getDefaultState()));
        return ModBlock.GRAVE.getDefaultState();
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!player.getAbilities().allowModifyWorld) {
            return ActionResult.PASS;
        } else {
            boolean active = state.get(ACTIVATED);
            ItemStack stack = player.getStackInHand(player.getActiveHand());
            Item item = stack.getItem();

            if (active) {
                if (item instanceof ShovelItem) {
                    stack.damage(1, player);
                    world.setBlockState(pos, state.with(ACTIVATED, false));
                    BlockEntity be = world.getBlockEntity(pos);
                    if (be instanceof GraveEntity ge) {
                        if (ge.TYPE.equals(Optional.of("item"))) {
                            world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY()+0.3, pos.getZ(), ge.items.getFirst()));
                        }
                    }
                } else {
                    return ActionResult.PASS;
                }
            } else {

                if (item != Items.AIR) {
                    world.setBlockState(pos, state.with(ACTIVATED, true));
                    BlockEntity be = world.getBlockEntity(pos);
                    if (be instanceof GraveEntity ge) {
                        ge.setNbtType(Optional.of("item"));
                        ge.setNbtItems(DefaultedList.ofSize(1, stack.copy()));
                        if (!player.isCreative()) {
                            stack.setCount(0);
                        }
                    }

                }
            }

            return ActionResult.SUCCESS;
        }
    }

    @Override
    public  VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(Properties.HORIZONTAL_FACING);
        return VoxelShapes.union(
                Helpers.FacingHorizontal(dir, 0.1875F, 0.8125F, 0.875F, 0.8125F, 0.9375F, 1F, true),
                Helpers.FacingHorizontal(dir, 0.125F, 0F, 0.875F, 0.875F, 0.8125F, 1F, true)
        );

    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(Properties.HORIZONTAL_FACING);
        return VoxelShapes.union(
                VoxelShapes.cuboid(0.0F, 0.0F, 0.0F, 1.0F, 0.1F, 1.0F),
                this.getCollisionShape(state, view, pos, context)
        );

    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

}
