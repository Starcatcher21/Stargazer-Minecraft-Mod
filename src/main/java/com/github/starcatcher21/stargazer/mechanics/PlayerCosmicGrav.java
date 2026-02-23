package com.github.starcatcher21.stargazer.mechanics;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.StargazerAttributes;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.worldgen.BiomeTags;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class PlayerCosmicGrav {
    public static EntityAttributeModifier gravity_modifier = new EntityAttributeModifier(Identifier.of(Stargazer.MOD_ID, "cosmic_gravity"),  -0.5F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    public static EntityAttributeModifier fall_damage_modifier = new EntityAttributeModifier(Identifier.of(Stargazer.MOD_ID, "cosmic_fall"),  10.0F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    public static EntityAttributeModifier jump_modifier = new EntityAttributeModifier(Identifier.of(Stargazer.MOD_ID, "cosmic_jump"),  0.35F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    public static EntityAttributeModifier dash_modifier = new EntityAttributeModifier(Identifier.of(Stargazer.MOD_ID, "cosmic_dash"), 1.0F, EntityAttributeModifier.Operation.ADD_VALUE);

    public static void tick(MinecraftClient client) {
        LivingEntity player = client.player;
        World world = player.getEntityWorld();
        if (world.getBiome(player.getBlockPos()).isIn(BiomeTags.MOON)) {
            applyEffect(player);
        } else {
            if (world.getBlockState(player.getBlockPos()).getBlock().equals(StarBlocks.COSMIC_BLOCK)) {
                applyEffect(player);
            } else {
                removeEffect(player);
            }
        }
    }

    public static void applyEffect(LivingEntity player) {
        try {
            player.getAttributeInstance(EntityAttributes.GRAVITY).addTemporaryModifier(gravity_modifier);
            player.getAttributeInstance(EntityAttributes.SAFE_FALL_DISTANCE).addTemporaryModifier(fall_damage_modifier);
            player.getAttributeInstance(EntityAttributes.JUMP_STRENGTH).addTemporaryModifier(jump_modifier);
            player.getAttributeInstance(StargazerAttributes.DASH_LEVEL).addTemporaryModifier(dash_modifier);
        } catch (IllegalArgumentException ignored) {

        }
    }

    public static void removeEffect(LivingEntity player) {
        player.getAttributeInstance(EntityAttributes.GRAVITY).removeModifier(Identifier.of(Stargazer.MOD_ID, "cosmic_gravity"));
        player.getAttributeInstance(EntityAttributes.SAFE_FALL_DISTANCE).removeModifier(Identifier.of(Stargazer.MOD_ID, "cosmic_fall"));
        player.getAttributeInstance(EntityAttributes.JUMP_STRENGTH).removeModifier(Identifier.of(Stargazer.MOD_ID, "cosmic_jump"));
        player.getAttributeInstance(StargazerAttributes.DASH_LEVEL).removeModifier(Identifier.of(Stargazer.MOD_ID, "cosmic_dash"));
    }
}
