package com.github.starcatcher21.stargazer.worldgen.features.trees.prismaticore;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.starlib.worldgen.features.trees.Tree;
import com.github.starcatcher21.starlib.worldgen.features.trees.TreeConfig;
import com.mojang.serialization.Codec;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class PrismaticOre extends Feature<TreeConfig> {
    public PrismaticOre(Codec<TreeConfig> configCodec) {
        super(configCodec);
    }

    public static ArrayList<Tree> TREELIST = new ArrayList<>();
    public static Tree PRISMSTARDEBRIE = register("PRISM_STAR_DEBRIE");
    public static Tree PRISMSTARDEMERALD = register("PRISM_STAR_EMERALD");
    public static Tree PRISMSTARDEMERALD2 = register("PRISM_STAR_EMERALD");
    public static Tree PRISMSTARDLAPIS = register("PRISM_STAR_LAPIS");
    public static Tree PRISMSTARDLAPIS2 = register("PRISM_STAR_LAPIS");
    public static Tree PRISMSTARDIRON = register("PRISM_STAR_IRON");
    public static Tree PRISMSTARDIRON2 = register("PRISM_STAR_IRON");
    public static Tree PRISMSTARROCK = register("PRISM_STAR_ROCK");
    public static Tree PRISMSTARROCK2 = register("PRISM_STAR_ROCK");

    public static Tree register(String name) {
        Tree tree = new Tree(false, name, MoonBlocks.PRISMATIC_ORE.defaultBlockState(), Blocks.OBSIDIAN.defaultBlockState());
        TREELIST.add(tree);
        return tree;
    }

    public static void init() {
        Prismatic1.init(PRISMSTARDEBRIE, Blocks.ANCIENT_DEBRIS.defaultBlockState());
        Prismatic1.init(PRISMSTARDEMERALD, Blocks.EMERALD_BLOCK.defaultBlockState());
        Prismatic1.init(PRISMSTARDEMERALD2, Blocks.EMERALD_BLOCK.defaultBlockState());
        Prismatic1.init(PRISMSTARDIRON, Blocks.IRON_BLOCK.defaultBlockState());
        Prismatic1.init(PRISMSTARDIRON2, Blocks.IRON_BLOCK.defaultBlockState());
        Prismatic1.init(PRISMSTARDLAPIS, Blocks.LAPIS_BLOCK.defaultBlockState());
        Prismatic1.init(PRISMSTARDLAPIS2, Blocks.LAPIS_BLOCK.defaultBlockState());
        Prismatic1.init(PRISMSTARROCK, MoonBlocks.MOON_ROCK.defaultBlockState());
        Prismatic1.init(PRISMSTARROCK2, MoonBlocks.MOON_ROCK.defaultBlockState());
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
