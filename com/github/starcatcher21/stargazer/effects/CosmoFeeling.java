package com.github.starcatcher21.stargazer.effects;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.StargazerAttributes;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class CosmoFeeling extends StatusEffect {
    public static EntityAttributeModifier dash_modifier = new EntityAttributeModifier(Identifier.of(Stargazer.MOD_ID, "cosmic_potion_dash"), 1.0F, EntityAttributeModifier.Operation.ADD_VALUE);
    public CosmoFeeling(StatusEffectCategory category, int color) {
        super(category, color);
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
