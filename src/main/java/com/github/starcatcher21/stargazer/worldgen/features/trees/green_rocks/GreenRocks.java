package com.github.starcatcher21.stargazer.worldgen.features.trees.green_rocks;

import com.github.starcatcher21.stargazer.block.register.RedOrbBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.DirectionalTree;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import com.github.starcatcher21.stargazer.worldgen.features.trees.TreeConfig;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class GreenRocks extends Feature<TreeConfig> {
    public static final ImmutableList<Direction> GROW_DIRECTIONS = ImmutableList.of(
            Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST
    );
    public static ArrayList<Tree> TREELIST = new ArrayList<>();
    public static Tree ROCK1 = register("Rock1");
    public static Tree ROCK2 = register("Rock2");
    public static Tree ROCK3 = register("Rock3");
    public static Tree ROCK4 = register("Rock4");
    public static Tree ROCK5 = register("Rock5");

    public GreenRocks(Codec<TreeConfig> codec) {
        super(codec);
    }

    public static Tree register(String name) {
        Tree tree = new Tree(true, name, RedOrbBlocks.GREEN_ROCK.defaultBlockState(), Blocks.AIR.defaultBlockState());
        TREELIST.add(tree);
        return tree;
    }
    public static void init() {
        Rock1.init(ROCK1);
        Rock2.init(ROCK2);
        Rock3.init(ROCK3);
        Rock4.init(ROCK4);
        Rock5.init(ROCK5);
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
        java.util.Random random = new java.util.Random();
        Tree tree = TREES.get(random.nextInt(TREES.size()));
        if (tree.ROTATO) {
            Direction dir = GROW_DIRECTIONS.get(random.nextInt(GROW_DIRECTIONS.size()));
            Tree rotated = DirectionalTree.getFromNorth(tree, dir);
            if (rotated.canGrow(context.level(), pos)) {
                rotated.Grow(context.level(), pos);
                return true;
            }
        } else {
            if (tree.canGrow(context.level(), pos)) {
                tree.Grow(context.level(), pos);
                return true;
            }
        }
        return false;
    }
}
