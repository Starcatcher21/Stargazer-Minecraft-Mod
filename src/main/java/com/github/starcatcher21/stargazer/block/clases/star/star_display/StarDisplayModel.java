package com.github.starcatcher21.stargazer.block.clases.star.star_display;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class StarDisplayModel extends GeoModel<StarDisplayEntity> {
    private final Identifier model = Identifier.of(Stargazer.MOD_ID, "block/star_display");
    private final Identifier animations = Identifier.of(Stargazer.MOD_ID, "block/star_display");
    private final Identifier texture = Identifier.of(Stargazer.MOD_ID, "textures/entity/star_yellow.png");

    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return model;
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return texture;
    }

    @Override
    public Identifier getAnimationResource(StarDisplayEntity animatable) {
        return animations;
    }

}

