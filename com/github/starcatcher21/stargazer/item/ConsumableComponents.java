package com.github.starcatcher21.stargazer.item;

import com.github.starcatcher21.stargazer.item.ConsumeEffects.StarGazeConsume;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.item.consume.UseAction;
import net.minecraft.sound.SoundEvents;

public class ConsumableComponents {
    public static final ConsumableComponent STARGAZE = food().consumeEffect(new StarGazeConsume()).build();

    public static ConsumableComponent.Builder food() {
        return ConsumableComponent.builder().consumeSeconds(1.6F).useAction(UseAction.EAT).sound(SoundEvents.ENTITY_GENERIC_EAT).consumeParticles(true);
    }
}
