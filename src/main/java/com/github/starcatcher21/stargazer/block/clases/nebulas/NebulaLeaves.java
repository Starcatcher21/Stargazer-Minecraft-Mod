package com.github.starcatcher21.stargazer.block.clases.nebulas;

import com.github.starcatcher21.stargazer.particle.Particles;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;

public class NebulaLeaves extends TintedParticleLeavesBlock {
    public static final MapCodec<NebulaLeaves> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("tint").forGetter(block -> block.tint),
            propertiesCodec()
    ).apply(instance, NebulaLeaves::new));

    protected int tint;
    public NebulaLeaves(int tin, Properties settings) {
        super(0.01F, settings);
        tint = tin;
    }

    @Override
    public MapCodec<? extends TintedParticleLeavesBlock> codec() {
        return CODEC;
    }

    @Override
    protected void spawnFallingLeavesParticle(Level world, BlockPos pos, RandomSource random) {
        ColorParticleOption tintedParticleEffect = ColorParticleOption.create(Particles.TINTED_STAR, tint);
        ParticleUtils.spawnParticleBelow(world, pos, random, tintedParticleEffect);
    }
}
