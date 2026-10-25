package com.github.starcatcher21.stargazer.entity.models;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.Rook;
import net.minecraft.resources.Identifier;
//? if >= 26.2 {
import com.geckolib.model.GeoModel;
import com.geckolib.renderer.base.GeoRenderState;
//? } else {
/*import com.geckolib.model.GeoModel;
*///? }

public class RookModel extends GeoModel<Rook> {
    //? if >= 26.2 {
    private final Identifier animations =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "animations/entity/rook");
    private final Identifier model =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "geo/entity/rook");
    //? } else {
    /*private final Identifier animations =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "animations/entity/rook.animation.json");
    private final Identifier model =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "geo/entity/rook.geo.json");
    *///? }
    private final Identifier texture =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/rook.png");

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
    public Identifier getModelResource(Rook animatable) {
        return model;
    }

    @Override
    public Identifier getTextureResource(Rook animatable) {
        return texture;
    }
    *///? }


    @Override
    public Identifier getAnimationResource(Rook animatable) {
        return animations;
    }
}
