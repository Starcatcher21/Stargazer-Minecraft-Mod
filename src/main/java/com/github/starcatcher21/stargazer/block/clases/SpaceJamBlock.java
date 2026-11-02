package com.github.starcatcher21.stargazer.block.clases;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SpaceJamBlock extends Block {
    public SpaceJamBlock(Properties settings) {
        super(settings);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
        super.stepOn(level, pos, onState, entity);
        if (entity instanceof LivingEntity le) {
            le.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 100, 1));
        }
    }

    @Override
    public void fallOn(
            Level level,
            BlockState state,
            BlockPos pos,
            Entity entity,
            //? if >= 26.2 {
            /*double fallDistance
            *///? } else {
            float fallDistance
            //? }
    ) {
    }
}
