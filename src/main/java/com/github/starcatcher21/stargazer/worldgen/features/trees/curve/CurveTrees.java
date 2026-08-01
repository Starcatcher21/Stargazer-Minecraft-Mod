package com.github.starcatcher21.stargazer.worldgen.features.trees.curve;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.starlib.worldgen.features.trees.DirectionalTree;
import com.github.starcatcher21.starlib.worldgen.features.trees.Tree;
import com.github.starcatcher21.starlib.worldgen.features.trees.TreeConfig;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class CurveTrees extends Feature<TreeConfig> {
    public static final ImmutableList<Direction> GROW_DIRECTIONS = ImmutableList.of(
            Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST
    );
    public static final ImmutableList<Direction> GROW_DIRECTIONS_UD = ImmutableList.of(
            Direction.UP, Direction.DOWN
    );

    public static ArrayList<Tree> TREELIST = new ArrayList<>();

    public CurveTrees(Codec<TreeConfig> configCodec) {
        super(configCodec);
    }

    public static Tree register(String name) {
        Tree tree = new Tree(true, name, MoonBlocks.CURVE_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Y), MoonBlocks.CURVE_LEAVES.defaultBlockState());
        TREELIST.add(tree);
        return tree;
    }
    public static void init() {
        CurveBase.CurveBaseInit();
        for (Direction dir : GROW_DIRECTIONS) {
            for (int i = 0; i < 1; i++) {
                for (Direction dir2 : GROW_DIRECTIONS_UD) {
                    for (int ntOff = 1; ntOff < 3; ntOff++) {
                        for (int j = 0; j < 1; j++) {
                            for (int endLR = 0; endLR < 1; endLR++) {
                                for (int endLR2 = 0; endLR2 < 1; endLR2++) {
                                    Tree tree = register("Basic" + dir.toString() + (i == 1) + dir2.toString() + ntOff + (j == 1) + (endLR == 1) + (endLR2 == 1));
                                    CurveTree.init(tree, dir, i == 1, dir2, ntOff, j == 1, endLR == 1, endLR2 == 1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean place(FeaturePlaceContext<TreeConfig> context) {
        TreeConfig config = context.config();
        boolean chunks = !context.level().hasNearbyAlivePlayer(context.origin().getX(), context.origin().getY(), context.origin().getZ(), 100);
        List<Block> growOn = config.growOn.stream().map(BlockBehaviour.BlockStateBase::getBlock).toList();
        if (!growOn.contains(context.level().getBlockState(context.origin().below(1)).getBlock()) && chunks) {
            return false;
        }
        List<String> allowed = config.NAMES;
        List<Tree> TREES;
        if (config.BLACKLIST) {
            TREES = TREELIST.stream().filter(name -> !allowed.contains(name.name)).toList();
        } else {
            TREES = TREELIST.stream().filter(name -> allowed.contains(name.name)).toList();
        }
        BlockPos pos = context.origin();
        Random random = new Random();
        Tree tree = TREES.get(random.nextInt(TREES.size()));
        if (tree.ROTATO) {
            Direction dir = GROW_DIRECTIONS.get(random.nextInt(GROW_DIRECTIONS.size()));
            Tree rotated = DirectionalTree.getFromNorth(tree, dir);
            if (rotated.canGrow(context.level(), pos)) {
                rotated.Grow(context.level(), pos);
                if (context.level().getBlockState(pos.below(1)).getBlock().equals(MoonBlocks.MOON_ROCK_NYLIUM)) {
                    this.setBlock(context.level(), pos.below(1), MoonBlocks.MOON_ROCK.defaultBlockState());
                }
                return true;
            }
        } else {
            if (tree.canGrow(context.level(), pos)) {
                tree.Grow(context.level(), pos);
                if (context.level().getBlockState(pos.below(1)).getBlock().equals(MoonBlocks.MOON_ROCK_NYLIUM)) {
                    this.setBlock(context.level(), pos.below(1), MoonBlocks.MOON_ROCK.defaultBlockState());
                }
                return true;
            }
        }
        return false;
    }
}
