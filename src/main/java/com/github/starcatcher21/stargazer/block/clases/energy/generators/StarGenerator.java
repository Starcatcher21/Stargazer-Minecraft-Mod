package com.github.starcatcher21.stargazer.block.clases.energy.generators;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.screens.StarGeneratorScreenHandler;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

public class StarGenerator extends BaseEntityBlock {
    public static final MapCodec<StarGenerator> CODEC = Block.simpleCodec(StarGenerator::new);
    public static final Component TITLE = Component.translatable("container.stargenerator");
    public StarGenerator(Properties settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new StarGeneratorEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return  world.isClientSide() ? null : createTickerHelper(type, BlockTypes.STAR_GENERATOR, StarGeneratorEntity::tick);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (!world.isClientSide()) {
            player.openMenu(state.getMenuProvider(world, pos));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof StarGeneratorEntity sge) {
            return new SimpleMenuProvider((syncId, inventory, player) -> new StarGeneratorScreenHandler(syncId, inventory, sge, sge.propertyDelegate), TITLE);
        } else {
            return new SimpleMenuProvider((syncId, inventory, player) -> new StarGeneratorScreenHandler(syncId, inventory, pos), TITLE);
        }
    }

}
