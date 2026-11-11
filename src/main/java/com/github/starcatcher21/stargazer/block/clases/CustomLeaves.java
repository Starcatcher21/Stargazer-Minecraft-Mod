package com.github.starcatcher21.stargazer.block.clases;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
//? if >= 26.2 {
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
 //?} else {
/*import net.minecraft.world.level.block.LeavesBlock;
*///?}

//? if >= 26.2 {
public class CustomLeaves extends TintedParticleLeavesBlock {
 //?} else {
/*public class CustomLeaves extends LeavesBlock {
*///?}

    //? if >= 26.2 {
    public static final MapCodec<CustomLeaves> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("tint").forGetter(block -> block.tint),
            propertiesCodec()
    ).apply(instance, CustomLeaves::new));

    protected int tint;

    public CustomLeaves(int tin, BlockBehaviour.Properties settings) {
        super(0.01F, settings);
        tint = tin;
    }

    @Override
    public MapCodec<? extends TintedParticleLeavesBlock> codec() {
        return CODEC;
    }

    @Override
    protected void spawnFallingLeavesParticle(Level world, BlockPos pos, RandomSource random) {
        // Particle logic intentionally disabled — see original file.
    }
    //?} else {
    /*// 1.21.1 has no TintedParticleLeavesBlock; extend LeavesBlock.
    // The codec reads only "tint" and uses default properties, which is correct
    // for a code-registered block.
    public static final MapCodec<CustomLeaves> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("tint").forGetter(block -> block.tint)
    ).apply(instance, tint -> new CustomLeaves(tint, BlockBehaviour.Properties.of())));

    protected int tint;

    public CustomLeaves(int tin, BlockBehaviour.Properties settings) {
        super(settings);
        tint = tin;
    }

    @Override
    public MapCodec<? extends LeavesBlock> codec() {
        return CODEC;
    }

    // LeavesBlock on 1.21.1 has no spawnFallingLeavesParticle hook.
    // Particle logic is intentionally disabled, so no override is needed.
    *///?}
}
