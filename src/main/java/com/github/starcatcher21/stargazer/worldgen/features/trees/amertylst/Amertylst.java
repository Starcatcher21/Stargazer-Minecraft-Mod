package com.github.starcatcher21.stargazer.worldgen.features.trees.amertylst;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class Amertylst extends Feature<AmertylstConfig> {
    public static final ImmutableList<Direction> OFFSET_DIRECTIONS = ImmutableList.of(
            Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST
    );
    public Amertylst(Codec<AmertylstConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<AmertylstConfig> context) {
        int l;
        int k;
        AmertylstConfig config = context.config();
        List<BlockState> mainBlock = config.mainBlock;
        List<BlockState> growOn = config.growOn;
        BlockPos blockPos = context.origin();
        int offset = config.offset;
        int size = config.size;
        boolean offsetBool = offset > 0;
        RandomSource random = context.random();
        Direction offsetDir = OFFSET_DIRECTIONS.get(random.nextIntBetweenInclusive(0, OFFSET_DIRECTIONS.size()-1));
        WorldGenLevel structureWorldAccess = context.level();
        while (structureWorldAccess.isEmptyBlock(blockPos) && blockPos.getY() > structureWorldAccess.getMinY() + 2) {
            blockPos = blockPos.below();
        }
        boolean chunks = !context.level().hasNearbyAlivePlayer(context.origin().getX(), context.origin().getY(), context.origin().getZ(), 100);
        if (!growOn.contains(structureWorldAccess.getBlockState(blockPos)) && chunks) {
            return false;
        }
        int i = random.nextInt(4) + size;
        int j = i / 4 + random.nextInt(2);
        for (k = 0; k < i; ++k) {
            float f = (1.0f - (float)k / (float)i) * (float)j;
            l = Mth.ceil(f);
            for (int m = -l; m <= l; ++m) {
                float g = (float)Mth.abs(m) - 0.25f;
                for (int n = -l; n <= l; ++n) {
                    float h = (float)Mth.abs(n) - 0.25f;
                    if ((m != 0 || n != 0) && g * g + h * h > f * f || (m == -l || m == l || n == -l || n == l) && random.nextFloat() > 0.75f) continue;
                    BlockState blockState = structureWorldAccess.getBlockState(blockPos.offset(m, k, n));
                    if (blockState.isAir() || isDirt(blockState) || growOn.contains(blockState)) {
                        if (offsetBool) {
                            this.setBlock(structureWorldAccess, blockPos.offset(m, k, n).relative(offsetDir, offset+k), mainBlock.get(random.nextIntBetweenInclusive(0, mainBlock.size() - 1)));
                        } else {
                            this.setBlock(structureWorldAccess, blockPos.offset(m, k, n), mainBlock.get(random.nextIntBetweenInclusive(0, mainBlock.size() - 1)));
                        }
                    }
                    if (k == 0 || l <= 1 || !(blockState = structureWorldAccess.getBlockState(blockPos.offset(m, -k, n))).isAir() && !isDirt(blockState) && !growOn.contains(blockState)) continue;
                    if (offsetBool) {
                        this.setBlock(structureWorldAccess, blockPos.offset(m, -k, n).relative(offsetDir, offset), mainBlock.get(random.nextIntBetweenInclusive(0, mainBlock.size() - 1)));
                    } else {
                        this.setBlock(structureWorldAccess, blockPos.offset(m, -k, n), mainBlock.get(random.nextIntBetweenInclusive(0, mainBlock.size() - 1)));
                    }
                }
            }
        }
        return true;
    }
}
