package com.github.starcatcher21.stargazer.worldgen.features.trees.bubbles;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.RedOrbBlocks;
import com.github.starcatcher21.stargazer.entity.Star;
import com.github.starcatcher21.stargazer.worldgen.features.trees.DirectionalTree;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import com.github.starcatcher21.stargazer.worldgen.features.trees.TreeConfig;
import com.github.starcatcher21.stargazer.worldgen.features.trees.green_rocks.*;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import dev.architectury.platform.Mod;
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

public class Bubbles extends Feature<TreeConfig> {
    public static final ImmutableList<Direction> GROW_DIRECTIONS = ImmutableList.of(
            Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST
    );
    public static ArrayList<Tree> TREELIST = new ArrayList<>();
    public static Tree R3 = register("R3");
    public static Tree G3 = register("G3");
    public static Tree B3 = register("B3");
    public static Tree R2 = register("R2");
    public static Tree G2 = register("G2");
    public static Tree B2 = register("B2");
    public static Tree I3 = register("I3");
    public static Tree I2 = register("I2");

    public Bubbles(Codec<TreeConfig> codec) {
        super(codec);
    }

    public static Tree register(String name) {
        Tree tree = new Tree(true, name, Blocks.AIR.getDefaultState(), ModBlock.NORED_BLOCK.getDefaultState());
        TREELIST.add(tree);
        return tree;
    }
    public static void init() {
        G3.clearLeave();
        B3.clearLeave();
        G2.clearLeave();
        B2.clearLeave();
        I2.clearLeave();
        I3.clearLeave();
        G3.addLeave(ModBlock.NOGREEN_BLOCK.getDefaultState());
        G2.addLeave(ModBlock.NOGREEN_BLOCK.getDefaultState());
        B3.addLeave(ModBlock.NOBLUE_BLOCK.getDefaultState());
        B2.addLeave(ModBlock.NOBLUE_BLOCK.getDefaultState());
        I3.addLeave(ModBlock.NEGATIVE_BLOCK.getDefaultState());
        I2.addLeave(ModBlock.NEGATIVE_BLOCK.getDefaultState());
        txt.init(R3);
        txt.init(G3);
        txt.init(B3);
        txt.init(I3);
        dxd.init(R2);
        dxd.init(G2);
        dxd.init(B2);
        dxd.init(I2);
    }

    @Override
    public boolean generate(FeatureContext<TreeConfig> context) {
        TreeConfig config = context.getConfig();
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
        if (tree.canGrow(context.getWorld(), pos)) {
            tree.Grow(context.getWorld(), pos);
            return true;
        }
        return false;
    }
}
