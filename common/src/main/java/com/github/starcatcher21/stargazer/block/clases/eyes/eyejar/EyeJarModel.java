package com.github.starcatcher21.stargazer.block.clases.eyes.eyejar;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.resources.Identifier;
import com.geckolib.model.GeoModel;
import com.geckolib.renderer.base.GeoRenderState;

public class EyeJarModel extends GeoModel<EyeJarEntity> {
    private final Identifier model = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/eye_jar");
    private final Identifier texture = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/block/eye_jar.png");
    private final Identifier animation = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "animations/block/eye_jar.animation.json");

    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return model;
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return texture;
    }

    @Override
    public Identifier getAnimationResource(EyeJarEntity animatable) {
        return animation;
    }

}

