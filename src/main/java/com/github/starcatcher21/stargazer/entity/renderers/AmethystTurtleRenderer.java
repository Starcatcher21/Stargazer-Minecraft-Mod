package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.AmethystTurtle;
import com.github.starcatcher21.stargazer.entity.models.AmethystTurtleModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class AmethystTurtleRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<AmethystTurtle, R> {
    public AmethystTurtleRenderer(EntityRendererFactory.Context context) {
        super(context, new AmethystTurtleModel());
    }
}