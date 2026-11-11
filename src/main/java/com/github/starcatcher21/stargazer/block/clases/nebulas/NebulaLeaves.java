package com.github.starcatcher21.stargazer.block.clases.nebulas;

import com.github.starcatcher21.stargazer.particle.Particles;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

//? if >= 26.2 {
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
 //?} else {
/*import net.minecraft.world.level.block.LeavesBlock;
*///?}

//? if >= 26.2 {
public class NebulaLeaves extends TintedParticleLeavesBlock {
 //?} else {
/*public class NebulaLeaves extends LeavesBlock {
*///?}

    //? if >= 26.2 {
    public static final MapCodec<NebulaLeaves> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        Codec.INT.fieldOf("tint").forGetter(block -> block.tint),
        propertiesCodec()
    ).apply(instance, NebulaLeaves::new));

    protected int tint;

    public NebulaLeaves(int tin, BlockBehaviour.Properties settings) {
        super(0.01F, settings);
        tint = tin;
    }

    @Override
    public MapCodec<? extends TintedParticleLeavesBlock> codec() {
        return CODEC;
    }
    //?} else {
    /*public static final MapCodec<NebulaLeaves> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("tint").forGetter(block -> block.tint)
    ).apply(instance, tint -> new NebulaLeaves(tint, BlockBehaviour.Properties.of())));

    protected int tint;

    public NebulaLeaves(int tin, BlockBehaviour.Properties settings) {
        super(settings);
        tint = tin;
    }

    @Override
    public MapCodec<? extends LeavesBlock> codec() {
        return CODEC;
    }
    *///?}

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    //? if >= 26.2 {
    @Override
    protected void spawnFallingLeavesParticle(Level world, BlockPos pos, RandomSource random) {
        ColorParticleOption tintedParticleEffect =
            ColorParticleOption.create(Particles.TINTED_STAR.get(), tint);
        ParticleUtils.spawnParticleBelow(world, pos, random, tintedParticleEffect);
    }
    //?} else {
    /*@Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        super.animateTick(state, world, pos, random);
        if (random.nextInt(20) == 0) {
            ColorParticleOption particle =
                    ColorParticleOption.create(Particles.TINTED_STAR.get(), tint);
            ParticleUtils.spawnParticleBelow(world, pos, random, particle);
        }
    }
    *///?}
}
