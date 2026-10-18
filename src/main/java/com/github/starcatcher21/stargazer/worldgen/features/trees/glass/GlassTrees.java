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
import net.minecraft.util.RandomSource;
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
    public static Tree TRANS;
    public static Tree WHITE;
    public static Tree ORANGE;
    public static Tree MAGENTA;
    public static Tree LIGHT_BLUE;
    public static Tree YELLOW;
    public static Tree LIME;
    public static Tree PINK;
    public static Tree GRAY;
    public static Tree LIGHT_GRAY;
    public static Tree CYAN;
    public static Tree PURPLE;
    public static Tree BLUE;
    public static Tree BROWN;
    public static Tree GREEN;
    public static Tree RED;
    public static Tree BLACK;

    public GlassTrees(Codec<TreeConfig> codec) {
        super(codec);
    }

    public static Tree register(String name, BlockState glass) {
        Tree tree = new Tree(true, name,
                RedOrbBlocks.GLASS_LOG.get().defaultBlockState()
                        .setValue(BlockStateProperties.AXIS, Direction.Axis.Y),
                glass);
        GlassTree1.init(tree);
        TREELIST.add(tree);
        return tree;
    }

    public static void init() {
        RedOrbBlocks.GLASS_LOG.listen(block -> {
            TRANS = register("trans", Blocks.GLASS.defaultBlockState());

            //? if >= 26.2 {
            /*WHITE      = register("white",      Blocks.STAINED_GLASS.white().defaultBlockState());
            ORANGE     = register("orange",     Blocks.STAINED_GLASS.orange().defaultBlockState());
            MAGENTA    = register("magenta",    Blocks.STAINED_GLASS.magenta().defaultBlockState());
            LIGHT_BLUE = register("light_blue", Blocks.STAINED_GLASS.lightBlue().defaultBlockState());
            YELLOW     = register("yellow",     Blocks.STAINED_GLASS.yellow().defaultBlockState());
            LIME       = register("lime",       Blocks.STAINED_GLASS.lime().defaultBlockState());
            PINK       = register("pink",       Blocks.STAINED_GLASS.pink().defaultBlockState());
            GRAY       = register("gray",       Blocks.STAINED_GLASS.gray().defaultBlockState());
            LIGHT_GRAY = register("light_gray", Blocks.STAINED_GLASS.lightGray().defaultBlockState());
            CYAN       = register("cyan",       Blocks.STAINED_GLASS.cyan().defaultBlockState());
            PURPLE     = register("purple",     Blocks.STAINED_GLASS.purple().defaultBlockState());
            BLUE       = register("blue",       Blocks.STAINED_GLASS.blue().defaultBlockState());
            BROWN      = register("brown",      Blocks.STAINED_GLASS.brown().defaultBlockState());
            GREEN      = register("green",      Blocks.STAINED_GLASS.green().defaultBlockState());
            RED        = register("red",        Blocks.STAINED_GLASS.red().defaultBlockState());
            BLACK      = register("black",      Blocks.STAINED_GLASS.black().defaultBlockState());
            *///?} else {
            WHITE      = register("white",      Blocks.WHITE_STAINED_GLASS.defaultBlockState());
            ORANGE     = register("orange",     Blocks.ORANGE_STAINED_GLASS.defaultBlockState());
            MAGENTA    = register("magenta",    Blocks.MAGENTA_STAINED_GLASS.defaultBlockState());
            LIGHT_BLUE = register("light_blue", Blocks.LIGHT_BLUE_STAINED_GLASS.defaultBlockState());
            YELLOW     = register("yellow",     Blocks.YELLOW_STAINED_GLASS.defaultBlockState());
            LIME       = register("lime",       Blocks.LIME_STAINED_GLASS.defaultBlockState());
            PINK       = register("pink",       Blocks.PINK_STAINED_GLASS.defaultBlockState());
            GRAY       = register("gray",       Blocks.GRAY_STAINED_GLASS.defaultBlockState());
            LIGHT_GRAY = register("light_gray", Blocks.LIGHT_GRAY_STAINED_GLASS.defaultBlockState());
            CYAN       = register("cyan",       Blocks.CYAN_STAINED_GLASS.defaultBlockState());
            PURPLE     = register("purple",     Blocks.PURPLE_STAINED_GLASS.defaultBlockState());
            BLUE       = register("blue",       Blocks.BLUE_STAINED_GLASS.defaultBlockState());
            BROWN      = register("brown",      Blocks.BROWN_STAINED_GLASS.defaultBlockState());
            GREEN      = register("green",      Blocks.GREEN_STAINED_GLASS.defaultBlockState());
            RED        = register("red",        Blocks.RED_STAINED_GLASS.defaultBlockState());
            BLACK      = register("black",      Blocks.BLACK_STAINED_GLASS.defaultBlockState());
            //?}
        });
    }

    @Override
    public boolean place(FeaturePlaceContext<TreeConfig> context) {
        TreeConfig config = context.config();
        boolean chunks = !context.level().hasNearbyAlivePlayer(
                context.origin().getX(), context.origin().getY(), context.origin().getZ(), 100);
        List<Block> growOn = config.growOn.stream()
                .map(BlockBehaviour.BlockStateBase::getBlock).toList();
        if (!growOn.contains(context.level().getBlockState(context.origin().below(1)).getBlock())
                && chunks) {
            return false;
        }

        List<String> allowed = config.NAMES;
        List<Tree> TREES;
        if (config.BLACKLIST) {
            TREES = TREELIST.stream().filter(name -> !allowed.contains(name.name)).toList();
        } else {
            TREES = TREELIST.stream().filter(name -> allowed.contains(name.name)).toList();
        }

        if (TREES.isEmpty()) {
            return false;
        }

        BlockPos pos = context.origin();
        RandomSource random = context.random();
        Tree tree = TREES.get(random.nextInt(TREES.size()));
        if (tree.ROTATO) {
            Direction dir = GROW_DIRECTIONS.get(random.nextInt(GROW_DIRECTIONS.size()));
            Tree rotated = DirectionalTree.getFromNorth(tree, dir);
            if (rotated.canGrow(context.level(), pos)) {
                rotated.Grow(context.level(), pos);
                if (context.level().getBlockState(pos.below(1)).getBlock()
                        .equals(MoonBlocks.MOON_ROCK_NYLIUM.get())) {
                    this.setBlock(context.level(), pos.below(1),
                            MoonBlocks.MOON_ROCK.get().defaultBlockState());
                }
                return true;
            }
        } else {
            if (tree.canGrow(context.level(), pos)) {
                tree.Grow(context.level(), pos);
                if (context.level().getBlockState(pos.below(1)).getBlock()
                        .equals(MoonBlocks.MOON_ROCK_NYLIUM.get())) {
                    this.setBlock(context.level(), pos.below(1),
                            MoonBlocks.MOON_ROCK.get().defaultBlockState());
                }
                return true;
            }
        }
        return false;
    }
}
