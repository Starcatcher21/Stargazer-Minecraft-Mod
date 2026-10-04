package com.github.starcatcher21.stargazer.entity.models;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.BlackRook;
import net.minecraft.resources.ResourceLocation;
//? if >= 26.2 {
/*import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;
*///? } else {
import software.bernie.geckolib.model.GeoModel;
//? }

public class BlackRookModel extends GeoModel<BlackRook> {
    private final ResourceLocation model =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "entity/rook");
    private final ResourceLocation animations =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "entity/rook");
    private final ResourceLocation texture =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/black_rook.png");

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
    public ResourceLocation getModelResource(BlackRook animatable) {
        return model;
    }

    @Override
    public ResourceLocation getTextureResource(BlackRook animatable) {
        return texture;
    }
    //? }


    @Override
    public ResourceLocation getAnimationResource(BlackRook animatable) {
        return animations;
    }
}
