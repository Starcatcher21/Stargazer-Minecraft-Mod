package com.github.starcatcher21.stargazer.worldgen.features;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerbedBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.*;

public class ForgetMeNow extends Feature<DefaultFeatureConfig> {
    public ForgetMeNow(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }
    public static final ImmutableList<Direction> DIRECTIONS = ImmutableList.of(
            Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST
    );

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        Random rand = new Random();
        BlockPos origin = context.getOrigin();
        StructureWorldAccess world = context.getWorld();
        for (int o = 0; o < origin.getY()/10; o++) {
            BlockPos newori = origin.down(o*10);
            BlockState neworiunderstate = world.getBlockState(newori.down());
            if (world.isValidForSetBlock(newori) && world.isAir(newori) && (neworiunderstate.equals(MoonBlocks.MOON_ROCK_NYLIUM.getDefaultState()) || neworiunderstate.equals(MoonBlocks.MOON_ROCK.getDefaultState()))) {
                this.setBlockState(world, newori, getState());
                for (int i = 0; i <= 3; i++) {
                    Direction dir = DIRECTIONS.get(i);
                    for (int j = 0; j < 4; j++) {
                        Direction randir = DIRECTIONS.get(rand.nextInt(4));
                        ArrayList<BlockPos> nex = next(world, newori.offset(dir, 1).offset(randir, 1));
                        if (!nex.isEmpty() && world.isValidForSetBlock(nex.getFirst())) {
                            this.setBlockState(world, nex.getFirst(), getState());
                        }
                    }
                }
            }
        }
        return true;
    }

    private BlockState getState() {
        Random rand = new Random();
        Direction direction = FlowerbedBlock.HORIZONTAL_FACING.getValues().get(rand.nextInt(FlowerbedBlock.HORIZONTAL_FACING.getValues().toArray().length));
        BlockState forgor = MoonBlocks.FORGET_ME_NOW.getDefaultState().with(FlowerbedBlock.HORIZONTAL_FACING, direction).with(FlowerbedBlock.FLOWER_AMOUNT, rand.nextInt(1,4));
        return forgor;
    }

    private ArrayList<BlockPos> next(StructureWorldAccess world, BlockPos pos) {
        for (int i = 0; i < 3; i++) {
            if (world.isValidForSetBlock(pos.down(i)) && world.isAir(pos.down(i))) {
                BlockState state = world.getBlockState(pos.down(i+1));
                if (state.equals(MoonBlocks.MOON_ROCK.getDefaultState()) || state.equals(MoonBlocks.MOON_ROCK_NYLIUM.getDefaultState())) {
                    return new ArrayList<BlockPos>(Collections.singleton(pos.down(i)));
                }
            } else if (world.isValidForSetBlock(pos.up(i)) && world.isAir(pos.up(i))) {
                BlockState state = world.getBlockState(pos.up(i).down());
                if (state.equals(MoonBlocks.MOON_ROCK.getDefaultState()) || state.equals(MoonBlocks.MOON_ROCK_NYLIUM.getDefaultState())) {
                    return new ArrayList<BlockPos>(Collections.singleton(pos.up(i)));
                }
            }
        }
        return new ArrayList<BlockPos>();
    }
}
