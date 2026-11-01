package com.github.starcatcher21.stargazer.block.clases.moon.star_trap;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.resources.Identifier;
import com.geckolib.model.GeoModel;
//? if >= 26.2 {
import com.geckolib.renderer.base.GeoRenderState;
//? }

public class StarTrapModel extends GeoModel<StarTrapEntity> {
    //? if >= 26.2 {
    private final Identifier animations =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "animations/block/star_trap");
    private final Identifier model =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "geo/block/star_trap");
    //? } else {
    /*private final Identifier animations =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "animations/block/star_trap.animation.json");
    private final Identifier model =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "geo/block/star_trap.geo.json");
    *///? }
    private final Identifier texture =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/block/star_trap.png");

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
    public Identifier getModelResource(StarTrapEntity animatable) {
        return model;
    }

    @Override
    public Identifier getTextureResource(StarTrapEntity animatable) {
        return texture;
    }
    *///? }

    @Override
    public Identifier getAnimationResource(StarTrapEntity animatable) {
        return animations;
    }
}
