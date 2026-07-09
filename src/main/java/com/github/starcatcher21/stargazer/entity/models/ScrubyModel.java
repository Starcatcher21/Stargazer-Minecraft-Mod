package com.github.starcatcher21.stargazer.entity.models;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.Scruby;
import net.minecraft.resources.Identifier;
import com.geckolib.model.GeoModel;
import com.geckolib.renderer.base.GeoRenderState;

public class ScrubyModel extends GeoModel<Scruby> {
    private final Identifier model = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "entity/scruby");
    private final Identifier animations = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "entity/scruby");
    private final Identifier texture = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/scruby.png");

    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return model;
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return texture;
    }

    @Override
    public Identifier getAnimationResource(Scruby animatable) {
        return animations;
    }
}
