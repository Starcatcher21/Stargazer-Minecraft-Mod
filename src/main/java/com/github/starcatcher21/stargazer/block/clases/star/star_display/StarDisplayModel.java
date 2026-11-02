package com.github.starcatcher21.stargazer.block.clases.star.star_display;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
//? if >= 26.2 {
/*import software.bernie.geckolib.renderer.base.GeoRenderState;
*///? }

public class StarDisplayModel extends GeoModel<StarDisplayEntity> {
    //? if >= 26.2 {
    /*private final ResourceLocation animations = ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "animations/block/star_display");
    private final ResourceLocation model = ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "geo/block/star_display");
    *///? } else {
    private final ResourceLocation animations = ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "animations/block/star_display.animation.json");
    private final ResourceLocation model = ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "geo/block/star_display.geo.json");
    //? }
    private final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/star_yellow.png");

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
    public ResourceLocation getModelResource(StarDisplayEntity animatable) {
        return model;
    }

    @Override
    public ResourceLocation getTextureResource(StarDisplayEntity animatable) {
        return texture;
    }
    //? }

    @Override
    public ResourceLocation getAnimationResource(StarDisplayEntity animatable) {
        return animations;
    }

}
