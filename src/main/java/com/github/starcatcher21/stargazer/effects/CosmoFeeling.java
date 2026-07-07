package com.github.starcatcher21.stargazer.effects;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.StargazerAttributes;
import java.util.Objects;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class CosmoFeeling extends MobEffect {
    public static AttributeModifier dash_modifier = new AttributeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic_potion_dash"), 1.0F, AttributeModifier.Operation.ADD_VALUE);
    public CosmoFeeling(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void removeAttributeModifiers(AttributeMap attributeContainer) {
        super.removeAttributeModifiers(attributeContainer);
        Objects.requireNonNull(attributeContainer.getInstance(StargazerAttributes.DASH_LEVEL)).removeModifier(dash_modifier);
    }

    @Override
    public void addAttributeModifiers(AttributeMap attributeContainer, int amplifier) {
        super.addAttributeModifiers(attributeContainer, amplifier);
        Objects.requireNonNull(attributeContainer.getInstance(StargazerAttributes.DASH_LEVEL)).addTransientModifier(dash_modifier);
    }
}
