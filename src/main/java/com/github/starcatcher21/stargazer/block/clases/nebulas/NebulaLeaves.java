package com.github.starcatcher21.stargazer.block.clases.nebulas;

import com.github.starcatcher21.stargazer.particle.Particles;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.TintedParticleLeavesBlock;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.particle.TintedParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NebulaLeaves extends TintedParticleLeavesBlock {
    protected int tint;
    public NebulaLeaves(int tin, Settings settings) {
        super(0.01F, settings);
        tint = tin;
    }

    @Override
    public BlockState getAppearance(BlockState state, BlockRenderView renderView, BlockPos pos, Direction side, @Nullable BlockState sourceState, @Nullable BlockPos sourcePos) {
        return super.getAppearance(state, renderView, pos, side, sourceState, sourcePos);
    }

    @Override
    public MapCodec<? extends TintedParticleLeavesBlock> getCodec() {
        return null;
    }

    @Override
    protected void spawnLeafParticle(World world, BlockPos pos, Random random) {
        TintedParticleEffect tintedParticleEffect = TintedParticleEffect.create(Particles.TINTED_STAR, tint);
        ParticleUtil.spawnParticle(world, pos, random, tintedParticleEffect);
    }
}
