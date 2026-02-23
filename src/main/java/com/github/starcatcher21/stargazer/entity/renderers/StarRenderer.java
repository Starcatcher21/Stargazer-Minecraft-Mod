package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.Star;
import com.github.starcatcher21.stargazer.entity.models.StarModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class StarRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<Star, R> {
    public StarRenderer(EntityRendererFactory.Context context) {
        super(context, new StarModel());
    }
}