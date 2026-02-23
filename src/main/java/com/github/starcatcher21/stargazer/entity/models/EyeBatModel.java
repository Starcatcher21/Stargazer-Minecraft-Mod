package com.github.starcatcher21.stargazer.entity.models;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.EyeBat;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class EyeBatModel extends GeoModel<EyeBat> {
    private final Identifier model = Identifier.of(Stargazer.MOD_ID, "geckolib/models/entity/eye_bat");
    private final Identifier animations = Identifier.of(Stargazer.MOD_ID, "geckolib/animations/entity/eye_bat");
    private final Identifier texture = Identifier.of(Stargazer.MOD_ID, "textures/entity/eye_bat.png");

    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return model;
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return texture;
    }

    @Override
    public Identifier getAnimationResource(EyeBat animatable) {
        return animations;
    }
}
