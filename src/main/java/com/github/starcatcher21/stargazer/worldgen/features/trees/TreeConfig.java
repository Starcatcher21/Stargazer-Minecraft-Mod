package com.github.starcatcher21.stargazer.worldgen.features.trees;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.List;

public class TreeConfig implements FeatureConfig {
    public final List<String> NAMES;
    public final List<BlockState> growOn;
    public final Boolean BLACKLIST;
    public static final Codec<TreeConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("blacklist").forGetter(config -> config.BLACKLIST),
            Codecs.NON_EMPTY_STRING.listOf().fieldOf("names").forGetter(config -> config.NAMES),
            Codecs.nonEmptyList(BlockState.CODEC.listOf(0, Integer.MAX_VALUE)).fieldOf("grow_on").forGetter(config -> config.growOn)
    ).apply(instance, TreeConfig::new));

    public TreeConfig(boolean blacklist, List<String> NAMES, List<BlockState> grow) {
        this.BLACKLIST = blacklist;
        this.NAMES = NAMES;
        this.growOn = grow;
    }
}
