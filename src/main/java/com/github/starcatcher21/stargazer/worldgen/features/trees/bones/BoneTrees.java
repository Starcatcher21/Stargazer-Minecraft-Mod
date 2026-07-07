package com.github.starcatcher21.stargazer.worldgen.features.trees.bones;

import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class BoneTrees extends Feature<TreeConfig> {
    public static final ImmutableList<Direction> GROW_DIRECTIONS = ImmutableList.of(
            Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST
    );

    public static ArrayList<Tree> TREELIST = new ArrayList<>();
    public static Tree Cinema = register("CinemaTree");
    public static Tree NotCinema = register("NotCinemaTree");
    public static Tree BALD1 = register("Bald1");
    public static Tree BALD2 = register("Bald2");
    public static Tree BALD3 = register("Bald3");
    public static Tree PCROWN = register("PCrown");
    public static Tree ECROWN = register("ECrown");
    public static Tree ICROWN = register("ICrown");

    public BoneTrees(Codec<TreeConfig> configCodec) {
        super(configCodec);
    }

    public static Tree register(String name) {
        Tree tree = new Tree(true, name, Blocks.BONE_BLOCK.defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Y), ModBlock.BONE_LEAVES.defaultBlockState());
        TREELIST.add(tree);
        return tree;
    }

    public static void init() {
        CinemaTree.CinemaInit(Cinema);
        NotCinemaTree.CinemaInit(NotCinema);
        bald1.CinemaInit(BALD1);
        bald2.CinemaInit(BALD2);
        bald3.CinemaInit(BALD3);
        crown.CinemaInit(PCROWN, MoonBlocks.PRISMATIC_SHARD_BLOCK);
        crown.CinemaInit(ECROWN, Blocks.EMERALD_BLOCK);
        crown.CinemaInit(ICROWN, Blocks.IRON_BLOCK);
        TREELIST.add(Cinema);
        TREELIST.add(Cinema);
        TREELIST.add(Cinema);
        TREELIST.add(NotCinema);
        TREELIST.add(NotCinema);
        TREELIST.add(NotCinema);
        TREELIST.add(BALD1);
        TREELIST.add(BALD2);
        TREELIST.add(BALD3);
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
