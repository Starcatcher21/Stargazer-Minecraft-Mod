package com.github.starcatcher21.stargazer.entity.models;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.DataTickets;
import com.github.starcatcher21.stargazer.entity.Star;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import com.geckolib.model.GeoModel;
import com.geckolib.renderer.base.GeoRenderState;

public class StarModel extends GeoModel<Star> {
    private final Identifier model = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "entity/star");
    private final Identifier animations = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "entity/star");
    private final Identifier yellow = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_yellow.png");
    private final Identifier red = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_red.png");
    private final Identifier blue = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_blue.png");
    private final Identifier orange = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_orange.png");
    private final Identifier light_blue = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_light_blue.png");
    private final Identifier lime = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_lime.png");
    private final Identifier green = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_green.png");
    private final Identifier black = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_black.png");
    private final Identifier white = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_white.png");
    private final Identifier light_gray = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_light_gray.png");
    private final Identifier gray = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_gray.png");
    private final Identifier pink = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_pink.png");
    private final Identifier magenta = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_magenta.png");
    private final Identifier purple = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_purple.png");
    private final Identifier cyan = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_cyan.png");
    private final Identifier brown = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_brown.png");

    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return model;
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        DyeColor color = renderState.getGeckolibData(DataTickets.DYE_COLOR);
        return switch (color) {
            case BLACK -> black;
            case BLUE -> blue;
            case RED -> red;
            case CYAN -> cyan;
            case GRAY -> gray;
            case LIME -> lime;
            case PINK -> pink;
            case BROWN -> brown;
            case GREEN -> green;
            case WHITE -> white;
            case ORANGE -> orange;
            case PURPLE -> purple;
            case YELLOW -> yellow;
            case MAGENTA -> magenta;
            case LIGHT_BLUE -> light_blue;
            case LIGHT_GRAY -> light_gray;
            case null, default -> yellow;
        };
    }

    @Override
    public Identifier getAnimationResource(Star animatable) {
        return animations;
    }
}
