package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.EyeBat;
import com.github.starcatcher21.stargazer.entity.models.EyeBatModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class EyeBatRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<EyeBat, R> {
    public EyeBatRenderer(EntityRendererFactory.Context context) {
        super(context, new EyeBatModel());
    }
}