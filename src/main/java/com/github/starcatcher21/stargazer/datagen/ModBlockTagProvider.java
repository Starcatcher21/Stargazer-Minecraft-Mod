package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.EyeBloodBlocks;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // Wood
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(MoonBlocks.MOON_LOG)
                .add(MoonBlocks.MOON_PLANKS_DOOR)
                .add(MoonBlocks.STRIPPED_MOON_LOG)
                .add(MoonBlocks.CURVE_LOG)
                .add(MoonBlocks.STRIPPED_CURVE_LOG)
                .add(EyeBloodBlocks.EYE_LOG)
                .add(EyeBloodBlocks.STRIPPED_EYE_LOG)
                .add(MoonBlocks.CURVE_PLANKS)
                .add(MoonBlocks.CURVE_PLANKS_STAIRS)
                .add(MoonBlocks.CURVE_PLANKS_SLAB)
                .add(MoonBlocks.CURVE_PLANKS_BUTTON)
                .add(MoonBlocks.CURVE_PLANKS_FENCE)
                .add(MoonBlocks.CURVE_PLANKS_FENCE_GATE)
                .add(MoonBlocks.MOON_PLANKS)
                .add(MoonBlocks.RED_MOON_PLANKS)
                .add(MoonBlocks.BLUE_MOON_PLANKS)
                .add(MoonBlocks.PURPLE_MOON_PLANKS)
                .add(MoonBlocks.YELLOW_MOON_PLANKS)
                .add(MoonBlocks.MOON_PLANKS_SLAB)
                .add(MoonBlocks.RED_MOON_PLANKS_SLAB)
                .add(MoonBlocks.BLUE_MOON_PLANKS_SLAB)
                .add(MoonBlocks.PURPLE_MOON_PLANKS_SLAB)
                .add(MoonBlocks.YELLOW_MOON_PLANKS_SLAB)
                .add(MoonBlocks.MOON_PLANKS_STAIRS)
                .add(MoonBlocks.RED_MOON_PLANKS_STAIRS)
                .add(MoonBlocks.BLUE_MOON_PLANKS_STAIRS)
                .add(MoonBlocks.PURPLE_MOON_PLANKS_STAIRS)
                .add(MoonBlocks.YELLOW_MOON_PLANKS_STAIRS)
                .add(MoonBlocks.MOON_PLANKS_BUTTON)
                .add(MoonBlocks.RED_MOON_PLANKS_BUTTON)
                .add(MoonBlocks.BLUE_MOON_PLANKS_BUTTON)
                .add(MoonBlocks.PURPLE_MOON_PLANKS_BUTTON)
                .add(MoonBlocks.YELLOW_MOON_PLANKS_BUTTON)
                .add(MoonBlocks.BLUE_MOON_PLANKS_FENCE)
                .add(MoonBlocks.YELLOW_MOON_PLANKS_FENCE)
                .add(MoonBlocks.MOON_PLANKS_FENCE)
                .add(MoonBlocks.PURPLE_MOON_PLANKS_FENCE)
                .add(StarBlocks.STAR_PLANKS_FENCE)
                .add(MoonBlocks.RED_MOON_PLANKS_FENCE)
                .add(MoonBlocks.BLUE_MOON_PLANKS_FENCE_GATE)
                .add(MoonBlocks.MOON_PLANKS_FENCE_GATE)
                .add(MoonBlocks.PURPLE_MOON_PLANKS_FENCE_GATE)
                .add(MoonBlocks.RED_MOON_PLANKS_FENCE_GATE)
                .add(StarBlocks.STAR_PLANKS_FENCE_GATE)
                .add(MoonBlocks.YELLOW_MOON_PLANKS_FENCE_GATE)
                .add(StarBlocks.STAR_LOG)
                .add(StarBlocks.STAR_PLANKS)
                .add(StarBlocks.STAR_PLANKS_SLAB)
                .add(StarBlocks.STAR_PLANKS_STAIRS)
                .add(StarBlocks.STAR_PLANKS_BUTTON)
                .add(StarBlocks.STAR_PLANKS_FENCE)
                .add(StarBlocks.STAR_PLANKS_FENCE_GATE)
                .add(StarBlocks.STRIPPED_STAR_LOG);
//                .add(Bonsai.LIVING_BONSAI_LOG)
//                .add(Bonsai.BONSAI_LOG);
        getOrCreateTagBuilder(BlockTags.LOGS)
                .add(MoonBlocks.MOON_LOG)
                .add(MoonBlocks.CURVE_LOG)
                .add(MoonBlocks.STRIPPED_CURVE_LOG)
                .add(MoonBlocks.STRIPPED_MOON_LOG)
                .add(EyeBloodBlocks.EYE_LOG)
                .add(EyeBloodBlocks.STRIPPED_EYE_LOG)
                .add(StarBlocks.STAR_LOG)
                .add(StarBlocks.STRIPPED_STAR_LOG);
