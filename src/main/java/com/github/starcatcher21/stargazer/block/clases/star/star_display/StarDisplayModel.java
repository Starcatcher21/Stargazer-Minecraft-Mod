package com.github.starcatcher21.stargazer.block.clases.star.star_display;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.resources.Identifier;
import com.geckolib.model.GeoModel;
//? if >= 26.2 {
import com.geckolib.renderer.base.GeoRenderState;
//? }

public class StarDisplayModel extends GeoModel<StarDisplayEntity> {
    //? if >= 26.2 {
    private final Identifier animations = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "animations/block/star_display");
    private final Identifier model = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "geo/block/star_display");
    //? } else {
    /*private final Identifier animations = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "animations/block/star_display.animation.json");
    private final Identifier model = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "geo/block/star_display.geo.json");
    *///? }
    private final Identifier texture = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_yellow.png");

    //? if >= 26.2 {
    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return model;
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return texture;
    }
    //? } else {
    /*@Override
    public Identifier getModelResource(StarDisplayEntity animatable) {
        return model;
    }

    @Override
    public Identifier getTextureResource(StarDisplayEntity animatable) {
        return texture;
    }
    *///? }

    @Override
    public Identifier getAnimationResource(StarDisplayEntity animatable) {
        return animations;
    }

}
