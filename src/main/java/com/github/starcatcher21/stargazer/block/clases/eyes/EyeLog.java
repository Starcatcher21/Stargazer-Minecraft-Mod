package com.github.starcatcher21.stargazer.block.clases.eyes;

import com.github.starcatcher21.stargazer.block.register.EyeBloodBlocks;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
//? if >= 26.2 {
/*import net.minecraft.advancements.triggers.CriteriaTriggers;
 *///?} else {
import net.minecraft.advancements.CriteriaTriggers;
//?}
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

//? if < 26.2 {
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.level.block.state.BlockBehaviour;
//?}

public class EyeLog extends RotatedPillarBlock {
    //? if >= 26.2 {
    /*public static final MapCodec<EyeLog> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("strip").forGetter(block -> block.STRIP),
            propertiesCodec()
    ).apply(instance, EyeLog::new));
    *///?} else {
    public static final MapCodec<EyeLog> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("strip").forGetter(block -> block.STRIP)
    ).apply(instance, strip -> new EyeLog(strip, BlockBehaviour.Properties.of())));
    //?}

    protected Block STRIP;
    protected String VARIANT;

    @Override
    public MapCodec<? extends RotatedPillarBlock> codec() {
        return CODEC;
    }

    /* ---------------- useItemOn (return type differs) ---------------- */
    //? if >= 26.2 {
    /*@Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level world,
                                          BlockPos pos, Player player,
                                          InteractionHand hand, BlockHitResult hit) {
        if (!stack.is(ItemTags.AXES)) {
            return InteractionResult.PASS;
        }
        BlockState newState = STRIP.defaultBlockState()
                .setValue(BlockStateProperties.AXIS, state.getValue(BlockStateProperties.AXIS));
        world.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
        if (player instanceof ServerPlayer sp) {
            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(sp, pos, stack);
        }
        world.setBlock(pos, newState, Block.UPDATE_ALL_IMMEDIATE);
        world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
        if (!player.hasInfiniteMaterials() && stack.isDamageableItem()) {
            stack.hurtWithoutBreaking(1, player);
        }
        return InteractionResult.SUCCESS;
    }
    *///?} else {
    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level world,
                                              BlockPos pos, Player player,
                                              InteractionHand hand, BlockHitResult hit) {
        if (!stack.is(ItemTags.AXES)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        BlockState newState = STRIP.defaultBlockState()
                .setValue(BlockStateProperties.AXIS, state.getValue(BlockStateProperties.AXIS));
        world.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
        if (player instanceof ServerPlayer sp) {
            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(sp, pos, stack);
        }
        world.setBlock(pos, newState, Block.UPDATE_ALL_IMMEDIATE);
        world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
        if (!player.getAbilities().instabuild && stack.isDamageableItem()) {
            stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
        }
        return ItemInteractionResult.SUCCESS;
    }
    //?}

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        boolean eye = state.getValue(BlockStateProperties.EYE);
        world.setBlockAndUpdate(pos, EyeBloodBlocks.EYE_LOG.get().defaultBlockState()
                .setValue(BlockStateProperties.EYE, !eye)
                .setValue(BlockStateProperties.AXIS, state.getValue(BlockStateProperties.AXIS)));
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    public EyeLog(Block strip, Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(BlockStateProperties.AXIS, Direction.Axis.Y)
                .setValue(BlockStateProperties.EYE, false));
        STRIP = strip;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.AXIS, BlockStateProperties.EYE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return super.getStateForPlacement(ctx)
                .setValue(BlockStateProperties.EYE, false)
                .setValue(BlockStateProperties.AXIS, ctx.getClickedFace().getAxis());
    }
}
