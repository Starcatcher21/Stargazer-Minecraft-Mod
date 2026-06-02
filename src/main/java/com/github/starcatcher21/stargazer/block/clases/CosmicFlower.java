package com.github.starcatcher21.stargazer.block.clases;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CosmicFlower
        extends PlantBlock
        implements SuspiciousStewIngredient {
    protected static final MapCodec<SuspiciousStewEffectsComponent> STEW_EFFECT_CODEC = SuspiciousStewEffectsComponent.CODEC.fieldOf("suspicious_stew_effects");
    public static final MapCodec<CosmicFlower> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(STEW_EFFECT_CODEC.forGetter(CosmicFlower::getStewEffects), CosmicFlower.createSettingsCodec()).apply(instance, CosmicFlower::new));
    private static final VoxelShape SHAPE = Block.createColumnShape(6.0, 0.0, 10.0);
    private final SuspiciousStewEffectsComponent stewEffects;

    public MapCodec<? extends CosmicFlower> getCodec() {
        return CODEC;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return CustomSapling.PLACE.contains(floor.getBlock());
    }

    public CosmicFlower(RegistryEntry<StatusEffect> stewEffect, float effectLengthInSeconds, AbstractBlock.Settings settings) {
        this(CosmicFlower.createStewEffectList(stewEffect, effectLengthInSeconds), settings);
    }

    public CosmicFlower(SuspiciousStewEffectsComponent stewEffects, AbstractBlock.Settings settings) {
        super(settings);
        this.stewEffects = stewEffects;
    }

    protected static SuspiciousStewEffectsComponent createStewEffectList(RegistryEntry<StatusEffect> effect, float effectLengthInSeconds) {
        return new SuspiciousStewEffectsComponent(List.of(new SuspiciousStewEffectsComponent.StewEffect(effect, MathHelper.floor(effectLengthInSeconds * 20.0f))));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE.offset(state.getModelOffset(pos));
    }

    @Override
    public SuspiciousStewEffectsComponent getStewEffects() {
        return this.stewEffects;
    }

    @Nullable
    public StatusEffectInstance getContactEffect() {
        return null;
    }
}
