package com.github.starcatcher21.stargazer.block.clases;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CosmicFlower
        extends VegetationBlock
        implements SuspiciousEffectHolder {
    protected static final MapCodec<SuspiciousStewEffects> STEW_EFFECT_CODEC = SuspiciousStewEffects.CODEC.fieldOf("suspicious_stew_effects");
    public static final MapCodec<CosmicFlower> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(STEW_EFFECT_CODEC.forGetter(CosmicFlower::getSuspiciousEffects), CosmicFlower.propertiesCodec()).apply(instance, CosmicFlower::new));
    private static final VoxelShape SHAPE = Block.column(6.0, 0.0, 10.0);
    private final SuspiciousStewEffects stewEffects;

    public MapCodec<? extends CosmicFlower> codec() {
        return CODEC;
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return CustomSapling.PLACE.contains(floor.getBlock());
    }

    public CosmicFlower(Holder<MobEffect> stewEffect, float effectLengthInSeconds, BlockBehaviour.Properties settings) {
        this(CosmicFlower.createStewEffectList(stewEffect, effectLengthInSeconds), settings);
    }

    public CosmicFlower(SuspiciousStewEffects stewEffects, BlockBehaviour.Properties settings) {
        super(settings);
        this.stewEffects = stewEffects;
    }

    protected static SuspiciousStewEffects createStewEffectList(Holder<MobEffect> effect, float effectLengthInSeconds) {
        return new SuspiciousStewEffects(List.of(new SuspiciousStewEffects.Entry(effect, Mth.floor(effectLengthInSeconds * 20.0f))));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE.move(state.getOffset(pos));
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
