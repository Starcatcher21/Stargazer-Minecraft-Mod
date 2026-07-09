package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.BlackFox;
import com.github.starcatcher21.stargazer.entity.models.BlackFoxModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;

public class BlackFoxRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<BlackFox, R> {
    public BlackFoxRenderer(EntityRendererProvider.Context context) {
        super(context, new BlackFoxModel());
    }
}