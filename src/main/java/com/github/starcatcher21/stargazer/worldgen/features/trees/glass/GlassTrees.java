package com.github.starcatcher21.stargazer.worldgen.features.trees.glass;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.RedOrbBlocks;
import com.github.starcatcher21.starlib.worldgen.features.trees.DirectionalTree;
import com.github.starcatcher21.starlib.worldgen.features.trees.Tree;
import com.github.starcatcher21.starlib.worldgen.features.trees.TreeConfig;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.ArrayList;
import java.util.List;

public class GlassTrees extends Feature<TreeConfig> {
    public static final ImmutableList<Direction> GROW_DIRECTIONS = ImmutableList.of(
            Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST
    );
    public static ArrayList<Tree> TREELIST = new ArrayList<>();
    public static Tree TRANS = register("trans", Blocks.GLASS.defaultBlockState());
    public static Tree WHITE = register("white", Blocks.STAINED_GLASS.white().defaultBlockState());
    public static Tree ORANGE = register("orange", Blocks.STAINED_GLASS.orange().defaultBlockState());
    public static Tree MAGENTA = register("magenta", Blocks.STAINED_GLASS.magenta().defaultBlockState());
    public static Tree LIGHT_BLUE = register("light_blue", Blocks.STAINED_GLASS.lightBlue().defaultBlockState());
    public static Tree YELLOW = register("yellow", Blocks.STAINED_GLASS.yellow().defaultBlockState());
    public static Tree LIME = register("lime", Blocks.STAINED_GLASS.lime().defaultBlockState());
    public static Tree PINK = register("pink", Blocks.STAINED_GLASS.pink().defaultBlockState());
    public static Tree GRAY = register("gray", Blocks.STAINED_GLASS.gray().defaultBlockState());
    public static Tree LIGHT_GRAY = register("light_gray", Blocks.STAINED_GLASS.lightGray().defaultBlockState());
    public static Tree CYAN = register("cyan", Blocks.STAINED_GLASS.cyan().defaultBlockState());
    public static Tree PURPLE = register("purple", Blocks.STAINED_GLASS.purple().defaultBlockState());
    public static Tree BLUE = register("blue", Blocks.STAINED_GLASS.blue().defaultBlockState());
    public static Tree BROWN = register("brown", Blocks.STAINED_GLASS.brown().defaultBlockState());
    public static Tree GREEN = register("green", Blocks.STAINED_GLASS.green().defaultBlockState());
    public static Tree RED = register("red", Blocks.STAINED_GLASS.red().defaultBlockState());
    public static Tree BLACK = register("black", Blocks.STAINED_GLASS.black().defaultBlockState());

    public GlassTrees(Codec<TreeConfig> codec) {
        super(codec);
    }

    public static Tree register(String name, BlockState glass) {
        Tree tree = new Tree(true, name, RedOrbBlocks.GLASS_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Y), glass);
        GlassTree1.init(tree);
        TREELIST.add(tree);
        return tree;
    }
    public static void init() {}

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
