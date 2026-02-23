package com.github.starcatcher21.stargazer.block.clases.eyes.eyejar;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class EyeJarModel extends GeoModel<EyeJarEntity> {
    private final Identifier model = Identifier.of(Stargazer.MOD_ID, "geckolib/models/block/eye_jar");
    private final Identifier texture = Identifier.of(Stargazer.MOD_ID, "textures/block/eye_jar.png");

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
        return null;
    }

}

