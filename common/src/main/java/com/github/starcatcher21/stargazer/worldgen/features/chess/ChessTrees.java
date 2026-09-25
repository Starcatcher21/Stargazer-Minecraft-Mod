package com.github.starcatcher21.stargazer.worldgen.features.chess;

import com.github.starcatcher21.stargazer.block.register.Chess;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.starlib.worldgen.features.trees.DirectionalTree;
import com.github.starcatcher21.starlib.worldgen.features.trees.Tree;
import com.github.starcatcher21.starlib.worldgen.features.trees.TreeConfig;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class ChessTrees extends Feature<TreeConfig> {
    public static final ImmutableList<Direction> GROW_DIRECTIONS = ImmutableList.of(
            Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST
    );
    public static ArrayList<Tree> TREELIST = new ArrayList<>();
    public static Tree WPAWN;
    public static Tree BPAWN;
    public static Tree WROOK;
    public static Tree BROOK;
    public static Tree WBISHOP;
    public static Tree BBISHOP;
    public static Tree WKING;
    public static Tree BKING;
    public static Tree WQUEEN;
    public static Tree BQUEEN;
    public static Tree WHORSE;
    public static Tree BHORSE;

    public ChessTrees(Codec<TreeConfig> codec) {
        super(codec);
    }

    public static Tree register(String name) {
        Tree tree = new Tree(false, name, Chess.WHITE_CHESSBOARD.get().defaultBlockState(), Chess.WHITE_CHESSBOARD.get().defaultBlockState());
        TREELIST.add(tree);
        return tree;
    }

    public static Tree registerBlack(String name) {
        Tree tree = new Tree(false, name, Chess.BLACK_CHESSBOARD.get().defaultBlockState(), Chess.BLACK_CHESSBOARD.get().defaultBlockState());
        TREELIST.add(tree);
        return tree;
    }
    public static void init() {
        Chess.BLACK_CHESSBOARD.listen(block -> {
            WPAWN = register("wpawn");
            BPAWN = registerBlack("bpawn");
            WROOK = register("wrook");
            BROOK = registerBlack("brook");
            WBISHOP = register("wbishop");
            BBISHOP = registerBlack("bbishop");
            WKING = register("wking");
            BKING = registerBlack("bking");
            WQUEEN = register("wqueen");
            BQUEEN = registerBlack("bqueen");
            WHORSE = register("whorse");
            BHORSE = registerBlack("bhorse");
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
