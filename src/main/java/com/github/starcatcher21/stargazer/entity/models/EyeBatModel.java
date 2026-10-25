package com.github.starcatcher21.stargazer.entity.models;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.EyeBat;
import net.minecraft.resources.Identifier;
//? if >= 26.2 {
import com.geckolib.model.GeoModel;
import com.geckolib.renderer.base.GeoRenderState;
//? } else {
/*import com.geckolib.model.GeoModel;
*///? }

public class EyeBatModel extends GeoModel<EyeBat> {
    //? if >= 26.2 {
    private final Identifier animations =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "animations/entity/eye_bat");
    private final Identifier model =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "geo/entity/eye_bat");
    //? } else {
    /*private final Identifier animations =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "animations/entity/eye_bat.animation.json");
    private final Identifier model =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "geo/entity/eye_bat.geo.json");
    *///? }
    private final Identifier texture =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/eye_bat.png");

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
    public Identifier getModelResource(EyeBat animatable) {
        return model;
    }

    @Override
    public Identifier getTextureResource(EyeBat animatable) {
        return texture;
    }
    *///? }

    @Override
    public Identifier getAnimationResource(EyeBat animatable) {
        return animations;
    }
}
