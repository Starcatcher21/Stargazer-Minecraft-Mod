package com.github.starcatcher21.stargazer.entity.models;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.DataTickets;
import com.github.starcatcher21.stargazer.entity.Star;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
//? if >= 26.2 {
/*import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;
*///? } else {
import software.bernie.geckolib.model.GeoModel;
//? }

public class StarModel extends GeoModel<Star> {
    private final ResourceLocation model =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "entity/star");
    private final ResourceLocation animations =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "entity/star");
    private final ResourceLocation yellow =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_yellow.png");
    private final ResourceLocation red =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_red.png");
    private final ResourceLocation blue =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_blue.png");
    private final ResourceLocation orange =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_orange.png");
    private final ResourceLocation light_blue =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_light_blue.png");
    private final ResourceLocation lime =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_lime.png");
    private final ResourceLocation green =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_green.png");
    private final ResourceLocation black =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_black.png");
    private final ResourceLocation white =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_white.png");
    private final ResourceLocation light_gray =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_light_gray.png");
    private final ResourceLocation gray =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_gray.png");
    private final ResourceLocation pink =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_pink.png");
    private final ResourceLocation magenta =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_magenta.png");
    private final ResourceLocation purple =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_purple.png");
    private final ResourceLocation cyan =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_cyan.png");
    private final ResourceLocation brown =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_brown.png");

    //? if >= 26.2 {
    /*@Override
    public ResourceLocation getModelResource(GeoRenderState renderState) {
        return model;
    }

    @Override
    public ResourceLocation getTextureResource(GeoRenderState renderState) {
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
    *///? } else {
    @Override
    public ResourceLocation getModelResource(Star animatable) {
        return model;
    }

    @Override
    public ResourceLocation getTextureResource(Star animatable) {
        DyeColor color = animatable.getDyeColor();
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
    //? }

    @Override
    public ResourceLocation getAnimationResource(Star animatable) {
        return animations;
    }
}
