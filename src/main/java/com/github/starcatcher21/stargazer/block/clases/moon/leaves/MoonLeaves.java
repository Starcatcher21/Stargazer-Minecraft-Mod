package com.github.starcatcher21.stargazer.block.clases.moon.leaves;

import com.github.starcatcher21.stargazer.block.clases.CustomLeaves;
import com.github.starcatcher21.stargazer.block.clases.moon.geode_fruit.GeodeFruit;
import com.github.starcatcher21.stargazer.block.clases.moon.geode_fruit.GeodeFruitStage;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class MoonLeaves extends CustomLeaves {
    public MoonLeaves(int tin, Properties settings) {
        super(tin, settings);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(BlockStateProperties.PERSISTENT);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (state.getValue(DISTANCE) == 7) {
            super.randomTick(state, world, pos, random);
        } else {
            if (world.getBlockState(pos.below()).isAir() && random.nextInt(45) == 0) {
                world.setBlockAndUpdate(pos.below(), MoonBlocks.GEODE_FRUIT.defaultBlockState().setValue(GeodeFruit.STAGE, GeodeFruitStage.start).setValue(GeodeFruit.FACING, GeodeFruit.FACING.getPossibleValues().get(random.nextIntBetweenInclusive(0, GeodeFruit.FACING.getPossibleValues().size() - 1))));
            }
        }
    }
}
