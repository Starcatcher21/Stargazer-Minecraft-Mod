package com.github.starcatcher21.stargazer.block.clases.energy.machines;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.screens.NightWatcherScreenHandler;
import com.github.starcatcher21.stargazer.screens.StarCrusherScreenHandler;
import com.github.starcatcher21.stargazer.screens.StarGeneratorScreenHandler;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;

public class StarCrusher extends BlockWithEntity {
    public static final MapCodec<StarCrusher> CODEC = Block.createCodec(StarCrusher::new);
    public static final Text TITLE = Text.translatable("container.starcrusher");
    public StarCrusher(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StarCrusherEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return  world.isClient() ? null : validateTicker(type, BlockTypes.STAR_CRUSHER, StarCrusherEntity::tick);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient()) {
            player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof StarCrusherEntity sge) {
            return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> new StarCrusherScreenHandler(syncId, inventory, sge, sge.propertyDelegate), TITLE);
        } else {
            return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> new StarCrusherScreenHandler(syncId, inventory, pos), TITLE);
        }
    }

}
