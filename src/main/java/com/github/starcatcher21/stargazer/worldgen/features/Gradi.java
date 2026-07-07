package com.github.starcatcher21.stargazer.worldgen.features;

import com.github.starcatcher21.stargazer.block.register.Darkness;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.FlowerBedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class Gradi extends Feature<NoneFeatureConfiguration> {
    public Gradi(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }
    public static final ImmutableList<Direction> DIRECTIONS = ImmutableList.of(
            Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST
    );

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        Random rand = new Random();
        BlockPos origin = context.origin();
        WorldGenLevel world = context.level();
        for (int o = 0; o < origin.getY()/10; o++) {
            BlockPos newori = origin.below(o*10);
            BlockState neworiunderstate = world.getBlockState(newori.below());
            if (world.ensureCanWrite(newori) && world.isEmptyBlock(newori) && (neworiunderstate.equals(Darkness.DYLIUM.defaultBlockState()) || neworiunderstate.equals(MoonBlocks.MOON_ROCK.defaultBlockState()))) {
                this.setBlock(world, newori, getState());
                for (int i = 0; i <= 3; i++) {
                    Direction dir = DIRECTIONS.get(i);
                    for (int j = 0; j < 4; j++) {
                        Direction randir = DIRECTIONS.get(rand.nextInt(4));
                        ArrayList<BlockPos> nex = next(world, newori.relative(dir, 1).relative(randir, 1));
                        if (!nex.isEmpty() && world.ensureCanWrite(nex.getFirst())) {
                            this.setBlock(world, nex.getFirst(), getState());
                        }
                    }
                }
            }
        }
        return true;
    }

    private BlockState getState() {
        Random rand = new Random();
        Direction direction = FlowerBedBlock.FACING.getPossibleValues().get(rand.nextInt(FlowerBedBlock.FACING.getPossibleValues().toArray().length));
        BlockState forgor = Darkness.GRADI.defaultBlockState().setValue(FlowerBedBlock.FACING, direction).setValue(FlowerBedBlock.AMOUNT, rand.nextInt(1,4));
        return forgor;
    }

    private ArrayList<BlockPos> next(WorldGenLevel world, BlockPos pos) {
        for (int i = 0; i < 3; i++) {
            if (world.ensureCanWrite(pos.below(i)) && world.isEmptyBlock(pos.below(i))) {
                BlockState state = world.getBlockState(pos.below(i+1));
                if (state.equals(MoonBlocks.MOON_ROCK.defaultBlockState()) || state.equals(Darkness.DYLIUM.defaultBlockState())) {
                    return new ArrayList<BlockPos>(Collections.singleton(pos.below(i)));
                }
            } else if (world.ensureCanWrite(pos.above(i)) && world.isEmptyBlock(pos.above(i))) {
                BlockState state = world.getBlockState(pos.above(i).below());
                if (state.equals(MoonBlocks.MOON_ROCK.defaultBlockState()) || state.equals(Darkness.DYLIUM.defaultBlockState())) {
                    return new ArrayList<BlockPos>(Collections.singleton(pos.above(i)));
                }
            }
        }
        return new ArrayList<BlockPos>();
    }
}
