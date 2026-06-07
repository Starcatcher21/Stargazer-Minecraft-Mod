package com.github.starcatcher21.stargazer.worldgen.features.trees.green_rocks;

import com.github.starcatcher21.stargazer.block.register.RedOrbBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.DirectionalTree;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import com.github.starcatcher21.stargazer.worldgen.features.trees.TreeConfig;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.ArrayList;
import java.util.List;

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
        Tree tree = new Tree(true, name, RedOrbBlocks.GREEN_ROCK.getDefaultState(), Blocks.AIR.getDefaultState());
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
        java.util.Random random = new java.util.Random();
        Tree tree = TREES.get(random.nextInt(TREES.size()));
        if (tree.ROTATO) {
            Direction dir = GROW_DIRECTIONS.get(random.nextInt(GROW_DIRECTIONS.size()));
            Tree rotated = DirectionalTree.getFromNorth(tree, dir);
            if (rotated.canGrow(context.getWorld(), pos)) {
                rotated.Grow(context.getWorld(), pos);
                return true;
            }
        } else {
            if (tree.canGrow(context.getWorld(), pos)) {
                tree.Grow(context.getWorld(), pos);
                return true;
            }
        }
        return false;
    }
}
