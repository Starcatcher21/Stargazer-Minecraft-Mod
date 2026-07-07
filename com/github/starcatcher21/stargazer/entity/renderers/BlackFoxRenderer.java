package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.BlackFox;
import com.github.starcatcher21.stargazer.entity.models.BlackFoxModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class BlackFoxRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<BlackFox, R> {
    public BlackFoxRenderer(EntityRendererFactory.Context context) {
        super(context, new BlackFoxModel());
    }
}