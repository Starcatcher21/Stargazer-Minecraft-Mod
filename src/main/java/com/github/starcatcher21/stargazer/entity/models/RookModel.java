package com.github.starcatcher21.stargazer.entity.models;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.Rook;
import net.minecraft.resources.Identifier;
import com.geckolib.model.GeoModel;
import com.geckolib.renderer.base.GeoRenderState;

public class RookModel extends GeoModel<Rook> {
    private final Identifier model = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "entity/rook");
    private final Identifier animations = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "entity/rook");
    private final Identifier texture = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/rook.png");

    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return model;
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return texture;
    }

    @Override
    public Identifier getAnimationResource(Rook animatable) {
        return animations;
    }
}
