package com.github.starcatcher21.stargazer.worldgen.features.trees.amertylst;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class AmertylstConfig implements FeatureConfig {
    public final List<BlockState> growOn;
    public final List<BlockState> mainBlock;
    public final int offset;
    public final int size;
    public static final Codec<AmertylstConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codecs.NON_NEGATIVE_INT.fieldOf("offset").forGetter(config -> config.offset),
            Codecs.NON_NEGATIVE_INT.fieldOf("size").forGetter(config -> config.size),
            Codecs.nonEmptyList(BlockState.CODEC.listOf()).fieldOf("grow_on").forGetter(config -> config.growOn),
            Codecs.nonEmptyList(BlockState.CODEC.listOf()).fieldOf("main_block").forGetter(config -> config.mainBlock)
    ).apply(instance, AmertylstConfig::new));

    public AmertylstConfig(int off, int size, List<BlockState> block1, List<BlockState> block2) {
        this.offset = off;
        this.size = size;
        this.growOn = block1;
        this.mainBlock = block2;
    }
}
