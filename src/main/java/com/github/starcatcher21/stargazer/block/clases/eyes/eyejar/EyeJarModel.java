package com.github.starcatcher21.stargazer.block.clases.eyes.eyejar;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.resources.Identifier;
import com.geckolib.model.GeoModel;
//? if >= 26.2 {
import com.geckolib.renderer.base.GeoRenderState;
//? }

public class EyeJarModel extends GeoModel<EyeJarEntity> {
    private final Identifier model =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "geo/block/eye_jar.geo.json");
    private final Identifier texture =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/block/eye_jar.png");
    private final Identifier animation =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "animations/block/eye_jar.animation.json");

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
    public Identifier getModelResource(EyeJarEntity animatable) {
        return model;
    }

    @Override
    public Identifier getTextureResource(EyeJarEntity animatable) {
        return texture;
    }
    *///? }


    @Override
    public Identifier getAnimationResource(EyeJarEntity animatable) {
        return animation;
    }

}
