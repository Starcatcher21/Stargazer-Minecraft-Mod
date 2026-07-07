package com.github.starcatcher21.stargazer.item;

import com.github.starcatcher21.stargazer.item.ConsumeEffects.StarGazeConsume;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;

public class ConsumableComponents {
    public static final Consumable STARGAZE = food().onConsume(new StarGazeConsume()).build();

    public static Consumable.Builder food() {
        return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true);
    }
}
