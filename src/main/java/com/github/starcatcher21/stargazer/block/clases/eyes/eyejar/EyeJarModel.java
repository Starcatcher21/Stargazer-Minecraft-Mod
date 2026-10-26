package com.github.starcatcher21.stargazer.block.clases.eyes.eyejar;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
//? if >= 26.2 {
/*import software.bernie.geckolib.renderer.base.GeoRenderState;
*///? }

public class EyeJarModel extends GeoModel<EyeJarEntity> {
    private final ResourceLocation model =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "geo/block/eye_jar.geo.json");
    private final ResourceLocation texture =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/block/eye_jar.png");
    private final ResourceLocation animation =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "animations/block/eye_jar.animation.json");

    //? if >= 26.2 {
    /*@Override
    public ResourceLocation getModelResource(GeoRenderState renderState) {
        return model;
    }

    @Override
    public ResourceLocation getTextureResource(GeoRenderState renderState) {
        return texture;
    }
    *///? } else {
    @Override
    public ResourceLocation getModelResource(EyeJarEntity animatable) {
        return model;
    }

    @Override
    public ResourceLocation getTextureResource(EyeJarEntity animatable) {
        return texture;
    }
    //? }


    @Override
    public ResourceLocation getAnimationResource(EyeJarEntity animatable) {
        return animation;
    }

}
