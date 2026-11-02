package com.github.starcatcher21.stargazer.block.clases;

import com.github.starcatcher21.stargazer.block.ModBlock;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
//? if >= 26.2 {
/*import net.minecraft.references.BlockIds;
import net.minecraft.world.level.block.SpreadingSnowyBlock;
*///?} else {
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
//?}
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;
import net.minecraft.world.phys.BlockHitResult;

//? if >= 26.2 {
/*public class InfestedCalcite extends SpreadingSnowyBlock {
 *///?} else {
public class InfestedCalcite extends SpreadingSnowyDirtBlock {
    //?}
    public static final MapCodec<InfestedCalcite> CODEC = simpleCodec(InfestedCalcite::new);

    public InfestedCalcite(final Properties properties) {
        //? if >= 26.2 {
        /*super(properties, Blocks.CALCITE.builtInRegistryHolder().key());
         *///?} else {
        super(properties);
        //?}
    }

    @Override
    //? if >= 26.2 {
    /*protected MapCodec<? extends SpreadingSnowyBlock> codec() {
    *///? } else {
    protected MapCodec<? extends SpreadingSnowyDirtBlock> codec() {
    //? }
        return CODEC;
    }

    @Override
    protected void onProjectileHit(Level world, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (!world.isClientSide()) {
            BlockPos blockPos = hit.getBlockPos();
            world.playSound(null, blockPos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0f, 0.5f + world.getRandom().nextFloat() * 1.2f);
        }
    }

    private static boolean canBeGrass(BlockState state, LevelReader world, BlockPos pos) {
        BlockPos blockPos = pos.above();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.getFluidState().getAmount() == 8) {
            return false;
        }
        //? if >= 26.2 {
        /*int i = LightEngine.getLightDampeningInto(state, blockState, Direction.UP, blockState.getLightDampening());
        *///? } else {
        int i = LightEngine.getLightBlockInto(world, blockState, blockPos, blockState, blockPos, Direction.UP, blockState.getLightBlock(world, pos.above()));
        //? }
        return i < 15;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!canBeGrass(state, world, pos)) {
            world.setBlockAndUpdate(pos, Blocks.CALCITE.defaultBlockState());
        }
    }
}
