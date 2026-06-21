package com.github.starcatcher21.stargazer.entity.models;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.AmethystTurtle;
import com.github.starcatcher21.stargazer.entity.Rook;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class RookModel extends GeoModel<Rook> {
    private final Identifier model = Identifier.of(Stargazer.MOD_ID, "entity/rook");
    private final Identifier animations = Identifier.of(Stargazer.MOD_ID, "entity/rook");
    private final Identifier texture = Identifier.of(Stargazer.MOD_ID, "textures/entity/rook.png");

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
