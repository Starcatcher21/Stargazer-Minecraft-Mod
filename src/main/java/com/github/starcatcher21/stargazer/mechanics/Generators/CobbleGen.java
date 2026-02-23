package com.github.starcatcher21.stargazer.mechanics.Generators;

import com.github.starcatcher21.stargazer.Helpers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import static net.minecraft.block.FluidBlock.FLOW_DIRECTIONS;

public class CobbleGen {
    public static final Codec<CobbleGen> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            TagKey.codec(RegistryKeys.FLUID).fieldOf("fluid").forGetter(CobbleGen::getFluid),
            BlockState.CODEC.fieldOf("lava").forGetter(CobbleGen::getLava),
            BlockState.CODEC.fieldOf("obsidian").forGetter(CobbleGen::getObs),
            BlockState.CODEC.fieldOf("cobble").forGetter(CobbleGen::getCobble)
    ).apply(instance, CobbleGen::new));


    public final BlockState LAVA;
    public final BlockState BLOCKOBS;
    public final BlockState BLOCKCOBBLE;
    public final TagKey<Fluid> FLUID;

    private BlockState getCobble() {
        return this.BLOCKCOBBLE;
    }

    private BlockState getObs() {
        return this.BLOCKOBS;
    }

    private BlockState getLava() {
        return this.LAVA;
    }

    private TagKey<Fluid> getFluid() {
        return this.FLUID;
    }

    public CobbleGen(TagKey<Fluid> fluid, BlockState lava, BlockState Obs, BlockState Cobble) {
        LAVA = lava;
        BLOCKCOBBLE = Cobble;
        BLOCKOBS = Obs;
        FLUID = fluid;
    }

    public boolean gen(World world, BlockPos pos) {
        for (Direction direction : FLOW_DIRECTIONS) {
            if (Helpers.isColliding(LAVA.getBlock(), world, pos, direction)) {
                BlockPos blockPos = pos.offset(direction.getOpposite());
                if (world.getFluidState(blockPos).isIn(FLUID)) {
                    BlockState block = world.getFluidState(pos).isStill() ? BLOCKOBS : BLOCKCOBBLE;
                    if (direction == Direction.DOWN) {
                        BlockPos newPos = Helpers.getCollidingPosition(LAVA.getBlock(), world, pos, FLOW_DIRECTIONS);
                        world.setBlockState(newPos, BLOCKOBS);
                    } else {
                        world.setBlockState(pos, block);
                    }
                    return false;
                }
            }
        }
        if (world.getFluidState(pos).isStill() && world.getFluidState(pos).isIn(FLUID)) {
            if (Helpers.isCollidingAny(LAVA.getBlock(), world, pos, FLOW_DIRECTIONS)) {
                BlockState block = world.getFluidState(pos).isStill() ? BLOCKOBS : BLOCKCOBBLE;
                BlockPos newPos = Helpers.getCollidingPosition(LAVA.getBlock(), world, pos, FLOW_DIRECTIONS);
                world.setBlockState(newPos, block);
                return false;
            }
        }
        return true;
    }
}
