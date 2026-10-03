package com.github.starcatcher21.stargazer.entity.models;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.BlackFox;
import net.minecraft.resources.Identifier;
//? if >= 26.2 {
import com.geckolib.model.GeoModel;
import com.geckolib.renderer.base.GeoRenderState;
//? } else {
/*import com.geckolib.model.GeoModel;
*///? }

public class BlackFoxModel extends GeoModel<BlackFox> {
    private final Identifier model =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "entity/black_fox");
    private final Identifier animations =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "entity/black_fox");
    private final Identifier texture =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/black_fox.png");

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
    public Identifier getModelResource(BlackFox animatable) {
        return model;
    }

    @Override
    public Identifier getTextureResource(BlackFox animatable) {
        return texture;
    }
    *///? }


    @Override
    public Identifier getAnimationResource(BlackFox animatable) {
        return animations;
    }
}
