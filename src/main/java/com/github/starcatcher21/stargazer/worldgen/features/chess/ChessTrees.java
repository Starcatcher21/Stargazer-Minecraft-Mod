package com.github.starcatcher21.stargazer.worldgen.features.chess;

import com.github.starcatcher21.stargazer.block.register.Chess;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.DirectionalTree;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import com.github.starcatcher21.stargazer.worldgen.features.trees.TreeConfig;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.ArrayList;
import java.util.List;

public class ChessTrees extends Feature<TreeConfig> {
    public static final ImmutableList<Direction> GROW_DIRECTIONS = ImmutableList.of(
            Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST
    );
    public static ArrayList<Tree> TREELIST = new ArrayList<>();
    public static Tree WPAWN = register("wpawn");
    public static Tree BPAWN = registerBlack("bpawn");
    public static Tree WROOK = register("wrook");
    public static Tree BROOK = registerBlack("brook");
    public static Tree WBISHOP = register("wbishop");
    public static Tree BBISHOP = registerBlack("bbishop");
    public static Tree WKING = register("wking");
    public static Tree BKING = registerBlack("bking");
    public static Tree WQUEEN = register("wqueen");
    public static Tree BQUEEN = registerBlack("bqueen");
    public static Tree WHORSE= register("whorse");
    public static Tree BHORSE = registerBlack("bhorse");

    public ChessTrees(Codec<TreeConfig> codec) {
        super(codec);
    }

    public static Tree register(String name) {
        Tree tree = new Tree(false, name, Chess.WHITE_CHESSBOARD.getDefaultState(), Chess.WHITE_CHESSBOARD.getDefaultState());
        TREELIST.add(tree);
        return tree;
    }

    public static Tree registerBlack(String name) {
        Tree tree = new Tree(false, name, Chess.BLACK_CHESSBOARD.getDefaultState(), Chess.BLACK_CHESSBOARD.getDefaultState());
        TREELIST.add(tree);
        return tree;
    }
    public static void init() {
        Pawn.init(WPAWN);
        Pawn.init(BPAWN);
        Rook.init(WROOK);
        Pawn.init(BROOK);
        Bishop.init(WBISHOP);
        Bishop.init(BBISHOP);
        King.init(WKING);
        King.init(BKING);
        Queen.init(WQUEEN);
        Queen.init(BQUEEN);
        Horse.init(WHORSE);
        Horse.init(BHORSE);
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
