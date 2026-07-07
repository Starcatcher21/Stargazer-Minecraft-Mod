package com.github.starcatcher21.stargazer.worldgen.features.trees.prismaticore;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.worldgen.features.trees.Tree;
import com.github.starcatcher21.stargazer.worldgen.features.trees.TreeConfig;
import com.mojang.serialization.Codec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        Tree tree = new Tree(false, name, MoonBlocks.PRISMATIC_ORE.getDefaultState(), Blocks.OBSIDIAN.getDefaultState());
        TREELIST.add(tree);
        return tree;
    }

    public static void init() {
        Prismatic1.init(PRISMSTARDEBRIE, Blocks.ANCIENT_DEBRIS.getDefaultState());
        Prismatic1.init(PRISMSTARDEMERALD, Blocks.EMERALD_BLOCK.getDefaultState());
        Prismatic1.init(PRISMSTARDEMERALD2, Blocks.EMERALD_BLOCK.getDefaultState());
        Prismatic1.init(PRISMSTARDIRON, Blocks.IRON_BLOCK.getDefaultState());
        Prismatic1.init(PRISMSTARDIRON2, Blocks.IRON_BLOCK.getDefaultState());
        Prismatic1.init(PRISMSTARDLAPIS, Blocks.LAPIS_BLOCK.getDefaultState());
        Prismatic1.init(PRISMSTARDLAPIS2, Blocks.LAPIS_BLOCK.getDefaultState());
        Prismatic1.init(PRISMSTARROCK, MoonBlocks.MOON_ROCK.getDefaultState());
        Prismatic1.init(PRISMSTARROCK2, MoonBlocks.MOON_ROCK.getDefaultState());
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
        Random random = new Random();
        Tree tree = TREES.get(random.nextInt(TREES.size()));
        if (tree.canGrow(context.getWorld(), pos)) {
            tree.Grow(context.getWorld(), pos);
            if (context.getWorld().getBlockState(pos.down(1)).getBlock().equals(MoonBlocks.MOON_ROCK_NYLIUM)) {
                this.setBlockState(context.getWorld(), pos.down(1), MoonBlocks.MOON_ROCK.getDefaultState());
            }
            return true;
        }
        return false;
    }
}
