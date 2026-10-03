package com.github.starcatcher21.stargazer.worldgen.features.crystals;

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
    public static Tree CRYSTAL;
    public static Tree CRYSTAL2;

    public static Tree register(String name) {
        Tree tree = new Tree(false, name, MoonBlocks.MOON_ROCK_CRYSTALS.get().defaultBlockState(), Blocks.OBSIDIAN.defaultBlockState());
        TREELIST.add(tree);
        return tree;
    }

    public static void init() {
        MoonBlocks.MOON_ROCK_CRYSTALS.listen(block -> {
            CRYSTAL = register("CRYSTAL");
            CRYSTAL2 = register("CRYSTAL2");
            crystal1.init(CRYSTAL);
            crystal2.init(CRYSTAL2);
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
        Random random = new Random();
        Tree tree = TREES.get(random.nextInt(TREES.size()));
        if (tree.canGrow(context.level(), pos)) {
            tree.Grow(context.level(), pos);
            return true;
        }
        return false;
    }
}
