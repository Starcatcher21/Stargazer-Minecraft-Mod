package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.AmethystTurtle;
import com.github.starcatcher21.stargazer.entity.models.AmethystTurtleModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;

public class AmethystTurtleRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<AmethystTurtle, R> {
    public AmethystTurtleRenderer(EntityRendererProvider.Context context) {
        super(context, new AmethystTurtleModel());
    }
}