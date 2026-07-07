package com.github.starcatcher21.stargazer.entity.models;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.DataTickets;
import com.github.starcatcher21.stargazer.entity.Star;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class StarModel extends GeoModel<Star> {
    private final Identifier model = Identifier.of(Stargazer.MOD_ID, "entity/star");
    private final Identifier animations = Identifier.of(Stargazer.MOD_ID, "entity/star");
    private final Identifier yellow = Identifier.of(Stargazer.MOD_ID, "textures/entity/star_yellow.png");
    private final Identifier red = Identifier.of(Stargazer.MOD_ID, "textures/entity/star_red.png");
    private final Identifier blue = Identifier.of(Stargazer.MOD_ID, "textures/entity/star_blue.png");
    private final Identifier orange = Identifier.of(Stargazer.MOD_ID, "textures/entity/star_orange.png");
    private final Identifier light_blue = Identifier.of(Stargazer.MOD_ID, "textures/entity/star_light_blue.png");
    private final Identifier lime = Identifier.of(Stargazer.MOD_ID, "textures/entity/star_lime.png");
    private final Identifier green = Identifier.of(Stargazer.MOD_ID, "textures/entity/star_green.png");
    private final Identifier black = Identifier.of(Stargazer.MOD_ID, "textures/entity/star_black.png");
    private final Identifier white = Identifier.of(Stargazer.MOD_ID, "textures/entity/star_white.png");
    private final Identifier light_gray = Identifier.of(Stargazer.MOD_ID, "textures/entity/star_light_gray.png");
    private final Identifier gray = Identifier.of(Stargazer.MOD_ID, "textures/entity/star_gray.png");
    private final Identifier pink = Identifier.of(Stargazer.MOD_ID, "textures/entity/star_pink.png");
    private final Identifier magenta = Identifier.of(Stargazer.MOD_ID, "textures/entity/star_magenta.png");
    private final Identifier purple = Identifier.of(Stargazer.MOD_ID, "textures/entity/star_purple.png");
    private final Identifier cyan = Identifier.of(Stargazer.MOD_ID, "textures/entity/star_cyan.png");
    private final Identifier brown = Identifier.of(Stargazer.MOD_ID, "textures/entity/star_brown.png");

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
