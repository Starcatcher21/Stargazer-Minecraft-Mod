package com.github.starcatcher21.stargazer.worldgen.features.trees.umbrella;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.RedOrbBlocks;
import com.github.starcatcher21.stargazer.block.register.Wander;
import com.github.starcatcher21.starlib.worldgen.features.trees.DirectionalTree;
import com.github.starcatcher21.starlib.worldgen.features.trees.Tree;
import com.github.starcatcher21.starlib.worldgen.features.trees.TreeConfig;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.ArrayList;
import java.util.List;

public class UmbrellaTrees extends Feature<TreeConfig> {
    public static final ImmutableList<Direction> GROW_DIRECTIONS = ImmutableList.of(
            Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST
    );
    public static ArrayList<Tree> TREELIST = new ArrayList<>();
    public static Tree umbrella;

    public UmbrellaTrees(Codec<TreeConfig> codec) {
        super(codec);
    }

    public static Tree register(String name) {
        Tree tree = new Tree(true, name, Wander.UMBRELLA_LOG.get().defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.X), Wander.UMBRELLA_LEAVES.get().defaultBlockState());
        TREELIST.add(tree);
        return tree;
    }
    public static void init() {
        Wander.UMBRELLA_LEAVES.listen(block -> {
            umbrella = register("umbrella");
            UmbrellaTree1.init(umbrella);
        });
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
                if (context.level().getBlockState(pos.below(1)).getBlock().equals(MoonBlocks.MOON_ROCK_NYLIUM.get())) {
                    this.setBlock(context.level(), pos.below(1), MoonBlocks.MOON_ROCK.get().defaultBlockState());
                }
                return true;
            }
        } else {
            if (tree.canGrow(context.level(), pos)) {
                tree.Grow(context.level(), pos);
                if (context.level().getBlockState(pos.below(1)).getBlock().equals(MoonBlocks.MOON_ROCK_NYLIUM.get())) {
                    this.setBlock(context.level(), pos.below(1), MoonBlocks.MOON_ROCK.get().defaultBlockState());
                }
                return true;
            }
        }
        return false;
    }
}
