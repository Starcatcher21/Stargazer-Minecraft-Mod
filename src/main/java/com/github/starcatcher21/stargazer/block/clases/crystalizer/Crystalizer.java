package com.github.starcatcher21.stargazer.block.clases.crystalizer;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.block.clases.grave.GraveEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class Crystalizer extends BaseEntityBlock {
    public static final MapCodec<Crystalizer> CODEC = Block.simpleCodec(Crystalizer::new);

    public Crystalizer(Properties arg) {
        super(arg);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (!player.getAbilities().mayBuild) {
            return InteractionResult.PASS;
        } else {
            ItemStack stack = player.getItemInHand(player.getUsedItemHand());
            Item item = stack.getItem();

            if (item != Items.AIR && stack.is(CustomTags.GEM)) {
                BlockEntity be = world.getBlockEntity(pos);
                if (be instanceof CrystalizerEntity ce) {
                    if (ce.getInput() != ItemStack.EMPTY) {
                        ItemStack in = ce.getInput();
                        if (!player.isCreative()) {
                            player.addItem(in);
                        }
                    }
                    ce.setInput(stack.copyWithCount(1));
                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }
                }
            } else if (item != Items.AIR && !stack.is(CustomTags.GEM)) {
                BlockEntity be = world.getBlockEntity(pos);
                if (be instanceof CrystalizerEntity ce) {
                    if (ce.getInput2() != ItemStack.EMPTY) {
                        ItemStack in2 = ce.getInput2();
                        if (!player.isCreative()) {
                            player.addItem(in2);
                        }
                    }
                    ce.setInput2(stack.copyWithCount(1));
                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }
                }
            } else {
                BlockEntity be = world.getBlockEntity(pos);
                if (be instanceof CrystalizerEntity ce) {
                    if (ce.getOutput() != ItemStack.EMPTY) {
                        ItemStack out = ce.getOutput().copy();
                        player.addItem(out);
                        ce.setOutput(ItemStack.EMPTY);
                    } else if (ce.getInput2() != ItemStack.EMPTY) {
                        ItemStack in2 = ce.getInput2().copy();
                        player.addItem(in2);
                        ce.setInput2(ItemStack.EMPTY);
                    } else if (ce.getInput() != ItemStack.EMPTY) {
                        ItemStack in = ce.getInput().copy();
                        player.addItem(in);
                        ce.setInput(ItemStack.EMPTY);
                    }
                }
            }

            return InteractionResult.SUCCESS;
        }
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.box(0,0,0, 1.0, 0.375, 1.0);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return createTickerHelper(type, BlockTypes.CRYSTALIZER.get(), CrystalizerEntity::serverTick);
    }


    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CrystalizerEntity(pos, state);
    }
}
