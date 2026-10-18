package com.github.starcatcher21.stargazer.block.clases.moon.star_trap;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
//? if >= 26.2 {
/*import software.bernie.geckolib.renderer.base.GeoRenderState;
*///? }

public class StarTrapModel extends GeoModel<StarTrapEntity> {
    private final ResourceLocation model =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "geo/block/star_trap.geo.json");
    private final ResourceLocation animations =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "block/star_trap");
    private final ResourceLocation texture =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/block/star_trap.png");

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
    public ResourceLocation getModelResource(StarTrapEntity animatable) {
        return model;
    }

    @Override
    public ResourceLocation getTextureResource(StarTrapEntity animatable) {
        return texture;
    }
    //? }

    @Override
    public ResourceLocation getAnimationResource(StarTrapEntity animatable) {
        return animations;
    }
}
