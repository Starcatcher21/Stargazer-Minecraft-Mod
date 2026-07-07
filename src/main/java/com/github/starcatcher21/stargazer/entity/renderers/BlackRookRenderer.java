package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.BlackRook;
import com.github.starcatcher21.stargazer.entity.models.BlackRookModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class BlackRookRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<BlackRook, R> {
    public BlackRookRenderer(EntityRendererProvider.Context context) {
        super(context, new BlackRookModel());
    }
}