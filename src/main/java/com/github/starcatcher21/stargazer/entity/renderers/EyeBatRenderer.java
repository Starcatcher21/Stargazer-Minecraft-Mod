package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.EyeBat;
import com.github.starcatcher21.stargazer.entity.models.EyeBatModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;

public class EyeBatRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<EyeBat, R> {
    public EyeBatRenderer(EntityRendererProvider.Context context) {
        super(context, new EyeBatModel());
    }
}