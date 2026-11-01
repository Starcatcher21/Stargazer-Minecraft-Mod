package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.Scruby;
import com.github.starcatcher21.stargazer.entity.models.ScrubyModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import com.geckolib.renderer.GeoEntityRenderer;
//? if >= 26.2 {
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import com.geckolib.renderer.base.GeoRenderState;
//? }

//? if >= 26.2 {
public class ScrubyRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<Scruby, R> {
//? } else {
/*public class ScrubyRenderer extends GeoEntityRenderer<Scruby> {
*///? }
    public ScrubyRenderer(EntityRendererProvider.Context context) {
        super(context, new ScrubyModel());
    }
}
