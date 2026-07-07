package com.github.starcatcher21.stargazer.worldgen.features.trees.amertylst;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class AmertylstConfig implements FeatureConfiguration {
    public final List<BlockState> growOn;
    public final List<BlockState> mainBlock;
    public final int offset;
    public final int size;
    public static final Codec<AmertylstConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ExtraCodecs.NON_NEGATIVE_INT.fieldOf("offset").forGetter(config -> config.offset),
            ExtraCodecs.NON_NEGATIVE_INT.fieldOf("size").forGetter(config -> config.size),
            ExtraCodecs.nonEmptyList(BlockState.CODEC.listOf()).fieldOf("grow_on").forGetter(config -> config.growOn),
            ExtraCodecs.nonEmptyList(BlockState.CODEC.listOf()).fieldOf("main_block").forGetter(config -> config.mainBlock)
    ).apply(instance, AmertylstConfig::new));

    public AmertylstConfig(int off, int size, List<BlockState> block1, List<BlockState> block2) {
        this.offset = off;
        this.size = size;
        this.growOn = block1;
        this.mainBlock = block2;
    }
}
