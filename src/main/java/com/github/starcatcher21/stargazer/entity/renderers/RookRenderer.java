package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.Rook;
import com.github.starcatcher21.stargazer.entity.models.RookModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;

public class RookRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<Rook, R> {
    public RookRenderer(EntityRendererProvider.Context context) {
        super(context, new RookModel());
    }
}