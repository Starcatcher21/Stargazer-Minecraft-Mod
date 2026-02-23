package com.github.starcatcher21.stargazer.worldgen.features.trees.curve;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.DirectionalTree;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import com.github.starcatcher21.stargazer.worldgen.features.trees.TreeConfig;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        Tree tree = new Tree(true, name, MoonBlocks.CURVE_LOG.getDefaultState().with(Properties.AXIS, Direction.Axis.Y), MoonBlocks.CURVE_LEAVES.getDefaultState());
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
    public boolean generate(FeatureContext<TreeConfig> context) {
        TreeConfig config = context.getConfig();
        boolean chunks = !context.getWorld().isPlayerInRange(context.getOrigin().getX(), context.getOrigin().getY(), context.getOrigin().getZ(), 100);
        List<Block> growOn = config.growOn.stream().map(AbstractBlock.AbstractBlockState::getBlock).toList();
        if (!growOn.contains(context.getWorld().getBlockState(context.getOrigin().down(1)).getBlock()) && chunks) {
            return false;
        }
        List<String> allowed = config.NAMES;
        List<Tree> TREES;
        if (config.BLACKLIST) {
            TREES = TREELIST.stream().filter(name -> !allowed.contains(name.name)).toList();
        } else {
            TREES = TREELIST.stream().filter(name -> allowed.contains(name.name)).toList();
        }
        BlockPos pos = context.getOrigin();
        Random random = new Random();
        Tree tree = TREES.get(random.nextInt(TREES.size()));
        if (tree.ROTATO) {
            Direction dir = GROW_DIRECTIONS.get(random.nextInt(GROW_DIRECTIONS.size()));
            Tree rotated = DirectionalTree.getFromNorth(tree, dir);
            if (rotated.canGrow(context.getWorld(), pos)) {
                rotated.Grow(context.getWorld(), pos);
                if (context.getWorld().getBlockState(pos.down(1)).getBlock().equals(MoonBlocks.MOON_ROCK_NYLIUM)) {
                    this.setBlockState(context.getWorld(), pos.down(1), MoonBlocks.MOON_ROCK.getDefaultState());
                }
                return true;
            }
        } else {
            if (tree.canGrow(context.getWorld(), pos)) {
                tree.Grow(context.getWorld(), pos);
                if (context.getWorld().getBlockState(pos.down(1)).getBlock().equals(MoonBlocks.MOON_ROCK_NYLIUM)) {
                    this.setBlockState(context.getWorld(), pos.down(1), MoonBlocks.MOON_ROCK.getDefaultState());
                }
                return true;
            }
        }
        return false;
    }
}
