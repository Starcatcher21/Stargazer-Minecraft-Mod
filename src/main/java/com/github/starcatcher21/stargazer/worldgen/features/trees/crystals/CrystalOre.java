package com.github.starcatcher21.stargazer.worldgen.features.trees.crystals;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.starlib.worldgen.features.trees.Tree;
import com.github.starcatcher21.starlib.worldgen.features.trees.TreeConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrystalOre extends Feature<TreeConfig> {
    public CrystalOre(Codec<TreeConfig> configCodec) {
        super(configCodec);
    }

    public static ArrayList<Tree> TREELIST = new ArrayList<>();
    public static Tree CRYSTAL = register("CRYSTAL");
    public static Tree CRYSTAL2 = register("CRYSTAL2");

    public static Tree register(String name) {
        Tree tree = new Tree(false, name, MoonBlocks.MOON_ROCK_CRYSTALS.defaultBlockState(), Blocks.OBSIDIAN.defaultBlockState());
        TREELIST.add(tree);
        return tree;
    }

    public static void init() {
        crystal1.init(CRYSTAL);
        crystal2.init(CRYSTAL2);
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
        Random random = new Random();
        Tree tree = TREES.get(random.nextInt(TREES.size()));
        if (tree.canGrow(context.level(), pos)) {
            tree.Grow(context.level(), pos);
            if (context.level().getBlockState(pos.below(1)).getBlock().equals(MoonBlocks.MOON_ROCK_NYLIUM)) {
                this.setBlock(context.level(), pos.below(1), MoonBlocks.MOON_ROCK.defaultBlockState());
            }
            return true;
        }
        return false;
    }
}
