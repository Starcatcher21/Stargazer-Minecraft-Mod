package com.github.starcatcher21.stargazer.worldgen.features.trees.moon;

import com.github.starcatcher21.stargazer.block.clases.moon.geode_fruit.GeodeFruit;
import com.github.starcatcher21.stargazer.block.clases.moon.geode_fruit.GeodeFruitStage;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.DirectionalTree;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import com.github.starcatcher21.stargazer.worldgen.features.trees.TreeConfig;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.ArrayList;
import java.util.List;

public class MoonTrees extends Feature<TreeConfig> {
    public static final ImmutableList<Direction> GROW_DIRECTIONS = ImmutableList.of(
            Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST
    );
    public static ArrayList<Tree> TREELIST = new ArrayList<>();
    public static Tree SmallMoon = register("SmallMoon");
    public static Tree HiMoon = register("HiMoon");
    public static Tree DoubleMoon = register("DoubleMoon");

    public MoonTrees(Codec<TreeConfig> codec) {
        super(codec);
    }

    public static Tree register(String name) {
        Tree tree = new Tree(true, name, MoonBlocks.MOON_LOG.getDefaultState().with(Properties.AXIS, Direction.Axis.Y), MoonBlocks.MOON_LEAVES.getDefaultState());
        tree.addFruit(MoonBlocks.GEODE_FRUIT.getDefaultState().with(GeodeFruit.STAGE, GeodeFruitStage.grown).with(GeodeFruit.FACING, Direction.NORTH));
        tree.addFruit(MoonBlocks.GEODE_FRUIT.getDefaultState().with(GeodeFruit.STAGE, GeodeFruitStage.grown).with(GeodeFruit.FACING, Direction.SOUTH));
        tree.addFruit(MoonBlocks.GEODE_FRUIT.getDefaultState().with(GeodeFruit.STAGE, GeodeFruitStage.grown).with(GeodeFruit.FACING, Direction.WEST));
        tree.addFruit(MoonBlocks.GEODE_FRUIT.getDefaultState().with(GeodeFruit.STAGE, GeodeFruitStage.grown).with(GeodeFruit.FACING, Direction.EAST));
        tree.setFruitChange(12);
        TREELIST.add(tree);
        return tree;
    }
    public static void init() {
        MoonBase.leavesInit();
        MoonTree1.init(SmallMoon);
        MoonTree2.init(HiMoon);
        MoonTree3.init(DoubleMoon);
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
