package com.github.starcatcher21.stargazer.entity.models;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.BlackFox;
import net.minecraft.resources.ResourceLocation;
//? if >= 26.2 {
/*import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;
*///? } else {
import software.bernie.geckolib.model.GeoModel;
//? }

public class BlackFoxModel extends GeoModel<BlackFox> {
    private final ResourceLocation model =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "entity/black_fox");
    private final ResourceLocation animations =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "entity/black_fox");
    private final ResourceLocation texture =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/black_fox.png");

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
    public ResourceLocation getModelResource(BlackFox animatable) {
        return model;
    }

    @Override
    public ResourceLocation getTextureResource(BlackFox animatable) {
        return texture;
    }
    //? }


    @Override
    public ResourceLocation getAnimationResource(BlackFox animatable) {
        return animations;
    }
}
