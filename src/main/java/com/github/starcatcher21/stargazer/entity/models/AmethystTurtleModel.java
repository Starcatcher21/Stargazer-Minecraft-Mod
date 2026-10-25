package com.github.starcatcher21.stargazer.entity.models;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.AmethystTurtle;
import net.minecraft.resources.Identifier;
//? if >= 26.2 {
import com.geckolib.model.GeoModel;
import com.geckolib.renderer.base.GeoRenderState;
//? } else {
/*import com.geckolib.model.GeoModel;
*///? }

public class AmethystTurtleModel extends GeoModel<AmethystTurtle> {
    //? if >= 26.2 {
    private final Identifier animations =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "animations/entity/amethyst_turtle");
    private final Identifier model =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "geo/entity/amethyst_turtle");
    //? } else {
    /*private final Identifier animations =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "animations/entity/amethyst_turtle.animation.json");
    private final Identifier model =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "geo/entity/amethyst_turtle.geo.json");
    *///? }
    private final Identifier texture =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/turtle.png");

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
    public Identifier getModelResource(AmethystTurtle animatable) {
        return model;
    }

    @Override
    public Identifier getTextureResource(AmethystTurtle animatable) {
        return texture;
    }
    *///? }

    @Override
    public Identifier getAnimationResource(AmethystTurtle animatable) {
        return animations;
    }
}
