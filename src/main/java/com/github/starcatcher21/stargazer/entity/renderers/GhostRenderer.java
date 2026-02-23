package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.Ghost;
import com.github.starcatcher21.stargazer.entity.models.GhostModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class GhostRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<Ghost, R> {

    public GhostRenderer(EntityRendererFactory.Context context) {
        super(context, new GhostModel());
        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }
}