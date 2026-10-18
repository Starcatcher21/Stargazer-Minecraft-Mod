package com.github.starcatcher21.stargazer.effects;

import com.github.starcatcher21.stargazer.mechanics.DamageTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class Hydrophobic extends MobEffect {
    public Hydrophobic(MobEffectCategory category, int color) {
        super(category, color);
    }

    //? if >= 26.2 {
    /*@Override
    public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier) {
    *///? } else {
    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!(entity.level() instanceof ServerLevel world)) {
            return false;
        }
    //?}
        if (world.getFluidState(entity.blockPosition()).is(FluidTags.WATER)) {
            DamageSource damageSource = new DamageSource(
                    world.registryAccess()
                            .lookupOrThrow(Registries.DAMAGE_TYPE)
                            .getOrThrow(DamageTypeRegistry.WATER_DAMAGE)
            );
            //? if >= 26.2 {
            /*entity.hurtServer(world, damageSource, amplifier + 1.0F);
             *///?} else {
            entity.hurt(damageSource, amplifier + 1.0F);
            //?}
        }
        //? if >= 26.2 {
        /*return super.applyEffectTick(world, entity, amplifier);
         *///?} else {
        return super.applyEffectTick(entity, amplifier);
        //?}
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void addAttributeModifiers(AttributeMap attributeContainer, int amplifier) {
        super.addAttributeModifiers(attributeContainer, amplifier);
    }
}
