package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.Rook;
import com.github.starcatcher21.stargazer.entity.models.RookModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class RookRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<Rook, R> {
    public RookRenderer(EntityRendererFactory.Context context) {
        super(context, new RookModel());
    }
}