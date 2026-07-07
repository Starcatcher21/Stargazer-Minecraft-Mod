package com.github.starcatcher21.stargazer.worldgen.features.trees.purple_shroom;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
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
import java.util.Random;

public class PurpleShrooms extends Feature<TreeConfig> {
    public static final ImmutableList<Direction> GROW_DIRECTIONS = ImmutableList.of(
            Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST
    );

    public static ArrayList<Tree> TREELIST = new ArrayList<>();
    public static Tree PURPLE = register("PURPLE_SHROOM");

    public PurpleShrooms(Codec<TreeConfig> codec) {
        super(codec);
    }

    public static Tree register(String name) {
        Tree tree = new Tree(true, name, Blocks.MUSHROOM_STEM.getDefaultState(), MoonBlocks.PURPLE_MUSHROOM_BLOCK.getDefaultState());
        TREELIST.add(tree);
        return tree;
    }
    public static void init() {
        PurpleBase.purpleInit();
        Purple1.init(PURPLE);
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
