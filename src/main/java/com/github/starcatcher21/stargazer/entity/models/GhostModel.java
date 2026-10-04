package com.github.starcatcher21.stargazer.entity.models;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.DataTickets;
import com.github.starcatcher21.stargazer.entity.Ghost;
//? if >= 26.2 {
/*import software.bernie.geckolib.model.GeoModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.base.GeoRenderState;
*///? } else {
import software.bernie.geckolib.model.GeoModel;
//? }

import java.util.Locale;
import java.util.Set;
import net.minecraft.resources.ResourceLocation;

public class GhostModel extends GeoModel<Ghost> {
    private final ResourceLocation model =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "entity/ghost");
    private final ResourceLocation animations =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "entity/ghost");
    private final ResourceLocation texture =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost.png");
    private final ResourceLocation texture_blinky =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost_blinky.png");
    private final ResourceLocation texture_clyde =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost_clyde.png");
    private final ResourceLocation texture_inky =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost_inky.png");
    private final ResourceLocation texture_pinky =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost_pinky.png");
    private final ResourceLocation texture_dead =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/pacman_ghost_dead.png");
    private final ResourceLocation texture_hurt =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/pacman_ghost_hurt.png");
    public static final Set<String> pacman = Set.of("blinky", "shadow", "clyde", "pokey", "inky", "bashful", "pinky", "speedy");
    private final ResourceLocation texture_trans =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost_trans.png");
    private final ResourceLocation texture_cat =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost_cat.png");

    private final ResourceLocation texture_cipher =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost_cipher.png");
    public static final Set<String> bill = Set.of("bill", "bill cipher", "cipher", "gold", "golden triangle", "60 degrees that comes in threes");
    public static final ResourceLocation texture_finn =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost_finn.png");
    public static final ResourceLocation texture_jake =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost_jake.png");
    public static final Set<String> adventure = Set.of("finn", "finn the human", "finn martens", "jake", "jake the dog");

    //? if >= 26.2 {
    /*@Override
    public ResourceLocation getModelResource(GeoRenderState renderState) {
        return model;
    }
    *///? } else {
    @Override
    public ResourceLocation getModelResource(Ghost animatable) {
        return model;
    }
    //? }


    //? if >= 26.2 {
    /*@Override
    public ResourceLocation getTextureResource(GeoRenderState renderState) {
        String name = renderState.getGeckolibData(DataTickets.CUSTOM_NAME).toLowerCase();
        if (renderState instanceof LivingEntityRenderState entityState) {
            if (pacman.contains(name) && entityState.deathTime > 0) {
                return texture_dead;
            }
            if (pacman.contains(name) && entityState.hasRedOverlay) {
                return texture_hurt;
            }
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
            case "trans", "transgender", "estrogen", "testosterone" -> {
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
        return texture;
    }
    *///? } else {
    @Override
    public ResourceLocation getTextureResource(Ghost animatable) {
        String name = animatable.CustomName.toLowerCase();
        if (pacman.contains(name) && animatable.deathTime > 0) {
            return texture_dead;
        }
        if (pacman.contains(name) && animatable.hurtTime > 0) {
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
            case "trans", "transgender", "estrogen", "testosterone" -> {
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
        return texture;
    }
    //? }



    @Override
    public ResourceLocation getAnimationResource(Ghost animatable) {
        return animations;
    }
}
