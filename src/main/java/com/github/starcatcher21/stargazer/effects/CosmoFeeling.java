package com.github.starcatcher21.stargazer.effects;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.StargazerAttributes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import java.util.Objects;

import static com.github.starcatcher21.stargazer.mechanics.dash.DashClient.canRefresh;
import static com.github.starcatcher21.stargazer.mechanics.dash.DashClient.refresh;

public class CosmoFeeling extends StatusEffect {
    public static EntityAttributeModifier dash_modifier = new EntityAttributeModifier(Identifier.of(Stargazer.MOD_ID, "cosmic_potion_dash"), 1.0F, EntityAttributeModifier.Operation.ADD_VALUE);
    public CosmoFeeling(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        return super.applyUpdateEffect(world, entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }


    @Override
    public void onRemoved(AttributeContainer attributeContainer) {
        super.onRemoved(attributeContainer);
        Objects.requireNonNull(attributeContainer.getCustomInstance(StargazerAttributes.DASH_LEVEL)).removeModifier(dash_modifier);
    }

    @Override
    public void onApplied(AttributeContainer attributeContainer, int amplifier) {
        super.onApplied(attributeContainer, amplifier);
        Objects.requireNonNull(attributeContainer.getCustomInstance(StargazerAttributes.DASH_LEVEL)).addTemporaryModifier(dash_modifier);
    }
}
