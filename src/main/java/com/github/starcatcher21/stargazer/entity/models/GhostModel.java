package com.github.starcatcher21.stargazer.entity.models;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.Ghost;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.Set;

public class GhostModel extends GeoModel<Ghost> {
    private final Identifier model = Identifier.of(Stargazer.MOD_ID, "geckolib/models/entity/ghost");
    private final Identifier animations = Identifier.of(Stargazer.MOD_ID, "geckolib/animations/entity/ghost");
    private final Identifier texture = Identifier.of(Stargazer.MOD_ID, "textures/entity/ghost.png");
    private final Identifier texture_blinky = Identifier.of(Stargazer.MOD_ID, "textures/entity/ghost_blinky.png");
    private final Identifier texture_clyde = Identifier.of(Stargazer.MOD_ID, "textures/entity/ghost_clyde.png");
    private final Identifier texture_inky = Identifier.of(Stargazer.MOD_ID, "textures/entity/ghost_inky.png");
    private final Identifier texture_pinky = Identifier.of(Stargazer.MOD_ID, "textures/entity/ghost_pinky.png");
    private final Identifier texture_dead = Identifier.of(Stargazer.MOD_ID, "textures/entity/pacman_ghost_dead.png");
    private final Identifier texture_hurt = Identifier.of(Stargazer.MOD_ID, "textures/entity/pacman_ghost_hurt.png");
    public static final Set<String> pacman = Set.of("blinky", "shadow", "clyde", "pokey", "inky", "bashful", "pinky", "speedy");
    private final Identifier texture_trans = Identifier.of(Stargazer.MOD_ID, "textures/entity/ghost_trans.png");
    private final Identifier texture_cat = Identifier.of(Stargazer.MOD_ID, "textures/entity/ghost_cat.png");

    private final Identifier texture_cipher = Identifier.of(Stargazer.MOD_ID, "textures/entity/ghost_cipher.png");
    public static final Identifier texture_finn = Identifier.of(Stargazer.MOD_ID, "textures/entity/ghost_finn.png");
    public static final Identifier texture_jake = Identifier.of(Stargazer.MOD_ID, "textures/entity/ghost_jake.png");

    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return model;
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        if (renderState instanceof LivingEntityRenderState entityState && entityState.customName != null) {
            String name = entityState.customName.getString().toLowerCase();
            if (pacman.contains(name) && entityState.deathTime > 0) {
                return texture_dead;
            }
            if (pacman.contains(name) && entityState.hurt) {
                return texture_hurt;
            }
            switch (name) {
                case "blinky", "shadow" -> {
                    return texture_blinky;
                }
                case "clyde", "pokey" -> {
                    return texture_clyde;
                }
                case "inky", "bashful" -> {
                    return texture_inky;
                }
                case "pinky", "speedy" -> {
                    return texture_pinky;
                }
                case "trans", "estrogen", "testosterone" -> {
                    return texture_trans;
                }
                case "cat" -> {
                    return texture_cat;
                }
                case "bill", "bill cipher", "cipher", "gold", "golden triangle", "60 degrees that comes in threes"-> {
                    return texture_cipher;
                }
                case "finn", "finn the human", "finn martens" -> {
                    return texture_finn;
                }
                case "jake", "jake the dog" -> {
                    return texture_jake;
                }
            }
        }
        return texture;
    }

    @Override
    public Identifier getAnimationResource(Ghost animatable) {
        return animations;
    }

    @Override
    public @Nullable RenderLayer getRenderType(GeoRenderState renderState, Identifier texture) {
        return RenderLayer.getEntityTranslucent(texture);
    }
}
