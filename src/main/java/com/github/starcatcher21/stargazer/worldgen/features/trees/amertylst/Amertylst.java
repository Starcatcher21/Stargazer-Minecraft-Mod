package com.github.starcatcher21.stargazer.worldgen.features.trees.amertylst;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.List;

public class Amertylst extends Feature<AmertylstConfig> {
    public static final ImmutableList<Direction> OFFSET_DIRECTIONS = ImmutableList.of(
            Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST
    );
    public Amertylst(Codec<AmertylstConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<AmertylstConfig> context) {
        int l;
        int k;
        AmertylstConfig config = context.getConfig();
        List<BlockState> mainBlock = config.mainBlock;
        List<BlockState> growOn = config.growOn;
        BlockPos blockPos = context.getOrigin();
        int offset = config.offset;
        int size = config.size;
        boolean offsetBool = offset > 0;
        Random random = context.getRandom();
        Direction offsetDir = OFFSET_DIRECTIONS.get(random.nextBetween(0, OFFSET_DIRECTIONS.size()-1));
        StructureWorldAccess structureWorldAccess = context.getWorld();
        while (structureWorldAccess.isAir(blockPos) && blockPos.getY() > structureWorldAccess.getBottomY() + 2) {
            blockPos = blockPos.down();
        }
        boolean chunks = !context.getWorld().isPlayerInRange(context.getOrigin().getX(), context.getOrigin().getY(), context.getOrigin().getZ(), 100);
        if (!growOn.contains(structureWorldAccess.getBlockState(blockPos)) && chunks) {
            return false;
        }
        int i = random.nextInt(4) + size;
        int j = i / 4 + random.nextInt(2);
        for (k = 0; k < i; ++k) {
            float f = (1.0f - (float)k / (float)i) * (float)j;
            l = MathHelper.ceil(f);
            for (int m = -l; m <= l; ++m) {
                float g = (float)MathHelper.abs(m) - 0.25f;
                for (int n = -l; n <= l; ++n) {
                    float h = (float)MathHelper.abs(n) - 0.25f;
                    if ((m != 0 || n != 0) && g * g + h * h > f * f || (m == -l || m == l || n == -l || n == l) && random.nextFloat() > 0.75f) continue;
                    BlockState blockState = structureWorldAccess.getBlockState(blockPos.add(m, k, n));
                    if (blockState.isAir() || isSoil(blockState) || growOn.contains(blockState)) {
                        if (offsetBool) {
                            this.setBlockState(structureWorldAccess, blockPos.add(m, k, n).offset(offsetDir, offset+k), mainBlock.get(random.nextBetween(0, mainBlock.size() - 1)));
                        } else {
                            this.setBlockState(structureWorldAccess, blockPos.add(m, k, n), mainBlock.get(random.nextBetween(0, mainBlock.size() - 1)));
                        }
                    }
                    if (k == 0 || l <= 1 || !(blockState = structureWorldAccess.getBlockState(blockPos.add(m, -k, n))).isAir() && !isSoil(blockState) && !growOn.contains(blockState)) continue;
                    if (offsetBool) {
                        this.setBlockState(structureWorldAccess, blockPos.add(m, -k, n).offset(offsetDir, offset), mainBlock.get(random.nextBetween(0, mainBlock.size() - 1)));
                    } else {
                        this.setBlockState(structureWorldAccess, blockPos.add(m, -k, n), mainBlock.get(random.nextBetween(0, mainBlock.size() - 1)));
                    }
                }
            }
        }
        return true;
    }
}
