package com.github.starcatcher21.stargazer.effects;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

import static com.github.starcatcher21.stargazer.mechanics.dash.DashClient.*;

public class CosmoFeeling extends StatusEffect {
    public CosmoFeeling(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        try {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            if (canRefresh(player)) {
                refresh(player, amplifier+1);
            }
        } catch (Exception ignored) {

        }
        return super.applyUpdateEffect(world, entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
