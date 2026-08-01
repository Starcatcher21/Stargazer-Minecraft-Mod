package com.github.starcatcher21.stargazer.block.clases.grave;

import com.github.starcatcher21.starlib.Helpers;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import java.util.Optional;

public class Grave extends BaseEntityBlock {

    public static final MapCodec<Grave> CODEC = Block.simpleCodec(Grave::new);
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GraveEntity(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING);
        builder.add(ACTIVATED);
    }

    public Grave(Properties settings) {
        super(settings);

        registerDefaultState(defaultBlockState().setValue(ACTIVATED, false));
    }

    @Override
    protected MapCodec<? extends Grave> codec() {
        return CODEC;
    }

    @Override
    public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        this.spawnDestroyParticles(world, player, pos, state);

        BlockEntity be = world.getBlockEntity(pos);
        if (!player.isCreative() && be instanceof GraveEntity ge) {
            if (ge.TYPE.equals(Optional.of("item"))) {
                world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), ge.items.getFirst()));
            }
        }
        world.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(player, ModBlock.GRAVE.defaultBlockState()));
        return ModBlock.GRAVE.defaultBlockState();
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (!player.getAbilities().mayBuild) {
            return InteractionResult.PASS;
        } else {
            boolean active = state.getValue(ACTIVATED);
            ItemStack stack = player.getItemInHand(player.getUsedItemHand());
            Item item = stack.getItem();

            if (active) {
                if (item instanceof ShovelItem) {
                    stack.hurtWithoutBreaking(1, player);
                    world.setBlockAndUpdate(pos, state.setValue(ACTIVATED, false));
                    BlockEntity be = world.getBlockEntity(pos);
                    if (be instanceof GraveEntity ge) {
                        if (ge.TYPE.equals(Optional.of("item"))) {
                            world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY()+0.3, pos.getZ(), ge.items.getFirst()));
                        }
                    }
                } else {
                    return InteractionResult.PASS;
                }
            } else {

                if (item != Items.AIR) {
                    world.setBlockAndUpdate(pos, state.setValue(ACTIVATED, true));
                    BlockEntity be = world.getBlockEntity(pos);
                    if (be instanceof GraveEntity ge) {
                        ge.setNbtType(Optional.of("item"));
                        ge.setNbtItems(NonNullList.withSize(1, stack.copy()));
                        if (!player.isCreative()) {
                            stack.setCount(0);
                        }
                    }

                }
            }

            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public  VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction dir = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        return Shapes.or(
                Helpers.FacingHorizontal(dir, 0.1875F, 0.8125F, 0.875F, 0.8125F, 0.9375F, 1F, true),
                Helpers.FacingHorizontal(dir, 0.125F, 0F, 0.875F, 0.875F, 0.8125F, 1F, true)
        );

    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext context) {
        Direction dir = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        return Shapes.or(
                Shapes.box(0.0F, 0.0F, 0.0F, 1.0F, 0.1F, 1.0F),
                this.getCollisionShape(state, view, pos, context)
        );

    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return super.getStateForPlacement(ctx).setValue(BlockStateProperties.HORIZONTAL_FACING, ctx.getHorizontalDirection().getOpposite());
    }

}