//                .add(Bonsai.LIVING_BONSAI_LOG)
//                .add(Bonsai.BONSAI_LOG);
        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(MoonBlocks.MOON_LEAVES)
                .add(MoonBlocks.CURVE_LEAVES)
                .add(EyeBloodBlocks.EYE_LEAVES)
                .add(StarBlocks.STAR_LEAVES);
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(MoonBlocks.MOON_LEAVES)
                .add(ModBlock.BONE_LEAVES)
                .add(MoonBlocks.CURVE_LEAVES)
                .add(EyeBloodBlocks.EYE_LEAVES)
                .add(StarBlocks.STAR_LEAVES)
                .add(MoonBlocks.PURPLE_MUSHROOM_BLOCK);
        // Rock
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(MoonBlocks.STAR_FORGE)
                .add(MoonBlocks.MOON_ROCK)
                .add(MoonBlocks.POLISHED_MOON_ROCK)
                .add(MoonBlocks.MOON_ROCK_NYLIUM)
                .add(MoonBlocks.MOON_ROCK_BRICKS)
                .add(MoonBlocks.MOON_ROCK_BRICKS_SLAB)
                .add(MoonBlocks.MOON_ROCK_BRICKS_STAIRS)
                .add(MoonBlocks.CHISELED_MOON_ROCK_BRICKS)
                .add(MoonBlocks.CRACKED_MOON_ROCK_BRICKS)
                .add(ModBlock.INFESTED_CALCITE)
                .add(MoonBlocks.BLACK_MOON_ROCK)
                .add(MoonBlocks.POLISHED_BLACK_MOON_ROCK)
                .add(MoonBlocks.PURPLE_MOON_ROCK_TILES)
                .add(MoonBlocks.PRISMATIC_ORE);
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(MoonBlocks.BLACK_MOON_ROCK)
                .add(MoonBlocks.POLISHED_BLACK_MOON_ROCK)
                .add(MoonBlocks.POLISHED_BLACK_MOON_ROCK_PURPLE);
//                .add(MoonBlocks.MOON_ROCK)
//                .add(MoonBlocks.MOON_ROCK_NYLIUM)
//                .add(MoonBlocks.MOON_ROCK_BRICKS)
//                .add(MoonBlocks.CHISELED_MOON_ROCK_BRICKS)
//                .add(MoonBlocks.CRACKED_MOON_ROCK_BRICKS);
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(MoonBlocks.PRISMATIC_ORE);
        // Fence
        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(MoonBlocks.BLUE_MOON_PLANKS_FENCE)
                .add(MoonBlocks.YELLOW_MOON_PLANKS_FENCE)
                .add(MoonBlocks.MOON_PLANKS_FENCE)
                .add(MoonBlocks.PURPLE_MOON_PLANKS_FENCE)
                .add(StarBlocks.STAR_PLANKS_FENCE)
                .add(MoonBlocks.CURVE_PLANKS_FENCE)
                .add(MoonBlocks.RED_MOON_PLANKS_FENCE);
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(MoonBlocks.BLUE_MOON_PLANKS_FENCE)
                .add(MoonBlocks.YELLOW_MOON_PLANKS_FENCE)
                .add(MoonBlocks.MOON_PLANKS_FENCE)
                .add(MoonBlocks.PURPLE_MOON_PLANKS_FENCE)
                .add(StarBlocks.STAR_PLANKS_FENCE)
                .add(MoonBlocks.CURVE_PLANKS_FENCE)
                .add(MoonBlocks.RED_MOON_PLANKS_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(MoonBlocks.BLUE_MOON_PLANKS_FENCE_GATE)
                .add(MoonBlocks.MOON_PLANKS_FENCE_GATE)
                .add(MoonBlocks.PURPLE_MOON_PLANKS_FENCE_GATE)
                .add(MoonBlocks.RED_MOON_PLANKS_FENCE_GATE)
                .add(StarBlocks.STAR_PLANKS_FENCE_GATE)
                .add(MoonBlocks.CURVE_PLANKS_FENCE_GATE)
                .add(MoonBlocks.YELLOW_MOON_PLANKS_FENCE_GATE);
        getOrCreateTagBuilder(CustomTags.COPPER_BLOCKS)
                .add(Blocks.COPPER_BLOCK)
                .add(Blocks.WAXED_COPPER_BLOCK)
                .add(Blocks.CUT_COPPER)
                .add(Blocks.WAXED_CUT_COPPER)
                .add(Blocks.CHISELED_COPPER)
                .add(Blocks.WAXED_CHISELED_COPPER)
                .add(Blocks.CUT_COPPER_STAIRS)
                .add(Blocks.WAXED_CUT_COPPER_STAIRS);
        // other
        getOrCreateTagBuilder(BlockTags.MUSHROOM_GROW_BLOCK)
                .add(MoonBlocks.MOON_ROCK_NYLIUM);
    }
}
