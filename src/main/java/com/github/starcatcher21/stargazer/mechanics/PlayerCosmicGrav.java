package com.github.starcatcher21.stargazer.mechanics;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.StargazerAttributes;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.worldgen.BiomeTags;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class PlayerCosmicGrav {
    public static AttributeModifier gravity_modifier = new AttributeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic_gravity"), -0.5F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    public static AttributeModifier fall_damage_modifier = new AttributeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic_fall"), 10.0F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    public static AttributeModifier jump_modifier = new AttributeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic_jump"), 0.35F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    public static AttributeModifier dash_modifier = new AttributeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic_dash"), 1.0F, AttributeModifier.Operation.ADD_VALUE);

    public static void tick(LivingEntity player) {
        Level world = player.level();
        if (world.getBiome(player.blockPosition()).is(BiomeTags.MOON)) {
            applyEffect(player);
        } else {
            if (world.getBlockState(player.blockPosition()).getBlock().equals(StarBlocks.COSMIC_BLOCK.get())) {
                applyEffect(player);
            } else {
                removeEffect(player);
            }
        }
    }

    public static void applyEffect(LivingEntity player) {
        safeAddModifier(player, Attributes.GRAVITY, gravity_modifier);
        safeAddModifier(player, Attributes.SAFE_FALL_DISTANCE, fall_damage_modifier);
        safeAddModifier(player, Attributes.JUMP_STRENGTH, jump_modifier);
        safeAddModifier(player, StargazerAttributes.DASH_LEVEL, dash_modifier);
    }

    private static void safeAddModifier(LivingEntity player, net.minecraft.core.Holder<net.minecraft.world.entity.ai.attributes.Attribute> attribute, AttributeModifier modifier) {
        AttributeInstance instance = player.getAttribute(attribute);
        if (instance != null && !instance.hasModifier(modifier.id())) {
            instance.addTransientModifier(modifier);
        }
    }

    public static void removeEffect(LivingEntity player) {
        safeRemoveModifier(player, Attributes.GRAVITY, gravity_modifier.id());
        safeRemoveModifier(player, Attributes.SAFE_FALL_DISTANCE, fall_damage_modifier.id());
        safeRemoveModifier(player, Attributes.JUMP_STRENGTH, jump_modifier.id());
        safeRemoveModifier(player, StargazerAttributes.DASH_LEVEL, dash_modifier.id());
    }

    private static void safeRemoveModifier(LivingEntity player, net.minecraft.core.Holder<net.minecraft.world.entity.ai.attributes.Attribute> attribute, Identifier id) {
        AttributeInstance instance = player.getAttribute(attribute);
        if (instance != null && instance.hasModifier(id)) {
            instance.removeModifier(id);
        }
    }
}