package com.github.starcatcher21.stargazer.entity.models;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.AmethystTurtle;
import net.minecraft.resources.ResourceLocation;
//? if >= 26.2 {
/*import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;
*///? } else {
import software.bernie.geckolib.model.GeoModel;
//? }

public class AmethystTurtleModel extends GeoModel<AmethystTurtle> {
    private final ResourceLocation model =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "entity/amethyst_turtle");
    private final ResourceLocation animations =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "entity/amethyst_turtle");
    private final ResourceLocation texture =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/turtle.png");

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
    public ResourceLocation getModelResource(AmethystTurtle animatable) {
        return model;
    }

    @Override
    public ResourceLocation getTextureResource(AmethystTurtle animatable) {
        return texture;
    }
    //? }

    @Override
    public ResourceLocation getAnimationResource(AmethystTurtle animatable) {
        return animations;
    }
}
