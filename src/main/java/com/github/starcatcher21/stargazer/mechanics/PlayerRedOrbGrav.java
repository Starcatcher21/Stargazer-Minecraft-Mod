package com.github.starcatcher21.stargazer.mechanics;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.worldgen.BiomeTags;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class PlayerRedOrbGrav {
    public static AttributeModifier gravity_modifier = new AttributeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "red_orb_gravity"),  0.5F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    public static AttributeModifier fall_damage_modifier = new AttributeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "red_orb_fall"),  0.1F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    public static AttributeModifier jump_modifier = new AttributeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "red_orb_jump"),  -0.15F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

    public static void tick(Minecraft client) {
        LivingEntity player = client.player;
        Level world = player.level();
        if (world.getBiome(player.blockPosition()).is(BiomeTags.RED_ORB)) {
            applyEffect(player);
        } else {
            removeEffect(player);
        }
    }

    public static void applyEffect(LivingEntity player) {
        try {
            player.getAttribute(Attributes.GRAVITY).addTransientModifier(gravity_modifier);
            player.getAttribute(Attributes.SAFE_FALL_DISTANCE).addTransientModifier(fall_damage_modifier);
            player.getAttribute(Attributes.JUMP_STRENGTH).addTransientModifier(jump_modifier);
        } catch (IllegalArgumentException ignored) {

        }
    }

    public static void removeEffect(LivingEntity player) {
        player.getAttribute(Attributes.GRAVITY).removeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "red_orb_gravity"));
        player.getAttribute(Attributes.SAFE_FALL_DISTANCE).removeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "red_orb_fall"));
        player.getAttribute(Attributes.JUMP_STRENGTH).removeModifier(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "red_orb_jump"));
    }
}
