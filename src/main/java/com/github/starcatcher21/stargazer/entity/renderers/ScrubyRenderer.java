package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.Scruby;
import com.github.starcatcher21.stargazer.entity.models.ScrubyModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;

public class ScrubyRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<Scruby, R> {
    public ScrubyRenderer(EntityRendererProvider.Context context) {
        super(context, new ScrubyModel());
    }
}