package com.github.starcatcher21.stargazer.mechanics;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.StargazerAttributes;
import com.github.starcatcher21.stargazer.block.register.Fluids;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.worldgen.BiomeTags;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class PlayerCosmicGrav {
    public static AttributeModifier gravity_modifier = new AttributeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic_gravity"),  -0.5F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    public static AttributeModifier fall_damage_modifier = new AttributeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic_fall"),  15.0F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    public static AttributeModifier jump_modifier = new AttributeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic_jump"),  0.35F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    public static AttributeModifier dash_modifier = new AttributeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic_dash"), 1.0F, AttributeModifier.Operation.ADD_VALUE);

    public static void tick(LivingEntity player) {
        Level world = player.level();
        if (world.getBiome(player.blockPosition()).is(BiomeTags.MOON)) {
            applyEffect(player);
            if (player.getAttributeValue(StargazerAttributes.DASH_LEVEL) != 1) {
                player.getAttribute(StargazerAttributes.DASH_LEVEL).addTransientModifier(dash_modifier);
            }
        } else {
            if (world.getBlockState(player.blockPosition()).getBlock().equals(StarBlocks.COSMIC_BLOCK) || world.getBlockState(player.blockPosition()).getBlock().equals(Fluids.DREAM)) {
                applyEffect(player);
                if (player.getAttributeValue(StargazerAttributes.DASH_LEVEL) != 1) {
                    player.getAttribute(StargazerAttributes.DASH_LEVEL).addTransientModifier(dash_modifier);
                }
            } else {
                removeEffect(player);
            }
        }
    }

    public static void applyEffect(LivingEntity player) {
        try {
            player.getAttribute(Attributes.GRAVITY).addTransientModifier(gravity_modifier);
            player.getAttribute(Attributes.SAFE_FALL_DISTANCE).addTransientModifier(fall_damage_modifier);
            player.getAttribute(Attributes.JUMP_STRENGTH).addTransientModifier(jump_modifier);
            player.getAttribute(StargazerAttributes.DASH_LEVEL).addTransientModifier(dash_modifier);
        } catch (IllegalArgumentException ignored) {

        }
    }

    public static void removeEffect(LivingEntity player) {
        player.getAttribute(Attributes.GRAVITY).removeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic_gravity"));
        player.getAttribute(Attributes.SAFE_FALL_DISTANCE).removeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic_fall"));
        player.getAttribute(Attributes.JUMP_STRENGTH).removeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic_jump"));
        player.getAttribute(StargazerAttributes.DASH_LEVEL).removeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic_dash"));
    }
}
