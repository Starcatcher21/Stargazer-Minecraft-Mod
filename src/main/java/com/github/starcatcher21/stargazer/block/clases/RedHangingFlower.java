package com.github.starcatcher21.stargazer.block.clases;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RedHangingFlower
        extends VegetationBlock
        implements SuspiciousEffectHolder {
    protected static final MapCodec<SuspiciousStewEffects> STEW_EFFECT_CODEC = SuspiciousStewEffects.CODEC.fieldOf("suspicious_stew_effects");
    public static final MapCodec<RedHangingFlower> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(STEW_EFFECT_CODEC.forGetter(RedHangingFlower::getSuspiciousEffects), RedHangingFlower.propertiesCodec()).apply(instance, RedHangingFlower::new));
    private static final VoxelShape SHAPE = Block.column(6.0, 0.0, 10.0);
    private final SuspiciousStewEffects stewEffects;

    public MapCodec<? extends RedHangingFlower> codec() {
        return CODEC;
    }

    @Override
	protected boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
		BlockPos attachedToPos = pos.relative(Direction.UP);
		BlockState attachedToState = level.getBlockState(attachedToPos);
		return this.canAttachTo(attachedToState) && attachedToState.isFaceSturdy(level, attachedToPos, Direction.DOWN);
	}

	@Override
	protected void tick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
		if (!state.canSurvive(level, pos)) {
			level.destroyBlock(pos, true);
		}
	}

    protected boolean canAttachTo(final BlockState state) {
        return CustomRedSapling.PLACE.contains(state.getBlock());
    }

    public RedHangingFlower(Holder<MobEffect> stewEffect, float effectLengthInSeconds, Properties settings) {
        this(RedHangingFlower.createStewEffectList(stewEffect, effectLengthInSeconds), settings);
    }

    public RedHangingFlower(SuspiciousStewEffects stewEffects, Properties settings) {
        super(settings);
        this.stewEffects = stewEffects;
    }

    protected static SuspiciousStewEffects createStewEffectList(Holder<MobEffect> effect, float effectLengthInSeconds) {
        return new SuspiciousStewEffects(List.of(new SuspiciousStewEffects.Entry(effect, Mth.floor(effectLengthInSeconds * 20.0f))));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE.move(state.getOffset(pos)).move(0.0, 0.5, 0.0);
    }

    @Override
    public SuspiciousStewEffects getSuspiciousEffects() {
        return this.stewEffects;
    }

    @Nullable
    public MobEffectInstance getContactEffect() {
        return null;
    }
}
