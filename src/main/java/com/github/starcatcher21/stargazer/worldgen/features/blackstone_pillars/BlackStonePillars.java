package com.github.starcatcher21.stargazer.worldgen.features.blackstone_pillars;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.starlib.worldgen.features.trees.DirectionalTree;
import com.github.starcatcher21.starlib.worldgen.features.trees.Tree;
import com.github.starcatcher21.starlib.worldgen.features.trees.TreeConfig;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import java.util.ArrayList;
import java.util.List;

import com.mojang.serialization.JsonOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import com.github.starcatcher21.starlib.worldgen.features.trees.TreeConfig;
//? if < 26.3 {
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
//? }

//? if < 26.3 {
public class BlackStonePillars
        extends Feature<TreeConfig>
        //? } else {
/*public record BlackStonePillars(TreeConfig config) implements Feature
*///? }
{
    public static final ImmutableList<Direction> GROW_DIRECTIONS = ImmutableList.of(
            Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST
    );
    public static ArrayList<Tree> TREELIST = new ArrayList<>();
    public static Tree small = register("small");
    public static Tree medium = register("medium");
    public static Tree large = register("large");

    //? if < 26.3 {
    public BlackStonePillars(
            Codec<TreeConfig> codec
    ) {
        super(codec);
    }
    //? } else {
    /*public static final MapCodec<BlackStonePillars> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(TreeConfig.CODEC.fieldOf("config").forGetter(BlackStonePillars::config)).apply(i, BlackStonePillars::new));
    *///? }

    public static Tree register(String name) {
        Tree tree = new Tree(true, name, Blocks.BLACKSTONE.defaultBlockState(), Blocks.BLACKSTONE.defaultBlockState());
        TREELIST.add(tree);
        return tree;
    }
    public static void init() {
        BlackStonePillar1.init(small);
        BlackStonePillar2.init(medium);
        BlackStonePillar3.init(large);
    }

    @Override
    public boolean place(
            //? if < 26.3 {
            FeaturePlaceContext<TreeConfig> context
            //? } else {
            /*WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource randomSource, BlockPos blockPos
            *///? }
            ) {
        //? if < 26.3 {
        TreeConfig config = context.config();
        BlockPos blockPos = context.origin();
        var level = context.level();
        //? }
        boolean chunks = !level.hasNearbyAlivePlayer(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 100);
        List<Block> growOn = config.growOn.stream().map(BlockBehaviour.BlockStateBase::getBlock).toList();
        if (!growOn.contains(level.getBlockState(blockPos.below(1)).getBlock()) && chunks) {
            return false;
        }
        List<String> allowed = config.NAMES;
        List<Tree> TREES;
        if (config.BLACKLIST) {
            TREES = TREELIST.stream().filter(name -> !allowed.contains(name.name)).toList();
        } else {
            TREES = TREELIST.stream().filter(name -> allowed.contains(name.name)).toList();
        }
        java.util.Random random = new java.util.Random();
        Tree tree = TREES.get(random.nextInt(TREES.size()));
        if (tree.ROTATO) {
            Direction dir = GROW_DIRECTIONS.get(random.nextInt(GROW_DIRECTIONS.size()));
            Tree rotated = DirectionalTree.getFromNorth(tree, dir);
            if (rotated.canGrow(level, blockPos)) {
                rotated.Grow(level, blockPos);
                if (level.getBlockState(blockPos.below(1)).getBlock().equals(MoonBlocks.MOON_ROCK_NYLIUM.get())) {
                    this.setBlock(level, blockPos.below(1), MoonBlocks.MOON_ROCK.get().defaultBlockState());
                }
                return true;
            }
        } else {
            if (tree.canGrow(level, blockPos)) {
                tree.Grow(level, blockPos);
                if (level.getBlockState(blockPos.below(1)).getBlock().equals(MoonBlocks.MOON_ROCK_NYLIUM.get())) {
                    this.setBlock(level, blockPos.below(1), MoonBlocks.MOON_ROCK.get().defaultBlockState());
                }
                return true;
            }
        }
        return false;
    }

    //? if >= 26.3 {
    /*@Override
    public MapCodec<? extends Feature> codec() {
        return CODEC;
    }

    *///? }
}
