package com.github.starcatcher21.stargazer.entity.models;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.BlackFox;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class BlackFoxModel extends GeoModel<BlackFox> {
    private final Identifier model = Identifier.of(Stargazer.MOD_ID, "entity/black_fox");
    private final Identifier animations = Identifier.of(Stargazer.MOD_ID, "entity/black_fox");
    private final Identifier texture = Identifier.of(Stargazer.MOD_ID, "textures/entity/black_fox.png");

    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return model;
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return texture;
    }

    @Override
    public Identifier getAnimationResource(BlackFox animatable) {
        return animations;
    }
}
