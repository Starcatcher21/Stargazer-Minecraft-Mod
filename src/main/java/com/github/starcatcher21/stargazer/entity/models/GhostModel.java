package com.github.starcatcher21.stargazer.entity.models;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.Ghost;
//? if >= 26.1.2 {
import com.geckolib.model.GeoModel;
import com.github.starcatcher21.stargazer.entity.DataTickets;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import com.geckolib.renderer.base.GeoRenderState;
//? } else {
/*import com.geckolib.model.GeoModel;
*///? }

import java.util.Locale;
import java.util.Set;
import net.minecraft.resources.Identifier;

public class GhostModel extends GeoModel<Ghost> {
    //? if >= 26.1.2 {
    private final Identifier animations =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "animations/entity/ghost");
    private final Identifier model =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "geo/entity/ghost");
    //? } else {
    /*private final Identifier animations =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "animations/entity/ghost.animation.json");
    private final Identifier model =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "geo/entity/ghost.geo.json");
    *///? }
    private final Identifier texture =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost.png");
    private final Identifier texture_blinky =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost_blinky.png");
    private final Identifier texture_clyde =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost_clyde.png");
    private final Identifier texture_inky =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost_inky.png");
    private final Identifier texture_pinky =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost_pinky.png");
    private final Identifier texture_dead =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/pacman_ghost_dead.png");
    private final Identifier texture_hurt =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/pacman_ghost_hurt.png");
    public static final Set<String> pacman = Set.of("blinky", "shadow", "clyde", "pokey", "inky", "bashful", "pinky", "speedy", "oikake", "machibuse", "kimagure", "otoboke");
    private final Identifier texture_trans =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost_trans.png");
    private final Identifier texture_cat =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost_cat.png");

    private final Identifier texture_cipher =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost_cipher.png");
    public static final Set<String> bill = Set.of("bill", "bill cipher", "cipher", "gold", "golden triangle", "60 degrees that comes in threes");
    public static final Identifier texture_finn =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost_finn.png");
    public static final Identifier texture_jake =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/ghost_jake.png");
    public static final Set<String> adventure = Set.of("finn", "finn the human", "finn martens", "jake", "jake the dog");

    //? if >= 26.1.2 {
    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return model;
    }
    //? } else {
    /*@Override
    public Identifier getModelResource(Ghost animatable) {
        return model;
    }
    *///? }


    //? if >= 26.1.2 {
    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
    //? } else {
    /*public Identifier getTextureResource(Ghost animatable) {
    *///? }
        //? if >= 26.1.2 {
        String name = renderState.getGeckolibData(DataTickets.CUSTOM_NAME).toLowerCase();
        if (renderState instanceof LivingEntityRenderState entityState) {
        //? } else {
        /*String name = animatable.CustomName.toLowerCase();
        var entityState = animatable;
        if (true) {
        *///? }
            if (pacman.contains(name) && entityState.deathTime > 0) {
                return texture_dead;
            }
            //? if >= 26.1.2 {
            if (pacman.contains(name) && entityState.hasRedOverlay) {
            //? } else {
            /*if (pacman.contains(name) && entityState.hurtTime > 0) {
            *///? }
                return texture_hurt;
            }
        }
        switch (name) {
            case "blinky", "shadow", "oikake" -> {
                return texture_blinky;
            }
            case "clyde", "pokey", "otoboke" -> {
                return texture_clyde;
            }
            case "inky", "bashful", "kimagure" -> {
                return texture_inky;
            }
            case "pinky", "speedy", "machibuse" -> {
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

    @Override
    public Identifier getAnimationResource(Ghost animatable) {
        return animations;
    }
}
