package com.github.starcatcher21.stargazer.block.clases.moon;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
//? if < 26.2 {
import net.minecraft.world.ItemInteractionResult;
//?}
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.lighting.LightEngine;
import net.minecraft.world.phys.BlockHitResult;

public class MoonRockNylium extends Block implements BonemealableBlock {
    public MoonRockNylium(Properties settings) {
        super(settings);
    }

    public static final MapCodec<MoonRockNylium> CODEC = Block.simpleCodec(MoonRockNylium::new);

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    //? if >= 26.2 {
    /*@Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
    *///? } else {
    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
    //?}
        if (stack.is(ItemTags.HOES)) {
            world.playSound((Entity)player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0f, 1.0f);
            if (!world.isClientSide()) {
                if (player != null) {
                    //? if >= 26.2 {
                    /*stack.hurtAndBreak(1, player, hand);
                     *///?} else {
                    stack.hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND
                            ? net.minecraft.world.entity.EquipmentSlot.MAINHAND
                            : net.minecraft.world.entity.EquipmentSlot.OFFHAND);
                    //?}
                }
            }
            world.setBlockAndUpdate(pos, MoonBlocks.MOON_FARMLAND.get().defaultBlockState());
            //? if >= 26.2 {
            /*return InteractionResult.SUCCESS;
             *///?} else {
            return ItemInteractionResult.SUCCESS;
            //?}
        }
        //? if >= 26.2 {
        /*return InteractionResult.PASS;
         *///?} else {
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        //?}
    }

    private static boolean canStayAlive(final BlockState state, final LevelReader level, final BlockPos pos) {
        BlockPos above = pos.above();
        BlockState aboveState = level.getBlockState(above);
        if (aboveState.is(Blocks.SNOW) && aboveState.getValue(SnowLayerBlock.LAYERS) == 1) {
            return true;
        }

        //? if >= 26.2 {
        /*if (aboveState.getFluidState().isFull()) {
         *///?} else {
        if (aboveState.getFluidState().getAmount() == 8) {
        //?}
            return false;
        }

        //? if >= 26.2 {
        /*int lightDampeningTopFace = LightEngine.getLightDampeningInto(state, aboveState, Direction.UP, aboveState.getLightDampening());
         *///?} else {
        int lightDampeningTopFace = LightEngine.getLightBlockInto(level, state, pos, aboveState, above, Direction.UP, aboveState.getLightBlock(level, above));
        //?}
        return lightDampeningTopFace < 15;

    }

    public static boolean canPropagate(final BlockState state, final LevelReader level, final BlockPos pos) {
        BlockPos above = pos.above();
        return canStayAlive(state, level, pos) && !level.getFluidState(above).is(FluidTags.WATER);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (world.getMaxLocalRawBrightness(pos.above()) >= 9) {
            BlockState blockState = this.defaultBlockState();
            for (int i = 0; i < 4; ++i) {
                BlockPos blockPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if (!world.getBlockState(blockPos).is(MoonBlocks.MOON_ROCK.get()) || !canPropagate(state, world, blockPos)) continue;
                world.setBlockAndUpdate(blockPos, (BlockState)blockState);
            }
        }
        //? if >= 26.2 {
        /*if (!world.getBlockState(pos.above()).propagatesSkylightDown()) {
         *///?} else {
        if (!world.getBlockState(pos.above()).propagatesSkylightDown(world, pos.above())) {
        //?}
            world.setBlockAndUpdate(pos, MoonBlocks.MOON_ROCK.get().defaultBlockState());
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state));
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return world.getBlockState(pos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos.above();
        BlockState blockState = MoonBlocks.MOON_GRASS.get().defaultBlockState();
        Optional<Holder.Reference<PlacedFeature>> optional = world.registryAccess().lookupOrThrow(Registries.PLACED_FEATURE).get(ResourceKey.create(Registries.PLACED_FEATURE,ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "moon_grass_bone")));
        block0: for (int i = 0; i < 128; ++i) {
            BonemealableBlock fertilizable;
            BlockPos blockPos2 = blockPos;
            for (int j = 0; j < i / 16; ++j) {
                if (!world.getBlockState((blockPos2 = blockPos2.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1)).below()).is(this) || world.getBlockState(blockPos2).isCollisionShapeFullBlock(world, blockPos2)) continue block0;
            }
            BlockState blockState2 = world.getBlockState(blockPos2);
            if (blockState2.is(blockState.getBlock()) && random.nextInt(10) == 0 && (fertilizable = (BonemealableBlock)((Object)blockState.getBlock())).isValidBonemealTarget(world, blockPos2, blockState2)) {
                fertilizable.performBonemeal(world, random, blockPos2, blockState2);
            }
            if (!blockState2.isAir()) continue;
            if (random.nextInt(8) == 0) {
                //? if >= 26.2 {
                /*List<ConfiguredFeature<?, ?>> list = world.getBiome(blockPos2).value().getGenerationSettings().getBoneMealFeatures();
                 *///?} else {
                List<ConfiguredFeature<?, ?>> list = world.getBiome(blockPos2).value().getGenerationSettings().getFlowerFeatures();
                //?}
                if (list.isEmpty()) continue;
                ConfiguredFeature<?, ?> configuredFeature = list.get(random.nextInt(list.size()));
                configuredFeature.place(world, world.getChunkSource().getGenerator(), random, blockPos2);
            } else {
                if (optional.isEmpty()) continue;
                optional.get().value().place(world, world.getChunkSource().getGenerator(), random, blockPos2);
            }
        }
    }

    @Override
    public Type getType() {
        return Type.NEIGHBOR_SPREADER;
    }}
